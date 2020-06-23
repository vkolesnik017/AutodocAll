package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Moto_Catalog_model_page_Logic extends Moto_Catalog_model_page {

    @Step("availability of moto selector .Moto_Catalog_model_page")
    public Moto_Catalog_model_page_Logic availabilityOfMotoSelector() {
        motoSelectorBlock().shouldBe(visible);
        return this;
    }

    @Step("visibility of main form of selector .Moto_Catalog_model_page")
    public Moto_Catalog_model_page_Logic openingSelector() {
        motoSelectorInCloseCondition().shouldBe(visible).click();
        motoSelectorMainForm().shouldBe(visible);
        return this;
    }

    @Step(" appears of tooltip for marke field in selector .Moto_Catalog_model_page")
    public Moto_Catalog_model_page_Logic visibilityOfToolTipForMarkeField() {
        if (!brandOfMotoSelector().getSelectedValue().equals("0")) {
            brandOfMotoSelector().selectOptionByValue("0");
        }
        btnSearchAtSelector().shouldBe(visible).click();
        toolTipForBrandFieldInSelector().shouldBe(visible);
        return this;
    }

    @Step(" appears of tooltip for model field in selector .Moto_Catalog_model_page")
    public Moto_Catalog_model_page_Logic visibilityOfToolTipForModelField() {
        brandOfMotoSelector().selectOptionByValue("4057");
        btnSearchAtSelector().click();
        toolTipForModelFieldInSelector().shouldBe(visible);
        return this;
    }

    @Step(" appears of tooltip for motor field in selector .Moto_Catalog_model_page")
    public Moto_Catalog_model_page_Logic visibilityOfToolTipForMotorField() {
        modelOfMotoSelector().selectOptionByValue("13475");
        btnSearchAtSelector().click();
        toolTipForMotorFieldInSelector().shouldBe(visible);
        return this;
    }

    @Step(" selecting motorcycle in selector .Moto_Catalog_model_page")
    public Moto_Catalog_page_Logic selectMotoInSelector(String marke, String model, String motor) {
        motoSelectorBlock().click();
        brandOfMotoSelector().selectOptionByValue(marke);
        modelOfMotoSelector().selectOptionByValue(model);
        motorOfMotoSelector().selectOptionByValue(motor);
        btnSearchAtSelector().click();
        return page(Moto_Catalog_page_Logic.class);
    }

    @Step("get brand of  motorcycle from Url .Moto_Catalog_model_page")
    public String getBrandOfMotoFromUrl() {
        String[] pathParts = url().split("/");
        String brandOfMoto = pathParts[pathParts.length - 2];
        return brandOfMoto;
    }

    @Step("get model of  motorcycle from Url .Moto_Catalog_model_page")
    public String getModelOfMotoFromUrl() {
        String[] pathParts = url().split("/");
        String modelOfMoto = pathParts[pathParts.length - 1];
        return modelOfMoto;
    }


    @Step(" appears of tooltip for motor field in selector .Moto_Catalog_model_page")
    public Moto_Catalog_model_page_Logic presenceBrandAndModelOfMotoInSelector(String brand, String model) {
        String brandOfMotoFromSelector = brandOfMotoSelector().getSelectedText().replaceAll("[^A-Z]", "");
        String modelOfMotoFromSelector = modelOfMotoSelector().getSelectedText().replaceAll("[^a-zA-Z]", "");
        Assert.assertEquals(brandOfMotoFromSelector, brand.replaceAll("[^a-z]", "").toUpperCase());
        Assert.assertEquals(modelOfMotoFromSelector, model.replaceAll("[^a-z]", "").toUpperCase());
        return this;
    }

    @Step(" visibility of opening selector .Moto_Catalog_model_page")
    public Moto_Catalog_model_page_Logic visibilityOfOpeningSelector() {
        motoSelectorBlock().shouldBe(visible);
        toolTipForMotorFieldInSelector().shouldBe(visible);
        return this;
    }

    @Step(" reset of motorcycle selector .Moto_Catalog_model_page")
    public Moto_Categories_page_Logic resetOfMotoSelector() {
        mainFormOfSelector().shouldBe(visible);
        btnResetOfSelector().shouldBe(visible).click();
        return page(Moto_Categories_page_Logic.class);
    }


    @Step(" check Bread Crumbs Block .Moto_Catalog_model_page")
    public Moto_Catalog_model_page_Logic checkBreadCrumbsBlock() {
        firstLinkOfBreadCrumbsBlock("SUZUKI MOTORCYCLES");
        secondLinkOfBreadCrumbsBlock("ACCESS");
        thirdLinkOfBreadCrumbsBlock("Motorrad Wählen");
        fourthLinkOfBreadCrumbsBlock("Teil Wählen");
        clickOnFirstLinkOfBreadCrumbs();
        return this;
    }

    @Step(" first link of bread crumbs block .Moto_Catalog_model_page")
    public Moto_Catalog_model_page_Logic firstLinkOfBreadCrumbsBlock(String titleOfMoto) {
        breadCrumbsLinks().shouldHaveSize(4);
        iconOfMotoBrandInBreadCrumbs().shouldBe(visible);
        titleOfMotoBrandInBreadCrumbs().shouldHave(text(titleOfMoto));
        return this;
    }

    @Step(" second link of bread crumbs block .Moto_Catalog_model_page")
    public Moto_Catalog_model_page_Logic secondLinkOfBreadCrumbsBlock(String titleOfMotoModel) {
        breadCrumbsLinks().get(1).shouldHave(text(titleOfMotoModel));
        return this;
    }

    @Step(" third link of bread crumbs block .Moto_Catalog_model_page")
    public Moto_Catalog_model_page_Logic thirdLinkOfBreadCrumbsBlock(String title) {
        breadCrumbsLinks().get(2).shouldHave(text(title)).shouldNotHave(attribute("href"));
        return this;
    }

    @Step(" fourth link of bread crumbs block .Moto_Catalog_model_page")
    public Moto_Catalog_model_page_Logic fourthLinkOfBreadCrumbsBlock(String title) {
        breadCrumbsLinks().get(3).shouldHave(text(title)).shouldNotHave(attribute("href"));
        return this;
    }

    @Step("click on first link of bread crumbs .Moto_Catalog_model_page")
    public Moto_Categories_maker_page_Logic clickOnFirstLinkOfBreadCrumbs() {
        titleOfMotoBrandInBreadCrumbs().click();
        return page(Moto_Categories_maker_page_Logic.class);
    }


    @Step(" presence brand and model in headLine .Moto_Catalog_model_page")
    public Moto_Catalog_model_page_Logic presenceBrandAndModelInHeadLine(String brandAndModel) {
        mainHeadline().shouldBe(visible).shouldHave(text(brandAndModel));
        return this;
    }

    @Step("click on child category with out all values in selector .Moto_Catalog_model_page")
    public Moto_Catalog_model_page_Logic clickOnChildCategoryWithOutAllValuesInSelector() {
        parentsCategoriesOfTecDocCatalog().get(0).click();
        childCategoriesFirstLevelBlock().get(0).shouldBe(visible);
        childCategoriesFirstLevel(1).get(0).shouldBe(visible).click();
        return this;
    }

    @Step("appear of selector with tooltip .Moto_Catalog_model_page")
    public Moto_Catalog_model_page_Logic openingOfSelectorWithTooltip() {
        mainFormOfSelector().shouldBe(visible);
        toolTipForMotorFieldInSelector().shouldBe(visible);
        return this;
    }

    @Step("check parent categories of TecDoc catalog .Moto_Catalog_model_page")
    public Moto_Catalog_model_page_Logic checkParentCategoriesOfTecDocCatalog() {
        tecDocCatalog().shouldBe(visible);
        for (int i = 0; i < parentsCategoriesOfTecDocCatalog().size(); i++) {
            parentsCategoriesOfTecDocCatalog().get(i).click();
            childCategoriesFirstLevelBlock().get(i).shouldBe(visible);
            checkFirstLevelOfParentCategories(i);
        }
        return this;
    }

    @Step("check First Level of parent categories .Moto_Catalog_model_page")
    public Moto_Catalog_model_page_Logic checkFirstLevelOfParentCategories(int position) {
      /*  if (childCategoriesFirstLevel(position + 1).get(0).isDisplayed() && intermediateChildCategoriesFirstLevel(position + 1).get(0).isDisplayed()) {
            childCategoriesFirstLevel(position + 1).shouldHave(sizeGreaterThan(0));
            checkIntermediateChildCategoryFirstLevel(position);
        } else if (childCategoriesFirstLevel(position + 1).get(0).isDisplayed()) {
            childCategoriesFirstLevel(position + 1).shouldHave(sizeGreaterThan(0));
        } else if (intermediateChildCategoriesFirstLevel(position + 1).get(0).isDisplayed()) {
            checkIntermediateChildCategoryFirstLevel(position);
        }*/
/*------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
        if (chCategory().get(0).isDisplayed() && imedCategory().get(0).isDisplayed()) {
            chCategory().shouldHave(sizeGreaterThan(0));
            checkIntermediateChildCategoryFirstLevel(position);
        } else if (chCategory().get(0).isDisplayed()) {
            chCategory().shouldHave(sizeGreaterThan(0));
        } else if (imedCategory().get(0).isDisplayed()) {
            checkIntermediateChildCategoryFirstLevel(position);
        }
/*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
        return this;
    }

    @Step("check intermediate child category first level .Moto_Catalog_model_page")
    public Moto_Catalog_model_page_Logic checkIntermediateChildCategoryFirstLevel(int position) {
        for (int j = 0; j < intermediateChildCategoriesFirstLevel(position + 1).size(); j++) {
            intermediateChildCategoriesFirstLevel(position + 1).get(j).click();
           // childCategoriesSecondLevelBlockk().get(0).should(appear);
            secondLevelBlock().should(appear);
         //   childCategoriesSecondLevelBlockCheck(position + 1).get(j).should(appear);
            checkSecondLevelOfParentCategories(position);
        }
        return this;
    }

    @Step("check Second Level of parent categories .Moto_Catalog_model_page")
    public Moto_Catalog_model_page_Logic checkSecondLevelOfParentCategories(int position) {
        childCategoriesSecondLevel(position + 1).shouldHave(sizeGreaterThan(0));
        return this;
    }

}
