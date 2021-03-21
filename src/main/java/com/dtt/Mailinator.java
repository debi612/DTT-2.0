package com.dtt;

import com.google.common.base.Function;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.online.UrlHandler;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mailinator {
    public static String activatelink(String username) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.mailinator.com/v4/public/inboxes.jsp?to=" + username);
        Mailinator u = new Mailinator();
        u.wait(3);
        WebElement activateEmail = driver.findElement(By.xpath("//td[contains(text(),'Activate your DT&T account')]"));
        fluentWait(driver, activateEmail);
        activateEmail.click();


        String url = driver.getCurrentUrl();
        String activationLink = queryParameter(url);

        String prfixURL = "https://www.mailinator.com/fetch_public?" + activationLink;

        Response res = RestAssured.get(prfixURL);
        JsonPath j = res.getBody().jsonPath();

        String token = queryParameter(j.getString("data.clickablelinks[0]"));
        String link = j.getString("data.clickablelinks[0]");
        driver.get(j.getString("data.clickablelinks[0]"));
        loaderCheck(driver);


        String otpText = driver.findElement(By.xpath("//form")).getText();
        String otp = "";

        String regex = "OTP ..\\d+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(otpText);
        while (m.find()) {
            otp = m.group(0).replaceAll("[^0-9]", "");
        }

        int otpLength = otp.length();
        for (int i = 0; i < otpLength; i++) {
            driver.findElement(By.xpath("(//div[contains(@class,'form-row')]//input)[" + (i + 1) + "]"))
                    .sendKeys(Character.toString(otp.charAt(i)));
        }
        driver.findElement(By.xpath("//button[@type='button'][text()='Continue']")).click();



        /*res = RestAssured.get("https://t2.dttmt.com/Trinity2/api/ValidateMobile?"+token);
        j = res.getBody().jsonPath();
        System.out.println(res.getBody().asString());
        String otp = j.getString("message");



        String text = "{"+token+","+otp+"}";
        text = text.replace("token=","token:\"").replace(",OTP = ","\",OTP:\"").replace("}","\"}");
        res = RestAssured.given().contentType(ContentType.JSON).body(text)
                .post("https://t2.dttmt.com/Trinity2/api/ValidateMobile");
        System.out.println(res.getStatusCode());*/


        driver.quit();
        return "";
    }

    public static void loaderCheck(WebDriver drive) {
        WebElement loader = drive.findElement(By.xpath("//div[@class='progress-loader']"));
        Mailinator u = new Mailinator();
        u.wait(2);
        String loaderAvailableFlag = loader.getAttribute("hidden");
        while (!"true".equalsIgnoreCase(loaderAvailableFlag)) {
            //System.out.println(loader.getAttribute("hidden"));
            u.wait(1);
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

    public static String queryParameter(String URL) {
        java.net.URL aurl = null;
        try {
            aurl = new URL(URL);
        } catch (MalformedURLException e) {
            System.out.println("url not formed properly");
            e.getStackTrace();
        }
        String prefixURL = aurl.getQuery();
        return prefixURL;
    }

    public static void fluentWait(WebDriver driver, WebElement element) {


        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
        wait.pollingEvery(Duration.ofSeconds(3));
        wait.withTimeout(Duration.ofSeconds(30));
        wait.ignoring(NoSuchElementException.class);

        Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver arg0) {
                boolean visible = false;
                try {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                    visible = element.isDisplayed();
                } catch (Exception e) {
                }
                if (visible == true) {
                    return true;
                }
                return false;
            }
        };

        wait.until(function);
    }
}
