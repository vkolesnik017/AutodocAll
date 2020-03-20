package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

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

    SelenideElement breadCrumbsBlock() {
        return $x("//div[@class='steps breadcrumbs']");
    }

    SelenideElement compatibilityTruckBlock() {
        return $x("//div[@class='product-info-block-accordion js--roll-up']");
    }

    SelenideElement linkOfCompatibilityTruckAndProduct() {
        return $x("//div[@class='accordion-selected']");
    }
}
