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

    @Step("check presence relink block by seasons. Tyres_page")
    public Tyres_page_Logic checkPresenceRelinkBlockBySeasons() {
        relinkBlockBySeasons().shouldBe(visible);
        return this;
    }

    @Step("Click on winter seasons from relink block. Tyres_page")
    public Tyres_season_page_Logic clickOnWinterBlockFromRelink() {
        winterSeasonsFromRelinkBlock().click();
        return page(Tyres_season_page_Logic.class);
    }

    @Step("Get url from winter seasons in relink block. Tyres_page")
    public String getUrlFromWinterSeasonsInRelinkBlock() {
        return winterSeasonsFromRelinkBlock().getAttribute("href");
    }

    @Step("Check presence relink by car block. Tyres_page")
    public Tyres_page_Logic checkPresenceRelinkByCarBlock() {
        relinkByCarBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        return this;
    }

    @Step("Click on model in relink by car block. Tyres_page")
    public Tyres_maker_page_Logic clickOnModelInRelinkByCarBlock() {
        modelFromRelinkByCarBlock().click();
        return page(Tyres_maker_page_Logic.class);
    }

    @Step("Click btn more in relink by car block. Tyres_page")
    public Tyres_type_list_makers_page_Logic clickBtnMoreInRelinkByCarBlock() {
        btnMoreFromRelinkByCarBlock().shouldBe(visible).click();
        return page(Tyres_type_list_makers_page_Logic.class);
    }

    @Step("Get url model in relink by car block. Tyres_page")
    public String getUrlModelInRelinkByCarBlock() {
        return modelFromRelinkByCarBlock().getAttribute("href");
    }

    @Step("Get url btn more in relink by car block. Tyres_page")
    public String getUrlBtnMorelInRelinkByCarBlock() {
        return btnMoreFromRelinkByCarBlock().getAttribute("href");
    }

    @Step("check presence relink block by size. Tyres_page")
    public Tyres_page_Logic checkPresenceRelinkBlockBySize() {
        relinkBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        return this;
    }

    @Step("Get url from btn size in relink by size block . Tyres_page")
    public String getUrlBtnSizeInRelinkBlockBySize() {
        return sizeDiameterFromRelinkBlock().getAttribute("href");
    }

    @Step("Get text from btn size in relink by size block . Tyres_page ")
    public String getTextBtnSizeInRelinkBlockBySize() {
        return sizeDiameterFromRelinkBlock().getText();
    }

    @Step("Click btn all size in relink by size block. Tyres_page")
    public Tyres_type_list_page_Logic clickBtnAllSizeInRelinkBySizeBlock() {
        btnAllSizeFromRelinkBySizeBlock().shouldBe(visible).click();
        return page(Tyres_type_list_page_Logic.class);
    }


}


