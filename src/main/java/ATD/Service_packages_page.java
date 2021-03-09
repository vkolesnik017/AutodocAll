package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class Service_packages_page {

    ElementsCollection priceValueForPeriod() {return $$(".atd-plus-services__item-price span");}

    SelenideElement monthlyPriceSwitch() {return $x("//div[@class='switch js--atd-plus-services-switch']//span[text()='monatlich']");}

    SelenideElement btnMoreOfGeneralConditionBlock() {return $(".atd-plus-return__more button");}

    SelenideElement cancellationForm() {return $x("//p[text()='– Ende der Widerrufsbelehrung –']/preceding-sibling::img");}

    SelenideElement btnActiveBasicPackage() {return $x("//div[contains(text(),'Basis')]/..//a");}

    SelenideElement registrationPopUp() {return $x("//div[@class='autodoc_login_popup popup_login pass']");}
}
