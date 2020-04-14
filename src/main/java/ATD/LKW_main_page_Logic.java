package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
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
        listOfParentsDropMainTruckCatalog().shouldHave(sizeNotEqual(0));
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
        markeOfVerticalTruckSelector().selectOptionByValue(markeOfTruck);
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
    public Tyres_page clickTiresCategory() {
        $(byCssSelector("[data-ga-action='23208']")).click();
        return page(Tyres_page.class);
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
    public CarParts_EngineOil_page clickEngineOilCategory() {
        $("[data-ga-action='12094']").click();
        return page(CarParts_EngineOil_page.class);
    }

    @Step("Click on Filters category. LKW_main_page")
    public CarParts_Filters_page clickFiltersCategory() {
        $("[data-ga-action='10105']").click();
        return page(CarParts_Filters_page.class);
    }

    @Step("Click on BrakeSystem category. LKW_main_page")
    public CarParts_BrakeSystem_page clickBrakeSystemCategory() {
        $("[data-ga-action='10106']").click();
        return page(CarParts_BrakeSystem_page.class);
    }

    @Step("Click on Engine category. LKW_main_page")
    public CarParts_Engine_page clickEngineCategory() {
        $(".header-i.header-i--engine").click();
        return page(CarParts_Engine_page.class);
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
        childCategoryInParentCategoryBLock("Ölfilter").click();
        return page(LKW_Category_page_Logic.class);
    }

    @Step("check of transition to Child category without car .LKW_main_page")
    public LKW_Category_car_list_page_Logic goToChildCategoryWithCar() {
        selectTruckInSelector("2242", "8959", "1012748").checkSuccessfullyPageLoading("https://lkwteile.autodoc.de/lastkraftwagen/askam-fargo-desoto/as-950?car_id=1012748");
        back();//.goToLKWMainPage();
        tecDocCatalogOnMainPageLKW().shouldBe(visible).scrollTo();
        childCategoryInParentCategoryBLock("Ölfilter").click();
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
        selectTruckInSelector("120", "11871", "1021805");
        popUpOfSpecification().shouldBe(visible);
        return this;
    }


    @Step("select specification in pop-up of specification .LKW_main_page")
    public LKW_maker_car_list_Logic selectSpecificationInPopUp() {
        specificationInPopUp("37.00").click();
        return page(LKW_maker_car_list_Logic.class);
    }
}
