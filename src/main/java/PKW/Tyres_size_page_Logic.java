package PKW;


import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.*;

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


}
