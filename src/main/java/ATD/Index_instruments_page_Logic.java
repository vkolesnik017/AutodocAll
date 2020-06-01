package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;
import java.sql.SQLException;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static com.codeborne.selenide.Selenide.page;

public class Index_instruments_page_Logic extends Index_instruments_page {

    @Step("Checks presence main title on page tools. Index_instruments_page")
    public Index_instruments_page_Logic checkPresenceMainTitle() {
        titleMainPage().shouldBe(visible);
        return this;
    }

    @Step("Checks current url .Index_instruments_page")
    public Index_instruments_page_Logic checkCurrentUrl(String subRoute) throws SQLException {
        DataBase db = new DataBase();
        Assert.assertEquals(url(), db.getFullRouteByRouteAndSubroute("prod", "DE", "main", subRoute));
        return this;
    }

    @Step(" Gets first  product name in block top-10. Index_instruments_page ")
    public String getProductName() {
        return productInBlockTop10().getText();
    }

    @Step("Click on first  products in block top-10 and transition to the product page. Index_instruments_page")
    public Product_page_Logic clickFirstProductInTopBlock() {
        productInBlockTop10().click();
        return page (Product_page_Logic.class);
    }

    @Step("Checking presence  title top-10 products block.  Index_instruments_page")
    public Index_instruments_page_Logic checkingPresenceTitleTop10Block() {
        titleTop10ProductsBlock().shouldBe(visible);
        return this;
    }

    @Step("Checking presence title top-6 products block. Index_instruments_page")
    public Index_instruments_page_Logic checkingPresenceTitleTop6Block() {
        titleTop6ProductsBlock().shouldBe(visible);
        return this;
    }

    @Step("Checking presence Subtitle main page. Index_instruments_page")
    public Index_instruments_page_Logic checkingPresenceSubtitleMainPage() {
        Assert.assertFalse(subTitleMainPage().text().isEmpty());
        return this;
    }

    @Step("Checking presence top brands block. Index_instruments_page")
    public Index_instruments_page_Logic checkingPresenceTopBrandsBlock() {
        blockTopBrands().shouldBe(visible);
        return this;
    }

    @Step("Checking presence Features block. Index_instruments_page")
    public Index_instruments_page_Logic checkingPresenceFeaturesBlock() {
        blockFeatures().shouldBe(visible);
        return this;
    }

    @Step("Checking presence delivery block. Index_instruments_page")
    public Index_instruments_page_Logic checkingPresenceDeliveryBlock() {
        blockDelivery().shouldBe(visible);
        return this;
    }

    @Step("Checking presence Subtitle Top-10 block. Index_instruments_page")
    public Index_instruments_page_Logic checkingPresenceSubtitleTop10Block(){
        Assert.assertFalse(subtitleBlockTop10().text().isEmpty());
        return this;
    }

    @Step("Checking the number of mini-cards in top products block. Index_instruments_page")
    public Index_instruments_page_Logic checkingNumberOfMiniCardsTopProducts(int expectedSize) {
        miniCardsInTopProductsBlock().shouldHave(size(expectedSize));
        return this;
    }

    @Step("Checking the number of mini-cards in top-10 products block. Index_instruments_page ")
    public Index_instruments_page_Logic checkingNumberOfMiniCardsTop10Products() {
        miniCardsInTop10ProductsBlock().shouldHave(size(10));
        return this;
    }



}
