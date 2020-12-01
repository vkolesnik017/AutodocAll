package PKW;


import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.visible;

public class Tyres_maker_group_page_Logic extends Tyres_maker_group_page {

    @Step("Get car name title. Tyres_maker_group_page")
    public String getCarNameTitle() {
        return carNameTitle().shouldBe(visible).getText();
    }
}
