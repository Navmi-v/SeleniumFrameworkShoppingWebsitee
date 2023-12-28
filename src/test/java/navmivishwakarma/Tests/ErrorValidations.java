package navmivishwakarma.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import navmivishwakarma.TestComponents.BaseTest;
import navmivishwakarma.pageobjects.CartPage;
import navmivishwakarma.pageobjects.ProductCatalogue;

public class ErrorValidations extends BaseTest {
	
	@Test
	public void loginError() {
		landingPage.login("navmi@gmail.com", "Navmi");
		String errorMessage = landingPage.getErrorMessage();
		Assert.assertEquals("Incorrect email or password.", errorMessage);
	}
	
	@Test
	public void productCheck() {
		ProductCatalogue productCatalogue = landingPage.login("nav@vish.com", "Navmi@18");
		productCatalogue.addTocart("Zara Coat 3");
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyCartProduct("Zara Coat match 3");	
		Assert.assertFalse(match);
	}

}
