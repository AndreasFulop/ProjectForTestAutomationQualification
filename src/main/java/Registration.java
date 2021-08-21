import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Registration {
    WebDriver driver;

    public Registration(WebDriver driver) {
        this.driver = driver;
    }

    private final By EMAIL_FIELD = By.name("email");
    private final By PASSWORD_FIELD = By.id("PasswordForm");
    private final By NAME_FIELD = By.name("name");
    private final By PHONE_FIELD = By.name("phone");
    private final By ASZF_CHECK = By.xpath("//*[@id=\"login-menu\"]/div[13]/div[1]/label");
    private final By DATAPROT_CHECK = By.xpath("//*[@id=\"login-menu\"]/div[13]/div[2]/label");
    private final By CONFIRM_REGISTRATION = By.className("func_btn");


    public void registrating() {
        Util util = new Util(driver);
        write(EMAIL_FIELD, "natan78@freemail.hu");
        write(PASSWORD_FIELD, "Coool123");
        write(NAME_FIELD, "Test Customer");
        write(PHONE_FIELD, "20/1234567");
        util.click(ASZF_CHECK);
        util.click(DATAPROT_CHECK);
        util.click(CONFIRM_REGISTRATION);
    }

    public void write(By by, String string) {
        driver.findElement(by).sendKeys(string);
    }
}
