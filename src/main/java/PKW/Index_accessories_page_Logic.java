package PKW;


import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.Collections;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class Index_accessories_page_Logic extends Index_accessories_page {


    @Step("Checking presence title name on main page. Index_accessories_page")
    public Index_accessories_page_Logic checkingPresenceTitleNamePage() {
        Assert.assertFalse(titleNamePage().text().isEmpty());
        return this;
    }


    @Step("Checking presence block Top brands. Index_accessories_page")
    public Index_accessories_page_Logic checkingPresenceBlockTopBrands() {
        blockTopBrands().shouldBe(visible);
        return this;
    }

    @Step("Get name first brand in block Top brands. Index_accessories_page")
    public String getNameFirstBrandInBlockTopBrands() {
        return firstBrandInBlockTopBrands().getText();
    }

    @Step("Click on first brand in block top brands. Index_accessories_page")
    public Supplier_page_Logic clickOnFirstBrandInBlockTopBrands() {
        firstBrandInBlockTopBrands().click();
        return page(Supplier_page_Logic.class);
    }


    @Step("Checking open popup with details after hover on first product in block top products . Index_accessories_page")
    public Index_accessories_page_Logic checkingPopupWithDetailsInBlockTopProducts() {
        firstProductInBlockTopProducts().scrollTo().hover();
        btnDetailsInPopupBlockTopProducts().shouldBe(visible);
        return this;
    }

    @Step("Checking presence block with breadCrumbs. Index_accessories_page")
    public Index_accessories_page_Logic checkingPresenceBlockBreadCrumbs(){
        blockBreadCrumbs().shouldBe(visible);
        return this;
    }

    @Step("Checking presence and not clickable second Bread Crumb. Index_accessories_page")
    public Index_accessories_page_Logic checkingPresenceAndNotClickableSecondBreadCrumb() {
        secondBreadCrumb().shouldBe(visible).shouldNotBe(attribute("href"));
        return this;
    }

    @Step("Click on first bread crumb. Index_accessories_page")
    public Parts_page_Logic clickOnFirstBreadCrumb() {
        firstBreadCrumb().click();
        return page(Parts_page_Logic.class);
    }

    @Step("Checking presence block Top Products. Index_accessories_page")
    public Index_accessories_page_Logic checkingPresenceTopProductsBlock() {
        blockTopProducts().scrollTo().shouldBe(visible);
        return this;
    }

    @Step("Checking work btn Previous and Next in block Top Products . Index_accessories_page")
    public Index_accessories_page_Logic checkingWorkBtnPrevAndNextInTopProductsBlock() {
        firstProductInBlockTopProducts().shouldBe(visible);
        btnNextInBlockTopProducts().click();
        sleep(2000);
        firstProductInBlockTopProducts().shouldNotBe(visible);
        btnPreviousInBlockTopProducts().click();
        firstProductInBlockTopProducts().shouldBe(visible);
        return this;
    }

    @Step("Checking quantity blocks with categories in main catalog . Index_accessories_page")
    public Index_accessories_page_Logic checkingQuantityBlocksWithCategoriesInMainCatalog() {
        blocksCategoriesInMainCatalog().shouldHaveSize(16);
        return this;
    }

    @Step("Get name first category from first block categories in main catalog.Index_accessories_page")
    public String getNameFirstCategoryFromFirstBlockInMainCatalog() {
        return firstCategoryInBlockCategoriesMainCatalog().getText();
    }

    @Step("Click on first category from first block categories in main catalog.Index_accessories_page")
    public Listing_accessories_page_Logic clickOnFirstCategoryInBlockCategoriesMainCatalog() {
        firstCategoryInBlockCategoriesMainCatalog().click();
        return page(Listing_accessories_page_Logic.class);
    }

    @Step("Get name first product in block top products. Index_accessories_page")
    public String getNameFirstProductInBlockTopProducts() {
        return nameFirstProductInBlockTopProducts().getText();
    }

    @Step("Click on first btn add to basket in block top products. Index_accessories_page")
    public Index_accessories_page_Logic clickOnFirstBtnAddProductToBasketInBlockTopProducts() {
        firstBtnAddProductToBasketInBlockTopProducts().click();
        popupBasketAddedProducts().waitUntil(attribute("style","visibility: visible; opacity: 1;"), 10000);
        return this;
    }

    @Step(":from Index_accessories_page" )
    public Cart_page_Logic clickBtnGoToBasket() {
        new Main_page_Logic().cartClick();
        return page(Cart_page_Logic.class);
    }

    @Step("Checking presence Seo text description block. Index_accessories_page")
    public Index_accessories_page_Logic checkingPresenceBlockWithSeoText() {
        Assert.assertFalse(blockWithSeoTextDescription().text().isEmpty());
        return this;
    }

    @Step("Checking presence top categories block. Index_accessories_page")
    public Index_accessories_page_Logic checkingPresenceTopCategoriesBlock() {
        blockTopCategories().scrollTo().shouldBe(visible);
        return this;
    }

    @Step("Checking quantity categories from Top categories block . Index_accessories_page")
    public Index_accessories_page_Logic checkingQuantityCategoriesFromTopCategoriesBlock() {
        categoriesFromTopCategoriesBlock().shouldHaveSize(12);
        return this;
    }

    @Step("Get URL product from top categories block. Index_accessories_page")
    public String getURLProductFromTopProductsBlock() {
       return categoryFromTopCategoriesBlock().getAttribute("href");
    }

    @Step("Click on product from top categories block. Index_accessories_page")
    public Listing_accessories_page_Logic clickOnProductFromTopCategoriesBlock() {
        categoryFromTopCategoriesBlock().click();
        return page(Listing_accessories_page_Logic.class);
    }

    @Step("Get id all categories from logical union then write to list. Index_accessories_page")
    public ArrayList<String> getIdCategoriesAndThenWriteToList() {
        blockMainProducts().scrollIntoView(false);
        ArrayList<String> categoriesFromLogicalUnion = new ArrayList<>();
        for (SelenideElement element : categoriesFromLogicalUnion()) {
            String idCategory = element.getAttribute("data-ga-action");
            categoriesFromLogicalUnion.add(idCategory);
        }
        Collections.sort(categoriesFromLogicalUnion);
        return categoriesFromLogicalUnion;
    }

    @Step("Get id Logical Unions and write to list. Index_accessories_page")
    public ArrayList<String> getIdLogicalUnionAndWriteToList() {
        ArrayList<String> logicalUnionsId = new ArrayList<>();
        for (SelenideElement element:logicalUnions()) {
            String idLogicalUnion = element.getAttribute("data-srcset").replaceAll("[\\s\\S]*\\/", "").replaceAll("\\D+", "");
            logicalUnionsId.add(idLogicalUnion);
        }
        Collections.sort(logicalUnionsId);
        return logicalUnionsId;
    }



}
