package com.retail.billing;

import java.util.List;

import com.retail.product.Product;
import com.retail.user.User;

/**
 * @author Sarang A. {@code BillingService } is resposible for contract of all
 *         methods for discount related functionality
 */
public interface BillingService {

	/**
	 * @param productBought
	 * @param user
	 * @return {@code Double}
	 */
	public Double getDiscountedBill(List<Product> productBought, User user);
}
