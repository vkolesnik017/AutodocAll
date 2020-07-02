package ATD;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static ATD.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;

public class Moto_Parent_Category_page_Logic extends Moto_Parent_Category_page {

    @Step(" selecting motorcycle in selector  .Moto_Parent_Category_page")
    public Moto_Catalog_page_Logic selectMotoInSelector(String marke, String model, String motor) {
        brandOfMotoField().selectOptionByValue(marke);
        brandOfMotoField().shouldHave(exactValue(marke));
        modelFiledInSelector().selectOptionByValue(model);
        modelFiledInSelector().shouldHave(exactValue(model));
        motorFiledInSelector().selectOptionByValue(motor);
        motorFiledInSelector().shouldHave(exactValue(motor));
        searchButton().click();
        return page(Moto_Catalog_page_Logic.class);
    }


    @Step(" Select brand of motorcycle .Moto_Parent_Category_page")
    public Moto_Parent_Category_page_Logic selectBrandOfMoto(String markeOfMoto) {
        brandOfMotoField().selectOptionByValue(markeOfMoto);
        return this;
    }

    @Step(" reset of motorcycle selector .Moto_Parent_Category_page")
    public Moto_Parent_Category_page_Logic resetOfMotoSelector() {
        btnResetOfSelector().shouldBe(visible).click();
        return this;
    }


    @Step(" reset of motorcycle selector .Moto_Parent_Category_page")
    public Moto_Parent_Category_page_Logic presenceOfEmptyValuesInSelector() {
        brandOfMotoField().shouldHave(exactValue("0"));
        modelFiledInSelector().shouldHave(exactValue("0"));
        motorFiledInSelector().shouldHave(exactValue("0"));
        return this;
    }

    @Step(" check current url  .Moto_Parent_Category_page")
    public Moto_Parent_Category_page_Logic checkCurrentUrl(String subRoute) throws SQLException {
        CommonMethods commonMethods = new CommonMethods();
        DataBase db = new DataBase();
        commonMethods.checkingContainsUrl(db.getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", subRoute));
        return this;
    }


    @Step(" presence of exact text in headline at child category block  .Moto_Parent_Category_page")
    public Moto_Parent_Category_page_Logic visibilityOfHeadlineAtChildCategoryBlock(String text) {
        headlineOfChildCategoryBlock().shouldBe(visible).shouldHave(exactText(text));
        return this;
    }


    @Step(" presence of main image of Parent category and it size  .Moto_Parent_Category_page")
    public Moto_Parent_Category_page_Logic visibilityOfMainImageOfParentCategory() {
        imageOfParentCategory().shouldBe(visible);
        checkOfImageSize(imageOfParentCategory(), 200);
        return this;
    }


    @Step(" check of child categories list  .Moto_Parent_Category_page")
    public Moto_Parent_Category_page_Logic checkOfChildCategoriesList() {
        childCategoriesListBlock().shouldBe(visible);
        linksOfChildCategoriesList().shouldHave(sizeGreaterThan(1));
        return this;
    }


    @Step(" presence of main elements in links at child categories block  .Moto_Parent_Category_page")
    public Moto_Parent_Category_page_Logic presenceOfElementsAtChildCategoriesBlock() {
        for (int i = 0; i < linksOfChildCategoriesList().size(); i++) {
            textOfChildCategoriesList().get(i).shouldBe(visible);
            checkOfImageSize(imageOfChildCategoriesList().get(i), 55);
        }
        return this;
    }

    @Step(" check of image size  .Moto_Parent_Category_page")
    public Moto_Parent_Category_page_Logic checkOfImageSize(SelenideElement element, int size) {
        int widthOfElement = element.getSize().getWidth();
        int heightOfElement = element.getSize().getHeight();
        Assert.assertEquals(widthOfElement, size);
        Assert.assertEquals(heightOfElement, size);
        return this;
    }


    @Step(" click on child Category  .Moto_Parent_Category_page")
    public Moto_Category_page_Logic clickOnChildCategory(int position) {
        linksOfChildCategoriesList().get(position).shouldBe(visible).click();
        return page(Moto_Category_page_Logic.class);
    }

    @Step(" click on child Category with selected motorcycle .Moto_Parent_Category_page")
    public Moto_Category_car_list_page_Logic clickOnChildCategoryWithMoto(int position) {
        linksOfChildCategoriesList().get(position).shouldBe(visible).click();
        return page(Moto_Category_car_list_page_Logic.class);
    }


    @Step(" check bread crumbs block .Moto_Parent_Category_page")
    public Moto_Parent_Category_page_Logic checkBreadCrumbsBlock() throws SQLException {
        breadCrumbsLinks().shouldHaveSize(6);
        checkFirstLinkOfBreadCrumbsLinks();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "moto_categories"));
        back();
        checkSecondLinkOfBreadCrumbsLinks("Filter");
        checkThirdLinkOfBreadCrumbsLinks("Teil Wählen");
        checkFourthLinkOfBreadCrumbsLinks("Marke Wählen");
        checkFifthLinkOfBreadCrumbsLinks("Modell Wählen");
        checkSixthLinkOfBreadCrumbsLinks("Motorrad Wählen");
        return this;
    }

    @Step(" check first link of bread crumbs links .Moto_Parent_Category_page")
    public Moto_Categories_page_Logic checkFirstLinkOfBreadCrumbsLinks() {
        iconOfCatalogBrandInBreadCrumbs().shouldBe(visible).click();
        return page(Moto_Categories_page_Logic.class);
    }

    @Step(" check second link of bread crumbs links .Moto_Parent_Category_page")
    public Moto_Parent_Category_page_Logic checkSecondLinkOfBreadCrumbsLinks(String title) {
        breadCrumbsLinks().get(1).shouldBe(visible).shouldHave(text(title)).shouldNotHave(attribute("href"));
        return this;
    }

    @Step(" check third link of bread crumbs links .Moto_Parent_Category_page")
    public Moto_Parent_Category_page_Logic checkThirdLinkOfBreadCrumbsLinks(String title) {
        breadCrumbsLinks().get(2).shouldBe(visible).shouldHave(text(title)).shouldNotHave(attribute("href"));
        return this;
    }

    @Step(" check fourth link of bread crumbs links .Moto_Parent_Category_page")
    public Moto_Parent_Category_page_Logic checkFourthLinkOfBreadCrumbsLinks(String title) {
        breadCrumbsLinks().get(3).shouldBe(visible).shouldHave(text(title)).shouldNotHave(attribute("href"));
        return this;
    }

    @Step(" check fifth link of bread crumbs links .Moto_Parent_Category_page")
    public Moto_Parent_Category_page_Logic checkFifthLinkOfBreadCrumbsLinks(String title) {
        breadCrumbsLinks().get(4).shouldBe(visible).shouldHave(text(title)).shouldNotHave(attribute("href"));
        return this;
    }

    @Step(" check sixth link of bread crumbs links .Moto_Parent_Category_page")
    public Moto_Parent_Category_page_Logic checkSixthLinkOfBreadCrumbsLinks(String title) {
        breadCrumbsLinks().get(5).shouldBe(visible).shouldHave(text(title)).shouldNotHave(attribute("href"));
        return this;
    }

    @Step("presence of TOP products block .Moto_Parent_Category_page")
    public Moto_Parent_Category_page_Logic presenceOfTopProductsBlock() {
        topProductsBlock().shouldBe(visible);
        return this;
    }

    @Step("check count of TOP products .Moto_Parent_Category_page")
    public Moto_Parent_Category_page_Logic checkCountOfTopProducts() {
        topProducts().shouldHaveSize(10);
        return this;
    }

    @Step("get id of product in TecDoc Listing .Moto_Parent_Category_page")
    public String getIdOfProductFromTecDocListing() {
        String idOfProduct = btnAddToBasketTopProduct().get(0).getAttribute("id");
        return idOfProduct;
    }

    @Step("added product to basket .Moto_Parent_Category_page")
    public Cart_page_Logic addProductToBasket() {
        btnAddToBasketTopProduct().get(0).click();
        basketDropMenu().should(appear);
        basketDropMenu().should(disappear);
        basket().click();
        return page(Cart_page_Logic.class);
    }

    @Step("get brand from TOP product title .Moto_Parent_Category_page")
    public String getBrandFromTopProductTitle() {
        String titleOfBrand = titleOfTopProducts().get(0).getText().replace(titleOfTopProducts().get(0).getText().substring(titleOfTopProducts().get(0).getText().lastIndexOf(" ")), "").toLowerCase();
        String pathUrl = titleOfBrand.replace(titleOfBrand.substring(titleOfBrand.lastIndexOf(" ")), "");
        return pathUrl;
    }


    @Step("Go to product page from TOP products block through Image, icon of brand, title in tecDoc listing .Moto_Parent_Category_page")
    public Moto_Parent_Category_page_Logic goToProductPageFromTopBlock(String brand) {
        clickOnImageOfTopProduct().checkUrlOfProductPage(brand);
        back();
        clickOnTitleOfTopProduct().checkUrlOfProductPage(brand);
        back();
        clickOnDetailsOfTopProduct().checkUrlOfProductPage(brand);
        return this;
    }

    @Step("click on image of TOP product .Moto_Parent_Category_page")
    public Moto_Product_page_Logic clickOnImageOfTopProduct() {
        imageOfTopProducts().get(0).shouldBe(visible).click();
        return page(Moto_Product_page_Logic.class);
    }

    @Step("click on title of TOP product .Moto_Parent_Category_page")
    public Moto_Product_page_Logic clickOnTitleOfTopProduct() {
        titleOfTopProducts().get(0).shouldBe(visible).click();
        return page(Moto_Product_page_Logic.class);
    }

    @Step("click on details of TOP product .Moto_Parent_Category_page")
    public Moto_Product_page_Logic clickOnDetailsOfTopProduct() {
        btnDetailsOfTopProducts().get(0).shouldBe(visible).click();
        return page(Moto_Product_page_Logic.class);
    }


    @Step("check generic and TOP product .Moto_Parent_Category_page")
    public Moto_Parent_Category_page_Logic checkGenericAndTopProduct() {
        List<String> genericFromBlock = new ArrayList<>();
        for (int i = 0; i < textOfChildCategoriesList().size(); i++) {
            genericFromBlock.add(textOfChildCategoriesList().get(i).getText());
        }
        HashSet<String> genericFromTitle = new HashSet<String>();
        for (int i = 0; i < titleOfTopProduct().size(); i++) {
            genericFromTitle.add(getGenericFromTitleOfTopProduct(titleOfTopProduct().get(i).getText()));
        }
        genericFromBlock.containsAll(genericFromTitle);
        return this;
    }


    @Step("get generic from title of TOP product .Moto_Parent_Category_page")
    public String getGenericFromTitleOfTopProduct(String titleOfProduct) {
        String brand = titleOfProduct.replace(titleOfProduct.substring(titleOfProduct.lastIndexOf(" ")), "");
        String generic = titleOfProduct.replace(brand, "");
        return generic;
    }

    @Step(" presence of parent category catalog in sidebar .Moto_Parent_Category_page")
    public Moto_Parent_Category_page_Logic presenceOfParentCategoryCatalogInSidebar() {
        parentCategoryBlockInSidebar().shouldBe(visible);
        return this;
    }

    @Step("open parent category catalog in sidebar .Moto_Parent_Category_page")
    public Moto_Parent_Category_page_Logic openParentCategoryCatalogInSidebar() {
        parentCategoryBlockInSidebar().shouldBe(visible).click();
        parentCategoriesInSideBar().shouldHaveSize(17);
        return this;
    }


    @Step("select parent category in sideBar .Moto_Parent_Category_page")
    public Moto_Parent_Category_page_Logic selectParentCategoryInSideBar(int position) {
        parentCategoriesInSideBar().get(position).shouldBe(visible).click();
        return page(Moto_Parent_Category_page_Logic.class);
    }

    @Step("presence of linking block .Moto_Parent_Category_page")
    public Moto_Parent_Category_page_Logic presenceOfLinkingBlock() {
        return this;
    }
}
