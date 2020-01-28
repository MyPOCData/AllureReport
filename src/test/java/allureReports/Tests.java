package allureReports;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

@Listeners({AllureListener.class})
public class Tests extends BaseClass{

	public WebDriver driver;
	
	@BeforeClass 
	public void setUp() {
		BaseClass bs= new BaseClass();
		driver = bs.initialize_driver();
		driver.get("https://demo.nopcommerce.com/");		
	}
	
	@BeforeMethod
	public void videoRecord() throws SecurityException, Exception {

	}
	
	@AfterMethod
	public void stopRecoding() throws Exception {

	}
	
	@Severity(SeverityLevel.MINOR)	
	@Test(priority=1, description="Verify Logo presence on Home Page")
	@Description("Verify Logo presence on Home Page........")
	@Epic("EP001")
	@Feature("Feature1: Logo")
	@Story("Story:Logo Presence")
	@Step("Verify logo Presence")
	public void logoPresence() throws Exception{
		MyScreenRecorder.startRecording(new Tests() {}.getClass().getEnclosingMethod().getName());
		Thread.sleep(2000);
		boolean dispStatus=driver.findElement(By.xpath("//div[@class='header-logo']//a//img")).isDisplayed();
		Assert.assertEquals(dispStatus, true);
		MyScreenRecorder.stopRecording();
	}
	
	@Severity(SeverityLevel.BLOCKER)	
	@Test(priority=2, description="Verify login")
	@Description("Verify login with Valid Credentials........")
	@Epic("EP001")
	@Feature("Feature2: Login")
	@Story("Story:Valid login")
	@Step("Verify login")
	public void loginTest() throws Exception{
		MyScreenRecorder.startRecording(new Tests() {}.getClass().getEnclosingMethod().getName());
		driver.findElement(By.linkText("Log in")).click();
		driver.findElement(By.id("Email")).sendKeys("pavanoltraining@gmail.com");
		driver.findElement(By.id("Password")).sendKeys("Test@123");
		driver.findElement(By.xpath("//input[@class='button-1 login-button']")).click();
		Thread.sleep(3000);
		Assert.assertEquals(driver.getTitle(), "nopCommerce demo store");
		MyScreenRecorder.stopRecording();
	}
		
	@Severity(SeverityLevel.NORMAL)	
	@Test(priority=3, description="Verify user Registration")
	@Description("Verify user Registration........")
	@Epic("EP001")
	@Feature("Feature3: Registration")
	@Story("Story:User registration")
	
	public void registrationTest() throws Exception{
		throw new SkipException("Skipping this Test");
	}
	
	@Severity(SeverityLevel.TRIVIAL)	
	@Test(priority=4, description="Verify user Registration1")
	@Description("Verify user Registration1........")
	@Epic("EP001")
	@Feature("Feature3: Registration1")
	@Story("Story:User registration1")
	
	public void registration1Test() throws Exception{
		Assert.assertTrue(true);
	}
	

	@AfterClass
	public void tearDown(){	
		driver.quit();
	}
	
	@BeforeSuite
	public void cleanResults() throws IOException {
		File dirAllureResult = new File("/Users/b0097042/my-workplace/AllurereportingSeleniumTestNG/allure-results");
        if(dirAllureResult.exists()) {
        	//remove all files except history folder 
        	for (File file: dirAllureResult.listFiles()) {
        		if(!file.getName().equals("history")) {   //|| !file.getName().equals("environment.properties")
        			file.delete();
        		}
        	}
        }
        File dirResult = new File("/Users/b0097042/my-workplace/AllurereportingSeleniumTestNG/Report");
        if(dirResult.exists()) {
        	//remove Report folder and its contents
        	String removeReportFolder = "rm -r /Users/b0097042/my-workplace/AllurereportingSeleniumTestNG/Report";
        	Runtime.getRuntime().exec(removeReportFolder);
        }
	}
	
	@AfterSuite
	public void reportGen() throws IOException, InterruptedException {
		File reportDir = new File("/Users/b0097042/my-workplace/AllurereportingSeleniumTestNG/Report");
		reportDir.mkdir();
		String genReport = "/usr/local/bin/allure generate -c -o /Users/b0097042/my-workplace/AllurereportingSeleniumTestNG/Report";
				
		Runtime.getRuntime().exec(genReport);
        File temp = new File("/Users/b0097042/my-workplace/AllurereportingSeleniumTestNG/TempReport");
        if(temp.exists()) {
        	String removeTemp = "rm -r /Users/b0097042/my-workplace/AllurereportingSeleniumTestNG/TempReport";
            Runtime.getRuntime().exec(removeTemp);
        }
        Thread.sleep(2000);
        temp.mkdir();
        Thread.sleep(2000);
        CopyData.copyDirectory(reportDir,temp);
		Thread.sleep(2000);
        String saveInTempFolder = "cp -R /Users/b0097042/my-workplace/AllurereportingSeleniumTestNG/Report /Users/b0097042/my-workplace/AllurereportingSeleniumTestNG/TempReport";
        Runtime.getRuntime().exec(saveInTempFolder);
		//remove all files/folders from history folder of allure_result
        File historyFolder = new File("/Users/b0097042/my-workplace/AllurereportingSeleniumTestNG/allure-results/history");
        if(historyFolder.exists()) {
        	String removeReportFolder = "rm -r /Users/b0097042/my-workplace/AllurereportingSeleniumTestNG/allure-results/history";
            Runtime.getRuntime().exec(removeReportFolder);
            Thread.sleep(2000);
        }
		String cpHistoryFolder = "cp -R /Users/b0097042/my-workplace/AllurereportingSeleniumTestNG/Report/history/ /Users/b0097042/my-workplace/AllurereportingSeleniumTestNG/allure-results/history";
		Runtime.getRuntime().exec(cpHistoryFolder);		
	}	
	
//	public void copyDirectory(File sourceLocation , File targetLocation)throws IOException {
//		if (sourceLocation.isDirectory()) {
//			if (!targetLocation.exists()) {
//				targetLocation.mkdir();
//		    }
//		    String[] children = sourceLocation.list();
//		    for (int i=0; i<children.length; i++) {
//		    	copyDirectory(new File(sourceLocation, children[i]),new File(targetLocation, children[i]));
//		    }
//		}
//	}
}
