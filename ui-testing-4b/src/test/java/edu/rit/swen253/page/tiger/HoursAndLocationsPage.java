package edu.rit.swen253.page.tiger;

import edu.rit.swen253.page.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import edu.rit.swen253.utils.DomElement;
import org.openqa.selenium.WebElement;

public class HoursAndLocationsPage extends AbstractPage {

    private final WebDriver driver;

    public HoursAndLocationsPage(WebDriver driver) {
        super();
        this.driver = driver;
    }

    // Find and interact with elements
    public void openDetails() {
        findOnPage(By.id("details-section")).click();
    }

    public void sortBy(String criteria) {
        DomElement sortDropdown = findOnPage(By.id("sort-dropdown"));
        sortDropdown.click();
        DomElement option = sortDropdown.findChildBy(By.xpath("//option[text()='" + criteria + "']"));
        option.click();
    }

    public void applyFilter(String filterText) {
        WebElement filterInput = driver.findElement(By.id("filter-input"));
        filterInput.sendKeys(filterText);
    }
}
