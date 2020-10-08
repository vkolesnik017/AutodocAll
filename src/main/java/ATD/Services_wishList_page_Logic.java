package ATD;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Services_wishList_page_Logic extends Services_wishList_page {

    @Step("presence of product list.  Services_wishList_page")
    public Services_wishList_page_Logic presenceOfProductList() {
        productList().shouldBe(visible);
        return this;
    }

    @Step("check count of products in list.  Services_wishList_page")
    public Services_wishList_page_Logic checkCountOfProducts(int expectedCount) {
        listOfProducts().shouldHaveSize(expectedCount);
        return this;
    }

    @Step("get MPN number of product. Services_wishList_page")
    public String getMpnNumberOfProduct(int positionOfProduct) {
        String mpnNumber = mpnNumberOfProduct().get(positionOfProduct).getText().replace("MPN: ", "");
        return mpnNumber;
    }

    @Step("go to WishList page. Services_wishList_page")
    public Services_wishList_page_Logic increaseQuantityOfProduct(int numberOfIncrease) {
        productBlock().shouldBe(visible);
        int currentCountOfProduct = Integer.parseInt(countOfProduct().getAttribute("value"));
        for (int i = 0; i < numberOfIncrease; i++) {
            btnIncreaseOfProduct().shouldBe(visible).click();
            currentCountOfProduct += 2;
            visibleOfAllBlocksInsideBar();
            refresh();
            productBlock().shouldBe(visible);
        }
        countOfProduct().shouldHave(attribute("value", Integer.toString(currentCountOfProduct)));
        return this;
    }

    @Step("go to WishList page. Services_wishList_page")
    public Services_wishList_page_Logic visibleOfAllBlocksInsideBar() {
        for (int i = 0; i < blocksInSideBar().size(); i++) {
            blocksInSideBar().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("check Chronological order of products. Services_wishList_page")
    public Services_wishList_page_Logic checkChronologicalOrderOfProducts(List<String> listOfArtNum) {
        List<String> artNumInWishlist = artNumOfProduct().stream().map(n -> n.getText().replaceAll("Artikelnummer: ", "")).collect(Collectors.toList());
        Collections.reverse(artNumInWishlist);
        Assert.assertEquals(listOfArtNum, artNumInWishlist);
        return this;
    }

    @Step("remove product from WishList. Services_wishList_page")
    public Services_wishList_page_Logic removeProductFromWishList(int position) {
        String currentCountOfProduct = currentCountOfProductInWishList().getText();
        btnRemoveProduct().get(position).click();
        dialogPopUp().shouldBe(visible);
        btnRemoveInDialogPopUp().click();
        currentCountOfProductInWishList().shouldNotHave(exactText(currentCountOfProduct));
        back();
        return this;
    }

    @Step("check Chronological order of products. Services_wishList_page")
    public Services_wishList_page_Logic checkCOrderOfProductsAfterRemove(List<String> listOfArtNum, int position) {
        List<String> artNumInWishlist = artNumOfProduct().stream().map(n -> n.getText().replaceAll("Artikelnummer: ", "")).collect(Collectors.toList());
        Collections.reverse(listOfArtNum);
        listOfArtNum.remove(position);
        Assert.assertEquals(artNumInWishlist, listOfArtNum);
        return this;
    }

    @Step("check of close remove all products pop-Up. Services_wishList_page")
    public Services_wishList_page_Logic checkOfCloseRemoveAllProductsPopUp(List<String> listOfArtNum) {
        openAndCheckRemoveAllProductsPopUp();
        closeRemoveProductPopUpByClickOutside();
        checkChronologicalOrderOfProducts(listOfArtNum);
        openAndCheckRemoveAllProductsPopUp();
        closeRemoveProductPopUpByClickX();
        checkChronologicalOrderOfProducts(listOfArtNum);
        openAndCheckRemoveAllProductsPopUp();
        closeRemoveProductPopUpByClickOnClosePopUpButton();
        checkChronologicalOrderOfProducts(listOfArtNum);
        return this;
    }

    @Step("check of close remove all products pop-Up. Services_wishList_page")
    public Services_wishList_page_Logic openAndCheckRemoveAllProductsPopUp() {
        btnRemoveProductsPopUp().shouldBe(visible).click();
        dialogPopUp().shouldBe(visible);
        dialogTextOfRemoveAProductsPopUp().shouldBe(visible).shouldHave(text("Sind Sie sicher, dass Sie diese Artikel von Ihrer Wunschliste löschen möchten?"));
        btnCloseRemoveProductPopUp().shouldBe(visible).shouldHave(text("Schließen"));
        btnDeleteProductPopUp().shouldBe(visible).shouldHave(text("Artikel entfernen"));
        btnCloseXRemoveProductPopUp().shouldBe(visible);
        return this;
    }

    @Step("close Remove product pop-Up by clicking outside the popUp area. Services_wishList_page")
    public Services_wishList_page_Logic closeRemoveProductPopUpByClickOutside() {
        actions().dragAndDropBy(btnBuyAllProducts(), 100, 100).click().perform();
        return this;
    }

    @Step("close Remove product pop-Up by click on X. Services_wishList_page")
    public Services_wishList_page_Logic closeRemoveProductPopUpByClickX() {
        btnCloseXRemoveProductPopUp().shouldBe(visible).click();
        return this;
    }

    @Step("close Remove product pop-Up by click on Close pop-up button. Services_wishList_page")
    public Services_wishList_page_Logic closeRemoveProductPopUpByClickOnClosePopUpButton() {
        btnCloseRemoveProductPopUp().shouldBe(visible).click();
        return this;
    }

    @Step("click on 'Add product to basket' button. Services_wishList_page")
    public Services_wishList_page_Logic clickOnBtnAddProductToBasket() {
        btnBuyAllProducts().shouldBe(visible).click();
        dialogPopUp().shouldBe(visible);
        return this;
    }

    @Step("click on random element of pop-Up. Services_wishList_page")
    public Cart_page_Logic clickOnRandomElementOfPopUp() {
        List<SelenideElement> list = Arrays.asList(btnCloseXRemoveProductPopUp(), btnCloseRemoveProductPopUp());
        Random rand = new Random();
        int randomIndex = rand.nextInt(list.size());
        list.get(randomIndex).click();
        return page(Cart_page_Logic.class);
    }

    @Step("click on 'Show alternative products' button. Services_wishList_page")
    public Services_wishList_page_Logic clickOnShowAlternativeProducts(int positionOfProduct) {
        btnAlternativeProducts().get(positionOfProduct).shouldBe(visible).click();
        alternativeBlock().shouldBe(visible);
        return this;
    }

    @Step("hover on Alternative product. Services_wishList_page")
    public Services_wishList_page_Logic hoverOnAlternativeProduct(int positionOfProducts) {
        alternativeBlock().shouldBe(visible);
        imageOfAlternativeProducts().get(positionOfProducts).hover();
        descriptionPopUpOfAlternativeProduct().get(positionOfProducts).shouldBe(visible);
        return this;
    }

    @Step("get id of alternative product. Services_wishList_page")
    public String getIdOfAlternativeProduct(int positionOfProduct) {
        String idOfAddedProduct = btnAddAlternativeProductToBasket().get(positionOfProduct).shouldBe(visible).getAttribute("id");
        return idOfAddedProduct;
    }

    @Step("add Alternative product to basket. Services_wishList_page")
    public Cart_page_Logic addAlternativeProductToBasket(int positionOfProduct) {
        btnAddAlternativeProductToBasket().get(positionOfProduct).shouldBe(visible).click();
        basketDropMenu().should(appear);
        basketDropMenu().should(disappear);
        basket().click();
        return page(Cart_page_Logic.class);
    }

    @Step("check elements with not logged user. Services_wishList_page")
    public Services_wishList_page_Logic checkElementsWithNotLoggedUser() {
        infoText().shouldBe(visible);
        btnOpenCatalog().shouldBe(visible);
        activeWishListItemInSidebar().shouldBe(visible).shouldHave(cssValue("color", "rgba(251, 101, 29, 1)"));
        return this;
    }

    @Step("click on WishList item in Sidebar. Services_wishList_page")
    public Services_wishList_page_Logic clickOnWishListItemInSidebar() {
        activeWishListItemInSidebar().shouldBe(visible).click();
        loginPopUp().shouldBe(visible);
        return this;
    }

    @Step("close Login pop-Up. Services_wishList_page")
    public Services_wishList_page_Logic closeLoginPopUp() {
        if (btnCloseLoginPopUp().isDisplayed()) {
            btnCloseLoginPopUp().click();
        }
        loginPopUp().shouldNotBe(visible);
        return this;
    }

    @Step("go to Catalog. Services_wishList_page")
    public Categories_page_Logic goToCatalog() {
        btnOpenCatalog().shouldBe(visible).click();
        return page(Categories_page_Logic.class);
    }

    @Step("compare elements of product from Listing in WishList. Services_wishList_page")
    public Services_wishList_page_Logic compareElementsFromListingInWishList(int positionOfProduct, String urlOfBrandImage, String dataFromPriceBlock, List<String> list, String count) {
        imageOfBrandInListing().get(positionOfProduct).shouldBe(visible).shouldHave(attribute("data-srcset", urlOfBrandImage));
        priceBlock().get(positionOfProduct).shouldHave(exactText(dataFromPriceBlock.replace("Meiner Wunschliste hinzufügen", "")));
        List<String> characteristicsOfProduct = visibleCharacteristicsOfProducts(positionOfProduct + 1).stream().map(n -> n.getText()).collect(Collectors.toList());
        Assert.assertEquals(list, characteristicsOfProduct);
        amountQuantityOfProduct().get(positionOfProduct).shouldHave(attribute("value", count));
        return this;
    }

    @Step("presence of Authorization block. Services_wishList_page")
    public Services_wishList_page_Logic presenceOfAuthorizationBlock() {
        authorizationBlock().shouldBe(visible);
        infoTextOfAuthorizationBlock().shouldBe(visible).shouldNotBe(empty);
        btnCloseOfAuthorizationBlock().shouldBe(visible);
        btnRegistrationOfAuthorizationBlock().shouldBe(visible);
        return this;
    }

    @Step("close Authorization block. Services_wishList_page")
    public Services_wishList_page_Logic closeAuthorizationBlock() {
        btnCloseOfAuthorizationBlock().shouldBe(visible).click();
        btnRegistrationOfAuthorizationBlock().shouldNotBe(visible);
        return this;
    }

    @Step("remove all products from WishList. Services_wishList_page")
    public Services_wishList_page_Logic removeAllProductsFromWishList() {
        int countOfProducts = 0;
        while (btnRemoveProduct().get(0).isDisplayed()) {
            countOfProducts = btnRemoveProduct().size();
            btnRemoveProduct().get(0).click();
            dialogPopUp().shouldBe(visible);
            btnRemoveInDialogPopUp().click();
            btnRemoveProduct().shouldHaveSize(countOfProducts - 1);
        }
        listOfProducts().shouldHaveSize(0);
        return this;
    }

    @Step("presence of Empty WishList block. Services_wishList_page")
    public Services_wishList_page_Logic presenceOfEmptyWishListBlock() {
        emptyWishListBlock().shouldBe(visible);
        return this;
    }

}
