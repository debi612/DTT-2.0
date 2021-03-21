package com.dtt.pages;

import com.dtt.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    public WebDriver driver;

    public BasePage() {
        //System.out.println("login default constructor");
        driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
        while (!"true".equalsIgnoreCase(loader.getAttribute("hidden"))) {
            //System.out.println(loader.getAttribute("hidden"));
            System.out.println("Pagelodding .........");
            wait(1);
        }
    }

    @FindBy(xpath = "//div[@class='progress-loader']")
    public WebElement loader;


    public void loaderCheck() {
        wait(2);
        String loaderAvailableFlag = loader.getAttribute("hidden");
        while (!"true".equalsIgnoreCase(loaderAvailableFlag)) {
            //System.out.println(loader.getAttribute("hidden"));
            wait(1);
            loaderAvailableFlag = loader.getAttribute("hidden");
            System.out.println("Pageloading loadcheck method ......... " + loaderAvailableFlag);
        }
    }

    public void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            System.out.println(e.getStackTrace());
        }
    }

    public String clickLogo() {
        driver.findElement(By.className("logo-header")).click();
        return driver.getTitle();
    }
}
