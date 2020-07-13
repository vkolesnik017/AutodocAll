package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
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

    SelenideElement deliveryStatusBlock() {
        return $x("//div[@class='order-status']");
    }

    SelenideElement historyOrderBlock() {
        return $x("//li[@class='history-order']");
    }

    SelenideElement orderNumber() {
        return $x("//td[@class='order-nr']");
    }

    ElementsCollection trackingNumber() {
        return $$x("//a[@data-ga-action='TrackingNumber_AllOrders']");
    }

    SelenideElement oneTrackingNumber() {
        return $x("//a[@data-ga-action='TrackingNumber_AllOrders']");
    }

    SelenideElement trackingTooltip() {
        return $x("//span[@class='js-order-status__numbers']");
    }

    ElementsCollection trackingNumFromTooltip() {
        return $$x("//span[@class='order-status__tracking-more']/ul/li");
    }

    SelenideElement bonusLabel() {
        return $x("//th[@class='order-bonus']");
    }

    SelenideElement titleBonusTooltip() {
        return $x("//div[@class='tooltip-bonus__title']");
    }

    SelenideElement textBonusTooltip() {
        return $x("//div[@class='tooltip-bonus__text']");
    }
}
