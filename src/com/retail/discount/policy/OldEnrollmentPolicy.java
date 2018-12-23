package com.retail.discount.policy;

import java.util.Date;

/**
 * @author Sarang A. Implemetation of {@link Policy} for previousely enrolled
 *         entity
 */
public class OldEnrollmentPolicy extends Policy {

	@Override
	public Double getDiscountPercentage(PolicyData policyData) {

		Double discount = 0d;
		try {

			if (policyData != null && policyData.getUser() != null
					&& policyData.getUser().getEnroolmentDate() != null) {

				Date today = new Date();

				if (today.getYear() - policyData.getUser().getEnroolmentDate().getYear() >= 2) {

					discount = 5d;
				}
			}
		}
		catch (IllegalArgumentException | NullPointerException e) {

			throw new IllegalArgumentException("Exception while checking employee discount policy ", e);
		}
		return discount;

	}
}
