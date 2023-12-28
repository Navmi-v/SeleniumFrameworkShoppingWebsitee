package navmivishwakarma.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import navmivishwakarma.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement shippingInfo;
	
	@FindBy(css=".list-group-item")
	List<WebElement> countries;
	
	@FindBy(css="a.action__submit")
	WebElement placeOrder;
	
	By countryNames = By.cssSelector(".list-group-item span ");
	

	
	public void selectCountry(String shippingCountry) {
		shippingInfo.sendKeys(shippingCountry);
		WebElement desiredCountry = countries.stream().filter(country -> country.findElement(countryNames).getText().equalsIgnoreCase(shippingCountry)).findFirst().orElse(null);
		desiredCountry.click();
	}
	
	public ConfirmationPage placeOrder() {
		scrollPage();
		placeOrder.click();
		ConfirmationPage orderPage = new ConfirmationPage(driver);
		return orderPage;
	}
	
	
	

}
