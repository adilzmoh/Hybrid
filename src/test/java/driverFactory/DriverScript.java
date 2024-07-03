package driverFactory;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import utilities.ExcelFileUtil;

public class DriverScript 
{
	String inputpath= "./FileInput/DataEngine.xlsx";
	String outputpath= "./FileOutput/HybridResults.xlsx";
	ExtentReports report;
	ExtentTest logger;
	String Sheet = "MasterTestCases";
	WebDriver driver;
	public void startTest() throws Throwable
	{
		//String Modulestatus = "True";
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		for(int i=1;i<=xl.rowCount(Sheet);i++)
		{
			String Modulestatus = "True";
			if(xl.getCellData(Sheet, i, 2).equalsIgnoreCase("Y")) 
			{
				String TCModule = xl.getCellData(Sheet, i, 1);
				report = new ExtentReports("./target/Reports/"+TCModule+FunctionLibrary.generateDate()+".html");
				logger = report.startTest(TCModule);
				logger.assignAuthor("Adil");
				for(int j=1;j<=xl.rowCount(TCModule);j++) 
				{
					String Description = xl.getCellData(TCModule, j, 0);
					String Object_Type = xl.getCellData(TCModule, j, 1);
					String Locator_Type = xl.getCellData(TCModule, j, 2);
					String Locator_Value = xl.getCellData(TCModule, j, 3);
					String Test_Data = xl.getCellData(TCModule, j, 4);
					try 
					{
						if(Object_Type.equalsIgnoreCase("startBrowser")) 
						{
							driver = FunctionLibrary.startBrowser();
							logger.log(LogStatus.INFO, Description);
						}
						if(Object_Type.equalsIgnoreCase("openUrl")) 
						{
							FunctionLibrary.openUrl();
							logger.log(LogStatus.INFO, Description);
						}
						if(Object_Type.equalsIgnoreCase("waitForElement")) 
						{
							FunctionLibrary.waitForElement(Locator_Type, Locator_Value, Test_Data);
							logger.log(LogStatus.INFO, Description);
						}
						if(Object_Type.equalsIgnoreCase("typeAction")) 
						{
							FunctionLibrary.typeAction(Locator_Type, Locator_Value, Test_Data);
							logger.log(LogStatus.INFO, Description);
						}
						if(Object_Type.equalsIgnoreCase("clickAction")) 
						{
							FunctionLibrary.clickAction(Locator_Type, Locator_Value);
							logger.log(LogStatus.INFO, Description);
						}
						if(Object_Type.equalsIgnoreCase("validateTitle")) 
						{
							FunctionLibrary.validateTitle(Test_Data);
							logger.log(LogStatus.INFO, Description);
						}
						if(Object_Type.equalsIgnoreCase("closeBrowser")) 
						{
							FunctionLibrary.closeBrowser();
							logger.log(LogStatus.INFO, Description);
						}
						if(Object_Type.equalsIgnoreCase("dropDownAction")) 
						{
							FunctionLibrary.dropDownAction(Locator_Type, Locator_Value, Test_Data);
							logger.log(LogStatus.INFO, Description);
						}
						if(Object_Type.equalsIgnoreCase("capturestock")) 
						{
							FunctionLibrary.capturestock(Locator_Type, Locator_Value);
							logger.log(LogStatus.INFO, Description);
						}
						if(Object_Type.equalsIgnoreCase("stockTable")) 
						{
							FunctionLibrary.stockTable();
							logger.log(LogStatus.INFO, Description);
						}
						if(Object_Type.equalsIgnoreCase("capturesup")) 
						{
							FunctionLibrary.capturesup(Locator_Type, Locator_Value);
							logger.log(LogStatus.INFO, Description);
						}
						if(Object_Type.equalsIgnoreCase("supplierTable")) 
						{
							FunctionLibrary.supplierTable();
							logger.log(LogStatus.INFO, Description);
						}
						if(Object_Type.equalsIgnoreCase("capturecus")) 
						{
							FunctionLibrary.capturecus(Locator_Type, Locator_Value);
							logger.log(LogStatus.INFO, Description);
						}
						if(Object_Type.equalsIgnoreCase("customerTable")) 
						{
							FunctionLibrary.customerTable();
							logger.log(LogStatus.INFO, Description);
						}
						xl.setCellData(TCModule, j, 5, "Pass", outputpath);
						logger.log(LogStatus.PASS, Description);
						//Modulestatus = "True";
						
					} catch (Exception e) 
					{
						System.out.println(e.getMessage());
						xl.setCellData(TCModule, j, 5, "Fail", outputpath);
						logger.log(LogStatus.FAIL, Description);
						Modulestatus = "False";
					}
					if(Modulestatus.equalsIgnoreCase("True")) 
					{
						xl.setCellData(Sheet, i, 3, "Pass", outputpath);
					}
					if(Modulestatus.equalsIgnoreCase("False")) 
					{
						xl.setCellData(Sheet, i, 3, "Fail", outputpath);
					}
					report.endTest(logger);
					report.flush();
					
				}
			}
			else 
			{
				xl.setCellData(Sheet, i, 3, "Blocked", outputpath);
			}
		}
	}

}











