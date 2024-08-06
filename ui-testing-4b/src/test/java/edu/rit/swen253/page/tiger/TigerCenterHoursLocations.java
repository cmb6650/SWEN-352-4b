package edu.rit.swen253.page.tiger;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class TigerCenterHoursLocations extends AbstractPage {
    private DomElement mainContentPanel;
    private static final By MAIN_CONTENT_FINDER = By.cssSelector("div");

    public TigerCenterHoursLocations() {
        try {
            mainContentPanel = findOnPage(MAIN_CONTENT_FINDER);
        } catch (TimeoutException e) {
            fail("Main content panel not found");
        }
    }

    //Need to add error handling for no hours
    public ArrayList<String> getDiningServiceInfo(String DiningService) throws InterruptedException {
        mainContentPanel.findChildBy(By.tagName("input")).clear();
        mainContentPanel.findChildBy(By.tagName("input")).enterText(DiningService);
        mainContentPanel.getWebElement().findElement(By.xpath("/html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/hours-and-locations/div/div/mat-tab-group/div/mat-tab-body[1]/div/dining/div[1]/div/div[9]/div[2]")).click();

        ArrayList<String> arr = new ArrayList<String>();
        //get hours
        arr.add(mainContentPanel.findChildBy(By.className("greenText")).getText());
        //get Menu
        mainContentPanel.getWebElement().findElement(By.xpath("/html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/hours-and-locations/div/div/mat-tab-group/div/mat-tab-body[1]/div/dining/div[1]/div/div[9]/div[2]/div[4]/div[3]/div/mat-tab-group/mat-tab-header/div[2]/div/div/div[2]")).click();
        arr.add(mainContentPanel.getWebElement().findElement(By.xpath("/html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/hours-and-locations/div/div/mat-tab-group/div/mat-tab-body[1]/div/dining/div[1]/div/div[9]/div[2]/div[4]/div[3]/div/mat-tab-group/div/mat-tab-body[2]/div/div[2]/p/span")).getText());
        // get description
        mainContentPanel.getWebElement().findElement(By.xpath("/html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/hours-and-locations/div/div/mat-tab-group/div/mat-tab-body[1]/div/dining/div[1]/div/div[9]/div[2]/div[4]/div[3]/div/mat-tab-group/mat-tab-header/div[2]/div/div/div[3]")).click();
        arr.add(mainContentPanel.getWebElement().findElement(By.xpath("/html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/hours-and-locations/div/div/mat-tab-group/div/mat-tab-body[1]/div/dining/div[1]/div/div[9]/div[2]/div[4]/div[3]/div/mat-tab-group/div/mat-tab-body[3]/div/div")).getText());
        Thread.sleep(5000);

        return arr;
    }

    public List<DomElement> getAllDiningServiceASC() throws InterruptedException {
        mainContentPanel.findChildBy(By.tagName("input")).clear();
        mainContentPanel.findChildBy(By.xpath("//*[@id=\"mat-tab-content-0-0\"]/div/dining/div[1]/div/div[9]/div[2]/div[1]/a[2]")).click();
        Thread.sleep(5000);
        return mainContentPanel.findChildrenBy(By.xpath("//*[@id=\"mat-tab-content-0-0\"]/div/dining/div[1]/div/div[9]/div[2]/div"))
                .stream()
                .toList();
    }


}
