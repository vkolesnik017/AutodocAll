package ATD;

import Common.DataBase;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ATD.CommonMethods.getCurrentShopFromJSVarInHTML;
import static Common.CommonMethods.checkingContainsUrl;
import static Common.CommonMethods.waitWhileRouteContainsExpectedCondition;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LKW_main_page_Logic extends LKW_main_page {

    @Step("Validation page loading. LKW_main_page")
    public LKW_main_page_Logic checkPagesIsSuccessfulyLoaded() {
        Assert.assertNotEquals(title(), "404");
        return this;
    }

    @Step("Checking of appearance Hint block after click on search field. LKW_main_page")
    public LKW_main_page_Logic checkAppearanceOfHintBlock() {
        searchBar().clear();
        searchBar().click();
        tooltipToSearch().should(appear);
        return this;
    }

    @Step("Checking of appearance Pop-Up block after click on Beispiel link. LKW_main_page")
    public LKW_main_page_Logic checkAppearanceOfBeispielPopUp() {
        infoIconForSearch().click();
        infoPopupForSearch().should(appear);
        return this;
    }

    @Step("Checking of appearance Pop-Up block after click on Beispiel link. LKW_main_page")
    public LKW_main_page_Logic checkingOfVisibilityOfLogoInHeader() {
        logoInHeader().shouldBe(visible);
        return this;
    }


    @Step("Checking of all categories of Trucks catalog drop menu. LKW_main_page")
    public LKW_main_page_Logic checkingOfAllCategoriesOfMainBlockTruckCatalog() {
        logoInHeader().shouldBe(visible);
        menuCatalogInHeader().click();
        dropMainMenuTrucksCatalogInHeader().shouldBe(visible);
        listOfParentsDropMainTruckCatalog().shouldHaveSize(33);
        checkOfChildBlocksSecondAndThirdLevel(categoriesOfSecondBlockWitnDropMenu(), thirdLevelDropMainMenuTrucksCatalogInHeader(), titleOfThirdLevelMainDropMenuTruckCatalog(), categoriesOfThirdBlock());
        return this;
    }

    @Step("Checking of child blocks of second and third level. LKW_main_page")
    private LKW_main_page_Logic checkOfChildBlocksSecondAndThirdLevel(ElementsCollection nameOfcategoriesOfSecondBlock,
                                                                      SelenideElement thirdChildBlock, ElementsCollection nameOfCategoriesOfThirdBlock, ElementsCollection sizeOfCategoriesOfThirdBlock) {

        for (int i = 0; i < listOfParentsDropMainTruckCatalog().size(); i++) {
            listOfParentsDropMainTruckCatalog().get(i).scrollIntoView("{block: \"end\"}").hover();
            if (listOfParentsDropMainTruckCatalog().get(i).getText().equals("Reifen")) {
                continue;
            }
            secondLevelDropMainMenuTrucksCatalogInHeader().should(appear);
            titleOfSecondLevelMainDropMenuTruckCatalog().get(0).shouldHave(exactText(listOfParentsDropMainTruckCatalog().get(i).getText()).because(titleOfSecondLevelMainDropMenuTruckCatalog().get(0).getText() + " - title of the second block doesn't match the selected category of main block"));
            imageOfSecondLevelMainDropMenuTruckCatalog().get(0).shouldBe(visible);
            listOfCategoriesSecondLevelDropMainTruckCatalog().shouldHave(sizeNotEqual(0).because(titleOfSecondLevelMainDropMenuTruckCatalog().get(0).getText() + " - second block is empty"));
            checkThirdBlock(nameOfcategoriesOfSecondBlock, thirdChildBlock, nameOfCategoriesOfThirdBlock, sizeOfCategoriesOfThirdBlock);
        }
        return this;
    }

    @Step("Checking of  third level of Trucks catalog drop menu. LKW_main_page")
    private LKW_main_page_Logic checkThirdBlock(ElementsCollection titleOfCategoriesOfSecondBlock,
                                                SelenideElement thirdBlock, ElementsCollection titleOfCategoriesOfThirdLevel,
                                                ElementsCollection listOfCategoriesThirdBlock) {

        if (titleOfCategoriesOfSecondBlock.get(0).isDisplayed()) {
            for (int j = 0; j < titleOfCategoriesOfSecondBlock.size(); j++) {
                titleOfCategoriesOfSecondBlock.get(j).scrollIntoView("{block: \"end\"}").hover();
                thirdBlock.should(appear);
                titleOfCategoriesOfThirdLevel.get(0).shouldHave(exactText(titleOfCategoriesOfSecondBlock.get(j).getText())
                        .because(titleOfCategoriesOfThirdLevel.get(0).getText() + " - title of the third block doesn't match the selected category of second block"));
                listOfCategoriesThirdBlock.shouldHave(sizeNotEqual(0));
                checkFourthBlock();
            }
        }
        return this;
    }

    @Step("Checking of  fourth level of Trucks catalog drop menu. LKW_main_page")
    private LKW_main_page_Logic checkFourthBlock() {

        if (categoriesOfThirdBlockWitnDropMenu().get(0).isDisplayed()) {
            for (int i = 0; i < categoriesOfThirdBlockWitnDropMenu().size(); i++) {
                categoriesOfThirdBlockWitnDropMenu().get(i).scrollIntoView("{block: \"end\"}").hover();
                fourthLevelDropMainMenuTrucksCatalogInHeader().should(appear);
                titleOfFourthLevelMainDropMenuTruckCatalog().get(0).shouldHave(exactText(categoriesOfThirdBlockWitnDropMenu().get(i).getText())
                        .because(titleOfFourthLevelMainDropMenuTruckCatalog().get(0).getText() + "- title of the fourth block doesn't match the selected category of third block"));
            }
        } else {
            categoriesOfThirdBlockWithOutDropMenu().shouldHave(sizeNotEqual(0).because(titleOfFourthLevelMainDropMenuTruckCatalog().get(0) + " - fourth block is empty"));
        }
        return this;
    }


    @Step("Checking of child category with and without car. LKW_main_page")
    public LKW_main_page_Logic checkingChildCategoryOlfilter() {
        selectChildCategoryWithOutCar()
                .selectChildCategoryWithCar();
        return this;
    }

    @Step("Checking of child category without car. LKW_main_page")
    private LKW_main_page_Logic selectChildCategoryWithOutCar() {
        selectingOlifilterModel();
        Assert.assertEquals(url(), "https://lkwteile.autodoc.de/ersatzteile/olfilter-200157");
        return this;
    }

    @Step("Checking of child category category with car. LKW_main_page")
    private LKW_main_page_Logic selectChildCategoryWithCar() {
        verticalTruckSelector().shouldBe(visible);
        markeOfVerticalTruckSelector().selectOptionByValue("69");
        modelOfVerticalTruckSelector().selectOptionByValue("9539");
        motorOfVerticalTruckSelector().selectOptionByValue("1013205");
        buttonSuchenOfVerticaltruckSelector().submit();
        logoInHeader().shouldBe(visible).click();
        menuCatalogInHeader().shouldBe(visible);
        menuCatalogInHeader().click();
        dropMainMenuTrucksCatalogInHeader().should(appear);
        listOfParentsDropMainTruckCatalog().shouldHave(sizeNotEqual(0));
        selectingOlifilterModel();
        Assert.assertEquals(url(), "https://lkwteile.autodoc.de/ersatzteile/olfilter-200157/man/cla?car_id=1013205");
        return this;
    }

    @Step("Selecting Olifilter category in DropMenu Truck Catalog. LKW_main_page")
    private LKW_main_page_Logic selectingOlifilterModel() {
        parentCategoryFilter().scrollIntoView("{block: \"end\"}").hover();
        secondLevelDropMainMenuTrucksCatalogInHeader().should(appear);
        childCategoryOlfilter().shouldBe(visible).click();
        logoInHeader().shouldBe(visible);
        return this;

    }

    @Step("Select truck in vertical selector. LKW_main_page")
    public LKW_Catalog_page_Logic selectTruckInSelector(String markeOfTruck, String modelOfTruck, String motorOfTruck) {
        markeOfVerticalTruckSelector().shouldBe(visible).selectOptionByValue(markeOfTruck);
        modelOfVerticalTruckSelector().selectOptionByValue(modelOfTruck);
        motorOfVerticalTruckSelector().selectOptionByValue(motorOfTruck);
        buttonSuchenOfVerticaltruckSelectorMainPage().click();
        return page(LKW_Catalog_page_Logic.class);
    }

    // Menu in header
    @Step("Click on PKW category. LKW_main_page")
    public Main_page clickPkwCategory() {
        $("[data-ga-action='PKW']").click();
        return page(Main_page.class);
    }

    @Step("Click on Moto category. LKW_main_page")
    public Moto_main_page clickMotoCategory() {
        $("[data-ga-action='MOTO']").click();
        return page(Moto_main_page.class);
    }

    @Step("Click on Tires category. LKW_main_page")
    public LKW_Tyres_page_Logic clickTiresCategory() {
        $(byCssSelector("[data-ga-action='23208']")).click();
        return page(LKW_Tyres_page_Logic.class);
    }

    @Step("Click on Instrument category. LKW_main_page")
    public Index_instruments_page clickInstrumentsCategory() {
        $("[data-ga-action='36000']").click();
        return page(Index_instruments_page.class);
    }

    @Step("Click on Accessories category. LKW_main_page")
    public Index_accessories_page clickAccessoriesCategory() {
        $("[data-ga-action='33000']").click();
        return page(Index_accessories_page.class);
    }

    @Step("Click on EngineOil category. LKW_main_page")
    public LKW_Category_page_Logic clickEngineOilCategory() {
        $("[data-ga-action='290001']").click();
        return page(LKW_Category_page_Logic.class);
    }

    @Step("Click on Filters category. LKW_main_page")
    public LKW_Parent_Category_page_Logic clickFiltersCategory() {
        $("[data-ga-action='200047']").click();
        return page(LKW_Parent_Category_page_Logic.class);
    }

    @Step("Click on BrakeSystem category. LKW_main_page")
    public CarParts_BrakeSystem_page clickBrakeSystemCategory() {
        $("[data-ga-action='200058']").click();
        return page(CarParts_BrakeSystem_page.class);
    }

    @Step("Click on Engine category. LKW_main_page")
    public LKW_Parent_Category_page_Logic clickEngineCategory() {
        $(".header-i.header-i--engine").click();
        return page(LKW_Parent_Category_page_Logic.class);
    }

    @Step("Check successfully LKW page loading. LKW_main_page")
    public LKW_main_page_Logic checkSuccessfullyLKWPageLoading() {
        menuCatalogInHeader().shouldBe(visible);
        Assert.assertTrue(url().contains("https://lkwteile.autodoc.de/"));
        return this;
    }

    @Step("Checking that selector is empty. LKW_main_page")
    public LKW_main_page_Logic checkOfEmptySelector() {
        markeOfVerticalTruckSelector().shouldHave(exactValue("0"));
        return this;
    }

    @Step("Select child category on LKW main page. LKW_main_page")
    public LKW_Category_page_Logic selectChildCategory() {
        tecDocCatalogOnMainPageLKW().scrollTo();
        childCategoryOnMainPage().click();
        return page(LKW_Category_page_Logic.class);
    }

    @Step("visibility of Top parent block and his elements .LKW_main_page")
    public LKW_main_page_Logic visibilityOfTopParentBlock() {
        tecDocCatalogOnMainPageLKW().shouldBe(visible);
        titleOfTecDocCatalog().shouldBe(visible);
        topCatalogBlock().shouldBe(visible);
        linkOnCatalogPage().shouldBe(visible);
        return this;
    }

    @Step("check of main elements in Parent category block .LKW_main_page")
    public LKW_main_page_Logic checkMainElementsOfParentCategoryBlock() {
        topParentCategories().shouldHave(size(12));

        for (int i = 0; i < topParentCategories().size(); i++) {
            imageOfTopParentCategoryInParentBlock().get(i).shouldBe(visible);
            titleOfTopParentCategoryInParentBlock().get(i).shouldBe(visible);
            linksOfChildCategoriesBlock().get(i).shouldBe(visible);
            if (i == 0) {
                linksOfChildCategories(i + 1).shouldHaveSize(3);
            }
        }
        return this;
    }

    @Step("check of transition to Child category .LKW_main_page")
    public LKW_main_page_Logic checkOfTransitionToChildCategory() {
        goToChildCategoryWithOutCar().checkSuccessfullyChildCategoryPageLoading();
        back();
        goToChildCategoryWithCar().checkSuccessfullyLKWCategoryCarListPageLoading("https://lkwteile.autodoc.de/ersatzteile/olfilter-200157/askam-fargo-desoto/as-950?car_id=1012748");
        back();
        return this;
    }

    @Step("check of transition to Child category without car .LKW_main_page")
    public LKW_Category_page_Logic goToChildCategoryWithOutCar() {
        childCategoryInParentCategoryBLock("??lfilter").scrollIntoView("{block: \"center\"}").click();
        return page(LKW_Category_page_Logic.class);
    }

    @Step("check of transition to Child category without car .LKW_main_page")
    public LKW_Category_car_list_page_Logic goToChildCategoryWithCar() {
        selectTruckInSelector("2242", "8959", "1012748").checkSuccessfullyPageLoading("https://lkwteile.autodoc.de/lastkraftwagen/askam-fargo-desoto/as-950?car_id=1012748");
        back();
        tecDocCatalogOnMainPageLKW().shouldBe(visible).scrollTo();
        childCategoryInParentCategoryBLock("??lfilter").click();
        return page(LKW_Category_car_list_page_Logic.class);
    }

    @Step("check of transition to Parent category .LKW_main_page")
    public LKW_main_page_Logic checkOfTransitionToParentCategory() {
        clickOnImageOfParentCategory().checkSuccessfullyLKWParentCategoryPageLoading("https://lkwteile.autodoc.de/ersatzteile/filter");
        back();
        return this;
    }

    @Step("click on image Of parent category .LKW_main_page")
    public LKW_Parent_Category_page_Logic clickOnImageOfParentCategory() {
        imageOfFilterParentCategory().click();
        return page(LKW_Parent_Category_page_Logic.class);
    }


    @Step("check of transition to Catalog .LKW_main_page")
    public LKW_Categories_page_Logic checkOfTransitionToCatalog() {
        linkOnCatalogPage().click();
        return page(LKW_Categories_page_Logic.class);
    }

    @Step("check of visibility  headline in Top brands block .LKW_main_page")
    public LKW_main_page_Logic visibilityHeadlineOfTopBrandsBlock() {
        titleOfTopBrandsBlock().shouldBe(visible);
        return this;
    }


    @Step("check of visibility  Top brands block .LKW_main_page")
    public LKW_main_page_Logic visibilityOfTopBrandsBlock() {
        brandsInTopBrandsBlock().shouldHaveSize(8);
        for (int i = 0; i < brandsInTopBrandsBlock().size(); i++) {
            brandsInTopBrandsBlock().get(i).shouldNotHave(attribute("href"));
        }
        return this;
    }

    @Step("availability of vertical truck selector .LKW_main_page")
    public LKW_main_page_Logic availabilityOfVerticalTruckSelector() {
        verticalTruckSelector().shouldBe(visible);
        return this;
    }


    @Step("availability of pop-up specification .LKW_main_page")
    public LKW_main_page_Logic visibilityOfPopUpSpecification() {
        selectTruckInSelector("120", "11871", "1021789");
        popUpOfSpecification().should(appear);
        return this;
    }


    @Step("select specification in pop-up of specification .LKW_main_page")
    public LKW_maker_car_list_Logic selectSpecificationInPopUp() {
        specificationInPopUp("27.00").click();
        return page(LKW_maker_car_list_Logic.class);
    }

    @Step("go to lkw_makers route .LKW_main_page")
    public LKW_makers_page_Logic goToAllTruckBrandsPage() {
        btnAllTruckBrands().scrollIntoView("{block: \"center\"}").click();
        return page(LKW_makers_page_Logic.class);
    }

    @Step("visibility of headline of selector and icon of truck  .LKW_main_page")
    public LKW_main_page_Logic visibilityOfHeadLineSelectorAndIconOfTruck(String selectTruck) {
        iconOfTruckInHeadlineOfSelector().shouldBe(visible);
        titleOfTruckInHeadlineOfSelector().shouldHave(exactText(selectTruck));
        return this;
    }

    @Step("select brand of car in vertical truck selector .LKW_main_page")
    public LKW_main_page_Logic selectBrandOfCarInVerticalSelector(String valueOfBrand) {
        markeOfVerticalTruckSelector().shouldBe(visible).selectOptionByValue(valueOfBrand);
        markeOfVerticalTruckSelector().shouldHave(value(valueOfBrand));
        return this;
    }

    @Step("reset of car brand field in vertical selector .LKW_main_page")
    public LKW_main_page_Logic resetOfCarBrandFieldInVerticalSelector() {
        String currentUrl = url();
        resetBtnInVerticalCarSelector().shouldBe(visible).click();
        Assert.assertTrue(currentUrl.equals(url()));
        arrowForMarkeFiled().shouldBe(visible);
        checkDefaultValuesInVerticalSelector();
        return this;
    }

    @Step("checking default values in vertical selector .LKW_main_page")
    public LKW_main_page_Logic checkDefaultValuesInVerticalSelector() {
        markeOfVerticalTruckSelector().shouldHave(exactValue("0"));
        modelOfVerticalTruckSelector().shouldHave(exactValue("0"));
        return this;
    }

    @Step("availability of headline of brands model block .LKW_main_page")
    public LKW_main_page_Logic availabilityOfHeadlineOfBrandsModelBlock() {
        truckBrandsBlock().shouldBe(visible);
        headlineOfTruckBrandsBlock().shouldBe(visible);
        return this;
    }

    @Step("availability of headline of brands model block .LKW_main_page")
    public LKW_main_page_Logic availabilityOfAutoPartsTopBrandsBlock() {
        truckBrandsBlock().shouldBe(visible);
        brandsOfTruckInTopBrandsBlock().shouldHaveSize(8);
        return this;
    }

    @Step("check transition at icon of truck brands in TOP brands block .LKW_main_page")
    public LKW_Categories_maker_page_Logic checkTransitionAtIconOfTruckBrand() {
        brandOfTruckInTopBlock("MERCEDES-BENZ").click();
        return page(LKW_Categories_maker_page_Logic.class);
    }

    @Step("visibility of headline of TOP products block .LKW_main_page")
    public LKW_main_page_Logic visibilityOfHeadlineOfTopProductsBlock() {
        headlineOfTopProductsBlock().shouldBe(visible);
        return this;
    }

    @Step("availability of top list block and top products .LKW_main_page")
    public LKW_main_page_Logic availabilityOfTopProductsBlock() {
        topProductsBlock().shouldBe(visible);
        productsInTopBlock().shouldHaveSize(12);
        return this;
    }

    @Step("availability of forward and back links of TOP products block .LKW_main_page")
    public LKW_main_page_Logic availabilityOfForwardBackLinksOfTopBLock() {
        String currentArtNumOfTopProduct = visibleArtNumOfTopProducts().get(0).getText();
        visibleArtNumOfTopProducts().shouldHaveSize(6);
        forwardLinkOfTopBLock().shouldBe(visible).click();
        visibleArtNumOfTopProducts().get(0).shouldNotHave(exactText(currentArtNumOfTopProduct));
        visibleArtNumOfTopProducts().shouldHaveSize(6);
        currentArtNumOfTopProduct = visibleArtNumOfTopProducts().get(0).getText();
        backLinkOfTopBLock().shouldBe(visible).click();
        visibleArtNumOfTopProducts().get(0).shouldNotHave(exactText(currentArtNumOfTopProduct));
        return this;
    }

    @Step("get id of product in Top products block .LKW_main_page")
    public String getIdOfTopProduct() {
        String idOfProduct = btnAddToBasketTopBLock(1).getAttribute("id");
        return idOfProduct;
    }

    @Step("added top product to basket .LKW_main_page")
    public Cart_page_Logic addTopProductToBasket() {
        btnAddToBasketTopBLock(1).shouldBe(visible).click();
        basketDropMenu().should(appear);
        basketDropMenu().should(disappear);
        basket().click();
        return page(Cart_page_Logic.class);
    }

    @Step("visibility of addition information when hover on the product in Top block .LKW_main_page")
    public LKW_main_page_Logic visibilityOfAdditionInfoInTopBlock() {
        topProductsBlock().scrollIntoView("{block: \"end\"}");
        for (int i = 0; i < imageOfTopProducts().size(); i++) {
            linkOnCatalogPage().hover();
            imageOfTopProducts().get(i).hover();
            additionInfoBlockOfTopProduct().get(0).should(appear);
            if (i == 5) {
                forwardLinkOfTopBLock().click();
            }
        }
        return this;
    }

    @Step("transition to product page by click on top image of product .LKW_main_page")
    public LKW_main_page_Logic transitionToProductPageByClickOnTopImage() {
        clickOnImageOfTopProduct().checkSuccessfullyLKWProductPageLoading("autodoc.de/mann-filter/");
        back();
        return this;
    }

    @Step("click on image of top product .LKW_main_page")
    public LKW_Product_page_Logic clickOnImageOfTopProduct() {
        if (!imageOfTopProductWithSelectedTitle("MANN-FILTER").isDisplayed()) {
            forwardLinkOfTopBLock().click();
        }
        imageOfTopProductWithSelectedTitle("MANN-FILTER").shouldBe(visible).click();
        return page(LKW_Product_page_Logic.class);
    }

    @Step("transition to product page by click on title of top product .LKW_main_page")
    public LKW_main_page_Logic transitionToProductPageByClickOnTitleOfTopProduct() {
        if (closeCookiesPopUp().isDisplayed()) {
            closeCookiesPopUp().click();
        }
        clickOnTitleOfTopProduct().checkSuccessfullyLKWProductPageLoading("autodoc.de/mann-filter/");
        back();
        return this;
    }

    @Step("click on title of top product .LKW_main_page")
    public LKW_Product_page_Logic clickOnTitleOfTopProduct() {
        if (!imageOfTopProductWithSelectedTitle("MANN-FILTER").isDisplayed()) {
            forwardLinkOfTopBLock().click();
        }
        topProductWithSelectedTitle("MANN-FILTER").shouldBe(visible).click();
        return page(LKW_Product_page_Logic.class);
    }

    @Step("transition to product page by click on link Details .LKW_main_page")
    public LKW_main_page_Logic transitionToProductPageByClickOnLinkDetails() {
        if (closeCookiesPopUp().isDisplayed()) {
            closeCookiesPopUp().click();
        }
        topProductsBlock().scrollIntoView("{block: \"center\"}");
        clickOnLinkDetails().checkSuccessfullyLKWProductPageLoading("autodoc.de/mann-filter/");
        back();
        return this;
    }

    @Step("click on Details link .LKW_main_page")
    public LKW_Product_page_Logic clickOnLinkDetails() {
        if (!imageOfTopProductWithSelectedTitle("MANN-FILTER").isDisplayed()) {
            forwardLinkOfTopBLock().click();
        }
        topProductWithSelectedTitle("MANN-FILTER").shouldBe(visible).hover();
        additionInfoBlockOfTopProductWithSelectedTitle("MANN-FILTER").shouldBe(visible);
        linkDetailsWithSelectedTitle("MANN-FILTER").shouldBe(visible).click();
        return page(LKW_Product_page_Logic.class);
    }


    @Step("visibility of sales banner .LKW_main_page")
    public LKW_main_page_Logic visibilityOfSalesBanner() {
        salesBanner().shouldBe(visible);
        return this;
    }

    @Step("Checking countries subscription from footer country list  .LKW_main_page")
    public LKW_main_page_Logic checkingCountriesSubscription() throws SQLException {
        DataBase db = new DataBase("ATD");
        String currentCountry;
        List<String> language = Arrays.asList("CH", "AT", "LD", "BG", "BE", "BE_fr", "CZ", "DK", "EE", "ES", "FI", "FR", "EN", "GR", "HU", "IT", "LT", "LV", "NL", "NO", "PL", "PT", "RO", "SE", "SI", "SK");
        int sizeOfLanguage = languagesOfSubscribe().size();
        for (int i = 0; i < sizeOfLanguage; i++) {
            if (language.get(i).equals("BE_fr")) {
                continue;
            } else {
                if (language.get(i).equals("LD")) {
                    currentCountry = "lu";
                } else if (language.get(i).equals("EN")) {
                    currentCountry = "uk";
                } else {
                    currentCountry = language.get(i).toLowerCase();
                }
                languageBlock().shouldBe(exist).scrollIntoView("{block: \"center\"}");
                languageBlock().click();
                languageListBlock().shouldBe(visible);
                languagesOfSubscribe().get(i).scrollIntoView("{block: \"end\"}");
                languagesOfSubscribe().get(i).click();
                waitWhileRouteContainsExpectedCondition(currentCountry);
                String urlFromBD = db.getFullRouteByRouteName("subprod", language.get(i), "lkw_main") + "/";
                Assert.assertEquals(url(), urlFromBD);
                back();
                waitWhileRouteContainsExpectedCondition(db.getRouteByRouteName("DE", "lkw_main"));
            }
        }
        return this;
    }

    @Step("Checks open and close footer droplist with countries .LKW_main_page")
    public LKW_main_page_Logic checkOpenAndCloseDropListCountries() {
        footerForm().scrollTo();
        languageBlock().click();
        dropDownCountry().shouldHave(attribute("style", "visibility: visible;"));
        languageBlock().click();
        dropDownCountry().shouldHave(attribute("style", "visibility: hidden;"));
        return this;
    }

    @Step("Select truck in vertical selector. LKW_main_page")
    public LKW_maker_car_list_Logic selectTruckInVerticalSelector(String markeOfTruck, String modelOfTruck, String motorOfTruck) {
        markeOfVerticalTruckSelector().selectOptionByValue(markeOfTruck);
        modelOfVerticalTruckSelector().selectOptionByValue(modelOfTruck);
        motorOfVerticalTruckSelector().selectOptionByValue(motorOfTruck);
        buttonSuchenOfVerticaltruckSelectorMainPage().click();
        return page(LKW_maker_car_list_Logic.class);
    }


    @Step("check Navigation categories in header .LKW_main_page")
    public LKW_main_page_Logic checkNavigationCategoriesInHeader() throws SQLException {
        DataBase db = new DataBase("ATD");
        String shop = getCurrentShopFromJSVarInHTML();
        clickPkwCategory();
        checkingContainsUrl(db.getRouteByRouteName(shop, "main"));
        back();
        clickMotoCategory();
        checkingContainsUrl(db.getRouteByRouteName(shop, "moto_main"));
        back();
        clickTiresCategory();
        checkingContainsUrl(db.getRouteByRouteName(shop, "lkw_tyres"));
        back();
        clickInstrumentsCategory();
        checkingContainsUrl(db.getRouteByRouteName(shop, "index_instruments"));
        back();
        clickAccessoriesCategory();
        checkingContainsUrl(db.getRouteByRouteName(shop, "index_accessories"));
        back();
        clickEngineOilCategory();
        checkingContainsUrl(db.getRouteByRouteName(shop, "lkw_category4"));
        back();
        clickFiltersCategory();
        checkingContainsUrl(db.getRouteByRouteName(shop, "lkw_parent_category"));
        back();
        clickBrakeSystemCategory();
        checkingContainsUrl(db.getRouteByRouteName(shop, "lkw_parent_category2"));
        back();
        clickEngineCategory();
        checkingContainsUrl(db.getRouteByRouteName(shop, "lkw_parent_category3"));
        return this;
    }

    @Step("presence of Oil parent category in header catalog .LKW_main_page")
    public LKW_main_page_Logic presenceOfOilParentCategoryInHeaderCatalog(String subRoute) throws SQLException {
        menuCatalogInHeader().click();
        dropMainMenuTrucksCatalogInHeader().shouldBe(visible);
        listOfParentsDropMainTruckCatalog().get(0).shouldHave(exactText("??le & Fl??ssigkeiten"));
        Assert.assertTrue(listOfParentsDropMainTruckCatalog().get(0).getAttribute("href").contains(new DataBase("ATD").getRouteByRouteName("DE", subRoute)));
        return this;
    }

    @Step("click on Oil child category .LKW_main_page")
    public LKW_Category_page_Logic clickOnOilChildCategory(int childPosition) {
        listOfCategoriesSecondLevelDropMainTruckCatalog().get(childPosition).shouldBe(visible).click();
        return page(LKW_Category_page_Logic.class);
    }

    @Step("presence of Oil category .LKW_main_page")
    public LKW_main_page_Logic presenceOfOilCategory() {
        imageOfTopParentCategoryInParentBlock().get(0).shouldBe(visible);
        titleOfTopParentCategoryInParentBlock().get(0).shouldBe(visible).shouldHave(exactText("??le & Fl??ssigkeiten"));
        return this;
    }

    @Step("check transition by click on Oil parent elements .LKW_main_page")
    public LKW_main_page_Logic checkTransitionByClickOnOilParentElements() throws SQLException {
        DataBase db = new DataBase("ATD");
        clickOnOilTitleParentCategory();
        checkingContainsUrl(db.getRouteByRouteName("DE", "lkw_parent_category4"));
        back();
        clickOnOilTitleChildCategory("Motor??l");
        checkingContainsUrl(db.getRouteByRouteName("DE", "lkw_category4"));
        back();
        clickOnOilTitleChildCategory("Getriebe??l");
        checkingContainsUrl(db.getRouteByRouteName("DE", "lkw_category8"));
        back();
        clickOnOilTitleChildCategory("Hydraulik??l");
        checkingContainsUrl(db.getRouteByRouteName("DE", "lkw_category7"));
        return this;
    }

    @Step("click on Oil title parent category .LKW_main_page")
    public LKW_Parent_Category_page_Logic clickOnOilTitleParentCategory() {
        titleOfTopParentCategoryInParentBlock().get(0).click();
        return page(LKW_Parent_Category_page_Logic.class);
    }

    @Step("click on Oil title child category .LKW_main_page")
    public LKW_Category_page_Logic clickOnOilTitleChildCategory(String titleOfChildCategory) {
        childCategory(titleOfChildCategory).shouldBe(visible).click();
        return page(LKW_Category_page_Logic.class);
    }

    @Step("click on Garage icon in header. LKW_main_page")
    public LKW_main_page_Logic clickOnGarageIconInHeader() {
        headerGarageIcon().shouldBe(visible).click();
        popUpOfGarageInHeader().shouldBe(visible);
        return this;
    }

    @Step("click on Garage icon in header. LKW_main_page")
    public LKW_main_page_Logic selectVehicleInGaragePopUp(String idOfVehicle) {
        idOfVehicleInGaragePopUp(idOfVehicle).shouldBe(visible).click();
        return this;
    }

    @Step("check expected values in selector. LKW_main_page")
    public LKW_main_page_Logic checkValuesInSelector(String marke, String model, String motor) {
        markeOfVerticalTruckSelector().shouldHave(value(marke));
        modelOfVerticalTruckSelector().shouldHave(value(model));
        motorOfVerticalTruckSelector().shouldHave(value(motor));
        return this;
    }

    @Step("Login in header with mail {mail} and transition to profile plus page and go back. LKW_main_page")
    public LKW_main_page_Logic loginToProfilePlusPageAndBack(String email) {
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(email).visibilityOfUsersName();
        back();
        return this;
    }

    @Step("update of page. LKW_main_page")
    public LKW_main_page_Logic updateOfPage() {
        refresh();
        return this;
    }

    @Step("update of page. LKW_main_page")
    public LKW_main_page_Logic checkCountOfVehicleInIconOfGarage(String expectedCountOfVehicle) {
        countOfVehicleInIconOfGarageInHeader().shouldBe(visible).shouldHave(exactText(expectedCountOfVehicle));
        return this;
    }

    @Step(": from. LKW_main_page")
    public LKW_main_page_Logic checksIfHintsInTheSearchFieldMatchByValue() {
        new Main_page_Logic().checksIfHintsInTheSearchFieldMatchByValue();
        return this;
    }

    @Step("click on example icon in search bar. LKW_main_page")
    public LKW_main_page_Logic clickOnExampleIconInSearchBar() {
        exampleIcon().shouldBe(visible).click();
        exampleInfoPopUp().shouldBe(visible);
        return this;
    }

    @Step("check Values In Example Information pop-up. LKW_main_page")
    public LKW_main_page_Logic checkValuesInExampleInfoPopUp() {
        headLineInfoExamplePopUp().shouldBe(visible).shouldHave(text("2 500 000 Autoteile ??? Finde das richtige Ersatzteil f??r dein Auto"));
        checkFullnessOfElements(autoPartsInInfoExamplePopUp());
        checkFullnessOfElements(artNumInfoExamplePopUp());
        checkFullnessOfElements(oenInfoExamplePopUp());
        return this;
    }

    @Step("check fullness of elements. LKW_main_page")
    public LKW_main_page_Logic checkFullnessOfElements(ElementsCollection list) {
        for (SelenideElement e : list) {
            e.shouldNotBe(empty);
        }
        return this;
    }

    @Step("close Example information pop-up. LKW_main_page")
    public LKW_main_page_Logic closeExampleInfoPopUp() {
        btnCloseInfoExamplePopUp().shouldBe(visible).click();
        return this;
    }

    @Step("click on invisible main logo. LKW_main_page")
    public LKW_main_page_Logic clickOnInvisibleMainLogo() {
        executeJavaScript("arguments[0].click();", logoInHeader());
        return this;
    }

    @Step(" presence Child categories In Parent Category. LKW_main_page")
    public LKW_main_page_Logic presenceChildInParentCategory(List<String> childCategory, String parentCategory) {
        List<String> childFront = childOfParentCategory(parentCategory).stream().map(title -> title.getText()).collect(Collectors.toList());
        Assert.assertEquals(childFront, childCategory);
        return this;
    }

    @Step("check Of Transition To Child Category From Header catalog. LKW_main_page")
    public LKW_main_page_Logic checkOfTransitionToChildCategoryFromHeader() throws SQLException {
        DataBase db = new DataBase("ATD");
        for (int i = 0; i < 5; i++) {
            menuCatalogInHeader().click();
            dropMainMenuTrucksCatalogInHeader().shouldBe(visible);
            listOfParentsDropMainTruckCatalog().get(0).shouldBe(visible).hover();
            titleOfSecondLevelMainDropMenuTruckCatalog().get(0).shouldHave(exactText(listOfParentsDropMainTruckCatalog().get(0).getText()));
            listOfCategoriesSecondLevelDropMainTruckCatalog().shouldHaveSize(5);
            clickOnOilChildCategory(i);
            switch (i) {
                case 0:
                    checkingContainsUrl(db.getRouteByRouteName("DE", "lkw_category6"));
                    break;
                case 1:
                    checkingContainsUrl(db.getRouteByRouteName("DE", "lkw_category8"));
                    break;
                case 2:
                    checkingContainsUrl(db.getRouteByRouteName("DE", "lkw_category7"));
                    break;
                case 3:
                    checkingContainsUrl(db.getRouteByRouteName("DE", "lkw_category4"));
                    break;
                case 4:
                    checkingContainsUrl(db.getRouteByRouteName("DE", "listing_chemicals6"));
                    break;
                default:
                    break;
            }
            back();
        }
        return this;
    }
}
