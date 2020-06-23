package PKW;

import io.qameta.allure.Step;

public class Listing_chemicals_page_Logic extends Listing_chemicals_page {

    @Step("Get title name listing page. Listing_chemicals_page")
    public String getTitleNameListingPage() {
       return titleNameListingPage().getText();
    }

}
