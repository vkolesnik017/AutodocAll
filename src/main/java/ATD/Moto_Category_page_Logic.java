package ATD;

import AWS.ProductCard_aws;
import Common.DataBase;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ATD.CommonMethods.getTextFromUnVisibleElement;
import static Common.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Moto_Category_page_Logic extends Moto_Category_page {

    @Step("check successfully child category page loading. Moto_Category_page")
    public Moto_Category_page_Logic checkSuccessfullyChildCategoryPageLoading() {
        imageOfChildCategory().shouldBe(visible);
        Assert.assertEquals(url(), "https://moto.autodoc.de/ersatzteile/motorrad-luftfilter-43063");
        return this;
    }

    @Step(" availability Of Moto Selector. Moto_Category_page")
    public Moto_Category_page_Logic availabilityOfMotoSelector() {
        motoSelectorBlock().shouldBe(visible);
        return this;
    }

    @Step(" appears of tooltip for marke field in selector. Moto_Category_page")
    public Moto_Category_page_Logic visibilityOfToolTipForMarkeField() {
        if (!brandOfMotoField().getSelectedValue().equals("0")) {
            brandOfMotoField().selectOptionByValue("0");
        }
        btnSearchAtSelector().click();
        tooltipOfMarkeField().shouldBe(visible);
        return this;
    }

    @Step(" appears of tooltip for model field in selector. Moto_Category_page")
    public Moto_Category_page_Logic visibilityOfToolTipForModelField() {
        brandOfMotoField().selectOptionByValue("4057");
        btnSearchAtSelector().click();
        tooltipOfModelField().shouldBe(visible);
        return this;
    }

    @Step(" appears of tooltip for motor field in selector. Moto_Category_page")
    public Moto_Category_page_Logic visibilityOfToolTipForMotorField() {
        modelFiledInSelector().selectOptionByValue("13475");
        btnSearchAtSelector().click();
        tooltipOfMotorField().shouldBe(visible);
        return this;
    }

    @Step(" selecting motorcycle in selector . Moto_Category_page")
    public Moto_Category_car_list_page_Logic selectMotoInSelector(String marke, String model, String motor) {
        brandOfMotoField().selectOptionByValue(marke);
        brandOfMotoField().shouldHave(exactValue(marke));
        modelFiledInSelector().selectOptionByValue(model);
        modelFiledInSelector().shouldHave(exactValue(model));
        motorFiledInSelector().selectOptionByValue(motor);
        motorFiledInSelector().shouldHave(exactValue(motor));
        searchButton().click();
        return page(Moto_Category_car_list_page_Logic.class);
    }

    @Step(" check current url. Moto_Category_page")
    public Moto_Category_page_Logic checkCurrentUrl(String subRoute) throws SQLException {
        checkingContainsUrl(new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", subRoute));
        return this;
    }

    @Step(" Select brand of motorcycle .Moto_Category_page")
    public Moto_Category_page_Logic selectBrandOfMoto(String markeOfMoto) {
        brandOfMotoField().selectOptionByValue(markeOfMoto);
        return this;
    }

    @Step(" reset of motorcycle selector .Moto_Category_page")
    public Moto_Category_page_Logic resetOfMotoSelector() {
        btnResetOfSelector().shouldBe(visible).click();
        return this;
    }

    @Step(" reset of motorcycle selector .Moto_Category_page")
    public Moto_Category_page_Logic presenceOfEmptyValuesInSelector() {
        brandOfMotoField().shouldHave(exactValue("0"));
        modelFiledInSelector().shouldHave(exactValue("0"));
        motorFiledInSelector().shouldHave(exactValue("0"));
        return this;
    }

    @Step(" select motorcycle model in selector .Moto_Category_page")
    public Moto_Category_page_Logic selectMotoModel(String model) {
        brandOfMotoField().shouldNotHave(exactValue("0"));
        modelFiledInSelector().shouldBe(visible).selectOptionByValue(model);
        return this;
    }

    @Step(" select motorcycle motor in selector .Moto_Category_page")
    public Moto_Category_page_Logic selectMotoMotor(String motor) {
        motorFiledInSelector().shouldBe(visible).selectOptionByValue(motor);
        return this;
    }

    @Step(" click on Search button in selector .Moto_Category_page")
    public Moto_Category_car_list_page_Logic clickOnSearchButton() {
        searchButton().click();
        return page(Moto_Category_car_list_page_Logic.class);
    }

    @Step("presence of benefits block .Moto_Category_page")
    public Moto_Category_page_Logic presenceOfBenefitsBlock() {
        benefitsBlock().shouldBe(visible);
        return this;
    }

    @Step("presence of headline at benefits block .Moto_Category_page")
    public Moto_Category_page_Logic presenceOfHeadlineAtBenefitsBlock() {
        headlineBenefitsBlock().shouldBe(visible);
        return this;
    }

    @Step("presence of benefits links .Moto_Category_page")
    public Moto_Category_page_Logic presenceOfBenefitsLinks() {
        benefitsLinks().shouldHaveSize(2);
        return this;
    }

    @Step("visibility of tooltip at benefits links .Moto_Category_page")
    public Moto_Category_page_Logic visibilityOfTooltipAtBenefitsLinks() {
        benefitsBlock().shouldBe(visible);
        for (int i = 0; i < imageOfBenefitsLinks().size(); i++) {
            imageOfBenefitsLinks().get(i).hover();
            tooltipOfBenefitsLinks().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("presence Of elements at benefit block .Moto_Category_page")
    public Moto_Category_page_Logic presenceOfElementsAtBenefitBlock() {
        benefitsBlock().shouldBe(visible);
        for (int i = 0; i < imageOfBenefitsLinks().size(); i++) {
            imageOfBenefitsLinks().get(i).shouldBe(visible);
            textOfBenefitsLinks().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("presence of main image at child category .Moto_Category_page")
    public Moto_Category_page_Logic presenceOfMainImageAtChildCategory() {
        mainImageOfChildCategory().shouldBe(visible);
        int widthOfMainImageAtChildCategory = mainImageOfChildCategory().getSize().getWidth();
        int heightOfMainImageAtChildCategory = mainImageOfChildCategory().getSize().getHeight();
        Assert.assertEquals(widthOfMainImageAtChildCategory, 200);
        Assert.assertEquals(heightOfMainImageAtChildCategory, 200);
        return this;
    }

    @Step(" check bread crumbs block .Moto_Category_page")
    public Moto_Category_page_Logic checkBreadCrumbsBlock() throws SQLException {
        breadCrumbsLinks().shouldHaveSize(6);
        checkFirstLinkOfBreadCrumbsLinks();
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "moto_categories"));
        back();
        checkSecondLinkOfBreadCrumbsLinks("Filter");
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "moto_parent_category"));
        back();
        checkThirdLinkOfBreadCrumbsLinks("Ölfilter");
        checkFourthLinkOfBreadCrumbsLinks("Marke Wählen");
        checkFifthLinkOfBreadCrumbsLinks("Modell Wählen");
        checkSixthLinkOfBreadCrumbsLinks("Motorrad Wählen");
        return this;
    }

    @Step(" check first link of bread crumbs links .Moto_Category_page")
    public Moto_Categories_page_Logic checkFirstLinkOfBreadCrumbsLinks() {
        iconOfCatalogBrandInBreadCrumbs().shouldBe(visible).click();
        return page(Moto_Categories_page_Logic.class);
    }

    @Step(" check second link of bread crumbs links .Moto_Category_page")
    public Moto_Parent_Category_page_Logic checkSecondLinkOfBreadCrumbsLinks(String title) {
        breadCrumbsLinks().get(1).shouldBe(visible).shouldHave(text(title)).click();
        return page(Moto_Parent_Category_page_Logic.class);
    }

    @Step(" check third link of bread crumbs links .Moto_Category_page")
    public Moto_Category_page_Logic checkThirdLinkOfBreadCrumbsLinks(String title) {
        breadCrumbsLinks().get(2).shouldBe(visible).shouldHave(text(title)).shouldNotHave(attribute("href"));
        return this;
    }

    @Step(" check fourth link of bread crumbs links .Moto_Category_page")
    public Moto_Category_page_Logic checkFourthLinkOfBreadCrumbsLinks(String title) {
        breadCrumbsLinks().get(3).shouldBe(visible).shouldHave(text(title)).shouldNotHave(attribute("href"));
        return this;
    }

    @Step(" check fifth link of bread crumbs links .Moto_Category_page")
    public Moto_Category_page_Logic checkFifthLinkOfBreadCrumbsLinks(String title) {
        breadCrumbsLinks().get(4).shouldBe(visible).shouldHave(text(title)).shouldNotHave(attribute("href"));
        return this;
    }

    @Step(" check sixth link of bread crumbs links .Moto_Category_page")
    public Moto_Category_page_Logic checkSixthLinkOfBreadCrumbsLinks(String title) {
        breadCrumbsLinks().get(5).shouldBe(visible).shouldHave(text(title)).shouldNotHave(attribute("href"));
        return this;
    }

    @Step("presence of TOP products block .Moto_Category_page")
    public Moto_Category_page_Logic presenceOfTopProductsBlock() {
        topProductsBlock().shouldBe(visible);
        return this;
    }

    @Step("presence of TOP products headline .Moto_Category_page")
    public Moto_Category_page_Logic presenceOfTopProductsHeadline() {
        headlineOfTopProductsBlock().shouldBe(visible);
        infoTextOfTopProductBlock().shouldBe(visible);
        return this;
    }

    @Step("check count of TOP products .Moto_Category_page")
    public Moto_Category_page_Logic checkCountOfTopProducts() {
        topProducts().shouldHaveSize(8);
        return this;
    }

    @Step("get id of product in TecDoc Listing .Moto_Category_page")
    public String getIdOfProductFromTecDocListing() {
        topProducts().get(0).hover();
        String idOfProduct = btnAddToBasketTopProduct().get(0).shouldBe(visible).getAttribute("id");
        return idOfProduct;
    }

    @Step("added product to basket .Moto_Category_page")
    public Cart_page_Logic addProductToBasket() {
        btnAddToBasketTopProduct().get(0).click();
        basketDropMenu().should(appear);
        basketDropMenu().should(disappear);
        basket().click();
        return page(Cart_page_Logic.class);
    }

    @Step("get brand from TOP product title .Moto_Category_page")
    public String getBrandFromTopProductTitle() {
        String pathUrl = titleOfTopProducts().get(0).getText().replaceAll("(.+)(\\s.+)", "$1").replaceAll(" ", "-").toLowerCase();
        return pathUrl;
    }

    @Step("Go to product page from TOP products block through Image, icon of brand, title in tecDoc listing .Moto_Category_page")
    public Moto_Category_page_Logic goToProductPageFromTopBlock(String brand) {
        clickOnImageOfTopProduct().checkUrlOfProductPage(brand);
        back();
        clickOnTitleOfTopProduct().checkUrlOfProductPage(brand);
        back();
        clickOnDetailsOfTopProduct().checkUrlOfProductPage(brand);
        return this;
    }

    @Step("click on image of TOP product .Moto_Category_page")
    public Moto_Product_page_Logic clickOnImageOfTopProduct() {
        imageOfTopProducts().get(0).shouldBe(visible).click();
        return page(Moto_Product_page_Logic.class);
    }

    @Step("click on title of TOP product .Moto_Category_page")
    public Moto_Product_page_Logic clickOnTitleOfTopProduct() {
        titleOfTopProducts().get(0).shouldBe(visible).click();
        return page(Moto_Product_page_Logic.class);
    }

    @Step("click on details of TOP product .Moto_Category_page")
    public Moto_Product_page_Logic clickOnDetailsOfTopProduct() {
        titleOfTopProducts().get(0).shouldBe(visible).hover();
        detailsOfTopProductsBlock().get(0).shouldBe(visible);
        btnDetailsOfTopProducts().get(0).shouldBe(visible).click();
        return page(Moto_Product_page_Logic.class);
    }

    @Step("visibility of PopUp with addition information .Moto_Category_page")
    public Moto_Category_page_Logic visibilityOfPopUpWithAdditionInfo() {
        for (int i = 0; i < imageOfTopProducts().size(); i++) {
            imageOfTopProducts().get(i).shouldBe(visible).hover();
            detailsOfTopProductsBlock().get(i).shouldBe(visible);
            headlineOfTopProductsBlock().hover();
        }
        return this;
    }

    @Step("visibility of PopUp with addition information .Moto_Category_page")
    public Moto_Category_page_Logic checkGenericAndTopProduct() {
        for (int i = 0; i < titleOfTopProducts().size(); i++) {
            titleOfTopProducts().get(i).shouldHave(text("Ölfilter"));
        }
        return this;
    }

    @Step("click on child category in sidebar .Moto_Category_page")
    public Moto_Category_page_Logic clickOnChildCategoryInSidebar(int position) {
        childCategoriesInSideBar().get(position).shouldBe(visible).click();
        return page(Moto_Category_page_Logic.class);
    }


    @Step("check child categories link .Moto_Category_page")
    public Moto_Category_page_Logic checkChildCategoriesLink() {
        List<String> categoriesFromTable = new ArrayList<>();
        categoriesFromTable.add("Luftfilter");
        categoriesFromTable.add("Kraftstofffilter");
        List<String> categoriesFromSideBar = new ArrayList<>();
        for (int i = 0; i < genericsInSideBar().size(); i++) {
            categoriesFromSideBar.add(genericsInSideBar().get(i).getText());
        }
        Assert.assertTrue(categoriesFromTable.containsAll(categoriesFromSideBar));
        return this;
    }

    @Step("get id of Dangerous product .Moto_Category_page")
    public String getIdOfDangerousProduct(int positionOfProduct) {
        return btnAddDangerousProductToWishList().get(positionOfProduct).getAttribute("data-product-id");
    }

    @Step("get signal word from first dangerous product .Moto_Category_page")
    public String getSignalWordFromFirstDangerousProduct(int positionOfProduct) {
        return getTextFromUnVisibleElement(signalWordOfDangerousProduct().get(positionOfProduct));
    }

    @Step("get attribute of Warning icon in pop-Up .Moto_Category_page")
    public List<String> getAttributeOfWarningIconInPopUp(int positionOfProduct) {
        List<String> attribute = new ArrayList<>();
        dangerousProducts().get(positionOfProduct).scrollIntoView("{block: \"center\"}").hover();
        for (int i = 0; i < attributeOfWarningIcon(positionOfProduct + 1).size(); i++) {
            String attributeFromIcon = attributeOfWarningIcon(positionOfProduct + 1).get(i).getAttribute("style").replace("background-image: url(\"", "").replace("\");", "");
            String partOfAttribute = attributeFromIcon.replace(attributeFromIcon.substring(attributeFromIcon.lastIndexOf(".")), "");
            attribute.add(partOfAttribute);
        }
        return attribute;
    }

    @Step("click on dangerous label of product and compare elements. Moto_Category_page")
    public Moto_Category_page_Logic clickOnDangerousLabelAndCompareElements(int positionOfProduct, String signalWord, List<String> attributeOfWarningIcon) {

        if (!labelTitleDangerousProducts().get(positionOfProduct).isDisplayed()) {
            dangerousProducts().get(positionOfProduct).scrollIntoView("{block: \"center\"}").hover();
        } else {
            labelTitleDangerousProducts().get(positionOfProduct).shouldBe(visible).click();
            blackBackground().shouldHave(attribute("style", "display: block;"));
            warningPopUp().shouldBe(visible).shouldHave(attribute("style", "display: block;"));

            if (signalWord.replaceAll("\n", "").trim().equals("Beachten Sie!")) {
                infoTextOfDangerousPopUp().shouldNotBe(empty);
            } else {
                titleOfDangerousPopUp().shouldBe(visible).shouldHave(exactText(signalWord));
                infoTextOfDangerousPopUp().shouldNotBe(empty);
            }

            List<String> attributeOfDangerousIcon = new ArrayList<>();
            for (int i = 0; i < dangerousIconInWarningPopUp().size(); i++) {
                String urlFromAttribute = dangerousIconInWarningPopUp().get(i).getAttribute("style").replace("background-image: url(\"", "").replace("\");", "");
                String partOfAttribute = urlFromAttribute.replace(urlFromAttribute.substring(urlFromAttribute.lastIndexOf(".")), "");
                attributeOfDangerousIcon.add(partOfAttribute);
            }
            Assert.assertEquals(attributeOfDangerousIcon, attributeOfWarningIcon);
        }
        return this;
    }

    @Step("get Id Of All TOP Products .Moto_Category_page")
    public List<String> getIdOfAllTopProducts() {
        List<String> idOfTopProduct = btnAddProductToWishList().stream().map(n -> n.attr("data-product-id")).collect(Collectors.toList());
        return idOfTopProduct;
    }

    @Step("checking product and generic conformity. Moto_Category_page")
    public Moto_Category_page_Logic checkProductAndGenericConformity(List<String> idOfTopProduct, String generic) {
        ProductCard_aws productCardAws = new ProductCard_aws();
        List<String> idCategory = new ArrayList<>();
        for (int i = 0; i < idOfTopProduct.size(); i++) {
            idCategory.add(productCardAws.openExactProductCardPageAndLogin(idOfTopProduct.get(i)).getCategoryId());
        }
        Assert.assertTrue(idCategory.contains(generic));
        return this;
    }

    @Step("check Seo Text Block. Moto_Category_page")
    public Moto_Category_page_Logic checkSeoTextBlock(String value) throws SQLException {
        String frontText = seoText().shouldBe(visible).getText().replaceAll("\\W", "");
        String bdText = new DataBase("ATD").getTranslate("seo_text", "DE", value).replaceAll("\\W", "");
        Assert.assertEquals(frontText, bdText);
        return this;
    }
}
