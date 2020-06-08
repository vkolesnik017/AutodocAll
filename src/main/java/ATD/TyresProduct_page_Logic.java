package ATD;


import io.qameta.allure.Step;

import java.sql.SQLException;

import static ATD.CommonMethods.waitingWhileLinkBecomeExpected;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.switchTo;

public class TyresProduct_page_Logic extends TyresProduct_page {

    @Step("Click delivery link and check redirect. TyresProduct_page")
    public TyresProduct_page_Logic clickDeliveryLinkAndCheckRedirect() throws SQLException {
        deliveryLink().click();
        switchTo().window(1);
        waitingWhileLinkBecomeExpected(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "staticVersand"));
        return this;
    }

    @Step("Check product price visibility. TyresProduct_page")
    public TyresProduct_page_Logic checkProductPriceVisibility() {
        productPrice().shouldBe(visible);
        return this;
    }

    @Step("Check product price visibility. TyresProduct_page")
    public TyresProduct_page_Logic checkDeliveryLinkVisibility() {
        deliveryLink().shouldBe(visible);
        return this;
    }

    @Step("Check FAQ block visibility. TyresProduct_page")
    public TyresProduct_page_Logic checkFAQblockVisibility() {
        faqBlock().shouldBe(visible);
        return this;
    }

    @Step("Check rating block visibility. TyresProduct_page")
    public TyresProduct_page_Logic checkRatingBlockVisibility() {
        ratingBlock().shouldBe(visible);
        return this;
    }

    @Step("Check payment methods block visibility. TyresProduct_page")
    public TyresProduct_page_Logic checkPaymentMethodsBlockVisibility() {
        paymentMethodsBlock().shouldBe(visible);
        return this;
    }

    @Step("Check delivery methods block visibility. TyresProduct_page")
    public TyresProduct_page_Logic checkDeliveryMethodsBlockVisibility() {
        deliveryMethodsBlock().shouldBe(visible);
        return this;
    }
}
