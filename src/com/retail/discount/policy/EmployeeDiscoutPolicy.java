package com.retail.discount.policy;

public class EmployeeDiscoutPolicy extends Policy {

	@Override
	public Double getDiscountPercentage(PolicyData policyData) {

		Double employeeDiscount = 0d;
		try {

			if (policyData != null && policyData.getUser().getUserType() != null
					&& policyData.getUser().getUserType().equals("E")) {

				employeeDiscount = 30d;
			}
		}
		catch (IllegalArgumentException | NullPointerException e) {

			throw new IllegalArgumentException("Exception while checking employee discount policy ", e);
		}
		return employeeDiscount;

	}

}
