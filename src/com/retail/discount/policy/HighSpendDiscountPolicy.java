package com.retail.discount.policy;

/**
 * @author Sarang A. Implemetation of {@link Policy} for discount for high
 *         spending entity
 */
public class HighSpendDiscountPolicy extends Policy {

	@Override
	public Double getDiscountPercentage(PolicyData policyData) {

		Double discount = 0d;
		try {

			if (policyData != null && policyData.getTotalBill() != null && policyData.getTotalBill() >= 100) {

				// For each 100$ spent 5% discount will get added
				discount = Math.floor(policyData.getTotalBill() / 100) * 5;
			}

			/* If discount is more than 100 then return 100 */
			if (discount > 100d) {

				discount = 100d;
			}
		}
		catch (IllegalArgumentException | NullPointerException e) {

			throw new IllegalArgumentException("Exception while checking affilated user discount policy ", e);
		}
		return discount;

	}
}
