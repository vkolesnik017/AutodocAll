package ATD;

import io.qameta.allure.Step;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;

public class Moto_Category_car_list_model_page_Logic extends Moto_Category_car_list_model_page {

    @Step(" selecting motorcycle in selector .Moto_Category_car_list_model_page")
    public Moto_Category_car_list_page_Logic selectMotoInSelector(String marke, String model, String motor) {
        brandOfMotoField().selectOptionByValue("0");
        brandOfMotoField().selectOptionByValue(marke);
        modelFiledInSelector().selectOptionByValue(model);
        motorFiledInSelector().selectOptionByValue(motor);
        searchButton().click();
        return page(Moto_Category_car_list_page_Logic.class);
    }


    @Step(" check bread crumbs block .Moto_Category_car_list_model_page")
    public Moto_Category_car_list_model_page_Logic checkBreadCrumbsBlock() throws SQLException {
        breadCrumbsLinks().shouldHaveSize(6);
        checkFirstLinkOfBreadCrumbsLinks();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "moto_categories"));
        back();
        checkSecondLinkOfBreadCrumbsLinks("Filter");
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "moto_parent_category"));
        back();
        checkThirdLinkOfBreadCrumbsLinks("Ölfilter");
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "moto_category"));
        back();
        checkFourthLinkOfBreadCrumbsLinks("BMW");
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "moto_category_maker"));
        back();
        checkFifthLinkOfBreadCrumbsLinks("K");
        checkSixthLinkOfBreadCrumbsLinks("Motorrad Wählen");
        return this;
    }

    @Step(" check first link of bread crumbs links .Moto_Category_car_list_model_page")
    public Moto_Categories_page_Logic checkFirstLinkOfBreadCrumbsLinks() {
        iconOfCatalogBrandInBreadCrumbs().shouldBe(visible).click();
        return page(Moto_Categories_page_Logic.class);
    }

    @Step(" check second link of bread crumbs links .Moto_Category_car_list_model_page")
    public Moto_Parent_Category_page_Logic checkSecondLinkOfBreadCrumbsLinks(String title) {
        breadCrumbsLinks().get(1).shouldBe(visible).shouldHave(text(title)).click();
        return page(Moto_Parent_Category_page_Logic.class);
    }

    @Step(" check third link of bread crumbs links .Moto_Category_car_list_model_page")
    public Moto_Category_page_Logic checkThirdLinkOfBreadCrumbsLinks(String title) {
        breadCrumbsLinks().get(2).shouldBe(visible).shouldHave(text(title)).click();
        return page(Moto_Category_page_Logic.class);
    }

    @Step(" check fourth link of bread crumbs links .Moto_Category_car_list_model_page")
    public Moto_Category_maker_page_Logic checkFourthLinkOfBreadCrumbsLinks(String title) {
        breadCrumbsLinks().get(3).shouldBe(visible).shouldHave(text(title)).click();
        return page(Moto_Category_maker_page_Logic.class);
    }

    @Step(" check fifth link of bread crumbs links .Moto_Category_car_list_model_page")
    public Moto_Category_car_list_model_page_Logic checkFifthLinkOfBreadCrumbsLinks(String title) {
        breadCrumbsLinks().get(4).shouldBe(visible).shouldHave(text(title)).shouldNotHave(attribute("href"));
        return this;
    }

    @Step(" check sixth link of bread crumbs links .Moto_Category_car_list_model_page")
    public Moto_Category_car_list_model_page_Logic checkSixthLinkOfBreadCrumbsLinks(String title) {
        breadCrumbsLinks().get(5).shouldBe(visible).shouldHave(text(title)).shouldNotHave(attribute("href"));
        return this;
    }

    @Step(" get id of an analog product .Moto_Category_car_list_model_page")
    public String getIdOfAnalogProduct() {
        clickOnReplacementBtnOfProduct("26-8031");
        analogBlockOfProduct().should(appear);
        btnAddToBasketAtAnAnalogProduct().get(0).scrollIntoView("{block: \"center\"}");
        String idOfBtn = btnAddToBasketAtAnAnalogProduct().get(0).shouldBe(visible).getAttribute("data-ga-label");
        return idOfBtn;
    }

    @Step("add product to basket from an analog block .Moto_Category_car_list_model_page")
    public Cart_page_Logic addProductToBasketFromAnalogBlock() {
        analogProducts().get(0).shouldBe(visible).hover();
        detailsBlockOfAnalogProduct().get(0).shouldBe(visible);
        visibleBtnAddToBasketAtAnAnalogProduct().get(0).click();
        if (!basketDropMenu().isDisplayed()) {
            visibleBtnAddToBasketAtAnAnalogProduct().get(0).click();
        }
        basketDropMenu().should(appear);
        basketDropMenu().should(disappear);
        basket().click();
        return page(Cart_page_Logic.class);
    }

    @Step("click on replacement button of product .Moto_Category_car_list_model_page")
    public Moto_Category_car_list_model_page_Logic clickOnReplacementBtnOfProduct(String artNumber) {
        btnShowReplacement(artNumber).shouldBe(visible).click();
        return this;
    }


    @Step("click on child category in sidebar .Moto_Category_car_list_model_page")
    public Moto_Category_car_list_model_page_Logic clickOnChildCategoryInSidebar(int position) {
        childCategoriesInSideBar().get(position).shouldBe(visible).click();
        return page(Moto_Category_car_list_model_page_Logic.class);
    }
}
