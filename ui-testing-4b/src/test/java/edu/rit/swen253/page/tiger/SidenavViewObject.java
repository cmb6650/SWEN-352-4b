package edu.rit.swen253.page.tiger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SidenavViewObject {

    private final WebDriver driver;

    public SidenavViewObject(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo(Link link) {
        String linkText = link.getText();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement sidenavLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@mattooltip='" + linkText + "']")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sidenavLink);
        sidenavLink.click();
    }

    public enum Link {
        CLASS_SEARCH("Class Search"),
        HOURS_LOCATIONS("Hours & Locations"),
        GPA_CALCULATOR("GPA Calculator"),
        CLASS_COMPARE("Class Compare"),
        RESOURCES("Resources");

        private final String text;

        Link(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
}
