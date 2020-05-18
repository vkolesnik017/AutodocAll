package ATD;

import static com.codeborne.selenide.Condition.*;
import io.qameta.allure.Step;



public class Index_accessories_page_Logic extends Index_accessories_page {

    @Step("Checks the visibility of a block with site features . Index_accessories_page")
    public Index_accessories_page_Logic checkVisibilityOfFeaturesBlock() {
        blockFeatures().shouldBe(visible);
        return this;
    }


    @Step("Checks the visibility of the delivery service block. Index_accessories_page")
    public Index_accessories_page_Logic checkingVisibilityOfDeliveryBlock() {
        blockDelivery().shouldBe(visible);
        return this;
    }

    @Step("Check presence of top brands block. Index_accessories_page" )
    public Index_accessories_page_Logic checkingOfTopBrandsBlock(){
        blockTopBrands().shouldBe(visible);
        return this;
    }

    @Step("Checks the visibility of the top products block.Index_accessories_page")
    public Index_accessories_page_Logic checkingOfTopProductBlock() {
        blockTopProducts().shouldBe(visible);
        return this;
    }



}
