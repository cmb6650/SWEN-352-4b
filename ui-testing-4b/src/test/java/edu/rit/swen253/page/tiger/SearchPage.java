package edu.rit.swen253.page.tiger;

import edu.rit.swen253.page.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import edu.rit.swen253.utils.HtmlUtils;

import static edu.rit.swen253.utils.SeleniumUtils.getDriver;

public class SearchPage extends AbstractPage {

    private final By sidenavMenuButton = By.cssSelector(".sidenav-menu-button"); // Adjust the selector as needed

    public SearchPage(WebDriver driver) {
        super();
    }

    /**
     * Opens the sidenav menu.
     */
    public void openSidenavMenu() {
        WebElement menuButton = getDriver().findElement(sidenavMenuButton);
        menuButton.click();
    }
}
