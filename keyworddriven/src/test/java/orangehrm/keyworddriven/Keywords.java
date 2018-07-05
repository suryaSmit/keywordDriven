package orangehrm.keyworddriven;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import helper.LocateElement;

public class Keywords {
	WebDriver driver;
	Actions a;
	
	// openBrowser
	public void openBrowser(String locType, String locProp, String input) {
		if(input.equalsIgnoreCase("firefox")) {
			String path = Keywords.class.getClassLoader().getResource("resource/geckodriver").getPath();
			System.out.println(path);
			System.setProperty("webdriver.gecko.driver", path);
			driver = new FirefoxDriver();
		}else if(input.equalsIgnoreCase("chrome")) {
			String path = Keywords.class.getClassLoader().getResource("resource/chromedriver").getPath();
			System.setProperty("webdriver.chrome.driver", path);
			driver = new ChromeDriver();
		}else {
			throw new NullPointerException("please enter valid browser name");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		a = new Actions(driver);
		

	}

	// navigate
	public void navigate(String locType, String locProp, String input) {
		driver.get(input);
	}

	// write
	public void write(String locType, String locProp, String input) {
		driver.findElement(LocateElement.locate(locType, locProp)).clear();
		driver.findElement(LocateElement.locate(locType, locProp)).sendKeys(input);
	}

	// click
	public void click(String locType, String locProp, String input) {
		driver.findElement(LocateElement.locate(locProp, locProp)).click();
	}

	// wait
	public void wait(String locType, String locProp, String input) {
		try {
			Thread.sleep(Long.parseLong(input));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// moveToElement
	public void moveToElement(String locType, String locProp, String input) {
		a.moveToElement(driver.findElement(LocateElement.locate(locType, locProp))).build().perform();
	}

	// moveToEleAndClick
	public void moveToEleAndClick(String locType, String locProp, String input) {
		a.moveToElement(driver.findElement(LocateElement.locate(locType, locProp))).click().build().perform();
	}

	// switchToFrame
	public void switchToFrame(String locType, String locProp, String input) {
		WebElement frame = driver.findElement(LocateElement.locate(locType, locProp));
		driver.switchTo().frame(frame);
	}

	// select
	public void select(String locType, String locProp, String input) {
		WebElement ele = driver.findElement(LocateElement.locate(locType, locProp));
		new Select(ele).selectByVisibleText(input);
	}

	// switchToDefaultContent
	public void switchToDefaultContent(String locType, String locProp, String input) {
		driver.switchTo().defaultContent();
	}

	// closeBrowser
	public void closeBrowser(String locType, String locProp, String input) {
		driver.close();
	}
}
