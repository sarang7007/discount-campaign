
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.retail.billing.BillingService;
import com.retail.billing.BillingServiceImpl;
import com.retail.discount.DiscountService;
import com.retail.product.Product;
import com.retail.user.User;

public class Test {

	public static void main(String[] args) {

		DiscountService discountService = new DiscountService();

		BillingService billingService = new BillingServiceImpl(discountService);

		List<Product> productBought = new ArrayList<>();

		Product prod = new Product();
		prod.setName("xyz");

		// must be G for groceries | O - for others
		prod.setProductType("G");
		prod.setPricePerProduct(100.00);
		prod.setQuatity(1);
		productBought.add(prod);

		Product prod2 = new Product();
		prod2.setName("xyzz");
		prod2.setProductType("O");
		prod2.setPricePerProduct(100.00);
		prod2.setQuatity(1);
		productBought.add(prod2);

		User user = new User();
		user.setEnroolmentDate(new Date());
		user.setUserName("Sarang");
		// must be E for Employees | A - for Affiliates | O - for others
		user.setUserType("E");

		double d = billingService.getDiscountedBill(productBought, user);

		System.out.println(d);

	}
}
