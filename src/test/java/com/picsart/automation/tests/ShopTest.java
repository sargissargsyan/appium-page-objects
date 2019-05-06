package com.picsart.automation.tests;

import com.picsart.automation.AppiumBase;
import com.picsart.automation.screens.DiscoverArtistsScreen;
import com.picsart.automation.screens.ShopScreen;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ShopTest extends AppiumBase {

    @Test
    public void tabs() {
        ShopScreen shopScreen = new ShopScreen(getDriver()).open();

        assertEquals("HOT", shopScreen.getTabName(0), "Tab name was not correct!");
        assertEquals("FAVORITES", shopScreen.getTabName(1), "Tab name was not correct!");
        assertEquals("MINE", shopScreen.getTabName(2), "Tab name was not correct!");
    }
}
