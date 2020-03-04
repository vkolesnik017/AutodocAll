package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LKW_main_page_Logic extends LKW_main_page {

    @Step("Validation page loading")
    public LKW_main_page_Logic checkPagesIsSuccessfulyLoaded() {
        Assert.assertNotEquals(title(), "404");
        return this;
    }

    @Step("checking of appearance Hint block after click on search field")
    public LKW_main_page_Logic checkAppearanceOfHintBlock() {
        searchBar().clear();
        searchBar().click();
        tooltipToSearch().should(appear);
        return this;
    }

    @Step("checking of appearance Pop-Up block after click on Beispiel link")
    public LKW_main_page_Logic checkAppearanceOfBeispielPopUp() {
        infoIconForSearch().click();
        infoPopupForSearch().should(appear);
        return this;
    }

    @Step("checking of appearance Pop-Up block after click on Beispiel link")
    public LKW_main_page_Logic checkingOfVisibilityOfLogoInHeader() {
        logoInHeader().shouldBe(visible);
        return this;
    }


    @Step("checking of all categories of Trucks catalog drop menu")
    public LKW_main_page_Logic checkingOfAllCategoriesOfMainBlockTruckCatalog() {
        logoInHeader().shouldBe(visible);
        closeBtnOfCookiesPopUp().click();
        menuCatalogInHeader().click();
        dropMainMenuTrucksCatalogInHeader().shouldBe(visible);
        listOfParentsDropMainTruckCatalog().shouldHave(sizeNotEqual(0));
        checkOfChildBlocksSecondAndThirdLevel(categoriesOfSecondBlockWitnDropMenu(), thirdLevelDropMainMenuTrucksCatalogInHeader(), titleOfThirdLevelMainDropMenuTruckCatalog(), categoriesOfThirdBlock());
        return this;
    }

    @Step("checking of child blocks of second and third level")
    public LKW_main_page_Logic checkOfChildBlocksSecondAndThirdLevel(ElementsCollection nameOfcategoriesOfSecondBlock,
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

    @Step("checking of  third level of Trucks catalog drop menu")
    public LKW_main_page_Logic checkThirdBlock(ElementsCollection titleOfCategoriesOfSecondBlock,
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

    @Step("checking of  fourth level of Trucks catalog drop menu")
    public LKW_main_page_Logic checkFourthBlock() {

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


    @Step("checking of child category with and without car")
    public LKW_main_page_Logic checkingChildCategoryOlfilter() {
        selectChildCategoryWithOutCar()
                .selectChildCategoryWithCar();
        return this;
    }

    @Step("checking of child category without car")
    public LKW_main_page_Logic selectChildCategoryWithOutCar() {
        selectingOlifilterModel();
        Assert.assertEquals(url(), "https://lkwteile.autodoc.de/ersatzteile/olfilter-200157");
        return this;
    }

    @Step("checking of child category category with car")
    public LKW_main_page_Logic selectChildCategoryWithCar() {
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

    @Step(" selecting Olifilter category in DropMenu Truck Catalog ")
    public LKW_main_page_Logic selectingOlifilterModel() {
        parentCategoryFilter().scrollIntoView("{block: \"end\"}").hover();
        secondLevelDropMainMenuTrucksCatalogInHeader().should(appear);
        childCategoryOlfilter().shouldBe(visible).click();
        logoInHeader().shouldBe(visible);
        return this;

    }

    @Step("clearing of Vertical selector if car was added")
    public LKW_main_page_Logic clearOfVerticalSelector() {
        if (resetBtnInVerticalCarSelector().isDisplayed()) {
            resetBtnInVerticalCarSelector().click();
        }
        return this;
    }

    @Step("selecting first parent category in drop Mein Truck catalog menu")
    public LKW_main_page_Logic selectFirstParentInMainDropTruckCatalogMenu() {
        listOfParentsDropMainTruckCatalog().get(0).hover();
        return this;
    }

    @Step("Get title of selecting element of main drop truck catalog menu")
    public String titleOfSelectingParent() {
        String titleOfSelectingElement = listOfParentsDropMainTruckCatalog().get(0).getText();
        return titleOfSelectingElement;
    }

    @Step("Select truck in vertical selector")
    public LKW_Catalog_Page_Logic selectTruckInSelector(){
        markeOfVerticalTruckSelector().selectOptionByValue("36");
        modelOfVerticalTruckSelector().selectOptionByValue("682");
        motorOfVerticalTruckSelector().selectOptionByValue("1008978");
        buttonSuchenOfVerticaltruckSelectorMainPage().click();
        return page(LKW_Catalog_Page_Logic.class);
    }

    // Menu in header
    @Step ("Click on PKW category")
    public Main_page clickPkwCategory() {
        $("[data-ga-action='PKW']").click();
        return page(Main_page.class);
    }

    @Step ("Click on Moto category")
    public Moto_main_page clickMotoCategory() {
        $("[data-ga-action='MOTO']").click();
        return page(Moto_main_page.class);
    }

    @Step ("Click on Tires category")
    public Tyres_page clickTiresCategory() {
        $(byCssSelector("[data-ga-action='23208']")).click();
        return page(Tyres_page.class);
    }

    @Step ("Click on Instrument category")
    public Index_instruments_page clickInstrumentsCategory() {
        $("[data-ga-action='36000']").click();
        return page(Index_instruments_page.class);
    }

    @Step ("Click on Accessories category")
    public Index_accessories_page clickAccessoriesCategory() {
        $("[data-ga-action='33000']").click();
        return page(Index_accessories_page.class);
    }

    @Step ("Click on EngineOil category")
    public CarParts_EngineOil_page clickEngineOilCategory() {
        $("[data-ga-action='12094']").click();
        return page(CarParts_EngineOil_page.class);
    }

    @Step ("Click on Filters category")
    public CarParts_Filters_page clickFiltersCategory() {
        $("[data-ga-action='10105']").click();
        return page(CarParts_Filters_page.class);
    }

    @Step ("Click on BrakeSystem category")
    public CarParts_BrakeSystem_page clickBrakeSystemCategory() {
        $("[data-ga-action='10106']").click();
        return page(CarParts_BrakeSystem_page.class);
    }

    @Step ("Click on Engine category")
    public CarParts_Engine_page clickEngineCategory() {
        $(".header-i.header-i--engine").click();
        return page(CarParts_Engine_page.class);
    }
}
