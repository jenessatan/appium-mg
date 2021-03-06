package views;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.NoSuchElementException;

import java.util.List;

public class MovieListing extends PageObject {
    public MovieListing(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.esoxjem.movieguide:id/movie_name']")
    private List<AndroidElement> movieTitles;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='MovieGuide']")
    private AndroidElement header;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.esoxjem.movieguide:id/action_search']")
    private AndroidElement searchButton;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.esoxjem.movieguide:id/search_src_text']")
    private AndroidElement searchField;

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Collapse']")
    private AndroidElement closeSearchField;

    @AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.esoxjem.movieguide:id/search_close_btn']")
    private AndroidElement clearSearchField;

    @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.esoxjem.movieguide:id/action_sort']")
    private AndroidElement filterButton;

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

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.esoxjem.movieguide:id/snackbar_text']")
    private AndroidElement snackBar;

    public void clickMovie(int number) {
        movieTitles.get(number).click();
    }

    public int getMovieCount() {
        return movieTitles.size();
    }

    public String getMovieTitle(int index) {
        return movieTitles.get(index).getText();
    }

    public boolean isHeaderDisplayed() {
        try {
            return header.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void openSearchField() {
        searchButton.click();
    }

    public void clearSearchField() {
        clearSearchField.click();
    }

    public String getSearchFieldContents() {
        return searchField.getText().trim();
    }

    public boolean isSearchFieldDisplayed() {
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

    public boolean isSnackBarDisplayed() {
        try {
            return snackBar.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isSnackBarForEmptyList() {
        String emptyListMessage = "Invalid index 0, size is 0";
        return snackBar.getText().equals(emptyListMessage);
    }
   
    public void clickFilterButton() {
        filterButton.click();
    }

    public boolean isSortMenuOpen() {
        try {
            return sortTitle.isDisplayed() && mostPopularRadioButton.isDisplayed() 
                    && highestRatedRadioButton.isDisplayed() && favouritesRadioButton.isDisplayed() 
                    && newestRadioButton.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
        
    public void selectFilter(String filter) {
        if(filter.equals("popular")) {
            mostPopularRadioButton.click();
        } else if (filter.equals("highest")) {
            highestRatedRadioButton.click();
        } else if(filter.equals("favourite")) {
            favouritesRadioButton.click();
        } else if(filter.equals("newest")) {
            newestRadioButton.click();
        }
    }

    
    public boolean isGivenFilterSelected(String filter) {
        switch (filter) {
            case "popular":
                return mostPopularRadioButton.getAttribute("checked").equalsIgnoreCase("true");
            case "highest":
                return highestRatedRadioButton.getAttribute("checked").equalsIgnoreCase("true");
            case "favourite":
                return favouritesRadioButton.getAttribute("checked").equalsIgnoreCase("true");
            case "newest":
                return newestRadioButton.getAttribute("checked").equalsIgnoreCase("true");
        }
        return false;
    }
}
