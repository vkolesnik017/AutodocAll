package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class Profile_plus_page_Logic extends Profile_plus_page {

    @Step("visibility of users name .Profile_plus_page")
    public Profile_plus_page_Logic visibilityOfUsersName() {
        nameOfUser().shouldBe(visible);
        return this;
    }

    @Step("Transition to profile addresses page. Profile_plus_page")
    public Profile_addresses_page_Logic goToProfileAddressesPage() {
        profileAddressBtn().click();
        return page(Profile_addresses_page_Logic.class);
    }
}
