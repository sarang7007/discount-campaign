package com.retail.user;

import java.util.Date;

public class User {

	private String userName;

	/**
	 * user type must be A-Affiliated N-Normal User and E- Employee
	 */
	private String userType;

	/**
	 * Date at which user has enrolled in system
	 */
	private Date enroolmentDate;

	public String getUserName() {
		return userName;
	}

	public Date getEnroolmentDate() {
		return enroolmentDate;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setEnroolmentDate(Date enroolmentDate) {
		this.enroolmentDate = enroolmentDate;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
