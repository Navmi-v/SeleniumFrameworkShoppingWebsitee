package navmivishwakarma.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import navmivishwakarma.TestComponents.BaseTest;
import navmivishwakarma.pageobjects.CartPage;
import navmivishwakarma.pageobjects.CheckoutPage;
import navmivishwakarma.pageobjects.ConfirmationPage;
import navmivishwakarma.pageobjects.OrdersPage;
import navmivishwakarma.pageobjects.ProductCatalogue;

public class SubmitOrder extends BaseTest {
	
	String productName = "zara coat 3";
	
	@Test(dataProvider="getData", groups= {"Regression"}, retryAnalyzer=navmivishwakarma.TestComponents.Retry.class)
	public void submitOrder(String email, String password, String productName, String country) {
		ProductCatalogue productCatalogue = landingPage.login(email, password);
		productCatalogue.addTocart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		cartPage.verifyCartProduct(productName);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry(country);
		ConfirmationPage confirmationPage = checkoutPage.placeOrder();
		confirmationPage.getOrderId();
		String message = confirmationPage.getConfirmationMessage();
		Assert.assertEquals(message, "THANKYOU FOR THE ORDER.");
	}
	
	@Test(dataProvider="getMapData", groups= {"MapData"}, retryAnalyzer=navmivishwakarma.TestComponents.Retry.class)
	public void submitOrder(HashMap<String, String> input) {
		ProductCatalogue productCatalogue = landingPage.login(input.get("email"), input.get("password"));
		productCatalogue.addTocart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		cartPage.verifyCartProduct(input.get("productName"));
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry(input.get("countryName"));
		ConfirmationPage confirmationPage = checkoutPage.placeOrder();
		confirmationPage.getOrderId();
		String message = confirmationPage.getConfirmationMessage();
		Assert.assertEquals(message, "THANKYOU FOR THE ORDER.");
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistory() {
		ProductCatalogue productCatalogue = landingPage.login("nav@vish.com", "Navmi@18");
		OrdersPage ordersPage=  productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrdersProduct(productName));
	}
	
	@DataProvider
	public Object[][] getData() {
		return new Object[][] {{"nav@vish.com", "Navmi@18", "Zara Coat 3", "India"}, {"vbn@kin.com", "Navmi@18", "ADIDAS ORIGINAL", "Bhutan"}};
	}
	
	@DataProvider
	public Object[][] getMapData() {
		HashMap<String, String> hm = new HashMap<String, String>();
		HashMap<String, String> hm1 = new HashMap<String, String>();
		hm.put("email", "nav@vish.com");
		hm.put("password", "Navmi@18");
		hm.put("productName", "Zara Coat 3");
		hm.put("countryName", "India");
		
		hm1.put("email", "vbn@kin.com");
		hm1.put("password", "Navmi@18");
		hm1.put("productName", "ADIDAS ORIGINAL");
		hm1.put("countryName", "Bhutan");
		
		return new Object[][] {{hm},{hm1}};
		
	}
	
	@DataProvider
	public Object[][] getJSONData() throws IOException{
		
		List<HashMap<String, String>> data = convertJSONtoMap(System.getProperty("user.dir") + "\\src\\test\\java\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	

}
