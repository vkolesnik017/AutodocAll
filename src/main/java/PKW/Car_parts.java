package PKW;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;


public class Car_parts {

    SelenideElement linkDatenschutzerklärungInSoft404Block() {
        return $x("//label[@id='privacy_policy1']/a");
    }

    SelenideElement soft404Block() {
        return $x("//div[@class='notification-form-block']");
    }

    SelenideElement subscriptionMailFieldInSoft404Block() {
        return $(byXpath("//input[@id='form_Soft404[subscribe-email]']"));
    }

    SelenideElement subscriptionMailCheckboxInSoft404Block() {
        return $(byXpath("//div[@class='check-row']/input[@id='subscribe_on']"));
    }

    SelenideElement subscriptionButtonInSoft404Block() {
        return $(byXpath("//button[@class='subscribe-btn']"));
    }

    SelenideElement subscriptionSuccessPopup() {
        return $(byXpath("//div[@id='popup_update']//div[@class='txt']"));
    }

    SelenideElement subscriptionPopupClose() {
        return $(byXpath("//div[@class='popup ']//*[@class='close']/span[2]"));
    }

    SelenideElement productListBlock() {return $x("//div[@class='listing_items']");}

    SelenideElement forwardLinkOfPaginator() { return $x("//span[@class='next'][1]/a");}

    ElementsCollection visibleTitleOfProducts() {return $$x("//*[self::a[@class='ga-click'] or self::span[@class='ga-click link']]");}

    ElementsCollection allCharacteristicsOfProducts() {return $$x("//div[@class='prod_params_container']//li");}

    ElementsCollection visibleArtNumOfProduct() {return $$x("//div[@class='nr']/span");}

    ElementsCollection visibleCharacteristicsOfProducts(int position) {return $$x("(//div[@class='prod_params_container'])["+position+"]/ul/li");}

    SelenideElement criteriaFrontAxle() {
        return $x("//div[@data-filter-id='criteria_100']//input[@id='b055d5']/..//span");
    }

    SelenideElement criteriaDiameterFromSidebar() {
        return $x("//div[@data-filter-id='criteria_200']//label");
    }

    public SelenideElement criteriaPRNumberInSidebar() {
        return $x("//div[@data-param-id='1197']//div[@class='name']");
    }

    SelenideElement criteriaBremsscheibenartFromSidebar() {
        return $x("//div[@data-filter-id='criteria_232']//div[@class='name']/span");
    }

    public ElementsCollection characteristicAxleSideFomProductBlock() {
        return $$x("//div[@class='listing_items']//span[contains(text() ,'Einbauseite')]/../span[contains(@class,'rc')]");
    }


    public ElementsCollection characteristicDiameterFomProductBlock() {
        return $$x("//div[@class='listing_items']//span[contains(text() ,'Durchmesser')]/../span[contains(@class,'rc')]");
    }

    public ElementsCollection characteristicPRNumberFomProductBlock() {
        return $$x("//div[@class='listing_items']//span[contains(text() ,'PR-Nummer')]/../span[contains(@class,'rc')]");
    }

    public ElementsCollection characteristicBremsscheibenartFomProductBlock() {
        return $$x("//div[@class='listing_items']//span[contains(text() ,'Bremsscheibenart')]/../span[contains(@class,'rc')]");
    }



}
