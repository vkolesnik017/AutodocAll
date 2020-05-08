package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Cart_page_mob {

    SelenideElement idOfAddedProduct() {
        return $x("//div[@class='prod_item ']");
    }

    SelenideElement deleteProductBtn() {
        return $x("//a[@class='delete delBtn']");
    }

    SelenideElement deleteConfirmationPopUp() {
        return $(".delete-popup");
    }

    SelenideElement deleteConfirmationBtn() {
        return $x("//body/div[@id='wrapper']/div[@id='popup']/div/div/a[2]");
    }

    SelenideElement emptyCart() {
        return $x("//div[@class='empty_cart']");
    }
}
