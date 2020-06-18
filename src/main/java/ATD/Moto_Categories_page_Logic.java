package ATD;

import io.qameta.allure.Step;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
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
        checkingContainsUrl(new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", subRoute));
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
        if (childCategoriesFirstLevel(position + 1).get(0).isDisplayed() && intermediateChildCategoriesFirstLevel(position + 1).get(0).isDisplayed()) {
            childCategoriesFirstLevel(position + 1).shouldHave(sizeGreaterThan(0));
            checkIntermediateChildCategoryFirstLevel(position);
        } else if (childCategoriesFirstLevel(position + 1).get(0).isDisplayed()) {
            childCategoriesFirstLevel(position + 1).shouldHave(sizeGreaterThan(0));
        } else if (intermediateChildCategoriesFirstLevel(position + 1).get(0).isDisplayed()) {
            checkIntermediateChildCategoryFirstLevel(position);
        }
        return this;
    }

    @Step("check intermediate child category first level .Moto_Categories_page")
    public Moto_Categories_page_Logic checkIntermediateChildCategoryFirstLevel(int position) {
        for (int j = 0; j < intermediateChildCategoriesFirstLevel(position + 1).size(); j++) {
            intermediateChildCategoriesFirstLevel(position + 1).get(j).click();
            childCategoriesSecondLevelBlock().should(appear);
            checkSecondLevelOfParentCategories(position);
        }
        return this;
    }

    @Step("check Second Level of parent categories .Moto_Categories_page")
    public Moto_Categories_page_Logic checkSecondLevelOfParentCategories(int position) {
        childCategoriesSecondLevel(position + 1).shouldHave(sizeGreaterThan(0));
        return this;
    }
}
