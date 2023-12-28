package navmivishwakarma.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import navmivishwakarma.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail; 
	
	@FindBy(id="userPassword")
	WebElement userPassword; 
	
	@FindBy(id="login")
	WebElement login; 
	
	@FindBy(css=".ng-trigger-flyInOut")
	WebElement errorMessage;
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductCatalogue login(String username, String password) {
		userEmail.sendKeys(username);
		userPassword.sendKeys(password);
		login.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String getErrorMessage() {
		waitWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}


}


