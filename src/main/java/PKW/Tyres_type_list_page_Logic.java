package PKW;


import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.visible;


public class Tyres_type_list_page_Logic extends Tyres_type_list_page {

    @Step("Check presence tires sizes table. Tyres_type_list_page")
    public Tyres_type_list_page_Logic checkPresenceTiresSizeTable() {
        tiresSizeTable().shouldBe(visible);
        return this;
    }


}
