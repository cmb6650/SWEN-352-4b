package edu.rit.swen253.test.HoursAndLocations;

import edu.rit.swen253.page.tiger.HoursAndLocationsPage;
import edu.rit.swen253.test.AbstractWebTest;
import edu.rit.swen253.utils.SeleniumUtils;
import edu.rit.swen253.page.tiger.FilterViewObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterLocationsTest extends AbstractWebTest {

    private WebDriver driver;
    private HoursAndLocationsPage hoursAndLocationsPage;
    private FilterViewObject filterView;

    @BeforeEach
    public void setUp() {
        // Set up the WebDriver
        SeleniumUtils.setupDriver();
        driver = SeleniumUtils.getDriver();

        hoursAndLocationsPage = new HoursAndLocationsPage(driver);
        filterView = new FilterViewObject(driver);
        hoursAndLocationsPage.navigateTo();
    }

    @Test
    public void testFilterByCafesAndShops() {
        // Locate the Cafes and Shops checkbox label
        WebElement cafesAndShopsLabel = driver.findElement(By.xpath("//mat-checkbox[@id='mat-checkbox-4']/label"));

        // Scroll to the label and wait for it to be clickable
        SeleniumUtils.scrollToElement(cafesAndShopsLabel);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(cafesAndShopsLabel));

        // Use JavaScript to click the label if standard click fails
        SeleniumUtils.clickElementUsingJs(cafesAndShopsLabel);

        // Wait for the results to update
        wait.until(ExpectedConditions.visibilityOfElementLocated(hoursAndLocationsPage.getLocationsListLocator()));

        // Verify the Cafes and Shops checkbox is checked
        WebElement cafesAndShopsCheckbox = driver.findElement(By.id("mat-checkbox-4-input"));
        assertTrue(cafesAndShopsCheckbox.getAttribute("aria-checked").equals("true"),
                "The Cafes and Shops checkbox should be checked.");

        // Verify the Bytes Micro Market location is displayed
        List<WebElement> filteredLocations = hoursAndLocationsPage.getDisplayedLocations();
        boolean locationFound = filteredLocations.stream()
                .anyMatch(location -> location.getText().contains("Bytes Micro Market"));

        assertTrue(locationFound, "Bytes Micro Market should be displayed after applying the Cafes and Shops filter.");

        // Uncheck the Cafes and Shops checkbox
        filterView.uncheckFilter(FilterViewObject.FilterType.CAFES_AND_SHOPS);

        // Wait for the results to update
        wait.until(ExpectedConditions.visibilityOfElementLocated(hoursAndLocationsPage.getLocationsListLocator()));

        // Verify all locations are displayed again
        List<WebElement> allLocations = hoursAndLocationsPage.getDisplayedLocations();
        assertFalse(allLocations.isEmpty(), "Locations list should not be empty");
    }

}
