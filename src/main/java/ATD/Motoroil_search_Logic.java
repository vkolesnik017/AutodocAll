package ATD;

import io.qameta.allure.Step;

public class Motoroil_search_Logic extends Motoroil_search{

    @Step(": for Motoroil_search_Logic")
    public Motoroil_search_Logic checkPresenceSpecificProductInGrayBtnInLiterBlock(String idProduct) {
        new Motoroil_brand_page_Logic().checkPresenceSpecificProductInGrayBtnInLiterBlock(idProduct);
        return this;
    }

    @Step(": for Motoroil_search_Logic")
    public Motoroil_search_Logic checkAbsenceArticleNum(String expectedArtNum) {
        new Listing_page_Logic().checkAbsenceArticleNum(expectedArtNum);
        return this;
    }
}
