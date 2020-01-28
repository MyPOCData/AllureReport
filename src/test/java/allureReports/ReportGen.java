package allureReports;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReportGen {

	public static void main(String[] args) throws IOException, InterruptedException {
		cleanResults();
		reportGen();
		
//        String cpHistoryFolder = "cp -R /Users/b0097042/my-workplace/AllurereportingSeleniumTestNG/Report/history/ /Users/b0097042/my-workplace/AllurereportingSeleniumTestNG/allure-results/history";
//        String removeReportFolder = "rm -r /Users/b0097042/my-workplace/AllurereportingSeleniumTestNG/Report";
//		String mkDirReport = "mkdir /Users/b0097042/my-workplace/AllurereportingSeleniumTestNG/Report";
//		String genReport = "/usr/local/bin/allure generate /Users/b0097042/my-workplace/AllurereportingSeleniumTestNG/allure-results -o /Users/b0097042/my-workplace/AllurereportingSeleniumTestNG/Report";
//        String openReport = "/usr/local/bin/allure open /Users/b0097042/my-workplace/AllurereportingSeleniumTestNG/Report";
//        String rmAllureResults = "rm -r /Users/b0097042/my-workplace/AllurereportingSeleniumTestNG/allure-results";
//        
//        File dirReport = new File("/Users/b0097042/my-workplace/AllurereportingSeleniumTestNG/Report");
//        if(dirReport.exists()) {
//        	 Runtime.getRuntime().exec(removeReportFolder);
//        }
//        Runtime.getRuntime().exec(mkDirReport);
//        Runtime.getRuntime().exec(genReport);
//        
//        File dirAllureResult = new File("/Users/b0097042/my-workplace/AllurereportingSeleniumTestNG/allure-results");
//        if(dirAllureResult.exists()) {
//        	//remove all files except history folder 
//        }
//        Runtime.getRuntime().exec(cpHistoryFolder);
//        Runtime.getRuntime().exec(rmAllureResults);
//        
//        
//        Runtime.getRuntime().exec(openReport);
	}
	
	public static void cleanResults() throws IOException {
		File dirAllureResult = new File("/Users/b0097042/my-workplace/AllurereportingSeleniumTestNG/allure-results");
        if(dirAllureResult.exists()) {
        	//remove all files except history folder 
        	for (File file: dirAllureResult.listFiles()) {
        		if(!file.getName().equals("history")) {
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
	
	public static void reportGen() throws IOException, InterruptedException {
		String mkDirReport = "mkdir /Users/b0097042/my-workplace/AllurereportingSeleniumTestNG/Report";
		Runtime.getRuntime().exec(mkDirReport);
		String genReport = "/usr/local/bin/allure generate /Users/b0097042/my-workplace/AllurereportingSeleniumTestNG/allure-results -o /Users/b0097042/my-workplace/AllurereportingSeleniumTestNG/Report";
		Runtime.getRuntime().exec(genReport);
		
//        String openReport = "/usr/local/bin/allure open /Users/b0097042/my-workplace/AllurereportingSeleniumTestNG/Report";
//        Runtime.getRuntime().exec(openReport);
//        Thread.sleep(5000);
		//create temp folder and copy this Report folder data in it
        File temp = new File("/Users/b0097042/my-workplace/AllurereportingSeleniumTestNG/TempReport");
        if(temp.exists()) {
        	String removeTemp = "rm -r /Users/b0097042/my-workplace/AllurereportingSeleniumTestNG/TempReport";
            Runtime.getRuntime().exec(removeTemp);
        }
		String mkDirTempReport = "mkdir /Users/b0097042/my-workplace/AllurereportingSeleniumTestNG/TempReport";
		Runtime.getRuntime().exec(mkDirTempReport);
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
}