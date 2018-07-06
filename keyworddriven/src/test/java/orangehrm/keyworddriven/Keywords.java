package orangehrm.keyworddriven;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.LogStatus;

import helper.LocateElement;

public class Keywords {
	WebDriver driver;
	Actions a;
	
	// openBrowser
	public void openBrowser(String locType, String locProp, String input) {
		if(input.equalsIgnoreCase("firefox")) {
			String path = Keywords.class.getClassLoader().getResource("resources/geckodriver").getPath();
			System.out.println(path);
			System.setProperty("webdriver.gecko.driver", path);
			driver = new FirefoxDriver();
		}else if(input.equalsIgnoreCase("chrome")) {
			String path = Keywords.class.getClassLoader().getResource("resources/chromedriver").getPath();
			System.out.println(path);
			System.setProperty("webdriver.chrome.driver", path);
			driver = new ChromeDriver();
		}else {
			throw new NullPointerException("please enter valid browser name");
		}
		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		a = new Actions(driver);
		

	}

	// navigate
	public void navigate(String locType, String locProp, String input) {
		Driver.test.log(LogStatus.INFO, "navigating to "+input);
		driver.get(input);
		Driver.test.log(LogStatus.INFO, "navigated to "+input);
	}

	// write
	public void write(String locType, String locProp, String input) {
		driver.findElement(LocateElement.locate(locType, locProp)).clear();
		driver.findElement(LocateElement.locate(locType, locProp)).sendKeys(input);
		wait("1000");
	}

	// click
	public void click(String locType, String locProp, String input) {
		driver.findElement(LocateElement.locate(locType, locProp)).click();
		wait("1000");
	}
	
	public void wait(String input) {
		try {
			Thread.sleep(Long.parseLong(input));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// wait
	public void wait(String locType, String locProp, String input) {
		wait(input);
	}

	// moveToElement
	public void moveToElement(String locType, String locProp, String input) {
		a.moveToElement(driver.findElement(LocateElement.locate(locType, locProp))).build().perform();
		wait("1000");
	}

	// moveToEleAndClick
	public void moveToEleAndClick(String locType, String locProp, String input) {
		a.moveToElement(driver.findElement(LocateElement.locate(locType, locProp))).click().build().perform();
		wait("1000");
	}

	// switchToFrame
	public void switchToFrame(String locType, String locProp, String input) {
		WebElement frame = driver.findElement(LocateElement.locate(locType, locProp));
		driver.switchTo().frame(frame);
		wait("1000");
	}

	// select
	public void select(String locType, String locProp, String input) {
		WebElement ele = driver.findElement(LocateElement.locate(locType, locProp));
		new Select(ele).selectByVisibleText(input);
		wait("1000");
	}

	// switchToDefaultContent
	public void switchToDefaultContent(String locType, String locProp, String input) {
		driver.switchTo().defaultContent();
		wait("1000");
	}
	
	//alertAccept
	public void alertAccept(String locType, String locProp, String input) {
		Driver.test.log(LogStatus.INFO, "alert came: "+driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
		Driver.test.log(LogStatus.INFO, "alert accepted.");
		
	}

	// closeBrowser
	public void closeBrowser(String locType, String locProp, String input) {
		driver.close();
	}
}
