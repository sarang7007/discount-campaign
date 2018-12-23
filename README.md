# discount-campaign for retail store.
Handles bussiness use cases like,
- Adding or Removing discounts without making any changes in existing code.
- Ability to decide/configure variouse strategies while applying discounts.
- Configure product type for which discount is not applicable.
- No code change required if new product type added to discount or existing is removed from availing discount.

#### Read through the java doc and code comments to understand the design.

## Prerequisites
Java 8 version required to run program.
Test cases are compatible with Junit4.

## Installing
To get a development env running
- Clone and Import java project into eclipse version oxygen and above.
- OR alternatively you can execute {java -jar retail-store.jar} to see test report

## UML Class Diagram
Refer uml-diagrame.jpg file

## Executing Test Cases
- Simplest way to run test cases is to download and run executable jar file retail-store.jar using command {java -jar retail-store.jar}
- To run test cases in developemet environment execute main method inside /src/testcases/TestRunner.java
- Test data is hard coded inside /src/testcases/DiscountTestCases.java test case provided.

