package edu.rit.swen253.test;

import edu.rit.swen253.page.SimplePage;
import edu.rit.swen253.page.tiger.TigerCenterHomePage;
import edu.rit.swen253.test.AbstractWebTest;
import edu.rit.swen253.test.maps.NavigateToRitMapsTest;
import edu.rit.swen253.utils.SeleniumUtils;
import org.junit.jupiter.api.*;

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

    private TigerCenterHomePage homePage;
    private String homePageHandle;

    //
    // Test sequence
    //

    @Test
    @Order(1)
    @DisplayName("First, navigate to the Tiger Center Home page.")
    public void navigateToHomePage() {
        homePage = navigateToPage("https://tigercenter.rit.edu", TigerCenterHomePage::new);

        Set<String> windowHandles = SeleniumUtils.getDriver().getWindowHandles();
        homePageHandle = windowHandles.iterator().next();
        log.info(String.format("Navigated to the Tiger Center Home page: %s", homePageHandle));
    }

    @Test
    @Order(1)
    @DisplayName("Second, navigate to the Tiger Center Class Search page.")
    public void navigateToClassSearchPage() {
        homePage.selectClassSearchAtRIT();

        // expect that TigerCenter window still exists
        Set<String> windowHandles = SeleniumUtils.getDriver().getWindowHandles();
        assertTrue(windowHandles.contains(homePageHandle), "Home page is not visible");
        System.out.println("\nmaster ofg none \n");
                //
                final SimplePage classSearchPage = assertNewPage(SimplePage::new);
                assertEquals("https://tigercenter.rit.edu/tigerCenterApp/api/class-search", classSearchPage.getURL());
    }

}
