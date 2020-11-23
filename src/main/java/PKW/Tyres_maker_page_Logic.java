package PKW;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class Tyres_maker_page_Logic extends Tyres_maker_page {

    @Step("presence of linking block by brands. Tyres_maker_page")
    public Tyres_maker_page_Logic presenceOfBrandsLinkingBlock() {
        linkingBlockByBrands().shouldBe(visible);
        return this;
    }

    @Step("select brandbByname. Tyres_maker_page")
    public Tyres_brand_page_Logic selectBrandByName(String brand) {
        brandByName(brand).shouldBe(visible).click();
        return page(Tyres_brand_page_Logic.class);
    }

    @Step("click on All brands button. Tyres_maker_page")
    public Tyres_type_list_brands_page_Logic clickOnAllBrands() {
        btnAllBrands().shouldBe(visible).click();
        return page(Tyres_type_list_brands_page_Logic.class);
    }

    @Step("Check presence relink by car block. Tyres_maker_page")
    public Tyres_maker_page_Logic checkPresenceRelinkByCarBlock() {
        relinkByCarBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        return this;
    }

    @Step("Click on model in relink by car block. Tyres_maker_page")
    public Tyres_maker_page_Logic clickOnModelInRelinkByCarBlock() {
        modelFromRelinkByCarBlock().click();
        return this;
    }

    @Step("Click btn more in relink by car block. Tyres_maker_page")
    public Tyres_type_list_makers_page_Logic clickBtnMoreInRelinkByCarBlock() {
        btnMoreFromRelinkByCarBlock().shouldBe(visible).click();
        return page(Tyres_type_list_makers_page_Logic.class);
    }

    @Step("Get url model in relink by car block. Tyres_maker_page")
    public String getUrlModelInRelinkByCarBlock() {
        return modelFromRelinkByCarBlock().getAttribute("url");
    }

    @Step("Get url btn more in relink by car block. Tyres_maker_page")
    public String getUrlBtnMorelInRelinkByCarBlock() {
        return btnMoreFromRelinkByCarBlock().getAttribute("href");
    }


}
