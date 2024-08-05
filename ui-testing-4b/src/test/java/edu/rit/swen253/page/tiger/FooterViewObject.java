package edu.rit.swen253.page.tiger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FooterViewObject {

    private final WebDriver driver;
    private final By linkSelector = By.cssSelector("footer a"); // Adjust selector as needed

    public FooterViewObject(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Navigates to a link in the footer.
     * @param link the enum value representing the link to navigate to
     */
    public void navigateTo(Link link) {
        WebElement linkElement = driver.findElement(By.linkText(link.getText()));
        linkElement.click();
    }

    public enum Link {
        COPYRIGHT_INFRINGEMENT("Copyright Infringement"),
        PRIVACY_STATEMENT("Privacy Statement"),
        DISCLAIMER("Disclaimer"),
        NONDISCRIMINATION("Nondiscrimination");

        private final String text;

        Link(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
}

