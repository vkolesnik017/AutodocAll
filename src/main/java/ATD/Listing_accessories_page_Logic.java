package ATD;

import io.qameta.allure.Step;

public class Listing_accessories_page_Logic extends Listing_accessories_page {

    @Step("Checking title name on listing")
    public String getTitleName() {
        return listingTitle().getText();
    }
}
