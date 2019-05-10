package com.picsart.automation.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

/**
 * @author Sargis Sargsyan on 2019-02-22
 * @project appium-android-example
 */
public class LoginScreen extends BaseScreen {
    public LoginScreen(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    @AndroidFindBy(id = "com.picsart.studio.light:id/on_boarding_sign_in_username")
    private MobileElement usernameField;

    @AndroidFindBy(id = "com.picsart.studio.light:id/on_boarding_sign_in_password")
    private MobileElement passwordField;

    @AndroidFindBy(id = "com.picsart.studio.light:id/on_boarding_register_button")
    private MobileElement registerButton;

    @AndroidFindBy(id = "com.picsart.studio.light:id/on_boarding_already_have_an_account_container")
    private MobileElement signInLink;

    @AndroidFindBy(id = "com.picsart.studio.light:id/google_button")
    private MobileElement googleButton;

    @AndroidFindBy(id = "com.picsart.studio.light:id/container")
    private MobileElement faceookButton;

    public String getDeepLink() {
        return "https://picsart.com/sign-in";
    }

    public LoginScreen open() {
        openScreen(getDeepLink());
        return new LoginScreen(driver);
    }

    public LoginScreen openScreen() {
        return new LoginScreen(driver);
    }

    public boolean isUsernameEnabled() {
        return usernameField.isEnabled();
    }

    public boolean isPasswordEnabled() {
        return passwordField.isEnabled();
    }

    public boolean isSignInEnabled() {
        return registerButton.isEnabled();
    }

    public boolean isGoogleButtonVisible() {
        return googleButton.isDisplayed();
    }
    public boolean isFacebookButtonVisible() {
        return faceookButton.isDisplayed();
    }

    public void login(String username, String password) {
        driver.findElement(By.id("com.picsart.studio.light:id/on_boarding_sign_in_username")).sendKeys(username);
        driver.findElement(By.id("com.picsart.studio.light:id/on_boarding_sign_in_password")).sendKeys(password);
        setValue(usernameField, username);
        setValue(passwordField, password);
        registerButton.click();
    }

}
