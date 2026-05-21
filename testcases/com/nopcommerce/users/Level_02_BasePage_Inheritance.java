package com.nopcommerce.users;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Level_02_BasePage_Inheritance extends BasePage {
    private WebDriver driver;
    private String firstName, middleName, lastName,email,companyName,password;


    @BeforeClass
    public void beforeClass(){
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--user-data-dir=C:/Users/ADMIN/AppData/Local/Google/Chrome/User Data/");
//        chromeOptions.addArguments("--profile-directory=Profile 1");
//        driver = new ChromeDriver(chromeOptions);
//        driver.get("https://demo.nopcommerce.com/");
        driver = new FirefoxDriver();
        driver.get("http://localhost:8086/");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        firstName = "Bo";
        middleName = "Van";
        lastName = "Bap";
        email="bobapthui" + generateRandomNumber() + "@gmail.com";
        companyName="bobapthui";
        password = "1234567";

    }

    @Test
    public void TC_01_Register() throws InterruptedException {

//        driver.findElement(By.cssSelector("a.ico-register")).click();
        waitForElementClickable(driver,"//a[@class='ico-register']");
        clickToElement(driver,"//a[@class='ico-register']");

        waitForElementClickable(driver,"//input[@id='gender-female']");
        clickToElement(driver,"//input[@id='gender-female']");

        sendkeyToElement(driver,"//input[@id='FirstName']",firstName);
        sendkeyToElement(driver,"//input[@id='LastName']",lastName);
        sendkeyToElement(driver,"//input[@id='Email']",email);
        sendkeyToElement(driver,"//input[@id='Company']",companyName);
        sendkeyToElement(driver,"//input[@id='Password']",password);
        sendkeyToElement(driver,"//input[@id='ConfirmPassword']",password);

        waitForElementClickable(driver,"//button[@id='register-button']");
        clickToElement(driver,"//button[@id='register-button']");
        sleepInSeconds(2);
        Assert.assertEquals(getTextElement(driver,"//div[@class='result']"),"Your registration completed");
        waitForElementClickable(driver,"//a[@class='ico-logout']");
        clickToElement(driver,"//a[@class='ico-logout']");
        sleepInSeconds(5);
    }

    @Test
    public void TC_02_Login(){

        waitForElementClickable(driver,"//a[@class='ico-login']");
        clickToElement(driver,"//a[@class='ico-login']");

        sendkeyToElement(driver,"//input[@id='Email']",email);
        sendkeyToElement(driver,"//input[@id='Password']",password);
        clickToElement(driver,"//button[@class='button-1 login-button']");

        Assert.assertTrue(isControlDisplayed(driver,"//a[@class='ico-account' and text() = 'My account']"));

    }



    @Test
    public void TC_03_MyAccount(){
        waitForElementClickable(driver,"//a[@class='ico-account']");
        clickToElement(driver,"//a[@class='ico-account']");

        Assert.assertTrue(isControlSelected(driver,"//input[@id='gender-female']"));
        Assert.assertEquals(getAtributeValue(driver,"//input[@id='FirstName']","value"),firstName);
        Assert.assertEquals(getAtributeValue(driver,"//input[@id='LastName']","value"),lastName);
        Assert.assertEquals(getAtributeValue(driver,"//input[@id='Email']","value"),email);
        Assert.assertEquals(getAtributeValue(driver,"//input[@id='Company']","value"),companyName);


    }

    @Test
    public void TC_04_Logout(){

        waitForElementClickable(driver,"//a[@class='ico-logout']");
        clickToElement(driver,"//a[@class='ico-logout']");
        sleepInSeconds(5);
        isControlEnabled(driver,"//a[@class='ico-login']");


    }

    @AfterClass
    public void afterClass(){
         driver.quit();

    }


    private int generateRandomNumber(){
        return  new Random().nextInt(99999);
    }


}
