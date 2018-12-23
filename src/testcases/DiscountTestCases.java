package testcases;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;

import com.retail.billing.BillingService;
import com.retail.billing.BillingServiceImpl;
import com.retail.discount.DiscountService;
import com.retail.product.Product;
import com.retail.user.User;

public class DiscountTestCases {

	DiscountService discountService;
	BillingService billingService;

	@Before
	public void testSetup() {

		discountService = new DiscountService();
		billingService = new BillingServiceImpl(discountService);
	}

	/**
	 * Test case to check employee of store will get 30% discount
	 */
	@org.junit.Test
	public void testEmployeeDiscount() {

		List<Product> productBought = new ArrayList<>();

		System.out.println("==== Executing Test case, for Employee will get 30% discount === ");

		Product prod = new Product();
		prod.setName("xyz");

		// must be G for groceries | O - for others
		prod.setProductType("O");
		prod.setPricePerProduct(100.00);
		prod.setQuatity(1);
		productBought.add(prod);

		User user = new User();
		user.setEnroolmentDate(new Date());
		user.setUserName("Sarang");
		// must be E for Employees | A - for Affiliates | O - for others
		user.setUserType("E");

		double d = billingService.getDiscountedBill(productBought, user);

		assertEquals(70.00d, d, 0.0);

	}

	/**
	 * Test case to check user affiliated to store will get 10% discount
	 */
	@org.junit.Test
	public void testAffiliatedUserDscount() {

		List<Product> productBought = new ArrayList<>();

		System.out.println("==== Executing Test case, Affiliated user will get 10% discount === ");

		Product prod = new Product();
		prod.setName("xyz");

		// product type must be G for groceries | O - for others
		prod.setProductType("O");
		prod.setPricePerProduct(100.00);
		prod.setQuatity(1);
		productBought.add(prod);

		User user = new User();
		user.setEnroolmentDate(new Date());
		user.setUserName("Sarang");
		// must be E for Employees | A - for Affiliates | O - for others
		user.setUserType("A");

		double d = billingService.getDiscountedBill(productBought, user);

		assertEquals(90.0, d, 0.0);

	}

	/**
	 * Test case to check discounted rates will not be applied for groceries.
	 */
	@org.junit.Test
	public void testNoDiscountForGroceries() {

		List<Product> productBought = new ArrayList<>();

		System.out.println("==== Executing Test case, No discount for groceries === ");

		Product prod = new Product();
		prod.setName("xyz");

		// product type must be G for groceries | O - for others
		prod.setProductType("G");
		prod.setPricePerProduct(100.00);
		prod.setQuatity(1);
		productBought.add(prod);

		User user = new User();
		user.setEnroolmentDate(new Date());
		user.setUserName("Sarang");
		// must be E for Employees | A - for Affiliates | O - for others
		user.setUserType("E");

		double d = billingService.getDiscountedBill(productBought, user);

		assertEquals(100.00d, d, 0.0);

	}

	/**
	 * Every 100 spend will get 5% extra discount
	 */
	@org.junit.Test
	public void testHighSpendDiscount() {

		List<Product> productBought = new ArrayList<>();

		System.out.println("==== Executing Test case, every 100$ spend will get 5% discount === ");

		Product prod = new Product();
		prod.setName("xyz");

		// product type must be G for groceries | O - for others
		prod.setProductType("O");
		prod.setPricePerProduct(990.00);
		prod.setQuatity(1);
		productBought.add(prod);

		User user = new User();
		user.setEnroolmentDate(new Date());
		user.setUserName("Sarang");
		// must be E for Employees | A - for Affiliates | O - for others
		user.setUserType("E");

		double d = billingService.getDiscountedBill(productBought, user);

		assertEquals(544.5, d, 0.0);

	}

	/**
	 * User enrolled 2 years before will get 5% discount
	 */
	@org.junit.Test
	public void testOldUseDiscount() {

		List<Product> productBought = new ArrayList<>();

		System.out.println("==== Executing Test case, User enrolled before two years will get 10 % discount === ");

		Product prod = new Product();
		prod.setName("xyz");

		// product type must be G for groceries | O - for others
		prod.setProductType("O");
		prod.setPricePerProduct(100.00);
		prod.setQuatity(1);
		productBought.add(prod);

		User user = new User();

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -2);
		Date twoYearOldDate = cal.getTime();

		// Set user enrollment date two year previouse
		user.setEnroolmentDate(twoYearOldDate);
		user.setUserName("Sarang");

		// must be E for Employees | A - for Affiliates | O - for others
		user.setUserType("O");

		double d = billingService.getDiscountedBill(productBought, user);

		assertEquals(95.0, d, 0.0);

	}

	/**
	 * Only one discount applied on a bill
	 */
	@org.junit.Test
	public void testOneDiscountPerBill() {

		List<Product> productBought = new ArrayList<>();

		System.out.println("==== Executing Test case, User can avail onely one highest discount === ");

		Product prod = new Product();
		prod.setName("xyz");

		// product type must be G for groceries | O - for others
		prod.setProductType("O");
		prod.setPricePerProduct(1000.00);
		prod.setQuatity(1);
		productBought.add(prod);

		User user = new User();

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -2);
		Date twoYearOldDate = cal.getTime();

		// Set user enrollment date two year previouse
		user.setEnroolmentDate(twoYearOldDate);
		user.setUserName("Sarang");

		// must be E for Employees | A - for Affiliates | O - for others
		user.setUserType("E");

		double d = billingService.getDiscountedBill(productBought, user);

		assertEquals(500.0, d, 0.0);

	}
}
