import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By EMAIL_FIELD = By.name("email");
    private final By PASSWORD_FIELD = By.name("customer_password");
    private final By CONFIRM_LOGIN = By.name("ok");

    public void login() {
        Util util = new Util(driver);
        write(EMAIL_FIELD, "natan797979797878@freemail.hu");
        write(PASSWORD_FIELD, "Coool123");
        util.click(CONFIRM_LOGIN);
    }

    public void write(By by, String string) {
        driver.findElement(by).sendKeys(string);
    }
}
