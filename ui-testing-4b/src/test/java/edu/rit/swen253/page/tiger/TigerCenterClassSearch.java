package edu.rit.swen253.page.tiger;


import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;



import static org.junit.jupiter.api.Assertions.fail;


public class TigerCenterClassSearch extends AbstractPage {

    private DomElement mainContentPanel;
    private static final By MAIN_CONTENT_FINDER = By.cssSelector("div");

    public TigerCenterClassSearch() {
        try {
            mainContentPanel = findOnPage(MAIN_CONTENT_FINDER);
        } catch (TimeoutException e) {
            fail("Main content panel not found");
        }
    }


    public void selectATerm(String term) {
        //Get Term Selector
        WebElement selectElement = mainContentPanel.findChildBy(By.name("termSelector")).getWebElement();
        Select select = new Select(selectElement);

        //retrieve list of options
         List <WebElement> options = select.getOptions();

         //select option based on string
        for (int i = 0; i < options.size(); i++){
            WebElement tmp = options.get(i);
            if(Objects.equals(term, tmp.getText())){
                select.selectByIndex(i+1);
                System.out.println("Term was Found");
                return;
            }
        }
        System.out.println("Term Not Found");
    }

    public List<classResultsObject> selectSearchButton(String text) throws InterruptedException {
        mainContentPanel.findChildBy(By.className("completer-input")).clear();
        mainContentPanel.findChildBy(By.className("completer-input")).enterText(text);
        mainContentPanel.findChildBy(By.className("classSearchSearchButton")).getWebElement().sendKeys(Keys.ENTER);;

        List<classResultsObject> main = mainContentPanel.findChildrenBy(By.cssSelector("div.classSearchBasicResultsMargin > app-class-search-row"))
                .stream()
                // build a Rating Info view object for each of these divs
                .map(classResultsObject::new)
                .toList();

        Thread.sleep(5000);

        return main;
    }


    public void  NavigatetoAdvancedSearch()  {
        mainContentPanel.findChildBy(By.className("classSearchAdvancedSearchText")).click();

    }


}
