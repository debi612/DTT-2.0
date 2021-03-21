package com.dtt.pageactions;

import com.aventstack.extentreports.ExtentTest;
import com.dtt.pages.Signup;
import com.dtt.reports.ExtentLogger;
import com.dtt.utils.Excel;
import com.github.javafaker.Faker;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupAct extends Signup {

    public String email;
    public String cellNumber;
    public String firstName;
    public String lastName;
    public String companyName;
    public String companyNumber;
    public String dob;
    public String password;
    Faker fake;
    //sExtentTest test;

    public SignupAct() {
        //System.out.println("login default constructor");
        fake = new Faker();
    }

    public List<WebElement> getRadioButtonOnSignup() {
        return radioButton;
    }

    public SignupAct clickPersonalAccount() {
        personalAccount.click();
        ExtentLogger.info("personalAccount button clicked");
        return this;
    }

    public SignupAct clickBusineslAccount() {
        businessAccount.click();
        ExtentLogger.info("Business account selected");
        return this;
    }

    public SignupAct clickContinue() {
        btnContinue.click();
        ExtentLogger.info("continue button clicked");
        loaderCheck();
        return this;
    }

    public SignupAct clickBack() {
        btnBack.click();
        return this;
    }

    public SignupAct enterCountryEmailCellNumber() {
        selectCountry();
        enterEmail();
        enterCellNumber();
        return this;
    }

    public SignupAct selectCountry(String country) {
        inputCountry.click();
        wait(1);
        listCountry.stream().filter(s -> s.getText().equalsIgnoreCase(country)).findFirst().get().click();
        return this;
    }

    public SignupAct selectCountry() {
        inputCountry.click();
        wait(1);
        ExtentLogger.info("Country dropdown selected");
        listCountry.stream().findAny().get().click();
        ExtentLogger.info("Value selected from country dropdown selected");
        return this;
    }

    public SignupAct enterEmail() {
        email = fake.name().firstName() + "@mailinator.com";
        inputEmail.sendKeys(email + "\t");
        System.out.println(email + " email address");
        loaderCheck();
        return this;
    }

    public SignupAct enterEmail(String email) {
        inputEmail.sendKeys(email + "\t");
        System.out.println(email + " email address");
        loaderCheck();
        return this;
    }
    public SignupAct enterEmailFromExcel() {
        String path = System.getProperty("user.dir") + "/src/test/resources" + "/user.xlsx";
        Excel.setExcelFile(path, "user");
        email =  Excel.writeExcel();
        inputEmail.sendKeys(email + "\t");
        System.out.println(email + " email address");
        loaderCheck();
        return this;
    }

    public SignupAct enterCellNumber() {
        cellNumber = fake.phoneNumber().cellPhone().replaceAll("[^0-9]", "").toString();
        inputNumber.sendKeys(cellNumber + "\t");
        loaderCheck();
        return this;
    }

    public SignupAct enterCellNumber(String cellNumber) {
        inputNumber.sendKeys(cellNumber + "\t");
        loaderCheck();
        return this;
    }

    public SignupAct selectTitle(String title) {
        inputTitle.click();
        listTitle.stream().filter(s -> s.getText().equalsIgnoreCase(title)).findFirst().get().click();
        return this;
    }

    public SignupAct selectTitle() {
        loaderCheck();
        inputTitle.click();
        listTitle.stream().findFirst().get().click();
        return this;
    }

    public SignupAct enterFirstName() {
        firstName = fake.name().firstName();
        inputFirstName.sendKeys(firstName);
        return this;
    }

    public SignupAct enterFirstName(String userFirstName) {
        if (userFirstName.contains("@")) {
            firstName = userFirstName.split("@")[0];
        } else firstName = userFirstName;
        inputFirstName.sendKeys(firstName);
        return this;
    }



    public SignupAct enterLastName() {
        lastName = fake.name().lastName();
        inputLastName.sendKeys(lastName);
        return this;
    }

    public SignupAct enterCompanyName() {
        companyName = fake.company().name();
        inputCompanyName.sendKeys(companyName);
        return this;
    }

    public SignupAct enterCompanyNumber() {
        companyNumber = Integer.toString(fake.number().randomDigitNotZero());
        inputCompanyNumber.sendKeys(companyName);
        return this;
    }

    public SignupAct enterDOB() {
        dob = new SimpleDateFormat("MM/dd/yyyy").format(fake.date().birthday(19, 100));
        inputDOB.sendKeys(dob);
        wait(2);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript
                ("arguments[0].style.display='none';", calender);
        return this;
    }

    public SignupAct enterPasswordAndRetypePassword() {
        while (!isValidPassword(password)) {
            password = fake.internet().password(8, 10, true, true, true);
        }
        System.out.println("password = " + password);
        inputConfirmPassword.sendKeys(password);
        inputRetypePassword.sendKeys(password);
        return this;
    }

    public void enterPersonalSignupDetails(String userFirstname) {
        selectTitle().enterFirstName(userFirstname).enterLastName()
                .enterDOB().enterPasswordAndRetypePassword().clickTermAndCondition();
        clickSubmit();
    }

    public void enterBusinessSignupDetails(String userFirstname) {
        selectTitle().enterFirstName(userFirstname).enterLastName().enterCompanyName()
                .enterCompanyNumber()
                .enterDOB().enterPasswordAndRetypePassword().clickTermAndCondition();
        clickSubmit();
    }

    public void clickTermAndCondition() {
        chkTermsAndCondition.click();
    }

    public SignupAct clickSubmit() {
        btnSubmit.click();
        loaderCheck();
        return this;
    }

    public boolean isValidPassword(String password) {

        // Regex to check valid password.
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the password is empty
        // return false
        if (password == null) {
            return false;
        }

        // Pattern class contains matcher() method
        // to find matching between given password
        // and regular expression.
        Matcher m = p.matcher(password);

        // Return if the password
        // matched the ReGex
        return m.matches();
    }

    public boolean countyEmailandNumberDisplayed() {
        if (inputCountry.isDisplayed() && inputEmail.isDisplayed() && inputNumber.isDisplayed()) {
            //test.info(" country dropdown,  email textfield and cell number displayed on UI");
            return true;
        } else return false;
    }

    public boolean errorMessageDisplayed() {
        if (errorMessage.size() > 0) {
            System.out.println("errorMessage.size() =" + errorMessage.size());
            return true;
        } else {
            System.out.println("errorMessage.size() =" + errorMessage.size());
            return false;
        }
    }

    public boolean errorCellMessageDisplayed() {
        return errorMessage.stream().filter(s -> s.getText().contains("Phone number is required"))
                .findFirst().isPresent();
    }


}

