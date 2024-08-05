package edu.rit.swen253.page.tiger;

import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.HtmlUtils;
import edu.rit.swen253.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SortViewObject {

    private final WebElement sortDropdown;

    public SortViewObject() {
        this.sortDropdown = SeleniumUtils.getDriver().findElement(By.cssSelector("#sort-dropdown"));
    }

    public enum SortCriteria {
        DATE("Date"),
        NAME("Name"),
        PRICE("Price");

        private final String value;

        SortCriteria(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public void sortBy(SortCriteria criteria) {
        sortDropdown.findElement(By.tagName("button")).click();
        WebElement option = sortDropdown.findElement(By.xpath("//option[text()='" + criteria.getValue() + "']"));
        option.click();
    }

    public boolean isSortedBy(SortCriteria criteria) {
        WebElement selectedOption = sortDropdown.findElement(By.tagName("option[selected]"));
        return selectedOption.getText().equals(criteria.getValue());
    }
}
