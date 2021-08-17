import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        driver.navigate().to("https://www.megamegallo.hu/");
    }

    public final By COOKIE = By.className("accept_cookie");
    public final By ADATVEDELEM = By.id("dataprotection_info");
    public final By ETLAP_BUTTON = By.id("li_animated_link_menucard");
    private final By REGISTRATION = By.id("animated_link_registrati");
    private final By LOGIN_PAGE = By.id("animated_link_login");
    private final By LOGOUT = By.xpath("//*[@id=\"container_frame2\"]/header/div[1]/div/div/div[1]/ul/li[8]/a");

 //   public final By LOGGED_USERNAME = By.xpath("//*[@id=\"flat\"]/div[2]");
    public final By LOGGED_USERNAME = By.className("profile_name");
    public final By BELEPES_BUTTON = By.id("mli_animated_link_login");

    public void clickCookie() {
        driver.findElement(COOKIE).click();
       // Util.click(COOKIE);
    }

    public void clickLogin() {
        driver.findElement(LOGIN_PAGE).click();
    }

    public void clickLogout() {
        driver.findElement(LOGOUT).click();
    }

    public void clickRegistration() {
        driver.findElement(REGISTRATION).click();
    }

}
