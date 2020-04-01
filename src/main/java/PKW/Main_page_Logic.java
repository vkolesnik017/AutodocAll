package PKW;

import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.page;

public class Main_page_Logic extends Main_page{

    @Step("Cart clicking. Main_page")
    public PKW.Cart_page cartClick() {
        cartIcon().click();
        return page(PKW.Cart_page.class);
    }
}
