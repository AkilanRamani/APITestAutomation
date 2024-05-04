package APITestAutomation;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestAutomation {

	@Test
	public void test1() {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://qavbox.github.io/demo/webtable/");
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
		WebElement ele = driver.findElement(By.xpath("//table//tr[2]"));

		List<WebElement> element = ele.findElements(By.xpath("td"));
		System.out.println(element);
		element.get(0).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ele.getText());
		driver.close();

	}

	@Test
	public void test2() {
		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();
		driver.get("https://qavbox.github.io/demo/webtable/");
		driver.manage().window().maximize();

		WebElement Table_1 = driver.findElement(By.id("table01"));

		List<WebElement> Rows = Table_1.findElements(By.tagName("tr"));
		System.out.println("No. of rows: " + Rows.size());
		String cell = null;
		for (int i = 0; i < Rows.size(); i++) {
			List<WebElement> cols = Rows.get(i).findElements(By.tagName("td"));
			for (int j = 0; j < cols.size(); j++) {
				cell = driver.findElement(By.xpath("//[@id='table01']/tbody/tr[" + i + "]/td[" + j + "]")).getText();
				System.out.print(cell);
			}
			System.out.println();
		}
		driver.close();

	}

}
