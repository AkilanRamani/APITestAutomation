package APITestAutomation;


import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;




public class ExtentTest extends BaseClass {
	
	@Test
	public void readDataFromConfigFile() {
		
		com.aventstack.extentreports.ExtentTest test1 = extent.createTest("Test1", "This is Demo test case");
		test1.log(Status.INFO, "Test1 has started");
		System.out.println("Test");
	}

}
