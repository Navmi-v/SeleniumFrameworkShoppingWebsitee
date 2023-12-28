package navmivishwakarma.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import navmivishwakarma.pageobjects.LandingPage;

public class BaseTest {
	
	protected WebDriver driver;
	protected LandingPage landingPage;
	//why protected??
	
	public WebDriver intializeDriver() throws IOException {
		
		Properties property = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\navmivishwakarma\\resources\\GlobalData.properties");
		property.load(fis);
		
		String browserName = System.getProperty("browser")!=null? System.getProperty("browser"): property.getProperty("browser");
		
		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
		}
		else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();			
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver = intializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
	
    public List<HashMap<String, String>> convertJSONtoMap(String filePath) throws IOException {
		
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		//Jackson data bind converts string to map
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data =  mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		return data;
		
	}
    
    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
    	//TakesScreenshot ts = (TakesScreenshot)driver;
    	File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	FileUtils.copyFile(source, new File(System.getProperty("user.dir")+ "//reports" + testCaseName +".png"));
    	return System.getProperty("user.dir")+ "//reports" + "testCaseName	" +".png";
    }

}
