package pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NavigationLinksPage extends PageObject {

    private final By NAVIGATION_LINKS = By.xpath("//main[@class='page-main']//div[@class='columns']//a");
    private final By ERROR_404 = By.xpath("//*[contains(text(), '404 Not Found')]");
    private final By INVALID_PAGE = By.cssSelector(".neterror");

    private final List<String> invalidPages = new ArrayList<>();
    private final List<String> pagesWith404Error = new ArrayList<>();
    private static String pageName;

    public void navigateToPage(String pageName) {
        this.pageName = pageName;
        openAt("/" + pageName.toLowerCase().replace(" ", "-") + ".html");
    }

    public void clickEachNavigationLink() {
        List<WebElement> links = getDriver().findElements(NAVIGATION_LINKS);

        for (int i = 0; i < links.size(); i++) {
            // Refetch the links to avoid stale references
            links = getDriver().findElements(NAVIGATION_LINKS);
            WebElement link = links.get(i);

            try {
                String href = link.getAttribute("href");

                if (href == null || href.isEmpty()) {
                    System.out.println("Attempting to click link without href: " + link.getText());
                    continue;
                }

                // Validate the link with an HTTP request
                if (!isValidLink(href)) {
                    System.out.println("Broken link found: " + href);
                    invalidPages.add(href); // Add to invalid pages list
                    continue;
                }

                // Navigate to the link
                getDriver().navigate().to(href);

                // Check if the page is invalid
                if (!findAll(INVALID_PAGE).isEmpty()) {
                    System.err.println("Invalid page loaded for link: " + href);
                    invalidPages.add(href); // Add to invalid pages list
                }

                // Check for 404 error
                if (!isNo404ErrorPresent()) {
                    System.out.println("404 Page Not Found error detected: " + href);
                    pagesWith404Error.add(href); // Add to 404 error pages list
                }

                // Navigate back to the main page
                getDriver().navigate().to("https://magento.softwaretestingboard.com/" + pageName.toLowerCase().replace(" ", "-") + ".html");
            } catch (StaleElementReferenceException e) {
                System.err.println("Stale element reference encountered. Retrying...");
            } catch (Exception e) {
                System.err.println("Error navigating to link: " + getDriver().getCurrentUrl());
            }
        }
    }

    public boolean isNo404ErrorPresent() {
        // Check if a "404 Page Not Found" message exists on the page
        return findAll(ERROR_404).isEmpty();
    }

    public boolean isNoInvalidPagePresent() {
        // Check if an invalid page (e.g., .neterror) is displayed
        return findAll(INVALID_PAGE).isEmpty();
    }

    // Getter for Invalid Pages
    public boolean areThereInvalidPages() {
        return invalidPages.isEmpty();
    }

    // Getter for 404 Error Pages
    public boolean areTherePagesWith404Error() {
        return pagesWith404Error.isEmpty();
    }

    public boolean areAllTagsHaveHref() {
        List<WebElement> links = getDriver().findElements(NAVIGATION_LINKS);

        for (WebElement link : links) {
            String href = link.getAttribute("href");
            if (href == null || href.isEmpty()) {
                System.out.println("<a> tag without href found: " + link.getText());
                return false;
            }
        }

        return true;
    }

    // Helper method to check if a URL is valid
    private boolean isValidLink(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();
            int responseCode = connection.getResponseCode();
            return responseCode >= 200 && responseCode < 400;
        } catch (Exception e) {
            return false;
        }
    }
}
