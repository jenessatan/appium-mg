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
        MovieDetails details = new MovieDetails(driver);
        Assert.assertTrue(listing.isHeaderDisplayed());
        listing.clickMovie(0);
        details.areDetailsDisplayed();
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

    @Test
    public void testFilterSelection() {
        MovieListing listing = new MovieListing(driver);
        Assert.assertTrue(listing.getMovieCount()>0);

        listing.clickFilterButton();
        Assert.assertTrue(listing.isSortMenuOpen());
        Assert.assertTrue(listing.isGivenFilterSelected("popular"));

        listing.selectFilter("favourite");
        Assert.assertFalse(listing.isSortMenuOpen());

        listing.clickFilterButton();
        Assert.assertTrue(listing.isGivenFilterSelected("favourite"));
        listing.selectFilter("newest");

        listing.clickFilterButton();
        Assert.assertTrue(listing.isGivenFilterSelected("newest"));
        listing.selectFilter("highest");

        listing.clickFilterButton();
        Assert.assertTrue(listing.isGivenFilterSelected("highest"));
        listing.selectFilter("popular");

        listing.clickFilterButton();
        Assert.assertTrue(listing.isGivenFilterSelected("popular"));
        driver.navigate().back();
        Assert.assertFalse(listing.isSortMenuOpen());
    }

    @Test
    public void testNewestSort() {
        MovieListing listing = new MovieListing(driver);
        MovieDetails details = new MovieDetails(driver);

        listing.clickFilterButton();
        Assert.assertTrue(listing.isSortMenuOpen());
        listing.selectFilter("newest");

        // get release date of first movie in list
        listing.clickMovie(0);
        Date firstMovieReleaseDate = details.getDate();
        driver.navigate().back();

        scrollDown();
        
        // get next random movie from list
        listing.clickMovie(0);
        Date randomMovieReleaseDate = details.getDate();
        driver.navigate().back();

        scrollDown();

        // get last random movie from list
        listing.clickMovie(0);
        Date lastRandomMovieReleaseDate = details.getDate();
        driver.navigate().back();

        Assert.assertNotNull(firstMovieReleaseDate);
        Assert.assertNotNull(randomMovieReleaseDate);
        Assert.assertNotNull(lastRandomMovieReleaseDate);

        boolean isCorrectlyOrdered = firstMovieReleaseDate.compareTo(randomMovieReleaseDate) < 0
                && randomMovieReleaseDate.compareTo(lastRandomMovieReleaseDate) < 0;

        Assert.assertTrue(isCorrectlyOrdered);
    }

@Test
    public void testHighestSort() {
        MovieListing listing = new MovieListing(driver);
        MovieDetails details = new MovieDetails(driver);

        listing.clickFilterButton();
        Assert.assertTrue(listing.isSortMenuOpen());
        listing.selectFilter("highest");

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        listing.clickMovie(0);
        float firstRating = details.getRating();
        driver.navigate().back();

        scrollDown();

        listing.clickMovie(0);
        float secondRating = details.getRating();
        driver.navigate().back();

        scrollDown();

        listing.clickMovie(0);
        float thirdRating = details.getRating();
        driver.navigate().back();

        Assert.assertNotEquals(-1, firstRating);
        Assert.assertNotEquals(-1, secondRating);
        Assert.assertNotEquals(-1, thirdRating);

        boolean isCorrectlyOrdered = firstRating >= secondRating && secondRating >= thirdRating;

        Assert.assertTrue(isCorrectlyOrdered);
    }

    // Helper function to perform scrolls
    public void scrollDown(){
        Dimension dimension = driver.manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * 0.8);
        int scrollEnd = (int) (dimension.getHeight() * 0.2);

        new TouchAction((PerformsTouchActions) driver)
                .press(PointOption.point(0, scrollStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(0, scrollEnd))
                .release().perform();

        new TouchAction((PerformsTouchActions) driver)
                .press(PointOption.point(0, scrollStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(0, scrollEnd))
                .release().perform();
    }
}
