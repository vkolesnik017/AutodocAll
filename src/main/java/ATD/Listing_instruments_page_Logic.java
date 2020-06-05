package ATD;

import io.qameta.allure.Step;

public class Listing_instruments_page_Logic extends Listing_instruments_page {

    @Step("Get title name Category. Listing_instruments_page")
    public String getTitleNameCategory() {
        return titleNameCategory().getText();
    }
}
