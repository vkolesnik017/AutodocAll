package ATD;

import static com.codeborne.selenide.Condition.*;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.sleep;


public class Index_chemicals_page_Logic extends Index_chemicals_page {



    @Step("Check presence Logical Union on main page chemie . Index_chemicals_page")
    public Index_chemicals_page_Logic CheckLogicalUnion(){
        GroupLogicalUnion_MainProductCatalog().hover();
        sleep(2000);
        LogicalUnion_MainProductCatalog().shouldBe(visible);
        return this;
    }


    @Step("Check presence div with top brands. Index_chemicals_page")
    public Index_chemicals_page_Logic CheckDivTopBrands() {
        DivTopBrands().shouldBe(visible);
        return this;
    }



}
