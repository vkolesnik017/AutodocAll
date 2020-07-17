package ATD;

import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class Profile_page_mob_Logic extends Profile_page_mob {

    @Step("Clicking address button. Profile_page_mob")
    public Profile_addresses_page_mob_Logic clickAddresseBtn() {
        addressBtn().click();
        return page(Profile_addresses_page_mob_Logic.class);
    }

    @Step("Cart clicking. Profile_page_mob")
    public Cart_page_mob_Logic cartClick() {
        new Main_page_mob_Logic().cartClick();
        return page(Cart_page_mob_Logic.class);
    }

    @Step("Checks presence icon user id. Profile_page_mob")
    public Profile_page_mob_Logic checkPresenceIconUserId() {
        iconUserId().shouldBe(visible);
        return this;
    }
}
