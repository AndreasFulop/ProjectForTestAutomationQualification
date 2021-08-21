import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestPage {
    WebDriver driver;


    @BeforeAll
    public static void Init() {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach
    public void setDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        options.addArguments("incognito");
        options.addArguments("--disable-gpu", "--ignore-certificate-errors", "--disable-extensions", "--disable-dev-shm-usage");
        options.addArguments("window-size=1200,730");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);

    }


    @Test
    @DisplayName("M-3 - Adatkezelési Nyilatkozat")
    @Order(4)
    @Severity(SeverityLevel.CRITICAL)
    public void testDataProtectionTitle(){
        MainPage mainPage = new MainPage(driver);
        DataProtection dataProtection = new DataProtection(driver);
        mainPage.clickCookie();
        mainPage.clickDataprotectionPage();
        String actual = dataProtection.getDataprotectionTitle();
        Assertions.assertEquals("Adatkezelési Tájékoztató", actual);
    }

    @Test
    @DisplayName("M-4 - Listázás")
    @Order(5)
    @Severity(SeverityLevel.CRITICAL)
    public void testDataProtectionList() throws InterruptedException{
        MainPage mainPage = new MainPage(driver);
        DataProtection dataProtection = new DataProtection(driver);
        mainPage.clickCookie();
        mainPage.clickDataprotectionPage();
        dataProtection.dataProtectionReader();
        List<String> contentOfTable = new ArrayList<>();
        contentOfTable.add("I. A Felhasználók által kifejezetten megadott adatok kezelése");
        contentOfTable.add("II. A Weboldal és Blog használatával összefüggésben egyébként gyűjtött információk – cookie-k");
        contentOfTable.add("III. Linkek");
        contentOfTable.add("IV. Adatbiztonság");
        contentOfTable.add("V. Jogérvényesítési lehetőségek");
        contentOfTable.add("VI. Egyéb rendelkezések");
        Assertions.assertEquals(contentOfTable, dataProtection.dataProtectionReader());
    }

    @Test
    @DisplayName("M-5.1 - Étlap bejárása1")
    @Order(6)
    @Severity(SeverityLevel.NORMAL)
    public void testMenuPagingByDropdown() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        Menu menu = new Menu(driver);
        mainPage.clickCookie();
        mainPage.clickMenuPage();
        List<String> actual = menu.clickNextMenuPageByDropdown();
        List<String> expected = new ArrayList<>();
        expected.add("Akció");
        expected.add("Hamburgerek");
        expected.add("Papucs szendvicsek");
        expected.add("Levesek");
        expected.add("Főételek");
        expected.add("Tészták");
        expected.add("Töltött húsok");
        expected.add("Vaslapon sült ételek");
        expected.add("Tálak");
        expected.add("Tortillák");
        expected.add("Hot Dogok");
        expected.add("Saláták");
        expected.add("Köretek");
        expected.add("Desszertek");
        expected.add("Öntetek");
        expected.add("Italok");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("M-5.2 - Étlap bejárása2")
    @Order(7)
    @Severity(SeverityLevel.NORMAL)
    public void testMenuPagingByButtons() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        Menu menu = new Menu(driver);
        mainPage.clickCookie();
        mainPage.clickMenuPage();
        List<String> actual = menu.clickNextMenuPageByButtons();
        List<String> expected = new ArrayList<>();
        expected.add("Akció");
        expected.add("Hamburgerek");
        expected.add("Papucs szendvicsek");
        expected.add("Levesek");
        expected.add("Főételek");
        expected.add("Tészták");
        expected.add("Töltött húsok");
        expected.add("Vaslapon sült ételek");
        expected.add("Tálak");
        expected.add("Tortillák");
        expected.add("Hot Dogok");
        expected.add("Saláták");
        expected.add("Köretek");
        expected.add("Desszertek");
        expected.add("Öntetek");
        expected.add("Italok");
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("M-6 - Rendelés")
    @Order(8)
    @Severity(SeverityLevel.CRITICAL)
    public void testOrderDessert() throws InterruptedException{
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCookie();
        mainPage.clickMenuPage();
        Menu menu = new Menu(driver);
        menu.orderDessert("Nutellás Golyó", 2);
        Thread.sleep(200);
        String actual = menu.seeBasket();
        String expected = "2db Nutellás Golyó";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("M-10 - Rendelés törlése")
    @Order(9)
    @Severity(SeverityLevel.CRITICAL)
    public void testOrderDessertThenDelete() throws InterruptedException{
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCookie();
        mainPage.clickMenuPage();
        Menu menu = new Menu(driver);
        menu.orderDessert("Aranygaluska", 2);
        menu.deleteBasket();
        Thread.sleep(500);
        String expected2 = "";
        String actual2 = menu.seeBasket();
        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    @DisplayName("M-9 - Rendelés módosítása")
    @Order(10)
    @Severity(SeverityLevel.CRITICAL)
    public void testOrderDessertThenModify() throws InterruptedException{
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCookie();
        mainPage.clickMenuPage();
        Menu menu = new Menu(driver);
        menu.orderDessert("Aranygaluska", 2);
        String actual = menu.seeBasket();
        String expected = "2db Aranygaluska";
        Assertions.assertEquals(expected, actual);
        menu.deleteBasket();
        menu.orderDessert("Kókuszos palacsinta", 4);
        String actual2 = menu.seeBasket();
        String expected2 = "4db Kókuszos palacsinta";
        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    @DisplayName("M-8 Rendelési adat mentése fájlba")
    @Order(11)
    @Severity(SeverityLevel.CRITICAL)
    public void testOrderAndWrite() throws InterruptedException, IOException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCookie();
        mainPage.clickMenuPage();
        Menu menu = new Menu(driver);
        menu.orderDessert("Aranygaluska", 3);
        menu.orderDessert("Kakaós palacsinta", 6);
        menu.orderDessert("Nutellás Golyó", 1);
        String orderList = menu.seeBasket();
        FileWriter myWriter = new FileWriter("menu.txt");
        myWriter.write(orderList);
        myWriter.close();
        Thread.sleep(200);
        int expected = 3;
        int actual = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("menu.txt"));
            while (reader.readLine() != null) actual++;
            reader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("M-7 - Fájlban összeírt rendelés feladása")
    @Order(12)
    @Severity(SeverityLevel.NORMAL)
    public void testOrderFromFile() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCookie();
        mainPage.clickMenuPage();
        Menu menu = new Menu(driver);
        String actual = "";
        String expected = "";
        try {
            File myObj = new File("menu.txt");
            Scanner scanner = new Scanner(myObj);
            while (scanner.hasNextLine()) {
                String temp = scanner.nextLine();
                expected += temp + "\n";
                String[] data = temp.split("db ");
                menu.orderDessert(data[1], Integer.parseInt(data[0]));
                actual = menu.seeBasket();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        if (!expected.equals("")) {
            expected = expected.substring(0, expected.length() - 1);
        }
        Assertions.assertEquals(expected, actual);
    }

    @Disabled
    @DisplayName("M-1 - Regisztráció")
    @Order(1)
    @Severity(SeverityLevel.CRITICAL)
    public void testRegistration() throws InterruptedException{
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCookie();
        mainPage.clickRegistration();
        Registration registration = new Registration(driver);
        registration.registrating();
        Thread.sleep(200);
        String actual = mainPage.getUserName();
        Assertions.assertEquals("Test Customer", actual);
    }

    @Test
    @DisplayName("M-2 - Bejelentkezés")
    @Order(2)
    @Severity(SeverityLevel.CRITICAL)
    public void testLogin() throws InterruptedException{
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCookie();
        mainPage.clickLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();
        Thread.sleep(200);
        String actual = mainPage.getUserName();
        Assertions.assertEquals("Test Customer", actual);
    }

    @Test
    @DisplayName("M-11 - Kijelentkezés")
    @Order(3)
    @Severity(SeverityLevel.CRITICAL)
    public void testLogout() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCookie();
        mainPage.clickLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();
        mainPage.clickLogout();
        String actual = mainPage.getLoginText();
        Assertions.assertEquals("Belépés", actual);
    }

        @AfterEach
    public void closing() {
        if (driver != null) {
            driver.quit();
        }
    }
}
