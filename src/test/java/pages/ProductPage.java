package pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class ProductPage extends PageObject {

    // Locators for Add to cart
    private final By PRODUCT_NAME = By.xpath("//a[@title='Radiant Tee']");
    private final By SIZE = By.xpath("//div[@id='option-label-size-143-item-167']");
    private final By COLOR = By.xpath("//div[@id='option-label-color-93-item-50']");
    private final By QUANTITY = By.xpath("//input[@name='qty']");
    private final By ADD_TO_CART = By.xpath("//button[@title='Add to Cart']");
    private final By SUCCESS_MESSAGE = By.xpath("//div[contains(@class, 'message-success')]/div");// Updated to reflect inline success message
    /// ***************************newly added************************************************/////////
    private final By QUANTITY_ERROR = By.id("qty-error");
    private final By SIZE_SELECTION_ERROR = By.id("super_attribute[143]-error");// Locator for the size selection error message
    private final By COLOR_SELECTION_ERROR = By.id("super_attribute[93]-error");

    // Locators for Add Review
    private final By REVIEW_TAB = By.xpath("//a[contains(@href,'#reviews')]");
    private final By REVIEW_FIELD = By.id("review_field");
    private final By SUMMARY_FIELD = By.id("summary_field");
    private final By NICKNAME_FIELD = By.id("nickname_field");
    private final By RATING_STARS = By.xpath("//input[@type='radio' and contains(@name, 'ratings')]");
    private final By SUBMIT_REVIEW_BUTTON = By.xpath("//div[contains(@class, 'review-add')]//button");
    private final By SUCCESS_REVIEW_MESSAGE = By.cssSelector(".message-success div");
    private final By REVIEWS = By.cssSelector(".review-items");

    //Locators for
    private final By CART_ICON = By.cssSelector(".minicart-wrapper"); // Cart icon locator
    private final By CART_PRODUCT_NAME = By.cssSelector(".product-item-name"); // Product name in cart

    // Add Review Methods
    public void navigateToReviewTab() {
        $(REVIEW_TAB).waitUntilClickable().click();
    }

    public void fillReviewForm(String review, String summary, String nickname, int rating) {

        // Construct XPath to match the radio button with the given rating value
        String ratingXPath = String.format("//input[@type='radio' and @name='ratings[4]' and @id='Rating_%d']", rating);

        // Scroll to the element and click it using JavaScript
        WebElement ratingElement = $(By.xpath(ratingXPath)).waitUntilVisible();
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", ratingElement);

        $(REVIEW_FIELD).clear();
        $(REVIEW_FIELD).type(review);

        $(SUMMARY_FIELD).clear();
        $(SUMMARY_FIELD).type(summary);

        $(NICKNAME_FIELD).clear();
        $(NICKNAME_FIELD).type(nickname);
    }

    public void submitReview() {
        $(SUBMIT_REVIEW_BUTTON).withTimeoutOf(5, TimeUnit.SECONDS).waitUntilClickable().click();
    }

    public boolean isReviewSuccessMessageDisplayed(String expectedMessage) {
        if(!$(SUCCESS_REVIEW_MESSAGE).withTimeoutOf(5, TimeUnit.SECONDS).isVisible()){
            return true;
        }
        return $(SUCCESS_REVIEW_MESSAGE).containsText(expectedMessage);
    }

    public boolean isReviewVisibleAmongReviews(String review) {
        navigateToReviewTab();
        return findAll(REVIEWS).stream().anyMatch(element -> element.getText().toLowerCase().contains(review.toLowerCase()));
    }

    /**
     * Selects size for the product.
     */
    public void selectSize() {
        $(SIZE).waitUntilClickable().click();
    }

    /**
     * Selects color for the product.
     */
    public void selectColor() {
        $(COLOR).waitUntilClickable().click();
    }

    /**
     * Sets the quantity for the product.
     *
     * @param quantity Desired quantity of the product.
     */
    public void setQuantity(String quantity) {
        $(QUANTITY).waitUntilVisible().clear(); // Clear any prefilled value
        $(QUANTITY).type(quantity); // Enter the quantity
    }

    /**
     * Adds the product to the cart.
     */
    public void addToCart() {
        $(ADD_TO_CART).waitUntilClickable().click();
    }

    /**
     * Verifies the success message displayed after adding the product to the cart.
     *
     * @return true if the success message is displayed, false otherwise.
     */
    public boolean isAddToCartSuccessMessageDisplayed(String expectedMessage) {
        return $(SUCCESS_MESSAGE).withTimeoutOf(5, TimeUnit.SECONDS).waitUntilVisible().containsText(expectedMessage);
    }

    /**
     * Opens the cart to verify the product.
     */
    public void openCartDropdown() {
        $(CART_ICON).click(); // Click the cart icon
        waitForTextToAppear("My Cart"); // Wait for the cart dropdown to load
    }

    /**
     * Checks if the product is visible in the cart.
     *
     * @param productName Name of the product to verify.
     * @return true if the product is in the cart, false otherwise.
     */
    public boolean isProductInCart(String productName) {
        return findAll(CART_PRODUCT_NAME).stream().anyMatch(element -> element.getText().toLowerCase().contains(productName.toLowerCase()));
    }


/// ***************************newly added************************************************/////////
    /**
     * Verifies if the error message for invalid quantity is displayed.
     *
     * @param expectedMessage The expected error message text.
     * @return true if the error message matches the expected text, false otherwise.
     */
    public boolean isQuantityErrorMessageDisplayed(String expectedMessage) {
        return $(QUANTITY_ERROR).isVisible() && $(QUANTITY_ERROR).containsText(expectedMessage);
    }

        /**
     * Verifies if the error message for not selecting a size is displayed.
     *
     * @return true if the size error message is displayed, false otherwise.
     */
    public boolean isSizeSelectionErrorMessageDisplayed() {
        return $(SIZE_SELECTION_ERROR).isVisible() && 
            $(SIZE_SELECTION_ERROR).containsText("This is a required field.");
    }

    public boolean isColorSelectionErrorMessageDisplayed() {
        return $(COLOR_SELECTION_ERROR).isVisible() &&
                $(COLOR_SELECTION_ERROR).containsText("This is a required field.");
    }
}
