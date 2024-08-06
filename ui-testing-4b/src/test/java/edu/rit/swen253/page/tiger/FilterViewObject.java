package edu.rit.swen253.page.tiger;

import edu.rit.swen253.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FilterViewObject {

    private final WebDriver driver;

    public FilterViewObject(WebDriver driver) {
        this.driver = driver;
    }

    public enum FilterType {
        CAFES_AND_SHOPS
    }

    public void checkFilter(FilterType filterType) {
        WebElement checkbox = getFilterCheckbox(filterType);
        SeleniumUtils.scrollToElement(checkbox); // Scroll to ensure visibility
        SeleniumUtils.clickElementUsingJs(checkbox); // Click using JavaScript
    }

    public void uncheckFilter(FilterType filterType) {
        WebElement checkbox = getFilterCheckbox(filterType);
        SeleniumUtils.scrollToElement(checkbox); // Scroll to ensure visibility
        SeleniumUtils.clickElementUsingJs(checkbox); // Click using JavaScript
    }

    private WebElement getFilterCheckbox(FilterType filterType) {
        // Locate the checkbox element based on filterType
        switch (filterType) {
            case CAFES_AND_SHOPS:
                return driver.findElement(By.id("mat-checkbox-4-input"));
            default:
                throw new IllegalArgumentException("Unknown filter type: " + filterType);
        }
    }
}
