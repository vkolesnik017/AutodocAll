package AWS;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class ProductCard_aws {
    private SelenideElement loginField() {
        return $(byId("login"));
    }

    private SelenideElement truckLabel() {
        return $x("//td/span[contains(text(),'LKW')]");
    }

    private SelenideElement truckCheckBox() {
        return $x("//td/span[contains(text(),'LKW')]/../following-sibling::td/input");
    }

    String productId;


    public ProductCard_aws(String productId) {
        this.productId = productId;
    }

    @Step("get current Url")
    public String currentUrl() {
        String url = "https://aws.autodoc.de/products/view/" + productId + "";
        return url;
    }

    @Step(" open product card page")
    public ProductCard_aws openProductCardPageAndLogin() {
        open(currentUrl());
        if (loginField().isDisplayed()) {
            new Login_aws().loginInAws();
        }
        truckLabel().shouldBe(visible);
        truckCheckBox().shouldHave(attribute("checked", "checked"));
        return this;
    }
}
