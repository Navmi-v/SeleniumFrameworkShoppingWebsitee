package navmivishwakarma.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import navmivishwakarma.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);	
	}
	
	
	@FindBy(className="card-body")
	List<WebElement> products; 
	
	By getProduct = By.tagName("h5");
	By addTocart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	By loadingScreen = By.className("ng-animating");
	
	public WebElement getProduct(String productName) {
		WebElement desiredProduct = products.stream().filter( product -> product.findElement(getProduct).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		return desiredProduct;
	}
	
	public void addTocart(String productName) {
		WebElement desiredProduct = getProduct(productName);
		desiredProduct.findElement(addTocart).click();
		waitElementToAppear(toastMessage);
		waitElementToDisappear(loadingScreen);
	}
	
	


}


