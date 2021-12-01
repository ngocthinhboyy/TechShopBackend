package com.techshopbe.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.techshopbe.dto.AttributeDTO;
import com.techshopbe.dto.DetailedProductDTO;
import com.techshopbe.dto.ProductDTO;
import com.techshopbe.dto.RatingInfoDTO;
import com.techshopbe.dto.SpecificationAttributeValueDTO;
import com.techshopbe.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT new com.techshopbe.dto.ProductDTO(p.id, c.name, b.name, p.rate, p.name, p.price, p.stock, p.warranty, p.purchased, c.slug, p.images, p.shortDescrip) FROM Product p, Category c, Brand b WHERE p.categoryID = c.id AND p.brandID = b.id AND p.isDeleted = false")
	List<ProductDTO> getAll();

	@Query("SELECT new com.techshopbe.dto.ProductDTO(p.id, c.name, b.name, p.rate, p.name, p.price, p.stock, p.warranty, p.purchased, c.slug, p.images, p.shortDescrip) FROM Product p, Category c, Brand b WHERE p.categoryID = c.id AND p.brandID = b.id AND c.id = ?1 ORDER BY p.purchased DESC")
	List<ProductDTO> findTopPurchasedByCategoryId(int categoryID);

	@Query("SELECT new com.techshopbe.dto.ProductDTO(p.id, c.name, b.name, p.rate, p.name, p.price, p.stock, p.warranty, p.purchased, c.slug, p.images, p.shortDescrip) FROM Product p, Category c, Brand b WHERE p.categoryID = c.id AND p.brandID = b.id ORDER BY p.purchased DESC")
	List<ProductDTO> findTrendingProducts();

	@Query("SELECT new com.techshopbe.dto.ProductDTO(p.id, c.name, b.name, p.rate, p.name, p.price, p.stock, p.warranty, p.purchased, c.slug, p.images, p.shortDescrip) FROM Product p, Category c, Brand b WHERE p.categoryID = c.id AND p.brandID = b.id AND c.slug = ?1")
	List<ProductDTO> findByCategorySlug(String categorySlug);

	@Query("SELECT new com.techshopbe.dto.DetailedProductDTO(p.id, p.categoryID, p.brandID, c.name, b.name, p.rate, p.name, p.price, p.stock, p.warranty, p.purchased, p.totalReviews, p.images, p.shortDescrip) FROM Product p, Category c, Brand b WHERE p.categoryID = c.id AND p.brandID = b.id AND p.id = ?1")
	DetailedProductDTO findDetailedProductByid(String id);

	String findQuery = "SELECT attr_varchar.id, attr.name, attr_varchar.value, attr.dataType FROM  PRODUCT p, ATTRIBUTE attr, ATTRIBUTE_VALUE_VARCHAR attr_varchar where p.id = ?1 and p.id = attr_varchar.productID and attr_varchar.attributeID = attr.id "
			+ "UNION ALL "
			+ "SELECT attr_float.id, attr.name, attr_float.value, attr.dataType FROM  PRODUCT p, ATTRIBUTE attr, ATTRIBUTE_VALUE_FLOAT attr_float WHERE p.id = attr_float.productID and attr_float.attributeID = attr.id and p.id = ?1 "
			+ "UNION ALL "
			+ "SELECT attr_int.id, attr.name, attr_int.value, attr.dataType FROM  PRODUCT p, ATTRIBUTE attr, ATTRIBUTE_VALUE_INT attr_int WHERE p.id = attr_int.productID and attr_int.attributeID = attr.id and p.id = ?1 "
			+ "UNION ALL "
			+ "SELECT attr_text.id, attr.name as name, attr_text.value, attr.dataType FROM  PRODUCT p, ATTRIBUTE attr, ATTRIBUTE_VALUE_TEXT attr_text WHERE p.id = attr_text.productID and attr_text.attributeID = attr.id and p.id = ?1 "
			+ "UNION ALL "
			+ "SELECT attr_boolean.id, attr.name as name, attr_boolean.value, attr.dataType FROM  PRODUCT p, ATTRIBUTE attr, ATTRIBUTE_VALUE_BOOL attr_boolean WHERE p.id = attr_boolean.productID and attr_boolean.attributeID = attr.id and p.id = ?1";

	@Query(value = findQuery, nativeQuery = true)
//	@Query(value = "SELECT attr.name FROM ATTRIBUTE_VALUE_VARCHAR attr_varchar, ATTRIBUTE attr WHERE attr.id = attr_varchar.attributeID and attr_varchar.productID = 1", nativeQuery=true)
	List<Object[]> findSpecificationsByid(String id);

	@Query("SELECT price FROM Product p where p.id = ?1")
	int findProductPriceByProductID(String id);

	@Query("SELECT new com.techshopbe.dto.ProductDTO(p.id, c.name, b.name, p.rate, p.name, p.price, p.stock, p.warranty, p.purchased, c.slug, p.images, p.shortDescrip) FROM Product p, Category c, Brand b WHERE p.categoryID = c.id AND p.brandID = b.id AND p.id != ?1 AND c.id = (SELECT categoryID FROM Product p where p.id = ?1) ")
	List<ProductDTO> findRelatedProductsByCategory(String id);

	@Query("SELECT new com.techshopbe.dto.ProductDTO(p.id, c.name, b.name, p.rate, p.name, p.price, p.stock, p.warranty, p.purchased, c.slug, p.images, p.shortDescrip) FROM Product p, Category c, Brand b WHERE p.categoryID = c.id AND p.brandID = b.id AND p.id != ?1 AND b.id = (SELECT brandID FROM Product p where p.id = ?1) ")
	List<ProductDTO> findRelatedProductsByBrand(String id);

	@Query("SELECT new com.techshopbe.dto.RatingInfoDTO(p.rate, p.totalReviews) FROM Product p WHERE p.id = ?1")
	RatingInfoDTO findRatingInfoByid(String id);

	@Transactional
	@Modifying
	@Query("UPDATE Product p SET p.rate = ?1, p.totalReviews = ?2 WHERE p.id = ?3")
	int updateRatingInfoByid(float rate, int totalReviews, String id);

	@Query(value = "SELECT attr.id, attr.name, attr.dataType FROM attribute_set attr_set, attribute attr WHERE attr_set.id = attr.attributeSetID and attr_set.categoryID = ?1 and attr_set.brandID = ?2", nativeQuery = true)
	List<Object[]> getSpecificationAttributeBycategoryIDAndbrandID(String categoryID, String brandID);

	@Query(value = "INSERT INTO ATTRIBUTE_SET (id, categoryID, brandID, `name`) VALUES(?1, ?2, ?3)", nativeQuery = true)
	void addNewAttributeSet(String id, String categoryID, String brandID, String name);

	@Query(value = "SELECT id FROM ATTRIBUTE_SET WHERE categoryID = ?1 AND brandID = ?2", nativeQuery = true)
	String getAttributeSet(String categoryID, String brandID);

	@Modifying
	@Query(value = "INSERT INTO ATTRIBUTE_VALUE_VARCHAR (id, value, attributeID, productID) VALUES(?1, ?2, ?3, ?4)", nativeQuery = true)
	void addVarCharAttrValue(String id, String value, String attributeID, String productID);

	@Modifying
	@Query(value = "INSERT INTO ATTRIBUTE_VALUE_FLOAT (id, value, attributeID, productID) VALUES(?1, ?2, ?3, ?4)", nativeQuery = true)
	void addFloatAttrValue(String id, float value, String attributeID, String productID);

	@Modifying
	@Query(value = "INSERT INTO ATTRIBUTE_VALUE_TEXT (id, value, attributeID, productID) VALUES(?1, ?2, ?3, ?4)", nativeQuery = true)
	void addTextAttrValue(String id, String value, String attributeID, String productID);

	@Modifying
	@Query(value = "INSERT INTO ATTRIBUTE_VALUE_INT (id, value, attributeID, productID) VALUES(?1, ?2, ?3, ?4)", nativeQuery = true)
	void addIntAttrValue(String id, int value, String attributeID, String productID);

	@Modifying
	@Query(value = "INSERT INTO ATTRIBUTE_VALUE_BOOL (id, value, attributeID, productID) VALUES(?1, ?2, ?3, ?4)", nativeQuery = true)
	void addBoolAttrValue(String id, boolean value, String attributeID, String productID);

	@Modifying
	@Query(value = "INSERT INTO ATTRIBUTE (id, code, name, defaultValue, dataType, attributeSetID, isRequired) VALUES(?1, ?2, ?3, ?4, ?5, ?6, ?7)", nativeQuery = true)
	void addAttribute(String id, String code, String name, String defaultValue, String dataType, String attributeSetID,
			boolean isRequired);

	Product findByIdAndIsDeleted(String id, boolean isDeleted);

	@Modifying
	@Query(value = "UPDATE ATTRIBUTE_VALUE_VARCHAR attr_value SET value = ?2 WHERE id = ?1", nativeQuery = true)
	void updateVarCharAttrValue(String id, String value);

	@Modifying
	@Query(value = "UPDATE ATTRIBUTE_VALUE_FLOAT attr_value SET value = ?2 WHERE id = ?1", nativeQuery = true)
	void updateFloatAttrValue(String id, float value);

	@Modifying
	@Query(value = "UPDATE ATTRIBUTE_VALUE_TEXT attr_value SET value = ?2 WHERE id = ?1", nativeQuery = true)
	void updateTextAttrValue(String id, String value);

	@Modifying
	@Query(value = "UPDATE ATTRIBUTE_VALUE_INT attr_value SET value = ?2 WHERE id = ?1", nativeQuery = true)
	void updateIntAttrValue(String id, int value);

	@Modifying
	@Query(value = "UPDATE ATTRIBUTE_VALUE_BOOL attr_value SET value = ?2 WHERE id = ?1", nativeQuery = true)
	void updateBoolAttrValue(String id, boolean value);

	@Query(value = "SELECT attr.id, attr.name, attr.dataType FROM attribute_set attr_set, attribute attr WHERE attr_set.id = attr.attributeSetID and attr.attributeSetID = ?1", nativeQuery = true)
	List<Object[]> findAllAttributesByAttributeSetID(String attrSetID);

}
