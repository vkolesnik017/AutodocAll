package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LKW_Categories_page_Logic extends LKW_Categories_page {

    @Step("Check successfully LKW_Categories page loading .LKW_Categories_page")
    public LKW_Categories_page_Logic checkSuccessfullyLKWCategoriesPageLoading() {
        verticalTruckSelectorInCloseCondition().shouldBe(visible);
        headlineInHeader().shouldBe(visible).shouldHave(exactText("LKW Ersatzteilkatalog"));
        Assert.assertTrue(url().contains("https://lkwteile.autodoc.de/ersatzteile"));
        return this;
    }

    @Step("visibility of headline .LKW_Categories_page")
    public LKW_Categories_page_Logic visibilityOfHeadLine() {
        headlineInHeader().shouldBe(visible);
        titleOfCatalog().shouldBe(visible);
        return this;
    }

    @Step("Select truck in vertical selector .LKW_Categories_page")
    public LKW_maker_car_list_Logic selectTruckInSelector(String markeOfTruck, String modelOfTruck, String motorOfTruck) {
        verticalTruckSelectorInCloseCondition().click();
        verticalTruckSelectorInOpenCondition().shouldBe(visible);
        markeOfVerticalTruckSelector().selectOptionByValue(markeOfTruck);
        modelOfVerticalTruckSelector().selectOptionByValue(modelOfTruck);
        motorOfVerticalTruckSelector().selectOptionByValue(motorOfTruck);
        buttonSuchenOfVerticaltruckSelector().click();
        verticalTruckSelectorInOpenCondition().should(disappear);
        return page(LKW_maker_car_list_Logic.class);
    }

    @Step("select brand of car in vertical truck selector .LKW_Categories_page")
    public LKW_Categories_page_Logic selectBrandOfCarInVerticalSelector(String valueOfBrand) {
        verticalTruckSelectorInCloseCondition().shouldBe(visible).click();
        markeOfVerticalTruckSelector().shouldBe(visible).selectOptionByValue(valueOfBrand);
        markeOfVerticalTruckSelector().shouldHave(value(valueOfBrand));
        return this;
    }

    @Step("reset of car brand field in vertical selector .LKW_Categories_page")
    public LKW_Categories_page_Logic resetOfCarBrandFieldInVerticalSelector() {
        resetBtnInVerticalCarSelectorInOpenCondition().shouldBe(visible).click();
        return this;
    }

    @Step("visibility of headline of TOP products block .LKW_Categories_page")
    public LKW_Categories_page_Logic visibilityOfHeadlineOfTopProductsBlock() {
        headlineOfTopProductsBlock().shouldBe(visible);
        return this;
    }


    @Step("availability of top block and top products .LKW_Categories_page")
    public LKW_Categories_page_Logic availabilityOfTopProductsBlock() {
        topBLock().shouldBe(visible);
        productsOfTopBlock().shouldHaveSize(6);
        return this;
    }

    @Step("visibility of addition information when hover on the product in Top block .LKW_Categories_page")
    public LKW_Categories_page_Logic visibilityOfAdditionInfoInTopBlock() {
        topProductsBlock().scrollIntoView("{block: \"end\"}");
        for (int i = 0; i < productsOfTopBlock().size(); i++) {
            headlineOfTopProductsBlock().hover();
            productsOfTopBlock().get(i).hover();
            additionInfoBlockOfTopProduct().get(i).should(appear);
        }
        return this;
    }


    @Step("transition to product page by click on top image of product .LKW_Categories_page")
    public LKW_Categories_page_Logic transitionToProductPageByClickOnTopImage() {
        clickOnImageOfTopProduct().checkSuccessfullyLKWProductPageLoading("https://www.autodoc.de/brembo/");
        back();
        return this;
    }

    @Step("click on image of top product .LKW_Categories_page")
    public LKW_Product_page_Logic clickOnImageOfTopProduct() {
        imageOfTopProduct().get(0).click();
        return page(LKW_Product_page_Logic.class);
    }

    @Step("transition to product page by click on title of top product .LKW_Categories_page")
    public LKW_Categories_page_Logic transitionToProductPageByClickOnTitleOfTopProduct() {
        if (closeCookiesPopUp().isDisplayed()) {
            closeCookiesPopUp().click();
        }
        clickOnTitleOfTopProduct().checkSuccessfullyLKWProductPageLoading("https://www.autodoc.de/brembo/");
        back();
        return this;
    }

    @Step("click on title of top product .LKW_Categories_page")
    public LKW_Product_page_Logic clickOnTitleOfTopProduct() {
        titleOfTopProduct().get(0).click();
        return page(LKW_Product_page_Logic.class);
    }

    @Step("transition to product page by click on link Details .LKW_Categories_page")
    public LKW_Categories_page_Logic transitionToProductPageByClickOnLinkDetails() {
        if (closeCookiesPopUp().isDisplayed()) {
            closeCookiesPopUp().click();
        }
        clickOnLinkDetails().checkSuccessfullyLKWProductPageLoading("https://www.autodoc.de/brembo/");
        back();
        return this;
    }

    @Step("click on Details link .LKW_Categories_page")
    public LKW_Product_page_Logic clickOnLinkDetails() {
        imageOfTopProduct().get(0).scrollIntoView("{block: \"center\"}").hover();
        additionInfoBlockOfTopProduct().get(0).should(appear);
        linkDetails().get(0).click();
        return page(LKW_Product_page_Logic.class);
    }
}
