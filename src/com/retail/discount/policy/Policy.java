package com.retail.discount.policy;

/**
 * @author Sarang A. Abstract discount policy class.
 */
public abstract class Policy {

	/**
	 * @param {@code PolicyData}
	 * @return {@code Double} discount percentage
	 */
	public abstract Double getDiscountPercentage(PolicyData policyData);
}
