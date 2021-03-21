package com.dtt.tests;

import com.dtt.Mailinator;
import com.dtt.listeners.ListenerClass;
import com.dtt.pageactions.LoginAct;
import com.dtt.pageactions.SignupAct;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Listeners(ListenerClass.class)
public class SignUp extends BaseTest {

    LoginAct logact;

    //TS_1
    @Test
    public void verifyDefaultLoginPage() {
        logact = new LoginAct();
        Assert.assertTrue(logact.verifySignInButtonAvailable());

    }

    //TS_2
    @Test
    public void verifySignupPageLinks() {
        List<String> radioButtonIds = Arrays.asList("rdoCustomerTypeBusiness", "rdoCustomerTypePersonal");
        logact = new LoginAct();
        List<WebElement> radioButton = logact.clickSignUp().getRadioButtonOnSignup();
        List<String> elements = radioButton.stream().map(s -> s.getAttribute("id")).collect(Collectors.toList());
        Assert.assertTrue(elements.containsAll(radioButtonIds));

    }

    //TS_3
    @Test
    public void verifySignupDetailsDisplayed() {
        logact = new LoginAct();
        boolean isDisplayed = logact.clickSignUp().clickPersonalAccount().clickContinue()
                .countyEmailandNumberDisplayed();
        Assert.assertTrue(isDisplayed);

    }

    //TS_4
   @Test
    public void verifyErrorMessageForSignupDetailsDisplayed() {
        logact = new LoginAct();
        boolean isDisplayed = logact.clickSignUp().clickPersonalAccount().clickContinue()
                .selectCountry("United Kingdom")
                .enterEmail("").enterCellNumber("").clickContinue().errorMessageDisplayed();
        Assert.assertTrue(isDisplayed);
    }

    @Test
    public void verifyPersonalAccountSignup() {
        logact = new LoginAct();
        SignupAct signup = logact.clickSignUp().clickPersonalAccount().clickContinue().selectCountry("United Kingdom")
                .enterEmailFromExcel().enterCellNumber().clickContinue();
        signup.enterPersonalSignupDetails(signup.email);
        String activateURL = Mailinator.activatelink(signup.firstName);
    }

    @Test
    public void verifyBusinessAccountSingnup() {
        logact = new LoginAct();
        SignupAct signup = logact.clickSignUp().clickBusineslAccount().clickContinue().selectCountry("United Kingdom")
                .enterEmail().enterCellNumber().clickContinue();
        signup.enterBusinessSignupDetails(signup.email);
        String activateURL = Mailinator.activatelink(signup.firstName);
    }

    //TS_6
    @Test
    public void verifyBackButtonRedirectSignup() {
        logact = new LoginAct();
        List<WebElement> radioButton = logact.clickSignUp().clickPersonalAccount().clickContinue()
                .enterCountryEmailCellNumber().clickBack().getRadioButtonOnSignup();
        List<String> radioButtonIds = Arrays.asList("rdoCustomerTypeBusiness", "rdoCustomerTypePersonal");
        List<String> elements = radioButton.stream().map(s -> s.getAttribute("id")).collect(Collectors.toList());
        Assert.assertTrue(elements.containsAll(radioButtonIds));
    }

    //TS_7
    @Test
    public void verifyErrorMessageForMissiningCell() {
        logact = new LoginAct();
        boolean isDisplayed = logact.clickSignUp().clickPersonalAccount().clickContinue()
                .selectCountry("United Kingdom")
                .enterEmail().enterCellNumber("").clickContinue().errorCellMessageDisplayed();
        Assert.assertTrue(isDisplayed);
    }

    //TS_8
    @Test
    public void verifyHomePageNavigation() {
        logact = new LoginAct();
        String pageTitle = logact.clickSignUp().clickPersonalAccount().clickContinue().selectCountry("United Kingdom")
                .enterEmail().enterCellNumber().clickContinue().clickLogo();
        Assert.assertEquals(pageTitle, "");
    }

    //TS_9
    @Test
    public void verifyFirstAndLastNameMaxLen() {
        logact = new LoginAct();
        SignupAct signup = logact.clickSignUp().clickBusineslAccount().clickContinue().selectCountry("United Kingdom")
                .enterEmail().enterCellNumber().clickContinue();
        signup.inputFirstName.getAttribute("maxlength");
        signup.inputLastName.getAttribute("maxlength");
        String expectedLength = "50";
        Assert.assertTrue((expectedLength.equals(signup.inputFirstName.getAttribute("maxlength")))
                && (expectedLength.equals(signup.inputLastName.getAttribute("maxlength"))));

    }

    //TS_9
    @Test
    public void verifyEmailMaxLen() {
        logact = new LoginAct();
        SignupAct signup = logact.clickSignUp().clickBusineslAccount().clickContinue().selectCountry("United Kingdom")
                .enterEmail();
        String expectedEmailLength = "60";
        Assert.assertTrue(expectedEmailLength.equals(signup.inputEmail.getAttribute("maxlength")));
    }

    //TS_9
    @Test
    public void verifyPasswordMaxLen() {
        logact = new LoginAct();
        SignupAct signup = logact.clickSignUp().clickBusineslAccount().clickContinue().selectCountry("United Kingdom")
                .enterEmail().enterCellNumber().clickContinue();
        String expectedPasswordLength = "20";
        Assert.assertTrue(expectedPasswordLength.equals(signup.inputConfirmPassword.getAttribute("maxlength")));

    }

    //TS_10
    @Test
    public void verifyErrorMessageOnSignupAccount() {
        logact = new LoginAct();
        SignupAct signup = logact.clickSignUp().clickBusineslAccount().clickContinue().selectCountry("United Kingdom")
                .enterEmail().enterCellNumber().clickContinue().clickSubmit();
        Assert.assertTrue(signup.errorMessageDisplayed());
    }

}
