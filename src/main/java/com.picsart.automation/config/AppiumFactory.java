package com.picsart.automation.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import static com.picsart.automation.config.AppiumDriverType.ANDROID;

public class AppiumFactory {
    private final static Logger LOGGER = Logger.getLogger(AppiumDriverType.class);


    private AppiumDriver driver;
    private AppiumDriverType selectedDriverConfiguration;

    private static final boolean USE_SELENIUM_GRID = Boolean.getBoolean("useSeleniumGrid");
    private static final URL DEFAULT_SERVER_LOCATION = AppiumServerHelper.appiumUrl;
    private static String APPIUM_SERVER_LOCATION = DEFAULT_SERVER_LOCATION.getPath();

    public AppiumFactory() {
        AppiumDriverType driverType = ANDROID;
        String appiumConfig = System.getProperty("appiumConfig", driverType.toString()).toUpperCase();
        try {
            driverType = AppiumDriverType.valueOf(appiumConfig);
        } catch (IllegalArgumentException ignored) {
            LOGGER.error("Unknown driver specified, defaulting to '" + driverType + "'...");
        } catch (NullPointerException ignored) {
            LOGGER.error("No driver specified, defaulting to '" + driverType + "'...");
        }
        selectedDriverConfiguration = driverType;
    }

    public AppiumDriver getDriver() {
        if (driver == null) {
            instantiateWebDriver(selectedDriverConfiguration);
        }
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private void instantiateWebDriver(AppiumDriverType appiumDriverType) {
        LOGGER.info("Current Appium Config Selection: " + selectedDriverConfiguration);
        LOGGER.info("Current Appium Server Location: " + APPIUM_SERVER_LOCATION);
        LOGGER.info("Connecting to Selenium Grid: " + USE_SELENIUM_GRID);

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        Optional.ofNullable(System.getProperty("device_id", null))
                .ifPresent(deviceID -> desiredCapabilities.setCapability(MobileCapabilityType.UDID, deviceID));
        if (USE_SELENIUM_GRID) {
            URL seleniumGridURL = null;
            try {
                seleniumGridURL = new URL(System.getProperty("gridURL"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            String desiredVersion = System.getProperty("desiredVersion");
            String desiredPlatform = System.getProperty("desiredPlatform");

            if (null != desiredPlatform && !desiredPlatform.isEmpty()) {
                desiredCapabilities.setPlatform(Platform.valueOf(desiredPlatform.toUpperCase()));
            }

            if (null != desiredVersion && !desiredVersion.isEmpty()) {
                desiredCapabilities.setVersion(desiredVersion);
            }

            desiredCapabilities.setBrowserName(selectedDriverConfiguration.toString());
            driver = new AppiumDriver(seleniumGridURL, desiredCapabilities);
        } else {
            driver = appiumDriverType.createAppiumObject(DEFAULT_SERVER_LOCATION, desiredCapabilities).getAppiumDriver();
        }
    }
}
