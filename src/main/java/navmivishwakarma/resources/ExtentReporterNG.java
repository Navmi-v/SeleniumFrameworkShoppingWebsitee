package navmivishwakarma.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject() {
		
		String path = System.getProperty("user.dir") + "//reports//ExtentReport.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Web Automation for Shopping Website");
		reporter.config().setReportName("Automation results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Navmi Vishwakarma");
		return extent;
	}

}
