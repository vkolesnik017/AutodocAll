package PKW;


import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class Listing_chemicals_page_Logic extends Listing_chemicals_page {

    @Step("Get title name listing page. Listing_chemicals_page")
    public String getTitleNameListingPage() {
       return titleNameListingPage().getText();
    }

    @Step("Click first bread crumb. Listing_chemicals_page")
    public Parts_page_Logic clickFirstBreadCrumb() {
        firstBreadCrumb().click();
        return page(Parts_page_Logic.class);
    }
    @Step("Click second bread crumb. Listing_chemicals_page")
    public Index_chemicals_page_Logic clickSecondBreadCrumb() {
        secondBreadCrumb().click();
        return page(Index_chemicals_page_Logic.class);
    }

    @Step("Checking presence and not clickable third bread crumb. Listing_chemicals_page")
    public Listing_chemicals_page_Logic checkingPresenceAndNotClickableThirdBreadCrumb() {
        thirdBreadCrumb().shouldBe(visible).shouldNotBe(attribute("href"));
        return this;
    }

}
