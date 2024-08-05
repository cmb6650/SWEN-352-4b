package edu.rit.swen253.page.tiger;

import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.HtmlUtils;
import edu.rit.swen253.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FilterViewObject {

    private final WebElement filterInput;

    public FilterViewObject() {
        this.filterInput = SeleniumUtils.getDriver().findElement(By.cssSelector("#filter-input"));
    }

    public enum FilterOption {
        CATEGORY("Category"),
        PRICE("Price"),
        RATING("Rating");

        private final String value;

        FilterOption(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public void applyFilter(FilterOption option, String filterText) {
        WebElement filterDropdown = filterInput.findElement(By.tagName("select"));
        filterDropdown.findElement(By.xpath("//option[text()='" + option.getValue() + "']")).click();
        WebElement inputField = filterInput.findElement(By.tagName("input"));
        inputField.sendKeys(filterText);
    }

    public boolean isFilterApplied(FilterOption option, String filterText) {
        WebElement selectedOption = filterInput.findElement(By.tagName("select")).findElement(By.xpath("//option[@selected]"));
        WebElement inputField = filterInput.findElement(By.tagName("input"));
        return selectedOption.getText().equals(option.getValue()) && inputField.getAttribute("value").equals(filterText);
    }
}
