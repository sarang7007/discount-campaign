package com.retail.discount.policy;

import com.retail.user.User;

/**
 * @author Sarang Domain to provide required data for policy check
 */
public class PolicyData {

	private User user;
	private Double totalBill;

	public User getUser() {
		return user;
	}

	public Double getTotalBill() {
		return totalBill;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setTotalBill(Double totalBill) {
		this.totalBill = totalBill;
	}

}
