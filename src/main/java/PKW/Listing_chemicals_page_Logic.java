package PKW;


import io.qameta.allure.Step;
import static PKW.CommonMethods.checkingContainsUrl;
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

    @Step("Checking presence bread crumbs block. Listing_chemicals_page")
    public Listing_chemicals_page_Logic checkingPresenceBreadCrumbsBlock() {
        blockBreadCrumbs().shouldBe(visible);
        return this;
    }

    @Step("Checking work buttons in pagination. Listing_chemicals_page")
    public  Listing_chemicals_page_Logic checkingWorkBtnInPagination () {
        btnSecondPageInPagination().scrollIntoView(false).click();
        btnForReturnOnFirstPageInPagination().scrollIntoView(false).shouldBe(visible);
        btnPreviousInPagination().shouldBe(visible);
        btnNextInPagination().shouldBe(visible);
        btnLastInPagination().shouldBe(visible);
        btnPreviousInPagination().click();
        blockPagination().scrollIntoView(false);
        btnForReturnOnFirstPageInPagination().shouldNotBe(visible);
        btnNextInPagination().click();
        checkingContainsUrl("page=2");
        btnLastInPagination().scrollIntoView(false).click();
        blockPagination().scrollIntoView(false);
        btnLastInPagination().shouldNotBe(visible);
        btnForReturnOnFirstPageInPagination().click();
        blockPagination().scrollIntoView(false);
        btnForReturnOnFirstPageInPagination().shouldNotBe(visible);
        return this;
    }


}
