package navmivishwakarma.OnlineShoppingWebsite;

import java.time.Duration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("nav@vish.com");
		driver.findElement(By.id("userPassword")).sendKeys("Navmi@18");
		driver.findElement(By.id("login")).click();
		
		List<WebElement> products = driver.findElements(By.className("card-body"));
		
		WebElement prod = products.stream().filter( product -> product.findElement(By.tagName("h5")).getText().equalsIgnoreCase("Zara Coat 3")).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ng-animating")));
		
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean match = cartItems.stream().anyMatch( item -> item.getText().equalsIgnoreCase("Zara Coat 3"));
		Assert.assertTrue(match);
		
		
		js.executeScript("window.scrollBy(1039,573)");
		
		driver.findElement(By.cssSelector("li.totalRow .btn-primary")).click();
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("India");
		List<WebElement> countries = driver.findElements(By.cssSelector(".list-group-item"));
		
		WebElement desiredCountry = countries.stream().filter(country -> country.findElement(By.cssSelector(".list-group-item span ")).getText().equalsIgnoreCase("India")).findFirst().orElse(null);
		desiredCountry.click();
		js.executeScript("window.scrollBy(1049, 583)");
		driver.findElement(By.cssSelector("a.action__submit")).click();
		String orderId = driver.findElement(By.cssSelector("label.ng-star-inserted")).getText();
		System.out.print(orderId);
		

	}

}
