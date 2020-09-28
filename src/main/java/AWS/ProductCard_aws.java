package AWS;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    private SelenideElement universalApplicabilityCheckbox() {
        return $(".select-all-applicability");
    }

    private SelenideElement pkwApplicabilityCheckbox() {
        return $x("//*[@class='label label-danger' and contains (text(),'PKW')]/../..//input");
    }

    SelenideElement dangerousIconOfProductBlock() {
        return $x("//div[@class='col-md-12 col-sm-12 clearfix']");
    }

    ElementsCollection iconIfDangerousProducts() {
        return $$x("//div[@class='col-md-12 col-sm-12 clearfix']//input[@checked='checked']/../img");
    }

    SelenideElement signalAttentionCheckBox() {
        return $x("//input[@value='isAttention']");
    }

    SelenideElement signalDangerousCheckBox() {
        return $x("//input[@value='isDanger']");
    }

    SelenideElement activeSwitchOfDangerousProduct() {return $x("//div[@class='col-md-6 col-sm-6'][2]//div[@class='switch-animate switch-on']/span[1]");}


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

    @Step("presence of dangerous icon block. ProductCard_aws")
    public ProductCard_aws presenceOfDangerousIconBlock() {
        dangerousIconOfProductBlock().shouldBe(visible);
        return this;
    }

    @Step("compare elements of Dangerous product. ProductCard_aws")
    public ProductCard_aws compareElementsOfDangerousProduct(List<String> listOfDangerousIconFromProduct, String signalWord) {
        if (signalWord.toUpperCase().equals("ACHTUNG!")) {
            signalAttentionCheckBox().shouldHave(attribute("checked", "true"));
        } else if (signalWord.toUpperCase().equals("GEFAHR!")) {
            signalDangerousCheckBox().shouldHave(attribute("checked", "true"));
        }
        List<String> dangerousIconFromAws = iconIfDangerousProducts().stream().map(n -> n.getAttribute("src").replace("pkwteile","autodoc")).collect(Collectors.toList());
        Assert.assertEquals(dangerousIconFromAws, listOfDangerousIconFromProduct);
        activeSwitchOfDangerousProduct().shouldBe(exist).shouldHave(text("ON"));
        return this;
    }

}
