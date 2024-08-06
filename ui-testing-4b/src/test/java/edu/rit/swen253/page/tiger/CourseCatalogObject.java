package edu.rit.swen253.page.tiger;

import edu.rit.swen253.utils.DomElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Objects;

public class CourseCatalogObject {
    private final DomElement viewContainer;

    public CourseCatalogObject (final DomElement viewContainer) {
        this.viewContainer = viewContainer;
    }

    public List<WebElement> getColleges() {
        return viewContainer.getWebElement().findElements(By.xpath("/html/body/div[3]/div[2]/div/mat-dialog-container/course-catalog-dialog/mat-dialog-content/div/mat-accordion/mat-expansion-panel"));
    }

    public List<WebElement> getDepartments(String college) {

        List<WebElement> colleges = getColleges();
        //select option based on string
        for (int i = 0; i < colleges.size(); i++){
            WebElement tmp = colleges.get(i).findElement(By.xpath("/html/body/div[3]/div[2]/div/mat-dialog-container/course-catalog-dialog/mat-dialog-content/div/mat-accordion/mat-expansion-panel[1]/mat-expansion-panel-header/span[1]/mat-panel-title/span"));
            if(Objects.equals(college, tmp.getText())){
                tmp.click();
                return viewContainer.getWebElement().findElements(By.xpath("/html/body/div[3]/div[2]/div/mat-dialog-container/course-catalog-dialog/mat-dialog-content/div/mat-accordion/mat-expansion-panel[1]/div/div/mat-accordion/mat-expansion-panel"));
            }
        }
        return List.of();
    }

}
