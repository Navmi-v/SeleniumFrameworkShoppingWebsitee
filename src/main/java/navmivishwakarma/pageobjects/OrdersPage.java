package navmivishwakarma.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import navmivishwakarma.AbstractComponents.AbstractComponents;

public class OrdersPage extends AbstractComponents {
	
	WebDriver driver;
	
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//table/tbody/tr/td[2]")
	List<WebElement> orderNames;
	
	
	public Boolean verifyOrdersProduct(String productName) {
		Boolean match = orderNames.stream().anyMatch( item -> item.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	
	

}
