package ATD;

import Common.DataBase;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Common.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class Moto_Categories_page_Logic extends Moto_Categories_page {


    @Step(" Select brand of motorcycle .Moto_Categories_page")
    public Moto_Categories_page_Logic selectBrandOfMoto(String markeOfMoto) {
        selectorInCloseCondition().shouldBe(visible).click();
        mainFormOfSelector().shouldBe(visible);
        brandOfMotoField().selectOptionByValue(markeOfMoto);
        return this;
    }

    @Step(" reset of motorcycle selector .Moto_Categories_page")
    public Moto_Categories_page_Logic resetOfMotoSelector() {
        btnResetOfSelector().shouldBe(visible).click();
        return this;
    }


    @Step(" reset of motorcycle selector .Moto_Categories_page")
    public Moto_Categories_page_Logic presenceOfEmptyValuesInSelector() {
        selectorInCloseCondition().shouldBe(visible).click();
        mainFormOfSelector().shouldBe(visible);
        brandOfMotoField().shouldHave(exactValue("0"));
        modelFiledInSelector().shouldHave(exactValue("0"));
        motorFiledInSelector().shouldHave(exactValue("0"));
        return this;
    }

    @Step(" check current url  .Moto_Categories_page")
    public Moto_Categories_page_Logic checkCurrentUrl(String subRoute) throws SQLException {
        checkingContainsUrl(new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", subRoute));
        return this;
    }


    @Step("presence of main Headline block .Moto_Categories_page")
    public Moto_Categories_page_Logic presenceOfMainHeadlineBlock() {
        mainHeadline().shouldBe(visible);
        return this;
    }

    @Step("check components of model block .Moto_makers_page")
    public Moto_Categories_page_Logic checkComponentsOfBrandsBlock() {
        brandsBlock().shouldBe(visible);
        for (int i = 0; i < linksOfBrands().size(); i++) {
            imageOfMotoBrands().get(i).shouldBe(visible);
            titleOfMotoBrands().get(i).shouldBe(visible);
            linksOfBrands().get(i).shouldHave(attribute("href"));
        }
        return this;
    }

    @Step("presence of brands block title .Moto_Categories_page")
    public Moto_Categories_page_Logic presenceOfBrandsTitle(String title) {
        brandsTitle().shouldBe(visible).shouldHave(text(title));
        return this;
    }


    @Step("presence of Motomakers block .Moto_Categories_page")
    public Moto_Categories_page_Logic presenceOfMotomakersBlock() {
        brandsBlock().shouldBe(visible);
        return this;
    }


    @Step("click om Motorcycle brand .Moto_Categories_page")
    public Moto_Categories_maker_page_Logic clickOnMotoBrands(int position) {
        linksOfBrands().get(position).click();
        return page(Moto_Categories_maker_page_Logic.class);
    }

    @Step("presence of TecDoc catalog .Moto_Categories_page")
    public Moto_Categories_page_Logic presenceOfTecDocCatalog() {
        tecDocCatalog().shouldBe(visible);
        parentsCategoriesOfTecDocCatalog().shouldHaveSize(17);
        return this;
    }

    @Step("check parent categories of TecDoc catalog .Moto_Categories_page")
    public Moto_Categories_page_Logic checkParentCategoriesOfTecDocCatalog() {
        tecDocCatalog().shouldBe(visible);
        for (int i = 0; i < parentsCategoriesOfTecDocCatalog().size(); i++) {
            parentsCategoriesOfTecDocCatalog().get(i).click();
            childCategoriesFirstLevelBlock().get(i).shouldBe(visible);
            checkFirstLevelOfParentCategories(i);
        }
        return this;
    }


    @Step("check First Level of parent categories .Moto_Categories_page")
    public Moto_Categories_page_Logic checkFirstLevelOfParentCategories(int position) {
        if (visibleChildCategory().get(0).isDisplayed() && visibleIntermediateCategory().get(0).isDisplayed()) {
            visibleChildCategory().shouldHave(sizeGreaterThan(0));
            checkIntermediateChildCategoryFirstLevel(position);
        } else if (visibleChildCategory().get(0).isDisplayed()) {
            visibleChildCategory().shouldHave(sizeGreaterThan(0));
        } else if (visibleIntermediateCategory().get(0).isDisplayed()) {
            checkIntermediateChildCategoryFirstLevel(position);
        }
        return this;
    }

    @Step("check intermediate child category first level .Moto_Categories_page")
    public Moto_Categories_page_Logic checkIntermediateChildCategoryFirstLevel(int position) {
        for (int j = 0; j < intermediateChildCategoriesFirstLevel(position + 1).size(); j++) {
            intermediateChildCategoriesFirstLevel(position + 1).get(j).click();
            secondLevelBlock().should(appear);
            checkSecondLevelOfParentCategories(position);
        }
        return this;
    }

    @Step("check Second Level of parent categories .Moto_Categories_page")
    public Moto_Categories_page_Logic checkSecondLevelOfParentCategories(int position) {
        childCategoriesSecondLevel(position + 1).shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("check images of parents and child categories .Moto_Categories_page")
    public Moto_Categories_page_Logic checkImagesOfParentAndChildCategories() {
        for (int i = 0; i < parentsCategoriesOfTecDocCatalog().size(); i++) {
            imageOfParentCategories().get(i).shouldBe(visible);
            parentsCategoriesOfTecDocCatalog().get(i).click();
            childCategoriesFirstLevelBlock().get(i).shouldBe(visible);
            checkImagesOfChildCategoriesFirstLevel(i);
        }
        return this;
    }


    @Step("check images of child categories first level .Moto_Categories_page")
    public Moto_Categories_page_Logic checkImagesOfChildCategoriesFirstLevel(int position) {
        if (visibleChildCategory().get(0).isDisplayed() && visibleIntermediateCategory().get(0).isDisplayed()) {
            visibilityOfChildCategoriesImages(position);
            checkIntermediateChildCategoriesImagesFirstLevel(position);
        } else if (visibleChildCategory().get(0).isDisplayed()) {
            visibleChildCategory().shouldHave(sizeGreaterThan(0));
            visibilityOfChildCategoriesImages(position);
        } else if (visibleIntermediateCategory().get(0).isDisplayed()) {
            checkIntermediateChildCategoriesImagesFirstLevel(position);
        }
        return this;
    }

    @Step("visibility of child categories images .Moto_Categories_page")
    public Moto_Categories_page_Logic visibilityOfChildCategoriesImages(int position) {
        for (int j = 0; j < childCategoriesFirstLevel(position + 1).size(); j++) {
            imageOfChildCategoriesFirstLevel(position + 1).get(j).shouldBe(visible);
        }
        return this;
    }

    @Step("check intermediate child categories images first level .Moto_Categories_page")
    public Moto_Categories_page_Logic checkIntermediateChildCategoriesImagesFirstLevel(int position) {
        for (int j = 0; j < intermediateChildCategoriesFirstLevel(position + 1).size(); j++) {
            intermediateChildCategoriesFirstLevel(position + 1).get(j).click();
                      secondLevelBlock().should(appear);
            checkImagesOfChildCategoriesSecondLevel(position);
        }
        return this;
    }

    @Step("check images of child categories second level .Moto_Categories_page")
    public Moto_Categories_page_Logic checkImagesOfChildCategoriesSecondLevel(int position) {
        for (int i = 0; i < childCategoriesSecondLevel(position).size(); i++) {
            imageOfChildCategoriesSecondLevel(position).get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("check sorting of child categories .Moto_Categories_page")
    public Moto_Categories_page_Logic checkSortingOfChildCategories() {
        for (int i = 0; i < parentsCategoriesOfTecDocCatalog().size(); i++) {
            parentsCategoriesOfTecDocCatalog().get(i).click();
            childCategoriesFirstLevelBlock().get(i).shouldBe(visible);
            checkSortingOfChildCategoriesFirstLevel(i);
        }
        return this;
    }

    @Step("check sorting of child categories first level .Moto_Categories_page")
    public Moto_Categories_page_Logic checkSortingOfChildCategoriesFirstLevel(int position) {

        childCategoriesFirstLevelBlock().get(position).shouldBe(visible);
        if (childCategoriesFirstLevel(position + 1).get(0).isDisplayed() && intermediateChildCategoriesFirstLevel(position + 1).get(0).isDisplayed()) {
            sortingOfChildCategories(childCategoriesFirstLevel(position + 1));
            checkSortingIntermediateChildCategoriesFirstLevel(position);
        } else if (childCategoriesFirstLevel(position + 1).get(0).isDisplayed()) {
            sortingOfChildCategories(childCategoriesFirstLevel(position + 1));
        } else if (intermediateChildCategoriesFirstLevel(position + 1).get(0).isDisplayed()) {
            checkSortingIntermediateChildCategoriesFirstLevel(position);
        }
        return this;
    }

    @Step("sorting of child categories first level .Moto_Categories_page")
    public Moto_Categories_page_Logic sortingOfChildCategories(ElementsCollection list) {
        List<String> titleOfChildCategories = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            titleOfChildCategories.add(list.get(i).shouldBe(visible).getText());
        }
        List<String> checkingList = new ArrayList<>(titleOfChildCategories);
        Collections.sort(checkingList);
        Assert.assertEquals(checkingList, titleOfChildCategories);
        titleOfChildCategories.clear();
        checkingList.clear();
        return this;
    }

    @Step("check sorting intermediate child categories first level .Moto_Categories_page")
    public Moto_Categories_page_Logic checkSortingIntermediateChildCategoriesFirstLevel(int position) {
        for (int j = 0; j < intermediateChildCategoriesFirstLevel(position + 1).size(); j++) {
            intermediateChildCategoriesFirstLevel(position + 1).get(j).shouldBe(visible).click();
            secondLevelBlock().should(appear);
            sortingOfChildCategories(childCategoriesSecondLevel(position + 1));
        }
        return this;
    }

    @Step("click on child category .Moto_Categories_page")
    public Moto_Category_page_Logic clickOnChildCategory() {
        parentsCategoriesOfTecDocCatalog().get(0).shouldBe(visible).click();
        childCategoriesFirstLevelBlock().get(0).shouldBe(visible);
        visibleChildCategory().get(0).shouldBe(visible).click();
        return page(Moto_Category_page_Logic.class);
    }

    @Step("select motorcycle in vertical selector .Moto_Categories_page")
    public Moto_Categories_page_Logic selectMotoWithOutTransition(String brand, String model, String motor) {
        closedSelector().shouldBe(visible).click();
        mainFormOfSelector().shouldBe(visible);
        brandOfMotoField().selectOptionByValue(brand);
        modelFiledInSelector().selectOptionByValue(model);
        motorFiledInSelector().selectOptionByValue(motor);
        return this;
    }

    @Step("presence of TOP products block .Moto_Categories_page")
    public Moto_Categories_page_Logic presenceOfTopProductsBlock() {
        topProductsBlock().shouldBe(visible);
        return this;
    }

    @Step("presence of TOP products headline .Moto_Categories_page")
    public Moto_Categories_page_Logic presenceOfTopProductsHeadline() {
        headlineOfTopProductsBlock().shouldBe(visible).shouldHave(text("MOTORRADTEILE"));
        return this;
    }

    @Step("get id of product in TecDoc Listing .Moto_Categories_page")
    public String getIdOfProductFromTecDocListing() {
        String idOfProduct = btnAddToBasketTopProduct().get(0).getAttribute("id");
        return idOfProduct;
    }

    @Step("added product to basket .Moto_Categories_page")
    public Cart_page_Logic addProductToBasket() {
        btnAddToBasketTopProduct().get(0).click();
        basketDropMenu().should(appear);
        basketDropMenu().should(disappear);
        basket().click();
        return page(Cart_page_Logic.class);
    }

}