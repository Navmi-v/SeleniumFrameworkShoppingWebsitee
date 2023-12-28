package navmivishwakarma.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import navmivishwakarma.TestComponents.BaseTest;
import navmivishwakarma.pageobjects.CartPage;
import navmivishwakarma.pageobjects.CheckoutPage;
import navmivishwakarma.pageobjects.ConfirmationPage;
import navmivishwakarma.pageobjects.LandingPage;
import navmivishwakarma.pageobjects.ProductCatalogue;

public class OrderingFlows extends BaseTest {
	
	@Test
	public void submitOrder() throws IOException {
		LandingPage landingPage = launchApplication();
		ProductCatalogue productCatalogue = landingPage.login("nav@vish.com", "Navmi@18");
		productCatalogue.addTocart("Zara Coat 3");
		CartPage cartPage = productCatalogue.goToCartPage();
		cartPage.verifyCartProduct("Zara Coat 3");
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.placeOrder();
		confirmationPage.getOrderId();
		String message = confirmationPage.getConfirmationMessage();
		Assert.assertEquals(message, "THANKYOU FOR THE ORDER.");
	}

}
