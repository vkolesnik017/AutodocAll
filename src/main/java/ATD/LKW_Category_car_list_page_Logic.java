package ATD;

import Common.DataBase;
import com.codeborne.selenide.ElementsCollection;
import files.Product;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ATD.CommonMethods.getAttributeFromUnVisibleElement;
import static ATD.CommonMethods.getTextFromUnVisibleElement;
import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.empty;
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
        if (addOfIssueProductBlock().has(exist)) {
            for (int i = 1; i < imageOfProductsWithOutAddIssue().size(); i++) {
                clickOnMainProductInTecDocListing(i).checkCompatibilityProductAndTruck();
                back();
            }
        } else {
            for (int i = 1; i <= productsOnPage().size(); i++) {
                clickOnProductInTecDocListing(i).checkCompatibilityProductAndTruck();
                back();
            }
        }
        return this;
    }

    @Step("click on Product in TecDoc listing .LKW_Category_car_list_page")
    public LKW_Product_page_Logic clickOnProductInTecDocListing(int point) {
        imageOfProductTecDocListingBlock(point).scrollIntoView("{block: \"center\"}").click();
        return page(LKW_Product_page_Logic.class);
    }

    @Step("click on main Product in TecDoc listing .LKW_Category_car_list_page")
    public LKW_Product_page_Logic clickOnMainProductInTecDocListing(int point) {
        oneImageOfProductTecDocListingBlock(point).scrollIntoView("{block: \"center\"}").click();
        return page(LKW_Product_page_Logic.class);
    }

    @Step("added product to basket .LKW_Category_car_list_page")
    public Cart_page_Logic addProductToBasket() {
        listingOfProducts().shouldBe(visible);
        btnOfFirstProductInTecDocListing().scrollIntoView("{block: \"center\"}").click();
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
    public LKW_Category_car_list_page_Logic goToProductPageFromImageBrandTitle(String linkOfProduct) {
        clickOnImageOfProduct().checkSuccessfullyLKWProductPageLoading(linkOfProduct);
        back();
        clickOnIconBrandOfProduct().checkSuccessfullyLKWProductPageLoading(linkOfProduct);
        back();
        clickOnTitleOfProduct().checkSuccessfullyLKWProductPageLoading(linkOfProduct);
        return this;
    }

    @Step("get link of product .LKW_Category_car_list_page")
    public String getLinkOfProduct(int position) {
        String linkOfProduct = titleOfProductInTecDocListingBlock().get(position).getAttribute("href");
        return linkOfProduct;
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
        titleOfProductInTecDocListingBlock().get(0).click();
        return page(LKW_Product_page_Logic.class);
    }

    @Step("Check of visibility dynamic characteristics of product in TecDoc listing .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkOfVisibilityDynamicCharacteristics() {
        List<String> artNumberOfProduct = new ArrayList<>();
        artNumberOfProduct.add("Artikelnummer: V31-1013");
        artNumberOfProduct.add("Artikelnummer: HU 12 140 x");
    //    artNumberOfProduct.add("Artikelnummer: 12140X-OF-PCS-MS");   // ВРЕМЕННО ОТКЛЮЧИЛ, ТАК КАК ТОВАРА НЕТ В ЛИСТИНГЕ

        for (int i = 0; i < artNumberOfProduct.size(); i++) {
            articleNumberOfProduct(artNumberOfProduct.get(i)).scrollTo();
            boolean label = dynamicCharacteristicInTecDocListingBlock(artNumberOfProduct.get(i)).getText().matches("OM\\s?541\\sLA");
            Assert.assertTrue(label);
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

    @Step("Check filter by Generic in TecDoc listing .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkFilterByGeneric() {

        if (genericsBlockInSideBar().isDisplayed()) {
            typeOfGenericsInSideBar().shouldHave(sizeGreaterThan(0));
        } else if (filterByGenericBlock().isDisplayed()) {
            genericsInFilterGenericBlock().shouldHave(sizeNotEqual(0));
        }
        return this;
    }

    @Step("Check TecDoc listing with selecting filter .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkTecDocListingWithSelectingFilterByGeneric(int positionOfGeneric) {
        if (genericsBlockInSideBar().isDisplayed()) {
            typeOfGenericsInSideBar().get(positionOfGeneric).shouldBe(visible).click();
            appearsOfLoader();
        } else if (filterByGenericBlock().isDisplayed()) {
            genericsInFilterGenericBlock().get(1).click();
            appearsOfLoader();
        }
        Assert.assertTrue(url().contains("%5D=133"));
        for (int i = 0; i < titleOfProductInTecDocListingBlock().size(); i++) {
            titleOfProductInTecDocListingBlock().get(i).shouldHave(text("Dichtung, Ölfilter"));
        }

        if (genericsBlockInSideBar().isDisplayed()) {
            typeOfGenericsInSideBar().get(0).click();
        } else {
            allFiltersGeneric().click();
        }
        appearsOfLoader();
        titleOfProductInTecDocListingBlock().shouldHave(sizeNotEqual(0));
        return this;
    }

    @Step("appears of Loader .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic appearsOfLoader() {
        loaderInTecDocListing().should(appear);
        loaderInTecDocListing().should(disappear);
        return this;
    }

    @Step("check visibility of Brands block .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic visibilityOfBrandsBlock() {
        if (!brandBlock().isDisplayed()) {
            brandBlockInSideBar().shouldBe(visible);
        }
        return this;
    }

    @Step("Check TecDoc listing with selecting brand .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkTecDocListingWithSelectingFilterByBrand(String subRoute) throws SQLException {
        while (!brandsOfBrandBlock("cb-brand-4868").isDisplayed()) {
            forwardLinkAtBrandsFilter().click();
        }
        brandsOfBrandBlock("cb-brand-4868").click();
        appearsOfLoader();
        Assert.assertEquals(getExpectedUrl(url()), getExpectedUrl(new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "lkw_main", subRoute)));
        for (int i = 0; i < titleOfProductInTecDocListingBlock().size(); i++) {
            titleOfProductInTecDocListingBlock().get(i).shouldHave(text("DONALDSON"));
        }
        return this;
    }

    @Step("check visibility of Brands block .LKW_Category_car_list_page")
    public String getExpectedUrl(String currentUrl) {
        String urlPart = currentUrl.replace(currentUrl.replace(currentUrl.substring(currentUrl.lastIndexOf("&")), ""), "");
        String firstPart = urlPart.substring(urlPart.lastIndexOf("="));
        String secondPart = urlPart.replace(firstPart, "");
        String lastUrl = currentUrl.replace(secondPart, "");
        return lastUrl;
    }


    @Step("check visibility of Installation side block .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic visibilityOfInstallationSideBlock() {
        installationSideBlockInSideBar().shouldBe(visible);

        return this;
    }

    @Step("Check TecDoc listing with selecting brand .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkTecDocListingWithSelectingInstallationSide() {
        if (installationSideBlockInSideBar().isDisplayed()) {
            sidesOfInstallationInSideBar().get(0).click();
            appearsOfLoader();
        } else if (installationSideBlockInHeader().isDisplayed()) {
            sidesOfInstallation().get(0).click();
            appearsOfLoader();
        }

        Assert.assertTrue(url().equals("https://lkwteile.autodoc.de/ersatzteile/scheibenbremsbelag-200342/iveco/eurostar?car_id=1006726&criteria%5B100%5D=HA"));
        checkOfPresenceInstallationSide();
        while (nextPagePagination().isDisplayed()) {
            nextPagePagination().click();
            checkOfPresenceInstallationSide();
        }
        return this;
    }

    @Step("Checking the presence of the installation side .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkOfPresenceInstallationSide() {
        for (int i = 1; i <= characteristicsBlock().size(); i++) {
            descriptionOfCharacteristics(i).shouldBe(exist);
        }
        return this;
    }

    @Step("select Vorderachse installation side .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic selectVorderachseInstallationSide() {
        sidesOfInstallationInSideBar().get(1).click();
        appearsOfLoader();
        Assert.assertTrue(url().contains("https://lkwteile.autodoc.de/ersatzteile/scheibenbremsbelag-200342/iveco/eurostar?car_id=1006726&criteria%5B100%5D=VA"));
        for (int i = 1; i <= characteristicsBlock().size(); i++) {
            descriptionOfCharacteristicsFrontSide(i).shouldBe(exist);
        }
        return this;
    }

    @Step("reset Vorderachse installation side .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic resetVorderachseInstallationSide() {
        sidesOfInstallationInSideBar().get(0).click();
        appearsOfLoader();
        Assert.assertTrue(url().contains("https://lkwteile.autodoc.de/ersatzteile/scheibenbremsbelag-200342/iveco/eurostar?car_id=1006726"));
        return this;
    }

    @Step("check of appears block of analog in products with grey button .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkOfAppearsAnalogBlock() {
        nextPagePagination().click();
        Assert.assertTrue(url().equals("https://lkwteile.autodoc.de/ersatzteile/olfilter-200157/mercedes-benz/actros?car_id=1000784&page=2"));
        for (int i = 0; i < showReplacementButton().size(); i++) {
            showReplacementButton().get(i).scrollIntoView("{block: \"start\"}").click();
            analogProductBlock().get(i).shouldBe(visible);
            if (productsInAnalogBlock(i + 1).size() > 0) {
                titleOfAnalogBlock().get(i).shouldHave(exactText("EMPFOHLENER ERSATZ FÜR DIESEN ARTIKEL:"));
            } else {
                titleOfAnalogBlock().get(i).shouldHave(exactText("Keine Äquivalente verfügbar"));
            }
            sleep(1000);
        }
        return this;
    }

    @Step("check of visibility PopUp with subscription .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkOfVisibilityPopUpAboutSubscribe() {
        popUpWithSubscribeButton().get(0).scrollIntoView("{block: \"start\"}").click();
        popUpWithSubscriptionAboutAppearOfProduct().should(appear);
        closePopUpWithSubscriptionAboutAppearOfProduct().click();
        popUpWithSubscriptionAboutAppearOfProduct().should(disappear);
        return this;
    }

    @Step("get id of product in TecDoc Listing .LKW_Category_car_list_page")
    public String getIdOfProductFromTecDocListingInAnalogBlock() {
        showReplacementButton().get(0).scrollIntoView("{block: \"center\"}").click();
        analogProductBlock().get(0).shouldBe(visible);
        String idOfProduct = btnOfFirstProductInTecDocListingOfAnalogBlock().getAttribute("data-ga-label");
        return idOfProduct;
    }

    @Step("added product to basket .LKW_Category_car_list_page")
    public Cart_page_Logic addProductToBasketFromAnalogBlock() {
        btnOfFirstProductInTecDocListingOfAnalogBlock().scrollIntoView("{block: \"center\"}").click();
        basketDropMenu().should(appear);
        basketDropMenu().should(disappear);
        basket().click();
        return page(Cart_page_Logic.class);
    }

    @Step("check sorting of product .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkSortingPrice() {
        List<Double> activeOlifilter = new ArrayList<>();
        List<Double> notActiveOlifilter = new ArrayList<>();
        List<Double> activeDichtungOlifilter = new ArrayList<>();
        List<Double> notActiveDichtungOlifilter = new ArrayList<>();
        addedPriceToList(activeOlifilter, notActiveOlifilter, activeDichtungOlifilter, notActiveDichtungOlifilter);
        while (nextPagePagination().isDisplayed()) {
            nextPagePagination().click();
            addedPriceToList(activeOlifilter, notActiveOlifilter, activeDichtungOlifilter, notActiveDichtungOlifilter);
        }
        checkOfSortingSelectedGeneric(activeOlifilter);
        checkOfSortingSelectedGeneric(notActiveOlifilter);
        checkOfSortingSelectedGeneric(activeDichtungOlifilter);
        checkOfSortingSelectedGeneric(notActiveDichtungOlifilter);
        return this;
    }

    @Step("check of sorting of selected generic .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkOfSortingSelectedGeneric(List<Double> pricesList) {
        List<Double> expectedSortedPrices = new ArrayList<>(pricesList);
        Collections.sort(expectedSortedPrices);
        Assert.assertEquals(expectedSortedPrices, pricesList);
        return this;
    }

    @Step("add price to list .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic addedPriceToList(List<Double> first, List<Double> second, List<Double> third, List<Double> fourth) {
        addedPriceIfVisibility(activeProductOlifilter(), first);
        addedPriceIfVisibility(activeProductsDichtungOlifilter(), second);
        addedPriceIfVisibility(notActiveProductsOlifilter(), third);
        addedPriceIfVisibility(notActiveProductsDichtungOlifilter(), fourth);
        return this;
    }

    @Step("add price if it visible .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic addedPriceIfVisibility(ElementsCollection priceOfProduct, List<Double> listOfPrice) {
        if (priceOfProduct.get(0).isDisplayed()) {
            getPrice(priceOfProduct, listOfPrice);
        }
        return this;
    }

    @Step("get price .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic getPrice(ElementsCollection priceOfProduct, List<Double> listOfPrice) {
        for (int i = 0; i < priceOfProduct.size(); i++) {
            listOfPrice.add(Double.parseDouble(priceOfProduct.get(i).getText().replaceAll("[^,0-9]", "").replaceAll(",", ".")));
        }
        return this;
    }

    @Step("Click on image of product in tecDoc listing .LKW_Category_car_list_page")
    public LKW_Product_page_Logic goToProductPageFromImageWithArticle() {
        imageOfProductWithArticle("4.90930").scrollIntoView("{block: \"center\"}").click();
        return page(LKW_Product_page_Logic.class);
    }

    @Step("availability of vertical truck selector .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic availabilityOfVerticalTruckSelector() {
        verticalTruckSelector().shouldBe(visible);
        return this;
    }


    @Step("availability of truck in selector from url .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic availabilityOfTruckInSelectorFromUrl() {
        markeInVerticalTruckSelector().shouldNotHave(exactValue("0"));
        modelInVerticalTruckSelector().shouldNotHave(exactValue("0"));
        motorInVerticalTruckSelector().shouldNotHave(exactValue("0"));
        String carBrandFromSelector = markeInVerticalTruckSelector().getText().toLowerCase();
        Assert.assertTrue(url().contains(carBrandFromSelector));
        return this;
    }

    @Step("visibility of headline of selector and icon of truck  .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic visibilityOfHeadLineSelectorAndIconOfTruck(String selectTruck) {
        iconOfTruckInHeadlineOfSelector().shouldBe(visible);
        titleOfTruckInHeadlineOfSelector().shouldHave(exactText(selectTruck));
        return this;
    }

    @Step("Select truck in vertical selector .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic selectTruckInSelector(String markeOfTruck, String modelOfTruck, String motorOfTruck) {
        String oldSelectedTruck = titleOfTruckInHeadlineOfSelector().getText();
        markeOfVerticalTruckSelector().selectOptionByValue(markeOfTruck);
        modelOfVerticalTruckSelector().selectOptionByValue(modelOfTruck);
        motorOfVerticalTruckSelector().selectOptionByValue(motorOfTruck);
        buttonSuchenOfVerticaltruckSelector().click();
        titleOfTruckInHeadlineOfSelector().shouldNotHave(exactText(oldSelectedTruck));
        return this;
    }

    @Step("Visibility of selected brand in block of brands .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic visibilityOfSelectedBrand() {
        brandsOfBrandBlock("cb-brand-4").shouldHave(attribute("class", "active slick-slide slick-current slick-active"));
        return this;
    }


    @Step("check title of product with selected brand in TecDoc listing .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkTitleOfProductWithSelectedBrand(String titleOfBrand) {
        for (int i = 0; i < titleOfProductInTecDocListingBlock().size(); i++) {
            titleOfProductInTecDocListingBlock().get(i).shouldHave(text(titleOfBrand));
        }
        return this;
    }

    @Step("select brand of car in vertical truck selector .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic selectBrandOfCarInVerticalSelector(String valueOfBrand) {
        markeInVerticalTruckSelector().shouldBe(visible).selectOptionByValue(valueOfBrand);
        markeInVerticalTruckSelector().shouldHave(value(valueOfBrand));
        return this;
    }


    @Step("open vertical truck selector  .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic openVerticalSelector() {
        verticalSelectorInCloseCondition().click();
        return this;
    }

    @Step("reset of car brand field in vertical selector .LKW_Category_car_list_page")
    public LKW_Categories_page_Logic resetOfVerticalSelector() {
        resetBtnInVerticalCarSelector().click();
        return page(LKW_Categories_page_Logic.class);
    }


    @Step("reset of car brand field in vertical selector .LKW_Category_car_list_page")
    public LKW_Category_page_Logic resetOfOpenVerticalSelector() {
        resetBtnInVerticalCarSelector().click();
        return page(LKW_Category_page_Logic.class);
    }


    @Step("availability of image of brand in main headline  .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic availabilityOfImageOfBrand() {
        imageOfBrandAtMainHeadline().shouldBe(visible);
        return this;
    }

    @Step("select brand in sidebar  .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic selectBrandFromFilterOfBrands(String subRoute, String idOfBrand) throws SQLException {
        brandsFilterBlock().shouldBe(visible);
        String articleOfFirstBrand = visibleBrands().get(0).getAttribute("for");
        while (!brandsLinkInSideBar(idOfBrand).isDisplayed()) {
            forwardLinkAtBrandsFilter().click();
            visibleBrands().get(0).shouldNotHave(attribute("for", articleOfFirstBrand));
            presenceOfVisibleBrand();
        }
        brandsLinkInSideBar(idOfBrand).shouldBe(visible).click();
        appearsOfLoader();
        Assert.assertEquals(getFilterNumber(url()), getFilterNumber(new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "lkw_main", subRoute)));
        return this;
    }

    @Step("presence of visible brands  .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic presenceOfVisibleBrand() {
        for (int i = 0; i < visibleBrands().size(); i++) {
            visibleBrands().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("get number of brand from current url  .LKW_Category_car_list_page")
    public String getFilterNumber(String pathUrl) {
        String urlPart = pathUrl.replace(pathUrl.substring(pathUrl.lastIndexOf("=")), "");
        String partOfUrl = urlPart.replace(urlPart.substring(urlPart.lastIndexOf("&")), "");
        String expectedUrl = pathUrl.replace(urlPart.replace(partOfUrl, "").replace(" ", ""), "");
        return expectedUrl;
    }

    @Step("Check TecDoc listing with selecting brand  and installation side.LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkTecDocListingWithSelectingBrandAndInstallationSide() {

        if (installationSideBlockInSideBar().isDisplayed()) {
            sidesOfInstallationInSideBar().get(0).click();
            appearsOfLoader();
        } else if (installationSideBlockInHeader().isDisplayed()) {
            sidesOfInstallation().get(0).click();
            appearsOfLoader();
        }
        checkOfPresenceInstallationSide();
        checkOfPresenceSelectedBrand("TEXTAR");
        return this;
    }

    @Step("Checking the presence of products with selecting brand in TecDoc listing .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkOfPresenceSelectedBrand(String selectingBrand) {
        for (int i = 0; i < titleOfProductInTecDocListingBlock().size(); i++) {
            titleOfProductInTecDocListingBlock().get(i).shouldHave(text(selectingBrand));
        }
        return this;
    }

    @Step("select generic filter  .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic selectGenericFilter(String subRoute, String idOfGeneric) throws SQLException {
        selectGenericFromBlock(subRoute, idOfGeneric);
        Assert.assertEquals(getFilterNumber(getFilterNumber(url())), getFilterNumber(getFilterNumber(new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "lkw_main", subRoute))));
        return this;
    }

    @Step("select generic filter  .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic selectGenericFilterInSideBar(String subRoute, String idOfGeneric) throws SQLException {
        selectGenericFromBlock(subRoute, idOfGeneric);
        Assert.assertEquals(getFilterNumber(url()), getFilterNumber(new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "lkw_main", subRoute)));
        return this;
    }

    @Step("select generic filter from block .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic selectGenericFromBlock(String subRoute, String idOfGeneric) throws SQLException {
        if (genericsBlockInSideBar().isDisplayed()) {
            for (int i = 0; i < typeOfGenericsInSideBar().size(); i++) {
                if (typeOfGenericsInSideBar().get(i).has(attribute("id", "checklist-category-" + idOfGeneric + ""))) {
                    typeOfGenericsInSideBar().get(i).hover().click();
                }
            }
            appearsOfLoader();
        } else if (genericsBlock().isDisplayed()) {
            for (int i = 0; i < typeOfGenerics().size(); i++) {
                if (typeOfGenerics().get(i).has(attribute("for", "category_" + idOfGeneric + "")))
                    typeOfGenerics().get(i).click();
            }
            appearsOfLoader();
        }
        return this;
    }


    @Step("Checking the presence of products with selecting generic in TecDoc listing .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkOfPresenceSelectingGeneric(String selectingGeneric) {
        for (int i = 0; i < titleOfProductInTecDocListingBlock().size(); i++) {
            titleOfProductInTecDocListingBlock().get(i).shouldHave(text(selectingGeneric));
        }
        return this;
    }

    @Step("selecting of installation side  .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic selectInstallationSide(String subRoute) throws SQLException {
        installationSideBlock().shouldBe(visible);
        sidesOfInstallation().get(1).click();
        appearsOfLoader();
        Assert.assertEquals(url(), new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "lkw_main", subRoute));
        return this;
    }

    @Step("presence of Parent categories block .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic presenceOfParentCategoriesBlock(int countOfParentCategories) {
        parentCategories().shouldHave(sizeGreaterThan(countOfParentCategories));
        return this;
    }

    @Step("visibility Of Child categories popUp of Parent Category .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkParentCategoriesOfTecDocCatalog() {
        for (int i = 0; i < parentCategories().size(); i++) {
            if (titleOfParentCategories().get(i).getText().equals("Reifen")) {
                continue;
            }
            imageOfParentCategories().get(i).shouldBe(visible);
            titleOfParentCategories().get(i).shouldBe(visible);
            parentCategories().get(i).click();
            childCategoriesPopUpOfParentCategory().get(i).shouldBe(visible);
            checkFirstLevelOfParentCategories(i);
        }
        return this;
    }

    @Step("check First Level of parent categories .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkFirstLevelOfParentCategories(int position) {
        if (childCategoriesFirstLevel().get(0).isDisplayed() && visibleIntermediateCategoryFirstLevel().get(0).isDisplayed()) {
            childCategoriesFirstLevel().shouldHave(sizeGreaterThan(0));
            checkVisibleChildCategoriesFirstLevel();
            checkIntermediateChildCategoryFirstLevel(position);
        } else if (childCategoriesFirstLevel().get(0).isDisplayed()) {
            childCategoriesFirstLevel().shouldHave(sizeGreaterThan(0));
            checkVisibleChildCategoriesFirstLevel();
        } else if (visibleIntermediateCategoryFirstLevel().get(0).isDisplayed()) {
            checkIntermediateChildCategoryFirstLevel(position);
        }
        return this;
    }

    @Step("check visible child categories .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkVisibleChildCategoriesFirstLevel() {
        for (int i = 0; i < childCategoriesFirstLevel().size(); i++) {
            imageOfVisibleChildCategoriesFirstLevel().get(i).shouldBe(visible);
            titleOfVisibleChildCategoriesFirstLevel().get(i).shouldBe(visible);
        }
        return this;
    }


    @Step("check intermediate child category first level .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkIntermediateChildCategoryFirstLevel(int position) {
        for (int j = 0; j < intermediateChildCategoriesFirstLevel(position + 1).size(); j++) {
            intermediateChildCategoriesFirstLevel(position + 1).get(j).click();
            secondLevelBlock().should(appear);
            checkSecondLevelOfParentCategories();
        }
        return this;
    }

    @Step("check Second Level of parent categories .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkSecondLevelOfParentCategories() {

        if (visibleChildCategorySecondLevel().get(0).isDisplayed() && visibleIntermediateCategorySecondLevel().get(0).isDisplayed()) {
            visibleChildCategorySecondLevel().shouldHave(sizeGreaterThan(0));
            checkVisibleChildCategoriesSecondLevel();
            checkIntermediateChildCategorySecondLevel();
        } else if (visibleChildCategorySecondLevel().get(0).isDisplayed()) {
            visibleChildCategorySecondLevel().shouldHave(sizeGreaterThan(0));
            checkVisibleChildCategoriesSecondLevel();
        } else if (visibleIntermediateCategorySecondLevel().get(0).isDisplayed()) {
            checkIntermediateChildCategorySecondLevel();
        }
        return this;
    }

    @Step("check visible child categories .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkVisibleChildCategoriesSecondLevel() {
        for (int i = 0; i < visibleChildCategoriesSecondLevel().size(); i++) {
            imageOfVisibleChildCategoriesSecondLevel().get(i).shouldBe(visible);
            titleOfVisibleChildCategoriesSecondLevel().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("check intermediate child category second level .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkIntermediateChildCategorySecondLevel() {
        for (int j = 0; j < intermediateChildCategoriesSecondLevel().size(); j++) {
            intermediateChildCategoriesSecondLevel().get(j).click();
            thirdLevelBlock().should(appear);
            checkThirdLevelOfParentCategories();
        }
        return this;
    }

    @Step("check third Level of parent categories .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkThirdLevelOfParentCategories() {
        childCategoriesThirdLevel().shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("transition to Child category .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic transitionToChildCategory(int parentCategoryPosition, int childCategoryPosition) {
        parentCategories().get(parentCategoryPosition).scrollIntoView("{block: \"start\"}").click();
        childCategoriesPopUpOfParentCategory().get(parentCategoryPosition).shouldBe(visible);
        childCategoriesFirstLevelForCheck().get(childCategoryPosition).shouldBe(visible).click();
        return page(LKW_Category_car_list_page_Logic.class);
    }


    @Step("presence of expected brands in block .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic presenceOfExpectedBrandsInBlock(String brands) {
        brandBlock().shouldBe(visible);
        List<String> brandsList = new ArrayList<>();
        List<String> brandsFromBlock = new ArrayList<>();
        String[] brand = brands.split("\\,");
        Collections.addAll(brandsList, brand);
        for (int i = 0; i < linksOfBrandsFromBlock().size(); i++) {
            brandsFromBlock.add(linksOfBrandsFromBlock().get(i).getAttribute("for").replaceAll("[^0-9]", ""));
        }
        for (int i = 0; i < brandsList.size(); i++) {
            Assert.assertTrue(brandsFromBlock.contains(brandsList.get(i)));
        }
        return this;
    }

    @Step("presence of dangerous products .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic presenceOfDangerousProducts() {
        listOfProductTableView().shouldBe(visible);
        labelTitleDangerousProducts().shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("get signal word from first dangerous product. LKW_Category_car_list_page")
    public String getSignalWordFromFirstDangerousProduct(int positionOfProduct) {
        return getTextFromUnVisibleElement(signalWordOfDangerousProduct().get(positionOfProduct));
    }

    @Step("click on dangerous label of product. LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic clickOnDangerousLabel(int positionOfProduct, String signalWord) {
        dangerousProducts().get(positionOfProduct).scrollIntoView("{block: \"center\"}").hover();
        labelIconDangerousProducts().get(0).shouldBe(visible);
        labelTitleDangerousProducts().get(positionOfProduct).shouldBe(visible).click();
        blackBackground().shouldHave(attribute("style", "display: block;"));
        warningPopUp().shouldBe(visible).shouldHave(attribute("style", "display: block;"));
        if (signalWord.replaceAll("\n", "").trim().equals("Beachten Sie!")) {
            infoTextOfDangerousPopUp().shouldNotBe(empty);
        } else {
            titleOfDangerousPopUp().shouldBe(visible).shouldHave(exactText(signalWord));
            infoTextOfDangerousPopUp().shouldNotBe(empty);
        }
        return this;
    }

    @Step("get id of Dangerous product .LKW_Category_car_list_page")
    public String getIdOfDangerousProduct(int positionOfProduct) {
        return btnAddDangerousProductToWishList().get(positionOfProduct).getAttribute("data-product-id");
    }

    @Step("get signal word from first dangerous product Listing View. LKW_Category_car_list_page")
    public String getSignalWordFromFirstDangerousProductListingView(int positionOfProduct) {
        return signalWordOfDangerousProductListingView().get(positionOfProduct).getText();
    }

    @Step("get attribute of Warning icon in pop-Up .LKW_Category_car_list_page")
    public List<String> getAttributeOfWarningIconInPopUp(int positionOfProduct) {
        List<String> attribute = new ArrayList<>();
        for (int i = 0; i < attributeOfWarningIcon(positionOfProduct + 1).size(); i++) {
            String attributeFromIcon = attributeOfWarningIcon(positionOfProduct + 1).get(i).shouldBe(visible).getAttribute("style").replace("background-image: url(\"", "").replace("\");", "");
            String partOfAttribute = attributeFromIcon.replace(attributeFromIcon.substring(attributeFromIcon.lastIndexOf(".")), "");
            attribute.add(partOfAttribute);
        }
        return attribute;
    }

    @Step("click on dangerous label of product and compare elements. LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic clickOnDangerousLabelAndCompareElements(int positionOfProduct, String signalWord, List<String> attributeOfWarningIcon) {
        btnMoreOfDangerousProducts().get(positionOfProduct).shouldBe(visible).scrollIntoView("{block: \"center\"}").hover().click();
        blackBackground().shouldHave(attribute("style", "display: block;"));
        warningPopUp().shouldBe(visible).shouldHave(attribute("style", "display: block;"));

        if (signalWord.equals("BEACHTEN SIE!")) {
            infoTextOfDangerousPopUp().shouldNotBe(empty);
        } else {
            titleOfDangerousPopUp().shouldBe(visible).shouldHave(exactText(signalWord));
            infoTextOfDangerousPopUp().shouldNotBe(empty);
        }

        List<String> attributeOfDangerousIcon = new ArrayList<>();
        for (int i = 0; i < dangerousIconInWarningPopUp().size(); i++) {
            String urlFromAttribute = dangerousIconInWarningPopUp().get(i).getAttribute("style").replace("background-image: url(\"", "").replace("\");", "");
            String partOfAttribute = urlFromAttribute.replace(urlFromAttribute.substring(urlFromAttribute.lastIndexOf(".")), "");
            attributeOfDangerousIcon.add(partOfAttribute);
        }
        Assert.assertEquals(attributeOfDangerousIcon, attributeOfWarningIcon);
        return this;
    }

    @Step("selecting of installation side  .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic selectInstallationSide(int positionOfSide) {
        installationSideBlock().shouldBe(visible);
        sidesOfInstallation().get(positionOfSide).click();
        appearsOfLoader();
        return this;
    }

    @Step("get generics from sideBar. LKW_Category_car_list_page")
    public List<String> getGenericsFromSideBar() {
        List<String> generics = genericsFromSideBar().stream().map(n -> n.getText()).collect(Collectors.toList());
        return generics;
    }

    @Step("check sorting of products with grey button. LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkSortingOfProductsWithGreyButton(List<String> generics) {
        List<Product> productList = new ArrayList<>();
        addProductToList(productList, productsFromListBlock());
        while (forwardOfListing().isDisplayed()) {
            forwardOfListing().click();
            addProductToList(productList, productsFromListBlock());
        }
        List<Product> listBeforeSorting = new ArrayList<>(productList);

        Comparator<String> genericsComparator = (g1, g2) -> {
            if (!generics.contains(g1)) {
                return 1;
            }
            if (!generics.contains(g2)) {
                return -1;
            }
            return generics.indexOf(g1) - generics.indexOf(g2);
        };
        Comparator<Product> productsComparator = Comparator
                .comparing((Product p) -> "button ".equals(p.getAttributeOfButton()) ? -1 : 0)
                .thenComparing(Product::getGenericOfProduct, genericsComparator);
        productList.sort(productsComparator);
        Assert.assertEquals(listBeforeSorting, productList);
        return this;
    }

    @Step("added product to list. LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic addProductToList(List<Product> activeList, ElementsCollection products) {
        for (int i = 0; i < products.size(); i++) {
            Product product = new Product();
            product.setGenericOfProduct(products.get(i).getAttribute("data-name"));
            product.setAttributeOfButton(attributeOfBtnAddedToBasket().get(i).getAttribute("class"));
            activeList.add(product);
        }
        return this;
    }

    @Step("select any visible brands. LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic selectAnyVisibleBrands(int countOfBrands) {
        for (int i = 0; i < countOfBrands; i++) {
            activeBrands().get(0).shouldBe(visible).click();
            appearsOfLoader();
        }
        return this;
    }

    @Step("get Id of product. LKW_Category_car_list_page")
    public List<String> getIdOfProduct() {
        List<String> id = btnAddProductToWishlist().stream().map(n -> getAttributeFromUnVisibleElement(n, "data-product-id")).collect(Collectors.toList());
        return id;
    }

    @Step("get price title of product. LKW_Category_car_list_page")
    public List<String> getPriceTitle() {
        List<String> priceTitle = priceTitle().stream().map(n -> getTextFromUnVisibleElement(n)).collect(Collectors.toList());
        return priceTitle;
    }

    @Step("Checking the presence of the installation side .LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic checkOfPresenceInstallationSide(String installSide) {
        for (int i = 1; i <= characteristicsBlock().size(); i++) {
            installSideInCharacteristics(i, installSide).shouldBe(exist);
        }
        return this;
    }

    @Step("set Lamp Filter By value. LKW_Category_car_list_page")
    public LKW_Category_car_list_page_Logic setLampFilterByValue(String lampValue) {
        lampDataValue(lampValue).shouldBe(visible).click();
        return this;
    }
}



