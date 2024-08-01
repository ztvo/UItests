package shop.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class OrderPage {
    public SelenideElement header() {
        return $("h2");
    }

    public SelenideElement noteLabel() {
        return $(byText("Note"));
    }

    public SelenideElement note() {
        return $("textarea[name=\"note\"]");
    }
    public SelenideElement orderButton() {
        return $(byText("Оформить"));
    }

    public SelenideElement orderCountLabel() {
        return $(byText("Итого"));
    }

    public SelenideElement orderCount(){
        return $(".cart-qty");
    }

    public SelenideElement orderSummaryLabel() {
        return $(byText("На сумму"));
    }

    public SelenideElement orderSummary() {
        return $(".cart-sum");
    }

    public String allHeaders() {
        return $("div.product-one table  thead  tr").getText();
    }

    public ElementsCollection allOrders() {
        return $$("div.product-one table tbody tr");
    }
}
