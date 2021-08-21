import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Util {

    private static WebDriver driver;

    public Util(WebDriver driver) {
        Util.driver = driver;
    }

    public void click(By by){
        driver.findElement(by).click();
    }

    public String getText(By by) {
        return driver.findElement(by).getText();
    }

    public String getText(WebElement webElement) {
        return webElement.getText();
    }


    public List<WebElement> finds (By by) {
        return driver.findElements(by);
    }

    public WebElement find (By by) {
        return driver.findElement(by);
    }
}
