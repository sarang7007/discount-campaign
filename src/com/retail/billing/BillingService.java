package com.retail.billing;

import java.util.List;

import com.retail.product.Product;
import com.retail.user.User;

/**
 * @author Sarang A.
 */
public interface BillingService {

	public Double getDiscountedBill(List<Product> productBought, User user);
}
