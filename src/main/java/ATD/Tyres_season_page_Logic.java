package ATD;

import io.qameta.allure.Step;

import java.io.IOException;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.back;

public class Tyres_season_page_Logic extends Tyres_season_page {

    @Step("check Transition Of TOP Tyre size Links. Tyres_season_page")
    public Tyres_season_page_Logic checkTransitionOfTopTyreSizeLinks() throws IOException {
        for (int i = 0; i < topCarTyreSizeLinks().size(); i++) {
            topCarTyreSizeLinks().get(i).shouldBe(visible).scrollTo().click();
            new Category_car_list_page_Logic().checkResponseOfServer(200);
            back();
        }
        return this;
    }
}
