package com.picsart.automation;

import com.picsart.automation.config.AppiumFactory;
import com.picsart.automation.config.AppiumServerHelper;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppiumBase {

    private static List<AppiumFactory> webDriverThreadPool = Collections.synchronizedList(new ArrayList<AppiumFactory>());
    private static ThreadLocal<AppiumFactory> appiumFactory;
    protected AppiumDriver driver;

    @BeforeSuite
    public void setUpTest() {
        AppiumServerHelper.startServer();
    }

    @BeforeTest
    public static void instantiateDriverObject() {
        appiumFactory = ThreadLocal.withInitial(() -> {
            AppiumFactory appiumFactory = new AppiumFactory();
            webDriverThreadPool.add(appiumFactory);
            return appiumFactory;
        });
    }

    @BeforeMethod
    public void setupBaseMethod() {
        driver = getDriver();
    }

    public static AppiumDriver getDriver() {
        return appiumFactory.get().getDriver();
    }

    @AfterTest(alwaysRun = true)
    public static void closeDriverObjects() {
        for (AppiumFactory appiumFactory : webDriverThreadPool) {
            appiumFactory.quitDriver();
        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownTest() {
        AppiumServerHelper.stopServer();
    }
}
