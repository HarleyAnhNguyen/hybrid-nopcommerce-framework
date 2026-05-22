package com.nopcommerce.users;

import commons.BasePage;
import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.CustomerInfoPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

import java.time.Duration;
import java.util.Random;

public class Level_03_Page_Object_Pattern extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private LoginPageObject loginPage;
    private CustomerInfoPageObject customerInfoPage;
    private String firstName, middleName, lastName,email,companyName,password;


    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.get("http://localhost:8086/");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        // Page do duoc sinh ra va bat dau lam nhung action cuar page do
        homePage = new HomePageObject(driver);
        firstName = "Bo";
        middleName = "Van";
        lastName = "Bap";
        email="bobapthui" + generateRandomNumber() + "@gmail.com";
        companyName="bobapthui";
        password = "1234567";

    }

    @Test
    public void User_01_Register(){
       //Action 1
       homePage.clickToRegisterLink();

       //Tu HomePage -> RegisterPage
       // Page do duoc sinh ra va bat dau lam nhung action cuar page do
       registerPage = new RegisterPageObject(driver);
       registerPage.clickToGenderRadioButton();
       registerPage.enterToFirstNameTextbox(firstName);
       registerPage.enterToLastNameTextbox(lastName);
       registerPage.enterToEmailTextbox(email);
       registerPage.enterToCompanyTextbox(companyName);
       registerPage.enterToPasswordTextbox(password);
       registerPage.enterToConfirmPasswordTextbox(password);
       registerPage.clickToRegisterButton();

       Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

       registerPage.clickToLogOutButton();
    }

    @Test
    public void User_02_Login(){
        homePage.clickToLoginLink();

        loginPage = new LoginPageObject(driver);
        loginPage.enterToEmailTextbox(email);
        loginPage.enterToPasswordTextbox(password);
        loginPage.clickToLoginButton();

        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }



    @Test
    public void User_03_MyAccount(){
        homePage.clickToMyAccountLink();

        customerInfoPage = new CustomerInfoPageObject(driver);

        Assert.assertTrue(customerInfoPage.isGenderSelected());
        Assert.assertEquals(customerInfoPage.getFirstNameTextboxValue(), firstName);
        Assert.assertEquals(customerInfoPage.getLastNameTextboxValue(), lastName);
        Assert.assertEquals(customerInfoPage.getEmailTextboxValue(), email);
        Assert.assertEquals(customerInfoPage.getCompanyTextboxValue(), companyName);

    }

    @AfterClass
    public void afterClass(){
         driver.quit();

    }


}
