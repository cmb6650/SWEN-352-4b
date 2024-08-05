package edu.rit.swen253.page.tiger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HeaderViewObject {

    private final WebDriver driver;
    private final By linkSelector = By.cssSelector("header a"); // Adjust selector as needed

    public HeaderViewObject(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Navigates to a link in the header.
     * @param link the enum value representing the link to navigate to
     */
    public void navigateTo(Link link) {
        WebElement linkElement = driver.findElement(By.linkText(link.getText()));
        linkElement.click();
    }

    public enum Link {
        FEEDBACK("Feedback"),
        SUPPORT("Support"),
        DIRECTORIES("Directories"),
        RIT_HOME("RIT Home");

        private final String text;

        Link(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
}

