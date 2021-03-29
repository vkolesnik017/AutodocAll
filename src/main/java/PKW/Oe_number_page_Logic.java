package PKW;

import io.qameta.allure.Step;

public class Oe_number_page_Logic extends Oe_number_page {

    @Step("Checking text in Title Page.Oe_number_page")
    public String checksTextInTitlePage() {
        return titlePage().getText();
    }


    @Step(":from. Oe_number_page")
    public Oe_number_page_Logic checkListingBrand(int sizeOfCheckingBrand, String brand) {
        new Listing_page_Logic().checkListingBrand(sizeOfCheckingBrand, brand);
        return this;
    }


    @Step(":from. Oe_number_page")
    public int getSizeOfActiveProductsWithBrand(String brand) {
        return new Listing_page_Logic().getSizeOfActiveProductsWithBrand(brand);
    }
}