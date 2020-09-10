package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Services_wishList_page_Logic extends Services_wishList_page {

    @Step("presence of product list.   Services_wishList_page")
    public Services_wishList_page_Logic presenceOfProductList() {
        productList().shouldBe(visible);
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
}
