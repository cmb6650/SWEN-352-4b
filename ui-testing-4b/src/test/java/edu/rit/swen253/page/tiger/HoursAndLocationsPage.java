package edu.rit.swen253.page.tiger;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HoursAndLocationsPage extends AbstractPage {

    private final WebDriver driver;

    public HoursAndLocationsPage(WebDriver driver) {
        super();
        this.driver = driver;
    }

    public void navigateTo() {
        driver.get("https://tigercenter.rit.edu/tigerCenterApp/api/hours-and-locations"); // Replace with the actual URL
    }

    public void openDetails(String locationName) {
        List<WebElement> locationElements = driver.findElements(By.cssSelector(".diningTabEateryName span"));
        for (WebElement locationElement : locationElements) {
            if (locationElement.getText().equals(locationName)) {
                locationElement.click();
                break;
            }
        }
    }

    public void sortBy(String criteria) {
        WebElement sortDropdown = driver.findElement(By.id("sort-dropdown"));
        sortDropdown.click();
        WebElement option = sortDropdown.findElement(By.xpath("//option[text()='" + criteria + "']"));
        option.click();
    }

    public void applyFilter(String filterText) {
        WebElement filterCheckbox = driver.findElement(By.xpath("//mat-checkbox[.//span[text()='" + filterText + "']]"));
        filterCheckbox.click();
    }

    public void removeFilter(String filterText) {
        WebElement filterCheckbox = driver.findElement(By.xpath("//mat-checkbox[.//span[text()='" + filterText + "']]"));
        filterCheckbox.click();
    }

    public List<WebElement> getDisplayedLocations() {
        return driver.findElements(By.cssSelector(".diningTabEateryName span"));
    }

    public By getLocationsListLocator() {
        return By.cssSelector(".diningTabEateryName span");
    }
}
