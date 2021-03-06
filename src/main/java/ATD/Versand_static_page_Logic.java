package ATD;

import Common.DataBase;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ATD.CommonMethods.mailinatorMailRandom;
import static ATD.CommonMethods.openPage;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

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
        oversizeShippingBlock().shouldBe(visible);
        tabUnfoldingButton().click();
        fullPartsList().shouldBe(visible);
        tabMinimizingButton().click();
        oversizeShippingTab().click();
        oversizeCountryBlock().shouldBe(visible);
        return this;
    }

    @Step ("Pulling prices for free delivery from the text in the delivery block. Versand_static_page")
    public Float getDeliveryLimitFromText() {
        String deliveryLimit = limitForFreeDelivery().getText();
        deliveryLimit = deliveryLimit.replaceAll("[^0-9,]", "");
        return Float.valueOf(deliveryLimit.startsWith(",") ? deliveryLimit.substring(1) : deliveryLimit.split(",")[0]);
    }

    @Step("Get the delivery price of the current country. Versand_static_page")
    public float getDeliveryPriceOfCurrentCountry(String shop) throws SQLException {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", shop, "main", "staticVersand"));
        return Float.parseFloat(deliveryPrice().getText().replaceAll("[^0-9,]", "").replaceAll(",","."));
    }

    @Step("Get the delivery price. Versand_static_page")
    public float getDeliveryPrice() throws SQLException {
        return Float.parseFloat(deliveryPrice().getText().replaceAll("[^0-9,]", "").replaceAll(",","."));
    }

    @Step("Get delivery price. Versand_static_page")
    public String getDeliveryPrice(String country) throws Exception {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "staticVersand"));
        allCountriesButton().click();
        return deliveryPriceLocator(country).getText().replaceAll("[^0-9,]", "");
    }

    @Step("Get delivery price for a user with a subscription plus pro. Versand_static_page")
    public float getDeliveryPriceForUserWithSubscriptionPlusPro(String country, String mail) throws Exception {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "staticVersand"));
        allCountriesButton().click();
        float deliveryPrice = Float.parseFloat(deliveryPriceLocator(country).getText().replaceAll("[^0-9,]", "").replace(",", "."));
        float exactDeliveryPrice;
        if (mail.contains("plusPro")) {
            exactDeliveryPrice = deliveryPrice * 0.7f;
        } else {
            exactDeliveryPrice = deliveryPrice;
        }
        return exactDeliveryPrice;
    }

    @Step("Get delivery price for current country for a user with a subscription plus pro. Versand_static_page")
    public float getDeliveryPriceForCurrentCountryForUserWithSubscriptionPlusPro(String country, String mail) throws Exception {
        float deliveryPriceCurrentCountry = getDeliveryPriceOfCurrentCountry(country);
        float exactDeliveryPrice;
        if (mail.contains("plusPro")) {
            exactDeliveryPrice = deliveryPriceCurrentCountry * 0.7f;
        } else {
            exactDeliveryPrice = deliveryPriceCurrentCountry;
        }
        return exactDeliveryPrice;
    }

    @Step("Get Safe Order price discount with any subscription or without subscription. Versand_static_page")
    public float getSafeOrderPriceWithAnyDiscountAndSubscription(String mail) throws SQLException {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "staticVersand"));
        float safeOrderPrice = Float.parseFloat(soPrice2().getText().replaceAll("[^0-9,]", "").replace(",", "."));
        float safeOrderPriceWithDiscount;
        if (mail.contains("plusBasic")) {
            safeOrderPriceWithDiscount = safeOrderPrice * 0.8f;
        } else if (mail.contains("plusOptimal")) {
            safeOrderPriceWithDiscount = safeOrderPrice * 0.65f;
        } else if (mail.contains("plusPro")) {
            safeOrderPriceWithDiscount = safeOrderPrice * 0.5f;
        } else {
            safeOrderPriceWithDiscount = safeOrderPrice;
        }
        return safeOrderPriceWithDiscount;
    }

    @Step("Get delivery price for AWS. Versand_static_page")
    public Float getDeliveryPriceForAWS(String country) throws Exception {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "staticVersand"));
        allCountriesButton().click();
        String deliveryPrice = deliveryPriceLocator(country).getText().replaceAll("[^0-9,]", "").replace(",", ".");
        return Float.valueOf(deliveryPrice);
    }

    @Step("Click all countries button. Versand_static_page")
    public Versand_static_page_Logic clickAllCountriesButton() {
        allCountriesButton().click();
        return this;
    }

    @Step("Check Datenschutzerklarung Link Behavior. Versand_static_page")
    public Versand_static_page_Logic checkingDatenschutzerklarungLinkBehavior() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(datenschutzerklarungLink(), "underline solid rgb(0, 0, 0)");
        return this;
    }

    @Step("Select country in dropdown. Versand_static_page")
    public Versand_static_page_Logic selectCountry(String country) {
        countryDropdown().selectOption(country);
        Wait().until(webDriver -> countryDropdown().getSelectedText().equals(country));
        return this;
    }

    @Step("Filling fields and checking behavior of send shipping form. Versand_static_page")
    public String fillingFieldsAndCheckBehaviorSendShipForm(String country) {
        String mail = mailinatorMailRandom("1947");
        sendShipFormMailField().setValue(mail);
        selectCountry(country);
        mailingCheckbox().click();
        submitButton().click();
        sendShipFormSuccesPopup().shouldBe(appear);
        sendShipFormSuccesPopupCloseBtn().click();
        return mail;
    }

    @Step("Get the delivery price for the expected country {expectedShop}. Versand_static_page")
    public float getDeliveryPrice(String actualShop, String expectedShop, String shop) throws SQLException {
        float deliveryPrice = 0.0f;
        if (actualShop.equals(expectedShop)) {
            deliveryPrice = Float.parseFloat(deliveryPrice().getText().replaceAll("[^0-9,]", "").replaceAll(",", "."));
        } else {
            allCountriesButton().click();
            deliveryPrice = Float.parseFloat(deliveryPriceLocatorWithNameShop(shop).getText().replaceAll("[^0-9,]", "").replace(",", "."));
        }
        return deliveryPrice;
    }

    @Step("Get safe order price. Versand_static_page")
    public String getSafeOrderPrice() {
        return safeOrderPrice().scrollIntoView("{block: \"center\"}").getText().replaceAll("[^0-9,]", "").replaceAll(",",".");
    }

    @Step("Checks title for island block. Versand_static_page")
    public Versand_static_page_Logic checkPresenceTitleInIslandBlock(String expectedTitle) {
        islandsBlockTitle().shouldBe(visible).shouldHave(text(expectedTitle));
        return this;
    }

    @Step("Compares prices for shipping to islands with the price in the AWS. Versand_static_page")
    public Versand_static_page_Logic comparesPricesForShippingToIslandWithPriceInAWS(List<String> priceWithAWS) {
        ArrayList<String> priceWithFront = new ArrayList<>();
        for (int i = 0; i < islandDeliveryPrices().size(); i++) {
            priceWithFront.add(islandDeliveryPrices().get(i).getText().replaceAll("[^0-9,]", "").replaceAll(",","."));
        }
        Assert.assertEquals(priceWithAWS, priceWithFront);
        return this;
    }
}
