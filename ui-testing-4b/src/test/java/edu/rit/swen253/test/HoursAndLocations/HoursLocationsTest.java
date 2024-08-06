package edu.rit.swen253.test.HoursAndLocations;

import edu.rit.swen253.page.tiger.AdvanceSearchObject;
import edu.rit.swen253.page.tiger.CourseCatalogObject;
import edu.rit.swen253.page.tiger.TigerCenterClassSearch;
import edu.rit.swen253.page.tiger.TigerCenterHoursLocations;
import edu.rit.swen253.test.AbstractWebTest;
import edu.rit.swen253.test.maps.NavigateToRitMapsTest;
import edu.rit.swen253.utils.SeleniumUtils;
import org.junit.jupiter.api.*;

import java.util.Set;
import java.util.logging.Logger;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HoursLocationsTest extends AbstractWebTest {
    private static final Logger log = Logger.getLogger(NavigateToRitMapsTest.class.getName());

    private TigerCenterHoursLocations homePage;
    private String homePageHandle;

    //
    // Test sequence
    //

    @Test
    @Order(1)
    @DisplayName("First, navigate to the Tiger Center Hours and Locations page.")
    public void navigateToClassSearchPage() {
        homePage = navigateToPage("https://tigercenter.rit.edu/tigerCenterApp/api/hours-and-locations", TigerCenterHoursLocations::new);

        Set<String> windowHandles = SeleniumUtils.getDriver().getWindowHandles();
        homePageHandle = windowHandles.iterator().next();
        log.info(String.format("Navigated to the Tiger Center Hours and Locations page: %s", homePageHandle));
    }

    @Test
    @Order(2)
    @DisplayName("Second, view Hours of a given location.")
    public void clickThroughHoursMenuandDescription() throws InterruptedException {

        System.out.println(homePage.getDiningServiceInfo("Artesano Bakery").toString());

    }

    @Test
    @Order(3)
    @DisplayName("Retrieve a list of Dining location")
    public void testretrieveLocations() throws InterruptedException {
        System.out.println(homePage.getAllDiningServiceASC());
    }

}
