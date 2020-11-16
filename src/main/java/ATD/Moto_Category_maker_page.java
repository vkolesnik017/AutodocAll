package ATD;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Moto_Category_maker_page {
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


    ElementsCollection breadCrumbsLinks() {return $$x("//div[@class='steps breadcrumbs']/ul/li//span/*[self::a or self::span]");}

    SelenideElement iconOfCatalogBrandInBreadCrumbs() {return $x("//li[@class='step_1 active parts_step_1']//img");}

    SelenideElement mainHeadline() {return $x("//div[@class='title_count_search ']/h2");}

    SelenideElement iconOfBrandInHeadline() {return $x("//div[@class='title_count_search ']/img");}

    ElementsCollection modelsLinks() {return $$x("//ul[@class='moto-cars-models__list']/li").filter(visible);}

    SelenideElement modelsBlock() {return $x("//div[@class='moto-cars-models']");}

    SelenideElement linkMoreOfModelBlock() {return $x("//div[@class='moto-cars-models']//div[@class='m_text show']");}

    SelenideElement linkLessOfModelBlock() {return $x("//div[@class='moto-cars-models']//div[@class='m_text hide']");}

    SelenideElement headlineOfBrandsBlock() {return $x("//h3[@class='title_list no_border_top pad_size']");}

    ElementsCollection brandsLinks() {return $$x("//div[@class='kategorie_top_brands']/ul/li").filter(visible);}

    SelenideElement brandsBlock() {return $x("//div[@class='kategorie_top_brands']");}

    SelenideElement linkMoreOfBrandsBlock() {return $x("//div[@class='kategorie_top_brands']//div/div");}

    ElementsCollection imageOfTopProducts() {return $$x("//div[@class='rec_products_image']//img");}

    ElementsCollection childCategoriesInSideBar() {return $$x("//div[@class='block categories blue topSubCats']//li/a");}

 }
