package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Moto_Parent_Category_page {

    SelenideElement brandOfMotoField() {
        return $(byId("form_maker_id"));
    }

    SelenideElement modelFiledInSelector() {
        return $(byId("form_model_id"));
    }

    SelenideElement motorFiledInSelector() {
        return $(byId("form_car_id"));
    }

    SelenideElement searchButton() {return $x("//a[contains(@class,'search_button')]");}

    SelenideElement btnResetOfSelector() {return $(byId("reset_selector_form"));}

    SelenideElement headlineOfChildCategoryBlock() {return $x("//div[@class='title']");}

    SelenideElement imageOfParentCategory() {return $x("//div[@class='filter_small_img']/img");}

    SelenideElement childCategoriesListBlock() {return $x("//ul[@class='simple_links']");}

    ElementsCollection linksOfChildCategoriesList() {return $$x("//ul[@class='simple_links']/li");}

    ElementsCollection textOfChildCategoriesList() {return $$x("//ul[@class='simple_links']/li/a/span");}

    ElementsCollection imageOfChildCategoriesList() {return $$x("//ul[@class='simple_links']/li/a/img");}

    ElementsCollection breadCrumbsLinks() {return $$x("//div[@class='steps breadcrumbs']/ul/li//span/*[self::a or self::span]");}

    SelenideElement iconOfCatalogBrandInBreadCrumbs() {return $x("//li[@class='step_1 active parts_step_1']//img");}

    SelenideElement topProductsBlock() {return $x("//ul[@class='list_products']");}

    ElementsCollection topProducts() {return $$x("//ul[@class='list_products']/li");}

    ElementsCollection btnAddToBasketTopProduct() {
        return $$x("//div[@class='button ']");
    }

    SelenideElement basketDropMenu() {
        return $x("//div[@class='cart-items-block ']");
    }

    SelenideElement basket() {
        return $x("//a[@class='header-cart__link']");
    }

    ElementsCollection  titleOfTopProducts() {return $$x("//div[@class='name']/a");}

    ElementsCollection  imageOfTopProducts() {return $$x("//div[@class='image']/span[2]/img");}

    ElementsCollection  btnDetailsOfTopProducts() {return $$x("//div[@class='about']/div/following-sibling::button");}
}
