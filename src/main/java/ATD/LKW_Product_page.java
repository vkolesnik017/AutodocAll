package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class LKW_Product_page {
    ElementsCollection breadCrumbsLinks() {
        return $$x("//div[@class='steps breadcrumbs']//li");
    }

    SelenideElement firstLinkOfBreadCrumbsBlock() {
        return $x("//div[@class='steps breadcrumbs']//img");
    }

    SelenideElement secondLinkOfBreadCrumbsBlock() {
        return $x("//li[@class='step_2 active parts_step_2']//a");
    }

    SelenideElement thirdLinkOfBreadCrumbsBlock() {
        return $x("//li[@class='step_3 active parts_step_3']//a");
    }

    SelenideElement fourthLinkOfBreadCrumbsBlock() {
        return $x("//li[@class='step_4 active parts_step_4']//a");
    }

    SelenideElement markeInHorizontalTruckSelector() {
        return $(byName("maker_id"));
    }

    SelenideElement modelInHorizontalTruckSelector() {
        return $(byName("model_id"));
    }

    SelenideElement motorInHorizontalTruckSelector() {
        return $(byName("car_id"));
    }

    SelenideElement searchBtnInHorizontalTruckSelector() {
        return $x("//button[@class='search-button-truck js--lkw_selector-btn-submit']");
    }

    SelenideElement titleInTruckSelectorHeader() {
        return $x("//div[@class='car-match-block car-match-block--truck car-match-block--select']/p");
    }

    SelenideElement titleInTruckSelectorHeaderWithNotSuitableCar() {
        return $x("//div[@class='atd-carselector__header']//p");
    }

    SelenideElement breadCrumbsBlock() {
        return $x("//div[@class='steps breadcrumbs']");
    }

    SelenideElement compatibilityTruckBlock() {
        return $x("//div[@class='product-info-block-accordion js--roll-up']");
    }

    SelenideElement linkOfCompatibilityTruckAndProduct() {
        return $x("//div[@class='accordion-selected']");
    }

    SelenideElement carCompatibilityBlock() {
        return $x("//div[@class='product-info-block__auto product-info-block--lkw single']");
    }

    SelenideElement titleOfSuitableCarInCompatibilityBlock() {
        return $x("//div[@class='accordion-selected']/p");
    }


    SelenideElement tooltipForFieldInHorizontalCarSelector() {
        return $x("//div[@class='validation-tooltip popup-error-select']");
    }

    SelenideElement resetBtnInHorizontalCarSelector() {
        return $x("//div[@class='atd-carselector__reset js--btn_reset_form']");
    }

    SelenideElement activeMarkeField() {
        return $x("//select[@name='maker_id']/ancestor::div[@class='atd-carselector__select-wrapper  js--lkw_selector-select_basic active']");
    }

    SelenideElement truckingBlockOfMatching() {
        return $x("//div[@class='product-info-block__auto__title product-info-block__title--lkw']");
    }

    ElementsCollection brandsOfTruckInMatchingBLock() {
        return $$x("//div[2]//div[@class='accordion-button']/span");
    }

    SelenideElement dynamicCharacteristic(String titleOfCharacteristic, String valueOfCharacteristic) {
        return $x("//li[@class='important']/span[contains(text(),'" + titleOfCharacteristic + "')]/following-sibling::span[contains(text(),'" + valueOfCharacteristic + "')]");
    }

    SelenideElement oenBlock() {
        return $x("//div[@class='product-info-block__oem']");
    }

    ElementsCollection listOfOen() {
        return $$x("//div[@class='oem-list__col']/ul/li/span");
    }

    SelenideElement compatibleTruckBLock() {
        return $x("//div[contains(@class,'product-info-block--lkw single')]");
    }

    ElementsCollection compatibleTruckList() {
        return $$x("//i[@class='icon plus']/following-sibling::span");
    }

    SelenideElement modelsListBlock() {
        return $x("//div[@class='accordion-button active']/following-sibling::div");
    }

    ElementsCollection compatibleTruckLinks() {
        return $$x("//div[@class='accordion-button active']/following-sibling::div/ul/li");
    }

    SelenideElement applicationSpecificationBLock() {
        return $x("//div[@class='accordion-button active']/following-sibling::div/ul/li/ul");
    }

    ElementsCollection brandsOfTrucksInSelector() {
        return $$x("//select[@name='maker_id']/option");
    }

    SelenideElement closeCompatibleTruckModelList() {
        return $x("//i[@class='icon minus']");
    }

    SelenideElement openBlockOfCharacteristic() {
        return $x("//p[@class='show-more-button']/span");
    }

    ElementsCollection listOfCharacteristics() {
        return $$x("//div[@class='product-block__description__info']/ul/li").filter(visible);
    }

    SelenideElement selectedTruckSelector() {
        return $x("//div[@class='atd-carselector enable-overlay']");
    }

    SelenideElement darkBackground() {
        return $x("//div[@class='overlay-lkw']");
    }

    ElementsCollection activeLinksOfCharacteristic() {
        return $$x("//div[@class='product-block__description__info']/ul/li/span[2]/a");
    }

    SelenideElement mainImageOfProduct() {
        return $x("//div[@class='product-block__main-image']");
    }

    SelenideElement horizontalTruckSelector() {
        return $x("//form[@class='js--lkw_selector']");
    }

    SelenideElement relatedProductsBlock() {
        return $x("//div[@class='product-list product-list-slider js-product-list-slider']");
    }

    SelenideElement analoguesBlock() {
        return $x("//span[contains(text(),'EMPFOHLENER ERSATZ ')]/../..");
    }

    ElementsCollection imageOfAnaloguesProducts() {
        return $$x("//span[contains(text(),'EMPFOHLENER ERSATZ ')]/../../div[2]//div[@class='product-list__row']/ul/li//img").filter(visible);
    }

    ElementsCollection additionInfoBlockOfAnaloguesProduct() {
        return $$x("//span[contains(text(),'EMPFOHLENER ERSATZ ')]/../../div[2]//div[@class='product-list__row']/ul/li//div[@class='product-list__item__popup']").filter(visible);
    }

    SelenideElement forwardLinkAnaloguesBlock() {
        return $x("//span[contains(text(),'EMPFOHLENER ERSATZ ')]/../..//a[@class='bx-next']");
    }

    SelenideElement headlineOfAnaloguesBlock() {
        return $x("//span[contains(text(),'EMPFOHLENER ERSATZ ')]");
    }

    ElementsCollection imageOfRelatedProductsBlock() {
        return $$x("//div[contains(@class,'js-product-list-slider')]//img").filter(visible);
    }

    ElementsCollection additionInfoBlockOfRelatesProduct() {
        return $$x("//div[contains(@class,'js-product-list-slider')]//div[@class='product-list__item__popup'] ").filter(visible);
    }

    SelenideElement forwardLinkRelatedBlock() {
        return $x("//div[contains(@class,'js-product-list-slider')]//a[@class='bx-next']");
    }

    SelenideElement headlineOfRelatedBlock() {
        return $x("//span[contains(text(),'Folgende Produkte werden ')]");
    }


    ElementsCollection btnAddToBasketRelatedBlock() {
        return $$x("//div[@class='product-list__item__button']").filter(visible);
    }

    SelenideElement basketDropMenu() {
        return $x("//div[@class='cart-items-block ']");
    }

    SelenideElement basket() {
        return $x("//a[@class='header-cart__link']");
    }
}
