package com.nopcommerce.users;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Level_01_Repeat_Yourself {
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
        driver.get("https://live.techpanda.org/index.php/");

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
        driver.findElement(By.xpath("//span[text() = 'Account']/parent::a")).click();
        driver.findElement(By.xpath("//a[text() = 'Register']")).click();

//Nopcommerce page
//        driver.findElement(By.cssSelector("input#gender-female")).click();
//        driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
//        driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);
//        driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
//        driver.findElement(By.cssSelector("input#Company")).sendKeys(companyName);
//        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
//        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(password);
//        driver.findElement(By.cssSelector("button#register-button")).click();
        //techpanda page
        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#middlename")).sendKeys(middleName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(email);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);
        driver.findElement(By.cssSelector("input#is_subscribed")).click();
        driver.findElement(By.xpath("//button[@title='Register']")).click();
        Thread.sleep(5000);



    }


//    public void TC_02_Login(){
//
//
//        driver.findElement(By.cssSelector("a.ico-login")).click();
//
//        driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
//        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
//        driver.findElement(By.cssSelector("button.login-button")).click();
//
//        Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ico-account' and text() = 'My account']")).isDisplayed());
//
//    }

    @Test
    public void TC_04_Login() throws InterruptedException {



        driver.findElement(By.xpath("//span[text() = 'Account']/parent::a")).click();
        driver.findElement(By.xpath("//a[text() = 'Log In']")).click();
        driver.findElement(By.cssSelector("input#email")).sendKeys(email);
        driver.findElement(By.cssSelector("input#pass")).sendKeys(password);
        driver.findElement(By.xpath("//button[@title='Login']")).click();
        Thread.sleep(5000);
        Assert.assertEquals(driver.findElement(By.cssSelector("p.welcome-msg")).getText(),"WELCOME, "+firstName.toUpperCase()+" "+middleName.toUpperCase()+" "+lastName.toUpperCase()+"!");



    }

    @Test
    public void TC_02_MyAccount(){


        driver.findElement(By.xpath("//span[text() = 'Account']/parent::a")).click();
        driver.findElement(By.xpath("//a[text() = 'My Account']")).click();
        driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/a")).click();

//        Assert.assertTrue(driver.findElement(By.cssSelector("input#gender-female")).isSelected());
//        Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"),firstName);
//        Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"),lastName);
//        Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("value"),email);
//        Assert.assertEquals(driver.findElement(By.cssSelector("input#Company")).getAttribute("value"),companyName);


        Assert.assertEquals(driver.findElement(By.cssSelector("input#firstname")).getAttribute("value"),firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#middlename")).getAttribute("value"),middleName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#lastname")).getAttribute("value"),lastName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#email")).getAttribute("value"),email);

    }

    @Test
    public void TC_03_Logout(){


        driver.findElement(By.xpath("//span[text() = 'Account']/parent::a")).click();
        driver.findElement(By.xpath("//a[text() = 'Log Out']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title h1")).getText(),"YOU ARE NOW LOGGED OUT");



    }

    @AfterClass
    public void afterClass(){
         driver.quit();

    }


    private int generateRandomNumber(){
        return  new Random().nextInt(99999);
    }


}
