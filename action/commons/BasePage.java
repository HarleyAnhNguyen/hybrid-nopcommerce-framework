package commons;

import net.bytebuddy.asm.Advice;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {

    public static BasePage getBasePage() {
        return new BasePage();
    }

    public By getXPath(String locator) {
        return By.xpath(locator);
    }
    public WebElement getWebElement(WebDriver driver, String locator) {
        return driver.findElement(getXPath(locator));
    }

    public List<WebElement> getListWebElement(WebDriver driver, String locator){
        return driver.findElements(getXPath(locator));
    }
    public void openPageURL(WebDriver driver,String url){
        driver.get(url);
    }

    public String getTitle(WebDriver driver){
        return driver.getTitle();
    }

    public String getCurrentURL(WebDriver driver){
       return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver){
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver){
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver){
        driver.navigate().forward();
    }

    public void refreshCurrentPage(WebDriver driver){
        driver.navigate().refresh();
    }

    public Alert waitAlertPresence(WebDriver driver){
        return new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.alertIsPresent());

    }

    public void acceptToAlert(WebDriver driver){
        waitAlertPresence(driver).accept();
    }

    public void cancelToAlert(WebDriver driver){
        waitAlertPresence(driver).dismiss();
    }

    public String getTextToAlert(WebDriver driver){
        return waitAlertPresence(driver).getText();
    }

    public void sendkeyToAlert(WebDriver driver, String valueAlertSendKey){
        waitAlertPresence(driver).sendKeys(valueAlertSendKey);
    }

    public void switchToWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            driver.switchTo().window(runWindows);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                break;
            }
        }
    }

    public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            if (!runWindows.equals(parentID)) {
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    public void clickToElement(WebDriver driver, String locator) {
       getWebElement(driver,locator).click();
    }

    public void sendkeyToElement(WebDriver driver, String locator, String value){
        getWebElement(driver,locator).sendKeys(value);
    }

    public void selectItemInDropDown(WebDriver driver, String locator, String valueItem){
       new Select(getWebElement(driver,locator)).selectByVisibleText(valueItem);
    }

    public String getSelectedItemInDropDown(WebDriver driver, String locator){
        return new Select(getWebElement(driver,locator))
                .getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator){
        return new Select(getWebElement(driver,locator)).isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
        getWebElement(driver,parentLocator).click();
        sleepInSeconds(2);

        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childItemLocator)));

        sleepInSeconds(2);
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                item.click();
                break;
            }
        }
    }

    public String getAtributeValue(WebDriver driver, String locator, String attributeName){
        return getWebElement(driver,locator).getAttribute(attributeName);
    }

    public String getTextElement(WebDriver driver, String locator){
        return getWebElement(driver,locator).getText();
    }

    public String getElementCssValue(WebDriver driver, String locator, String propertyName){
        return getWebElement(driver,locator).getCssValue(propertyName);
    }

    public String getHexaColorFromRGBA(WebDriver driver, String locator, String propertyName) {
        return Color.fromString(getElementCssValue(driver, locator, propertyName)).asHex().toUpperCase();
    }

    public  void getListElementNumber(WebDriver driver, String locator){
       getListWebElement(driver,locator).size();
    }

    public boolean isControlDisplayed(WebDriver driver, String locator){
        return getWebElement(driver,locator).isDisplayed();
    }

    public boolean isControlEnabled(WebDriver driver, String locator){
        return getWebElement(driver,locator).isEnabled();
    }

    public boolean isControlSelected(WebDriver driver, String locator){
        return getWebElement(driver,locator).isSelected();
    }

    public void checkToCheckboxOrRadio(WebDriver driver, String locator){
        if(!getWebElement(driver,locator).isSelected()){
            getWebElement(driver,locator).click();
        }
    }

    public void uncheckToCheckboxOrRadio(WebDriver driver, String locator){
        if(getWebElement(driver,locator).isSelected()){
            getWebElement(driver,locator).click();
        }
    }

    public void switchToIFarm(WebDriver driver, String locator){
        driver.switchTo().frame(getWebElement(driver,locator));
    }

    public void switchToFarm(WebDriver driver, String nameOrID){
        driver.switchTo().frame(nameOrID);
    }

    public void switchToDefaultContent(WebDriver driver){
        driver.switchTo().defaultContent();
    }
    //Code tu viet chua sua
    public void leftClickToElement(WebDriver driver, String locator){
        new Actions(driver).moveToElement(getWebElement(driver,locator)).perform();
    }

    public void doubleClickToElement(WebDriver driver, String locator){
        new Actions(driver).doubleClick(getWebElement(driver,locator)).perform();
    }

    public void hoverMouseToElement(WebDriver driver, String locatorFirst, String locatorLast){
        new Actions(driver).clickAndHold(getWebElement(driver,locatorFirst))
                .moveToElement(getWebElement(driver,locatorLast))
                .release()
                .perform();
    }

    public void rightClickToElement(WebDriver driver, String locator){
        new Actions(driver).contextClick(getWebElement(driver,locator)).perform();
    }

    public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator){
        new Actions(driver).dragAndDrop(getWebElement(driver,sourceLocator),getWebElement(driver,targetLocator)).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys key){
        new Actions(driver).sendKeys(getWebElement(driver,locator),key).perform();
    }

    public void scrollToElementAction(WebDriver driver, String locator){
        new Actions(driver).scrollToElement(getWebElement(driver, locator)).perform();
    }

    public  void sendKeyboardToElement(WebDriver driver, String locator, Keys key){
        new Actions(driver).sendKeys(getWebElement(driver,locator),key).perform();
    }

    public  void uploadFileSendkey(WebDriver driver, String locator, String filePath) {
        getWebElement(driver, locator).sendKeys(filePath);
    }

    public void uploadRobot(WebDriver driver, String filePath) {

    }

    public void uploadAutoIT(WebDriver driver, String filePath) {

    }

    public  void jsExecutorToBrowser(WebDriver driver, String script) {
        ((JavascriptExecutor) driver).executeScript(script);
    }

    public Object jsExecutorToElement(WebDriver driver, String locator, String script) {
        return ((JavascriptExecutor) driver).executeScript(script, getWebElement(driver, locator));
    }

    public void jsScrollToBottomPage(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void scrollElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
    }

    public void hightlightElement(WebDriver driver, String locator) {
        String originalStyle = getWebElement(driver,locator).getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", getWebElement(driver,locator), "border: 2px solid red; border-style: dashed;");
        sleepInSeconds(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", getWebElement(driver,locator), getWebElement(driver,locator).getAttribute("style"));
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
        sleepInSeconds(3);
    }

    public void scrollToElementOnTopByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
    }

    public void scrollToElementOnDownByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
    }

    public void scrollToBottomPageByJS(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void setAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", getWebElement(driver, locator));
    }

    public void removeAttributeInDOMByJS(WebDriver driver, String locator, String attributeRemove) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
    }

    public String getAttributeInDOMByJS(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getWebElement(driver, locator));
    }

    public String getElementValidationMessageByJS(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    public boolean isImageLoadedByJS(WebDriver driver, String locator) {
        return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete " +
                        "&& typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                getWebElement(driver, locator));
    }

    public void waitForElementVisible(WebDriver driver, String locator) {
         new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(getXPath(locator)));
    }

    public WebElement waitForListElementVisible(WebDriver driver, List<String> locators) {
        for (String locator : locators) {
            return new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOfElementLocated(getXPath(locator)));
        }
        return null;
    }

    public void waitForElementInvisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.invisibilityOfElementLocated(getXPath(locator)));
    }

    public WebElement waitForListElementInvisible(WebDriver driver, List<String> locators){
        for (String locator : locators){
            return new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOfElementLocated(getXPath(locator)));
        }
        return null;
    }

    public void waitForElementPresence(WebDriver driver, String locator) {
         new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated(getXPath(locator)));
    }

    public WebElement waitForListElementPresence(WebDriver driver, List<String> locators) {
        for (String locator : locators) {
            return new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.presenceOfElementLocated(getXPath(locator)));
        }
        return null;
    }

    public void waitForElementSelected(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeSelected(getXPath(locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(getXPath(locator)));
    }

    public void waitForAlertPresence(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.alertIsPresent());
    }

    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
