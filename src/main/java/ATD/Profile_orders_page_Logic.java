package ATD;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;

public class Profile_orders_page_Logic extends Profile_orders_page {

    @Step("Get bonus order sum. Profile_orders_page")
    public String getBonusOrderSum() {
        return String.valueOf(orderBonusSum().getText());
    }

    @Step("Click details order button. Profile_orders_page")
    public Profile_orders_page_Logic clickDetailsOrderBtn() {
        detailsOrderBtn().click();
        return this;
    }

    @Step("Checks absence order bonus. Profile_orders_page")
    public Profile_orders_page_Logic checkAbsenceOrderBonus() {
        orderBonus().shouldNotBe(visible);
        return this;
    }

    @Step("Exit order details back to order history. Profile_orders_page")
    public Profile_orders_page_Logic clickExitOrderDetailsBtn() {
        exitOrderDetailsBtn().click();
        return this;
    }
}
