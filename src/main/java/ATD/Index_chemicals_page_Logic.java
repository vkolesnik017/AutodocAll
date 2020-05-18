package ATD;

import static com.codeborne.selenide.Condition.*;
import io.qameta.allure.Step;
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
    public Index_chemicals_page_Logic checkingOfTopBrandsBlock() {
        blockTopBrands().shouldBe(visible);
        return this;
    }


    @Step("Checks the visibility of a block with site features . Index_chemicals_page")
    public Index_chemicals_page_Logic checkVisibilityOfFeaturesBlock() {
        blockFeatures().shouldBe(visible);
        return this;
    }

    @Step("Checks the visibility of the top products block.Index_chemicals_page")
    public Index_chemicals_page_Logic checkingOfTopProductBlock() {
        blockTopProducts().shouldBe(visible);
        return this;
    }

    @Step("Checks the number of products in top block.  Index_chemicals_page")
    public Index_chemicals_page_Logic checkingTheQuantityOfGoods (int expectedSize) {
        productsInTopBlock().shouldHave(size(expectedSize));
        return this;
    }



}
