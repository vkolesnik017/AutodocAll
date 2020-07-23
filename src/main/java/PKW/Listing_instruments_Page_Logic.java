package PKW;

import io.qameta.allure.Step;

public class Listing_instruments_Page_Logic extends Listing_instruments_Page {


    @Step("Get name title category. Listing_instruments_Page")
    public String getNameTitleCategory() {
        return titleCategory().getText();
    }


}
