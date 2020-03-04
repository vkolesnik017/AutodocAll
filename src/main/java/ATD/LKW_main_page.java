package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class LKW_main_page {
    protected SelenideElement logoInHeader() {
        return $(".header__logo-main");
    }

    protected SelenideElement searchBar() {
        return $(byId("search"));
    }

    private By tooltipsToSearch = byCssSelector(".autocomplete-suggestions>div");

    protected SelenideElement tooltipToSearch() {
        return $(tooltipsToSearch);
    }

    protected SelenideElement infoIconForSearch() {
        return $(".header-i.header-i--info.inf");
    }

    protected SelenideElement infoPopupForSearch() {
        return $(".ex_popup.ex_popup_shown");
    }

    protected SelenideElement menuCatalogInHeader() {
        return $(".menu-catalog>a");
    }

    protected SelenideElement closeBtnOfCookiesPopUp() {
        return $x("//div[contains(@class,'block-cookies__close')]");
    }

    protected SelenideElement dropMainMenuTrucksCatalogInHeader() {
        return $(byId("menu-category-lkw"));
    }

    protected SelenideElement secondLevelDropMainMenuTrucksCatalogInHeader() {
        return $(byXpath("//div[@class='menu-category__2-block']"));
    }

    protected SelenideElement resetBtnInVerticalCarSelector() {
        return $(byId("reset_selector_form"));
    }

    protected ElementsCollection listOfParentsDropMainTruckCatalog() {
        return $$(byXpath("//li[contains(@class,'menu-category__first-item')]/a"));
    }

    protected ElementsCollection titleOfSecondLevelMainDropMenuTruckCatalog() {
        return $$(byXpath("//div[@class='menu-category__2-block']//div[@class='menu-category__title']")).filter(visible);
    }

    protected ElementsCollection titleOfThirdLevelMainDropMenuTruckCatalog() {
        return $$(byXpath("//div[@class='menu-category__3-block']//div[@class='menu-category__title']")).filter(visible);
    }

    protected ElementsCollection titleOfFourthLevelMainDropMenuTruckCatalog() {
        return $$(byXpath("//div[@class='menu-category__4-block']//div[@class='menu-category__title']")).filter(visible);
    }

    protected ElementsCollection imageOfSecondLevelMainDropMenuTruckCatalog() {
        return $$(byXpath("//div[@class='menu-category__2-block']//div[@class='menu-category__header']/img")).filter(visible);
    }


    protected ElementsCollection listOfCategoriesSecondLevelDropMainTruckCatalog() {
        return $$(byXpath("//div[@class='menu-category__2-block']//li[contains(@class,'menu-category__item')]")).filter(visible);
    }

    protected ElementsCollection categoriesOfSecondBlockWitnDropMenu() {
        return $$(byXpath("//div[@class='menu-category__2-block']//li[contains(@class,'menu-category__item')]/span/span")).filter(visible);
    }

    protected ElementsCollection categoriesOfThirdBlock() {
        return $$(byXpath("//div[@class='menu-category__3-block']//li[@class='menu-category__item']")).filter(visible);
    }

    protected SelenideElement thirdLevelDropMainMenuTrucksCatalogInHeader() {
        return $(byXpath("//div[@class='menu-category__3-block']"));
    }

    protected SelenideElement fourthLevelDropMainMenuTrucksCatalogInHeader() {
        return $(byXpath("//div[@class='menu-category__4-block']"));
    }

    protected SelenideElement verticalTruckSelector() {
        return $x("//form[@class='js--lkw_selector']");
    }

    protected SelenideElement markeOfVerticalTruckSelector() {
        return $(byName("maker_id"));
    }

    protected SelenideElement modelOfVerticalTruckSelector() {
        return $(byName("model_id"));
    }

    protected SelenideElement motorOfVerticalTruckSelector() {
        return $(byName("car_id"));
    }

    protected SelenideElement buttonSuchenOfVerticaltruckSelector() {
        return $x("//a[@class='truck_submit js--lkw_selector-btn-submit']");
    }

    protected SelenideElement buttonSuchenOfVerticaltruckSelectorMainPage() {
        return $x("//div[@class='block-select-truck__button']");
    }

    protected SelenideElement parentCategoryFilter() {
        return $x("//a[contains(text(),'Filter')]");
    }

    protected SelenideElement childCategoryOlfilter() {
        return $x("//li[@class='menu-category__item']//span[contains(text(),'Ölfilter')]");
    }
    protected SelenideElement childCategoryOnMainPage() {
        return $x("//div[@class='truck-home-parts__cat-list']//span[contains(text(),'Ölfilter')]");
    }

    protected ElementsCollection categoriesOfThirdBlockWitnDropMenu() {
        return $$(byXpath("//div[@class='menu-category__3-block']//li[contains(@class,'menu-category__item')]/span/span")).filter(visible);
    }

    protected ElementsCollection categoriesOfThirdBlockWithOutDropMenu() {
        return $$(byXpath("//div[@class='menu-category__3-block']//li[contains(@class,'menu-category__item')]/a/span")).filter(visible);
    }
    protected SelenideElement tecDocCatalogOnMainPageLKW() {return $x("//div[@class='truck-home-parts']");}
}


