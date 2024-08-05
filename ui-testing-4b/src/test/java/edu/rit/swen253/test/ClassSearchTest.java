package edu.rit.swen253.test;

import edu.rit.swen253.page.SimplePage;
import edu.rit.swen253.page.tiger.TigerCenterClassSearch;
import edu.rit.swen253.page.tiger.TigerCenterHomePage;
import edu.rit.swen253.page.tiger.classResultsObject;
import edu.rit.swen253.test.AbstractWebTest;
import edu.rit.swen253.test.maps.NavigateToRitMapsTest;
import edu.rit.swen253.utils.SeleniumUtils;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import static edu.rit.swen253.utils.SeleniumUtils.navigateToPage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;



/**
 * A simple test that uses the TigerCenter <em>Maps at RIT</em> button to navigate
 * to a new tab that displays the {@code maps.rit.edu} web page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClassSearchTest extends AbstractWebTest {
    private static final Logger log = Logger.getLogger(NavigateToRitMapsTest.class.getName());

    private TigerCenterClassSearch homePage;
    private String homePageHandle;

    //
    // Test sequence
    //

    @Test
    @Order(1)
    @DisplayName("First, navigate to the Tiger Center Class Search page.")
    public void navigateToClassSearchPage() {
        homePage = navigateToPage("https://tigercenter.rit.edu/tigerCenterApp/api/class-search", TigerCenterClassSearch::new);

        Set<String> windowHandles = SeleniumUtils.getDriver().getWindowHandles();
        homePageHandle = windowHandles.iterator().next();
        log.info(String.format("Navigated to the Tiger Center Class Search page: %s", homePageHandle));
    }

    @Test
    @Order(2)
    @DisplayName("Second, select a valid term.")
    public void selectTerm() {
        homePage.selectATerm("2023-24 Summer (2238)");

    }

    @Test
    @Order(3)
    @DisplayName("Third, search a valid course number.")
    public void searchCourseCode() throws InterruptedException {
        List<classResultsObject>  results = homePage.selectSearchButton("IGME");
        assertEquals(50, results.size());
    }

    @Test
    @Order(4)
    @DisplayName("Fourth, search a valid course number.")
    public void searchCourseNumber() throws InterruptedException {
        List<classResultsObject>  results = homePage.selectSearchButton("SWEN 261");
        assertEquals(9, results.size());
    }

    @Test
    @Order(5)
    @DisplayName("Fifth, search a valid course number.")
    public void searchCourseName() throws InterruptedException {
        List<classResultsObject>  results = homePage.selectSearchButton("Philosophy");
        assertEquals(20, results.size());
    }

    @Test
    @Order(6)
    @DisplayName("Sixth, invalid entry.")
    public void invalidCourseSearch() throws InterruptedException {
        List<classResultsObject>  results = homePage.selectSearchButton("TheBestClass");
        assertEquals(0, results.size());
    }


    @Test
    @Order(7)
    @DisplayName("Seventh, Go to advanced view.")
    public void advancedView() throws InterruptedException {
        homePage.NavigatetoAdvancedSearch();
    }
}
