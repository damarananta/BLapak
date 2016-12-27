package BL;

import java.net.MalformedURLException;

/**
 * Created by damarananta on 12/27/16.
 * Note: Base test
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
/**
 * Created by damarananta on 12/27/16.
 * Note: reuse
 */
public class BaseTest {
	public static WebDriver driver;
	protected String BaseURL = "https://www.notDefinedYet.com";
	protected String ChromePath = "{Please add your chrome.exe/.app path}";
	StringList_en String = new StringList_en();

	@BeforeClass
	protected void setUpChrome() throws MalformedURLException{
		System.out.println("setUpChrome");
		System.setProperty("webdriver.chrome.driver", ChromePath);
		driver=new ChromeDriver();
		openChrome(BaseURL);
	}
	@Test(enabled=false)
	public void openChrome(String URL){
		//assume that we open web page related to "Successful transaction"
		driver.get("URL");
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.name(String.TransactionResultHeader))).isDisplayed();
		String displayedURL=driver.getCurrentUrl();
		Assert.assertEquals(displayedURL, BaseURL );
	}
	@AfterClass
	public void sudoHalt(){
		System.out.println("quit");
		driver.quit();
	}
}
