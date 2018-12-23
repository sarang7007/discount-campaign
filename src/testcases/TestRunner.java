package testcases;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * @author Sarang A. Execute all test cases for discount
 */
public class TestRunner {

	public static void main(String[] args) {

		Result result = JUnitCore.runClasses(testcases.DiscountTestCases.class);

		for (Failure failure : result.getFailures()) {

			System.out.println(failure.toString());
		}

		System.out.println("\n ===============Test Execution Report ==================");
		System.out.println("\nCount of failed test cases : " + result.getFailureCount());
		System.out.println("Total test cases exeucted  : " + result.getRunCount());
		System.out.println("Test Result : " + result.wasSuccessful());
	}
}
