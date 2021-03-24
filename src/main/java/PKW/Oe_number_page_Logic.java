package PKW;

import io.qameta.allure.Step;

public class Oe_number_page_Logic extends Oe_number_page {

    @Step("Checking text in Title Page.Oe_number_page")
    public String checksTextInTitlePage() {
        return titlePage().getText();
    }
}
