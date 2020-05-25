package ATD;

import static com.codeborne.selenide.Condition.*;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.CollectionCondition.size;


public class Index_chemicals_page_Logic extends Index_chemicals_page {



    @Step("Check presence block subcategories after hover on catalog category. Index_chemicals_page")
    public Index_chemicals_page_Logic checkLogicalUnion(){
        catalogFirstGroup().hover();
        sleep(2000);
        catalogCategories().shouldBe(visible);
        return this;
    }


    @Step("Check presence of top brands block. Index_chemicals_page")
    public Index_chemicals_page_Logic checkingPresenceOfTopBrandsBlock() {
        blockTopBrands().shouldBe(visible);
        return this;
    }


    @Step("Checks the presence of a block with site features . Index_chemicals_page")
    public Index_chemicals_page_Logic checkingPresenceOfFeaturesBlock() {
        blockFeatures().shouldBe(visible);
        return this;
    }

    @Step("Checks the presence of the top products block.Index_chemicals_page")
    public Index_chemicals_page_Logic checkingPresenceOfTopProductBlock() {
        blockTopProducts().shouldBe(visible);
        return this;
    }

    @Step("Checks the number of products in top block.  Index_chemicals_page")
    public Index_chemicals_page_Logic checkingTheQuantityOfGoods (int expectedSize) {
        productsInTopBlock().shouldHave(size(expectedSize));
        return this;
    }

    @Step("Checking block Advantages and popup describing the Advantage. Index_chemicals_page")
    public Index_chemicals_page_Logic checkingPresenceOfAdvantagesBlockAndAdvantageDescription() {
        blockAdvantages().shouldBe(visible);
        secondAdvantageInBlockAdvantages().hover();
        blockAdvantagesPopup().shouldBe(visible);
        return this;
    }

    @Step("Get name first product in block top products. Index_chemicals_page ")
    public String getNameProductInBlockTopProducts (){
        return nameFirstProductInBlockTopProducts().getText();
    }

    @Step("Checking transition on product page after click on button details in popup product block top products. Index_chemicals_page ")
    public Product_page_Logic checkingTransitionOnProductPageAfterClickDetails() {
        nameFirstProductInBlockTopProducts().hover();
        detailsBtnInPopupInTopProductBlock().click();
        return page(Product_page_Logic.class);

    }



}
