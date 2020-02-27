package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class LKW_main_page {
    public SelenideElement logoInHeader() {
        return $(".header__logo-main");
    }

    public SelenideElement searchBar() {
        return $(byId("search"));
    }

    private By tooltipsToSearch = byCssSelector(".autocomplete-suggestions>div");

    public SelenideElement tooltipToSearch() {
        return $(tooltipsToSearch);
    }

    public ElementsCollection tooltipsToSearch() {
        return $$(tooltipsToSearch);
    }

    public SelenideElement infoIconForSearch() {
        return $(".header-i.header-i--info.inf");
    }

    public SelenideElement infoPopupForSearch() {
        return $(".ex_popup.ex_popup_shown");
    }

    public SelenideElement menuCatalogInHeader() {
        return $(".menu-catalog>a");
    }

    public SelenideElement closeBtnOfCookiesPopUp() {
        return $x("//div[contains(@class,'block-cookies__close')]");
    }

    public SelenideElement dropMainMenuTrucksCatalogInHeader() {
        return $(byId("menu-category-lkw"));
    }

    public SelenideElement secondLevelDropMainMenuTrucksCatalogInHeader() {
        return $(byXpath("//div[@class='menu-category__2-block']"));
    }

    public SelenideElement resetBtnInVerticalCarSelector() {
        return $(byId("reset_selector_form"));
    }

    public SelenideElement titleOfMainDropMenuTruckCatalog() {
        return $(byXpath("//div[@class='menu-category__title']"));
    }

    public SelenideElement imageOfMainDropMenuTruckCatalog() {
        return $(byXpath("//div[@class='menu-category__first-lvl']//img"));
    }

    public ElementsCollection listOfParentsDropMainTruckCatalog() {
        return $$(byXpath("//li[contains(@class,'menu-category__first-item')]/a"));
    }

    public ElementsCollection titleOfSecondLevelMainDropMenuTruckCatalog() {
        return $$(byXpath("//div[@class='menu-category__2-block']//div[@class='menu-category__title']")).filter(visible);
    }

    public ElementsCollection titleOfThirdLevelMainDropMenuTruckCatalog() {
        return $$(byXpath("//div[@class='menu-category__3-block']//div[@class='menu-category__title']")).filter(visible);
    }

    public ElementsCollection titleOfFourthLevelMainDropMenuTruckCatalog() {
        return $$(byXpath("//div[@class='menu-category__4-block']//div[@class='menu-category__title']")).filter(visible);
    }

    public ElementsCollection imageOfSecondLevelMainDropMenuTruckCatalog() {
        return $$(byXpath("//div[@class='menu-category__2-block']//div[@class='menu-category__header']/img")).filter(visible);
    }


    public ElementsCollection listOfCategoriesSecondLevelDropMainTruckCatalog() {
        return $$(byXpath("//div[@class='menu-category__2-block']//li[contains(@class,'menu-category__item')]")).filter(visible);
    }

    public ElementsCollection categoriesOfSecondBlockWitnDropMenu() {
        return $$(byXpath("//div[@class='menu-category__2-block']//li[contains(@class,'menu-category__item')]/span/span")).filter(visible);
    }

    public ElementsCollection categoriesOfThirdBlock() {
        return $$(byXpath("//div[@class='menu-category__3-block']//li[@class='menu-category__item']")).filter(visible);
    }

    public SelenideElement thirdLevelDropMainMenuTrucksCatalogInHeader() {
        return $(byXpath("//div[@class='menu-category__3-block']"));
    }

    public SelenideElement fourthLevelDropMainMenuTrucksCatalogInHeader() {
        return $(byXpath("//div[@class='menu-category__4-block']"));
    }

    public ElementsCollection imagesOfThirdBlock() {
        return $$(byXpath("//div[@class='menu-category__3-block']//img")).filter(visible);
    }

    public SelenideElement verticalTruckSelector() {
        return $x("//form[@class='js--lkw_selector']");
    }

    public SelenideElement markeOfVerticalTruckSelector() {
        return $(byName("maker_id"));
    }

    public SelenideElement modelOfVerticalTruckSelector() {
        return $(byName("model_id"));
    }

    public SelenideElement motorOfVerticalTruckSelector() {
        return $(byName("car_id"));
    }

    public SelenideElement buttonSuchenOfVerticaltruckSelector() {
        return $x("//a[@class='truck_submit js--lkw_selector-btn-submit']");
    }

    public SelenideElement parentCategoryFilter() {
        return $x("//a[contains(text(),'Filter')]");
    }

    public SelenideElement childCategoryOlfilter() {
        return $x("//li[@class='menu-category__item']//span[contains(text(),'Ölfilter')]");
    }


    public ElementsCollection categoriesOfThirdBlockWitnDropMenu() {
        return $$(byXpath("//div[@class='menu-category__3-block']//li[contains(@class,'menu-category__item')]/span/span")).filter(visible);
    }

    public ElementsCollection categoriesOfThirdBlockWithOutDropMenu() {
        return $$(byXpath("//div[@class='menu-category__3-block']//li[contains(@class,'menu-category__item')]/a/span")).filter(visible);
    }
}


