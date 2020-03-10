package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class LKW_Categories_maker_page {

    protected SelenideElement firstLinkBreadCrumbsBlock() {
        return $x("//li[@class='step_1 active parts_step_1']");
    }

    protected SelenideElement secondLinkBreadCrumbsBlock() {
        return $x("//li[@class='step_2 not_active parts_step_2']");
    }

    protected SelenideElement thirdLinkBreadCrumbsBlock() {
        return $x("//li[@class='step_3 not_active parts_step_3']");
    }

    protected SelenideElement imageOfFirstLinkBreadCrumbs() {
        return $x("//li[@class='step_1 active parts_step_1']//img");
    }

    protected SelenideElement titleOfFirstLinkBreadCrumbs() {
        return $x("//li[@class='step_1 active parts_step_1']//a/span");
    }

    protected SelenideElement mainImageBlock() {
        return $x("//div[@class='image-car-model']");
    }

    protected SelenideElement iconOfCarInMainImageBlock() {
        return $x("//div[@class='image-car-model']/img");
    }

    protected SelenideElement titleOfTopParentCategoryBlock() {
        return $x("//div[@class='cont']//div[@class='title_list no_border_top'][2]");
    }

    protected SelenideElement topCategoriesCatalog() {
        return $x("//div[@class='truck-cars-parts']");
    }

    protected ElementsCollection categoriesOfTopCategoriesCatalog() {
        return $$x("//li[@class='truck-cars-parts__item']");
    }

    protected SelenideElement imageOfTopCategoryBlock(int position) {
        return $x("(//span[@class='truck-cars-parts__item-image'])[" + position + "]");
    }

    protected SelenideElement titleOfTopCategoryBlock(int position) {
        return $x("(//span[@class='truck-cars-parts__item-title'])[" + position + "]");
    }

    protected ElementsCollection linksOfTopCategoryBlock(int position) {
        return $$x("//li[@class='truck-cars-parts__item'][" + position + "]//a[@class='truck-cars-parts__cat-link js--lkw-modal__cat-link']");
    }


    protected SelenideElement seoTextBlock() {
        return $x("//div[@class='search_n_text']");
    }

    protected SelenideElement linkBlockTopBrand() {
        return $x("//div[@class='block_links']");
    }

    protected ElementsCollection linksInTopBrandBlock() {
        return $$x("//div[@class='block_links']/a");
    }

    protected SelenideElement categoriesInSideBar() {
        return $x("//div[@class='teile_catalog']");
    }

}
