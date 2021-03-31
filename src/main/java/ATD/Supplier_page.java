package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;

public class Supplier_page {
    SelenideElement iconOfBrandInMainHeadline() {
        return $x("//div[@class='catalog-title__block']/img");
    }
    SelenideElement titleOfBrandInMainHeadline() {
        return $x("//div[@class='catalog-title__block']/h1");
    }

    SelenideElement breadCrumbsBlock() {return $x("//div[@class='steps breadcrumbs']");}

    SelenideElement selectorBlock() {return $(byId("top-select"));}

    SelenideElement titleOfTopChildCategories() {return $x("//h2[@class='bestsellers__title']");}

    ElementsCollection childCategories() {return $$x("//ul[@class='bestsellers__list']/li/a");}

    SelenideElement titleOfAdvantagesBlock() {return $x("//div[@class='brand-features__title']/h3");}

    ElementsCollection blocksOfOfAdvantagesBlock() {return $$x("//div[@class='brand-features__wrap-icons']/div");}

    SelenideElement titleOfParentCategoriesBlock() {return $x("//h2[@class='catalog-block__title']");}

    SelenideElement titleOfReviewBlock() {return $x("//h3[@class='product-feedback__title']");}

    SelenideElement titleOfBrandsBlock() {return $x("//h3[@class='top-brands__title']");}

    SelenideElement titleOfTopProductsBlock() {return $x("//h3[@class='top-product-block__title']/span");}

    SelenideElement topProductsBlock() {return $(byId("anchor-point"));}

    ElementsCollection brandLinks() {return $$x("//a[@class='top-brands__item-link']");}

    ElementsCollection visibleBrandsLinks() {return $$x("//div[@class='slick-track']/*[self::li[@class='top-brands__item slick-slide slick-current slick-active'] or self::li[@class='top-brands__item slick-slide slick-active']]/a").filter(visible);}

    SelenideElement btnNextOfBrandsBlock() {return $x("//span[@class='next slick-arrow']");}

    SelenideElement btnBackOfBrandsBlock() {return $x("//span[@class='prev slick-arrow']");}

    SelenideElement notActiveBtnNextOfBrandsBlock() {return $x("//span[@class='next slick-arrow slick-disabled']");}

    ElementsCollection idNumParents() {
        return $$x("//div[@class='catalog-block']//div[@class='catalog-block__wrap-image']/img");
    }

   public ElementsCollection categoriesCatalog() {
        return $$x("//div[@class='catalog-block']//div[@class='catalog-block__wrap']//ul/li//*[2]");
    }

    SelenideElement seoText() {return $(".brand-features__wrap");}
}
