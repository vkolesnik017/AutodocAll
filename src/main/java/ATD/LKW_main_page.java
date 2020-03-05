package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class LKW_main_page {
    SelenideElement logoInHeader() {
        return $(".header__logo-main");
    }

    SelenideElement searchBar() {
        return $(byId("search"));
    }

    private By tooltipsToSearch = byCssSelector(".autocomplete-suggestions>div");

    SelenideElement tooltipToSearch() {
        return $(tooltipsToSearch);
    }

    SelenideElement infoIconForSearch() {
        return $(".header-i.header-i--info.inf");
    }

    SelenideElement infoPopupForSearch() {
        return $(".ex_popup.ex_popup_shown");
    }

    SelenideElement menuCatalogInHeader() {
        return $(".menu-catalog>a");
    }

    SelenideElement closeBtnOfCookiesPopUp() {
        return $x("//div[contains(@class,'block-cookies__close')]");
    }

    SelenideElement dropMainMenuTrucksCatalogInHeader() {
        return $(byId("menu-category-lkw"));
    }

    SelenideElement secondLevelDropMainMenuTrucksCatalogInHeader() {
        return $(byXpath("//div[@class='menu-category__2-block']"));
    }

    protected SelenideElement resetBtnInVerticalCarSelector() {
        return $(byId("reset_selector_form"));
    }

    ElementsCollection listOfParentsDropMainTruckCatalog() {
        return $$(byXpath("//li[contains(@class,'menu-category__first-item')]/a"));
    }

    ElementsCollection titleOfSecondLevelMainDropMenuTruckCatalog() {
        return $$(byXpath("//div[@class='menu-category__2-block']//div[@class='menu-category__title']")).filter(visible);
    }

    ElementsCollection titleOfThirdLevelMainDropMenuTruckCatalog() {
        return $$(byXpath("//div[@class='menu-category__3-block']//div[@class='menu-category__title']")).filter(visible);
    }

    ElementsCollection titleOfFourthLevelMainDropMenuTruckCatalog() {
        return $$(byXpath("//div[@class='menu-category__4-block']//div[@class='menu-category__title']")).filter(visible);
    }

    ElementsCollection imageOfSecondLevelMainDropMenuTruckCatalog() {
        return $$(byXpath("//div[@class='menu-category__2-block']//div[@class='menu-category__header']/img")).filter(visible);
    }


    ElementsCollection listOfCategoriesSecondLevelDropMainTruckCatalog() {
        return $$(byXpath("//div[@class='menu-category__2-block']//li[contains(@class,'menu-category__item')]")).filter(visible);
    }

    ElementsCollection categoriesOfSecondBlockWitnDropMenu() {
        return $$(byXpath("//div[@class='menu-category__2-block']//li[contains(@class,'menu-category__item')]/span/span")).filter(visible);
    }

    ElementsCollection categoriesOfThirdBlock() {
        return $$(byXpath("//div[@class='menu-category__3-block']//li[@class='menu-category__item']")).filter(visible);
    }

    SelenideElement thirdLevelDropMainMenuTrucksCatalogInHeader() {
        return $(byXpath("//div[@class='menu-category__3-block']"));
    }

    SelenideElement fourthLevelDropMainMenuTrucksCatalogInHeader() {
        return $(byXpath("//div[@class='menu-category__4-block']"));
    }

    SelenideElement verticalTruckSelector() {
        return $x("//form[@class='js--lkw_selector']");
    }

    SelenideElement markeOfVerticalTruckSelector() {
        return $(byName("maker_id"));
    }

    SelenideElement modelOfVerticalTruckSelector() {
        return $(byName("model_id"));
    }

    SelenideElement motorOfVerticalTruckSelector() {
        return $(byName("car_id"));
    }

    SelenideElement buttonSuchenOfVerticaltruckSelector() {
        return $x("//a[@class='truck_submit js--lkw_selector-btn-submit']");
    }

    SelenideElement buttonSuchenOfVerticaltruckSelectorMainPage() {
        return $x("//div[@class='block-select-truck__button']");
    }

    SelenideElement parentCategoryFilter() {
        return $x("//a[contains(text(),'Filter')]");
    }

    SelenideElement childCategoryOlfilter() {
        return $x("//li[@class='menu-category__item']//span[contains(text(),'Ölfilter')]");
    }
    SelenideElement childCategoryOnMainPage() {
        return $x("//div[@class='truck-home-parts__cat-list']//span[contains(text(),'Ölfilter')]");
    }

    ElementsCollection categoriesOfThirdBlockWitnDropMenu() {
        return $$(byXpath("//div[@class='menu-category__3-block']//li[contains(@class,'menu-category__item')]/span/span")).filter(visible);
    }

    ElementsCollection categoriesOfThirdBlockWithOutDropMenu() {
        return $$(byXpath("//div[@class='menu-category__3-block']//li[contains(@class,'menu-category__item')]/a/span")).filter(visible);
    }
    SelenideElement tecDocCatalogOnMainPageLKW() {return $x("//div[@class='truck-home-parts']");}
}


