package edu.rit.swen253.page.tiger;

import edu.rit.swen253.page.AbstractAngularPage;
import edu.rit.swen253.utils.DomElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HoursAndLocationsPage extends AbstractAngularPage {
    private final WebDriver driver;

    public HoursAndLocationsPage(WebDriver driver) {
        super("app-hours-and-locations"); // Assuming this is the component name for this page
        this.driver = driver;
    }

    public void openLocationDetailView(String locationName) {
        DomElement location = DomElement.findBy(By.xpath(String.format("//div[contains(text(),'%s')]", locationName)));
        location.click();
    }

    public void clickMenu() {
        DomElement.findBy(By.cssSelector("selector-for-menu-tab")).click();
    }

    public void clickDescription() {
        DomElement.findBy(By.cssSelector("selector-for-description-tab")).click();
    }

    public void clickHideDetails() {
        DomElement.findBy(By.cssSelector("selector-for-hide-details-button")).click();
    }

    public void sortOpenNow() {
        DomElement.findBy(By.cssSelector("selector-for-sort-open-now-button")).click();
    }

    public void sortAZ() {
        DomElement.findBy(By.cssSelector("selector-for-sort-a-z-button")).click();
    }

    public void toggleCafesAndShopsFilter() {
        DomElement.findBy(By.cssSelector("selector-for-cafes-and-shops-checkbox")).click();
    }

    public boolean isHoursDisplayed() {
        return DomElement.findBy(By.cssSelector("selector-for-hours-section")).hasChild(DomElement.IDENTITY_FINDER);
    }

    public boolean isMenuDisplayed() {
        return DomElement.findBy(By.cssSelector("selector-for-menu-section")).hasChild(DomElement.IDENTITY_FINDER);
    }

    public boolean isDescriptionDisplayed() {
        return DomElement.findBy(By.cssSelector("selector-for-description-section")).hasChild(DomElement.IDENTITY_FINDER);
    }

    public boolean isDetailViewDisplayed() {
        return DomElement.findBy(By.cssSelector("selector-for-detail-view")).hasChild(DomElement.IDENTITY_FINDER);
    }

}