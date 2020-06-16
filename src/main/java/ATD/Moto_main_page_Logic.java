package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Moto_main_page_Logic extends Moto_main_page {

    @Step("Check successfully Moto page loading. Moto_main_page")
    public Moto_main_page_Logic checkSuccessfullyMotoPageLoading() {
        menuCatalogInHeader().shouldBe(visible);
        Assert.assertTrue(url().contains("https://moto.autodoc.de/"));
        return this;
    }

    @Step("Checking that selector is empty. Moto_main_page")
    public Moto_main_page_Logic checkOfEmptyMotoSelector() {
        markeOfHorizontalMotoSelector().shouldHave(exactValue("0"));
        modelOfHorizontalMotoSelector().shouldHave(exactValue("0"));
        motorOfHorizontalMotoSelector().shouldHave(exactValue("0"));
        return this;
    }

    @Step("Select child category on Moto main page. Moto_main_page")
    public Moto_Category_page_Logic selectChildCategory() {
        tecDocCatalogOnMainPageMoto().scrollTo();
        childCategoryOnMotoMainPage().click();
        return page(Moto_Category_page_Logic.class);
    }

    @Step("Select child category on Moto main page. Moto_main_page")
    public Moto_Catalog_page_Logic selectMotoInHorizontalMotoSelector(String marke, String model, String motor) {
        markeOfHorizontalMotoSelector().selectOptionByValue(marke);
        modelOfHorizontalMotoSelector().selectOptionByValue(model);
        motorOfHorizontalMotoSelector().selectOptionByValue(motor);
        searchInHorizontalMotoSelector().click();
        return page(Moto_Catalog_page_Logic.class);
    }


    @Step("availability Of main searching field  . Moto_main_page")
    public Moto_main_page_Logic availabilityOfMainSearchingField() {
        mainSearchField().shouldBe(visible);
        return this;
    }


    @Step("click on main searching field  . Moto_main_page")
    public Moto_main_page_Logic clickOnMainSearchingField() {
        mainSearchField().click();
        hintsOfMainSearchingFieldBlock().shouldBe(visible);
        return this;
    }


    @Step("input of brand in main search field . Moto_main_page")
    public Search_page_Logic inputOfBrandInMainSearchField(String titleOfBrand) {
        mainSearchField().setValue(titleOfBrand).pressEnter();
        return page(Search_page_Logic.class);
    }


    @Step("availability of delivery block  . Moto_main_page")
    public Moto_main_page_Logic availabilityOfDeliveryBlock() {
        deliveryBlock().shouldBe(visible);
        return this;
    }

    @Step("availability of delivery block  . Moto_main_page")
    public Moto_main_page_Logic availabilityOfPaymentMethodsBlock() {
        paymentMethodsBlock().shouldBe(visible);
        return this;
    }


    @Step(" availability Of Linking Banner Block .Moto_main_page")
    public Moto_main_page_Logic availabilityOfLinkingBannerBlock() {
        linkingBannerBlock().shouldBe(visible);
        return this;
    }

    @Step(" check Transition By Click On Linking Banner .Moto_main_page")
    public Moto_main_page_Logic checkTransitionByClickOnLinkingBanner() throws SQLException {
        clickOnLeftLinkingBanner().checkCurrentUrl("index_instruments");
        back();
        clickOnRightLinkingBanner();
        checkingContainsUrl(new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", "moto_category2"));
        return this;
    }

    @Step(" click on left linking banner of Moto .Moto_main_page")
    public Index_instruments_page_Logic clickOnLeftLinkingBanner() {
        leftMotoLinkingBanner().click();
        return page(Index_instruments_page_Logic.class);
    }

    @Step(" click on right linking banner of Moto .Moto_main_page")
    public Moto_Category_page_Logic clickOnRightLinkingBanner() {
        rightMotoLinkingBanner().click();
        return page(Moto_Category_page_Logic.class);
    }


    @Step("availability Of Moto Selector .Moto_main_page")
    public Moto_main_page_Logic availabilityOfMotoSelector() {
        motorSelectorBlock().shouldBe(visible);
        return this;
    }


    @Step(" appears of tooltip for marke field in selector .Moto_main_page")
    public Moto_main_page_Logic visibilityOfToolTipForMarkeField() {
        btnSearchInSelector().shouldBe(visible).click();
        toolTipForBrandFieldInSelector().shouldBe(visible);
        return this;
    }

    @Step(" appears of tooltip for model field in selector .Moto_main_page")
    public Moto_main_page_Logic visibilityOfToolTipForModelField() {
        markeOfHorizontalMotoSelector().selectOptionByValue("4057");
        btnSearchInSelector().click();
        toolTipForModelFieldInSelector().shouldBe(visible);
        return this;
    }

    @Step(" appears of tooltip for motor field in selector .Moto_main_page")
    public Moto_main_page_Logic visibilityOfToolTipForMotorField() {
        modelOfHorizontalMotoSelector().selectOptionByValue("13475");
        btnSearchInSelector().click();
        toolTipForMotorFieldInSelector().shouldBe(visible);
        return this;
    }

    @Step(" Select brand of motorcycle .Moto_main_page")
    public Moto_main_page_Logic selectBrandOfMoto(String markeOfMoto) {
        markeOfHorizontalMotoSelector().selectOptionByValue(markeOfMoto);
        return this;
    }

    @Step(" reset of motorcycle selector .Moto_main_page")
    public Moto_main_page_Logic resetOfMotoSelector() {
        btnResetOfSelector().shouldBe(visible).click();
        return this;
    }

    @Step(" reset of motorcycle selector .Moto_main_page")
    public Moto_main_page_Logic presenceOfEmptyValuesInSelector() {
        btnResetOfSelector().shouldNotBe(visible);
        markeOfHorizontalMotoSelector().shouldHave(exactValue("0"));
        modelOfHorizontalMotoSelector().shouldHave(exactValue("0"));
        motorOfHorizontalMotoSelector().shouldHave(exactValue("0"));
        return this;
    }

    @Step(" check current url .Moto_main_page")
    public Moto_main_page_Logic checkCurrentUrl(String subRoute) throws SQLException {
        String urlFromBD = new DataBase().getRouteByRouteName("DE", subRoute);
        checkingContainsUrl(urlFromBD);
        return this;
    }

    @Step(" presence Of Top Parent Block .Moto_main_page")
    public Moto_main_page_Logic presenceOfTopParentBlock() {
        topParentBlock().shouldBe(visible);
        linkTopParentBlock().shouldHaveSize(12);
        return this;
    }

    @Step(" presence Of image Top Parent Block .Moto_main_page")
    public Moto_main_page_Logic presenceOfImageTopParentBlock() {
        for (int i = 0; i < linkTopParentBlock().size(); i++) {
            imageTopParentBlock().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step(" presence Of title Top Parent Block .Moto_main_page")
    public Moto_main_page_Logic presenceOfTitleTopParentBlock() {
        for (int i = 0; i < linkTopParentBlock().size(); i++) {
            titleTopParentBlock().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step(" presence Of child category block .Moto_main_page")
    public Moto_main_page_Logic presenceOfChildCategoryLinks() {
        for (int i = 1; i <= linkTopParentBlock().size(); i++) {
            childCategoryLinks(i).shouldHaveSize(3);
        }
        return this;
    }

    @Step(" click on More link at Parent catalog .Moto_main_page")
    public Moto_Categories_page_Logic clickOnMoreLinkAtParentCatalog() {
        linkMoreAtParentCategoryBlock().shouldBe(visible).click();
        return page(Moto_Categories_page_Logic.class);
    }

    @Step(" presence Of Headline at Top parent block .Moto_main_page")
    public Moto_main_page_Logic presenceOfHeadlineAtTopParentBlock() {
        headlineOfTopParentBlock().shouldBe(visible);
        return this;
    }


    @Step("check transition by click on image parent category .Moto_main_page")
    public Moto_main_page_Logic checkTransitionByClickOnImageParentCategory(String subRoute) throws SQLException {
        clickOnImageParentCategory();
        checkingContainsUrl(new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", subRoute));
        back();
        return this;
    }

    @Step("check transition by click on image parent category .Moto_main_page")
    public Moto_Parent_Category_page_Logic clickOnImageParentCategory() {
        imageTopParentBlock().get(0).shouldBe(visible).click();
        return page(Moto_Parent_Category_page_Logic.class);
    }

    @Step("check transition by click on child category .Moto_main_page")
    public Moto_main_page_Logic checkTransitionByClickOnChildCategory(String subRoute) throws SQLException {
        clickOnChildCategory();
        checkingContainsUrl(new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", subRoute));
        back();
        return this;
    }

    @Step("check transition by click on image parent category .Moto_main_page")
    public Moto_Category_page_Logic clickOnChildCategory() {
        childCategoryLinks(1).get(0).shouldBe(visible).click();
        return page(Moto_Category_page_Logic.class);
    }

    @Step("check transition by click on child category with selected motorcycle .Moto_main_page")
    public Moto_main_page_Logic checkTransitionByClickOnChildCategoryWithMoto(String subRoute) throws SQLException {
        clickOnChildCategoryWithMoto();
        checkingContainsUrl(new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", subRoute));
        back();
        return this;
    }

    @Step("check transition by click on image parent category with selected motorcycle .Moto_main_page")
    public Moto_Category_car_list_page_Logic clickOnChildCategoryWithMoto() {
        childCategoryLinks(1).get(0).shouldBe(visible).click();
        return page(Moto_Category_car_list_page_Logic.class);
    }

    @Step(" visibility of login/registration block .Moto_main_page")
    public Moto_main_page_Logic visibilityOfLoginRegistrationBlock() {
        registrationBlock().shouldBe(visible);
        return this;
    }

    @Step("login of user .Moto_main_page")
    public Profile_plus_page_Logic loginOfUser(String login, String password) {
        registrationBlock().click();
        loginPopUp().shouldBe(visible);
        loginFieldAtLoginPopUp().setValue(login);
        passwordFieldAtLoginPopUp().setValue(password);
        btnRegistrationAtLoginPopUp().click();
        return page(Profile_plus_page_Logic.class);
    }

    @Step("check of main elements at TOP brands block .Moto_main_page")
    public Moto_main_page_Logic checkOfMainElementsAtTopBrandsBlock() {
        topBrandsBlock().shouldBe(visible);
        titleOfTopBrandsBlock().shouldBe(visible);
        listOfTopBrands().shouldHaveSize(8);
        linkMoreAtTopBrandsBlock().shouldBe(visible);
        return this;
    }

    @Step("select TOP motorcycle brand from block .Moto_main_page")
    public Moto_Categories_maker_page_Logic selectTopMotoBrandFromBlock() {
        topBrandsBlock().shouldBe(visible);
        clickOnTopMotoBrand(0);
        return page(Moto_Categories_maker_page_Logic.class);
    }

    @Step("click on TOP motorcycle brand .Moto_main_page")
    public Moto_main_page_Logic clickOnTopMotoBrand(int position) {
        listOfTopBrands().get(position).click();
        return this;
    }

    @Step("click on link More at TOP brands block .Moto_main_page")
    public Moto_makers_page_Logic clickOnLinkMoreAtTopBrandsBlock() {
        linkMoreAtTopBrandsBlock().shouldBe(visible).click();
        return page(Moto_makers_page_Logic.class);
    }

    @Step("presence of navigation block .Moto_main_page")
    public Moto_main_page_Logic presenceOfNavigationBlock() {
        navigationBlockInHeader().shouldBe(visible);
        linksAtTopCategoriesInHeader().shouldHaveSize(9);
        return this;
    }

    @Step("check links of TOP categories in header .Moto_main_page")
    public Moto_main_page_Logic checkTopCategoriesInHeader() throws SQLException {
        linksAtTopCategoriesInHeader().get(0).shouldHave(attribute("href", "https://www." + new DataBase().getRouteByRouteName("DE", "main") + "/"));  //TODO  сделать метод, который будет возвращать только главный рут. параметры: envFromTest,shop,routeName - https://www.autodoc.de
        linksAtTopCategoriesInHeader().get(1).shouldHave(attribute("href", "https://" + new DataBase().getRouteByRouteName("DE", "lkw_main") + "/"));
        linksAtTopCategoriesInHeader().get(2).shouldHave(attribute("href", new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "tyres4")));
        linksAtTopCategoriesInHeader().get(3).shouldHave(attribute("href", new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "index_instruments")));
        linksAtTopCategoriesInHeader().get(4).shouldHave(attribute("href", new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "index_accessories")));
        linksAtTopCategoriesInHeader().get(5).shouldHave(attribute("href", new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", "moto_category4")));
        linksAtTopCategoriesInHeader().get(6).shouldHave(attribute("href", new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", "moto_parent_category")));
        linksAtTopCategoriesInHeader().get(7).shouldHave(attribute("href", new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", "moto_parent_category2")));
        linksAtTopCategoriesInHeader().get(8).shouldHave(attribute("href", new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", "moto_parent_category3")));
        return this;
    }

    @Step("check navigation links in header .Moto_main_page")
    public Moto_main_page_Logic checkNavigationLinks() throws SQLException {
        clickOnFirstTopLinkInHeader();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "main"));
        back();
        clickOnSecondTopLinkInHeader();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "lkw_main"));
        back();
        clickOnThirdTopLinkInHeader();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "tyres4"));
        back();
        clickOnFourthTopLinkInHeader();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "index_instruments"));
        back();
        clickOnFifthTopLinkInHeader();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "index_accessories"));
        back();
        clickOnSixthTopLinkInHeader();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "moto_category4"));
        back();
        clickOnSeventhTopLinkInHeader();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "moto_parent_category"));
        back();
        clickOnEighthTopLinkInHeader();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "moto_parent_category2"));
        back();
        clickOnNinthTopLinkInHeader();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "moto_parent_category3"));
        return this;
    }

    @Step("click on first TOP link in header .Moto_main_page")
    public Main_page_Logic clickOnFirstTopLinkInHeader() {
        navigationBlockInHeader().shouldBe(visible);
        linksAtTopCategoriesInHeader().get(0).click();
        return page(Main_page_Logic.class);
    }

    @Step("click on second TOP link in header .Moto_main_page")
    public LKW_main_page_Logic clickOnSecondTopLinkInHeader() {
        navigationBlockInHeader().shouldBe(visible);
        linksAtTopCategoriesInHeader().get(1).click();
        return page(LKW_main_page_Logic.class);
    }

    @Step("click on third TOP link in header .Moto_main_page")
    public Tyres_page_Logic clickOnThirdTopLinkInHeader() {
        navigationBlockInHeader().shouldBe(visible);
        linksAtTopCategoriesInHeader().get(2).click();
        return page(Tyres_page_Logic.class);
    }

    @Step("click on fourth TOP link in header .Moto_main_page")
    public Index_instruments_page_Logic clickOnFourthTopLinkInHeader() {
        navigationBlockInHeader().shouldBe(visible);
        linksAtTopCategoriesInHeader().get(3).click();
        return page(Index_instruments_page_Logic.class);
    }

    @Step("click on fifth TOP link in header .Moto_main_page")
    public Index_accessories_page_Logic clickOnFifthTopLinkInHeader() {
        navigationBlockInHeader().shouldBe(visible);
        linksAtTopCategoriesInHeader().get(4).click();
        return page(Index_accessories_page_Logic.class);
    }

    @Step("click on sixth TOP link in header .Moto_main_page")
    public Moto_Category_page_Logic clickOnSixthTopLinkInHeader() {
        navigationBlockInHeader().shouldBe(visible);
        linksAtTopCategoriesInHeader().get(5).click();
        return page(Moto_Category_page_Logic.class);
    }

    @Step("click on seventh TOP link in header .Moto_main_page")
    public Moto_Parent_Category_page_Logic clickOnSeventhTopLinkInHeader() {
        navigationBlockInHeader().shouldBe(visible);
        linksAtTopCategoriesInHeader().get(6).click();
        return page(Moto_Parent_Category_page_Logic.class);
    }

    @Step("click on eighth TOP link in header .Moto_main_page")
    public Moto_Parent_Category_page_Logic clickOnEighthTopLinkInHeader() {
        navigationBlockInHeader().shouldBe(visible);
        linksAtTopCategoriesInHeader().get(7).click();
        return page(Moto_Parent_Category_page_Logic.class);
    }

    @Step("click on ninth TOP link in header .Moto_main_page")
    public Moto_Parent_Category_page_Logic clickOnNinthTopLinkInHeader() {
        navigationBlockInHeader().shouldBe(visible);
        linksAtTopCategoriesInHeader().get(8).click();
        return page(Moto_Parent_Category_page_Logic.class);
    }

    @Step("presence Of TOP child categories block .Moto_main_page")
    public Moto_main_page_Logic presenceOfTopChildCategoriesBlock() {
        topChildCategoriesBlock().shouldBe(visible);
        headlineOfTopChildCategoriesBlock().shouldBe(visible);
        linksOfTopChildCategoriesBlock().shouldHaveSize(5);
        return this;
    }


    @Step("check child categories links .Moto_main_page")
    public Moto_main_page_Logic checkChildCategoriesLinks() throws SQLException {
        topChildCategoriesBlock().shouldBe(visible);
        clickOnLinkAtChildCategory(1);
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "moto_category3"));
        back();
        selectMotoInHorizontalMotoSelector("4081", "12008", "135713");
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "moto_catalog"));
        back();
        clickOnLinkAtChildCategoryWithMoto(1);
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "moto_category_car_list7"));
        return this;
    }

    @Step("click on link at child category .Moto_main_page")
    public Moto_Category_page_Logic clickOnLinkAtChildCategory(int position) {
        linksOfTopChildCategoriesBlock().get(position).shouldBe(visible).click();
        return page(Moto_Category_page_Logic.class);
    }

    @Step("click on link at child category with selected motorcycle .Moto_main_page")
    public Moto_Category_car_list_page_Logic clickOnLinkAtChildCategoryWithMoto(int position) {
        linksOfTopChildCategoriesBlock().get(position).shouldBe(visible).click();
        return page(Moto_Category_car_list_page_Logic.class);
    }
}
