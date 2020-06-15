package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;



public class Listing_accessories_page {

    SelenideElement listingTitle() {
        return $x("//div[@class='cont']//h2[text()]");
    }

    SelenideElement selectedBrand() {
        return $x("//div[@class='js-brands-filter js-filter-wrapper']//li[@class='active']");
    }

    SelenideElement titleCategoriesBlockInSidebar() {
        return $x("//div[@class='block categories blue topSubCats']//b[text()]");
    }

    SelenideElement firstCategoryInSidebar() {
        return $x("//div[@class='sidebar']//li[1]//span[text()]");
    }

    ElementsCollection categoriesInSidebar() {
        return $$x("//div[@class='block categories blue topSubCats']/ul[@class='filetree cat_tree treeview']/li");
    }

    SelenideElement blockProductQuantity () {
        return $x("//div[@class='product_count']//span[@class='js-products-qty']");
    }

    SelenideElement blockChangePositionProductsOnListOrGrid() {
        return $x("//div[@class='filter_select ']");
    }

    SelenideElement btnChangePositionProductsAsGrid() {
        return $x("//div[@class='filter_select ']//span[@url][2]");
    }

    SelenideElement listingProductsDisplayedAsList() {
        return $x("//div[@class='listing-wrap']//ul[@class='list_products ']");
    }
    SelenideElement listingProductsDisplayedAsGrid() {
        return $x("//div[@class='listing-wrap']//div[@class='sub_catalog_grid']");
    }




}