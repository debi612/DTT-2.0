package com.dtt.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login extends BasePage {

    @FindBy(xpath = "//input[@id='txtLoginUsername']")
    public WebElement userName;

    @FindBy(xpath = "//input[@id='txtLoginPassword']")
    public WebElement password;

    @FindBy(xpath = "//button[text()='Sign in']")
    public WebElement signin;

    @FindBy(xpath = "//button[@type='button'][text()='Back']")
    public WebElement back;

    @FindBy(xpath = "//a[text()='Sign up']")
    public WebElement signUp;

    @FindBy(xpath = "//a[text()='Forgot password']")
    public WebElement forgotPassword;


}