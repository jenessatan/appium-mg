package tests;

import views.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;
import views.MovieListing;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MovieListingTest {
    private static AndroidDriver<AndroidElement> driver;

    @BeforeSuite
    public static void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.UDID, Utils.DEVICE_ID);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.esoxjem.movieguide");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".listing.MoviesListingActivity");
        capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);

        driver = new AndroidDriver<>(new URL(Utils.APPIUM_SERVER_URL), capabilities);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.resetApp();
    }

    @AfterSuite
    public void tearDown() {
        driver.closeApp();
        driver.quit();
    }

    @Test
    public void testClickFirstMovie() {
        MovieListing listing = new MovieListing(driver);
        Assert.assertTrue(listing.isHeaderDisplayed());
        listing.clickMovie(0);
    }

    @Test
    public void testOpenCloseSearchBar() {
        MovieListing listing = new MovieListing(driver);
        Assert.assertTrue(listing.isHeaderDisplayed());

        listing.openSearchField();
        Assert.assertFalse(listing.isHeaderDisplayed());

        listing.closeSearchField();
        Assert.assertTrue(listing.isHeaderDisplayed());
        Assert.assertFalse(listing.isSearchFieldDisplayed());
    }
    
    @Test
    public void testClearSearchBar() {
        String searchTerm = "Deadpool";
        MovieListing listing = new MovieListing(driver);
        Assert.assertTrue(listing.isHeaderDisplayed());

        listing.openSearchField();
        Assert.assertFalse(listing.isHeaderDisplayed());

        Assert.assertTrue(listing.isSearchFieldDisplayed());
        listing.enterSearchTerms(searchTerm);
        Assert.assertEquals(searchTerm, listing.getSearchFieldContents());

        listing.clearSearchField();
        Assert.assertEquals("", listing.getSearchFieldContents());

        Assert.assertTrue(listing.isSearchFieldDisplayed());
    }

    @Test
    public void testMovieSearch() {
        String movieTitleToSearch = "Hot Fuzz";

        MovieListing listing = new MovieListing(driver);
        Assert.assertTrue(listing.isHeaderDisplayed());

        listing.openSearchField();
        Assert.assertFalse(listing.isHeaderDisplayed());

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        Assert.assertTrue(listing.isSearchFieldDisplayed());
        listing.enterSearchTerms(movieTitleToSearch);

        Assert.assertEquals(1, listing.getMovieCount());
        Assert.assertEquals(movieTitleToSearch, listing.getMovieTitle(0));
    }
}
