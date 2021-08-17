import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    private final By ORDERABLE_MEALS = By.xpath("//*[@id=\"food_dring_size\"]/a");
  //  private final By CONFIRM_ORDER = By.id("block_bill_button");
    private final By CONFIRM_ORDER = By.xpath("//*[@id=\"block_bill_button\"]");
    private final By PIECES_SELECTOR = By.xpath("//*[@id=\"ammount\"]/div/select");

    public final By BASKET = By.xpath("//*[@id=\"centertop_column\"]/div[2]/p[1]/img");
    private final By AMOUNT_IN_BASKET = By.xpath("//*[@id=\"not_fixed\"]/div/form/table/tbody/tr[2]/td[2]/div");
    private final By AMOUNT_IN_BASKET2 = By.xpath("//*[@id=\"not_fixed\"]/div/form/table/tbody/tr[5]/td[2]/div");
    private final By AMOUNT_IN_BASKET3 = By.xpath("//*[@id=\"not_fixed\"]/div/form/table/tbody/tr[8]/td[2]/div");
    public final By TABLEROWS_IN_BASKET = By.xpath("//*[@id=\"not_fixed\"]/div/form/table/tbody/tr");
    private final By ORDER_IN_BASKET = By.xpath("//*[@id=\"not_fixed\"]/div/form/table/tbody/tr[2]/td[3]/div/b");
    private final By ORDER_IN_BASKET2 = By.xpath("//*[@id=\"not_fixed\"]/div/form/table/tbody/tr[5]/td[3]/div/b");
    private final By ORDER_IN_BASKET3 = By.xpath("//*[@id=\"not_fixed\"]/div/form/table/tbody/tr[8]/td[3]/div/b");
    private final By DELETE_ORDER = By.xpath("//*[@id=\"not_fixed\"]/div/form/table/tbody/tr[2]/td[4]/div/a");
    //private final By DELETE_ORDER = By.className("fas fa-times-circle redstyle");
   // public final By CLOSE_BASKET_WINDOW = By.id("close_minibasket_btn");
  //  public final By CLOSE_BASKET_WINDOW = By.xpath("//*[@id=\"not_fixed\"]/div[1]");
    public final By CLOSE_BASKET_WINDOW = By.xpath("//input[@type='button']");

    public List<String> clickNextMenuPageByDropdown() throws InterruptedException {
        List<WebElement> menuItems = driver.findElements(MENU_ITEMS);
//        System.out.println(menuItems.size());
        Actions action = new Actions(driver);
        List<String> result = new ArrayList<>();
        for (int j = 1; j <= menuItems.size()/2; j++) {
            WebElement live = driver.findElement(MENU_ID);   //important to be in the loop
            action.moveToElement(live).build().perform();
            Thread.sleep(200);
            driver.findElement(By.xpath(MENU_NAME + "[" + j + "]")).click();
            Thread.sleep(300);
            String temp = driver.findElement(By.xpath("//*[@id=\"food_type_name_center\"]/h1")).getText();
            result.add(temp);
        }
        return result;
    }

    public List<String> clickNextMenuPageByButtons() throws InterruptedException {
        List<WebElement> menuItems2 = driver.findElements(MENU_BUTTONS);
        System.out.println(menuItems2.size());
//        for (WebElement q:menuItems2) {System.out.println(q.getText());}
        List<String> result = new ArrayList<>();
        for (int j = 1; j <= menuItems2.size()/3; j++) {
//            System.out.println(menuItems2.size() + "\n");
            driver.findElement(By.xpath(MENU_BUTTON_NAME + "[" + j + "]")).click();
            Thread.sleep(300);
            try {
                String temp = driver.findElement(By.xpath("//*[@id=\"food_type_name_center\"]/h1")).getText();
                result.add(temp);
            } catch (Exception e) {
                String temp = driver.findElement(By.id("tableId")).getText();
                result.add(temp);
                System.out.println(temp + " - " + e);
            }
        }
        return result;
    }

    public void orderDessert(String order, int amount) throws InterruptedException{
        Thread.sleep(500);
     //   JavascriptExecutor js = (JavascriptExecutor)driver;
     //   js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@class=\"menu_type\"][14]")));
        driver.findElement(By.xpath("//*[@class=\"menu_type\"][14]")).click();
/*        List<WebElement> dessertList = driver.findElements(By.xpath("//*[@class=\"foods_desszertek subtypes\"]"));
        List<String> temp = new ArrayList<>();
        for (int i = 0; i<dessertList.size();i++) {
            temp.add(dessertList.get(i).getText());
            if (temp.get(i).contains(order)) {
                Thread.sleep(200);*/
                String title = "a[title = 'Mega Megálló - " + order + " - Desszert - Online rendelés']";
                Thread.sleep(200);
      //  js.executeScript("arguments[0].click();",driver.findElement(By.cssSelector(title)));
                driver.findElement(By.cssSelector(title)).click();
           //     break;
           // }
     //   }
        Thread.sleep(300);
        Select select = new Select(driver.findElement(PIECES_SELECTOR));
        select.selectByValue(Integer.toString(amount));
        Thread.sleep(300);
        driver.findElement(CONFIRM_ORDER).click();
   //     return seeBasket();
    }


    public void deleteBasket() throws InterruptedException{
        driver.findElement(BASKET).click();
        driver.findElement(DELETE_ORDER).click();
        Thread.sleep(1000);
        driver.findElement(CLOSE_BASKET_WINDOW).click();
    }

    public String seeBasket() throws InterruptedException{
        driver.findElement(BASKET).click();
        Thread.sleep(300);
        List<WebElement> tablerows = driver.findElements(TABLEROWS_IN_BASKET);
        String result = "";
        if (tablerows.size() > 0) {
            for (int i = 3; i <= tablerows.size(); i+=3) {
                String resultPath = "//*[@id=\"not_fixed\"]/div/form/table/tbody/tr[" + Integer.toString(i-1) + "]/td[2]/div";
                String resultPath2 = "//*[@id=\"not_fixed\"]/div/form/table/tbody/tr[" + Integer.toString(i-1) + "]/td[3]/div/b";
                result += driver.findElement(By. xpath(resultPath)).getText() + " " + driver.findElement(By.xpath(resultPath2)).getText() + "\n";
            }
        }
  //          Actions ac = new Actions(driver);
 //           WebElement live = driver.findElement(CLOSE_BASKET_WINDOW);
//            ac.moveToElement(live).build().perform();
//            ac.doubleClick(live).perform();
        driver.findElement(CLOSE_BASKET_WINDOW).click();
        Thread.sleep(500);
        tablerows.clear();
        if (!result.equals("")) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }
}
