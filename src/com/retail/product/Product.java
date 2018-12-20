package com.retail.product;

/**
 * @author Sarang A. Product domain
 */
public class Product {

	private String name;

	/**
	 * Product type G-grocery | O- Other
	 */
	private String productType;
	/**
	 * Integer quantity of product bought
	 */
	private Integer quatity;

	/**
	 * price of the product
	 */
	private Double pricePerProduct;

	public String getName() {
		return name;
	}

	public String getProductType() {
		return productType;
	}

	public Integer getQuatity() {
		return quatity;
	}

	public Double getPricePerProduct() {
		return pricePerProduct;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public void setQuatity(Integer quatity) {
		this.quatity = quatity;
	}

	public void setPricePerProduct(Double pricePerProduct) {
		this.pricePerProduct = pricePerProduct;
	}

}
