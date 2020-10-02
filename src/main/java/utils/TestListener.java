package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/*
 * Docomunetation: http://static.javadoc.io/org.testng/testng/6.11/org/testng/ITestListener.html
 */
public class TestListener implements ITestListener {
	
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

	public void onFinish(ITestContext iTestContext) {
		// TODO Auto-generated method stub
		Log.endLog(iTestContext.getName()); //log end of class
	}

	public void onStart(ITestContext iTestContext) {
		// TODO Auto-generated method stub
		Log.startLog(iTestContext.getName()); //log start of class
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult iTestResult) {
		// TODO Auto-generated method stub
		Log.error("FAILED Test: " +iTestResult.getTestClass() +": " +iTestResult.getName());
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestStart(ITestResult iTestResult) {
		// TODO Auto-generated method stub
		Log.startMethodLog(getTestMethodName(iTestResult)); // log start of the method
	}

	public void onTestSuccess(ITestResult iTestResult) {
		// TODO Auto-generated method stub
		//Log.info("Passed: " +iTestResult);
		Log.info("PASSED Test: " +iTestResult.getTestClass() +": " +iTestResult.getName());
		/*
		Log.info("getInstanceName: " +iTestResult.getInstanceName());
		Log.info("getName: " +iTestResult.getName());
		Log.info("getTestName: " +iTestResult.getTestName());
		Log.info("getStatus: " +iTestResult.getStatus());
		Log.info("getTestClass: " +iTestResult.getTestClass());
		Log.info("getClass: " +iTestResult.getClass());
		*/
	}

}
