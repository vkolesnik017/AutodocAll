package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
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
}
