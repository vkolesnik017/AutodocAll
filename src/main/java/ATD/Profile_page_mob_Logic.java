package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.page;

public class Profile_page_mob_Logic extends Profile_page_mob {

    @Step("Clicking address button. Profile_page_mob")
    public Profile_addresses_page_mob_Logic clickAddresseBtn() {
        addressBtn().click();
        return page(Profile_addresses_page_mob_Logic.class);

    }
}
