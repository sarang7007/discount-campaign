package com.retail.discount.policy;

public class AffiliateUserPolicy extends Policy {

	@Override
	public Double getDiscountPercentage(PolicyData policyData) {

		Double discount = 0d;
		try {

			if (policyData != null && policyData.getUser().getUserType() != null
					&& policyData.getUser().getUserType().equals("A")) {

				discount = 10d;
			}
		}
		catch (IllegalArgumentException | NullPointerException e) {

			throw new IllegalArgumentException("Exception while checking affilated user discount policy ", e);
		}
		return discount;

	}
}
