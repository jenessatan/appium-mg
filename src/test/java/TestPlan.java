import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


import java.net.MalformedURLException;
import java.net.URL;

public class TestPlan {
    private static AndroidDriver driver;

    @BeforeSuite
    public static void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.UDID, "LGD8524f24eecd");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
        capabilities.setCapability("appPackage", "com.esoxjem.movieguide");
        capabilities.setCapability("appActivity", ".listing.MoviesListingActivity");
        capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);

        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.resetApp();
    }

    @Test
    public void clickFirstMovie() {
        driver.findElement(By.id("com.esoxjem.movieguide:id/movie_poster")).click();
    }

    @AfterMethod
    public void tearDown() {
        driver.closeApp();
    }
}
