package ATD;

import Common.DataBase;
import Common.Excel;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static Common.CommonMethods.checkingContainsUrl;
import static Common.CommonMethods.waitWhileRouteContainsExpected;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
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
        checkingContainsUrl(new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", "moto_category2"));
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
        btnResetOfSelector().shouldNotBe(visible);
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
        String urlFromBD = new DataBase("ATD").getRouteByRouteName("DE", subRoute);
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
        checkingContainsUrl(new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", subRoute));
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
        checkingContainsUrl(new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", subRoute));
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
        checkingContainsUrl(new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", subRoute));
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
    public Moto_Categories_maker_page_Logic selectTopMotoBrandFromBlock(int positionOfBrand) {
        topBrandsBlock().shouldBe(visible);
        clickOnTopMotoBrand(positionOfBrand);
        return page(Moto_Categories_maker_page_Logic.class);
    }

    @Step("click on TOP motorcycle brand .Moto_main_page")
    public Moto_main_page_Logic clickOnTopMotoBrand(int position) {
        listOfTopBrands().get(position).click();
        return this;
    }

    @Step("click on link More at TOP brands block .Moto_main_page")
    public Moto_makers_page_Logic clickOnLinkMoreAtTopBrandsBlock() {
        linkMoreAtTopBrandsBlock().scrollIntoView("{block: \"center\"}").click();
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
        DataBase db = new DataBase("ATD");
        linksAtTopCategoriesInHeader().get(0).shouldHave(attribute("href", "https://www." + db.getRouteByRouteName("DE", "main") + "/"));  //TODO  сделать метод, который будет возвращать только главный рут. параметры: envFromTest,shop,routeName - https://www.autodoc.de
        linksAtTopCategoriesInHeader().get(1).shouldHave(attribute("href", "https://" + db.getRouteByRouteName("DE", "lkw_main") + "/"));
        linksAtTopCategoriesInHeader().get(2).shouldHave(attribute("href", db.getFullRouteByRouteAndSubroute("prod", "DE", "main", "tyres4")));
        linksAtTopCategoriesInHeader().get(3).shouldHave(attribute("href", db.getFullRouteByRouteAndSubroute("prod", "DE", "main", "index_instruments")));
        linksAtTopCategoriesInHeader().get(4).shouldHave(attribute("href", db.getFullRouteByRouteAndSubroute("prod", "DE", "main", "index_accessories")));
        linksAtTopCategoriesInHeader().get(5).shouldHave(attribute("href", db.getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", "moto_category4")));
        linksAtTopCategoriesInHeader().get(6).shouldHave(attribute("href", db.getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", "moto_parent_category")));
        linksAtTopCategoriesInHeader().get(7).shouldHave(attribute("href", db.getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", "moto_parent_category2")));
        linksAtTopCategoriesInHeader().get(8).shouldHave(attribute("href", db.getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", "moto_parent_category3")));
        return this;
    }

    @Step("check navigation links in header .Moto_main_page")
    public Moto_main_page_Logic checkNavigationLinks() throws SQLException {
        clickOnFirstTopLinkInHeader();
        DataBase db = new DataBase("ATD");
        checkingContainsUrl(db.getRouteByRouteName("DE", "main"));
        back();
        clickOnSecondTopLinkInHeader();
        checkingContainsUrl(db.getRouteByRouteName("DE", "lkw_main"));
        back();
        clickOnThirdTopLinkInHeader();
        checkingContainsUrl(db.getRouteByRouteName("DE", "tyres4"));
        back();
        clickOnFourthTopLinkInHeader();
        checkingContainsUrl(db.getRouteByRouteName("DE", "index_instruments"));
        back();
        clickOnFifthTopLinkInHeader();
        checkingContainsUrl(db.getRouteByRouteName("DE", "index_accessories"));
        back();
        clickOnSixthTopLinkInHeader();
        checkingContainsUrl(db.getRouteByRouteName("DE", "moto_category4"));
        back();
        clickOnSeventhTopLinkInHeader();
        checkingContainsUrl(db.getRouteByRouteName("DE", "moto_parent_category"));
        back();
        clickOnEighthTopLinkInHeader();
        checkingContainsUrl(db.getRouteByRouteName("DE", "moto_parent_category2"));
        back();
        clickOnNinthTopLinkInHeader();
        checkingContainsUrl(db.getRouteByRouteName("DE", "moto_parent_category3"));
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
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "moto_category3"));
        back();
        selectMotoInHorizontalMotoSelector("4081", "12008", "135713");
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "moto_catalog"));
        back();
        clickOnLinkAtChildCategoryWithMoto(1);
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "moto_category_car_list7"));
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

    @Step("presence of TOP models block .Moto_main_page")
    public Moto_main_page_Logic presenceOfTopModelsBlock() {
        topModelsBlock().shouldBe(visible);
        headlineOfTopModelsBlock().shouldBe(visible);
        topMotoModels().shouldHaveSize(20);
        return this;
    }

    @Step("check TOP models block .Moto_main_page")
    public Moto_main_page_Logic checkTopModelsBlock() {
        visibleTopMotoModels().shouldHaveSize(5);
        scrollToRightOfTopModelsBlock();
        scrollToLeftOfTopModelsBlock();
        return this;
    }

    @Step("scroll to right of TOP models block .Moto_main_page")
    public Moto_main_page_Logic scrollToRightOfTopModelsBlock() {
        int countOfClickRight = 0;
        topModelsBlock().scrollTo();
        while (!notActiveLinkForwardOfTopModel().isDisplayed()) {
            for (int i = 0; i < titleOfVisibleTopModels().size(); i++) {
                titleOfVisibleTopModels().get(i).shouldBe(visible);
            }
            linkForwardOfTopModel().click();
            countOfClickRight++;
        }
        Assert.assertEquals(countOfClickRight, 3);
        linkBackOfTopModel().should(appear);
        return this;
    }

    @Step("scroll to left of TOP models block .Moto_main_page")
    public Moto_main_page_Logic scrollToLeftOfTopModelsBlock() {
        int countOfClickLeft = 0;
        while (!notActiveLinkBackOfTopModel().isDisplayed()) {
            for (int i = 0; i < titleOfVisibleTopModels().size(); i++) {
                titleOfVisibleTopModels().get(i).shouldBe(visible);
            }
            linkBackOfTopModel().click();
            countOfClickLeft++;
        }
        Assert.assertEquals(countOfClickLeft, 3);
        return this;
    }

    @Step("hover on TOP motorcycle models .Moto_main_page")
    public Moto_main_page_Logic hoverOnTopMotoModels() {
        String titleOfMotoModel;
        topModelsBlock().scrollTo();
        checkColorOfTopModels();
        while (activeLinkForwardOfTopModel().isDisplayed()) {
            titleOfMotoModel = titleOfVisibleTopModels().get(0).getText();
            activeLinkForwardOfTopModel().click();
            titleOfVisibleTopModels().get(0).shouldBe(visible).shouldNotHave(exactText(titleOfMotoModel));
            checkColorOfTopModels();
        }
        return this;
    }

    @Step("check color of TOP models block .Moto_main_page")
    public Moto_main_page_Logic checkColorOfTopModels() {
        for (int i = 0; i < titleTopModelsBlock().size(); i++) {
            titleTopModelsBlock().get(i).shouldBe(visible).shouldHave(cssValue("color", "rgba(0, 0, 0, 1)"));
            titleTopModelsBlock().get(i).shouldBe(visible).hover();
            titleTopModelsBlock().get(i).shouldBe(visible).shouldHave(cssValue("color", "rgba(255, 255, 255, 1)"));
        }
        return this;
    }

    @Step("select TOP motorcycle model from block .Moto_main_page")
    public Moto_Catalog_model_page_Logic selectTopMotoModelsFromBlock(int position) {
        topModelsBlock().shouldBe(visible);
        titleOfVisibleTopModels().get(position).click();
        return page(Moto_Catalog_model_page_Logic.class);
    }

    @Step("presence of TOP products block .Moto_main_page")
    public Moto_main_page_Logic presenceOfTopProductsBlock() {
        topProductsBlock().shouldBe(visible);
        return this;
    }

    @Step("presence of TOP products block .Moto_main_page")
    public Moto_main_page_Logic presenceOfTopProductsHeadline() {
        headlineOfTopProductsBlock().shouldBe(visible).shouldHave(text("Neue Motorradteile im Sortiment"));
        return this;
    }

    @Step("check count of TOP products .Moto_main_page")
    public Moto_main_page_Logic checkCountOfTopProducts() {
        topProducts().shouldHaveSize(12);
        return this;
    }

    @Step("get brand from TOP product title .Moto_main_page")
    public String getBrandFromTopProductTitle() {
        String titleOfBrand = titleOfTopProducts().get(0).getAttribute("url");
        return titleOfBrand;
    }

    @Step("Go to product page from TOP products block through Image, icon of brand, title in tecDoc listing .Moto_main_page")
    public Moto_main_page_Logic goToProductPageFromTopBlock(String brand) {
        clickOnImageOfTopProduct().checkUrlOfProductPage(brand);
        back();
        clickOnTitleOfTopProduct().checkUrlOfProductPage(brand);
        back();
        clickOnDetailsOfTopProduct().checkUrlOfProductPage(brand);
        return this;
    }

    @Step("click on image of TOP product .Moto_main_page")
    public Moto_Product_page_Logic clickOnImageOfTopProduct() {
        imageOfTopProducts().get(0).shouldBe(visible).click();
        return page(Moto_Product_page_Logic.class);
    }

    @Step("click on title of TOP product .Moto_main_page")
    public Moto_Product_page_Logic clickOnTitleOfTopProduct() {
        titleOfTopProducts().get(0).shouldBe(visible).click();
        return page(Moto_Product_page_Logic.class);
    }

    @Step("click on details of TOP product .Moto_main_page")
    public Moto_Product_page_Logic clickOnDetailsOfTopProduct() {
        titleOfTopProducts().get(0).shouldBe(visible).hover();
        detailsOfTopProductsBlock().get(0).shouldBe(visible);
        btnDetailsOfTopProducts().get(0).shouldBe(visible).click();
        return page(Moto_Product_page_Logic.class);
    }

    @Step("visibility of PopUp with addition information .Moto_main_page")
    public Moto_main_page_Logic visibilityOfPopUpWithAdditionInfo() {
        hoverOnTopProduct();
        if (linkForwardOfTopProductBlock().isDisplayed()) {
            linkForwardOfTopProductBlock().click();
            hoverOnTopProduct();
        }
        return this;
    }

    @Step("hover on TOP product .Moto_main_page")
    public Moto_main_page_Logic hoverOnTopProduct() {
        for (int i = 0; i < visibleTitleOfTopProducts().size(); i++) {
            visibleTitleOfTopProducts().get(i).shouldBe(visible).hover();
            visibleDetailsOfTopProductsBlock().get(0).shouldBe(visible);
            headlineOfTopProductBlock().hover();
        }
        return this;
    }

    @Step("presence of navigation arrows .Moto_main_page")
    public Moto_main_page_Logic presenceOfNavigationArrows() {
        String currentArtNumOfTopProduct = visibleArtNumOfTopProducts().get(0).getText();
        activeLinkForwardOfTopProductBlock().shouldBe(visible).click();
        visibleArtNumOfTopProducts().get(0).shouldNotHave(exactText(currentArtNumOfTopProduct));
        linkBackOfTopProductBlock().shouldBe(visible);
        return this;
    }

    @Step("visibility of Parent categories in vertical selector .Moto_main_page")
    public Moto_main_page_Logic visibilityParentCategoriesInVerticalSelector() {
        logoInHeader().shouldBe(visible);
        btnVerticalMotoCatalog().click();
        parentCategoriesBlockInVerticalCatalog().shouldBe(visible);
        return this;
    }

    @Step("check vertical catalog in header .Moto_main_page")
    public Moto_main_page_Logic checkVerticalCatalogInHeader() {
        headlineOfParentCategoriesVerticalCatalog().shouldBe(visible);
        imageOfParentCategoriesVerticalCatalog().shouldBe(visible);
        listOfParentCategoriesInVerticalCatalog().shouldHaveSize(17);
        checkSecondLevelOfVerticalCatalog();
        return this;
    }

    @Step("check second level of vertical catalog .Moto_main_page")
    public Moto_main_page_Logic checkSecondLevelOfVerticalCatalog() {
        for (int i = 0; i < listOfParentCategoriesInVerticalCatalog().size(); i++) {
            listOfParentCategoriesInVerticalCatalog().get(i).hover();
            verticalCatalogBlockSecondLevel().shouldBe(visible);
            titleOfVerticalCatalogBlockSecondLevel().get(0).shouldBe(visible).getText().replace(" ", "").equals(titleOfVerticalCatalogBlockSecondLevel().get(0).shouldBe(visible).getText().replace(" ", ""));
            imageOfVerticalCatalogBlockSecondLevel().get(0).shouldBe(visible);
            listOfCategoriesInVerticalCatalogSecondLevel().shouldHave(sizeGreaterThan(0));
            checkThirdLevelOfVerticalCatalog();
        }
        return this;
    }

    @Step("check third level of vertical catalog .Moto_main_page")
    public Moto_main_page_Logic checkThirdLevelOfVerticalCatalog() {
        if (intermediateCategoriesSecondLevel().get(0).isDisplayed()) {
            for (int i = 0; i < intermediateCategoriesSecondLevel().size(); i++) {
                intermediateCategoriesSecondLevel().get(i).hover();
                verticalCatalogBlockThirdLevel().shouldBe(visible);
                titleOfVerticalCatalogBlockThirdLevel().get(0).shouldBe(visible).shouldHave(exactText(intermediateCategoriesSecondLevel().get(i).getText()));
                childCategoriesThirdLevelAtVerticalCatalog().shouldHave(sizeGreaterThan(0));
                for (int j = 0; j < childCategoriesThirdLevelAtVerticalCatalog().size(); j++) {
                    titleOfChildCategoriesThirdLevelAtVerticalCatalog().get(j).shouldBe(visible);
                    imageOfChildCategoriesThirdLevelAtVerticalCatalog().get(j).shouldBe(visible);
                }
            }
        }
        return this;
    }

    @Step("select parent category in vertical catalog .Moto_main_page")
    public Moto_main_page_Logic selectParentCategoryInVerticalCatalog(int position) {
        listOfParentCategoriesInVerticalCatalog().get(position).shouldBe(visible).hover();
        verticalCatalogBlockSecondLevel().shouldBe(visible);
        return this;
    }

    @Step("select parent category in vertical catalog .Moto_main_page")
    public Moto_Category_page_Logic selectChildCategoryInVerticalCatalogSecondLevel(int position) {
        listOfCategoriesInVerticalCatalogSecondLevel().get(position).shouldBe(visible).click();
        return page(Moto_Category_page_Logic.class);
    }

    @Step("select parent category in vertical catalog .Moto_main_page")
    public Moto_Category_car_list_page_Logic selectChildCategoryInVerticalCatalogSecondLevelWithMoto(int position) {
        listOfCategoriesInVerticalCatalogSecondLevel().get(position).shouldBe(visible).click();
        return page(Moto_Category_car_list_page_Logic.class);
    }

    @Step("presence of main banner .Moto_main_page")
    public Moto_main_page_Logic presenceOfMainBanner() {
        mainBanner().shouldBe(visible);
        return this;
    }

    @Step("hover On TOP child categories .Moto_main_page")
    public Moto_main_page_Logic hoverOnTopChildCategories() {
        topChildCategoriesBlock().shouldBe(visible);
        for (int i = 0; i < titleOfTopChildCategories().size(); i++) {
            titleOfTopChildCategories().get(i).shouldBe(visible).shouldHave(cssValue("color", "rgba(0, 0, 0, 1)"));
            titleOfTopChildCategories().get(i).shouldBe(visible).hover();
            titleOfTopChildCategories().get(i).shouldBe(visible).shouldHave(cssValue("color", "rgba(255, 255, 255, 1)"));
        }
        return this;
    }

    @Step("click on Garage icon in header. Moto_main_page")
    public Moto_main_page_Logic clickOnGarageIconInHeader() {
        headerGarageIcon().shouldBe(visible).click();
        popUpOfGarageInHeader().shouldBe(visible);
        return this;
    }

    @Step("click on Garage icon in header. Moto_main_page")
    public Moto_main_page_Logic selectVehicleInGaragePopUp(String idOfVehicle) {
        idOfVehicleInGaragePopUp(idOfVehicle).shouldBe(visible).click();
        return this;
    }

    @Step("check expected values in selector. Moto_main_page")
    public Moto_main_page_Logic checkValuesInSelector(String marke, String model, String motor) {
        markeOfHorizontalMotoSelector().shouldHave(value(marke));
        modelOfHorizontalMotoSelector().shouldHave(value(model));
        motorOfHorizontalMotoSelector().shouldHave(value(motor));
        return this;
    }

    @Step("Login in header with mail {mail} and transition to profile plus page and go back. Moto_main_page")
    public Moto_main_page_Logic loginToProfilePlusPageAndBack(String email) {
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(email).visibilityOfUsersName();
        back();
        return this;
    }

    @Step("update of page. Moto_main_page")
    public Moto_main_page_Logic updateOfPage() {
        refresh();
        return this;
    }

    @Step("update of page. Moto_main_page")
    public Moto_main_page_Logic checkCountOfVehicleInIconOfGarage(String expectedCountOfVehicle) {
        countOfVehicleInIconOfGarageInHeader().shouldBe(visible).shouldHave(exactText(expectedCountOfVehicle));
        return this;
    }

    @Step("set Value In Main Search. Moto_main_page")
    public Moto_main_page_Logic setValueInMainSearch(List<String> expectedValues) {
        for (int i = 0; i < expectedValues.size(); i++) {
            String mainUrl = url();
            inputValueInMainSearchField(expectedValues.get(i));
            Assert.assertFalse(url().equals(mainUrl));
            waitWhileRouteContainsExpected("moto_search");
            back();
            Assert.assertTrue(mainUrl.equals(url()));
        }
        return this;
    }

    @Step("input of brand in main search field . Moto_main_page")
    public Search_page_Logic inputValueInMainSearchField(String titleOfBrand) {
        mainSearchField().setValue(titleOfBrand).pressEnter();
        return page(Search_page_Logic.class);
    }

    @Step("get TOP Moto Brands From Selector. Moto_main_page")
    public List<String> getTopMotoBrandsFromSelector() {
        markeOfHorizontalMotoSelector().shouldBe(visible);
        List<String> topMarke = topMarkeValuesFromSelector().stream().map(n -> n.getText()).collect(Collectors.toList());
        return topMarke;
    }

    @Step("presence TOP brands and models in selector. Moto_main_page")
    public Moto_main_page_Logic presenceTopBrandsAndModelsInSelector(List<String> topBrandsFront, String file, String language) {
        List<String> allMotoBrands = new Excel().readFromExcel(file, language, 6);
        List<String> topBrandsFile = allMotoBrands.stream().filter(title -> !title.equals("")).skip(1).limit(10).collect(Collectors.toList());
        Assert.assertEquals(topBrandsFront, topBrandsFile);
        markeOfHorizontalMotoSelector().selectOption(topBrandsFront.get(0));
        Wait().until(webDriver -> markeOfHorizontalMotoSelector().getSelectedText().equals(topBrandsFront.get(0)));
        checkVisibleTopBrands();
        modelFieldSelector().shouldHave(attribute("class", "slt-selectbox-wrapper moto-select__field filled active"));
        List<String> allMotoModels = new Excel().readFromExcel(file, language, 7);
        List<String> topModelsFile = allMotoModels.stream().filter(title -> !title.equals("")).skip(1).limit(10).map(title -> title.replaceAll("\\s\\(.+", "")).collect(Collectors.toList());
        List<String> topModelsFront = topModelValuesFromSelector().stream().map(n -> n.getText().replaceAll("\\s\\(.+", "")).collect(Collectors.toList());
        Assert.assertEquals(topModelsFront, topModelsFile);
        return this;
    }

    @Step("check Visible TOP brands. Moto_main_page")
    public Moto_main_page_Logic checkVisibleTopBrands() {
        for (int i = 0; i < listOfVisibleTopBrands().size(); i++) {
            listOfVisibleTopBrands().get(i).shouldBe(visible);
        }
        return this;
    }
}
