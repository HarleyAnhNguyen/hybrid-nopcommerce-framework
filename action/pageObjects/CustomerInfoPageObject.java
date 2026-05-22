package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.CustomerInfoPageUI;

public class CustomerInfoPageObject extends BasePage {
    private WebDriver driver;
    public CustomerInfoPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isGenderSelected() {
        waitForElementSelected(driver,CustomerInfoPageUI.GENDER_FEMALE_RADIO);
        return isControlSelected(driver, CustomerInfoPageUI.GENDER_FEMALE_RADIO);
    }

    public String getFirstNameTextboxValue() {
        waitForElementVisible(driver, CustomerInfoPageUI.FIRST_NAME_TEXTBOX);
        return getAtributeValue(driver, CustomerInfoPageUI.FIRST_NAME_TEXTBOX, "value");
    }

    public String getLastNameTextboxValue() {
        waitForElementVisible(driver, CustomerInfoPageUI.LAST_NAME_TEXTBOX);
        return getAtributeValue(driver, CustomerInfoPageUI.LAST_NAME_TEXTBOX, "value");
    }

    public String getEmailTextboxValue() {
        waitForElementVisible(driver, CustomerInfoPageUI.EMAIL_TEXTBOX);
        return getAtributeValue(driver, CustomerInfoPageUI.EMAIL_TEXTBOX, "value");
    }

    public String getCompanyTextboxValue() {
        waitForElementVisible(driver, CustomerInfoPageUI.COMPANY_TEXTBOX);
        return getAtributeValue(driver, CustomerInfoPageUI.COMPANY_TEXTBOX, "value");
    }
}
