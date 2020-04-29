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

    SelenideElement dropMainMenuTrucksCatalogInHeader() {
        return $(byId("menu-category-lkw"));
    }

    SelenideElement secondLevelDropMainMenuTrucksCatalogInHeader() {
        return $(byXpath("//div[@class='menu-category__2-block']"));
    }

    SelenideElement resetBtnInVerticalCarSelector() {
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

    SelenideElement tecDocCatalogOnMainPageLKW() {
        return $x("//div[@class='truck-home-parts']");
    }

    SelenideElement titleOfTecDocCatalog() {
        return $x("//h2[@class='truck-home-parts__title']");
    }

    SelenideElement linkOnCatalogPage() {
        return $x("//span[@class='truck-home-parts__show-all link']");
    }

    SelenideElement topCatalogBlock() {
        return $x("//ul[@class='truck-home-parts__list']");
    }

    SelenideElement imageOfFilterParentCategory() {
        return $x("//span[contains(text(),'Filter')]/..//img");
    }

    ElementsCollection topParentCategories() {
        return $$x("//li[@class='truck-home-parts__item']");
    }

    ElementsCollection imageOfTopParentCategoryInParentBlock() {
        return $$x("//span[@class='truck-home-parts__item-image']");
    }

    ElementsCollection titleOfTopParentCategoryInParentBlock() {
        return $$x("//span[@class='truck-home-parts__item-title']");
    }

    ElementsCollection linksOfChildCategoriesBlock() {
        return $$x("//*[@class='truck-home-parts__cat-list']");
    }

    SelenideElement childCategoryInParentCategoryBLock(String titleOfChildCategory) {
        return $x("//a[@class='truck-home-parts__cat-link js--lkw-modal__cat-link']/span[contains(text(),'" + titleOfChildCategory + "')]");
    }

    SelenideElement titleOfTopBrandsBlock() {
        return $x("//div[@class='truck-home-top-brands__title']/h3");
    }

    ElementsCollection brandsInTopBrandsBlock() {
        return $$x("//div[@class='truck-home-top-brands']//li/img");
    }

    SelenideElement popUpOfSpecification() {
        return $x("//div[@class='popup-selector-lkw']");
    }

    SelenideElement specificationInPopUp(String tonna) {
        return $x("//a[contains(@class,'popup-selector-lkw__table-row')]//div[contains(text(),'" + tonna + "')]");
    }

    SelenideElement btnAllTruckBrands() {
        return $x("//a[@class='truck-home-cars__show-all']");
    }

    SelenideElement iconOfTruckInHeadlineOfSelector() {
        return $x("//span[@class='car-icon']");
    }

    SelenideElement titleOfTruckInHeadlineOfSelector() {
        return $x("//div[contains(@class,'block-select-car__head-car--lkw')]/span[2]");
    }

    SelenideElement verticalSelectorInCloseCondition() {
        return $x("//div[contains(@class,'catalog-title__change-car')]");
    }

    SelenideElement resetBtnInVerticalCarSelectorInOpenCondition() {
        return $x("//a[@class='block-select-car__update hidden js--btn_reset_form']");
    }

    SelenideElement verticalTruckSelectorInOpenCondition() {
        return $x("//div[@class='block-select-car block-select-car--sidebar']");
    }

    SelenideElement truckBrandsBlock() {
        return $x("//div[@class='truck-home-cars']");
    }

    SelenideElement headlineOfTruckBrandsBlock() {
        return $x("//h2[@class='truck-home-cars__title']");
    }

    ElementsCollection brandsOfTruckInTopBrandsBlock() {
        return $$x("//ul[@class='truck-home-cars__list']//li").filter(visible);
    }

    SelenideElement arrowForMarkeFiled() {
        return $x("//div[@class='block-select-car__select filled js--lkw_selector-select_basic active']//span[@class='arrow']");
    }

    SelenideElement brandOfTruckInTopBlock(String brand) {return $x("//span[contains(text(),'"+brand+"')]/ancestor::a[@class='truck-home-cars__link']");}
}


