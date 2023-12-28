package navmivishwakarma.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import navmivishwakarma.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {
	
	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="label.ng-star-inserted")
	WebElement orderId;
	
	@FindBy(tagName="h1")
	WebElement confirmationMessage;
	
	public void getOrderId() {
		String orderid = orderId.getText();
		System.out.println(orderid);		
	}
	
	public String getConfirmationMessage() {
		String message = confirmationMessage.getText();
		return message;
	}
	
	
	

}
