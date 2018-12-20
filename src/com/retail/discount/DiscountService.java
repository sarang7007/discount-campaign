package com.retail.discount;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.retail.discount.policy.Policy;
import com.retail.discount.policy.PolicyData;
import com.retail.product.Product;
import com.retail.user.User;

public class DiscountService {

	DiscountFactory discountFactory;

	private Double amountForToAvailDiscount = 0d;
	private Double nonDiscountedAmount = 0d;
	private Double totalAmount = 0d;

	public DiscountService() {

		discountFactory = DiscountFactory.getInstance();
	}

	public Double getAmountToPay(List<Product> productBought, User user) {

		double totalBillAfterDiscount = 0.0;

		this.getTotalAmountToAvailDiscount(productBought, user);

		List<Policy> discountPolicies = discountFactory.getConfiguredDiscountPolicies();

		PolicyData policyData = new PolicyData();
		policyData.setUser(user);
		policyData.setTotalBill(amountForToAvailDiscount);

		List<Double> discounts = new ArrayList<>();

		Double maxDiscountAvailableToAvail = 0.0;
		if (amountForToAvailDiscount > 0.0) {

			for (Policy policy : discountPolicies) {

				discounts.add(policy.getDiscountPercentage(policyData));
			}

			// Current implemetation supports only one discount avail strategy
			maxDiscountAvailableToAvail = Collections.max(discounts);

			double s = 100 - maxDiscountAvailableToAvail;

			double discountedAmout = (s * amountForToAvailDiscount) / 100;

			totalBillAfterDiscount = discountedAmout + nonDiscountedAmount;
		}

		else {

			totalBillAfterDiscount = nonDiscountedAmount;
		}

		System.out.println("Total amount: " + totalAmount);
		System.out.println("Amount available for discount : " + amountForToAvailDiscount);
		System.out.println("Elegible discount : " + maxDiscountAvailableToAvail);

		System.out.println("Amount to pay after discount : " + totalBillAfterDiscount);

		return totalBillAfterDiscount;
	}

	private void getTotalAmountToAvailDiscount(List<Product> productBought, User user) {

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
	}

	public Double getAmountSaved(List<Product> productBought, User user) {
		return null;
	}
}
