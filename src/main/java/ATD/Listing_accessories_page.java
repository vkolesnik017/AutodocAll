package ATD;

import com.codeborne.selenide.SelenideElement;
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

    SelenideElement blockProductQuantity () {
        return $x("//div[@class='product_count']//span[@class='js-products-qty']");
    }


}
