package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byName;
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
        return $$x("//div[@class='moto-top-v__item']/a");
    }

    ElementsCollection visibleTopMotoModels() {
        return $$x("//div[@class='moto-top-v__item']/a").filter(visible);
    }

    SelenideElement activeLinkForwardOfTopModel() {
        return $x("//div[@class='moto-top-v']//a[@class='bx-next']");
    }

    SelenideElement activeLinkBackOfTopModel() {
        return $x("//div[@class='moto-top-v']//a[@class='bx-prev']");
    }

    SelenideElement linkForwardOfTopModel() {
        return $x("//div[@class='moto-top-v']//div[@class='bx-controls-direction']//a[2]");
    }

    SelenideElement linkBackOfTopModel() {
        return $x("//div[@class='moto-top-v']//div[@class='bx-controls-direction']//a[1]");
    }

    ElementsCollection titleOfVisibleTopModels() {
        return $$x("//div[@class='moto-top-v__item']/a/span[2]").filter(visible);
    }

}
