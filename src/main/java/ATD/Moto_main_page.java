package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

class Moto_main_page {
    protected SelenideElement menuCatalogInHeader() {
        return $(".menu-catalog>a");
    }

    protected SelenideElement markeOfHorizontalMotoSelector() {
        return $(byName("maker_id"));
    }

    protected SelenideElement modelOfHorizontalMotoSelector() {
        return $(byName("model_id"));
    }

    protected SelenideElement motorOfHorizontalMotoSelector() {
        return $(byName("car_id"));
    }

    protected SelenideElement tecDocCatalogOnMainPageMoto() {
        return $x("//ul[@class='moto-parts-catalog__list']");
    }

    protected SelenideElement childCategoryOnMotoMainPage() {
        return $x("//ul[@class='moto-parts-catalog__list']//span[contains(text(),'Luftfilter')]");
    }

    protected SelenideElement searchInHorizontalMotoSelector() {
        return $x("//a[@class='submit search_button ripple-out']");
    }

    protected SelenideElement modelFieldSelector() {
        return $("#model-select");
    }

    SelenideElement mainSearchField() {
        return $x("//div[@class='header-search__input-row']/input");
    }

    SelenideElement hintsOfMainSearchingFieldBlock() {
        return $(byId("autocomplete-suggestions-list"));
    }

    SelenideElement deliveryBlock() {
        return $x("//div[@class='delivery-method']");
    }

    SelenideElement paymentMethodsBlock() {
        return $x("//div[@class='pay-method']");
    }

    SelenideElement linkingBannerBlock() {
        return $x("//div[@class='moto-banner moto-banner--2col']");
    }

    SelenideElement leftMotoLinkingBanner() {
        return $x("//div[@class='moto-banner moto-banner--2col']/span[1]");
    }

    SelenideElement rightMotoLinkingBanner() {
        return $x("//div[@class='moto-banner moto-banner--2col']/span[2]");
    }

    SelenideElement motorSelectorBlock() {
        return $x("//div[@class='moto-select']");
    }

    SelenideElement toolTipForBrandFieldInSelector() {
        return $(byId("selector-error-tooltip"));
    }

    SelenideElement toolTipForModelFieldInSelector() {
        return $(byId("selector-error-tooltip-model"));
    }

    SelenideElement toolTipForMotorFieldInSelector() {
        return $(byId("form_car_id"));
    }

    SelenideElement btnSearchInSelector() {
        return $x("//a[@class='submit search_button']");
    }

    SelenideElement btnResetOfSelector() {
        return $(byId("reset_selector_form"));
    }

    ElementsCollection linkTopParentBlock() {
        return $$x("//li[@class='moto-parts-catalog__item']");
    }

    ElementsCollection imageTopParentBlock() {
        return $$x("//span[@class='moto-parts-catalog__item-image']");
    }

    ElementsCollection titleTopParentBlock() {
        return $$x("//span[@class='moto-parts-catalog__item-title']");
    }

    ElementsCollection childCategoryLinks(int position) {
        return $$x("(//li[@class='moto-parts-catalog__item'])[" + position + "]//div[@class='moto-parts-catalog__cat-list']/a");
    }

    SelenideElement linkMoreAtParentCategoryBlock() {
        return $x("//span[@class='moto-parts-catalog__show-all link']");
    }

    SelenideElement headlineOfTopParentBlock() {
        return $x("//strong[@class='moto-parts-catalog__title']");
    }

    SelenideElement topParentBlock() {
        return $x("//ul[@class='moto-parts-catalog__list']");
    }

    SelenideElement registrationBlock() {
        return $x("//a[contains(@class,'js-show-login-popup')]");
    }

    SelenideElement loginPopUp() {
        return $x("//div[@class='autodoc_login_popup popup_login pass']");
    }

    SelenideElement loginFieldAtLoginPopUp() {
        return $(byId("login_top_email"));
    }

    SelenideElement passwordFieldAtLoginPopUp() {
        return $(byName("Password"));
    }

    SelenideElement btnRegistrationAtLoginPopUp() {
        return $x("//a[@class='enter submit']");
    }

    SelenideElement topBrandsBlock() {
        return $x("//div[@class='moto-manufactures']");
    }

    SelenideElement titleOfTopBrandsBlock() {
        return $x("//strong[@class='moto-manufactures__title']");
    }

    SelenideElement linkMoreAtTopBrandsBlock() {
        return $x("//a[@class='moto-manufactures__show-all']");
    }

    ElementsCollection listOfTopBrands() {
        return $$x("//ul[@class='moto-manufactures__list']//a");
    }

    SelenideElement navigationBlockInHeader() {
        return $x("//ul[@class='header__nav-list']");
    }

    ElementsCollection linksAtTopCategoriesInHeader() {
        return $$x("//ul[@class='header__nav-list']/li/a");
    }

    SelenideElement topChildCategoriesBlock() {
        return $x("//div[@class='moto-home-categories']");
    }

    SelenideElement headlineOfTopChildCategoriesBlock() {
        return $x("//h2[@class='moto-home-categories__title']");
    }

    ElementsCollection linksOfTopChildCategoriesBlock() {
        return $$x("//div[@class='moto-home-categories__item']");
    }

    SelenideElement topModelsBlock() {
        return $x("//div[@class='moto-top-v']");
    }

    SelenideElement headlineOfTopModelsBlock() {
        return $x("//h3[@class='moto-top-v__title']/span");
    }

    ElementsCollection topMotoModels() {
        return $$x("//div[contains(@class,'moto-top-v__item')]/a");
    }

    ElementsCollection visibleTopMotoModels() {
        return $$x("//div[contains(@class,'moto-top-v__item')]/a").filter(visible);
    }

    SelenideElement activeLinkForwardOfTopModel() {
        return $x("//div[@class='moto-top-v']//button[@class='slick-next slick-arrow']");
    }

    SelenideElement linkForwardOfTopModel() {
        return $x("//div[contains(@class,'moto-top-v')]//button[contains(@class,'slick-next')]");
    }

    SelenideElement notActiveLinkForwardOfTopModel() {
        return $x("//div[contains(@class,'moto-top-v')]//button[@class='slick-next slick-arrow slick-disabled']");
    }

    SelenideElement linkBackOfTopModel() {
        return $x("//div[@class='moto-top-v']//button[@class='slick-prev slick-arrow']");
    }

    SelenideElement notActiveLinkBackOfTopModel() {
        return $x("//div[@class='moto-top-v']//button[@class='slick-prev slick-arrow slick-disabled']");
    }

    ElementsCollection titleOfVisibleTopModels() {
        return $$x("//div[contains(@class,'moto-top-v__item')]/a/span[2]").filter(visible);
    }

    ElementsCollection titleTopModelsBlock() {
        return $$x("//span[@class='moto-top-v__name']").filter(visible);
    }

    SelenideElement topProductsBlock() {
        return $x("//*[self::div[@class='moto-top-products'] or self::div[@class='moto-top-product']]");
    }

    SelenideElement headlineOfTopProductsBlock() {
        return $x("//b[@class='moto-top-products__title']");
    }

    ElementsCollection topProducts() {
        return $$x("//div[contains(@class,'product-list__row')]/ul/li");
    }

    ElementsCollection titleOfTopProducts() {
        return $$x("//div[@class='product-list__item__title']/span");
    }

    ElementsCollection imageOfTopProducts() {
        return $$x("//div[@class='product-list__item__image']/*[self::a or self::span]/img");
    }

    ElementsCollection detailsOfTopProductsBlock() {return $$x("//div[@class='product-list__item__popup']");}

    ElementsCollection btnDetailsOfTopProducts() {return $$x("//div[@class='item-table-box__details']/span");}

    ElementsCollection visibleTitleOfTopProducts() {
        return $$x("//div[@class='product-list__item__title']/span").filter(visible);
    }

    ElementsCollection visibleDetailsOfTopProductsBlock() {return $$x("//div[@class='product-list__item__popup']").filter(visible);}

    SelenideElement headlineOfTopProductBlock() {return $x("//*[self::div[@class='moto-top-product__title'] or self::b[@class='moto-top-products__title']]");}

    SelenideElement linkForwardOfTopProductBlock() {
        return $x("//div[@class='moto-top-products']//a[@class='bx-next']");
    }

    SelenideElement linkBackOfTopProductBlock() {
        return $x("//*[self::div[@class='moto-top-product'] or self::div[@class='moto-top-products']]//button[@class='slick-prev slick-arrow']");
    }

    SelenideElement activeLinkForwardOfTopProductBlock() {
        return $x("//*[self::div[@class='moto-top-product'] or self::div[@class='moto-top-products']]//button[@class='slick-next slick-arrow']");
    }

    SelenideElement logoInHeader() {return $(".header__logo-main");}

    SelenideElement btnVerticalMotoCatalog() {return $x("//div[@class='menu-catalog']");}

    SelenideElement parentCategoriesBlockInVerticalCatalog() {return $x("//div[@id='menu-category-moto']");}

    SelenideElement headlineOfParentCategoriesVerticalCatalog() {return $x("//div[@class='menu-category__first-lvl']//div[@class='menu-category__title']/a");}

    SelenideElement imageOfParentCategoriesVerticalCatalog() {return $x("//div[@class='menu-category__first-lvl']//div[@class='menu-category__header']/img");}

    ElementsCollection listOfParentCategoriesInVerticalCatalog() {return $$x("//li[contains(@class,'menu-category__first-item')]/a");}

    SelenideElement verticalCatalogBlockSecondLevel() {
        return $(byXpath("//div[@class='menu-category__2-block']"));
    }

    ElementsCollection titleOfVerticalCatalogBlockSecondLevel() {
        return $$(byXpath("//div[@class='menu-category__2-block']//div[@class='menu-category__title']")).filter(visible);
    }

    ElementsCollection imageOfVerticalCatalogBlockSecondLevel() {
        return $$(byXpath("//div[@class='menu-category__2-block']//div[@class='menu-category__header']/img")).filter(visible);
    }

    ElementsCollection listOfCategoriesInVerticalCatalogSecondLevel() {
        return $$(byXpath("//div[@class='menu-category__2-block']//li[contains(@class,'menu-category__item')]")).filter(visible);
    }

    ElementsCollection intermediateCategoriesSecondLevel() {
        return $$(byXpath("//div[@class='menu-category__2-block']//li[contains(@class,'menu-category__item')]/span/span")).filter(visible);
    }

    SelenideElement verticalCatalogBlockThirdLevel() {
        return $(byXpath("//div[@class='menu-category__3-block']"));
    }

    ElementsCollection titleOfVerticalCatalogBlockThirdLevel() {
        return $$(byXpath("//div[@class='menu-category__3-block']//div[@class='menu-category__title']")).filter(visible);
    }

    ElementsCollection childCategoriesThirdLevelAtVerticalCatalog() {return $$x("//div[@class='menu-category__3-block']//li[@class='menu-category__item']/a").filter(visible);}

    ElementsCollection titleOfChildCategoriesThirdLevelAtVerticalCatalog() {return $$x("//div[@class='menu-category__3-block']//li[@class='menu-category__item']/a/span").filter(visible);}

    ElementsCollection imageOfChildCategoriesThirdLevelAtVerticalCatalog() {return $$x("//div[@class='menu-category__3-block']//li[@class='menu-category__item']/a/img").filter(visible);}

    SelenideElement mainBanner() {return $x("//div[@class='moto-banner']");}

    ElementsCollection titleOfTopChildCategories() {return $$x("//span[@class='moto-home-categories__name']");}

    SelenideElement popUpOfGarageInHeader() {return $x("//div[@class='header-garage__logged-header']");}

    SelenideElement idOfVehicleInGaragePopUp(String idOfVehicle) {return $x("//div[@class='wrapper-radio']/label[@for='"+idOfVehicle+"']");}

    SelenideElement headerGarageIcon(){ return $x("//div[@class='header-garage js-header-garage']"); }

    SelenideElement  countOfVehicleInIconOfGarageInHeader() {return $x("//span[@class='header-garage__count header-garage__count--added']");}

    ElementsCollection visibleArtNumOfTopProducts() {
        return $$x("//div[@class='product-list__item__nummer']").filter(visible);
    }

    ElementsCollection topMarkeValuesFromSelector() {return $$x("//select[@id='form_maker_id']/*[2]/option");}

    ElementsCollection topModelValuesFromSelector() {return $$x("//select[@id='form_model_id']/*[2]/option");}

    ElementsCollection listOfVisibleTopBrands() {
        return $$x("//ul[@class='moto-manufactures__list']//a").filter(visible);
    }
}
