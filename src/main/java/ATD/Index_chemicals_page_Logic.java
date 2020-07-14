package ATD;

import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.Collections;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.CollectionCondition.size;


public class Index_chemicals_page_Logic extends Index_chemicals_page {



    @Step("Check presence block subcategories after hover on catalog category. Index_chemicals_page")
    public Index_chemicals_page_Logic checkLogicalUnion(){
        catalogFirstGroup().hover();
        sleep(2000);
        catalogCategories().shouldBe(visible);
        return this;
    }


    @Step("Check presence of top brands block. Index_chemicals_page")
    public Index_chemicals_page_Logic checkingPresenceOfTopBrandsBlock() {
        blockTopBrands().shouldBe(visible);
        return this;
    }


    @Step("Checks the presence of a block with site features . Index_chemicals_page")
    public Index_chemicals_page_Logic checkingPresenceOfFeaturesBlock() {
        blockFeatures().shouldBe(visible);
        return this;
    }

    @Step("Checks the presence of the top products block.Index_chemicals_page")
    public Index_chemicals_page_Logic checkingPresenceOfTopProductBlock() {
        blockTopProducts().shouldBe(visible);
        return this;
    }

    @Step("Checks the number of products in top block.  Index_chemicals_page")
    public Index_chemicals_page_Logic checkingTheQuantityOfGoods (int expectedSize) {
        productsInTopBlock().shouldHave(size(expectedSize));
        return this;
    }

    @Step("Checking block Advantages and popup describing the Advantage. Index_chemicals_page")
    public Index_chemicals_page_Logic checkingPresenceOfAdvantagesBlockAndAdvantageDescription() {
        blockAdvantages().shouldBe(visible);
        secondAdvantageInBlockAdvantages().hover();
        blockAdvantagesPopup().shouldBe(visible);
        return this;
    }

    @Step("Get name first product in block top products. Index_chemicals_page ")
    public String getNameProductInBlockTopProducts (){
        return nameFirstProductInBlockTopProducts().getText();
    }

    @Step("Checking transition on product page after click on button details in popup product block top products. Index_chemicals_page ")
    public Product_page_Logic checkingTransitionOnProductPageAfterClickDetails() {
        nameFirstProductInBlockTopProducts().hover();
        detailsBtnInPopupInTopProductBlock().click();
        return page(Product_page_Logic.class);
    }

    @Step("Checking presence subtitle. Index_chemicals_page ")
    public Index_chemicals_page_Logic checkingPresenceSubtitle() {
        Assert.assertFalse(subTitleNameMainPage().text().isEmpty());
        return this;
    }

    @Step("Checking presence title. Index_chemicals_page")
    public Index_chemicals_page_Logic checkingPresenceTitle() {
        Assert.assertFalse(titleNameMainPage().text().isEmpty());
        return this;
    }

    @Step("Checking presence block Delivery. Index_chemicals_page")
    public Index_chemicals_page_Logic checkingPresenceBlockDelivery() {
        blockDelivery().shouldBe(visible);
        return this;
    }

    @Step("Click on first button Add to basket in top products block. Index_chemicals_page")
    public Index_chemicals_page_Logic clickOnFirstBtnAddToBasketInTopProductsBlock() {
        firstBtnAddToBasketInTopProductsBlock().click();
        sleep(2000);
        return this;
    }

    @Step(":from Index_chemicals_page")
    public Cart_page_Logic clickOnBtnGoToBasket() {
        new Main_page_Logic().cartClick();
        return page(Cart_page_Logic.class);
    }

    @Step("Get name first category in Logical Union after Hover. Index_chemicals_page")
    public String getNameFirstCategoryInLogicalUnionAfterHover() {
        catalogFirstGroup().hover();
        return nameFirstCategoryInLogicalUnion().getText();

    }

    @Step("Click on first category in Logical Union. Index_chemicals_page")
    public Listing_chemicals_Page_Logic clickOnFirstCategoryInLogicalUnion() {
        nameFirstCategoryInLogicalUnion().click();
        return page(Listing_chemicals_Page_Logic.class);
    }

    @Step("Checking hover popup in top 6 product block. Index_chemicals_page")
    public Index_chemicals_page_Logic checkingHoverPopupInTop6ProductBlock() {
        nameFirstProductInBlockTopProducts().hover();
        detailsBtnInPopupInTopProductBlock().shouldBe(visible);
        return this;
    }

    @Step("Get name first separate category on main catalog. Index_chemicals_page")
    public String getNameFirstSeparateCategoryMainCatalog() {
        return firstSeparateCategoryInMainCatalog().getText();
    }

    @Step("Click first separate category on main catalog. Index_chemicals_page")
    public Listing_chemicals_Page_Logic clickFirstSeparateCategoryMainCatalog() {
        firstSeparateCategoryInMainCatalog().shouldBe(visible).click();
        return page(Listing_chemicals_Page_Logic.class);
    }


    @Step("Get name all categories in logical union and add in list. Index_chemicals_page")
    public ArrayList getNameAllCategoriesInLogicalUnionAndAddToList() {
        catalogFirstGroup().scrollTo().hover();
        secondCategoryInLogicalUnion().shouldBe(visible);
        ArrayList <String> nameCategories = new ArrayList<>();
        for(SelenideElement element : categoriesInLogicalUnion()) {
            String name = element.getText();
            nameCategories.add(name);
        }
        return nameCategories;
    }

    @Step("Get name all separate categories in main catalog and add in list. Index_chemicals_page")
    public ArrayList getNameAllSeparateCategoriesInMainCatalogAndAddToList() {
        ArrayList <String> nameSeparateCategories = new ArrayList<>();
        for(SelenideElement element : separateCategoriesInMainCatalog()) {
            String name = element.getText();
            nameSeparateCategories.add(name);
            Collections.sort(nameSeparateCategories);
        }
        return nameSeparateCategories;
    }


}
