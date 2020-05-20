package ATD;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;

public class Moto_Catalog_model_page_Logic extends Moto_Catalog_model_page {

    @Step("availability of moto selector .Moto_Catalog_model_page")
    public Moto_Catalog_model_page_Logic availabilityOfMotoSelector() {
        motoSelectorBlock().shouldBe(visible);
        return this;
    }

    @Step("opening are closed Selector .Moto_Catalog_model_page")
    public Moto_Catalog_model_page_Logic openingSelector() {
        motoSelectorInCloseCondition().shouldBe(visible).click();
        motoSelectorMainForm().shouldBe(visible);
        return this;
    }

}
