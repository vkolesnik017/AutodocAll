package ATD;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class LKW_Category_maker_brand_page {
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
        return $x("//li[@class='step_5 active parts_step_5']//a");
    }

    SelenideElement sixthLinkOfBreadCrumbsBlock() {
        return $x("//li[@class='step_6 not_active parts_step_6']/span/span");
    }

    SelenideElement seventhLinkOfBreadCrumbsBlock() {
        return $x("//li[@class='step_7 not_active parts_step_7']/span/span");
    }

    SelenideElement imageOfTruckInHeadLine() {
        return $x("//div[contains(@class,'title_count_search')]//img");
    }

    SelenideElement titleOfTopBrandsBlock() {
        return $x("//h3[@class='title_list no_border_top pad_size']");
    }

    SelenideElement topBrandsBlock() {
        return $x("//div[@class='kategorie_top_brands']");
    }

    ElementsCollection brandsOfTopBrandsBlock() {
        return $$x("//div[@class='kategorie_top_brands']//li").filter(visible);
    }

    SelenideElement linkMoreOfTopBrandsBlock() {
        return $x("//div[@class='kategorie_top_brands']//div[@class='m_text show']");
    }

    SelenideElement linkCloseOfTopBrandsBlock() {
        return $x("//div[@class='kategorie_top_brands']//div[@class='m_text hide']");
    }

    SelenideElement childCategoryBlockInSideBar() {
        return $x("//div[@class='sidebar']");
    }

    SelenideElement markeInVerticalTruckSelector() { return $(byName("maker_id"));    }

    SelenideElement resetBtnInVerticalCarSelector() {return $(byId("reset_selector_form"));}

    SelenideElement headlineOfTopModelBlock() {return $x("//div[@class='title_list no_border_top']");}

    SelenideElement modelOfTruckInTopBlock(String model) {return $x("//div[@class='lkw_top_models']//span[contains(text(),'"+model+"')]");}

    SelenideElement headlineOfTopProductsBlock() {return $x("//section[@class='title_list']");}
}
