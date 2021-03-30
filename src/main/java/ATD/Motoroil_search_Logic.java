package ATD;

import io.qameta.allure.Step;

public class Motoroil_search_Logic extends Motoroil_search{

    @Step(": for Motoroil_search_Logic")
    public Motoroil_search_Logic checkPresenceSpecificProductInGrayBtnInLiterBlock(String idProduct) {
        new Motoroil_brand_page_Logic().checkPresenceSpecificProductInGrayBtnInLiterBlock(idProduct);
        return this;
    }

    @Step(": for Motoroil_search_Logic")
    public Motoroil_search_Logic checkAbsenceSpecificProductInGrayBtnInLiterBlock(String idProduct) {
        new Motoroil_brand_page_Logic().checkAbsenceSpecificProductInGrayBtnInLiterBlock(idProduct);
        return this;
    }

    @Step(": for Motoroil_search_Logic")
    public Motoroil_search_Logic checkAbsenceArticleNum(String expectedArtNum) {
        new Listing_page_Logic().checkAbsenceArticleNum(expectedArtNum);
        return this;
    }

    @Step(": for Motoroil_search_Logic")
    public Motoroil_search_Logic checkPresenceSpecificArticleNumOnListing(String expectedArtNum) {
        new Listing_page_Logic().checkPresenceSpecificArticleNumOnListing(expectedArtNum);
        return this;
    }

    @Step(": for Motoroil_search_Logic")
    public Motoroil_search_Logic checkAbsenceGrayBtnAtExpectedProduct(String idProduct) {
        new Listing_page_Logic().checkAbsenceGrayBtnAtExpectedProduct(idProduct);
        return this;
    }
}
