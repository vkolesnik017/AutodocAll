package ATD;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;
import java.util.ArrayList;


public class Index_accessories_page_Logic extends Index_accessories_page {

    @Step("Checks the presence of a block with site features . Index_accessories_page")
    public Index_accessories_page_Logic checkingPresenceOfFeaturesBlock() {
        blockFeatures().shouldBe(visible);
        return this;
    }


    @Step("Checks the presence of the delivery service block. Index_accessories_page")
    public Index_accessories_page_Logic checkingPresenceOfDeliveryBlock() {
        blockDelivery().shouldBe(visible);
        return this;
    }

    @Step("Check presence of top brands block. Index_accessories_page" )
    public Index_accessories_page_Logic checkingPresenceOfTopBrandsBlock(){
        blockTopBrands().shouldBe(visible);
        return this;
    }

    @Step("Checks the presence of the top products block.Index_accessories_page")
    public Index_accessories_page_Logic checkingPresenceOfTopProductBlock() {
        blockTopProducts().shouldBe(visible);
        return this;
    }

    @Step("Open first group in logical union and get second category name. Index_accessories_page")
    public String getCategoryName() {
        catalogFirstGroup().hover();
        return secondCategoryInLogicalUnion().getText();
    }

    @Step("Click second category in a logical union. Index_accessories_page")
    public Listing_accessories_page_Logic clicksOnCategory() {
        secondCategoryInLogicalUnion().click();
        return page(Listing_accessories_page_Logic.class);
    }

    @Step("Checking presence main title page. Index_accessories_page")
    public Index_accessories_page_Logic checkPresenceMainTitle(){
        mainTitlePage().shouldBe(visible);
        return this;
    }

    @Step("Get name first Category in block top accessories.Index_accessories_page")
    public String getNameFirstCategoryInBlockTopAccessories(){
        return nameFirstCategoryBlockTopAccessories().getText();
    }


    @Step("Click first Category in block top accessories. Index_accessories_page")
    public Listing_accessories_page_Logic clickOnCategoryInBlockTopAccessories(){
        nameFirstCategoryBlockTopAccessories().click();
        return page(Listing_accessories_page_Logic.class);
    }

    @Step("Checking presence text subtitle on main page. Index_accessories_page")
    public Index_accessories_page_Logic checkingPresenceTextSubtitle() {
        Assert.assertFalse(textSubtitle().text().isEmpty());
        return this;
    }

    @Step("Checking presence of the top categories block.Index_accessories_page")
    public Index_accessories_page_Logic checkingPresenceOfTopCategoriesBlock() {
        blockTopCategories().shouldBe(visible);
        return this;
    }

    @Step("Checking the number of mini-cards in top-6 products block. Index_accessories_page")
    public Index_accessories_page_Logic checkingNumberOfMiniCardsTop6Products() {
        miniCardsInTop6ProductsBlock().shouldHave(size(6));
        return this;
    }

    @Step("Click on first button Add to basket in top-6 block. Index_accessories_page")
    public  Index_accessories_page_Logic clickOnFirstBtnAddToBasketInTop6Block(){
        firstBtnAddToBasketInTop6Block().click();
        sleep(2000);
        return this;
    }

    @Step("Get name first product in top-6 block. Index_accessories_page")
    public String getNameFirstProductInTop6Block() {
        return nameFirstProductInTop6Block().getText();
    }

    @Step(":from Index_accessories_page")
    public Cart_page_Logic clickOnBtnGoToBasket() {
        new Main_page_Logic().cartClick();
        return page(Cart_page_Logic.class);
    }

    @Step("Click on first product in top-6 block. Index_accessories_page")
    public Product_page_Logic clickOnFirstProductInTop6Block() {
        nameFirstProductInTop6Block().click();
        return page(Product_page_Logic.class);
    }

    @Step("Check presence categories after hover on Logical Union. Index_accessories_page")
    public Index_accessories_page_Logic checkLogicalUnion() {
        catalogFirstGroup().hover();
        sleep(2000);
        secondCategoryInLogicalUnion().shouldBe(visible);
        return this;
    }

    @Step("Checking the number of categories in each tab of the top accessories block. Index_accessories_page")
    public Index_accessories_page_Logic checkNumberOfCategoriesInEachTabBlockTopAcc() {
        firstTabInBlockTopAccessories().shouldBe(visible);
        categoriesInTabBlockTopAccessories().shouldHaveSize(4);
        secondTabInBlockTopAccessories().click();
        categoriesInTabBlockTopAccessories().shouldHaveSize(4);
        thirdTabInBlockTopAccessories().click();
        categoriesInTabBlockTopAccessories().shouldHaveSize(4);
        return this;
    }

    @Step("Checking hover popup in top 6 product block. Index_accessories_page")
    public Index_accessories_page_Logic checkingHoverPopupInTop6ProductBlock() {
        nameFirstProductInTop6Block().hover();
        btnDetailsInPopupTop6Block().shouldBe(visible);
        return this;
    }

    @Step("Get name all categories in logical union and add in list. Index_accessories_page")
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

    @Step("Checking presence block Advantages and information popup after hover on tab number 2-3. Index_accessories_page")
    public Index_accessories_page_Logic checkingPresenceAdvantagesBlockAndInformationPopup() {
        blockAdvantages().scrollTo().shouldBe(visible);
        firstTabAdvantages().shouldBe(visible);
        secondTabAdvantage().hover();
        popupSecondTabAdvantage().shouldBe(visible);
        thirdTabAdvantage().hover();
        popupThirdTabAdvantage().shouldBe(visible);
        return this;
    }

    @Step("Checking presence block with main catalog categories. Index_accessories_page")
    public Index_accessories_page_Logic checkingPresenceBlockMainCatalogCategories() {
        blockMainCatalogCategories().shouldBe(visible);
        return this;
    }

}
