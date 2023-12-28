package navmivishwakarma.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import navmivishwakarma.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartItems;
	
	@FindBy(css="li.totalRow .btn-primary")
	WebElement checkout;
	
	public Boolean verifyCartProduct(String productName) {
		Boolean match = cartItems.stream().anyMatch( item -> item.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		scrollPage();
		checkout.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}
	

}
