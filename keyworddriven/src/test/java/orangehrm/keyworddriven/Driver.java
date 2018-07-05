package orangehrm.keyworddriven;

import java.lang.reflect.Method;

import helper.Excel;

public class Driver {

	static Excel TCD;
	static Excel TSD;

	public static void main(String[] args) {
		TCD = new Excel("resources", "hrm_keywords.xls");
		TSD = new Excel("resources", "hrm_keywords.xls");
		TCD.setExcel("testcases");
		TSD.setExcel("teststeps");
		Keywords actionKeywords = new Keywords();
		Method methods[] = actionKeywords.getClass().getMethods();

		for (int i = 1; i < TCD.rowCount(); i++) {
			String TCD_TCName = TCD.readData(i, Constants.TCD_TCNAME);
			String runMode = TCD.readData(i, Constants.RUNMODE);
			if (runMode.equals("yes")) {
				for (int j = 0; j < TSD.rowCount(); j++) {
					String TSD_TCName = TSD.readData(j, Constants.TSD_TCNAME);
					if (TCD_TCName.equals(TSD_TCName)) {
						String testStepName = TSD.readData(j, Constants.TSNAME);
						String locator = TSD.readData(j, Constants.LOCATOR);
						String locProperty = TSD.readData(j, Constants.LOCATORPROPERTY);
						String action = TSD.readData(j, Constants.ACTION);
						String input = TSD.readData(j, Constants.INPUT);
						for (int m = 0; m < methods.length; m++) {
							if (action.equalsIgnoreCase(methods[m].getName())) {
								try {
									methods[m].invoke(actionKeywords, locator, locProperty, input);
									break;
								} catch (Exception e) {

								}

							}
						}

					}

				}
			}

		}

	}

}
