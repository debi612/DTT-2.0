package com.dtt.driver;

import org.openqa.selenium.WebDriver;


public final class DriverManager {

    /**
     * Private constructor to avoid external instantiation
     */
    private DriverManager() {
    }

    private static ThreadLocal<WebDriver> dr = new ThreadLocal<>();


    public static WebDriver getDriver() {
        return dr.get();
    }


    public static void setDriver(WebDriver driverref) {
        dr.set(driverref);
    }

    public static void unload() {
        dr.remove();
    }

}
