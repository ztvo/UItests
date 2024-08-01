package shop.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchPage {

    public SelenideElement searchField() {
        return $(By.id("typeahead"));
    }

    public SelenideElement searchButton() {
        return $("input[type=\"submit\"]");
    }

    public SelenideElement searchTitle() {
        return $(By.xpath("//div [@class=\"breadcrumbs-main\"]/ol/li[2]"));
    }

    public ElementsCollection searchResult() {
        return $$("div.col-md-4");
    }

    public SelenideElement bucketButton() {
        return $("input[type=\"submit\"]");
    }

    public SelenideElement getAddToBucket(SelenideElement product) {
        return product.$("a.add-to-cart-link");
    }

    public SelenideElement getProductTitleByName(String productName) {
        String xPath = String.format("//div[@class='col-md-4 product-left p-left']/div/div[1]/h3[text()='%s']", productName);
        return $(By.xpath(xPath));
    }

    public SelenideElement getProductPriceByName(String productName) {
        String xPath = String.format("//div[@class='col-md-4 product-left p-left']/div/div[1]/h3[text()='%s']/following-sibling::h4/span", productName);
        return $(By.xpath(xPath));
    }

    public SelenideElement getProductByName(String productName) {
        String xPath = String.format("//div[@class='col-md-4 product-left p-left']/div/div[1]/h3[text()='%s']/../../..", productName);
        return $(By.xpath(xPath));
    }

    public void startSearch(String searchCriteria) {
        this.searchField().click();
        this.searchField().clear();
        this.searchField().setValue(searchCriteria);
        this.searchButton().click();
    }

}
