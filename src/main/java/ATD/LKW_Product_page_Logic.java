package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class LKW_Product_page_Logic extends LKW_Product_page {

    @Step("Check links in bread crumbs block .LKW_Product_page")
    public LKW_Product_page_Logic checkLinksInBreadCrumbsBlock() {
        breadCrumbsLinks().shouldHave(size(4));
        firstLinkOfBreadCrumbsBlock().shouldBe(visible);
        secondLinkOfBreadCrumbsBlock().shouldHave(exactText("Filter"));
        thirdLinkOfBreadCrumbsBlock().shouldHave(exactText("Innenraumluftfilter"));
        fourthLinkOfBreadCrumbsBlock().shouldHave(exactText("HENGST FILTER Filter, Innenraumluft")).shouldNotHave(attribute("href"));
        return this;
    }

    @Step("Check links in bread crumbs block with not active product .LKW_Product_page")
    public LKW_Product_page_Logic checkLinksInBreadCrumbsBlockWithNotActiveProduct() {
        breadCrumbsLinks().shouldHave(size(2));
        firstLinkOfBreadCrumbsBlock().shouldBe(visible);
        secondLinkOfBreadCrumbsBlock().shouldHave(exactText("PROKOM Relais"));
        return this;
    }

    @Step("Check links in bread crumbs block with selecting truck .LKW_Product_page")
    public LKW_Category_car_list_page_Logic checkLinksInBreadCrumbsWithTruck() {
        selectTruckInHorizontalSelector("24", "4176", "1003688");
        titleInTruckSelectorHeader().shouldHave(text("Dieses Produkt passt zu Ihrem"));
        thirdLinkOfBreadCrumbsBlock().click();
        return page(LKW_Category_car_list_page_Logic.class);
    }


    @Step("Select truck in horizontal truck selector .LKW_Product_page")
    public LKW_Product_page_Logic selectTruckInHorizontalSelector(String marke, String model, String motor) {
        markeInHorizontalTruckSelector().selectOptionByValue(marke);
        modelInHorizontalTruckSelector().selectOptionByValue(model);
        motorInHorizontalTruckSelector().selectOptionByValue(motor);
        searchBtnInHorizontalTruckSelector().click();
        return this;
    }

    @Step("Select tecDoc Catalog in bread crumbs .LKW_Product_page")
    public LKW_Categories_page_Logic goToTecDocCatalogFromBreadCrumbs() {
        firstLinkOfBreadCrumbsBlock().click();
        return page(LKW_Categories_page_Logic.class);
    }

}
