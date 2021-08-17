import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util {

    static WebDriver driver;

    public Util(WebDriver driver) {
        Util.driver = driver;
    }

    public static void click(By by){

        driver.findElement(by).click();
    }


}
