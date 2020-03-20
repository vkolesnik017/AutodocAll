package ATD;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.poi.ss.formula.functions.Match;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LKW_Category_car_list_page_Logic extends LKW_Category_car_list_page {
    @Step("Check successfully LKW_Category car list page loading .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkSuccessfullyLKWCategoryCarListPageLoading(String currentUrl) {
        listingOfProducts().shouldBe(visible);
        Assert.assertTrue(url().contains(currentUrl));
        return this;
    }


    @Step("Check links in bread crumbs block .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkLinksInBreadCrumbsBlockTecDocListing() {
        breadCrumbsLinks().shouldHave(size(5));
        firstLinkOfBreadCrumbsBlock().shouldBe(visible);
        secondLinkOfBreadCrumbsBlock().shouldHave(exactText("Filter"));
        thirdLinkOfBreadCrumbsBlock().shouldHave(exactText("Ölfilter"));
        fourthLinkOfBreadCrumbsBlock().shouldHave(exactText("MERCEDES-BENZ"));
        fifthLinkOfBreadCrumbsBlock().shouldHave(exactText("ACTROS")).shouldNotHave(attribute("href"));
        return this;
    }

    @Step("Check link click in bread crumbs block  TecDoc Listing.LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkLinkClickInBreadCrumbsBlock() {
        firstLinkClick().checkSuccessfullyMakerCarListPageLoading("https://lkwteile.autodoc.de/lastkraftwagen/mercedes-benz/actros");
        back();
        secondLinkClick().checkSuccessfullyCategoryMakerPageLoading("https://lkwteile.autodoc.de/ersatzteile/filter");
        back();
        thirdLinkClick().checkSuccessfullyLKWCategoryCarListPageLoading("https://lkwteile.autodoc.de/ersatzteile/olfilter-200157/mercedes-benz/actros?car_id=1000784");
        back();
        fourthLinkClick().checkSuccessfullyCategoryMakerPageLoading("https://lkwteile.autodoc.de/ersatzteile/olfilter-200157/mercedes-benz");
        return this;
    }

    @Step("Click on first link in bread crumbs block .LKW_Category_car_list_page")
    public LKW_maker_car_list_Logic firstLinkClick() {
        firstLinkOfBreadCrumbsBlock().click();
        return page(LKW_maker_car_list_Logic.class);
    }

    @Step("Click on second link in bread crumbs block .LKW_Category_car_list_page")
    public LKW_Category_maker_Logic secondLinkClick() {
        secondLinkOfBreadCrumbsBlock().click();
        return page(LKW_Category_maker_Logic.class);
    }

    @Step("Click on third link in bread crumbs block .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic thirdLinkClick() {
        thirdLinkOfBreadCrumbsBlock().click();
        return this;
    }

    @Step("Click on fourth link in bread crumbs block .LKW_Category_car_list_page")
    public LKW_Category_maker_Logic fourthLinkClick() {
        fourthLinkOfBreadCrumbsBlock().click();
        return page(LKW_Category_maker_Logic.class);
    }

    @Step("Check links in bread crumbs block TecDoc catalog .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkLinksInBreadCrumbsBlockTecDocCatalog() {
        breadCrumbsLinks().shouldHave(size(4));
        firstLinkOfBreadCrumbsBlock().shouldBe(visible);
        firstLinkOfBreadCrumbsBlockTitleTecDoC().shouldHave(exactText("MERCEDES-BENZ"));
        secondLinkOfBreadCrumbsBlock().shouldHave(exactText("ACTROS"));
        thirdLinkOfBreadCrumbsBlock().shouldHave(exactText("1831 AK"));
        return this;
    }

    @Step("Check link click in bread crumbs block TecDoc Catalog .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkLinkClickTecDocCatalog() {
        firstLinkClickTecDocCatalog().checkUrlWithSelectingCar("https://lkwteile.autodoc.de/lastkraftwagen/mercedes-benz");
        back();
        secondLinkClickTecDocCatalog().checkSuccessfullyMakerCarListPageLoading("https://lkwteile.autodoc.de/lastkraftwagen/mercedes-benz/actros");
        return this;
    }

    @Step("Click on first link in bread crumbs block TecDoc catalog .LKW_Category_car_list_page")
    public LKW_Categories_maker_page_Logic firstLinkClickTecDocCatalog() {
        firstLinkOfBreadCrumbsBlockTitleTecDoC().click();
        return page(LKW_Categories_maker_page_Logic.class);
    }

    @Step("Click on second link in bread crumbs block TecDoc catalog .LKW_Category_car_list_page")
    public LKW_maker_car_list_Logic secondLinkClickTecDocCatalog() {
        secondLinkOfBreadCrumbsBlock().click();
        return page(LKW_maker_car_list_Logic.class);
    }

    @Step("checking of visibility of TecDoc Listing block .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic visibilityOfTecDocListingBlock() {
        listOfProductInTecDocListingBlock().shouldBe(visible);
        return this;
    }


    @Step("checking the applicability of product for selected truck .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkingApplicabilityOfProductForSelectedTruck() {
        selectProductInTecDocListing();
        while (nextPagePagination().isDisplayed()) {
            nextPagePagination().click();
            selectProductInTecDocListing();
        }

        return this;
    }

    @Step("click on product in TecDoc Listing .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic selectProductInTecDocListing() {
        for (int i = 1; i <= productsOnPage().size(); i++) {
            clickOnProductInTecDocListing(i).checkCompatibilityProductAndTruck();
            back();
        }
        return this;
    }

    @Step("click on Product in TecDoc listing .LKW_Category_car_list_page")
    public LKW_Product_page_Logic clickOnProductInTecDocListing(int point) {
        imageOfProductTecDocListingBlock(point).scrollIntoView("{block: \"center\"}").click();
        return page(LKW_Product_page_Logic.class);
    }

    @Step("added product to basket .LKW_Category_car_list_page")
    public Cart_page_Logic addProductToBasket() {
        listingOfProducts().shouldBe(visible);
        btnOfFirstProductInTecDocListing().click();
        basketDropMenu().should(appear);
        basketDropMenu().should(disappear);
        basket().click();
        return page(Cart_page_Logic.class);
    }

    @Step("get id of product in TecDoc Listing .LKW_Category_car_list_page")
    public String getIdOfProductFromTecDocListing() {
        String idOfProduct = btnOfFirstProductInTecDocListing().getAttribute("id");
        return idOfProduct;
    }

    @Step("Go to product page from tecDoc listing through Image, icon of brand, title in tecDoc listing .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic goToProductPageFromImageBrandTitle() {
        clickOnImageOfProduct().checkSuccessfullyLKWProductPageLoading("https://lkwteile.autodoc.de/boss-filters/7175133");
        back();
        clickOnIconBrandOfProduct().checkSuccessfullyLKWProductPageLoading("https://lkwteile.autodoc.de/boss-filters/7175133");
        back();
        clickOnTitleOfProduct().checkSuccessfullyLKWProductPageLoading("https://lkwteile.autodoc.de/boss-filters/7175133");
        return this;
    }

    @Step("Click on image of product in tecDoc listing .LKW_Category_car_list_page")
    public LKW_Product_page_Logic clickOnImageOfProduct() {
        imageOfProductTecDocListingBlock(1).click();
        return page(LKW_Product_page_Logic.class);
    }

    @Step("Click on image of brand of product in tecDoc listing .LKW_Category_car_list_page")
    public LKW_Product_page_Logic clickOnIconBrandOfProduct() {
        imageBrandOfProductTecDocListingBlock(1).click();
        return page(LKW_Product_page_Logic.class);
    }

    @Step("Click on title of product in tecDoc listing .LKW_Category_car_list_page")
    public LKW_Product_page_Logic clickOnTitleOfProduct() {
        titleOfProductInTecDocListingBlock(1).click();
        return page(LKW_Product_page_Logic.class);
    }

    @Step("Check of visibility dynamic characteristics of product in TecDoc listing .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkOfVisibilityDynamicCharacteristics() {
        List<String> artNumberOfProduct = new ArrayList<>();
        artNumberOfProduct.add("Artikelnummer: V31-1013");
        artNumberOfProduct.add("Artikelnummer: HU 12 140 x");
        artNumberOfProduct.add("Artikelnummer: PFU 19 226 x");

        for (int i = 0; i < artNumberOfProduct.size(); i++) {
            articleNumberOfProduct(artNumberOfProduct.get(i)).scrollTo();
            if (i == 0) {
                dynamicCharacteristicInTecDocListingBlock(artNumberOfProduct.get(i)).shouldHave(exactText("OM541 LA"));
            } else {
                dynamicCharacteristicInTecDocListingBlock(artNumberOfProduct.get(i)).shouldHave(exactText("OM 541 LA"));
            }
        }
        return this;
    }

    @Step("Check amount of pages in TecDoc listing .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkAmountOfPagesInTecDocListing() {
        int actualAmountPagesInTecDocListing = (int) Math.ceil(Integer.parseInt(totalAmountOfProductsInTecDocListing().getText()) / 20.0);
        int expectedAmountPagesInTecDocListing = Integer.parseInt(lastPagePaginator().getAttribute("href").substring(97));
        Assert.assertTrue(actualAmountPagesInTecDocListing == expectedAmountPagesInTecDocListing);
        nextPagePagination().click();
        productsOnPage().shouldHave(sizeNotEqual(0));
        return this;
    }

}



