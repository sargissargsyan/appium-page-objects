package com.picsart.automation.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**docker cp /Users/loacl-macosx-path-to-apk/app-debug.apk container-appium:/opt
 * @author Sargis Sargsyan on 2019-02-04
 * @project appium-android-example
 */
public abstract class BaseScreen <T extends LoadableComponent<T>> extends LoadableComponent<T> {


    AppiumDriver driver;
    WebDriverWait webDriverWait;
    TouchAction touchAction;
    public BaseScreen(AppiumDriver driver) {
        try {
            this.driver = driver;
        } catch (Exception ignored) {
            //This will be be thrown when the test starts if it cannot connect to a RemoteWebDriver Instance
        }
        this.webDriverWait = new WebDriverWait(driver, 5, 250);
        this.touchAction = new TouchAction(driver);
        init();
    }


    protected void init() {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(15)), this);
    }

    protected void tapOn(MobileElement mobileElement) {
        try {
            if (mobileElement.isEnabled()) {
                mobileElement.click();
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Unable to locate the Element using: " + mobileElement.toString());
        }
    }

    protected void setValue(MobileElement mobileElement, String setValue) {

        try {
            mobileElement.sendKeys(setValue);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Unable to locate the Element using: " + mobileElement.toString());
        }
    }

    protected void openScreen(String deepLink) {
        driver.get(deepLink);
    }

    public String getCurrentHookUrl() {
        return driver.getCurrentUrl();
    }

    public abstract String getDeepLink();

    public boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean isEnabled(MobileElement mobileElement){
        return mobileElement.isEnabled();
    }

    protected void scrollTo(String text) {
        driver.findElement(MobileBy
                .AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))"));
    }


    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {

    }

}
