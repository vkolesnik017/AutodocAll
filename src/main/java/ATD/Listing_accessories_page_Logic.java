package ATD;

import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.sleep;

public class Listing_accessories_page_Logic extends Listing_accessories_page {

    @Step("Checking title name on listing")
    public String getTitleName() {
        return listingTitle().getText();
    }

    @Step("Checking that selected brand become not active after clicking on it")
    public Listing_accessories_page_Logic checkResetSelectedFilterByBrand() {
        selectedBrand().shouldBe(visible).click();
        sleep(3000);
        selectedBrand().shouldNotBe(visible);
        return this;
    }

}
