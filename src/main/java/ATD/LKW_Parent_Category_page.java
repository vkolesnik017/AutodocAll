package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class LKW_Parent_Category_page {
    SelenideElement childCategoryBlock() {
        return $x("//div[@class='filter_small_img']");
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
        return $x("//li[@class='step_3 not_active parts_step_3']/span/span");
    }

    SelenideElement fourthLinkOfBreadCrumbsBlock() {
        return $x("//li[@class='step_4 not_active parts_step_4']/span/span");
    }

    SelenideElement closeToolTipTextSelector() {
        return $x("//div[@class='tooltiptext-close js-tooltiptext-close']");
    }

    SelenideElement catalogBlockInSideBar() {
        return $x("//div[@class='teile_catalog']/a");
    }

    ElementsCollection categoriesInParentCatalogInSideBar() {
        return $$x("//div[@id='teile_catalog_list']//ul[@class='filetree cat_tree treeview']//li");
    }

    SelenideElement parentCategoryInSideBar(String titleOfParentCategory) {
        return $x("//div[@id='teile_catalog_list']//ul[@class='filetree cat_tree treeview']//span[contains(text(),'" + titleOfParentCategory + "')]");
    }

    SelenideElement childCategoryBlockInSideBar() {
        return $x("//div[@class='block categories blue topSubCats']");
    }

    ElementsCollection linksOfChildCategoriesBlockInSideBar() {
        return $$x("//div[@class='block categories blue topSubCats']//li");
    }

    SelenideElement titleOfChildCategoriesBlockInSideBar() {
        return $x("//div[@class='block categories blue topSubCats']/../b");
    }

    ElementsCollection childCategoriesInChildCategoryBlock() {
        return $$x("//ul[@class='simple_links']//li");
    }

    SelenideElement titleOfChildCategoriesBlock() {
        return $x("//div[@class='title']");
    }

    SelenideElement mainImageOfChildCategoriesBlock() {
        return $x("//div[@class='filter_small_img']/img");
    }

    ElementsCollection titleOfLinksInChildCategoriesBlock() {
        return $$x("//ul[@class='simple_links']//li//span");
    }

    ElementsCollection imageOfLinksInChildCategoriesBlock() {
        return $$x("//ul[@class='simple_links']//li//img");
    }

    SelenideElement headlineInHeader() {
        return $x("//div[@class='top_title no_image']");
    }

    SelenideElement iconOfTruckInHeadlineOfSelector() {
        return $x("//span[@class='car-icon']");
    }

    SelenideElement titleOfTruckInHeadlineOfSelector() {
        return $x("//div[contains(@class,'block-select-car__head-car--lkw')]/span[2]");
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

    SelenideElement headlineOfTopProductsBlock() {
        return $x("//div[@class='title_list']");
    }

    SelenideElement textForHeadlineOfTopProductsBlock() {
        return $x("//span[@class='more_text']");
    }

    SelenideElement topListBlock() {
        return $x("//ul[@class='list_products']");
    }

    ElementsCollection productsInTopListBlock() {
        return $$x("//ul[@class='list_products']/li");
    }

    SelenideElement  btnAddToBasketTopBLock(int position) {return $x("(//div[@class='button '])["+position+"]");}

    SelenideElement basketDropMenu() {
        return $x("//div[@class='cart-items-block ']");
    }

    SelenideElement basket() {
        return $x("//a[@class='header-cart__link']");
    }

    ElementsCollection imageOfTopProduct() {return $$x("//div[@class='image']/span[2]/img");}

    ElementsCollection titleOfTopProduct() {return $$x("//div[@class='name']/a");}

    ElementsCollection linkDetails() {return $$x("//button[@class='link details ga-click']");}

    SelenideElement closeCookiesPopUp() {
        return $x("//div[@class='block-cookies__close']");
    }

    ElementsCollection titleOfChildCategoriesInSideBar() {return $$x("//div[@class='block categories blue topSubCats']//li/span");}
}
