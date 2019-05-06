package com.picsart.automation.tests;

import com.picsart.automation.AppiumBase;
import com.picsart.automation.screens.DiscoverArtistsScreen;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.testng.Assert.assertEquals;

public class DiscoverArtistsTest extends AppiumBase {

    @Test
    public void findContactsOptions() {
        DiscoverArtistsScreen discoverArtistsScreen = new DiscoverArtistsScreen(getDriver()).open();
        assertEquals(discoverArtistsScreen.getFindFacebookFriendsText(), "Find Facebook Friends",
                "Find Facebook Friends was not displayed!");
        assertEquals(discoverArtistsScreen.getFindContactsText(), "Find Contacts",
                "Find Contacts was not displayed!");
    }
}
