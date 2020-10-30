package views;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

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



}
