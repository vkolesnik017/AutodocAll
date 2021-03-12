package ATD;


import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.visible;


public class Tyres_model_page_Logic extends Tyres_model_page {


    @Step("Check presence top model block. Tyres_model_page")
    public Tyres_model_page_Logic checkPresenceTopModelBlock() {
        topModelBlock().shouldBe(visible);
        return this;
    }




}
