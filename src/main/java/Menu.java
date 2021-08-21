import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    WebDriver driver;

    public Menu(WebDriver driver) {
        this.driver = driver;
    }

    private final By MENU_ITEMS = By.xpath("//*[@id=\"li_animated_link_menucard\"]/ul/li");
    private final By MENU_ID = By.id("li_animated_link_menucard");
    private final String MENU_NAME = "//*[@id=\"li_animated_link_menucard\"]/ul/li";
    private final By MENU_BUTTONS = By.className("menu_type");
    private final String MENU_BUTTON_NAME = "//*[@class=\"menu_type\"]";
    private final By DESSERT_BUTTON = By.xpath("//*[@class=\"menu_type\"][14]");

    private final By CONFIRM_ORDER = By.id("block_bill_button");
    private final By PIECES_SELECTOR = By.xpath("//*[@id=\"ammount\"]/div/select");

    private final By BASKET = By.xpath("//*[@id=\"centertop_column\"]/div[2]/p[1]/img");
    private final By TABLEROWS_IN_BASKET = By.xpath("//*[@id=\"not_fixed\"]/div/form/table/tbody/tr");
    private final By DELETE_ORDER = By.xpath("//*[@id=\"not_fixed\"]/div/form/table/tbody/tr[2]/td[4]/div/a");
    public final By CLOSE_BASKET_WINDOW = By.id("close_minibasket_btn");
    private final By TEMP = By.xpath("//*[@id=\"food_type_name_center\"]/h1");
    private final By TABLE_ID= By.id("tableId");

    public List<String> clickNextMenuPageByDropdown() throws InterruptedException {
        Util util = new Util(driver);
        List<WebElement> menuItems = util.finds(MENU_ITEMS);
        Actions action = new Actions(driver);
        List<String> result = new ArrayList<>();
        for (int j = 1; j <= menuItems.size()/2; j++) {
            System.out.print(menuItems.size()+"/"+j);
            WebElement live = util.find(MENU_ID);   //important to be in the loop
            action.moveToElement(live).build().perform();
            Thread.sleep(200);
            util.click(By.xpath(MENU_NAME + "[" + j + "]"));
            Thread.sleep(300);
            String temp = util.getText(TEMP);
            result.add(temp);
            System.out.println(" - done");
        }
        return result;
    }

    public List<String> clickNextMenuPageByButtons() throws InterruptedException {
        Util util = new Util(driver);
        List<WebElement> menuItems2 = util.finds(MENU_BUTTONS);
        System.out.println(menuItems2.size());
        List<String> result = new ArrayList<>();
        for (int j = 1; j <= menuItems2.size()/3; j++) {
            util.click(By.xpath(MENU_BUTTON_NAME + "[" + j + "]"));
            Thread.sleep(300);
            try {
                String temp = util.getText(TEMP);
                result.add(temp);
            } catch (Exception e) {
                String temp = util.getText(TABLE_ID);
                result.add(temp);
                System.out.println(temp + " - " + e);
            }
        }
        return result;
    }

    public void orderDessert(String order, int amount) throws InterruptedException{
        Util util = new Util(driver);
        Thread.sleep(500);
        util.click(DESSERT_BUTTON);
        String title = "a[title = 'Mega Megálló - " + order + " - Desszert - Online rendelés']";
        Thread.sleep(200);
        util.click(By.cssSelector(title));
        Thread.sleep(300);
        Select select = new Select(util.find(PIECES_SELECTOR));
        select.selectByValue(Integer.toString(amount));
        Thread.sleep(300);
        util.click(CONFIRM_ORDER);
    }


    public void deleteBasket() throws InterruptedException{
        Util util = new Util(driver);
        util.click(BASKET);
        util.click(DELETE_ORDER);
        Thread.sleep(1000);
        util.click(CLOSE_BASKET_WINDOW);
    }

    public String seeBasket() throws InterruptedException{
        Util util = new Util(driver);
        util.click(BASKET);
        Thread.sleep(300);
        List<WebElement> tablerows = util.finds(TABLEROWS_IN_BASKET);
        String result = "";
        if (tablerows.size() > 0) {
            for (int i = 3; i <= tablerows.size(); i+=3) {
                String resultPath = "//*[@id=\"not_fixed\"]/div/form/table/tbody/tr[" + (i-1) + "]/td[2]/div";
                String resultPath2 = "//*[@id=\"not_fixed\"]/div/form/table/tbody/tr[" + (i-1) + "]/td[3]/div/b";
                result += util.getText(By.xpath(resultPath)) + " " + util.getText(By.xpath(resultPath2)) + "\n";
            }
        }
        util.click(CLOSE_BASKET_WINDOW);
        Thread.sleep(500);
        tablerows.clear();
        if (!result.equals("")) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }
}
