package navmivishwakarma.OnlineShoppingWebsite;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import navmivishwakarma.pageobjects.CartPage;
import navmivishwakarma.pageobjects.CheckoutPage;
import navmivishwakarma.pageobjects.LandingPage;
import navmivishwakarma.pageobjects.ConfirmationPage;
import navmivishwakarma.pageobjects.ProductCatalogue;

public class StandAlone {
	
	public static void main (String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		LandingPage landingPage = new LandingPage(driver);

		landingPage.goTo();
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
