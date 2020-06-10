package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Moto_Catalog_page_Logic extends Moto_Catalog_page {

    @Step("Сheck that the page loads successfully. Moto_Catalog_page")
    public Moto_Catalog_page_Logic checkSuccessfullyMotoCatalogPageLoading() {
        catalogTecDoc().shouldBe(visible);
        Assert.assertEquals(url(), "https://moto.autodoc.de/motorradteile/bmw-motorcycles/c?car_id=115569");
        return this;
    }

    @Step("Visibility Of TecDoc Catalog. Moto_Catalog_page")
    public Moto_Catalog_page_Logic visibilityOfTecDocCatalog() {
        catalogTecDoc().shouldBe(visible);
        return this;
    }

    @Step("Select Car category. Moto_Catalog_page")
    public Main_page_Logic selectCarCategory() {
        carCategory().click();
        return page(Main_page_Logic.class);
    }

    @Step("Select LKW category. Moto_Catalog_page")
    public LKW_main_page_Logic selectLKWCategory() {
        lkwCategory().click();
        return page(LKW_main_page_Logic.class);
    }

    @Step(" availability Of Moto Selector .Moto_Catalog_page")
    public Moto_Catalog_page_Logic availabilityOfMotoSelector() {
        motoSelectorBlock().shouldBe(visible);
        return this;
    }

    @Step(" appears of tooltip for marke field in selector .Moto_Catalog_page")
    public Moto_Catalog_page_Logic visibilityOfToolTipForMarkeField() {
        motoSelectorBlock().shouldBe(visible).click();
        mainFormOfSelector().shouldBe(visible);
        if (!brandOfMotoSelector().getSelectedValue().equals("0")) {
            brandOfMotoSelector().selectOptionByValue("0");
        }
        btnSearchAtSelector().click();
        toolTipForBrandFieldInSelector().shouldBe(visible);
        return this;
    }

    @Step(" appears of tooltip for model field in selector .Moto_Catalog_page")
    public Moto_Catalog_page_Logic visibilityOfToolTipForModelField() {
        brandOfMotoSelector().selectOptionByValue("4057");
        btnSearchAtSelector().click();
        toolTipForModelFieldInSelector().shouldBe(visible);
        return this;
    }

    @Step(" appears of tooltip for motor field in selector .Moto_Catalog_page")
    public Moto_Catalog_page_Logic visibilityOfToolTipForMotorField() {
        modelOfMotoSelector().selectOptionByValue("13475");
        btnSearchAtSelector().click();
        toolTipForMotorFieldInSelector().shouldBe(visible);
        return this;
    }

    @Step(" selecting motorcycle in selector .Moto_Catalog_page")
    public Moto_Catalog_page_Logic selectMotoInSelector(String marke, String model, String motor) {
        motoSelectorBlock().click();
        mainFormOfSelector().shouldBe(visible);
        brandOfMotoSelector().selectOptionByValue(marke);
        modelOfMotoSelector().selectOptionByValue(model);
        motorOfMotoSelector().selectOptionByValue(motor);
        btnSearchAtSelector().click();
        return page(Moto_Catalog_page_Logic.class);
    }

    @Step("get brand of  motorcycle from Url .Moto_Catalog_page")
    public String getBrandOfMotoFromUrl() {
        String[] pathParts = url().replace(url().substring(url().lastIndexOf("=")), "").split("/");
        String brandOfMoto = pathParts[pathParts.length - 2];
        return brandOfMoto;
    }

    @Step("get model of  motorcycle from Url .Moto_Catalog_page")
    public String getModelOfMotoFromUrl() {

        String[] pathParts = url().replace(url().substring(url().lastIndexOf("=")), "").split("/");
        String modelOfMoto = (pathParts[pathParts.length - 1]).replaceAll("\\?(.*)", "");
        return modelOfMoto;
    }

    @Step("get motor of  motorcycle from Url .Moto_Catalog_page")
    public String getValueOfMotorOfMotoFromUrl() {

        String motorOfMoto = url().substring(3).substring(url().replace(url().substring(url().lastIndexOf("=")), "").lastIndexOf("id"));
        return motorOfMoto;
    }

    @Step(" appears of tooltip for motor field in selector .Moto_Catalog_page")
    public Moto_Catalog_page_Logic presenceBrandAndModelOfMotoInSelector(String brand, String model, String motor) {
        if (motoSelectorBlock().isDisplayed()) {
            motoSelectorBlock().click();
        }
        mainFormOfSelector().shouldBe(visible);
        String brandOfMotoFromSelector = brandOfMotoSelector().getSelectedText().replaceAll("[^A-Z]", "");
        String modelOfMotoFromSelector = modelOfMotoSelector().getSelectedText().replaceAll("[^a-zA-Z]", "");
        String motorOfMotoFromSelector = motorOfMotoSelector().getSelectedValue();
        Assert.assertEquals(brandOfMotoFromSelector, brand.replaceAll("[^a-z]", "").toUpperCase());
        Assert.assertEquals(modelOfMotoFromSelector, model.replaceAll("[^a-z]", "").toUpperCase());
        Assert.assertEquals(motorOfMotoFromSelector, motor);
        return this;
    }


    @Step("presence Of Headline In Selector .Moto_Catalog_page")
    public Moto_Catalog_page_Logic presenceOfHeadlineInSelector() {
        if (motoSelectorBlock().isDisplayed()) {
            motoSelectorBlock().click();
        }
        headlineBlockInSelector().shouldBe(visible);
        imageInHeadlineInSelector().shouldBe(visible);
        return this;
    }


    @Step("presence Of Selected motorcycle In Headline() .Moto_Catalog_page")
    public Moto_Catalog_page_Logic presenceOfSelectedMotoInHeadline() {
        String motoModel = getModelOfMotoFromSelector();
        Assert.assertTrue(textOfHeadlineInSelector().getText().replaceAll("[^A-Z]", "").contains(motoModel.replaceAll("[^A-Z]", "")));
        return this;
    }

    @Step("presence Of Headline In Selector .Moto_Catalog_page")
    public String getModelOfMotoFromSelector() {
        String brandOfMoto = brandOfMotoSelector().getSelectedText();
        String modelOfMoto;
        if (!motorOfMotoSelector().getSelectedValue().equals("0")) {
            modelOfMoto = motorOfMotoSelector().getSelectedText();
        } else {
            modelOfMoto = modelOfMotoSelector().getSelectedText();
        }
        String moto = brandOfMoto + " " + modelOfMoto;
        String motoFromSelector = moto.replace(moto.substring(moto.lastIndexOf("(")), "").toUpperCase();
        return motoFromSelector;
    }

    @Step("check current url .Moto_Catalog_page")
    public Moto_Catalog_page_Logic checkCurrentUrl(String subRoute) throws SQLException {
        catalogTecDoc().shouldBe(visible);
        Assert.assertEquals(url(), new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", subRoute));
        return this;
    }

    @Step(" reset of motorcycle selector .Moto_Catalog_page")
    public Moto_Categories_page_Logic resetOfMotoSelector() {
        selectorInCloseCondition().shouldBe(visible).click();
        mainFormOfSelector().shouldBe(visible);
        btnResetOfSelector().shouldBe(visible).click();
        return page(Moto_Categories_page_Logic.class);
    }


    @Step("check current url .Moto_Catalog_page")
    public Moto_main_page_Logic clickOnMainLogoInHeader() {
        mainLogoInHeader().shouldBe(visible).click();
        return page(Moto_main_page_Logic.class);
    }

    @Step(" check Bread Crumbs Block .Moto_Catalog_page")
    public Moto_Catalog_page_Logic checkBreadCrumbsBlock() throws SQLException {
        firstLinkOfBreadCrumbsBlock("SUZUKI MOTORCYCLES");
        secondLinkOfBreadCrumbsBlock("ACCESS");
        thirdLinkOfBreadCrumbsBlock("Access 125");
        fourthLinkOfBreadCrumbsBlock("Teil Wählen");
        clickOnFirstLinkOfBreadCrumbs();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "moto_categories_maker3"));
        back();
        clickOnSecondLinkOfBreadCrumbs();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "moto_catalog_model2"));
        return this;
    }

    @Step(" first link of bread crumbs block .Moto_Catalog_page")
    public Moto_Catalog_page_Logic firstLinkOfBreadCrumbsBlock(String titleOfMoto) {
        breadCrumbsLinks().shouldHaveSize(4);
        iconOfMotoBrandInBreadCrumbs().shouldBe(visible);
        titleOfMotoBrandInBreadCrumbs().shouldHave(text(titleOfMoto));
        return this;
    }

    @Step(" second link of bread crumbs block .Moto_Catalog_page")
    public Moto_Catalog_page_Logic secondLinkOfBreadCrumbsBlock(String titleOfMotoModel) {
        breadCrumbsLinks().get(1).shouldHave(text(titleOfMotoModel));
        return this;
    }

    @Step(" third link of bread crumbs block .Moto_Catalog_page")
    public Moto_Catalog_page_Logic thirdLinkOfBreadCrumbsBlock(String title) {
        breadCrumbsLinks().get(2).shouldHave(text(title));
        return this;
    }

    @Step(" fourth link of bread crumbs block .Moto_Catalog_page")
    public Moto_Catalog_page_Logic fourthLinkOfBreadCrumbsBlock(String title) {
        breadCrumbsLinks().get(3).shouldHave(text(title)).shouldNotHave(attribute("href"));
        return this;
    }

    @Step("click on first link of bread crumbs .Moto_Catalog_page")
    public Moto_Categories_maker_page_Logic clickOnFirstLinkOfBreadCrumbs() {
        titleOfMotoBrandInBreadCrumbs().click();
        return page(Moto_Categories_maker_page_Logic.class);
    }

    @Step("click on second link of bread crumbs .Moto_Catalog_page")
    public Moto_Catalog_model_page_Logic clickOnSecondLinkOfBreadCrumbs() {
        breadCrumbsLinks().get(1).shouldBe(visible).click();
        return page(Moto_Catalog_model_page_Logic.class);
    }
}
