package com.mercari.exceptionHandling;

import com.mercari.extentReports.ExtentManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.ITestResult;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

/**
 * @author Nirmal.Shah
 *
 */
public class ExceptionTrap {
	
	private ArrayList<Exception> registeredException = new ArrayList<Exception>();
	private ITestResult testResult = null;
	private static final Logger log = LogManager.getLogger(ExtentManager.class);
	
	public ExceptionTrap(ITestResult result) {
		this.testResult = result;
		// Add run time exception here
		registeredException.add(new NoSuchElementException(ExceptionMesages.NoSuchElementException.toString()));
		registeredException
				.add(new StaleElementReferenceException(ExceptionMesages.StaleElementReferenceException.toString()));
		registeredException.add(new NullPointerException(ExceptionMesages.NullPointerException.toString()));
		registeredException.add(new NoSuchWindowException(ExceptionMesages.NoSuchWindowException.toString()));
	}
	
	public void setExtendReportStatus() throws InterruptedException {

		boolean matchedRegisteredException = false;
		if (testResult.getStatus() == ITestResult.SKIP) {
			log.debug("Logger.debug " +  testResult.getMethod().getMethodName() + " : "+ getExceptionStackTrace());
			return;
		}

		for (Exception exception : registeredException) {
			if (exception.getClass().getName().equalsIgnoreCase(testResult.getThrowable().getClass().getName())) {
				log.debug("Logger.debug " +testResult.getMethod().getMethodName() + " : "+  getExceptionStackTrace());
				matchedRegisteredException = true;
			}
		}
		if(!matchedRegisteredException){
			log.debug("Logger.debug " +  getExceptionStackTrace());
		}
	}
	
	private String getExceptionStackTrace(){
		StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    Throwable cause = testResult.getThrowable();
	    if (null != cause) {
	        cause.printStackTrace(pw);
	       return sw.getBuffer().toString();
	    }
		return null;
	}

}
