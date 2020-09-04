package PKW;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;


public class Parts_group_page {


    SelenideElement titlePage() {
        return $x("//div[@class='top_title']/h2");
    }

    ElementsCollection visibleTopProducts() {return $$x("//ul[@class='pkw-related']/li").filter(visible);}

    ElementsCollection visibleArtNumOfTopProducts() {return $$x("//span[@class='pkw-related__item-art']").filter(visible);}

    SelenideElement topProductsBlock() {return $(byId("topSeller"));}

    ElementsCollection allCharacteristicsOfTopProducts() {return $$x("//div[@class='pkw-related__item-header']//ul/li");}

    ElementsCollection visibleImageOfTopBrands() {return $$x("//div[@class='pkw-related__header-row']/img").filter(visible);}

    ElementsCollection visibleCharacteristicsOfTopProducts(int position) {return $$x("(//div[@class='pkw-related__item-header'])["+position+"]//ul/li");}

    SelenideElement forwardOfTopBlock() {return $x("//div[@id='topSeller']//a[@class='bx-next']");}

   }
