package edu.rit.swen253.test.HoursAndLocations;

import edu.rit.swen253.page.tiger.DetailsViewObject;
import edu.rit.swen253.test.AbstractWebTest;
import edu.rit.swen253.utils.SeleniumUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DetailsViewTest extends AbstractWebTest {

    private WebDriver driver;
    private DetailsViewObject detailsView;

    @BeforeEach
    public void setUp() {
        setUpSeleniumDriver();
        driver = SeleniumUtils.getDriver();
        detailsView = new DetailsViewObject(driver);
        driver.get("https://tigercenter.rit.edu/tigerCenterApp/api/hours-and-locations"); // Navigate to the Hours and Locations page
    }

    @Test
    @Order(1)
    @DisplayName("View details of a location and check hours")
    public void testViewDetails() {
        // Click on a specific location
        detailsView.clickLocation("Artesano Bakery & Cafe");

        // Verify that the advanced view shows a no hours message
        assertTrue(detailsView.isNoHoursMessageDisplayed(), "No hours message should be displayed.");
    }
}
