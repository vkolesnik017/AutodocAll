package PKW;


import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;


public class Index_chemicals_page {


    SelenideElement blockSeoTextWithDescription() {
        return $x("//div[@class='text_description']");
    }

    SelenideElement separateCategoryInMainCatalogCategories() {
        return $x("//div[@class='category-block']//a[@class='category-block__item ga-click'][1]");
    }

    SelenideElement blockBreadCrumb() {
        return $x("//div[@class='crabs']");
    }

    SelenideElement firstBreadCrumb() {
        return $x("//div[@class='crabs']//div[@style='float:left;'][1]");
    }

    SelenideElement secondBreadCrumb() {
        return $x("//div[@class='crabs']//div[@style='float:left;'][2]/a");
    }


}
