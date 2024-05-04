package APITestAutomation;

import org.testng.annotations.AfterSuite;

import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseClass {

	ExtentReports extent;
	ExtentSparkReporter spark;

	@BeforeSuite
	public void setupReport() {
		extent = new ExtentReports();
		spark = new ExtentSparkReporter("C:\\Users\\akilan-18633\\eclipse-workspace\\APITestAutomation\\src\\test\\resources\\SchemaValidation\\extentTest.html");
		extent.attachReporter(spark);
	}

	@AfterSuite
	public void teardownReport() {
		extent.flush();
	}
}
