package com.nopcommerce.users;

import commons.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Level_02_BasePage_Init {
    private WebDriver driver;
    BasePage basePage;
    private String firstName, middleName, lastName,email,companyName,password;


    @BeforeClass
    public void beforeClass(){
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--user-data-dir=C:/Users/ADMIN/AppData/Local/Google/Chrome/User Data/");
//        chromeOptions.addArguments("--profile-directory=Profile 1");
//        driver = new ChromeDriver(chromeOptions);
//        driver.get("https://demo.nopcommerce.com/");
        driver = new FirefoxDriver();
        basePage = new BasePage();
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
        basePage.waitForElementClickable(driver,"//a[@class='ico-register']");
        basePage.clickToElement(driver,"//a[@class='ico-register']");

        basePage.waitForElementClickable(driver,"//input[@id='gender-female']");
        basePage.clickToElement(driver,"//input[@id='gender-female']");

        basePage.sendkeyToElement(driver,"//input[@id='FirstName']",firstName);
        basePage.sendkeyToElement(driver,"//input[@id='LastName']",lastName);
        basePage.sendkeyToElement(driver,"//input[@id='Email']",email);
        basePage.sendkeyToElement(driver,"//input[@id='Company']",companyName);
        basePage.sendkeyToElement(driver,"//input[@id='Password']",password);
        basePage.sendkeyToElement(driver,"//input[@id='ConfirmPassword']",password);

        basePage.waitForElementClickable(driver,"//button[@id='register-button']");
        basePage.clickToElement(driver,"//button[@id='register-button']");
        basePage.sleepInSeconds(2);
        Assert.assertEquals(basePage.getTextElement(driver,"//div[@class='result']"),"Your registration completed");
        basePage.waitForElementClickable(driver,"//a[@class='ico-logout']");
        basePage.clickToElement(driver,"//a[@class='ico-logout']");
        basePage.sleepInSeconds(5);
    }

    @Test
    public void TC_02_Login(){

        basePage.waitForElementClickable(driver,"//a[@class='ico-login']");
        basePage.clickToElement(driver,"//a[@class='ico-login']");

        basePage.sendkeyToElement(driver,"//input[@id='Email']",email);
        basePage.sendkeyToElement(driver,"//input[@id='Password']",password);
        basePage.clickToElement(driver,"//button[@class='button-1 login-button']");

        Assert.assertTrue(basePage.isControlDisplayed(driver,"//a[@class='ico-account' and text() = 'My account']"));

    }



    @Test
    public void TC_03_MyAccount(){
        basePage.waitForElementClickable(driver,"//a[@class='ico-account']");
        basePage.clickToElement(driver,"//a[@class='ico-account']");

        Assert.assertTrue(basePage.isControlSelected(driver,"//input[@id='gender-female']"));
        Assert.assertEquals(basePage.getAtributeValue(driver,"//input[@id='FirstName']","value"),firstName);
        Assert.assertEquals(basePage.getAtributeValue(driver,"//input[@id='LastName']","value"),lastName);
        Assert.assertEquals(basePage.getAtributeValue(driver,"//input[@id='Email']","value"),email);
        Assert.assertEquals(basePage.getAtributeValue(driver,"//input[@id='Company']","value"),companyName);


    }

    @Test
    public void TC_04_Logout(){

        basePage.waitForElementClickable(driver,"//a[@class='ico-logout']");
        basePage.clickToElement(driver,"//a[@class='ico-logout']");
        basePage.sleepInSeconds(5);
        basePage.isControlEnabled(driver,"//a[@class='ico-login']");



    }

    @AfterClass
    public void afterClass(){
         driver.quit();

    }


    private int generateRandomNumber(){
        return  new Random().nextInt(99999);
    }


}
