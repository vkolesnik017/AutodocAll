package PKW;


import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.*;


public class Car_tires_page_Logic extends Car_tires_page {

    @Step("Get value car id from vehicle selector. Car_tires_page")
    public String getValueCarIdFromVehicleSelectors() {
        return typeSelectorInVerticalCarSelector().shouldBe(visible).getValue();
    }

    @Step("Click button add to basket. Car_tires_page")
    public Car_tires_page_Logic clickBtnAddToBasket() {
        activeRedButton().click();
        popupBasketAddedProducts().waitUntil(attribute("style","visibility: visible; opacity: 1;"), 10000);
        return this;
    }



}
