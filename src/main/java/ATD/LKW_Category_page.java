package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

class LKW_Category_page {
    SelenideElement imageOfChildCategory() {
        return $x("//div[@class='autoteile-top-content__image']");
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
        return $x("//li[@class='step_4 not_active parts_step_4']/span/span");
    }

    SelenideElement fifthLinkOfBreadCrumbsBlock() {
        return $x("//li[@class='step_5 not_active parts_step_5']/span/span");
    }

    SelenideElement sixthLinkOfBreadCrumbsBlock() {
        return $x("//li[@class='step_6 not_active parts_step_6']/span/span");
    }

    SelenideElement topChildCategoryBlockInSidebar() {
        return $x("//div[@class='block categories blue topSubCats']");
    }

    ElementsCollection linksOfChildCategoriesOfTopChildBlockInSidebar() {
        return $$x("//div[@class='block categories blue topSubCats']//li//span");
    }

    SelenideElement titleOfBrand(String title) {
        return $x("//li[@data-brand-name='" + title + "']/a");
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

    SelenideElement headlineOfTopBlockBrands() {
        return $x("//h3[@class='title_list no_border_top']");
    }

    SelenideElement topBrandsBlock() {
        return $x("//div[@class='kategorie_top_autos']");
    }

    ElementsCollection brandsInTopBrandsBlock() {
        return $$x("//div[@class='kategorie_top_autos']//li").filter(visible);
    }

    ElementsCollection imageOfBrandsInTopBrandsBlock() {
        return $$x("//div[@class='kategorie_top_autos']//li//img").filter(visible);
    }

    SelenideElement brandOfTruckInTopBlock() {
        return $x("//div[@class='kategorie_top_autos']/ul/li[1]/a");
    }

    SelenideElement linkMoreOfTopBrandBlock() {
        return $x("//div[@class='kategorie_top_autos']//span[contains(text(),'Mehr')]");
    }

    SelenideElement linkLessOfTopBrandBlock() {
        return $x("//div[@class='m_text hide']");
    }

    ElementsCollection brandsOfTruckInBlock() {
        return $$x("//div[@class='kategorie_top_autos']//li//img").filter(visible);
    }

    ElementsCollection firstRowOfBrands() {
        return $$x("//div[@class='kategorie_top_autos']//ul[1]//img");
    }

    ElementsCollection linksOfPopularModelList(int position) {
        return $$x("//div[@class='kategorie_top_autos']//ul[1]/li[" + position + "]//ul//a");
    }

    SelenideElement advantageBlock() {return $x("//div[@class='autoteile-features']");}
}
