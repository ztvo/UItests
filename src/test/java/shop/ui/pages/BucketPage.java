package shop.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BucketPage {
    public SelenideElement header() {
        return $("div.modal-header").$("h4");
    }

    public SelenideElement continueShoppingButton() {
        return $("div.modal-content").$(byText("Продолжить покупки"));
    }

    public SelenideElement orderButton() {
        return $("div.modal-content").$(byText("Оформить заказ"));
    }

    public SelenideElement clearButton() {
        return $("div.modal-content").$(byText("Очистить корзину"));
    }

    public SelenideElement orderCountLabel() {
        return $("div.modal-content").$(byText("Итого"));
    }

    public SelenideElement orderCount(){
        return $("div.modal-content").$(".cart-qty");
    }

    public SelenideElement orderSummaryLabel() {
        return $("div.modal-content").$(byText("На сумму"));
    }

    public SelenideElement orderSummary() {
        return $("div.modal-content .cart-sum");
    }

    public String allHeaders() {
        return $("div.modal-content  table  thead  tr").getText();
    }

    public ElementsCollection allOrders() {
        return $$("div.modal-content table tbody tr");
    }
}
