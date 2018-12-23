package com.retail.discount;

import java.util.List;
import java.util.stream.Collectors;

import com.retail.discount.policy.Policy;
import com.retail.discount.policy.PolicyData;
import com.retail.product.Product;
import com.retail.user.User;

/**
 * @author Sarang A. Disocunt service to manage all discounts related operations
 */
public class DiscountService {

	DiscountFactory discountFactory;

	public DiscountService() {

		discountFactory = DiscountFactory.getInstance();
	}

	/**
	 * Method to return amount after applying discount
	 * 
	 * @param productBought
	 *            {@link List<Product>}
	 * @param user
	 *            {@link User}
	 * @return {@code Double}
	 */
	public Double getAmountToPay(List<Product> productBought, User user) {

		double totalBillAfterDiscount = 0.0;
		double amountForToAvailDiscount = 0d;
		double nonDiscountedAmount = 0d;
		double totalAmount = 0d;
		Double maxDiscountAvailableToAvail = 0.0;

		/**
		 * Calculating amount available for discount and Total amount
		 */
		List<String> noDiscountProductType = discountFactory.getDiscountNotAvailableForType();

		for (Product product : productBought) {

			if (noDiscountProductType.contains(product.getProductType())) {

				nonDiscountedAmount = nonDiscountedAmount + (product.getPricePerProduct() * product.getQuatity());
			}
			else {

				amountForToAvailDiscount = amountForToAvailDiscount
						+ (product.getPricePerProduct() * product.getQuatity());
			}
		}

		totalAmount = nonDiscountedAmount + amountForToAvailDiscount;

		/** If amount is greater than 0 then execute discount policies */
		if (amountForToAvailDiscount > 0.0) {

			/**
			 * discount factory will populate {discountPolicies} list with
			 * configured policy implemetation references
			 */
			List<Policy> discountPolicies = discountFactory.getConfiguredDiscountPolicies();

			/** populating data object required for policy execution */
			PolicyData policyData = new PolicyData(user, amountForToAvailDiscount);

			/**
			 * Call getDiscountPercentage() method on each configured policy
			 * Object and collecting discount offered by each
			 */
			List<Double> discounts = discountPolicies.parallelStream()
					.map(policy -> policy.getDiscountPercentage(policyData)).collect(Collectors.toList());

			/**
			 * Current implemetation supports only one discount avail strategy.
			 * This code may be modified to create handlers for multiple discout
			 * strategy support.
			 */
			maxDiscountAvailableToAvail = discounts.stream().collect(Collectors.summarizingDouble(Double::doubleValue))
					.getMax();

			/** Calculationg percentage */
			double percentage = 100 - maxDiscountAvailableToAvail;

			double discountedAmout = (percentage * amountForToAvailDiscount) / 100;

			totalBillAfterDiscount = discountedAmout + nonDiscountedAmount;
		}

		else {

			totalBillAfterDiscount = nonDiscountedAmount;
		}

		/** printing on console for debuging */
		System.out.println("Total Billing Amount: " + totalAmount + " $");
		System.out.println("Valid Amount for Discount : " + amountForToAvailDiscount + " $");
		System.out.println("Elegible For Discount : " + maxDiscountAvailableToAvail + " %");
		System.out.println("Amount to pay after discount : " + totalBillAfterDiscount + " $");

		return totalBillAfterDiscount;
	}

	public Double getAmountSaved(List<Product> productBought, User user) {
		/* TODO implement */
		return null;
	}
}
