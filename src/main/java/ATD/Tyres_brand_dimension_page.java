package ATD;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Tyres_brand_dimension_page {

    SelenideElement productListBlock() {return $x("//ul[@class='list_products']");}

    SelenideElement grayButtonByEan(String ean) {return $x("//span[contains(text(),'"+ean+"')]/ancestor::li//div[@class='button not_active']/a");}

    ElementsCollection mpnNumberOfProduct() {return $$x("//div[@class='name']/span[contains(text(),'MPN')]");}

    SelenideElement btnNextPaginator() {return $x("//span[@class='next']");}

    SelenideElement popUpNotifyAboutAvailability() {return $x("//div[@class='popup-available']");}

    SelenideElement labelOfPopUpNotifyAboutAvailability() {return $x("//label[@class='popup-available__label']");}

    SelenideElement emailFieldOfFeedBackPopUp() { return $(byId("form_AvailabilityReminder[email]")); }

    SelenideElement btnSendOfFeedBackPopUp() { return $x("//input[@class='popup-available__button']");  }

    SelenideElement btnCloseOutOfStockProductPopUp() {return $x("//div[@class='popup_top']/a");}

    ElementsCollection visibleTopTireSizeLinks() {return $$x("//div[@class='left_block two']/ul/li").filter(visible);}
}


