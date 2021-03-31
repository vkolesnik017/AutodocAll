package ATD;

import Common.DataBase;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.testng.Assert.assertEquals;

public class Index_instruments_page_Logic extends Index_instruments_page {

    @Step("Checks presence main title on page tools. Index_instruments_page")
    public Index_instruments_page_Logic checkPresenceMainTitle() {
        titleMainPage().shouldBe(visible);
        return this;
    }

    @Step("Checks current url .Index_instruments_page")
    public Index_instruments_page_Logic checkCurrentUrl(String subRoute) throws SQLException {
        DataBase db = new DataBase("ATD");
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

    @Step("Checking the number of mini-cards in top-6 products block. Index_instruments_page")
    public Index_instruments_page_Logic checkingNumberOfMiniCardsTop6Products() {
        miniCardsInTopProductsBlock().shouldHave(size(6));
        return this;
    }

    @Step("Checking the number of mini-cards in top-10 products block. Index_instruments_page")
    public Index_instruments_page_Logic checkingNumberOfMiniCardsTop10Products() {
        firstBtnAddToBasketInTop10ProductsBlock().scrollIntoView("{block: \"center\"}");
        miniCardsInTop10ProductsBlock().shouldHave(size(10));
        return this;
    }

    @Step("Checks presence VAT postscript {expectedVatPostscript} in mini-cards in top-6 products. Index_instruments_page")
    public Index_instruments_page_Logic checkPresenceVatPostscriptInMiniCardsInTop6(String expectedVatPostscript) {
        By vatPostscriptInMiniCards = (byCssSelector(".product-list__item__info"));
        for (SelenideElement miniCardFirsSlide : miniCardsInTopProductsBlock()) {
            miniCardFirsSlide.scrollIntoView("{block: \"center\"}");
            miniCardFirsSlide.$(vatPostscriptInMiniCards).shouldHave(text(expectedVatPostscript));
        }
        return this;
    }

    @Step("Checks presence VAT postscript {expectedVatPostscript} in mini-cards in top-10 products. Index_instruments_page")
    public Index_instruments_page_Logic checkPresenceVatPostscriptInMiniCardsInTop10(String expectedVatPostscript) {
        By vatPostscriptInMiniCards = (byCssSelector(".price_vat"));
        for (SelenideElement miniCardFirsSlide : miniCardsInTop10ProductsBlock()) {
            miniCardFirsSlide.scrollIntoView("{block: \"center\"}");
            miniCardFirsSlide.$(vatPostscriptInMiniCards).shouldHave(text(expectedVatPostscript));
        }
        return this;
    }

    @Step("Hover and Click on first button Add to basket in top-10 products block. Index_instruments_page")
    public Index_instruments_page_Logic hoverAndClickOnFirstBtnAddToBasketInTop10ProductsBlock() {
        firstBtnAddToBasketInTop10ProductsBlock().hover().click();
        sleep(2000);
        return this;
    }

    @Step(":from Index_instruments_page")
    public Cart_page_Logic clickOnBtnGoToBasket() {
        new Main_page_Logic().cartClick();
        return page(Cart_page_Logic.class);
    }

    @Step("Gets name first product in block top-6 products. Index_instruments_page")
    public String getNameFirstProductInTop6ProductBlock() {
        return nameFirstProductInTop6ProductsBlock().getText();
    }

    @Step("Click on first button Add to basket in top-6 products block. Index_instruments_page")
    public Index_instruments_page_Logic clickOnFirstBtnAddToBasketInTop6ProductsBlock() {
        firstBtnAddToBasketInTop6ProductsBlock().scrollIntoView("{block: \"center\"}").click();
        sleep(2000);
        return this;
    }

    @Step("Get name first category in Logical Union after Hover. Index_instruments_page")
    public String getNameFirstCategoryInLogicalUnionAfterHover() {
        firstGroupLogicalUnion().hover();
        return firstCategoryInLogicalUnion().getText();
    }

    @Step("Click on first category in Logical Union. Index_instruments_page")
    public Listing_instruments_page_Logic clickOnFirstCategoryInLogicalUnion() {
        firstCategoryInLogicalUnion().click();
        return page(Listing_instruments_page_Logic.class);
    }

    @Step("Checking hover popup in top 6 product block. Index_instruments_page")
    public Index_instruments_page_Logic checkingHoverPopupInTop6ProductBlock() {
        nameFirstProductInTop6ProductsBlock().hover();
        btnDetailsInPopupGoodsTop6Block().shouldBe(visible);
        return this;
    }

    @Step("Click on first Product in block top-6. Index_instruments_page")
    public Product_page_Logic clickOnFirstProductInBlockTop6() {
        nameFirstProductInTop6ProductsBlock().click();
        return page(Product_page_Logic.class);
    }

    @Step("Checking hover popup in top 10 product block. Index_instruments_page")
    public Index_instruments_page_Logic checkingHoverPopupInTop10ProductBlock() {
        productInBlockTop10().hover();
        btnDetailsInPopupGoodsTop10ProductsBlock().shouldBe(visible);
        return this;
    }

    @Step("Checking presence block Advantages and information popup after hover on tab number 2-3. Index_instruments_page")
    public Index_instruments_page_Logic checkingPresenceAdvantagesBlockAndInformationPopup() {
        blockAdvantages().scrollTo().shouldBe(visible);
        firstTabAdvantages().shouldBe(visible);
        secondTabAdvantage().hover();
        popupSecondTabAdvantage().shouldBe(visible);
        thirdTabAdvantage().hover();
        popupThirdTabAdvantage().shouldBe(visible);
        return this;
    }

    @Step("Get name first separate category on main catalog. Index_instruments_page")
    public String getNameFirstSeparateCategoryMainCatalog() {
        return firstSeparateCategoryInMainCatalog().getText();
    }

    @Step("Click first separate category on main catalog. Index_instruments_page")
    public Listing_instruments_page_Logic clickFirstSeparateCategoryMainCatalog() {
        firstSeparateCategoryInMainCatalog().shouldBe(visible).click();
        return page(Listing_instruments_page_Logic.class);
    }

    @Step("Get id all categories from logical union and separate categories then write to list. Index_instruments_page")
    public ArrayList<String> getIdCategoriesAndSeparateCategoriesThenWriteToList() {
        titleTop6ProductsBlock().scrollIntoView(false);
        ArrayList<String> categoriesFromLogicalUnion = new ArrayList<>();
        for (SelenideElement element : categoriesFromLogicalUnion()) {
            String idCategory = element.getAttribute("data-ga-action");
            categoriesFromLogicalUnion.add(idCategory);
        }

        ArrayList<String> separateCategoriesId = new ArrayList<>();
        for (SelenideElement element : nameSeparateCategories()) {
            String idSeparateCategory = element.getAttribute("data-ga-action");
            separateCategoriesId.add(idSeparateCategory);
        }

        categoriesFromLogicalUnion.addAll(separateCategoriesId);
        Collections.sort(categoriesFromLogicalUnion);
        return categoriesFromLogicalUnion;
    }


    @Step("Get id Logical Unions and write to list. Index_instruments_page")
    public ArrayList<String> getIdLogicalUnionAndWriteToList() {
        ArrayList<String> logicalUnionsId = new ArrayList<>();
        for (SelenideElement element:logicalUnions()) {
            String idLogicalUnion = element.getAttribute("data-cat-id");
            logicalUnionsId.add(idLogicalUnion);
            Collections.sort(logicalUnionsId);
        }
        return logicalUnionsId;
    }

    @Step("Get url all categories from logical union and separate categories then write to list. Index_instruments_page ")
    public ArrayList<String> getUrlCategoriesAndSeparateCategoriesThenWriteToList(ElementsCollection categoriesLogicalUnion, ElementsCollection separateCategories) {
        titleTop6ProductsBlock().scrollIntoView(false);
        ArrayList<String> allCategoriesMainBlock = new ArrayList<>();
        for (SelenideElement element : categoriesLogicalUnion) {
            String urlCategory = element.getAttribute("href");
            allCategoriesMainBlock.add(urlCategory);
        }

        ArrayList<String> separateCategoriesUrl = new ArrayList<>();
        for (SelenideElement element : separateCategories) {
            String urlSeparateCategory = element.getAttribute("href");
            separateCategoriesUrl.add(urlSeparateCategory);
        }

        allCategoriesMainBlock.addAll(separateCategoriesUrl);
        return allCategoriesMainBlock;
    }

    @Step(": from. Index_instruments_page")
    public Index_instruments_page_Logic checkCategoriesForServerResponses200( List<String> allCategories) throws IOException {
        CommonMethods.checkCategoriesForServerResponses200(allCategories);
        return this;
    }

    @Step("check Seo Text Block. Index_instruments_page")
    public Index_instruments_page_Logic checkSeoTextBlock(String value) throws SQLException {
        String frontText = seoText().shouldBe(visible).getText().replaceAll("\\W", "");
        String bdText = new DataBase("ATD").getTranslate("seo_text", "DE", value).replaceAll("\\W", "");
        Assert.assertEquals(frontText, bdText);
        return this;
    }


}
