package ATD;

import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.visible;

public class AutodocClub_page_Logic extends AutodocClub_page {


    @Step("check presence icon autodoc club in header. AutodocClub_page")
    public AutodocClub_page checkPresenceIconAutodocClubInHeader() {
        iconAutodocClub().shouldBe(visible);
        return this;
    }

}
