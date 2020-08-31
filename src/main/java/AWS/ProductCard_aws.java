package AWS;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.*;
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
        return $x("//td/span[contains(text(),'LKW')]/../following-sibling::td/input[@checked='checked']");
    }

    private SelenideElement vehicleLabel() {
        return $x("//td/span[contains(text(),'PKW')]");
    }

    private SelenideElement vehicleCheckBox() {
        return $x("//td/span[contains(text(),'PKW')]/../following-sibling::td/input[@checked='checked']");
    }

    private SelenideElement motoLabel() {
        return $x("//td/span[contains(text(),'MOTO')]");
    }

    private SelenideElement motoCheckBox() {
        return $x("//td/span[contains(text(),'MOTO')]/../following-sibling::td/input");
    }

    private SelenideElement universalApplicabilityCheckbox() { return $(".select-all-applicability"); }

    private SelenideElement pkwApplicabilityCheckbox() { return $x("//*[@class='label label-danger' and contains (text(),'PKW')]/../..//input"); }

    String productId;

    public ProductCard_aws() {
    }

    public ProductCard_aws(String productId) {
        this.productId = productId;
    }

    public SelenideElement searchTextOnPage(String textForSearch) {
        return $x("//*[contains(text(),'" + textForSearch + "')]");

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
        return this;
    }

    @Step("Check alternatives in AWS")
    public ProductCard_aws checkAlternativesInAws(ArrayList articlesToCheck) {
        new Login_aws().loginInAwsWithOpen();
        open("https://aws.autodoc.de/products/view/7868162");
        for (int i = 0; i < articlesToCheck.size(); i++) {
            searchTextOnPage(articlesToCheck.get(i).toString()).shouldBe(visible);
        }
        return this;
    }

    @Step("check truck label")
    public ProductCard_aws checkTruckLabel() {
        truckLabel().shouldBe(visible);
        truckCheckBox().shouldBe(visible);
        return this;
    }

    @Step("check vehicle label. ProductCard_aws")
    public ProductCard_aws checkVehicleLabel() {
        vehicleLabel().shouldBe(visible);
        vehicleCheckBox().shouldBe(visible);
        return this;
    }

    @Step("check motorcycle label. ProductCard_aws")
    public ProductCard_aws checkMotoLabel() {
        motoLabel().shouldBe(visible);
        motoCheckBox().shouldHave(attribute("checked", "checked"));
        return this;
    }

    @Step("Check universal applicability of product. ProductCard_aws")
    public boolean checkUniversalApplicabilityOfProduct() {
        return universalApplicabilityCheckbox().is(selected);
    }

    @Step("Check PKW applicability of product. ProductCard_aws")
    public ProductCard_aws checkPKWApplicabilityOfProduct() {
        pkwApplicabilityCheckbox().shouldBe(selected);
        return this;
    }

    @Step("Check universal or PKW applicability. ProductCard_aws")
    public ProductCard_aws checkUniversalOrPKWApplicability() {
        if (!checkUniversalApplicabilityOfProduct()) {
            checkPKWApplicabilityOfProduct();
        }
            return this;
    }

}
