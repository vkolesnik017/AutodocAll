package ATD;

import AWS.ProductCard_aws;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

import static Common.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;

public class Supplier_brand_line_page_Logic extends Supplier_brand_line_page {

    @Step("presence Another Brands Block.  Supplier_brand_line_page")
    public Supplier_brand_line_page_Logic presenceAnotherBrandsBlock() {
        anotherBrandsBlock().shouldBe(visible);
        anotherBrandLinks().shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("transition To All Another Brands.  Supplier_brand_line_page")
    public Supplier_brand_line_page_Logic transitionToAllAnotherBrands(String brand) {
        for (int i = 0; i < anotherBrandLinks().size(); i++) {
            String currentBrand = anotherBrandLinks().get(i).getText().replaceAll(brand + " ", "");
            String currentMainHeadline = mainHeadline().getText();
            anotherBrandLinks().get(i).click();
            mainHeadline().shouldNotHave(exactText(currentMainHeadline));
            List<String> currentLinks = anotherBrandLinks().stream().map(n -> n.getText().replaceAll(brand + " ", "")).collect(Collectors.toList());
            Assert.assertFalse(currentLinks.contains(currentBrand.toUpperCase()));
            currentLinks.clear();
        }
        return this;
    }

    @Step("presence TOP Products block.  Supplier_brand_line_page")
    public Supplier_brand_line_page_Logic presenceTopProductsBlock() {
        topProductsBlock().shouldBe(visible);
        return this;
    }

    @Step("get id Of TOP product.  Supplier_brand_line_page")
    public String getIdOfTopProduct(int position) {
        return btnAddProductToWishList().get(position).attr("data-product-id");
    }

    @Step("add TOP Product To Basket.  Supplier_brand_line_page")
    public Supplier_brand_line_page_Logic addTopProductToBasket(int position) {
        btnAddTopProductToBasket().get(position).scrollTo().click();
        return this;
    }

    @Step("appear of Basket Drop Menu.  Supplier_brand_line_page")
    public Supplier_brand_line_page_Logic appearBasketDropMenu() {
        basketDropMenu().should(appear);
        basketDropMenu().should(disappear);
        return this;
    }

    @Step("click On Basket In Header.  Supplier_brand_line_page")
    public Supplier_brand_line_page_Logic clickOnBasketInHeader() {
        basket().click();
        return this;
    }

    @Step("display Of TOP product pop-Up.  Supplier_brand_line_page")
    public Supplier_brand_line_page_Logic displayOfTopProductPopUp() {
        for (int i = 0; i < imageOfTopProducts().size(); i++) {
            headlineOfTopProductBlock().hover();
            imageOfTopProducts().get(i).hover();
            topProductsPopUp().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("check Transition To Product Page.  Supplier_brand_line_page")
    public Supplier_brand_line_page_Logic checkTransitionToProductPage(int position) {
        String idOfProduct = getIdOfTopProduct(position);
        clickOnImageOfTopProduct(position);
        checkingContainsUrl(idOfProduct);
        back();
        clickOnTitleOfTopProduct(position);
        checkingContainsUrl(idOfProduct);
        back();
        clickOnDetailsOfTopProduct(position);
        checkingContainsUrl(idOfProduct);
        return this;
    }

    @Step("click On Image Of TOP Product.  Supplier_brand_line_page")
    public Product_page_Logic clickOnImageOfTopProduct(int position) {
        imageOfTopProducts().get(position).shouldBe(visible).click();
        return page(Product_page_Logic.class);
    }

    @Step("click On title Of TOP Product.  Supplier_brand_line_page")
    public Product_page_Logic clickOnTitleOfTopProduct(int position) {
        titleOfTopProducts().get(position).shouldBe(visible).click();
        return page(Product_page_Logic.class);
    }

    @Step("click On Details Of TOP Product.  Supplier_brand_line_page")
    public Product_page_Logic clickOnDetailsOfTopProduct(int position) {
        titleOfTopProducts().get(position).scrollTo().hover();
        detailsOfTopProducts().get(position).shouldBe(visible).click();
        return page(Product_page_Logic.class);
    }

    @Step("check Presence Of Line Value().  Supplier_brand_line_page")
    public Supplier_brand_line_page_Logic checkPresenceLineValue(String expectedValue) {
        for (int i = 0; i < imageOfTopProducts().size(); i++) {
            headlineOfTopProductBlock().hover();
            imageOfTopProducts().get(i).hover();
            topProductsPopUp().get(i).shouldBe(visible);
            lineValueInTopPopUp(i + 1).shouldBe(visible).shouldHave(exactText(expectedValue));
        }
        return this;
    }

    @Step("get all id Of TOP product.  Supplier_brand_line_page")
    public List<String> getAllIdOfTopProduct() {
        List<String> idOfProducts = btnAddProductToWishList().stream().map(n -> n.attr("data-product-id")).collect(Collectors.toList());
        return idOfProducts;
    }

    @Step("check Presence Of Line Value.  Supplier_brand_line_page")
    public Supplier_brand_line_page_Logic checkBrandLineValueInAws(List<String> idOfProduct, String idOfCharacteristic, String value) {
        ProductCard_aws productCardAws = new ProductCard_aws();
        for (int i = 0; i < idOfProduct.size(); i++) {
            productCardAws.openExactProductCardPageAndLogin(idOfProduct.get(i)).checkValueOfIdCharacteristic(idOfCharacteristic, value);
        }
        return this;
    }
}
