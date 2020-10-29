import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.NoSuchElementException;

import java.util.List;

public class MovieListing extends PageObject {
    public MovieListing(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='com.esoxjem.movieguide:id/movie_poster']")
    private List<AndroidElement> moviePosters;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='MovieGuide']")
    private AndroidElement header;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.esoxjem.movieguide:id/action_search']")
    private AndroidElement searchButton;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.esoxjem.movieguide:id/search_src_text']")
    private AndroidElement searchField;

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Collapse']")
    private AndroidElement closeSearchField;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/title']")
    private AndroidElement sortTitle;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@resource-id='com.esoxjem.movieguide:id/most_popular']")
    private AndroidElement mostPopularRadioButton;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@resource-id='com.esoxjem.movieguide:id/highest_rated']")
    private AndroidElement highestRatedRadioButton;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@resource-id='com.esoxjem.movieguide:id/favorites']")
    private AndroidElement favouritesRadioButton;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@resource-id='com.esoxjem.movieguide:id/newest']")
    private AndroidElement newestRadioButton;


    public void clickMoviePoster(int number) {
        moviePosters.get(number).click();
    }

    public boolean headerDisplayed() {
        try {
            return header.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void openSearchField() {
        searchButton.click();
    }

    public boolean searchFieldDisplayed() {
        try {
            return searchField.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void enterSearchTerms(String searchTerm) {
        searchField.sendKeys(searchTerm+"\n");
    }

    public void closeSearchField() {
        if (closeSearchField != null && searchField.isEnabled()) {
            closeSearchField.click();
        }
    }
}
