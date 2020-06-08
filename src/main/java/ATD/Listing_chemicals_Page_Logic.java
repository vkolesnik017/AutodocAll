package ATD;

import io.qameta.allure.Step;

public class Listing_chemicals_Page_Logic extends Listing_chemicals_Page {

    @Step("Get name title Category. Listing_chemicals_Page")
    public String getNameTitleCategory() {
        return titleNameCategory().getText();
    }
}
