package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
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
}
