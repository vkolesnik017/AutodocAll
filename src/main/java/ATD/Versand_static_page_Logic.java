package ATD;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static ATD.CommonMethods.openPage;
import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class Versand_static_page_Logic extends Versand_static_page {

    @Step("Checks for the presence of a TOP block and elements inside it. Versand_static_page")
    public Versand_static_page_Logic checkBlockTop(){
        topBlock().shouldBe(visible);
        topBlockLeft().shouldBe(visible);
        topBlockRight().shouldBe(visible);
        deliveryCompanyImages().shouldBe(visible);
        return this;
    }

    @Step("Checks for the presence of a Prices block and elements inside it. Versand_static_page")
    public Versand_static_page_Logic checkPricesBlock(){
        pricesBlock().shouldBe(visible);
        deliveryPriceBlock().shouldBe(visible);
        vatBlock().shouldBe(visible);
        return this;
    }

    @Step("Checks for the presence of a Country Prices block and elements inside it. Versand_static_page")
    public Versand_static_page_Logic checkCountryPricesBlock(){
        countryPricesBlock().shouldBe(visible);
        countyPriceDelivery().shouldHave(size(3));
        allCountriesButton().click();
        allCountriesBlock().shouldBe(visible);
        allCountriesButton().click();
        allCountriesBlock().shouldNotBe(visible);
        return this;
    }

    @Step("Checks for the presence of a Delivery Time block and elements inside it. Versand_static_page")
    public Versand_static_page_Logic checkDeliveryTimeBlock(){
        deliveryTimeBlock().shouldBe(visible);
        textDelivery().shouldBe(visible);
        greenDeliveryBlock().shouldBe(visible);
        redDeliveryBlock().shouldBe(visible);
        deliveryLink().shouldBe(visible);
        return this;
    }

    @Step("Checks for the presence of a Recommendation block. Versand_static_page")
    public Versand_static_page_Logic checkRecommendationBlock(){
        recommendationBlock().shouldBe(visible);
        return this;
    }

    @Step("Checks the functionality of the checkbox. Versand_static_page")
    private Versand_static_page_Logic checkUncheckCheckbox(SelenideElement checkLocator, SelenideElement clickLocator) {
        $(checkLocator).shouldNotBe(selected);
        $(checkLocator).shouldNotBe(checked);
        $(clickLocator).click();
        $(checkLocator).shouldBe(selected);
        $(checkLocator).shouldBe(checked);
        $(clickLocator).click();
        $(checkLocator).shouldNotBe(selected);
        $(checkLocator).shouldNotBe(checked);
        return this;
    }

    @Step("Checks for the presence of a Safe Order block and elements inside it. Versand_static_page")
    public Versand_static_page_Logic checkSafeOrderBlock(){
        safeOrderBlock().shouldBe(visible);
        checkUncheckCheckbox(deliveryCheckbox(), deliveryCheckboxClick());
        soDays().shouldHave(text("14"));
        soDaysBig().shouldHave(text("200"));
        soTime().shouldHave(text("14"));
        soPrice1().shouldHave(text("0"));
        soPrice2().shouldHave(text("2,99"));
        soDays2().shouldHave(text("14"));
        soDays3().shouldHave(text("200"));
        soText().shouldBe(visible);
        soReturnText().shouldBe(visible);
        return this;
    }

    @Step("Checks for the presence of a Tyres Delivery block and elements inside it. Versand_static_page")
    public Versand_static_page_Logic checkTyresDeliveryBlock(){
        tyresDelivery().shouldBe(visible);
        tyresDeliveryRowItemFree().shouldBe(visible);
        tyresDeliveryRowItemOrder().shouldBe(visible);
        tyresDeliveryRowItemPoint().shouldBe(visible);
        return this;
    }

    @Step("Checks for the presence of a Oversize shipping product block and elements inside it. Versand_static_page")
    public Versand_static_page_Logic checkOversizeShippingProductBlock(){
        shippingBlock().shouldBe(visible);
        tabUnfoldingButton().click();
        fullPartsList().shouldBe(visible);
        tabMinimizingButton().click();
        shippingTab2().click();
        countryBlock().shouldBe(visible);
        return this;
    }

    @Step("Checks for the presence of a Choose Delivery block and elements and options inside it. Versand_static_page")
    public Versand_static_page_Logic checkChooseDeliveryBlock(){
        chooseDeliveryBlock().shouldBe(visible);
        chooseDeliveryTitle().shouldBe(visible);
        checkUncheckCheckbox(glsCheckbox(), glsCheckboxClick());
        checkUncheckCheckbox(dhlCheckbox(), dhlCheckboxClick());
        checkUncheckCheckbox(noxCheckbox(), noxCheckboxClick());
        checkUncheckCheckbox(dpdCheckbox(), dpdCheckboxClick());
        checkUncheckCheckbox(pnordCheckbox(), pnordCheckboxClick());
        chooseDeliveryInput().sendKeys("Test Delivery");
        chooseDeliveryButton().click();
        chooseDeliveryAnswerText().shouldHave(text("Danke für Ihre Antwort! Wir tun unser Bestes, um Ihnen Ersatzteile auf den bequemsten Weg zu liefern."));
        deliveryOptionsBlock().shouldNotBe(visible);
        chooseDeliveryInput().shouldNotBe(visible);
        return this;
    }

    @Step ("Pulling prices for free delivery from the text in the delivery block. Versand_static_page")
    public Float getDeliveryLimitFromText() {
        String deliveryLimit = limitForFreeDelivery().getText();
        deliveryLimit = deliveryLimit.replaceAll("[^0-9,]", "");
        return Float.valueOf(deliveryLimit.startsWith(",") ? deliveryLimit.substring(1) : deliveryLimit.split(",")[0]);
    }

    @Step("Get delivery price to UK for alldata. Versand_static_page")
    public String deliveryPriceToUK() throws Exception {
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "staticVersand"));
        allCountriesButton().click();
        return deliveryPriceForUKlocator().getText().replace(" €", "");
    }

    @Step("Get delivery price to UK for AWS. Versand_static_page")
    public Float deliveryPriceToUKforAWS() throws Exception {
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "staticVersand"));
        allCountriesButton().click();
        String deliveryPrice = deliveryPriceForUKlocator().getText().replace(" €", "").replace(",", ".");
        Float deliveryPriceToUKforAWS = Float.parseFloat(deliveryPrice);
        return deliveryPriceToUKforAWS;
    }
}
