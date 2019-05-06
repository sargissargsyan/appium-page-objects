package com.picsart.automation.config;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.log4j.Logger;

import java.net.URL;

public class AppiumServerHelper {
    private final static Logger LOGGER = Logger.getLogger(AppiumServerHelper.class);

    private AppiumServerHelper() {}

    public static URL appiumUrl;
    private static AppiumDriverLocalService service;

    public static void startServer() {
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1");
        builder.usingAnyFreePort();
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.RELAXED_SECURITY);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL,"info");
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
        appiumUrl = service.getUrl();
        LOGGER.info("Appium server is running on " + appiumUrl);
    }

    public static void stopServer() {
        if (service != null && service.isRunning()) {
            service.stop();
        }
    }

}
