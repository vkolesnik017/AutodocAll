package ATD;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Retouren_page {

    SelenideElement indexField() {
        return $(byId("form_index"));
    }

    SelenideElement idOrderField() {
        return $(byId("form_orderId"));
    }

    SelenideElement suchenButton() {
        return $(".returnOrder");
    }

    // locators for REKLAMATION block
    ElementsCollection causesReturnInSelect() {
        return $$x("//select[@name='causes']/option[position()>1]");
    }

    SelenideElement selectWithCausesReturn() {
        return $x("//select[@name='causes']");
    }

    SelenideElement checkboxesToProducts() {
        return $x("//label[@class='checkbox color']//span");
    }

    SelenideElement selectMountedOrNot() {
        return $x("//select[@name='mounting']");
    }

    ElementsCollection optionsInSelectMountedOrNot() {
        return $$x("//select[@name='mounting']/option[position()>1]");
    }

    SelenideElement formForMessage() {
        return $(byId("form_message"));
    }

    SelenideElement fileBlock() {
        return $x("//label[@class='file-block']");
    }

    SelenideElement inputFileBlock() {
        return $x("//label[@class='file-block']/input");
    }

    SelenideElement sendenButton() {
        return $(".inside-application__submit");
    }

    SelenideElement successPopup() {
        return $x("//div[@class='popup ']//h3[text()='Vielen Dank!']");
    }

    SelenideElement errorPopupForReturn() {
        return $x("//div[@id='popup_update']//h3[text()='Fehler']");
    }

    SelenideElement closePopupButton() {
        return $x("//div[@id='popup_update']//*[@class='close']");
    }

    SelenideElement productPriceForReturn() {
        return $x("//div[contains(@class,'popup-retoure__wrap')]//td[4]");
    }

}
