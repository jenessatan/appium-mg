import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestPlan {
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

    @Test
    public void clickFirstMovie() {
        MovieListing listing = new MovieListing(driver);
        Assert.assertTrue(listing.headerDisplayed());
        listing.clickMoviePoster();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

//    @AfterMethod
//    public void resetAfterEach() {
//        driver.resetApp();
//    }

    @AfterSuite
    public void tearDown() {
        driver.closeApp();
        driver.quit();
    }
}
