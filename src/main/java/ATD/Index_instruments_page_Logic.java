package ATD;

import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class Index_instruments_page_Logic extends Index_instruments_page {

    @Step("Checks presence main title on page tools. Index_instruments_page")
    public Index_instruments_page_Logic checkPresenceMainTitle() {
        titleMainPage().shouldBe(visible);
        return this;
    }

    @Step(" Gets the product name in block top-10 ")
    public String getProductName() {
        return productInBlockTop10().getText();
    }

    @Step("Click on products in block top-10 and transition to the product page")
    public Product_page_Logic clickAndTransitionProductPage() {
        productInBlockTop10().click();
        return page (Product_page_Logic.class);
    }



}
