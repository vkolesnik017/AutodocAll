package PKW;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;


public class Tyres_page_Logic extends Tyres_page {

    @Step("Check presence car tabs on page. Tyres_page")
    public Tyres_page_Logic checkPresenceCarTabs() {
        carTabs().shouldBe(visible);
        return this;
    }

    @Step("Checks the entered vehicle data with the data in the selector. Tyres_page")
    public Tyres_page_Logic checkDataAddedVehicleWithDataInSelector(String brandVehicle, String modelVehicle, String motorVehicle) {
        activeMotorInput().shouldBe(visible);
        String brandFromSelector = brandInput().getValue().toUpperCase();
        String modelFromSelector = modelInput().getValue().toUpperCase();
        String motorFromSelector = motorInput().getValue().toUpperCase();
        Assert.assertEquals(brandVehicle,brandFromSelector);
        Assert.assertEquals(modelVehicle,modelFromSelector);
        Assert.assertEquals(motorVehicle,motorFromSelector);
        return this;
    }

    @Step("Click on size diameter from relink block. Tyres_page")
    public Tyres_size_page_Logic clickOnSizeDiameterFromRelinkBlock() {
        relinkBlock().scrollIntoView("{block: \"center\"}");
        sizeDiameterFromRelinkBlock().shouldBe(visible).click();
        return page(Tyres_size_page_Logic.class);
    }

}
