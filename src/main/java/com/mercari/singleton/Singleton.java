package com.mercari.singleton;

import org.openqa.selenium.WebDriver;

public class Singleton {

    public String AUT_environment = "";
    public String AUT_URL = "";
    public String userName = "";
    public String password = "";
    public WebDriver driver;
    public int maxRetry = 1;

    private Singleton(){}

    private static final class SingleInstanceHolder {
        static final Singleton singleInstance = new Singleton();
    }

    public static Singleton getInstance()
    {
        return SingleInstanceHolder.singleInstance;
    }
}
