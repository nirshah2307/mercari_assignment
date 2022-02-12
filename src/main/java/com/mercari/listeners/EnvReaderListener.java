package com.mercari.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import java.util.Map;

public class EnvReaderListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        Map<String, String> parameters = suite.getXmlSuite().getParameters();
        for (Map.Entry<String, String> parameter : parameters.entrySet()) {
            String env = System.getenv(parameter.getKey());
            if (env != null && ! env.trim().isEmpty()) {
                parameter.setValue(env);
            }
        }
    }

    @Override
    public void onFinish(ISuite suite) {

    }

}
