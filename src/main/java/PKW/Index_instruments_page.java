package PKW;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;


public class Index_instruments_page {

    SelenideElement titleMainPage() {
        return $x("//h1[@class='page-title']");
    }

    SelenideElement firstBreadCrumb() {
        return $x("//div[@class='breadcrumbs']//a[@href][1]");
    }

    SelenideElement secondBreadCrumb() {
        return $x("//div[@class='breadcrumbs']//a[@class='defcur']");
    }

    SelenideElement blockTopBrands() {
        return $x("//div[@class='brand-block']");
    }

    SelenideElement firstBrandInTopBrandsBlock() {
        return $x("//div[@class='brand-block']//a[@class='brand-block__wrap-item'][1]");
    }

    SelenideElement mainCatalogCategoriesBlock() {
        return $x("//div[@class='category-block']");
    }


}
