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

    @Step("Check presence top tyres block. Tyres_maker_page")
    public Tyres_maker_page_Logic checkPresenceTopTyresBlock() {
        topTyresBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        return this;
    }

    @Step("Check size products from top tyres block. Tyres_maker_page")
    public Tyres_maker_page_Logic checkSizeProductsFromTopBlock() {
        productsFromTopBlock().shouldHaveSize(12);
        return this;
    }

    @Step("Click btn add to basket from top block. Tyres_maker_page")
    public Tyres_maker_page_Logic clickBtnAddToBasketFromTopBlock() {
        btnAddToBasketFromTopBlock().click();
        popupBasketAddedProducts().waitUntil(attribute("style","visibility: visible; opacity: 1;"), 10000);
        return this;
    }

    @Step("Get model product from top products block. Tyres_maker_page")
    public String getModelFromTopProductsBlock() {
        return modelProductFromTopBlock().getText();
    }

    @Step("Get info text from popup cart. Tyres_maker_page")
    public String getInfoTextFromPopupCart() {
        iconCart().hover();
        return productFromPopupCart().getText();
    }

    @Step("Click btn details in popup from top tyres block. Tyres_maker_page")
    public Tyres_item_page_Logic clickBtnDetailsInPopupFromTopBlock() {
        productFromTopBlock().hover();
        btnDetailsInPopupFromTopBlock().shouldBe(visible).click();
        return page(Tyres_item_page_Logic.class);
    }

    @Step("presence of TOP product block. Tyres_maker_page")
    public Tyres_maker_page_Logic presenceOfTopProductBlock() {
        topProductsBlock().shouldBe(visible);
        return this;
    }

    @Step("Get url from btn wheel Dimensions in relink by size block . Tyres_maker_page")
    public String getUrlBtnWheelDimensionsInRelinkBlockBySize() {
        return wheelDimensionsFromRelinkBlock().getAttribute("href");
    }

    @Step("Get text from btn wheel Dimensions in relink by size block . Tyres_maker_page ")
    public String getTextBtnWheelDimensionsInRelinkBlockBySize() {
        return wheelDimensionsFromRelinkBlock().getText();
    }

    @Step("Click on Wheel Dimensions from relink block. Tyres_maker_page")
    public Tyres_dimension_maker_page_Logic clickOnWheelDimensionsFromRelinkBlock() {
        relinkBlockBySize().scrollIntoView("{block: \"center\"}");
        wheelDimensionsFromRelinkBlock().shouldBe(visible).click();
        return page(Tyres_dimension_maker_page_Logic.class);
    }


    @Step("check size of TOP products. Tyres_maker_page")
    public Tyres_maker_page_Logic checkSizeOfTopProducts(int size) {
        Assert.assertTrue(imageOfBrandAtTopProducts().size() <= size);
        return this;
    }

    @Step("checking the ability to add an item to the cart. Tyres_maker_page")
    public Tyres_maker_page_Logic checkAbilityToAddTopProductToCart() {
        for (int i = 0; i < btnAddTopProductToBasket().size(); i++) {
            btnAddTopProductToBasket().get(i).shouldHave(attribute("data-ga-action", "Add_to_basket"));
        }
        return this;
    }
}
