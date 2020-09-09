package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.refresh;

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
        List<String> artNumInWishlist = artNumOfProduct().stream().map(n->n.getText().replaceAll("Artikelnummer: ","")).collect(Collectors.toList());
        Collections.reverse(artNumInWishlist);
        Assert.assertEquals(listOfArtNum, artNumInWishlist);
        return this;
    }

    @Step("remove product from WishList. Services_wishList_page")
    public Services_wishList_page_Logic removeProductFromWishList(int position) {
        btnRemoveProduct().get(position).click();
        dialogPopUp().shouldBe(visible);
        return this;
    }
}
