package com.retail.discount.policy;

import com.retail.user.User;

/**
 * @author Sarang Domain to provide required data for policy check
 */
public class PolicyData {

	private User user;
	private Double totalBill;

	public PolicyData(User user, Double totalBill) {
		this.user = user;
		this.totalBill = totalBill;
	}

	public User getUser() {
		return user;
	}

	public Double getTotalBill() {
		return totalBill;
	}
}
