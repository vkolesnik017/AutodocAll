package PKW;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static PKW.CommonMethods.waitWhileRouteContainsExpectedCondition;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;

public class Motoroil_page_Logic extends Motoroil_page {

    @Step("presence of brands block. Motoroil_page")
    public Motoroil_page_Logic presenceOfBrandsBlock() {
        brandsBlock().shouldBe(visible);
        return this;
    }

    @Step("select brand in block. Motoroil_page")
    public Motoroil_page_Logic selectBrandInBlock(int positionOfBrands) {
        brandsBlock().shouldBe(visible);
        brandLinks().get(positionOfBrands).click();
        return this;
    }

    @Step("presence of Tolerance block. Motoroil_page")
    public Motoroil_page_Logic presenceOfToleranceBlock() {
        toleranceBlock().shouldBe(visible);
        return this;
    }

    @Step("select Tolerance link. Motoroil_page")
    public Motoroil_Release_page_Logic selectToleranceLink(int positionOfLink) {
        toleranceBlock().shouldBe(visible);
        toleranceLinks().get(positionOfLink).click();
        return page(Motoroil_Release_page_Logic.class);
    }

    @Step("presence of Tolerance block. Motoroil_page")
    public Motoroil_page_Logic presenceOfViscosityBlock() {
        viscosityBlock().shouldBe(visible);
        return this;
    }

    @Step("check elements of viscosity block. Motoroil_page")
    public Motoroil_page_Logic checkElementsOfViscosityBlock() {
        for (int i = 0; i < linksOfViscosityBlock().size(); i++) {
            linksOfViscosityBlock().get(i).shouldHave(attribute("href"));
            iconOfLinksInViscosityBlock().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("select Viscosity in block. Motoroil_page")
    public Motoroil_viscosity_page_Logic selectViscosityInBlock(int positionOfViscosityLink) {
        linksOfViscosityBlock().get(positionOfViscosityLink).shouldBe(visible).click();
        return page(Motoroil_viscosity_page_Logic.class);
    }

    @Step("presence of TOP  products block. Motoroil_page")
    public Motoroil_page_Logic presenceOfTopProductsBlock() {
        topProductsBlock().shouldBe(visible);
        return this;
    }

    @Step("check count of TOP products. Motoroil_page")
    public Motoroil_page_Logic checkCountOfTopProducts(int expectedCountOfTopProducts) {
        linksOfTopProducts().shouldHaveSize(expectedCountOfTopProducts);
        return this;
    }

    @Step("get Id of TOP product. Motoroil_page")
    public String getIdOfTopProduct(int positionOfProduct) {
        presenceOfTopProductsBlock();
        linksOfTopProducts().get(positionOfProduct).hover();
        String idOfProduct = btnDetailsOfTopProducts().get(positionOfProduct).shouldBe(visible).getAttribute("data-ga-action");
        return idOfProduct;
    }

    @Step("click On Details button of TOP product. Motoroil_page")
    public Product_page_Logic clickOnBtnDetailsOfTopProduct(int positionOfProduct) {
        btnDetailsOfTopProducts().get(positionOfProduct).shouldBe(visible).click();
        return page(Product_page_Logic.class);
    }

    @Step("check transition to Product page. Motoroil_page")
    public Motoroil_page_Logic checkTransitionToProductPage(String idOfProduct) {
        clickOnBtnDetailsOfTopProduct(0);
        waitWhileRouteContainsExpectedCondition(idOfProduct);
        back();
        clickOnTitleOfTopProduct(0);
        waitWhileRouteContainsExpectedCondition(idOfProduct);
        back();
        clickOnImageOfTopProduct(0);
        waitWhileRouteContainsExpectedCondition(idOfProduct);
        return this;
    }

    @Step("click On title of TOP product. Motoroil_page")
    public Product_page_Logic clickOnTitleOfTopProduct(int positionOfProduct) {
        presenceOfTopProductsBlock();
        linksOfTopProducts().get(positionOfProduct).hover();
        arrowsDownOfTopProduct().get(positionOfProduct).shouldBe(visible).click();
        titleOfTopProducts().get(positionOfProduct).shouldBe(visible).click();
        return page(Product_page_Logic.class);
    }

    @Step("click On image of TOP product. Motoroil_page")
    public Product_page_Logic clickOnImageOfTopProduct(int positionOfProduct) {
        presenceOfTopProductsBlock();
        linksOfTopProducts().get(positionOfProduct).hover();
        arrowsDownOfTopProduct().get(positionOfProduct).shouldBe(visible).click();
        imageOfTopProducts().get(positionOfProduct).shouldBe(visible).click();
        return page(Product_page_Logic.class);
    }

    @Step("presence Of Specification block. Motoroil_page")
    public Motoroil_page_Logic presenceOfSpecificationBlock() {
        specificationBlock().shouldBe(visible);
        return this;
    }

    @Step("click on Specification link. Motoroil_page")
    public Motoroil_specification_page_Logic clickOnSpecificationLink(int positionOfLink) {
        specificationLinks().get(positionOfLink).click();
        return page(Motoroil_specification_page_Logic.class);
    }

    @Step("added TOP product with expected count to Basket. Motoroil_page")
    public Motoroil_page_Logic addedTopProductWithCountToBasket(int positionOfTopProduct, int expectedCountOfTopProduct) {
        clickOnBtnCountUpTopProduct(btnUpOfCountTopProduct().get(positionOfTopProduct), expectedCountOfTopProduct);
        btnAddToBasketTopBLock().get(positionOfTopProduct).shouldBe(visible).click();
        basketDropMenu().should(appear);
        basketDropMenu().should(disappear);
        basket().click();
        return this;
    }

    @Step("click on Added Top product to basket button. Motoroil_page")
    public Motoroil_page_Logic clickOnBtnCountUpTopProduct(SelenideElement element, int expectedCount) {
        for (int i = 1; i < expectedCount; i++) {
            element.click();
        }
        return this;
    }

    @Step("presence Of KBA selector. Motoroil_page")
    public Motoroil_page_Logic presenceOfKbaSelector() {
        kbaSelector().shouldBe(visible);
        return this;
    }

    @Step("select Vehicle in KBA selector with incorrect number. Motoroil_page")
    public Motoroil_page_Logic selectVehicleInKbaSelectorWithIncorrectNumber(String firstValue, String secondValue) {
        firstFieldKbaSelector().setValue(firstValue);
        secondFieldKbaSelector().setValue(secondValue);
        btnSearchOfKbaSelector().click();
        return this;
    }

    @Step("visibility of KBA error toolTip. Motoroil_page")
    public Motoroil_page_Logic visibilityKbaErrorToolTip() {
        errorToolTipFirstField().shouldBe(visible);
        return this;
    }

    @Step("click on Search button of KBA selector. Motoroil_page")
    public Motoroil_page_Logic clickOnBtnSearchOfKbaSelector() {
        btnSearchOfKbaSelector().click();
        return this;
    }

    @Step("visibility error message about empty KBA fields. Motoroil_page")
    public Motoroil_page_Logic visibilityErrorMessageAboutEmptyKbaFields() {
        errorMessageAboutEmptyKbaFields().shouldBe(visible);
        return this;
    }

    @Step("presence of Information KBA pop-up. Motoroil_page")
    public Motoroil_page_Logic presenceOfInformationKbaPopUp() {
        informationKbaPopUp().shouldBe(visible).click();
        informationKbaPopUp().shouldBe(visible);
        return this;
    }

    @Step("presence of Information KBA pop-up. Motoroil_page")
    public Motoroil_page_Logic presenceOfDetectedVehiclePopUp() {
        detectedVehicleLink().shouldBe(visible).click();
        detectedVehiclePopUp().shouldBe(visible);
        return this;
    }
}
