package com.picsart.automation.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Sargis Sargsyan on 2019-02-27
 * @project appium-android-example
 */
public class DiscoverArtistsScreen extends BaseScreen {

    public DiscoverArtistsScreen(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.picsart.studio.light:id/find_facebook_friends")
    private MobileElement findFacebookFriends;

    @AndroidFindBy(id = "com.picsart.studio.light:id/find_contacts")
    private MobileElement findContacts;

    @Override
    public String getDeepLink() {
        return "picsart://discoverartists";
    }

    public DiscoverArtistsScreen open() {
        openScreen(getDeepLink());
        return new DiscoverArtistsScreen(driver);
    }

    public String getFindContactsText() {
        return  findContacts.getText();
    }

    public String getFindFacebookFriendsText() {
        return  findFacebookFriends.getText();
    }
}
