package PKW;


import io.qameta.allure.Step;
import org.testng.Assert;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;


public class Index_instruments_page_Logic extends Index_instruments_page {

    @Step("Checking presence title main page. Index_instruments_page")
    public Index_instruments_page_Logic checkingPresenceTitleMainPage() {
        Assert.assertFalse(titleMainPage().text().isEmpty());
        return this;
    }

    @Step("Click first bread crumb. Index_instruments_page")
    public Parts_page_Logic clickFirstBreadCrumb() {
        firstBreadCrumb().click();
        return page(Parts_page_Logic.class);
    }

    @Step("Checking presence and not clickable second bread crumb. Index_instruments_page")
    public Index_instruments_page_Logic checkingPresenceAndNotClickableSecondCrumb() {
        secondBreadCrumb().shouldBe(visible).shouldNotBe(attribute("href"));
        return this;
    }

    @Step("Checking presence top brands block. Index_instruments_page")
    public Index_instruments_page_Logic checkingPresenceTopBrandsBlock() {
        blockTopBrands().shouldBe(visible);
        return this;
    }

    @Step("Get name first brand in top brands block. Index_instruments_page")
    public String getNameFirstBrandInTopBrandsBlock() {
        return firstBrandInTopBrandsBlock().getText();
    }

    @Step("Click on first brand in top brands block. Index_instruments_page")
    public Supplier_page_Logic clickFirstBrandInTopBrandsBlock() {
        firstBrandInTopBrandsBlock().click();
        return page(Supplier_page_Logic.class);
    }

    @Step("Checking presence main catalog categories block. Index_instruments_page")
    public Index_instruments_page_Logic checkingPresenceMainCatalogCategoriesBlock() {
        mainCatalogCategoriesBlock().shouldBe(visible);
        return this;
    }


}
