package navmivishwakarma.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import navmivishwakarma.pageobjects.CartPage;
import navmivishwakarma.pageobjects.OrdersPage;

public class AbstractComponents {
	
	WebDriver driver;

	
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, driver);
	}
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cart;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orders;

	//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
	
	
	
	public void waitElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitWebElementToAppear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitElementToDisappear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	public CartPage goToCartPage() {
		cart.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrdersPage goToOrdersPage() {
		orders.click();
		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;
	}
	
	public void scrollPage() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(1049,568)");
	}
	
}
