package shop.ui.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import shop.ui.pages.BucketPage;
import shop.ui.pages.LoginPage;
import shop.ui.pages.OrderPage;
import shop.ui.pages.SearchPage;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class ShopUITests {

    @BeforeClass
    @Parameters({"baseUrl"})
    public static void setup(String baseUrl) {
        Configuration.baseUrl = baseUrl;
        Configuration.browserCapabilities.setCapability("acceptInsecureCerts", false);
    }

    LoginPage loginPage = new LoginPage();
    SearchPage searchPage = new SearchPage();
    BucketPage bucketPage = new BucketPage();
    OrderPage orderPage = new OrderPage();

    @Test(testName = "Login")
    @Description("Авторизация в shop")
    @Parameters({"username", "password", "loginHeader", "loginMessage"})

    public void login(String username, String password, String loginHeader, String loginMessage) {
        loginPage.open();
        loginPage.openLoginForm();
        loginPage.header().shouldHave(text(loginHeader));
        loginPage.loginAsUser(username, password);
        loginPage.successMessage().shouldHave(text(loginMessage));
    }

    @Test(testName = "Search product")
    @Description("Поиск товара")
    @Parameters({"searchString", "productPrice"})
    public void searchProduct(String searchString, String productPrice) {
        loginPage.open();
        searchPage.startSearch(searchString);
        searchPage.searchTitle().shouldHave(text(searchString));
        searchPage.searchResult().shouldHave(sizeGreaterThan(0));
        // проверка найденного значения
        searchPage.getProductTitleByName(searchString).shouldHave(text(searchString));
        searchPage.getProductPriceByName(searchString).shouldHave(text(productPrice));
    }

    @Test(testName = "Add product to bucket")
    @Description("Добавление товара в корзину")
    @Parameters({"productTitle", "productPrice", "productCount", "orderSummaryBucket", "orderTableHeadersBucket"})
    public void addProductToBucket(String productName, String productPrice, String productCount, String orderSummary,
                                   String headerNames) {
        loginPage.open();
        searchPage.startSearch(productName);
        SelenideElement product = searchPage.getProductByName(productName);
        searchPage.getAddToBucket(product).click();
        // проверка окна корзины
        bucketPage.header().shouldHave(text("Корзина"));
        bucketPage.clearButton().shouldBe(visible);
        bucketPage.orderButton().shouldBe(visible);
        bucketPage.continueShoppingButton().shouldBe(visible);
        // header таблицы заказов
        Assert.assertEquals(bucketPage.allHeaders(), headerNames,
                "Названия таблицы заказов в корзине не соотвествует ожидаемым");
        // заказ: наименование, количество, цена
        bucketPage.allOrders().shouldHave(size(Integer.parseInt(productCount) + 2));
        bucketPage.allOrders().get(0).shouldHave(text(String.format("%s %s %s", productName, productCount,
                productPrice.substring(1))));
        // количество заказов
        bucketPage.orderCount().shouldHave(text(productCount));
        // итоговая сумма заказа
        bucketPage.orderSummary().shouldHave(text(orderSummary));
        //
    }

    @Test(testName = "Order")
    @Description("Оформление заказа")
    @Parameters({"productTitle", "productPrice", "productCount", "orderSummaryBucket", "orderTableHeadersBucket", "note"})
    public void order(String productName, String productPrice, String productCount, String orderSummary,
                      String headerNames, String comment) {
        loginPage.open();
        searchPage.startSearch(productName);
        SelenideElement product = searchPage.getProductByName(productName);
        searchPage.getAddToBucket(product).click();
        // переход к оформлению заказа
        bucketPage.orderButton().click();
        // проверка формы заказов
        orderPage.header().shouldHave(text("Оформление заказа"));
        // header таблицы заказов
        Assert.assertEquals(orderPage.allHeaders(), headerNames,
                "Названия таблицы заказов в корзине не соотвествует ожидаемым");
        // заказ: наименование, количество, цена
        orderPage.allOrders().shouldHave(size(Integer.parseInt(productCount) + 2));
        orderPage.allOrders().get(0).shouldHave(text(String.format("%s %s %s", productName, productCount,
                productPrice.substring(1))));
        // количество заказов
        orderPage.orderCount().shouldHave(text(productCount));
        // ввод комментария
        orderPage.note().setValue(comment);
        orderPage.note().shouldHave(text(comment));
        // оформление заказа
        orderPage.orderButton().click();
        // итоговая сумма заказа
        bucketPage.orderSummary().shouldHave(text(orderSummary));
    }
}
