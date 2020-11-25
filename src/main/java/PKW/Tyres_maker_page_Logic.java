package PKW;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;

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

    @Step("Click on size diameter from relink block. Tyres_maker_page")
    public Tyres_size_page_Logic clickOnSizeDiameterFromRelinkBlock() {
        relinkBlockBySize().scrollIntoView("{block: \"center\"}");
        sizeDiameterFromRelinkBlock().shouldBe(visible).click();
        return page(Tyres_size_page_Logic.class);
    }

    @Step("check presence relink block by size. Tyres_maker_page")
    public Tyres_maker_page_Logic checkPresenceRelinkBlockBySize() {
        relinkBlockBySize().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        return this;
    }

    @Step("Get url from btn size in relink by size block . Tyres_maker_page")
    public String getUrlBtnSizeInRelinkBlockBySize() {
        return sizeDiameterFromRelinkBlock().getAttribute("href");
    }

    @Step("Get text from btn size in relink by size block . Tyres_maker_page")
    public String getTextBtnSizeInRelinkBlockBySize() {
        return sizeDiameterFromRelinkBlock().getText();
    }

    @Step("Click btn all size in relink by size block. Tyres_maker_page")
    public Tyres_type_list_page_Logic clickBtnAllSizeInRelinkBySizeBlock() {
        btnAllSizeFromRelinkBySizeBlock().shouldBe(visible).click();
        return page(Tyres_type_list_page_Logic.class);
    }

    @Step("Check presence premium car block. Tyres_maker_page")
    public Tyres_maker_page_Logic checkPresencePremiumCarBlock() {
        premiumCarBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        return this;
    }

    @Step("Click second dot pagination in premium block. Tyres_maker_page")
    public Tyres_maker_page_Logic clickSecondDotInPremiumBlock() {
        secondDotPaginationInPremiumBlock().click();
        return this;
    }

    @Step("Click btn Prev in premium block. Tyres_maker_page")
    public Tyres_maker_page_Logic clickBtnPrevInPremiumBlock() {
        btnPrevInPremiumBlock().click();
        return this;
    }

    @Step("Get text name active car from Premium block.Tyres_maker_page")
    public String getTexNameActiveCarFromPremiumBlock() {
        return activeCarFromPremiumBlock().shouldBe(visible).getText();
    }

    @Step("Check work btn pagination in premium block. Tyres_maker_page")
    public Tyres_maker_page_Logic checkWorkPaginationInPremiumBlock() {
        String carFirstPage = getTexNameActiveCarFromPremiumBlock();
        clickSecondDotInPremiumBlock();
        secondDotPaginationInPremiumBlock().shouldHave(attribute("class", "slick-active"));
        String carSecondPage = getTexNameActiveCarFromPremiumBlock();
        Assert.assertNotEquals(carFirstPage, carSecondPage);
        sleep(1000);
        clickBtnPrevInPremiumBlock();
        firstDotPaginationInPremiumBlock().shouldHave(attribute("class", "slick-active"));
        String againCarFirstPage = getTexNameActiveCarFromPremiumBlock();
        Assert.assertEquals(carFirstPage, againCarFirstPage);
        return this;
    }

    @Step("Click on car from premium block. Tyres_maker_page")
    public Tyres_maker_group_page_Logic clickOnCarFromPremiumBlock() {
        carFromPremiumBlock().click();
        return page(Tyres_maker_group_page_Logic.class);
    }


}
