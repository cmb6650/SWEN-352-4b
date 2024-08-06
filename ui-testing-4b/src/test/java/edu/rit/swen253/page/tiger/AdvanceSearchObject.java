package edu.rit.swen253.page.tiger;

import edu.rit.swen253.utils.DomElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AdvanceSearchObject {
    private final DomElement viewContainer;

    public AdvanceSearchObject(final DomElement viewContainer) {
        this.viewContainer = viewContainer;
    }

    public List<WebElement> getAdvancedSearchObject() {
        return viewContainer.getWebElement().findElements(By.xpath("/html/body/div[3]/div[2]/div/mat-dialog-container/advanced-search-dialog/mat-dialog-content/div/div/div"));
    }

    public void saveOptions() {
        viewContainer.getWebElement().findElement(By.xpath("/html/body/div[3]/div[2]/div/mat-dialog-container/advanced-search-dialog/mat-dialog-actions/button[2]")).click();
    }
}
