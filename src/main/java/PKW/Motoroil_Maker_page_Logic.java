package PKW;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;

public class Motoroil_Maker_page_Logic extends Motoroil_Maker_page {

    @Step("presence of bread crumbs block. Motoroil_Maker_page")
    public Motoroil_Maker_page_Logic presenceOfBreadCrumbsBlock() {
        breadCrumbsBlock().shouldBe(visible);
        return this;
    }

    @Step("presence of products list block. Motoroil_Maker_page")
    public Motoroil_Maker_page_Logic presenceOfProductsListBlock() {
        productsListBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().shouldHaveSize(3);
        return this;
    }

    @Step("check transition by click on links of breadcrumbs. Motoroil_Maker_page")
    public Motoroil_Maker_page_Logic checkTransitionByClickOnLinksOfBreadCrumbs() throws SQLException {
        checkFirstLinkOfBreadCrumbs().presenceOfTecDocCatalog();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "parts"));
        back();
        checkSecondLinkOfBreadCrumbs().presenceOfBrandsBlock();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "motoroil"));
        back();
        presenceOfBreadCrumbsBlock().checkThirdLinkOfBreadCrumbs("VW");
        return this;
    }

    @Step("check First link of breadCrumbs. Motoroil_Maker_page")
    public Parts_page_Logic checkFirstLinkOfBreadCrumbs() {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(0).click();
        return page(Parts_page_Logic.class);
    }

    @Step("check Second link of breadCrumbs. Motoroil_Maker_page")
    public Motoroil_page_Logic checkSecondLinkOfBreadCrumbs() {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(1).click();
        return page(Motoroil_page_Logic.class);
    }

    @Step("check Third link of breadCrumbs. Motoroil_Maker_page")
    public Motoroil_Maker_page_Logic checkThirdLinkOfBreadCrumbs(String text) {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(2).shouldNotHave(attribute("href")).shouldHave(text(text));
        return this;
    }

    @Step("presence of Relinking blocks. Motoroil_Maker_page")
    public Motoroil_Maker_page_Logic presenceOfRelinkingBlocks(int expectedSize) {
        relinkingBlocks().get(0).shouldBe(visible);
        relinkingBlocks().shouldHaveSize(expectedSize);
        return this;
    }

    @Step("check main elements of Relinking blocks. Motoroil_Maker_page")
    public Motoroil_Maker_page_Logic checkElementsOfRelinkingBlocks() {
        for (int i = 0; i < relinkingBlocks().size(); i++) {
            titleOfRelinkingBLocks(i + 1).shouldBe(visible);
            contentPartOfRelinkingBLocks(i + 1).shouldBe(visible);
        }
        return this;
    }

    @Step("get attribute from link in Relinking block. Motoroil_Maker_page")
    public String getAttributeFromLink(ElementsCollection list, int position) {
        String urlFromLink = list.get(position).getAttribute("href");
        String urlPart = urlFromLink.replace(urlFromLink.substring(urlFromLink.lastIndexOf("/")), "");
        String cutUrlPart = urlPart.replace(urlPart.substring(urlPart.lastIndexOf("/")), "");
        String expectedPart = urlFromLink.replace(cutUrlPart + "/", "");
        return expectedPart;
    }

    @Step("check transition by click in Relinking block. Motoroil_Maker_page")
    public Motoroil_Maker_page_Logic checkTransitionByClickInRelinkingBlock() throws SQLException {
        String firstBlock = getAttributeFromLink(linksOfRelinkingBlocks(1), 0);
        String secondBlock = getAttributeFromLink(linksOfRelinkingBlocks(2), 0);

        clickOnValueFromFirstRelinkingBlock(0);
        checkingContainsUrl(firstBlock);
        back();
        clickOnValueFromSecondRelinkingBlock(0);
        checkingContainsUrl(secondBlock);
        return this;
    }

    @Step("click on value from First relinking block. Motoroil_Maker_page")
    public Motoroil_Maker_Group_page_Logic clickOnValueFromFirstRelinkingBlock(int position) {
        linksOfRelinkingBlocks(1).get(position).shouldBe(visible).scrollIntoView("{block: \"center\"}").hover().click();
        return page(Motoroil_Maker_Group_page_Logic.class);
    }

    @Step("click on value from Second relinking block. Motoroil_Maker_page")
    public Motoroil_viscosity_page_Logic clickOnValueFromSecondRelinkingBlock(int position) {
        linksOfRelinkingBlocks(2).get(position).shouldBe(visible).click();
        return page(Motoroil_viscosity_page_Logic.class);
    }


    @Step("presence of selector. Motoroil_Maker_page")
    public Motoroil_Maker_page_Logic presenceOfSelector() {
        selector().shouldBe(visible);
        return this;
    }

    @Step("visibility errorTooltip for Marke field of selector. Motoroil_Maker_page")
    public Motoroil_Maker_page_Logic visibilityErrorToolTipForMarkeFieldOfSelector() {
        selector().shouldBe(visible);
        markeFieldInSelector().selectOptionByValue("0");
        btnSearchOfSelector().click();
        errorToolTipOfMarkeFieldInSelector().shouldBe(visible);
        return this;
    }

    @Step("visibility errorTooltip for Model field of selector. Motoroil_Maker_page")
    public Motoroil_Maker_page_Logic visibilityErrorToolTipForModelFieldOfSelector(String idOfMarke) {
        selector().shouldBe(visible);
        markeFieldInSelector().selectOptionByValue(idOfMarke);
        btnSearchOfSelector().click();
        errorToolTipOfModelFieldInSelector().shouldBe(visible);
        return this;
    }

    @Step("visibility errorTooltip for Motor field of selector. Motoroil_Maker_page")
    public Motoroil_Maker_page_Logic visibilityErrorToolTipForMotorFieldOfSelector(String idOfModel) {
        selector().shouldBe(visible);
        modelFieldInSelector().selectOptionByValue(idOfModel);
        btnSearchOfSelector().click();
        errorToolTipOfMotorFieldInSelector().shouldBe(visible);
        return this;
    }

    @Step("presence of main listing of products. Motoroil_Maker_page")
    public Motoroil_Maker_page_Logic presenceOfMainListingOfProducts() {
        productsListBlock().shouldBe(visible);
        return this;
    }

    @Step("get Volume of added product. Motoroil_Maker_page")
    public String getVolumeOfAddedProduct(int positionOfAddedProduct) {
        String volumeOfProduct;
        if (activeValueOfVolumeAtProduct().get(positionOfAddedProduct).has(attribute("listtype", "listing"))) {
            volumeOfProduct = activeValueOfVolumeAtProduct().get(positionOfAddedProduct).getText().replaceAll("[^0-9]", "");
        } else {
            volumeOfProduct = "1";
        }
        return volumeOfProduct;
    }

    @Step("get id of added product to basket. Motoroil_Maker_page")
    public String getIdOfAddedProductToBasket(int positionOfAddedProduct) {
        String idOfProduct = btnAddedProductToBasket().get(positionOfAddedProduct).getAttribute("data-ga-entity-product-id");
        return idOfProduct;
    }

    @Step("added Product to basket. Motoroil_Maker_page")
    public Cart_page_Logic addProductToBasket(int positionOfAddedProduct) {
        btnAddedProductToBasket().get(positionOfAddedProduct).click();
        basketDropMenu().should(appear);
        basketDropMenu().should(disappear);
        basket().click();
        return page(Cart_page_Logic.class);
    }
}
