package PKW;

import io.qameta.allure.Step;

public class Listing_accessories_page_Logic extends Listing_accessories_page {

    @Step("Get name main title on Listing Page. Listing_accessories_page")
    public String getNameMainTitleOnListingPage(){
        return mainTitleListingPage().getText();
    }
}
