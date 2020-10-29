import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.FindBy;

public class MovieListing extends PageObject{
    public MovieListing(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    @AndroidFindBy (id="com.esoxjem.movieguide:id/movie_poster")
    private AndroidElement moviePoster;

    @AndroidFindBy (xpath="//android.widget.TextView[@text='MovieGuide']")
    private AndroidElement header;

    public void clickMoviePoster() {
        moviePoster.click();
    }

    public boolean headerDisplayed() {
        return header.isDisplayed();
    }
}
