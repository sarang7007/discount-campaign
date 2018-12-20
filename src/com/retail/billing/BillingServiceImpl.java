package com.retail.billing;

import java.util.List;

import com.retail.discount.DiscountService;
import com.retail.product.Product;
import com.retail.user.User;

public class BillingServiceImpl implements BillingService {

	private DiscountService discountService;

	public BillingServiceImpl(DiscountService discountService) {
		super();
		this.discountService = discountService;
	}

	@Override
	public Double getDiscountedBill(List<Product> productBought, User user) {

		return discountService.getAmountToPay(productBought, user);
	}

}
