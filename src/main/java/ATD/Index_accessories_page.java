package ATD;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;



class Index_accessories_page {

    SelenideElement blockFeatures() {
        return $x("//div[@class='features__wrapp']");
    }

    SelenideElement blockDelivery() {
        return $x("//div[@class='delivery']");
    }

    SelenideElement blockTopBrands() {
        return $x("//div[@class='top-brands']");
    }

    SelenideElement blockTopProducts() {
        return  $x("//div[@class='top-product-block']");
    }

    SelenideElement catalogFirstGroup() {
        return $x("//div[@class='accessories-catalog__row'][1]/div[@class='accessories-catalog__group'][1]");
    }

    SelenideElement secondCategoryInLogicalUnion(){
        return $x("//div[@class='accessories-catalog__categories' and @style='display: block;']//li[2]");
    }

    SelenideElement mainTitlePage() {
        return $x("//h1[@class='page-title'][text()]");
    }

    SelenideElement nameFirstCategoryBlockTopAccessories() {
        return $x("//div[@class='accessories-tabs__item active']//div[@class='accessories-tabs__slide-title']");
    }

    SelenideElement textSubtitle() {
        return $x("//div[@class='page-subtitle']");
    }

    SelenideElement blockTopCategories() {
        return $x("//div[@class='accessories-top']");
    }

    ElementsCollection miniCardsInTop6ProductsBlock () {
        return $$x("//div[@class='top-product']//div[@class='product-list__item active']");
    }


}
