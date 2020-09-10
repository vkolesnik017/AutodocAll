package ATD;

import Common.DataBase;
import io.qameta.allure.Step;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;

public class LKW_Categories_page_Logic extends LKW_Categories_page {

    @Step("Check successfully LKW_Categories page loading .LKW_Categories_page")
    public LKW_Categories_page_Logic checkSuccessfullyLKWCategoriesPageLoading() throws SQLException {
        verticalTruckSelectorInCloseCondition().shouldBe(visible);
        headlineInHeader().shouldBe(visible).shouldHave(exactText("LKW Ersatzteilkatalog"));
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE","lkw_categories"));
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

    @Step("get brand from TOP product title .LKW_Categories_page")
    public String getBrandFromTopProductTitle() {
        String titleOfBrand = titleOfTopProduct().get(3).getText().replace(titleOfTopProduct().get(3).getText().substring(titleOfTopProduct().get(3).getText().lastIndexOf(" ")), "").toLowerCase();
        return titleOfBrand;
    }

    @Step("transition to product page by click on top image of product .LKW_Categories_page")
    public LKW_Categories_page_Logic transitionToProductPageByClickOnTopImage(String brand) {
        clickOnImageOfTopProduct().checkSuccessfullyLKWProductPageLoading("autodoc.de/" + brand + "/");
        back();
        return this;
    }

    @Step("click on image of top product .LKW_Categories_page")
    public LKW_Product_page_Logic clickOnImageOfTopProduct() {
        imageOfTopProduct().get(3).click();
        return page(LKW_Product_page_Logic.class);
    }

    @Step("transition to product page by click on title of top product .LKW_Categories_page")
    public LKW_Categories_page_Logic transitionToProductPageByClickOnTitleOfTopProduct(String brand) {
        if (closeCookiesPopUp().isDisplayed()) {
            closeCookiesPopUp().click();
        }
        clickOnTitleOfTopProduct().checkSuccessfullyLKWProductPageLoading("autodoc.de/" + brand + "/");
        back();
        return this;
    }

    @Step("click on title of top product .LKW_Categories_page")
    public LKW_Product_page_Logic clickOnTitleOfTopProduct() {
        titleOfTopProduct().get(3).click();
        return page(LKW_Product_page_Logic.class);
    }

    @Step("transition to product page by click on link Details .LKW_Categories_page")
    public LKW_Categories_page_Logic transitionToProductPageByClickOnLinkDetails(String brand) {
        if (closeCookiesPopUp().isDisplayed()) {
            closeCookiesPopUp().click();
        }
        clickOnLinkDetails().checkSuccessfullyLKWProductPageLoading("autodoc.de/" + brand + "/");
        back();
        return this;
    }

    @Step("click on Details link .LKW_Categories_page")
    public LKW_Product_page_Logic clickOnLinkDetails() {
        imageOfTopProduct().get(3).scrollIntoView("{block: \"center\"}").hover();
        additionInfoBlockOfTopProduct().get(3).should(appear);
        linkDetails().get(3).click();
        return page(LKW_Product_page_Logic.class);
    }

    @Step("presence of Parent categories block .LKW_Categories_page")
    public LKW_Categories_page_Logic presenceOfParentCategoriesBlock(int countOfParentCategories) {
        parentCategories().shouldHave(sizeGreaterThan(countOfParentCategories));
        return this;
    }

    @Step("visibility Of Child categories popUp of Parent Category .LKW_Categories_page")
    public LKW_Categories_page_Logic checkParentCategoriesOfTecDocCatalog() {
        for (int i = 0; i < parentCategories().size(); i++) {
            if (titleOfParentCategories().get(i).getText().equals("Reifen")) {
                continue;
            }
            imageOfParentCategories().get(i).shouldBe(visible);
            titleOfParentCategories().get(i).shouldBe(visible);
            parentCategories().get(i).click();
            childCategoriesPopUpOfParentCategory().get(i).shouldBe(visible);
            checkFirstLevelOfParentCategories(i);
        }
        return this;
    }

    @Step("check First Level of parent categories .LKW_Categories_page")
    public LKW_Categories_page_Logic checkFirstLevelOfParentCategories(int position) {
        if (childCategoriesFirstLevel().get(0).isDisplayed() && visibleIntermediateCategoryFirstLevel().get(0).isDisplayed()) {
            childCategoriesFirstLevel().shouldHave(sizeGreaterThan(0));
            checkVisibleChildCategoriesFirstLevel();
            checkIntermediateChildCategoryFirstLevel(position);
        } else if (childCategoriesFirstLevel().get(0).isDisplayed()) {
            childCategoriesFirstLevel().shouldHave(sizeGreaterThan(0));
            checkVisibleChildCategoriesFirstLevel();
        } else if (visibleIntermediateCategoryFirstLevel().get(0).isDisplayed()) {
            checkIntermediateChildCategoryFirstLevel(position);
        }
        return this;
    }

    @Step("check visible child categories .LKW_Categories_page")
    public LKW_Categories_page_Logic checkVisibleChildCategoriesFirstLevel() {
        for (int i = 0; i < childCategoriesFirstLevel().size(); i++) {
            imageOfVisibleChildCategoriesFirstLevel().get(i).shouldBe(visible);
            titleOfVisibleChildCategoriesFirstLevel().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("check intermediate child category first level .LKW_Categories_page")
    public LKW_Categories_page_Logic checkIntermediateChildCategoryFirstLevel(int position) {
        for (int j = 0; j < intermediateChildCategoriesFirstLevel(position + 1).size(); j++) {
            intermediateChildCategoriesFirstLevel(position + 1).get(j).click();
            secondLevelBlock().should(appear);
            checkSecondLevelOfParentCategories();
        }
        return this;
    }

    @Step("check Second Level of parent categories .LKW_Categories_page")
    public LKW_Categories_page_Logic checkSecondLevelOfParentCategories() {

        if (visibleChildCategorySecondLevel().get(0).isDisplayed() && visibleIntermediateCategorySecondLevel().get(0).isDisplayed()) {
            visibleChildCategorySecondLevel().shouldHave(sizeGreaterThan(0));
            checkVisibleChildCategoriesSecondLevel();
            checkIntermediateChildCategorySecondLevel();
        } else if (visibleChildCategorySecondLevel().get(0).isDisplayed()) {
            visibleChildCategorySecondLevel().shouldHave(sizeGreaterThan(0));
            checkVisibleChildCategoriesSecondLevel();
        } else if (visibleIntermediateCategorySecondLevel().get(0).isDisplayed()) {
            checkIntermediateChildCategorySecondLevel();
        }
        return this;
    }

    @Step("check visible child categories .LKW_Categories_page")
    public LKW_Categories_page_Logic checkVisibleChildCategoriesSecondLevel() {
        for (int i = 0; i < visibleChildCategoriesSecondLevel().size(); i++) {
            imageOfVisibleChildCategoriesSecondLevel().get(i).shouldBe(visible);
            titleOfVisibleChildCategoriesSecondLevel().get(i).shouldBe(visible);

        }
        return this;
    }

    @Step("check intermediate child category second level .LKW_Categories_page")
    public LKW_Categories_page_Logic checkIntermediateChildCategorySecondLevel() {
        for (int j = 0; j < intermediateChildCategoriesSecondLevel().size(); j++) {
            intermediateChildCategoriesSecondLevel().get(j).click();
            thirdLevelBlock().should(appear);
            checkThirdLevelOfParentCategories();
        }
        return this;
    }

    @Step("check Second Level of parent categories .LKW_Categories_page")
    public LKW_Categories_page_Logic checkThirdLevelOfParentCategories() {
        childCategoriesThirdLevel().shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("transitionToChildCategory .LKW_Categories_page")
    public LKW_Category_page_Logic transitionToChildCategory(int parentCategoryPosition, int childCategoryPosition) {
        parentCategories().get(parentCategoryPosition).click();
        childCategoriesPopUpOfParentCategory().get(parentCategoryPosition).shouldBe(visible);
        childCategoriesFirstLevelForCheck().get(childCategoryPosition).shouldBe(visible).click();
        return page(LKW_Category_page_Logic.class);
    }

    @Step("presence of Oil category .LKW_Categories_page")
    public LKW_Categories_page_Logic presenceOfOilCategory() {
        imageOfParentCategories().get(0).shouldBe(visible);
        titleOfParentCategories().get(0).shouldBe(visible).shouldHave(exactText("Öle & Flüssigkeiten"));
        return this;
    }

    @Step("click on Oil parent category .LKW_Categories_page")
    public LKW_Categories_page_Logic clickOnOilParentCategory() {
        imageOfParentCategories().get(0).shouldBe(visible).click();
        popUpOfParentCategories().get(0).shouldBe(visible);
        visibleChildCategoriesPopUpOfParentCategory().shouldHaveSize(1);
        return this;
    }

    @Step("click on Oil child category .LKW_Categories_page")
    public LKW_Category_page_Logic clickOnOilChildCategory() {
        visibleChildCategoriesPopUpOfParentCategory().get(0).shouldBe(visible).click();
        return page(LKW_Category_page_Logic.class);
    }

    @Step("click on Garage icon in header. LKW_Categories_page")
    public LKW_Categories_page_Logic clickOnGarageIconInHeader() {
        headerGarageIcon().shouldBe(visible).click();
        popUpOfGarageInHeader().shouldBe(visible);
        return this;
    }

    @Step("click on Garage icon in header. LKW_Categories_page")
    public LKW_Categories_page_Logic selectVehicleInGaragePopUp(String idOfVehicle) {
        idOfVehicleInGaragePopUp(idOfVehicle).shouldBe(visible).click();
        return this;
    }

    @Step("check values in selector .LKW_Categories_page")
    public LKW_Categories_page_Logic checkValuesInSelector(String markeOfTruck, String modelOfTruck, String motorOfTruck) {
        verticalTruckSelectorInCloseCondition().click();
        verticalTruckSelectorInOpenCondition().shouldBe(visible);
        markeOfVerticalTruckSelector().shouldHave(value(markeOfTruck));
        modelOfVerticalTruckSelector().shouldHave(value(modelOfTruck));
        motorOfVerticalTruckSelector().shouldHave(value(motorOfTruck));
        return this;
    }

}
