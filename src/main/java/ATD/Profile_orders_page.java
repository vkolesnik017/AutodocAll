package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Profile_orders_page {

    SelenideElement orderBonusSum() {
        return $x("//td[@class='order-bonus']");
    }

    SelenideElement detailsOrderBtn() {
        return $x("//td[@class='order-data']/a");
    }

    SelenideElement orderBonus() {
        return $x("//div[@class='order-details__bonus']");
    }

    SelenideElement exitOrderDetailsBtn() {
        return $x("//div[@class='back-page']/a");
    }
}
