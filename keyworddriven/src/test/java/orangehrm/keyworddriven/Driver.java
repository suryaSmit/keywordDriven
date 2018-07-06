package orangehrm.keyworddriven;

import java.lang.reflect.Method;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import helper.Excel;
import helper.GenericMethods;

public class Driver {

	static Excel TCD;
	static Excel TSD;
	public static ExtentTest test;

	public static void main(String[] args) {
		TCD = new Excel("resources", "Input.xls");
		TSD = new Excel("resources", "Input.xls");
		TCD.setExcel("testcases");
		TSD.setExcel("teststeps");
		ExtentReports report = new ExtentReports(GenericMethods.getPath("reports", "report.html"));
		
		Keywords actionKeywords = new Keywords();
		Method methods[] = actionKeywords.getClass().getMethods();

		for (int i = 1; i < TCD.rowCount(); i++) {
			String TCD_TCName = TCD.readData(i, Constants.TCD_TCNAME);
			String runMode = TCD.readData(i, Constants.RUNMODE);
			if (runMode.equals("yes")) {
				test = report.startTest(TCD_TCName);
				for (int j = 1; j < TSD.rowCount(); j++) {
					String TSD_TCName = TSD.readData(j, Constants.TSD_TCNAME);
					if (TCD_TCName.equals(TSD_TCName)) {
						String testStepName = TSD.readData(j, Constants.TSNAME);
						String locator = TSD.readData(j, Constants.LOCATOR);
						String locProperty = TSD.readData(j, Constants.LOCATORPROPERTY);
						String action = TSD.readData(j, Constants.ACTION);
						String input = TSD.readData(j, Constants.INPUT);
						test.log(LogStatus.INFO, testStepName);
						for (int m = 0; m < methods.length; m++) {
							if (action.equals(methods[m].getName())) {
								try {
									methods[m].invoke(actionKeywords, locator, locProperty, input);
									break;
								} catch (Exception e) {
									System.out.println(e.getMessage());
								}

							}
						}

					}

				}
				report.endTest(test);
			}

		}
		report.flush();
		report.close();

	}

}
