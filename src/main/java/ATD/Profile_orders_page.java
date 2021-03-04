package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Profile_orders_page {

    SelenideElement profileMyOrderBtn() {
        return $x("//a[@data-ga-action='Sidebar_MyOrders']");
    }

    SelenideElement orderBonusSum() {
        return $x("//td[@class='order-bonus']");
    }

    SelenideElement detailsOrderBtn() {
        return $x("//td[@class='order-data']/a");
    }

    SelenideElement orderBonusInDetails() {
        return $x("//div[@class='order-details__bonus']");
    }

    SelenideElement exitOrderDetailsBtn() {
        return $x("//div[@class='back-page']/a");
    }

    SelenideElement statusOrderBlock() {
        return $x("//div[@class='order-status']");
    }

    SelenideElement orderNum() {
        return $x("//td[@class='order-nr']");
    }

    SelenideElement orderData() {
        return $x("//td[@class='order-date']");
    }

    SelenideElement orderQty() {
        return $x("//td[@class='order-qty']");
    }

    SelenideElement orderSum() {
        return $x("//td[@class='order-summ']");
    }

    SelenideElement orderBonus() {
        return $x("//td[@class='order-bonus']");
    }

    SelenideElement orderStatusText() {
        return $x("//div[@class='order-status']/p");
    }

    SelenideElement orderStatusIcon() {
        return $x("//p[contains(@class,'icon-status')]");
    }

    public SelenideElement activeAllHistoryTab() {
        return $x("//div[@class='orders-history__tabs']/a[contains(@class, 'btn--all active')]");
    }

    public SelenideElement openOrdersTab() {
        return $x("//a[@data-ga-action='MyOrders_OpenOrders']");
    }

    public SelenideElement completedOrdersTab() {
        return $x("//a[@data-ga-action='MyOrders_CompletedOrders']");
    }

    public SelenideElement historyOrderBlock() {
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

    public SelenideElement orderDetailsBlock() {
        return $x("//div[@class='order-details']");
    }

    SelenideElement orderIdInDetailsBlock() {
        return $x("(//div[@class='order-details__order']/span)[1]");
    }

    SelenideElement orderDetailsDate() {
        return $x("//span[@class='order-details__date']");
    }

    SelenideElement barStatusOrder() {
        return $x("//div[@class='status-bar ']");
    }

    SelenideElement barStatusPercentage(String expectedPercentage) {
        return $x("//div[@class='status-bar ']/span[@style='width: " + expectedPercentage + "%']");
    }

    SelenideElement orderDetailsList() {
        return $x("//div[@class='order-details__list']");
    }

    SelenideElement orderDetailsQty() {
        return $x("//p[@class='order-details__qty']");
    }

    SelenideElement orderDetailsPrice() {
        return $x("//p[@class='order-details__price']");
    }

    SelenideElement orderDetailsLager() {
        return $x("//div[@class='order-details__lager']");
    }

    SelenideElement reOrderBtn() {
        return $x("//button[contains(@class,'repeat-order')]");
    }

    SelenideElement orderSummeryAddress() {
        return $x("//div[@class='order-summary__address']");
    }

    SelenideElement paymentsMethod() {
        return $x("//div[@class='order-summary__pays']");
    }

    public SelenideElement orderDetailsImg() {
        return $x("//div[@class='order-details__img']");
    }

    public SelenideElement orderDetailsProductName() {
        return $x("//p[@class='order-details__product']");
    }

    SelenideElement orderDetailsProductArticle() {
        return $x("//p[@class='order-details__article']");
    }

    SelenideElement totalPriceInDetailsProduct() {
        return $x("//div[contains(@class,'summary__row--total')]//span[2]");
    }
}
