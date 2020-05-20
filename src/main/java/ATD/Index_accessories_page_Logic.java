package ATD;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;


import io.qameta.allure.Step;



public class Index_accessories_page_Logic extends Index_accessories_page {

    @Step("Checks the presence of a block with site features . Index_accessories_page")
    public Index_accessories_page_Logic checkingPresenceOfFeaturesBlock() {
        blockFeatures().shouldBe(visible);
        return this;
    }


    @Step("Checks the presence of the delivery service block. Index_accessories_page")
    public Index_accessories_page_Logic checkingPresenceOfDeliveryBlock() {
        blockDelivery().shouldBe(visible);
        return this;
    }

    @Step("Check presence of top brands block. Index_accessories_page" )
    public Index_accessories_page_Logic checkingPresenceOfTopBrandsBlock(){
        blockTopBrands().shouldBe(visible);
        return this;
    }

    @Step("Checks the presence of the top products block.Index_accessories_page")
    public Index_accessories_page_Logic checkingPresenceOfTopProductBlock() {
        blockTopProducts().shouldBe(visible);
        return this;
    }

    @Step("Opens a logical union and gets the category name")
    public String getCategoryName() {
        catalogFirstGroup().hover();
        return secondCategoryInLogicalUnion().getText();
    }

    @Step("Clicks on a category in a logical union")
    public Listing_accessories_page_Logic clicksOnCategory() {
        secondCategoryInLogicalUnion().click();
        return page(Listing_accessories_page_Logic.class);
    }





}
