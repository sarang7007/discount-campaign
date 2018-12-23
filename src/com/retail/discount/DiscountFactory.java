package com.retail.discount;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.retail.discount.policy.Policy;

/**
 * @author Sarang A. Factory class to create and return object of all discount
 *         implemetation classes
 */
public class DiscountFactory {

	private static DiscountFactory factory = null;

	protected List<String> configuredDiscounts = null;

	private String discountsStrategy = null;
	private List<String> discountNotAvailableForType = null;

	private static final String PATH = "com.retail.discount.policy.";

	private List<Policy> policies;

	/**
	 * Singleton object of factory will get created and read configuration file
	 * while initializing factory
	 */
	private DiscountFactory() {

		try {
			Properties configProperties = new Properties();

			InputStream inputStream = DiscountFactory.class.getClassLoader()
					.getResourceAsStream("resources/config.properties");

			configProperties.load(inputStream);

			configuredDiscounts = Arrays.asList(configProperties.getProperty("current.discounts").split(","));

			discountsStrategy = configProperties.getProperty("discount.strategy");

			discountNotAvailableForType = Arrays
					.asList(configProperties.getProperty("discount.notAvailableForProducts").split(","));
		}
		catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static DiscountFactory getInstance() {

		if (factory == null) {

			factory = new DiscountFactory();
		}
		return factory;
	}

	public String getDiscountsStrategy() {
		return discountsStrategy;
	}

	public List<String> getDiscountNotAvailableForType() {
		return discountNotAvailableForType;
	}

	public List<Policy> getConfiguredDiscountPolicies() {

		// If policy objects already created do not create them again.
		if (policies == null) {

			policies = new ArrayList<>();

			for (String discount : configuredDiscounts) {

				String policyFqcn = PATH + discount;

				Policy policy = null;

				try {
					policy = (Policy) Class.forName(policyFqcn).newInstance();
				}
				catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {

					e.printStackTrace();
				}

				policies.add(policy);

			}
		}

		return policies;
	}
}
