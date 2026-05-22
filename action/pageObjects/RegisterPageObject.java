package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage {
    private WebDriver driver;
    public RegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToGenderRadioButton() {
        waitForElementClickable(driver, RegisterPageUI.GENDER_FEMALE_RADIO);
        checkToCheckboxOrRadio(driver,RegisterPageUI.GENDER_FEMALE_RADIO);
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, RegisterPageUI.FIRST_NAME_TEXTBOX);
        sendkeyToElement(driver, RegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
    }

    public void enterToLastNameTextbox(String lastName) {
        waitForElementVisible(driver, RegisterPageUI.LAST_NAME_TEXTBOX);
        sendkeyToElement(driver, RegisterPageUI.LAST_NAME_TEXTBOX, lastName);
    }

    public void enterToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, emailAddress);
    }

    public void enterToCompanyTextbox(String companyName) {
        waitForElementVisible(driver, RegisterPageUI.COMPANY_TEXTBOX);
        sendkeyToElement(driver, RegisterPageUI.COMPANY_TEXTBOX, companyName);
    }

    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);
    }

    public void enterToConfirmPasswordTextbox(String confirmPassword) {
        waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendkeyToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);
    }

    public void clickToRegisterButton() {
        waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
        clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
    }

    public String getRegisterSuccessMessage() {
        waitForElementVisible(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
        return getTextElement(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
    }

    public void clickToLogOutButton() {
        waitForElementClickable(driver, RegisterPageUI.LOGOUT_BUTTON);
        clickToElement(driver, RegisterPageUI.LOGOUT_BUTTON);
    }
}
