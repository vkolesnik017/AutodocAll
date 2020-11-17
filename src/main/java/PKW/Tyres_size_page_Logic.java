package PKW;


import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class Tyres_size_page_Logic extends Tyres_size_page {

    @Step(": from. Tyres_size_page")
    public Tyres_size_page_Logic checkDataAddedVehicleWithDataInSelector(String brandVehicle, String modelVehicle, String motorVehicle) {
        new Tyres_page_Logic().checkDataAddedVehicleWithDataInSelector(brandVehicle, modelVehicle, motorVehicle);
        return this;
    }

    @Step("Check presence main products block. Tyres_size_page")
    public Tyres_size_page_Logic checkPresenceMainProductsBlock() {
        blockWithProducts().shouldBe(visible);
        return this;
    }

    @Step(":from. Tyres_size_page")
    public Tyres_size_page_Logic clickBtnAddToBasket() {
        new Car_tires_page_Logic(). clickBtnAddToBasket();
        return this;
    }

    @Step(":from. Tyres_size_page")
    public Cart_page_Logic cartClick() {
        new Main_page_Logic().cartClick();
        return page(Cart_page_Logic.class);
    }



}
