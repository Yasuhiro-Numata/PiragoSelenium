package listeners;

import controllers.ApplicationConfigReader;
import controllers.InitMethod;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.*;
import org.testng.xml.XmlSuite;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import utils.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WebDriverListenerBase implements ITestListener, IReporter  {
	private boolean isLocalMode = true;
	private String reportS3Path = "";
	Set<ITestResult> failedTests = new HashSet<ITestResult>();
	Set<ITestResult> succeedTests = new HashSet<ITestResult>();
	Set<ITestResult> skippedTests = new HashSet<ITestResult>();
	private String diffReportText = "";
	private String autotestReportText = "";

	public static final long processTimestamp =  System.currentTimeMillis();

	public void setIsLocalMode(Boolean val) {
		isLocalMode = val;
	}
    public void saveFullPageScreenshot(ITestResult testResult, WebDriver webDriver) throws IOException {
    	String name = imagePath(testResult, webDriver);

        Screenshot screenshot = new AShot().takeScreenshot(webDriver);
        ImageIO.write(screenshot.getImage(), "PNG", new File(name));
    }
    
    private String imagePath(ITestResult testResult, WebDriver webDriver) throws IOException {
    	return InitMethod.ReportErrorCaptured + testResult.getTestName() + "_" + browserName(webDriver) + ".png";
    }
    
    private String browserName(WebDriver webDriver) {
    	return ((RemoteWebDriver) webDriver).getCapabilities().getBrowserName();
    }

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
	}
	
	private String createApprovalInfo() {
		return "Approval Link: " + ApplicationConfigReader.instance.getAWSCODEPIPELINEApprovelURL();
	}
	
	private String createTestsMess(Set<ITestResult> result, boolean getErrorStack) {
		String res = "";
		for (ITestResult test : result) {
//			System.out.print("---------------" + test.getName());
			res += testResultStr(test, getErrorStack);
		}
		
		return res;
	}
	
	private String testResultStr(ITestResult test, boolean getErrorStack) {
		String result = "\n";
		result += test.getName() + " - browser: " + test.getTestContext().getCurrentXmlTest().getLocalParameters().get("browserName");
//		System.out.println("---------------" + test.getName() + test.getTestName() + "====" + test.getTestContext().getName());
		if (getErrorStack && test.getThrowable() != null) {

			StringWriter sw = new StringWriter();
			test.getThrowable().printStackTrace(new PrintWriter(sw));
			result += "  " + sw.toString();
		}
		
		return result;
	}

	private void setAutotestReportText(){
		autotestReportText += "\n\n>>> FAILED = " + failedTests.size() + "\n\n"
				+ createTestsMess(failedTests, true)
				+ "\n\n>>> SUCCEED = " + succeedTests.size() + "\n\n"
				+ createTestsMess(succeedTests, false)
				+ "\n\n>>> SKIPPED = " + skippedTests.size() + "\n\n"
				+ createTestsMess(skippedTests, false);
	}
	
//	private void sendMailReport() {
//		try {
//			boolean isSuccess = failedTests.size() == 0;
//
//			// #1. create autoTest mail
//			setAutotestReportText();
////			FileUtils.writeToFile(autotestReportText, InitMethod.AutoTestLogFile);
//
//			// #2. Zip report directory
//			ZipUtils.zip(InitMethod.ReportRootPath, InitMethod.ReportZipFilePath);
//
//			// #3. Upload to S3
//			reportS3Path = S3Utils.putObject(InitMethod.AwsS3BucketName, InitMethod.ReportZipFilePath);
//        	Log.info("Upload result to path : [" + reportS3Path + "]");
//
//        	// #4. Send mail
//			String mailContent = getReport(isSuccess);
//			MailUtils.sendReportMail(getMailSubject(isSuccess), mailContent, null);
//        } catch (Exception ex) {
//        	ex.printStackTrace();
//        }
//	}

	private void printFailedTest() {
		System.out.println("\n\n=============================" +
				"\n\nFailed Test:");
		String prefix = "       ";
		for (ITestResult test : failedTests) {
			System.out.println(prefix + test.getName() + "(" + test.getTestContext().getName() + ")");
		}
		System.out.println("\n\n=============================\n\n");
	}
	

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		//Iterating over each suite included in the test
		for (ISuite suite : suites) {

			//Following code gets the suite name
			System.out.println(suite.getName());
			//Getting the results for the said suite
			Map<String, ISuiteResult> suiteResults = suite.getResults();

			for (ISuiteResult sr : suiteResults.values()) {
				ITestContext tc = sr.getTestContext();
				failedTests.addAll(tc.getFailedTests().getAllResults());
				succeedTests.addAll(tc.getPassedTests().getAllResults());
				skippedTests.addAll(tc.getSkippedTests().getAllResults());
			}
		}

//		if (!isLocalMode && !ApplicationConfigReader.instance.isLocal()){
//			// 1. Run diff screenShot
//			PhotoComparator comparator = new PhotoComparator();
//			this.diffReportText = comparator.runDiff();
//
//			// 2. Zip and send result
//			sendMailReport();
//			printFailedTest();
//		}
//
//		if (isLocalMode && ApplicationConfigReader.instance.isLocal()){
//			printFailedTest();
//		}

		printFailedTest();
	}
}
