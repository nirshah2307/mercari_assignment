package com.mercari.listeners;

import com.mercari.singleton.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import static com.mercari.listeners.TestListener.extent;
import static com.mercari.listeners.TestListener.test;

/**
 * @author Nirmal.Shah
 */
public class RetryListener implements IRetryAnalyzer {

    public static final int maxTry = Singleton.getInstance().maxRetry;
    private static final Logger log = LogManager.getLogger(RetryListener.class);
    private int count = 0;

    @Override
    public boolean retry(ITestResult iTestResult) {

        if (!iTestResult.isSuccess()) {
            if (count < maxTry) {
                count++;
                log.info("Going to retry test case: " + iTestResult.getMethod() + ", " + count
                        + " out of " + maxTry);

                extent.removeTest(test.get().fail(iTestResult.getThrowable()));
                return true;
            }
        }
        return false;
    }
}
