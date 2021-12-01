package com.techshopbe.dto;

// chi tiet hoa don
public class DetailedInvoiceDTO {
	private String id;
	private int oldPrice;
	private int quantity;
	private int totalPrice;
	private String name;
	private boolean isReviewed;
	private String images;
	private String categorySlug;
	private int salePrice;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(int oldPrice) {
		this.oldPrice = oldPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isReviewed() {
		return isReviewed;
	}

	public void setReviewed(boolean isReviewed) {
		this.isReviewed = isReviewed;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getCategorySlug() {
		return categorySlug;
	}

	public void setCategorySlug(String categorySlug) {
		this.categorySlug = categorySlug;
	}

	public int getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}

	public DetailedInvoiceDTO(String id, int oldPrice, int quantity, int totalPrice, String name, boolean isReviewed,
			String images, String categorySlug, int salePrice) {
		super();
		this.id = id;
		this.oldPrice = oldPrice;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.name = name;
		this.isReviewed = isReviewed;
		this.images = images;
		this.categorySlug = categorySlug;
		this.salePrice = salePrice;
	}

}
