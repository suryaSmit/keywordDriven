package helper;

import org.openqa.selenium.By;

import com.relevantcodes.extentreports.LogStatus;

import orangehrm.keyworddriven.Driver;

public class LocateElement {
	static By loc;

	public static By locate(String locType, String locProp) {
		Driver.test.log(LogStatus.INFO, "locating element using  "+locType+" and value "+locProp);
		switch (locType) {
		case "id":
			loc = By.id(locProp);
			break;
		case "name":
			loc = By.name(locProp);
			break;
		case "xpath":
			loc = By.xpath(locProp);
			break;
		case "cssSelector":
			loc = By.cssSelector(locProp);
			break;
		case "linkText":
			loc = By.linkText(locProp);
			break;
		case "partialLinkText":
			loc = By.partialLinkText(locProp);
			break;
		default:
			throw new NullPointerException("no such locator.. please provide valid locator type");
		}
		Driver.test.log(LogStatus.INFO, "element located successfully using  "+locType+" and value "+locProp);
		return loc;
	}

}
