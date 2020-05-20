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
}
