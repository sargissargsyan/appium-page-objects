package com.picsart.automation.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * @author Sargis Sargsyan on 2019-02-04
 * @project appium-android-example
 */
public class HomeScreen extends BaseScreen {
    @AndroidFindBy(id = "space_icon")
    private MobileElement spaceIcon;


    public HomeScreen(AppiumDriver driver) {
        super(driver);
    }

    public String getDeepLink() {
        return null;
    }

    public void clickSpaveIcon() {
        spaceIcon.click();
    }
}
