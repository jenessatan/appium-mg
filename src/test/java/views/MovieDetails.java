package views;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

public class MovieDetails extends PageObject{
    public MovieDetails(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    @AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='Navigate up']")
    private AndroidElement backButton;

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@resource-id='com.esoxjem.movieguide:id/favorite']")
    private AndroidElement favouriteButton;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='android.widget.ImageView']")
    private AndroidElement bannerImage;

    @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.esoxjem.movieguide:id/movie_name']")
    private AndroidElement movieTitle;

    @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.esoxjem.movieguide:id/movie_year']")
    private AndroidElement releaseDate;

    @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.esoxjem.movieguide:id/movie_rating']")
    private AndroidElement rating;

    @AndroidFindBy(xpath="//android.widget.TextView[@text='Summary']")
    private AndroidElement summaryLabel;

    @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.esoxjem.movieguide:id/movie_description']")
    private AndroidElement movieDescription;

    @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.esoxjem.movieguide:id/trailers_label']")
    private AndroidElement trailerLabel;

    public Date getDate() {
        String releaseDateString = releaseDate.getText().split(":")[1];
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(releaseDateString);
        }catch(ParseException e) {
            return null;
        }
    }
    
    public float getRating() {
        String ratingString = rating.getText().split("/")[0];
        try {
            return Float.parseFloat(ratingString);
        } catch(NumberFormatException e) {
            return -1;
        }
    }

    public void clickBackButton() {
        backButton.click();
    }

    public boolean isFavouriteButtonDisplayed() {
        try {
            return favouriteButton.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickFavouriteButton() {
        favouriteButton.click();
    }

    public String getMovieTitle() {
        return movieTitle.getText().trim();
    }

    public boolean areDetailsDisplayed() {
        try {
            return summaryLabel.isDisplayed() && movieDescription.isDisplayed()
                    && releaseDate.isDisplayed() && rating.isDisplayed();
        } catch(NoSuchElementException e) {
            return false;
        }
    }
}
