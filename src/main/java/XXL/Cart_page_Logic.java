package XXL;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;

public class Cart_page_Logic extends Cart_page {

    @Step("Clicking next button. Cart_page")
    public CartAccount_page_Logic nextButtonClick() {
        sleep(2000);
        nextButton().click();
        return page(CartAccount_page_Logic.class);
    }
}
