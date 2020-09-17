package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.refresh;

public class Profile_wishlist_page_Logic extends Profile_wishlist {


    @Step("go to WishList page. Profile_wishlist")
    public Profile_wishlist_page_Logic increaseQuantityOfProduct(int numberOfIncrease) {
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

    @Step("go to WishList page. Profile_wishlist")
    public Profile_wishlist_page_Logic visibleOfAllBlocksInsideBar() {
        for (int i = 0; i < blocksInSideBar().size(); i++) {
            blocksInSideBar().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("check elements with logged user. Profile_wishlist")
    public Profile_wishlist_page_Logic checkElementsWithLoggedUser() {
        infoText().shouldBe(visible);
        btnOpenCatalog().shouldBe(visible);
        idOfClient().shouldBe(visible).shouldNotBe(empty);
        nameOfClient().shouldBe(visible).shouldNotBe(empty);
        return this;
    }

    @Step("go to Catalog. Profile_wishlist")
    public Categories_page_Logic goToCatalog() {
        btnOpenCatalog().shouldBe(visible).click();
        return page(Categories_page_Logic.class);
    }
}
