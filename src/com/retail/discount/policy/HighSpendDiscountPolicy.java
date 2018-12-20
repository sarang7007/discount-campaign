package com.retail.discount.policy;

public class HighSpendDiscountPolicy extends Policy {

	@Override
	public Double getDiscountPercentage(PolicyData policyData) {

		Double discount = 0d;
		try {

			if (policyData != null && policyData.getTotalBill() != null && policyData.getTotalBill() >= 100) {

				// For each 100$ spent 5% discount will get added
				discount = (policyData.getTotalBill() / 100) * 5;
			}
		}
		catch (IllegalArgumentException | NullPointerException e) {

			throw new IllegalArgumentException("Exception while checking affilated user discount policy ", e);
		}
		return discount;

	}
}
