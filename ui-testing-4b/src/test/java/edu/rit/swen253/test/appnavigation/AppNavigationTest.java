package edu.rit.swen253.test.appnavigation;

import edu.rit.swen253.page.tiger.HeaderViewObject;
import edu.rit.swen253.page.tiger.FooterViewObject;
import edu.rit.swen253.page.tiger.SidenavViewObject;
import edu.rit.swen253.test.AbstractWebTest;
import edu.rit.swen253.utils.SeleniumUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.WebDriver;

import java.util.Set;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppNavigationTest extends AbstractWebTest {

    private static final Logger log = Logger.getLogger(AppNavigationTest.class.getName());

    private WebDriver driver;
    private String homePageUrl = "https://tigercenter.rit.edu";
    private String classSearchUrl = "https://tigercenter.rit.edu/tigerCenterApp/api/class-search";
    private String homePageHandle;

    @BeforeAll
    public void setUp() {
        setUpSeleniumDriver();
        driver = SeleniumUtils.getDriver();
    }
//    @Disabled
    @Test
    @Order(1)
    @DisplayName("Navigate to the Home page.")
    public void navigateToHomePage() {
        driver.get(homePageUrl);
        Set<String> windowHandles = driver.getWindowHandles();
        homePageHandle = windowHandles.iterator().next();
        log.info(String.format("Navigated to the Home page: %s", homePageHandle));
    }
//    @Disabled
    @ParameterizedTest
    @EnumSource(HeaderViewObject.Link.class)
    @Order(2)
    @DisplayName("Test header navigation.")
    public void testHeaderNavigation(HeaderViewObject.Link link) {
        driver.get(homePageUrl);
        String originalHandle = driver.getWindowHandle();
        HeaderViewObject header = new HeaderViewObject(driver);
        header.navigateTo(link);

        handleNavigationAndAssert(originalHandle, getExpectedUrlForHeaderLink(link));
    }
//    @Disabled
    @ParameterizedTest
    @EnumSource(FooterViewObject.Link.class)
    @Order(3)
    @DisplayName("Test footer navigation.")
    public void testFooterNavigation(FooterViewObject.Link link) {
        driver.get(homePageUrl);
        String originalHandle = driver.getWindowHandle();
        FooterViewObject footer = new FooterViewObject(driver);
        footer.navigateTo(link);

        handleNavigationAndAssert(originalHandle, getExpectedUrlForFooterLink(link));
    }

//    @Disabled
    @ParameterizedTest
    @EnumSource(SidenavViewObject.Link.class)
    @Order(4)
    @DisplayName("Test sidenav navigation.")
    public void testSidenavNavigation(SidenavViewObject.Link link) throws InterruptedException {
        navigateToClassSearchPage(); // Ensure we are on the Class Search page before each test

        SidenavViewObject sidenav = new SidenavViewObject(driver);
        sidenav.navigateTo(link);

        // Wait for 3 seconds before proceeding to validate the URL
        Thread.sleep(3000);

        // Get the current window handles
        Set<String> windowHandles = driver.getWindowHandles();

        // Handle cases where a new tab might be opened
        if (windowHandles.size() > 1) {
            // Find the new tab handle
            final String newTabHandle = windowHandles.stream()
                    .filter(handle -> !handle.equals(homePageHandle))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Could not find valid window handle for the new tab."));

            // Switch to the new tab
            driver.switchTo().window(newTabHandle);

            // Validate the URL in the new tab
            String expectedUrl = getExpectedUrlForSidenavLink(link);
            assertEquals(expectedUrl, driver.getCurrentUrl(), "Navigation failed for sidenav link: " + link.name());

            // Close the new tab and switch back to the original tab
            driver.close();
            driver.switchTo().window(homePageHandle);
        } else {
            // Validate the URL in the current tab
            String expectedUrl = getExpectedUrlForSidenavLink(link);
            assertEquals(expectedUrl, driver.getCurrentUrl(), "Navigation failed for sidenav link: " + link.name());
        }
    }


    private void navigateToClassSearchPage() {
        String classSearchUrl = "https://tigercenter.rit.edu/tigerCenterApp/api/class-search";
        driver.get(classSearchUrl);
    }


    private void handleNavigationAndAssert(String originalHandle, String expectedUrl) {
        Set<String> windowHandles = driver.getWindowHandles();
        if (windowHandles.size() > 1) {
            String newTabHandle = windowHandles.stream()
                    .filter(handle -> !handle.equals(originalHandle))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Could not find valid window handle for the new tab."));
            driver.switchTo().window(newTabHandle);
            assertEquals(expectedUrl, driver.getCurrentUrl(), "Navigation failed.");
            driver.close();
            driver.switchTo().window(originalHandle);
        } else {
            assertEquals(expectedUrl, driver.getCurrentUrl(), "Navigation failed.");
        }
    }

    private String getExpectedUrlForHeaderLink(HeaderViewObject.Link link) {
        switch (link) {
            case FEEDBACK:
                return "https://help.rit.edu/sp?id=sc_cat_item&sys_id=36a3646ddbb60c50881c89584b9619f5";
            case SUPPORT:
                return "https://help.rit.edu/sp?id=sc_cat_item&sys_id=9b613be5dbf24c50881c89584b961986";
            case DIRECTORIES:
                return "https://www.rit.edu/directory";
            case RIT_HOME:
                return "https://www.rit.edu/";
            default:
                throw new IllegalArgumentException("Unexpected link: " + link);
        }
    }

    private String getExpectedUrlForFooterLink(FooterViewObject.Link link) {
        switch (link) {
            case COPYRIGHT_INFRINGEMENT:
                return "https://www.rit.edu/copyright-infringement";
            case PRIVACY_STATEMENT:
                return "https://www.rit.edu/privacy";
            case DISCLAIMER:
                return "https://www.rit.edu/disclaimer";
            case NONDISCRIMINATION:
                return "https://www.rit.edu/nondiscrimination";
            default:
                throw new IllegalArgumentException("Unexpected link: " + link);
        }
    }

    private String getExpectedUrlForSidenavLink(SidenavViewObject.Link link) {
        switch (link) {
            case CLASS_SEARCH:
                return "https://tigercenter.rit.edu/tigerCenterApp/api/class-search";
            case HOURS_LOCATIONS:
                return "https://tigercenter.rit.edu/tigerCenterApp/api/hours-and-locations";
            case GPA_CALCULATOR:
                return "https://tigercenter.rit.edu/tigerCenterApp/api/gpa-calc";
            case CLASS_COMPARE:
                return "https://tigercenter.rit.edu/tigerCenterApp/api/class-compare";
            case RESOURCES:
                return "https://tigercenter.rit.edu/tigerCenterApp/api/resources";
            default:
                throw new IllegalArgumentException("Unexpected link: " + link);
        }
    }
}
