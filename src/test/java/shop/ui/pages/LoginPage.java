package shop.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    public LoginPage open() {
        Selenide.open("/");
        return this;
    }

    public SelenideElement accountMenu() {
        return $("div.btn-group");
    }

    public SelenideElement inputItem() {
        return $("ul.dropdown-menu > li").$(byText("Вход"));
    }

    public SelenideElement header() {
        return $("h2");
    }

    public SelenideElement login() {
        return $("input#login");
    }

    public SelenideElement loginLabel() {
        return $("label[for=\"login\"]");
    }

    public SelenideElement password() {
        return $("input#pasword");
    }

    public SelenideElement passwordLabel() {
        return $("label[for=\"pasword\"");
    }

    public SelenideElement submitButton() {
        return $("button.btn");
    }

    public SelenideElement successMessage() {
        return $("div.alert-success");
    }

    public void openLoginForm() {
        this.accountMenu().click();
        this.inputItem().click();
    }

    public void loginAsUser(String userName, String password) {
        this.login().setValue(userName);
        this.password().setValue(password);
        this.submitButton().click();
    }
}
