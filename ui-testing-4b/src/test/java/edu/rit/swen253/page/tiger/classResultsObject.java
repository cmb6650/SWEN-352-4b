package edu.rit.swen253.page.tiger;

import edu.rit.swen253.utils.DomElement;
import org.openqa.selenium.By;

public class classResultsObject {
    private final DomElement viewContainer;

    public classResultsObject(final DomElement viewContainer) {
        this.viewContainer = viewContainer;
    }

    public String getCourse() {
        viewContainer.scrollIntoView();
        return viewContainer.findChildBy(By.xpath("div[2]")).getText();
    }

    public String getStatus() {
        viewContainer.scrollIntoView();
        return viewContainer.findChildBy(By.xpath("div[3]")).getText();
    }

    public String getType() {
        viewContainer.scrollIntoView();
        return viewContainer.findChildBy(By.xpath("div[4]")).getText();
    }

    public String getAttributes() {
        viewContainer.scrollIntoView();
        return viewContainer.findChildBy(By.xpath("div[5]")).getText();
    }

    public String getCredits() {
        viewContainer.scrollIntoView();
        return viewContainer.findChildBy(By.xpath("div[6]")).getText();
    }

    public String getDaysandTimes() {
        viewContainer.scrollIntoView();
        return viewContainer.findChildBy(By.xpath("div[7]")).getText();
    }

    public String getLocation() {
        viewContainer.scrollIntoView();
        return viewContainer.findChildBy(By.xpath("div[8]")).getText();
    }

    public String getInstructor() {
        viewContainer.scrollIntoView();
        return viewContainer.findChildBy(By.xpath("div[9]")).getText();
    }
}
