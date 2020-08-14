package PKW;

import io.qameta.allure.Step;

import static PKW.CommonMethods.waitWhileRouteContainsExpectedCondition;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
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
}
