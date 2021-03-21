

package com.dtt.driver;

import com.dtt.enums.ConfigProperties;
import com.dtt.exceptions.BrowserInvocationFailedException;
import com.dtt.factories.DriverFactory;
import com.dtt.utils.PropertyUtils;

import java.net.MalformedURLException;
import java.util.Objects;


public final class Driver {

    private Driver() {
    }


    public static void initDriver() {


        if (Objects.isNull(DriverManager.getDriver())) {
            try {
                DriverManager.setDriver(DriverFactory.getDriver());
            } catch (MalformedURLException e) {
                throw new BrowserInvocationFailedException("Please check the capabilities of browser");
            }
            DriverManager.getDriver().get(PropertyUtils.get(ConfigProperties.URL));

        }
    }


    public static void quitDriver() {
        if (Objects.nonNull(DriverManager.getDriver())) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }

}
