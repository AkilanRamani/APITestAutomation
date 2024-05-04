package APITestAutomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public interface ActionInterface {
	
	final WebDriver driver = new ChromeDriver();
	
	public String screenShot(WebDriver driver, String filename);
	

}
