package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

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

    SelenideElement truckingBlockOfMatching() {return $x("//div[@class='product-info-block__auto__title product-info-block__title--lkw']");}

    ElementsCollection brandsOfTruckInMatchingBLock() {return $$x("//div[2]//div[@class='accordion-button']/span");}

    SelenideElement dynamicCharacteristic(String titleOfCharacteristic, String valueOfCharacteristic) {return $x("//li[@class='important']/span[contains(text(),'"+titleOfCharacteristic+"')]/following-sibling::span[contains(text(),'"+valueOfCharacteristic+"')]");}

    SelenideElement oenBlock() {return $x("//div[@class='product-info-block__oem']");}

    ElementsCollection listOfOen() {return $$x("//div[@class='oem-list__col']/ul/li/span");}
}
