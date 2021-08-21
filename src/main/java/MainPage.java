import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        driver.navigate().to("https://www.megamegallo.hu/");
    }

    private final By COOKIE = By.className("accept_cookie");
    private final By ADATVEDELEM_PAGE = By.id("dataprotection_info");
    private final By MENU_BUTTON = By.id("li_animated_link_menucard");
    private final By REGISTRATION = By.id("animated_link_registrati");
    private final By LOGIN_PAGE = By.id("animated_link_login");
    private final By LOGOUT = By.xpath("//*[@id=\"container_frame2\"]/header/div[1]/div/div/div[1]/ul/li[8]/a");

    private final By LOGGED_USERNAME = By.className("profile_name");
    private final By BELEPES_BUTTON = By.id("mli_animated_link_login");

    public void clickCookie() {
        Util util = new Util(driver);
        util.click(COOKIE);
    }

    public void clickLogin() {
        Util util = new Util(driver);
        util.click(LOGIN_PAGE);
    }

    public void clickLogout() {
        Util util = new Util(driver);
        util.click(LOGOUT);
    }

    public void clickRegistration() {
        Util util = new Util(driver);
        util.click(REGISTRATION);
    }

    public void clickDataprotectionPage() {
        Util util = new Util(driver);
        util.click(ADATVEDELEM_PAGE);
    }
    public void clickMenuPage() {
        Util util = new Util(driver);
        util.click(MENU_BUTTON);
    }

    public String getUserName() {
        Util util = new Util(driver);
        return util.getText(LOGGED_USERNAME);
    }

    public String getLoginText() {
        Util util = new Util(driver);
        return util.getText(BELEPES_BUTTON);
    }

}
