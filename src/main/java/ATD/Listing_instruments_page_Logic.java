package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;
import static com.codeborne.selenide.Condition.visible;

public class Listing_instruments_page_Logic extends Listing_instruments_page {

    @Step("Get title name Category. Listing_instruments_page")
    public String getTitleNameCategory() {
        return titleNameCategory().getText();
    }

    @Step("Checking presence Seo text block. Listing_instruments_page")
    public Listing_instruments_page_Logic checkingPresenceSeoTextBlock() {
        Assert.assertFalse(seoTextBlock().text().isEmpty());
        return this;
    }

    @Step("Checking work buttons in pagination. Listing_instruments_page")
    public Listing_instruments_page_Logic checkingWorkBtnInPagination () {
        btnSecondPageInPagination().click();
        btnReturnOnFirstPageInPagination().shouldBe(visible);
        btnPreviousPageInPagination().shouldBe(visible);
        btnNextPageInPagination().shouldBe(visible);
        btnLastPageInPagination().shouldBe(visible);
        btnPreviousPageInPagination().click();
        btnReturnOnFirstPageInPagination().shouldNotBe(visible);
        btnNextPageInPagination().click();
        PKW.CommonMethods.checkingContainsUrl("page=2");
        btnLastPageInPagination().click();
        btnLastPageInPagination().shouldNotBe(visible);
        btnReturnOnFirstPageInPagination().click();
        btnReturnOnFirstPageInPagination().shouldNotBe(visible);
        return this;
    }


}
