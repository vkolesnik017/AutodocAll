package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

class LKW_Category_page {
    SelenideElement imageOfChildCategory() {
        return $x("//div[@class='autoteile-top-content__image']/img");
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

    SelenideElement advantageBlock() {
        return $x("//div[@class='autoteile-features']");
    }

    SelenideElement headlineOfAdvantageBlock() {
        return $x("//div[@class='autoteile-features']/b");
    }

    ElementsCollection advantagesInBlock() {
        return $$x("//ul[@class='autoteile-features__list']//li");
    }

    ElementsCollection tooltipOfAdvantage() {
        return $$x("//div[@class='tooltip-container']");
    }

    ElementsCollection imageOfAdvantageInBlock() {
        return $$x("//ul[@class='autoteile-features__list']//li//img");
    }

    ElementsCollection titleOfAdvantageInBlock() {
        return $$x("//ul[@class='autoteile-features__list']//li/span");
    }

    SelenideElement headlineOfTopProductsBlock() {return $x("//div[@class='title_list no_border_top'] ");}

    SelenideElement topBLock() {return $x("//div[@class='sub_catalog_grid gfre4']");}

    ElementsCollection productsOfTopBlock() {return $$(".rec_products_block");}

    SelenideElement btnAddToBasketTopBLock(int position) {return $x("(//div[@class='rec_prod_btn button '])["+position+"]");}

    SelenideElement basketDropMenu() {
        return $x("//div[@class='cart-items-block ']");
    }

    SelenideElement basket() {
        return $x("//a[@class='header-cart__link']");
    }

    SelenideElement headLineOfTopProductsBlock() {return $x("//div[@class='title_list no_border_top']");}

    ElementsCollection additionInfoBlockOfTopProduct() {return $$x("//div[@class='rec_prod_info_popup']");}

    SelenideElement blockBefore()  {return $x("//div[@class='sub_catalog_grid gfre4']/preceding-sibling::div[1]");}

    ElementsCollection titleOfTopProduct() {return $$x("//div[@class='rec_prod_title ']/span");}

    ElementsCollection imageOfTopProduct() {return $$x("//a[@class='ga-click']/img");}

    ElementsCollection linkDetails() {return $$x("//span[@class='details link']");}

    SelenideElement closeCookiesPopUp() {return $x("//div[@class='block-cookies__close']");}

    SelenideElement topProductsBlock() {return $x("//div[@class='sub_catalog_grid gfre4']");}

    ElementsCollection signalWordOfDangerousProduct() {return $$x("//div[contains(@class,'dangerous-listing__title')]");}

    ElementsCollection attributeOfWarningIcon(int positionOfProduct) {return $$x("(//div[@class='rec_prod_info_popup'])["+positionOfProduct+"]//div[@class='dangerous-listing__icon dangerous-listing__icon-attention']");}

    ElementsCollection dangerousProducts() {return $$x("//span[@class='dangerous-listing__show-more']/ancestor::div[@class='rec_products_block js-products-item ']/div[1]");}

    ElementsCollection labelIconDangerousProducts() {return $$x("//div[@class='rec_products_block']//div[@class='dangerous-listing__icon'][1]").filter(visible);}

    SelenideElement blackBackground() {return $x("//div[@class='overlay black hidden']");}

    SelenideElement warningPopUp() {return $x("//div[@class='popup-dangerous']");}

    SelenideElement titleOfDangerousPopUp() {return $x("//div[@class='popup-dangerous__title']");}

    SelenideElement infoTextOfDangerousPopUp() {return $x("//div[@class='popup-dangerous']//p");}

    ElementsCollection labelTitleDangerousProducts() {return $$x("//div[@class='rec_products_block']//span[@class='dangerous-listing__show-more']");}

    ElementsCollection dangerousIconInWarningPopUp() {return $$x("//div[@class='popup-dangerous__icon']").filter(visible);}

    ElementsCollection btnAddDangerousProductToWishList() {return $$x("//span[@class='dangerous-listing__show-more']/ancestor::div[@class='rec_products_block js-products-item ']/div[1]");}

    ElementsCollection urlOfTopProduct() {return $$x("//div[@class='rec_products_image']/a");}

   public SelenideElement infoPriceForSetFromTopProduct() { return $x("//div[@class='recommended_products']/div//p[text()='(Preis pro Satz)']");}

    public SelenideElement infoPriceForPieceFromTopProduct() { return $x("//div[@class='recommended_products']/div//p[text()='(Artikelpreis)']");}
}
