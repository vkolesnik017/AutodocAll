package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

class LKW_Category_maker {

    SelenideElement childCategoryBlockSideBar() {
        return $x("//div[@class='block categories blue topSubCats']");
    }

    SelenideElement breadCrumbsBlock() {
        return $x("//div[@class='steps breadcrumbs']");
    }

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

    SelenideElement fifthLinkOfBreadCrumbsBlock() {
        return $x("//li[@class='step_5 not_active parts_step_5']/span/span");
    }

    SelenideElement sixthLinkOfBreadCrumbsBlock() {
        return $x("//li[@class='step_6 not_active parts_step_6']/span/span");
    }

    SelenideElement headlineInHeader() {
        return $x("//div[@class='title_count_search']/h2");
    }

    SelenideElement titleOfBrand(String title) {
        return $x("//li[@data-brand-name='" + title + "']/a");
    }

    SelenideElement resetSelector() {return $(byId("reset_selector_form"));}

    SelenideElement markeInVerticalCarSelector() {return $(byName("maker_id"));}

    SelenideElement motorInVerticalCarSelector() {return $(byName("model_id"));}

    SelenideElement btnSearchInVerticalCarSelector() {
        return $x("//a[@class='truck_submit js--lkw_selector-btn-submit']");
    }

    SelenideElement tooltipForMarkeFieldInVerticalCarSelector() { return $x("//div[@class='validation-tooltip popup-error-select']");}
}
