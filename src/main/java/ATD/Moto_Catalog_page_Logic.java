package ATD;

import Common.DataBase;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Common.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.password;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
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
        Assert.assertEquals(url(), new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", subRoute));
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
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "moto_categories_maker3"));
        back();
        clickOnSecondLinkOfBreadCrumbs();
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "moto_catalog_model2"));
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

    @Step("presence of main Headline block .Moto_Catalog_page")
    public Moto_Catalog_page_Logic presenceOfMainHeadlineBlock() {
        mainHeadline().shouldBe(visible);
        return this;
    }

    @Step("presence of brand icon in Headline .Moto_Catalog_page")
    public Moto_Catalog_page_Logic presenceOfBrandIconInHeadline() {
        iconOfBrandInHeadline().shouldBe(visible);
        return this;
    }


    @Step("presence of information icon .Moto_Catalog_page")
    public Moto_Catalog_page_Logic presenceOfInformationIcon() {
        informationIconAtHeadline().shouldBe(visible);
        return this;
    }

    @Step("presence of information pop-Up .Moto_Catalog_page")
    public Moto_Catalog_page_Logic presenceOfInformationPopUp() {
        informationIconAtHeadline().click();
        informationPopUp().shouldBe(visible);
        return this;
    }

    @Step("select product in search field .Moto_Catalog_page")
    public Moto_Product_page_Logic selectProductInSearchField(String artNumOfProduct) {
        mainSearchField().setValue(artNumOfProduct);
        hintBlockOfMainSearchField().shouldBe(visible);
        productInHintBlockOfSearchField().shouldHave(text(artNumOfProduct)).shouldBe(visible).click();
        return page(Moto_Product_page_Logic.class);
    }

    @Step("presence of TecDoc catalog .Moto_Catalog_page")
    public Moto_Catalog_page_Logic presenceOfTecDocCatalog() {
        catalogTecDoc().shouldBe(visible);
        parentsCategoriesOfTecDocCatalog().shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("click on child category .Moto_Catalog_page")
    public Moto_Category_car_list_page_Logic clickOnChildCategory() {
        parentsCategoriesOfTecDocCatalog().get(0).click();
        childCategoriesFirstLevelBlock().get(0).shouldBe(visible);
        visibleChildCategory().get(0).shouldBe(visible).click();
        return page(Moto_Category_car_list_page_Logic.class);
    }


    @Step("check parent categories of TecDoc catalog .Moto_Catalog_page")
    public Moto_Catalog_page_Logic checkParentCategoriesOfTecDocCatalog() {
        tecDocCatalog().shouldBe(visible);
        for (int i = 0; i < parentsCategoriesOfTecDocCatalog().size(); i++) {
            parentsCategoriesOfTecDocCatalog().get(i).shouldBe(visible).click();
            childCategoriesFirstLevelBlock().get(i).shouldBe(visible);
            checkFirstLevelOfParentCategories(i);
        }
        return this;
    }

    @Step("check First Level of parent categories .Moto_Catalog_page")
    public Moto_Catalog_page_Logic checkFirstLevelOfParentCategories(int position) {
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

    @Step("check intermediate child category first level .Moto_Catalog_page")
    public Moto_Catalog_page_Logic checkIntermediateChildCategoryFirstLevel(int position) {
        for (int j = 0; j < intermediateChildCategoriesFirstLevel(position + 1).size(); j++) {
            intermediateChildCategoriesFirstLevel(position + 1).get(j).click();
            secondLevelBlock().should(appear);
            checkSecondLevelOfParentCategories(position);
        }
        return this;
    }

    @Step("check Second Level of parent categories .Moto_Catalog_page")
    public Moto_Catalog_page_Logic checkSecondLevelOfParentCategories(int position) {
        childCategoriesSecondLevel(position + 1).shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("check count of TOP products .Moto_Catalog_page")
    public Moto_Catalog_page_Logic checkCountOfTopProducts() {
        topProducts().shouldHaveSize(6);
        return this;
    }

    @Step("Comparison TecDoc and catalog in header .Moto_Catalog_page")
    public Moto_Catalog_page_Logic comparisonTecDocAndCatalogInHeader() {
        List<Integer> attributeOfTecDocCatalog = new ArrayList<>(getCategoryInTecDocCatalog());
        List<Integer> attributeOfInHeaderCatalog = new ArrayList<>(getCategoryInHeaderCatalog());
        Assert.assertTrue(attributeOfTecDocCatalog.containsAll(attributeOfInHeaderCatalog));
        return this;
    }

    @Step("Getting category in TecDock catalog .Moto_Catalog_page")
    public List<Integer> getCategoryInTecDocCatalog() {
        List<Integer> tecDocCatalogList = new ArrayList<>();
        catalogTecDoc().shouldBe(visible);
        addAttributeOfProductToList(categoriesTecDocCatalog(), tecDocCatalogList);
        return tecDocCatalogList;
    }

    @Step("add attributes to list .Moto_Catalog_page")
    private Moto_Catalog_page_Logic addAttributeOfProductToList(ElementsCollection category, List<Integer> listWithAttribute) {
        for (int k = 0; k < category.size(); k++) {
            listWithAttribute.add(Integer.parseInt(category.get(k).getAttribute("data-category-id")));
        }
        return this;
    }

    @Step("Getting category in Header Catalog  .Moto_Catalog_page")
    public List<Integer> getCategoryInHeaderCatalog() {
        List<Integer> catalogInHeaderList = new ArrayList<>();
        catalogInHeader().scrollTo().click();
        parentCategoriesInVerticalCatalog().shouldBe(visible);
        customCategory().hover();
        addAttributeOfProductToList(categoriesInHeaderCatalogSecondBlock(), catalogInHeaderList);
        addAttributeOfProductToList(categoriesInHeaderCatalogThirdBlock(), catalogInHeaderList);
        return catalogInHeaderList;
    }

    @Step("checking list of added vehicle in header after selected a new vehicle .Moto_Catalog_page")
    public Moto_Catalog_page_Logic checkListOfAddedVehicleInHeaderAfterSelectedNewAuto() {
        mainHeadline().shouldBe(visible);
        garageIconInHeader().shouldBe(visible).click();
        urlsOfAddedVehicleInPopUpOfGarageFromSelector().get(0).shouldBe(visible).shouldHave(attribute("href", url()));
        radioBtnOfAddedVehicleInPopUpOfGarageFromSelector().get(0).shouldHave(attribute("checked", "true"));
        return this;
    }

    @Step("added current url to list .Moto_Catalog_page")
    public Moto_Catalog_page_Logic addedCurrentUrlToList(List<String> list) {
        selectorInCloseCondition().shouldBe(visible);
        list.add(url());
        return this;
    }

    @Step("check selected vehicle in PopUp of garage icon .Moto_Catalog_page")
    public Moto_Catalog_page_Logic checkSelectedVehicleInPopUpOfGarageIcon(List<String> list, int sizeOfAddedVehicle) {
        List<String> listOfVehicleFromPopUp = new ArrayList<>();
        garageIconInHeader().shouldBe(visible).click();
        popUpOfGarageInHeader().shouldBe(visible);
        for (int i = 0; i < urlsOfAddedVehicleInPopUpOfGarageInHeader().size(); i++) {
            listOfVehicleFromPopUp.add(urlsOfAddedVehicleInPopUpOfGarageInHeader().get(i).getAttribute("href"));
        }
        Assert.assertTrue(urlsOfAddedVehicleInPopUpOfGarageInHeader().get(0).getAttribute("href").equals(list.get(list.size() - 1)));
        visibleVehicleInPopUpOfMyGarageUrls().shouldHaveSize(sizeOfAddedVehicle);
        return this;
    }

    @Step("close popUp of my garage block .Moto_Catalog_page")
    public Moto_Catalog_page_Logic closePopUpOfMyGarageBlock() {
        garageIconInHeaderActive().shouldBe(visible).click();
        return this;
    }

    @Step("check Added Vehicle In My Garage Pop-Up .Moto_Catalog_page")
    public Moto_Catalog_page_Logic checkAddedVehicleInMyGaragePopUp(int size) {
        urlsOfAddedVehicleInPopUpOfGarageInHeader().shouldHaveSize(size);
        return this;
    }

    @Step("checking absence vehicle in My garage block .Moto_Catalog_page")
    public Moto_Catalog_page_Logic checkingAbsenceVehicleInMyGarageBlock(String vehicle) {
        List<String> visibleVehicle = new ArrayList<>();
        for (int i = 0; i < visibleVehicleInPopUpOfMyGarageUrls().size(); i++) {
            visibleVehicle.add(visibleVehicleInPopUpOfMyGarageUrls().get(i).getAttribute("href"));
        }
        Assert.assertTrue(!visibleVehicle.contains(vehicle));
        return this;
    }

    @Step("visible pop-Up of My garage block .Moto_Catalog_page")
    public Moto_Catalog_page_Logic visiblePopUpOfMyGarageBlock() {
        garageIconInHeader().shouldBe(visible).click();
        popUpOfGarageInHeader().shouldBe(visible);
        return this;
    }

    @Step("visible pop-Up of My garage block .Moto_Catalog_page")
    public Moto_Catalog_page_Logic openRegistrationFormFromMyGarage(int position) {
        btnSaveAddedVehicle().get(position).shouldBe(visible).click();
        registrationForm().shouldBe(visible);
        return this;
    }

    @Step("Login from My_garage block with mail {mail} and transition to profile plus page. Moto_Catalog_page")
    public Profile_garage_page_Logic loginFromMyGarageAndTransitionToProfilePlusPage(String mail) {
        mailFieldLogin().shouldBe(visible).setValue(mail);
        passFieldLogin().setValue(password);
        submitBtnLogin().click();
        return page(Profile_garage_page_Logic.class);
    }

    @Step("check transition by click on vehicle in My garage popUp .Moto_Catalog_page")
    public Moto_Catalog_page_Logic checkTransitionByClickOnVehicleInMyGaragePopUp() throws SQLException {
        clickOnMotoInMyGaragePopUp();
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "moto_catalog7"));
        back();
        clickOnTruckInMyGaragePopUp();
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "lkw_maker_car_list8"));
        back();
        clickOnVehicleInMyGaragePopUp();
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "maker_car_list10"));
        return this;
    }

    @Step("click on motorcycle in Viewed history block in My garage Pop-Up .Moto_Catalog_page")
    public Moto_Catalog_page_Logic clickOnMotoInMyGaragePopUp() {
        urlsOfAddedVehicleInPopUpOfGarageInHeader().get(0).shouldBe(visible).click();
        return page(Moto_Catalog_page_Logic.class);
    }

    @Step("click on truck in Viewed history block in My garage Pop-Up .Moto_Catalog_page")
    public LKW_maker_car_list_Logic clickOnTruckInMyGaragePopUp() {
        garageIconInHeader().shouldBe(visible).click();
        popUpOfGarageInHeader().shouldBe(visible);
        urlsOfAddedVehicleInPopUpOfGarageInHeader().get(1).shouldBe(visible).click();
        return page(LKW_maker_car_list_Logic.class);
    }

    @Step("click on vehicle in Viewed history block in My garage Pop-Up .Moto_Catalog_page")
    public LKW_maker_car_list_Logic clickOnVehicleInMyGaragePopUp() {
        garageIconInHeader().shouldBe(visible).click();
        popUpOfGarageInHeader().shouldBe(visible);
        urlsOfAddedVehicleInPopUpOfGarageInHeader().get(2).shouldBe(visible).click();
        return page(LKW_maker_car_list_Logic.class);
    }

    @Step("absence of selector from My garage block .Moto_Catalog_page")
    public Moto_Catalog_page_Logic absenceOfSelectorFromMyGarageBlock() {
        selectorFromMyGarageBlock().shouldNotHave(exist);
        return this;
    }

    @Step("Open moto Selector Block. Moto_Catalog_page")
    public Moto_Catalog_page_Logic openMotoSelectorsBlock() {
        motoSelectorBlock().shouldBe(visible).click();
        return this;
    }

    @Step("Get data from motor Of Moto Selector. Moto_Catalog_page")
    public String getDataFromMotorOfMotoSelector() {
        motorOfMotoSelector().shouldBe(visible);
        return motorOfMotoSelector().getText().replaceAll(" ", "").toLowerCase();
    }

}
