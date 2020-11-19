package PKW;

import io.qameta.allure.Step;


import static com.codeborne.selenide.Condition.visible;

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
        Assert.assertEquals(brandVehicle, brandFromSelector);
        Assert.assertEquals(modelVehicle, modelFromSelector);
        Assert.assertEquals(motorVehicle, motorFromSelector);
        return this;
    }


    @Step("presence of linking block by brands. Tyres_page")
    public Tyres_page_Logic presenceOfBrandsLinkingBlock() {
        linkingBlockByBrands().shouldBe(visible);
        return this;
    }

    @Step("select brandbByname. Tyres_page")
    public Tyres_brand_page_Logic selectBrandByName(String brand) {
        brandByName(brand).shouldBe(visible).click();
        return page(Tyres_brand_page_Logic.class);
    }

    @Step("click on All brands button. Tyres_page")
    public Tyres_type_list_brands_page_Logic clickOnAllBrands() {
        btnAllBrands().shouldBe(visible).click();
        return page(Tyres_type_list_brands_page_Logic.class);
    }

    @Step("Click on size diameter from relink block. Tyres_page")
    public Tyres_size_page_Logic clickOnSizeDiameterFromRelinkBlock() {
        relinkBlock().scrollIntoView("{block: \"center\"}");
        sizeDiameterFromRelinkBlock().shouldBe(visible).click();
        return page(Tyres_size_page_Logic.class);

    }

}


