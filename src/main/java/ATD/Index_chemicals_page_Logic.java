package ATD;

import static com.codeborne.selenide.Condition.*;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.sleep;


public class Index_chemicals_page_Logic extends Index_chemicals_page {



    @Step("Check presence block categories after hover on catalog group. Index_chemicals_page")
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



}
