package edu.rit.swen253.test.maps;

import edu.rit.swen253.page.SimplePage;
import edu.rit.swen253.page.tiger.TigerCenterHomePage;
import edu.rit.swen253.test.AbstractWebTest;
import edu.rit.swen253.utils.SeleniumUtils;
import org.junit.jupiter.api.*;

import java.util.Set;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * A simple test that uses the TigerCenter <em>Maps at RIT</em> button to navigate
 * to a new tab that displays the {@code maps.rit.edu} web page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NavigateToRitMapsTest extends AbstractWebTest {
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
  @Order(2)
  @DisplayName("Second, click on the Maps at RIT button and validate navigation.")
  public void navigateToMaps() {
    homePage.selectMapsAtRIT();

    // expect that TigerCenter window still exists
    Set<String> windowHandles = SeleniumUtils.getDriver().getWindowHandles();
    assertTrue(windowHandles.contains(homePageHandle), "Home page is not visible");

    // find the window handle for the new tab
    final String newTabHandle = windowHandles.stream()
      .filter(handle -> !handle.equals(homePageHandle))
      .findFirst()
      .orElseThrow(() -> new RuntimeException("Could not find valid window handle for the Maps tab."));

    // switch to that tab and assert that the URL of this page matches the expected maps website
    SeleniumUtils.getDriver().switchTo().window(newTabHandle);
    final SimplePage mapsPage = assertNewPage(SimplePage::new);
    assertEquals("https://maps.rit.edu/", mapsPage.getURL());
  }
}
