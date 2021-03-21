package com.dtt.pageactions;

import com.dtt.pages.Login;
import com.dtt.reports.ExtentLogger;

public class LoginAct extends Login {


    public LoginAct() {
        //System.out.println("login default constructor");
        wait(5);
    }

    public void enterUsername(String userName) {
        this.userName.sendKeys(userName);
        ExtentLogger.info("Username entered in password field "+userName);
    }

    public void enterPassword(String enterPassword) {
        password.sendKeys(enterPassword);
        ExtentLogger.info("password entered in password field "+enterPassword);
    }

    public void clickSignin() {
        signin.click();
        ExtentLogger.info("Signin button clicked");
    }

    public void clickBack() {
        back.click();
        ExtentLogger.info("Back button clicked");
    }

    public SignupAct clickSignUp() {
        signUp.click();
        ExtentLogger.info("sign up button click");
        return new SignupAct();
    }

    public void clickForgotPassword() {
        forgotPassword.click();
    }

    public void login(String userName, String password) {
        enterUsername(userName);
        enterPassword(password);
        clickSignin();
    }


    public boolean verifySignInButtonAvailable() {
        boolean flag = signin.isDisplayed();
        ExtentLogger.info("Sign in button displayed "+flag);
        return flag;
    }
}

