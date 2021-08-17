import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class DataProtection {
    WebDriver driver;

    public DataProtection(WebDriver driver) {
        this.driver = driver;
    }

    public final By ADATVEDELEM_TITLE = By.xpath("//*[@id=\"global_information\"]/h1");
    private final By ADATVEDELEM_H2 = By.xpath("//*[@id=\"global_information\"]/h2");


    public List<String> dataProtectionReader() throws InterruptedException{
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int i = 0;
        while (i < 24){
        js.executeScript("window.scrollBy(0,+350)", "");
        i++;
        Thread.sleep(200);
        }
        List<WebElement> content = driver.findElements(ADATVEDELEM_H2);
        List<String> result = new ArrayList<>();
        for (WebElement j: content) {
            result.add(j.getText());
        }
        return result;
    }
}
