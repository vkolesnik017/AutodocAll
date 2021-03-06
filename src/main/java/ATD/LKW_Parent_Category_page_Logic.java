package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LKW_Parent_Category_page_Logic extends LKW_Parent_Category_page {

    @Step("Check successfully LKW_Categories page loading .LKW_Parent_Category_page")
    public LKW_Parent_Category_page_Logic checkSuccessfullyLKWParentCategoryPageLoading(String currentUrl) {
        childCategoryBlock().shouldBe(visible);
        Assert.assertTrue(url().contains(currentUrl));
        return this;
    }

    @Step("Check links in bread crumbs block .LKW_Parent_Category_page")
    public LKW_Parent_Category_page_Logic checkLinksInBreadCrumbsBlock() throws SQLException {
        breadCrumbsLinks().shouldHave(size(4));
        firstLinkOfBreadCrumbsBlock().shouldBe(visible);
        secondLinkOfBreadCrumbsBlock().shouldHave(exactText("Filter")).shouldNotHave(attribute("href"));
        thirdLinkOfBreadCrumbsBlock().shouldHave(exactText("Hersteller auswählen")).shouldNotHave(attribute("href"));
        fourthLinkOfBreadCrumbsBlock().shouldHave(exactText("Modell auswählen")).shouldNotHave(attribute("href"));
        closeToolTipPopUpIfVisible();
        firstLinkClick().checkSuccessfullyLKWCategoriesPageLoading();
        return this;
    }

    @Step("Click on first link in bread crumbs block .LKW_Parent_Category_page")
    public LKW_Categories_page_Logic firstLinkClick() {
        firstLinkOfBreadCrumbsBlock().click();
        return page(LKW_Categories_page_Logic.class);
    }

    @Step("Close tooltip text pop-up in vertical selector .LKW_Parent_Category_page")
    public LKW_Parent_Category_page_Logic closeToolTipPopUpIfVisible() {
        if (closeToolTipTextSelector().isDisplayed()) {
            closeToolTipTextSelector().click();
        }
        return this;
    }

    @Step("presence of a Catalog block in the sidebar .LKW_Parent_Category_page")
    public LKW_Parent_Category_page_Logic presenceOfCatalogBlockInSidebar() {
        catalogBlockInSideBar().shouldBe(visible);
        return this;
    }

    @Step("presence of all parent categories in the sidebar .LKW_Parent_Category_page")
    public LKW_Parent_Category_page_Logic presenceOfAllParentCategoriesInSideBar() {
        catalogBlockInSideBar().shouldBe(exist).scrollIntoView("{block: \"center\"}").click();
        categoriesInParentCatalogInSideBar().shouldHaveSize(32);
        return this;
    }

    @Step("go to child category from sidebar .LKW_Parent_Category_page")
    public LKW_Parent_Category_page_Logic goToParentCategoryPage(String parentCategory) {
        parentCategoryInSideBar(parentCategory).scrollIntoView("{block: \"center\"}").click();
        return this;
    }

    @Step("Presence of elements in Child categories block in sidebar .LKW_Parent_Category_page")
    public LKW_Parent_Category_page_Logic presenceOfElementsChildCategoriesBlockInSideBar() {
        titleOfChildCategoriesBlockInSideBar().shouldBe(visible);
        childCategoryBlockInSideBar().shouldBe(visible);
        linksOfChildCategoriesBlockInSideBar().shouldHave(sizeNotEqual(0));
        return this;
    }

    @Step("check of main elements in Child categories block .LKW_Parent_Category_page")
    public LKW_Parent_Category_page_Logic checkOfMainElementsInChildCategoriesBlock() {
        titleOfChildCategoriesBlock().shouldBe(visible).shouldHave(exactText("Wählen Sie die benötigten Filter Ersatzteile aus"));
        mainImageOfChildCategoriesBlock().shouldBe(visible);
        for (int i = 0; i < childCategoriesInChildCategoryBlock().size(); i++) {
            titleOfLinksInChildCategoriesBlock().get(i).shouldBe(visible);
            imageOfLinksInChildCategoriesBlock().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("select child category in Child Categories block .LKW_Parent_Category_page")
    public LKW_Category_page_Logic selectChildCategoryInHeader(String titleOfChildCategory) {
        for (int i = 0; i < titleOfLinksInChildCategoriesBlock().size(); i++) {
            titleOfLinksInChildCategoriesBlock().get(i).shouldHave(exactText(titleOfChildCategory)).click();
        }

        return page(LKW_Category_page_Logic.class);
    }

    @Step("visibility of headline   .LKW_Parent_Category_page")
    public LKW_Parent_Category_page_Logic visibilityOfHeadLine() {
        headlineInHeader().shouldBe(visible);
        return this;
    }

    @Step("visibility of headline of selector and icon of truck  .LKW_Parent_Category_page")
    public LKW_Parent_Category_page_Logic visibilityOfHeadLineSelectorAndIconOfTruck(String selectTruck) {
        iconOfTruckInHeadlineOfSelector().shouldBe(visible);
        titleOfTruckInHeadlineOfSelector().shouldHave(exactText(selectTruck));
        return this;
    }

    @Step("Select truck in vertical selector .LKW_Parent_Category_page")
    public LKW_maker_car_list_Logic selectTruckInSelector(String markeOfTruck, String modelOfTruck, String motorOfTruck) {
        markeOfVerticalTruckSelector().selectOptionByValue(markeOfTruck);
        modelOfVerticalTruckSelector().selectOptionByValue(modelOfTruck);
        motorOfVerticalTruckSelector().selectOptionByValue(motorOfTruck);
        buttonSuchenOfVerticaltruckSelector().click();
        return page(LKW_maker_car_list_Logic.class);
    }


    @Step("visibility of headline of TOP products block .LKW_Parent_Category_page")
    public LKW_Parent_Category_page_Logic visibilityOfHeadlineOfTopProductsBlock() {
        headlineOfTopProductsBlock().shouldBe(visible);
        textForHeadlineOfTopProductsBlock().shouldBe(visible);
        return this;
    }

    @Step("availability of top list block and top products .LKW_Parent_Category_page")
    public LKW_Parent_Category_page_Logic availabilityOfTopProductsBlock() {
        topListBlock().shouldBe(visible);
        productsInTopListBlock().shouldHaveSize(10);
        return this;
    }

    @Step("get id of product in Top products block .LKW_Parent_Category_page")
    public String getIdOfTopProduct() {
        topListBlock().shouldBe(visible);
        String idOfProduct = btnAddToBasketTopBLock(1).getAttribute("id");
        return idOfProduct;
    }

    @Step("added top product to basket .LKW_Parent_Category_page")
    public Cart_page_Logic addTopProductToBasket() {
        btnAddToBasketTopBLock(1).shouldBe(visible).click();
        basketDropMenu().should(appear);
        basketDropMenu().should(disappear);
        basket().click();
        return page(Cart_page_Logic.class);
    }

    @Step("transition to product page by click on top image of product .LKW_Parent_Category_page")
    public LKW_Parent_Category_page_Logic transitionToProductPageByClickOnTopImage(String url) {
        clickOnImageOfTopProduct().checkSuccessfullyLKWProductPageLoading(url);
        back();
        return this;
    }

    @Step("click on image of top product .LKW_Parent_Category_page")
    public LKW_Product_page_Logic clickOnImageOfTopProduct() {
        imageOfTopProduct().get(0).click();
        return page(LKW_Product_page_Logic.class);
    }

    @Step("transition to product page by click on title of top product .LKW_Parent_Category_page")
    public LKW_Parent_Category_page_Logic transitionToProductPageByClickOnTitleOfTopProduct(String url) {
        clickOnTitleOfTopProduct().checkSuccessfullyLKWProductPageLoading(url);
        back();
        return this;
    }

    @Step("click on title of top product .LKW_Parent_Category_page")
    public LKW_Product_page_Logic clickOnTitleOfTopProduct() {
        titleOfTopProduct().get(0).click();
        return page(LKW_Product_page_Logic.class);
    }

    @Step("transition to product page by click on link Details .LKW_Parent_Category_page")
    public LKW_Parent_Category_page_Logic transitionToProductPageByClickOnLinkDetails(String url) {
        if (closeCookiesPopUp().isDisplayed()) {
            closeCookiesPopUp().click();
        }
        clickOnLinkDetails().checkSuccessfullyLKWProductPageLoading(url);
        back();
        return this;
    }

    @Step("click on Details link .LKW_Parent_Category_page")
    public LKW_Product_page_Logic clickOnLinkDetails() {
        linkDetails().get(0).click();
        return page(LKW_Product_page_Logic.class);
    }

    @Step("comparison of product and generic .LKW_Parent_Category_page")
    public LKW_Parent_Category_page_Logic comparisonOfProductAndGeneric(String titleOfGeneric) {
        for (int i = 0; i < titleOfTopProduct().size(); i++) {
            titleOfTopProduct().get(i).shouldHave(text(titleOfGeneric));
        }
        return this;
    }

    @Step("get Url of TOP product from his title. LKW_Parent_Category_page")
    public String getUrlOfTopProductFromHisTitle(int positionOfProduct) {
        String urlOfProduct = titleOfTopProduct().get(positionOfProduct).shouldBe(visible).getAttribute("href");
        return urlOfProduct;
    }

    @Step("click On child category .LKW_Parent_Category_page")
    public LKW_Parent_Category_page_Logic clickOnChildCategory(int positionOfChildCategory) {
        linksOfChildCategoriesBlockInSideBar().get(positionOfChildCategory).scrollIntoView("{block: \"center\"}").click();
        return this;
    }

    @Step("check correctness displaying child category in sidebar.LKW_Parent_Category_page")
    public LKW_Parent_Category_page_Logic checkCorrectnessDisplayingChildCategory() {
        List<String> expectedChildCategories = Arrays.asList("Riementrieb / Zahnriemen", "Kraftstoffförderanlage", "Kraftstoffaufbereitung", "Abgasanlage", "Kühlung", "Kupplung");
        List<String> titleOfChildCategoriesFromSideBar = titleOfChildCategoriesInSideBar().stream().map(n -> n.getText()).collect(Collectors.toList());
        Assert.assertEquals(titleOfChildCategoriesFromSideBar, expectedChildCategories);
        return this;
    }
}
