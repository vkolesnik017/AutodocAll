package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;

import static com.codeborne.selenide.Condition.exactValue;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Moto_main_page_Logic extends Moto_main_page {
    CommonMethods commonMethods = new CommonMethods();
    DataBase db = new DataBase();

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
        CommonMethods commonMethods = new CommonMethods();
        DataBase db = new DataBase();
        clickOnLeftLinkingBanner().checkCurrentUrl("index_instruments");
        back();
        clickOnRightLinkingBanner();
        commonMethods.checkingContainsUrl(db.getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", "moto_category2"));
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
        String urlFromBD = db.getRouteByRouteName("DE", subRoute);
        commonMethods.checkingContainsUrl(urlFromBD);
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
        commonMethods.checkingContainsUrl(db.getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", subRoute));
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
        commonMethods.checkingContainsUrl(db.getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", subRoute));
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
        commonMethods.checkingContainsUrl(db.getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", subRoute));
        back();
        return this;
    }

    @Step("check transition by click on image parent category with selected motorcycle .Moto_main_page")
    public Moto_Category_car_list_page_Logic clickOnChildCategoryWithMoto() {
        childCategoryLinks(1).get(0).shouldBe(visible).click();
        return page(Moto_Category_car_list_page_Logic.class);
    }
}
