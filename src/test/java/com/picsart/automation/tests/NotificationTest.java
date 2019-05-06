package com.picsart.automation.tests;

import com.picsart.automation.AppiumBase;
import com.picsart.automation.screens.NotificationScreen;
import org.testng.annotations.Test;

/**
 * @author Sargis Sargsyan on 2019-05-02
 * @project appium-page-objects
 */
public class NotificationTest extends AppiumBase {
    @Test
    public void network() {
        NotificationScreen notificationScreen = new NotificationScreen(getDriver()).open();

    }

}
