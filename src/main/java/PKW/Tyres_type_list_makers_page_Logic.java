package PKW;


import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.visible;


public class Tyres_type_list_makers_page_Logic extends  Tyres_type_list_makers_page {

    @Step("Check presence all cars block. Tyres_type_list_makers_page")
    public Tyres_type_list_makers_page_Logic checkPresenceAllCarsBlock() {
        allCarsBlock().shouldBe(visible);
        return this;
    }


}
