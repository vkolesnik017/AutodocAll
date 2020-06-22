package ATD;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LKW_Categories_maker_page_Logic extends LKW_Categories_maker_page {

    @Step("Checking of visibility bread crumbs block .LKW_Categories_maker_page")
    public LKW_Categories_maker_page_Logic checkingOfVisibilityBreadCrumbsBlock() {
        firstLinkBreadCrumbsBlock().shouldBe(visible);
        secondLinkBreadCrumbsBlock().shouldBe(visible);
        thirdLinkBreadCrumbsBlock().shouldBe(visible);
        return this;
    }

    @Step("Checking of visibility bread crumbs block .LKW_Categories_maker_page")
    public LKW_Categories_maker_page_Logic checkingImageAndTitleInFirstLinkOfBreadCrumbsBlock() {
        imageOfFirstLinkBreadCrumbs().shouldBe(visible);
        titleOfFirstLinkBreadCrumbs().shouldHave(exactText("MERCEDES-BENZ"));
        return this;
    }

    @Step("Checking of main icon of car brand .LKW_Categories_maker_page")
    public LKW_Categories_maker_page_Logic checkingMainIconOfCarBrand() {
        mainImageBlock().shouldBe(visible);
        iconOfCarInMainImageBlock();
        return this;
    }

    @Step("Checking of elements in parent and Child blocks .LKW_Categories_maker_page")
    public LKW_Categories_maker_page_Logic checkingOfElementsInParentAndChildBlocks() {
        titleOfTopParentCategoryBlock().shouldBe(visible);
        topCategoriesCatalog().shouldBe(visible);
        categoriesOfTopCategoriesCatalog().shouldHave(sizeNotEqual(0));
        checkingOfElementsInTopCategoryBlock();
        return this;
    }

    @Step("Checking of main icon of car brand .LKW_Categories_maker_page")
    public LKW_Categories_maker_page_Logic checkingOfElementsInTopCategoryBlock() {
        for (int i = 1; i <= categoriesOfTopCategoriesCatalog().size(); i++) {
            imageOfTopCategoryBlock(i).shouldBe(visible);
            titleOfTopCategoryBlock(i).shouldBe(visible);
            linksOfTopCategoryBlock(i).shouldHave(sizeNotEqual(0)).shouldHave(size(3));
        }
        return this;
    }


    @Step("Select child category in Top categories block .LKW_Categories_maker_page")
    public LKW_Category_maker_Logic selectChildCategoryInTopCategoryBlock() {
        topCategoriesCatalog().scrollTo();
        linksOfTopCategoryBlock(1).get(0).click();
        return page(LKW_Category_maker_Logic.class);
    }

    @Step("Checking of visibility seo text block .LKW_Categories_maker_page")
    public LKW_Categories_maker_page_Logic checkingOfVisibilitySeoTextBlock() {
        seoTextBlock().shouldBe(visible);
        return this;
    }

    @Step("Checking of visibility seo text block .LKW_Categories_maker_page")
    public LKW_Categories_maker_page_Logic checkingOfVisibilityLinkBlockTopBrands() {
        linkBlockTopBrand().shouldBe(visible);
        linksInTopBrandBlock().filter(visible).shouldHave(size(6));
        return this;
    }

    @Step("Select first link in TOP brands block .LKW_Categories_maker_page")
    public LKW_Categories_maker_page_Logic selectFirstLinkInTopBrandsBlock() {
        linksInTopBrandBlock().get(0).click();
        return this;
    }

    @Step("check successfully Categories maker page loading .LKW_Categories_maker_page ")
    public LKW_Categories_maker_page_Logic checkSuccessfullyCategoriesMakerPageLoading() {
        categoriesInSideBar().shouldBe(visible);
        checkUrlWithSelectingCar("https://lkwteile.autodoc.de/lastkraftwagen/man");
        return this;
    }

    @Step("Check current url with selecting car .LKW_Categories_maker_page")
    public LKW_Categories_maker_page_Logic checkUrlWithSelectingCar(String currentUrl) {
        Assert.assertEquals(url(), currentUrl);
        return this;
    }

    @Step("Select truck in vertical selector .LKW_makers_page .LKW_Categories_maker_page")
    public LKW_maker_car_list_Logic selectTruckInSelector(String markeOfTruck, String modelOfTruck, String motorOfTruck) {
        markeOfVerticalTruckSelector().selectOptionByValue(markeOfTruck);
        modelOfVerticalTruckSelector().selectOptionByValue(modelOfTruck);
        motorOfVerticalTruckSelector().selectOptionByValue(motorOfTruck);
        buttonSuchenOfVerticaltruckSelector().click();
        return page(LKW_maker_car_list_Logic.class);
    }

    @Step("availability of headline in TOP model block .LKW_Categories_maker_page")
    public LKW_Categories_maker_page_Logic availabilityOfHeadlineOfTopModelBlock(String partOfHeadline) {
        headlineOfTopModelBlockFirstLevel().shouldBe(visible).shouldHave(text(partOfHeadline));
        headlineOfTopModelBlockSecondLevel().shouldBe(visible).shouldHave(text(partOfHeadline));
        return this;
    }


    @Step("check of model count in model block .LKW_Categories_maker_page")
    public LKW_Categories_maker_page_Logic checkOfModelCountInModelBlock() {
        int countFromHeadLine = Integer.parseInt(headlineOfTopModelBlockFirstLevel().getText().replaceAll("[^0-9]", ""));
        Assert.assertEquals(modelsOfTruckBrandBlock().size(), countFromHeadLine);
        return this;
    }


    @Step("availability of model list block .LKW_Categories_maker_page")
    public LKW_Categories_maker_page_Logic availabilityOfModelListBlock() {
        modelListBlock().shouldBe(visible);
        return this;
    }


    @Step("check of elements in model list block .LKW_Categories_maker_page")
    public LKW_Categories_maker_page_Logic checkOfElementsInModelListBlock() {
        for (int i = 0; i < modelsInListBlock().size(); i++) {
            imageOfModelsInListBlock().get(i).shouldBe(visible);
            titleOfModelsInListBlock().get(i).shouldBe(visible);
            yearOfModelsInListBlock().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("select brand of car in vertical truck selector .LKW_Categories_maker_page")
    public LKW_Categories_maker_page_Logic selectBrandOfCarInVerticalSelector(String valueOfBrand) {
        markeInVerticalCarSelector().shouldBe(visible).selectOptionByValue(valueOfBrand);
        markeInVerticalCarSelector().shouldHave(value(valueOfBrand));
        return this;
    }

    @Step("reset of car brand field in vertical selector .LKW_Categories_maker_page")
    public LKW_Categories_page_Logic resetOfVerticalSelector() {
        resetBtnInVerticalCarSelector().click();
        return page(LKW_Categories_page_Logic.class);
    }

    @Step("check successfully child category page loading .LKW_Categories_maker_page")
    public LKW_Categories_maker_page_Logic checkSuccessfullyCategoriesMakerLoading(String currentUrl) {
        mainImageBlock().shouldBe(visible);
        Assert.assertEquals(url(), currentUrl);
        return this;
    }

    @Step("check transition at icon of truck model in TOP brands block .LKW_Categories_maker_page")
    public LKW_maker_car_list_Logic checkTransitionAtIconOfTruckModel() {
        modelOfTruckInTopBlock("ACTROS MP4").shouldBe(visible).click();
        return page(LKW_maker_car_list_Logic.class);
    }

    @Step("visibility of headline of TOP products block .LKW_Categories_maker_page")
    public LKW_Categories_maker_page_Logic visibilityOfHeadlineOfTopProductsBlock() {
        headlineOfTopProductsBlock().shouldBe(visible);
        return this;
    }

    @Step("availability of top list block and top products .LKW_Categories_maker_page")
    public LKW_Categories_maker_page_Logic availabilityOfTopProductsBlock() {
        topProductsBlock().shouldBe(visible);
        productsInTopBlock().shouldHaveSize(10);
        return this;
    }

    @Step("availability of forward and back links of TOP products block .LKW_Categories_maker_page")
    public LKW_Categories_maker_page_Logic availabilityOfForwardBackLinksOfTopBLock() {
        forwardLinkOfTopBLock().shouldBe(visible).click();
        productsInTopBlockSecondLevel().shouldBe(visible);
        backLinkOfTopBLock().shouldBe(visible).click();
        productsInTopBlockFirstLevel().shouldBe(visible);
        return this;
    }

    @Step("get id of product in Top products block .LKW_Categories_maker_page")
    public String getIdOfTopProduct() {
        topProductsBlock().shouldBe(visible).scrollTo();
        String idOfProduct = btnAddToBasketTopBLock().get(0).getAttribute("data-ga-action");
        return idOfProduct;
    }

    @Step("added top product to basket .LKW_Categories_maker_page")
    public Cart_page_Logic addTopProductToBasket() {
        btnAddToBasketTopBLock().get(0).click();
        basketDropMenu().should(appear);
        basketDropMenu().should(disappear);
        basket().click();
        return page(Cart_page_Logic.class);
    }

    @Step("visibility of addition information when hover on the product in Top block .LKW_Categories_maker_page")
    public LKW_Categories_maker_page_Logic visibilityOfAdditionInfoInTopBlock() {
        topProductsBlock().scrollIntoView("{block: \"end\"}");
        for (int i = 0; i < productsInTopBlock().size(); i++) {
            headlineOfTopProductsBlock().hover();
            productsInTopBlock().get(i).hover();
            additionInfoBlockOfTopProduct().get(0).should(appear);
            if (i == 4) {
                forwardLinkOfTopBLock().click();
            }
        }
        return this;
    }


    @Step("transition to product page by click on top image of product .LKW_Categories_maker_page")
    public LKW_Categories_maker_page_Logic transitionToProductPageByClickOnTopImage() {
        clickOnImageOfTopProduct().checkSuccessfullyLKWProductPageLoading("autodoc.de/dt/");
        back();
        return this;
    }

    @Step("click on image of top product .LKW_Categories_maker_page")
    public LKW_Product_page_Logic clickOnImageOfTopProduct() {
        imageOfTopProduct().get(0).click();
        return page(LKW_Product_page_Logic.class);
    }

    @Step("transition to product page by click on title of top product .LKW_Categories_maker_page")
    public LKW_Categories_maker_page_Logic transitionToProductPageByClickOnTitleOfTopProduct() {
        if (closeCookiesPopUp().isDisplayed()) {
            closeCookiesPopUp().click();
        }
        clickOnTitleOfTopProduct().checkSuccessfullyLKWProductPageLoading("autodoc.de/dt/");
        back();
        return this;
    }

    @Step("click on title of top product .LKW_Categories_maker_page")
    public LKW_Product_page_Logic clickOnTitleOfTopProduct() {
        titleOfTopProduct().get(0).click();
        return page(LKW_Product_page_Logic.class);
    }

    @Step("transition to product page by click on link Details .LKW_Categories_maker_page")
    public LKW_Categories_maker_page_Logic transitionToProductPageByClickOnLinkDetails() {
        if (closeCookiesPopUp().isDisplayed()) {
            closeCookiesPopUp().click();
        }
        clickOnLinkDetails().checkSuccessfullyLKWProductPageLoading("autodoc.de/dt/");
        back();
        return this;
    }

    @Step("click on Details link .LKW_Categories_maker_page")
    public LKW_Product_page_Logic clickOnLinkDetails() {
        imageOfTopProduct().get(0).scrollIntoView("{block: \"center\"}").hover();
        additionInfoBlockOfTopProduct().get(0).should(appear);
        linkDetails().get(0).click();
        return page(LKW_Product_page_Logic.class);
    }

    @Step("applicability of top product to truck on Product page .LKW_Categories_maker_page")
    public LKW_Categories_maker_page_Logic applicabilityOfTopProductToTruck(String selectedBrand) {
        for (int i = 0; i < topProductsFirstLevel().size(); i++) {
            clickOnTopProduct(topProductsFirstLevel(), i).matchingProductToSelectedTruck(selectedBrand);
            back();
        }
        return this;
    }

    @Step("applicability of top product to truck on Product page .LKW_Categories_maker_page")
    public LKW_Product_page_Logic clickOnTopProduct(ElementsCollection topProduct, int position) {
        topProduct.get(position).click();
        return page(LKW_Product_page_Logic.class);
    }

    @Step("get selected brand of truck from selector .LKW_Categories_maker_page")
    public String getSelectedTruck() {
        String brandOfTruck = markeInVerticalCarSelector().getText();
        return brandOfTruck;
    }


    @Step("visibility of main headline .LKW_Categories_maker_page")
    public LKW_Categories_maker_page_Logic visibilityOfMainHeadline() {
        mainHeadline().shouldBe(visible).shouldHave(text("für MERCEDES-BENZ"));
        return this;
    }
}
