package com.picsart.automation.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Sargis Sargsyan on 2019-05-02
 * @project appium-page-objects
 */
public class NotificationScreen extends BaseScreen {

    public NotificationScreen(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.picsart.studio.light:id/find_facebook_friends")
    private MobileElement findFacebookFriends;

    @AndroidFindBy(id = "com.picsart.studio.light:id/find_contacts")
    private MobileElement findContacts;

    @Override
    public String getDeepLink() {
        return "picsart://notifications";
    }

    public NotificationScreen open() {
        openScreen(getDeepLink());
        return new NotificationScreen(driver);
    }
}
