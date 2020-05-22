package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;

public class Moto_Category_car_list_page_Logic  extends Moto_Category_car_list_page{

    @Step("visibility of TecDoc listing .Moto_Category_car_list_pag")
    public Moto_Category_car_list_page_Logic visibilityOfTecDocListing() {
        tecDocListingBlock().shouldBe(visible);
        return this;
    }
}
