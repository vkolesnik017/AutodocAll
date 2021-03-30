package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Moto_Category_page {
    protected SelenideElement imageOfChildCategory() {
        return $x("//div[@class='autoteile-top-content__image']");
    }

    SelenideElement motoSelectorBlock() {
        return $(byId("top-select"));
    }

    SelenideElement brandOfMotoField() {
        return $(byId("form_maker_id"));
    }

    SelenideElement btnSearchAtSelector() {
        return $x("//a[@class='submit search_button']");
    }

    SelenideElement tooltipOfMarkeField() {
        return $x("//div[@id='maker-select']//div[@id='selector-error-tooltip']");
    }

    SelenideElement tooltipOfModelField() {
        return $(byId("selector-error-tooltip-model"));
    }

    SelenideElement tooltipOfMotorField() {
        return $(byId("selector-error-tooltip-car"));
    }

    SelenideElement modelFiledInSelector() {
        return $(byId("form_model_id"));
    }

    SelenideElement motorFiledInSelector() {
        return $(byId("form_car_id"));
    }

    SelenideElement searchButton() {return $x("//a[contains(@class,'search_button')]");}

    SelenideElement btnResetOfSelector() {return $(byId("reset_selector_form"));}

    SelenideElement benefitsBlock() {return $x("//div[@class='autoteile-features']");}

    SelenideElement headlineBenefitsBlock() {return $x("//div[@class='autoteile-features']/b");}

    ElementsCollection benefitsLinks() {return $$x("//li[@class='has_tooltip']");}

    ElementsCollection imageOfBenefitsLinks() {return $$x("//ul[@class='autoteile-features__list']/li/div[@class='icon']");}

    ElementsCollection tooltipOfBenefitsLinks() {return $$x("//div[@class='tooltip-container']");}

    ElementsCollection textOfBenefitsLinks() {return $$x("//li[@class='has_tooltip']/span");}

    SelenideElement mainImageOfChildCategory() {return $x("//div[@class='autoteile-top-content__image']/img");}

    ElementsCollection breadCrumbsLinks() {return $$x("//div[@class='steps breadcrumbs']/ul/li//span/*[self::a or self::span]");}

    SelenideElement iconOfCatalogBrandInBreadCrumbs() {return $x("//li[@class='step_1 active parts_step_1']//img");}

    SelenideElement topProductsBlock() {return $x("//div[@class='sub_catalog_grid']");}

    SelenideElement headlineOfTopProductsBlock() {return $x("//div[@class='title_list'] ");}

    SelenideElement infoTextOfTopProductBlock() {return $x("//span[@class='more_text'] ");}

    ElementsCollection topProducts() {return $$(".rec_products_block ");}

    SelenideElement basketDropMenu() {
        return $x("//div[@class='cart-items-block ']");
    }

    SelenideElement basket() {
        return $x("//a[@class='header-cart__link']");
    }

    ElementsCollection btnAddToBasketTopProduct() {return $$x("//div[@class='rec_prod_btn button ']");}

    ElementsCollection titleOfTopProducts() {return $$x("//div[@class='rec_products_image']/following-sibling::div[1]/span");}

    ElementsCollection  imageOfTopProducts() {return $$x("//div[@class='rec_products_image']/a/img");}

    ElementsCollection  detailsOfTopProductsBlock() {return $$x("//div[@class='rec_prod_info_popup']");}

    ElementsCollection  btnDetailsOfTopProducts() {return $$x("//div[@class='product_desc_table_container']/following-sibling::span");}

    ElementsCollection childCategoriesInSideBar() {return $$x("//div[@class='block categories blue topSubCats']//li/a");}

    ElementsCollection btnAddDangerousProductToWishList() {return $$x("//span[@class='dangerous-listing__show-more']/ancestor::div[@class='rec_products_block js-products-item ']/div[1]");}

    ElementsCollection signalWordOfDangerousProduct() {return $$x("//div[contains(@class,'dangerous-listing__title')]");}

    ElementsCollection dangerousProducts() {return $$x("//span[@class='dangerous-listing__show-more']/ancestor::div[@class='rec_products_block js-products-item ']/div[1]");}

    ElementsCollection attributeOfWarningIcon(int positionOfProduct) {return $$x("(//span[@class='dangerous-listing__show-more'])["+positionOfProduct+"]/ancestor::div[@class='rec_prod_info_popup']//div[@class='dangerous-listing__icon dangerous-listing__icon-attention']");}

    ElementsCollection labelTitleDangerousProducts() {return $$x("//div[@class='rec_products_block']//span[@class='dangerous-listing__show-more']");}

    ElementsCollection dangerousIconInWarningPopUp() {return $$x("//div[@class='popup-dangerous__icon']").filter(visible);}

    SelenideElement blackBackground() {return $x("//div[@class='overlay black hidden']");}

    SelenideElement warningPopUp() {return $x("//div[@class='popup-dangerous']");}

    SelenideElement titleOfDangerousPopUp() {return $x("//div[@class='popup-dangerous__title']");}

    SelenideElement infoTextOfDangerousPopUp() {return $x("//div[@class='popup-dangerous']//p");}

    ElementsCollection genericsInSideBar() {return $$x("//div[@class='block categories blue topSubCats']//li");}

    ElementsCollection btnAddProductToWishList() {return $$x("//div[@class='add-to-wishlist add-article']");}

    SelenideElement seoText() {return $(".block_youtube_video");}
}
