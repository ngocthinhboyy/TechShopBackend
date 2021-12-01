package com.techshopbe.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.techshopbe.dto.AttributeDTO;
import com.techshopbe.dto.DetailedProductDTO;
import com.techshopbe.dto.ProductDTO;
import com.techshopbe.dto.ProductRequestDTO;
import com.techshopbe.dto.ProductSpecificationDTO;
import com.techshopbe.dto.RatingInfoDTO;
import com.techshopbe.dto.SpecificationAttributeValueDTO;
import com.techshopbe.entity.Product;
import com.techshopbe.repository.ProductRepository;
import com.techshopbe.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<ProductDTO> getAll() {
		return productRepository.getAll();
	}

	@Override
	public List<ProductDTO> getTrendingProducts() {
		List<ProductDTO> products = productRepository.findTrendingProducts();
		List<ProductDTO> trendingProducts = new ArrayList<ProductDTO>();
		for (int i = 0; i < 8; i++) {
			trendingProducts.add(products.get(i));
		}
		return trendingProducts;
	}

	@Override
	public List<ProductDTO> getTopPurchasedProducts(int categoryID) {

		List<ProductDTO> productsByCategory = productRepository.findTopPurchasedByCategoryId(categoryID);
		List<ProductDTO> topPurchasedProducts = new ArrayList<ProductDTO>();
		for (int i = 0; i < 5; i++) {
			topPurchasedProducts.add(productsByCategory.get(i));
		}
		return topPurchasedProducts;

	}

	@Override
	public List<ProductDTO> getProductsByCategory(String categorySlug) {

		return productRepository.findByCategorySlug(categorySlug);

	}

	@Override
	public DetailedProductDTO getDetailedProduct(String productID) {
		DetailedProductDTO detailedProduct = productRepository.findDetailedProductByid(productID);
		List<Object[]> specs = productRepository.findSpecificationsByid(productID);
		List<ProductSpecificationDTO> productSpecsList = new ArrayList<ProductSpecificationDTO>();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean isAdminRole = auth.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

		for (Object[] spec : specs) {
			ProductSpecificationDTO specsDto = new ProductSpecificationDTO();

			if (isAdminRole) {
				specsDto.setId(spec[0].toString());
				specsDto.setDataType(spec[3].toString());
			}
			specsDto.setName(spec[1].toString());
			specsDto.setValue(spec[2].toString());

			productSpecsList.add(specsDto);
		}
		detailedProduct.setSpecifications(productSpecsList);
		if (detailedProduct.getStock() > 0)
			detailedProduct.setStockStatus("in-stock");
		else if (detailedProduct.getStock() == 0)
			detailedProduct.setStockStatus("out-of-stock");
		return detailedProduct;
	}

	@Override
	public List<ProductDTO> getRelatedCategoryProducts(String productID) {
		List<ProductDTO> productsByCategory = productRepository.findRelatedProductsByCategory(productID);
		List<ProductDTO> relatedProducts = new ArrayList<ProductDTO>();
		for (int i = 0; i < productsByCategory.size() && i < 4; i++) {
			relatedProducts.add(productsByCategory.get(i));
		}
		return relatedProducts;
	}

	@Override
	public List<ProductDTO> getRelatedBrandProducts(String productID) {
		List<ProductDTO> productsByBrand = productRepository.findRelatedProductsByBrand(productID);
		List<ProductDTO> relatedProducts = new ArrayList<ProductDTO>();

		for (int i = 0; i < productsByBrand.size() && i < 4; i++) {
			relatedProducts.add(productsByBrand.get(i));
		}
		return relatedProducts;
	}

	@Override
	public void updateRating(String productID, float rate) {
		RatingInfoDTO ratingInfoDTO = productRepository.findRatingInfoByid(productID);
		int newTotalReviews = ratingInfoDTO.getTotalReviews() + 1;
		float newRating = (ratingInfoDTO.getProductRate() * ratingInfoDTO.getTotalReviews() + rate) / newTotalReviews;
		productRepository.updateRatingInfoByid(newRating, newTotalReviews, productID);
	}

	@Override
	public List<SpecificationAttributeValueDTO> getProductSpecificationAttribute(String categoryID, String brandID) {
		List<Object[]> objects = productRepository.getSpecificationAttributeBycategoryIDAndbrandID(categoryID, brandID);
		List<SpecificationAttributeValueDTO> specifcationAttributes = new ArrayList<SpecificationAttributeValueDTO>();
		for (Object[] object : objects) {
			specifcationAttributes.add(new SpecificationAttributeValueDTO(object[0].toString(), object[1].toString(),
					object[2].toString()));
		}
		return specifcationAttributes;
	}

	@Transactional
	@Override
	public void add(ProductRequestDTO product) {
		// save new product first. if not, other tables cannot use productID
		String productID = UUID.randomUUID().toString();
		Product newProduct = new Product(product);
		newProduct.setId(productID);

		List<AttributeDTO> existedAttributes = product.getExistedAttributes();
		List<ProductSpecificationDTO> newSpecifications = product.getNewSpecifications();
		String attrSetID = "";

		attrSetID = productRepository.getAttributeSet(product.getCategory(), product.getBrand());

		if (attrSetID.equals("")) {
			// new attribute set
			attrSetID = UUID.randomUUID().toString();
			// save attribute set
			String attrSetName = "attr_set_" + product.getCategory() + "_" + product.getBrand();
			productRepository.addNewAttributeSet(attrSetID, product.getCategory(), product.getBrand(), attrSetName);
		}
		newProduct.setAttributeSetID(attrSetID);
		productRepository.save(newProduct);
		if (existedAttributes.size() != 0) {
			for (AttributeDTO attribute : existedAttributes) {
				addNewAttributeValue(attribute, productID);
			}
		}

		if (newSpecifications.size() != 0) {

			List<SpecificationAttributeValueDTO> existedAttributesDB = getProductSpecificationAttribute(
					product.getCategory(), product.getBrand());
			for (ProductSpecificationDTO newSpecs : newSpecifications) {
				boolean isExisted = false;

				// existed specs -> new value
				for (SpecificationAttributeValueDTO existedAttributeDB : existedAttributesDB) {
					if (existedAttributeDB.getName().equalsIgnoreCase(newSpecs.getName())
							&& existedAttributeDB.getDataType().equalsIgnoreCase(newSpecs.getDataType())) {
						isExisted = true;
						AttributeDTO attributeDTO = new AttributeDTO(existedAttributeDB.getId(),
								existedAttributeDB.getDataType(), newSpecs.getValue());
						addNewAttributeValue(attributeDTO, productID);
						break;
					}
				}

				if (!isExisted) {
					// create ATTRIBUTE
					String attrID = UUID.randomUUID().toString();
					productRepository.addAttribute(attrID, newSpecs.getName(), newSpecs.getName(), "",
							newSpecs.getDataType(), attrSetID, true);

					// create VALUE
					AttributeDTO attributeDTO = new AttributeDTO(attrID, newSpecs.getDataType(), newSpecs.getValue());
					addNewAttributeValue(attributeDTO, productID);
				}
			}

		}

	}

	public void addNewAttributeValue(AttributeDTO attribute, String productID) {

		String attrValueID = UUID.randomUUID().toString();
		String attrID = attribute.getId();
		String attrValue = attribute.getValue();

		switch (attribute.getDataType()) {
		case "FLOAT":
			productRepository.addFloatAttrValue(attrValueID, Float.parseFloat(attrValue), attrID, productID);
			break;
		case "VARCHAR":
			productRepository.addVarCharAttrValue(attrValueID, attrValue, attrID, productID);
			break;
		case "TEXT":
			productRepository.addTextAttrValue(attrValueID, attrValue, attrID, productID);
			break;
		case "BOOL":
			boolean attrBoolValue = false;
			if (attrValue.equals("Yes")) {
				attrBoolValue = true;
			}
			productRepository.addBoolAttrValue(attrValueID, attrBoolValue, attrID, productID);
			break;
		case "INT":
			productRepository.addIntAttrValue(attrValueID, Integer.parseInt(attrValue), attrID, productID);
			break;

		}
	}

	@Override
	public void update(ProductRequestDTO product) throws Exception {
		String productID = product.getId();
		Product newProduct = productRepository.findByIdAndIsDeleted(productID, false);

		if (newProduct == null) {
			throw new Exception("Product is deleted or not existed.");
		}
		newProduct.setPrice(product.getPrice());
		newProduct.setShortDescrip(product.getShortDescription());
		newProduct.setStock(product.getStock());
		newProduct.setWarranty(product.getWarranty());

		String attrSetID = productRepository.getAttributeSet(newProduct.getCategoryID(), newProduct.getBrandID());

		List<AttributeDTO> existedAttributes = product.getExistedAttributes();
		List<ProductSpecificationDTO> newSpecifications = product.getNewSpecifications();

		if (newSpecifications.size() != 0) {
			List<SpecificationAttributeValueDTO> existedAttributesDB = getProductSpecificationAttribute(
					newProduct.getCategoryID(), newProduct.getBrandID());
			for (ProductSpecificationDTO newSpecs : newSpecifications) {
				boolean isExisted = false;

				// existed specs -> new value
				for (SpecificationAttributeValueDTO existedAttributeDB : existedAttributesDB) {
					if (existedAttributeDB.getName().equalsIgnoreCase(newSpecs.getName())
							&& existedAttributeDB.getDataType().equalsIgnoreCase(newSpecs.getDataType())) {
						isExisted = true;
						AttributeDTO attributeDTO = new AttributeDTO(existedAttributeDB.getId(),
								existedAttributeDB.getDataType(), newSpecs.getValue());
						addNewAttributeValue(attributeDTO, productID);
						break;
					}
				}

				if (!isExisted) {
					// create ATTRIBUTE
					String attrID = UUID.randomUUID().toString();
					productRepository.addAttribute(attrID, newSpecs.getName(), newSpecs.getName(), "",
							newSpecs.getDataType(), attrSetID, true);

					// create VALUE
					AttributeDTO attributeDTO = new AttributeDTO(attrID, newSpecs.getDataType(), newSpecs.getValue());
					addNewAttributeValue(attributeDTO, productID);
				}
			}

		}

		// update existed value
		if (existedAttributes.size() != 0) {
			for (AttributeDTO attribute : existedAttributes) {
				updateAttributeValue(attribute);
			}
		}
	}

	public void updateAttributeValue(AttributeDTO attribute) {
		String attrValueID = attribute.getId();
		String attrValue = attribute.getValue();

		switch (attribute.getDataType()) {
		case "FLOAT":
			productRepository.updateFloatAttrValue(attrValueID, Float.parseFloat(attrValue));
			break;
		case "VARCHAR":
			productRepository.updateVarCharAttrValue(attrValueID, attrValue);
			break;
		case "TEXT":
			productRepository.updateTextAttrValue(attrValueID, attrValue);
			break;
		case "BOOL":
			boolean attrBoolValue = false;
			if (attrValue.equals("Yes")) {
				attrBoolValue = true;
			}

			productRepository.updateBoolAttrValue(attrValueID, attrBoolValue);
			break;
		case "INT":
			productRepository.updateIntAttrValue(attrValueID, Integer.parseInt(attrValue));
			break;

		}
	}

	@Override
	public void delete(String id) throws Exception {

		Product deleteProduct = productRepository.findByIdAndIsDeleted(id, false);
		if (deleteProduct == null) {
			throw new Exception("Product is already deleted.");
		}
		deleteProduct.setDeleted(true);
		productRepository.save(deleteProduct);
	}

}
