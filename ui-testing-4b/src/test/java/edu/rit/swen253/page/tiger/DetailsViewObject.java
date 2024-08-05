package edu.rit.swen253.page.tiger;

import edu.rit.swen253.utils.HtmlUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DetailsViewObject {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private static final By LOCATION_NAME_LOCATOR = By.cssSelector(".diningTabEateryName span");
    private static final By LOCATION_SUMMARY_LOCATOR = By.cssSelector(".diningTabEaterySummary span");
    private static final By HOURS_TAB_LOCATOR = By.xpath("//div[text()='Hours']");
    private static final By MENU_TAB_LOCATOR = By.xpath("//div[text()='Menu']");
    private static final By NO_HOURS_MESSAGE_LOCATOR = By.cssSelector(".diningTabNoPaddingText");

    public DetailsViewObject(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickLocation(String locationName) {
        // Wait for the location element to be clickable and click on it
        WebElement locationElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='" + locationName + "']")));
        locationElement.click();
    }

    public boolean isNoHoursMessageDisplayed() {
        // Wait for the no hours message element to be visible and return true if it's displayed
        try {
            WebElement noHoursMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(NO_HOURS_MESSAGE_LOCATOR));
            return noHoursMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
