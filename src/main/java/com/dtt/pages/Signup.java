package com.dtt.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Signup extends BasePage {

    public Signup() {
        //System.out.println("login default constructor");
    }

    @FindBy(xpath = "//input[@type='radio']")
    public List<WebElement> radioButton;

    //@FindBy(xpath = "//input[@id='rdoCustomerTypePersonal']")
    @FindBy(xpath = "//label[@for='rdoCustomerTypePersonal']")
    public WebElement personalAccount;

    @FindBy(xpath = "//input[@id='rdoCustomerTypeBusiness']")
    public WebElement businessAccount;

    @FindBy(xpath = "//button[@type='button'][text()='Continue']")
    public WebElement btnContinue;

    @FindBy(xpath = "//button[@type='button'][text()='Back']")
    public WebElement btnBack;

    @FindBy(xpath = "//button[text()='Submit']")
    public WebElement btnSubmit;

    @FindBy(xpath = "//*[@id='drpSignupCountry'][@formcontrolname='countryId']//input")
    public WebElement inputCountry;

    @FindBy(xpath = "//*[@role='listbox']//div[@role='option']")
    public List<WebElement> listCountry;

    @FindBy(xpath = "//input[@type='text'][@id='txtSignupEmail']")
    public WebElement inputEmail;

    @FindBy(xpath = "//input[@type='text'][@id='txtSignupPhoneNumber']")
    public WebElement inputNumber;

    @FindBy(xpath = "//input[@id='txtSignupFirstName']")
    public WebElement inputFirstName;

    @FindBy(xpath = "//input[@id='txtSignupLastName']")
    public WebElement inputLastName;

    @FindBy(xpath = "//input[@id='txtSignupCompanyName']")
    public WebElement inputCompanyName;

    @FindBy(xpath = "//input[@id='txtSignupCompanyNumber']")
    public WebElement inputCompanyNumber;

    @FindBy(xpath = "//input[@id='txtSignupDOB']")
    public WebElement inputDOB;

    @FindBy(xpath = "//bs-datepicker-container")
    public WebElement calender;

    @FindBy(xpath = "//input[@id='txtSignupPassword']")
    public WebElement inputConfirmPassword;

    @FindBy(xpath = "//input[@id='txtSignupRetypePassword']")
    public WebElement inputRetypePassword;

    @FindBy(xpath = "//*[@id='drpSignupTitle'][@formcontrolname='titleId']//input")
    public WebElement inputTitle;

    @FindBy(xpath = "//*[@role='listbox']//div[@role='option']")
    public List<WebElement> listTitle;

    @FindBy(xpath = "//label[@for='chkSignupTermsAndConditions']")
    public WebElement chkTermsAndCondition;

    @FindBy(xpath = "//div[@class='error-message']")
    public List<WebElement> errorMessage;


}

