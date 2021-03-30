package ATD;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
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

    SelenideElement markeInVerticalCarSelector() {
        return $(byName("maker_id"));
    }

    SelenideElement motorInVerticalCarSelector() {
        return $(byName("model_id"));
    }

    SelenideElement btnSearchInVerticalCarSelector() {
        return $x("//a[@class='truck_submit js--lkw_selector-btn-submit']");
    }

    SelenideElement tooltipForMarkeFieldInVerticalCarSelector() {
        return $x("//div[@class='validation-tooltip popup-error-select']");
    }

    SelenideElement resetBtnInVerticalCarSelector() {
        return $(byId("reset_selector_form"));
    }

    SelenideElement topBrandsBlock() {
        return $x("//div[@class='lkw_top_models']");
    }

    ElementsCollection brandsInTopBrandsBlock() {
        return $$x("//div[@class='lkw_top_models']//li").filter(visible);
    }

    ElementsCollection imageOfBrandsInTopBrandsBlock() {
        return $$x("//div[@class='lkw_top_models']//li//img").filter(visible);
    }

    ElementsCollection titleOfBrandsInTopBrandsBlock() {
        return $$x("//div[@class='lkw_top_models']//li//span[2]").filter(visible);
    }

    ElementsCollection yearOfBrandsInTopBrandsBlock() {
        return $$x("//div[@class='lkw_top_models']//li//span[contains(text(),'ab')]").filter(visible);
    }

    SelenideElement headLine() {
        return $x("//div[@class='title_count_search ']");
    }

    SelenideElement modelOfTruckInTopBlock(String model) {return $x("//div[@class='lkw_top_models']//span[contains(text(),'"+model+"')]");}

    SelenideElement linkMoreOfTopBrandBlock() {return $x("//div[@class='lkw_top_models']//span[contains(text(),'Mehr')]");}

    ElementsCollection modelsOfTruckInBlock() {return $$x("//div[@class='lkw_top_models']//li").filter(visible);}

    SelenideElement linkLessOfModelsBlock() {return $x("//div[@class='m_text hide']");}

    SelenideElement headlineOfAdvantageBlock() {return $x("//div[@class='autoteile-features__title']");}

    ElementsCollection advantagesInBlock() {return  $$x("//ul[@class='autoteile-features__list']//li");}

    ElementsCollection imageOfTopProduct() {return $$x("//a[@class='ga-click']/img");}

    ElementsCollection btnDetails() {return $$x("//span[@class='details js-product-link pointer']");}

    SelenideElement linksBlock() {return $(".block_links");}

    ElementsCollection topAutoLinks() {return $$x("//b[text()='Top LKW-Marken:']/..//a");}
}
