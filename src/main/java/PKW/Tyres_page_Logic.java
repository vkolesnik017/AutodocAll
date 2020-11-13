package PKW;


import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.visible;


public class Tyres_page_Logic extends Tyres_page {

    @Step("Check presence car tabs on page. Tyres_page")
    public Tyres_page_Logic checkPresenceCarTabs() {
        carTabs().shouldBe(visible);
        return this;
    }

    @Step("Checks the entered vehicle data with the data in the selector. Tyres_page")
    public Tyres_page_Logic checkDataAddedVehicleWithDataInSelector() {

        String brand = brandInput().getText().toUpperCase();
        String model = modelInput().getText().toUpperCase();
        String motor = motorInput().getText().toUpperCase();
        return this;
    }

}
