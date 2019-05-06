package com.picsart.automation.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * @author Sargis Sargsyan on 2019-02-27
 * @project appium-android-example
 */
public class ShopScreen extends BaseScreen {

    public ShopScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBys({
            @AndroidBy(className = "android.support.v7.app.ActionBar$Tab")
    })
    private List<MobileElement> tabs;

    @Override
    public String getDeepLink() {
        return "picsart://shop";
    }

    public ShopScreen open() {
        openScreen(getDeepLink());
        return new ShopScreen(driver);
    }

    public String getTabName(int index) {
        return  tabs.get(0).findElement(By.className("android.widget.TextView")).getText();
    }
}
