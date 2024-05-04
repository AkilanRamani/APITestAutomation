package APITestAutomation;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(ListenerClass.class)
public class BaseTest {
	
    ExtentManager manager = new ExtentManager();

	
    @BeforeTest
	public void getTest1() throws IOException {
    	
		manager.setExtent();
		
    }
	@Test
	public void getName() {
		
		System.out.println("Test");
		
	}

	
	@Test
	public void getTest() {
		
		System.out.println("Test");
		
	}
	

	@Test
	public void getTestFailed() {
		
		Assert.assertTrue(false);
		
	}
	

    @AfterTest
	public void afterTest() throws IOException {
    	manager.endReport();
}
}