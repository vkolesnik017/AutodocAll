package ATD;

import AWS.Order_aws;
import Common.DataBase;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.ArrayList;

import static Common.CommonMethods.waitWhileRouteBecomeExpected;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Profile_orders_page_Logic extends Profile_orders_page {

    @Step("Get bonus order sum. Profile_orders_page")
    public String getBonusOrderSum() {
        return String.valueOf(orderBonusSum().getText());
    }

    @Step("Click details order button. Profile_orders_page")
    public Profile_orders_page_Logic clickDetailsOrderBtn() {
        detailsOrderBtn().shouldBe(visible).click();
        return this;
    }

    @Step("Checks absence order bonus. Profile_orders_page")
    public Profile_orders_page_Logic checkAbsenceOrderBonus() {
        orderBonusInDetails().shouldNotBe(visible);
        return this;
    }

    @Step("Exit order details back to order history. Profile_orders_page")
    public Profile_orders_page_Logic clickExitOrderDetailsBtn() {
        exitOrderDetailsBtn().click();
        return this;
    }

    @Step("Checks presence status order block. Profile_orders_page")
    public Profile_orders_page_Logic checkPresenceStatusOrderBlock() {
        statusOrderBlock().shouldBe(visible);
        return this;
    }

    @Step("Checks the number of delivery services added. Profile_orders_page")
    public Profile_orders_page_Logic checkNumberDeliveryServiceAdded(int expectedSize) {
        trackingNumber().shouldHaveSize(expectedSize);
        return this;
    }

    @Step("Checks transition to delivery page and check tracking number. Profile_orders_page")
    public Profile_orders_page_Logic checkTransitionToDeliveryPage() {
        for (int i = 0; i < trackingNumber().size(); i++) {
            String tracingNum = trackingNumber().get(i).getText();
            trackingNumber().get(i).click();
            switchTo().window(1);
            String numInDeliveryPage = url().replaceAll(".+=([0-9]{2,}).*", "$1");
            closeWindow();
            switchTo().window(0);
            Assert.assertEquals(tracingNum, numInDeliveryPage);
        }
        return this;
    }

    @Step("Checks the number of delivery services added from tooltip. Profile_orders_page")
    public Profile_orders_page_Logic checkNumberDeliveryServiceAddedFromTooltip(int expectedSize) {
        trackingTooltip().click();
        trackingNumFromTooltip().shouldHaveSize(expectedSize);
        return this;
    }

    @Step("Checks transition to delivery page and check tracking number from tooltip. Profile_orders_page")
    public Profile_orders_page_Logic trackingNumInDeliveryPageFromTooltip() {
        for (int i = 0; i < trackingNumFromTooltip().size(); i++) {
            String trackingNum = trackingNumFromTooltip().get(i).getText();
            trackingNumFromTooltip().get(i).click();
            switchTo().window(1);
            String numInDeliveryPage = url().replaceAll(".+=([0-9]{2,}).*", "$1");
            closeWindow();
            switchTo().window(0);
            Assert.assertEquals(trackingNum, numInDeliveryPage);
        }
        return this;
    }

    @Step("Transition to delivery page and get URL. Profile_orders_page")
    public String transitionToDeliveryPageAndGetURL(String deliveryService) {
        String deliveryPageURL = null;
        if (deliveryService.equals("DHL")) {
            oneTrackingNumber().click();
            switchTo().window(1);
            closeWindow();
            switchTo().window(0);
            oneTrackingNumber().click();
            switchTo().window(1);
            deliveryPageURL = url().replaceAll("&rfn.+","").replaceAll("html?.+.=", "");
            closeWindow();
            switchTo().window(0);
        } else {
            oneTrackingNumber().click();
            switchTo().window(1);

            deliveryPageURL = url();
            closeWindow();
            switchTo().window(0);
        }
        return deliveryPageURL;
    }

    @Step("Transition to delivery page and get tracking number from url. Profile_orders_page")
    public ArrayList<String> transitionToDeliveryPageAndGetTrackingNumFromURL() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < trackingNumber().size(); i++) {
            trackingNumber().get(i).click();
            switchTo().window(1);
            String number = url().replaceAll(".+=([0-9]{2,}).*", "$1");
            list.add(number);
            closeWindow();
            switchTo().window(0);
        }
        return list;
    }

    @Step("Get tracking number. Profile_orders_page")
    public String getTrackingNum() {
        return String.valueOf(oneTrackingNumber().getText());
    }

    @Step("Checks order history and clears it in case if there is an order in order history. Profile_orders_page")
    public Order_aws checkOrderHistoryAndClearIt(String expectedStatus) {
        if (historyOrderBlock().isDisplayed()) {
            String orderNumber = orderNumber().getText();
            new Order_aws(orderNumber).openOrderInAwsWithLogin()
                    .reSaveOrder()
                    .checkCurrentStatusInOrder(expectedStatus);
        }
        return page(Order_aws.class);
    }

    @Step("Check presence history order block. Profile_orders_page")
    public Profile_orders_page_Logic checkPresenceHistoryOrderBlock() {
        historyOrderBlock().shouldBe(visible);
        return this;
    }

    @Step("Checks the translation of Bonus Labels. Profile_orders_page")
    public Profile_orders_page_Logic checkTranslationBonusLabels(String shop) throws SQLException {
        String actualTranslation = bonusLabel().getText();
        String expectedTranslation = new DataBase("ATD").getTranslate("bonus_translate", shop, "bonus");
        Assert.assertEquals(actualTranslation, expectedTranslation);
        return this;
    }

    @Step("Checks the translation of Bonus tooltip. Profile_orders_page")
    public Profile_orders_page_Logic checkTranslationBonusTooltip(String shop) throws SQLException {
        bonusLabel().hover();
        String actualTranslationTitle = titleBonusTooltip().getText();
        String expectedTranslationTitle = new DataBase("ATD").getTranslate("bonus_translate", shop, "bonus_popup_title");
        Assert.assertEquals(actualTranslationTitle, expectedTranslationTitle);
        String actualTranslationText = textBonusTooltip().getText();
        String expectedTranslationText = new DataBase("ATD").getTranslate("bonus_translate", shop, "bonus_popup_text");
        Assert.assertEquals(actualTranslationText, expectedTranslationText);
        return this;
    }

    @Step("Get name tab My order. Profile_orders_page")
    public String getNameTabMyOrder() {
        return profileMyOrderBtn().getText();
    }

    @Step(":from. Profile_orders_page")
    public Main_page_Logic logOutClick() {
        new Main_page_Logic().logOutClick()
                .loginBtnInHeader().shouldBe(visible);
        return page(Main_page_Logic.class);
    }

    @Step("Check text {expectedText} in order status. Profile_orders_page")
    public Profile_orders_page_Logic checkTextInStatusOrder(String expectedText) {
        orderStatusText().shouldHave(text(expectedText));
        return this;
    }

    @Step("Check presence element {Element}. Profile_orders_page")
    public Profile_orders_page_Logic checkPresenceElement(SelenideElement element) {
        element.shouldBe(visible);
        return this;
    }

    @Step("Check absence element {Element}. Profile_orders_page")
    public Profile_orders_page_Logic checkAbsenceElement(SelenideElement element) {
        element.shouldNotBe(visible);
        return this;
    }

    @Step("Check id order {expectedOrderID} in order details block. Profile_orders_page")
    public Profile_orders_page_Logic checkIdInOrderDetails(String expectedOrderID) {
        orderIdInDetailsBlock().shouldHave(text(expectedOrderID));
        return this;
    }

    @Step("Checks a redirect to a product page by clicking on a product name or photo. Profile_orders_page")
    public Profile_orders_page_Logic checkRedirectByClickingOnProduct(SelenideElement element) {
        String artNum = orderDetailsProductArticle().getText().replaceAll("^.+\\s", "");
        element.click();
        switchTo().window(1);
        waitWhileRouteBecomeExpected("product");
        new Product_page_Logic().checkArtNumOfProduct(artNum);
        closeWindow();
        switchTo().window(0);
        return this;
    }

    @Step("Get total price in details product. Profile_orders_page")
    public float getTotalPrice() {
        String totalPrice = totalPriceInDetailsProduct().getText().replaceAll("[^0-9,]", "").replaceAll(",", ".");
        return Float.parseFloat(totalPrice);
    }

    @Step("Checks presence elements in details card. Profile_orders_page")
    public Profile_orders_page_Logic checkPresenceElementsInDetailsCard() {
        orderDetailsBlock().shouldBe(visible);
        orderIdInDetailsBlock().shouldBe();
        orderDetailsDate().shouldBe(visible);
        orderStatusText().shouldBe(visible);
        barStatusOrder().shouldBe(visible);
        orderDetailsList().shouldBe(visible);
        orderDetailsQty().shouldBe(visible);
        orderDetailsPrice().shouldBe(visible);
        orderDetailsLager().shouldBe(visible);
        reOrderBtn().shouldBe(visible);
        orderSummeryAddress().shouldBe(visible);
        paymentsMethod().shouldBe(visible);
        totalPriceInDetailsProduct().shouldBe(visible);
        return this;
    }

    @Step("Check presence elements in order card. Profile_orders_page")
    public Profile_orders_page_Logic checkPresenceElementsInOrderCard() {
        orderNum().shouldBe(visible);
        orderData().shouldBe(visible);
        orderQty().shouldBe(visible);
        orderSum().shouldBe(visible);
        orderBonus().shouldBe(visible);
        detailsOrderBtn().shouldBe(visible);
        orderStatusText().shouldBe(visible);
        orderStatusIcon().shouldBe(visible);
        barStatusOrder().shouldBe(visible);
        return this;
    }

    @Step("Click reorder btn and check added product in basket preview. Profile_orders_page")
    public Profile_orders_page_Logic clickReorderBtnAndCheckAddedProductInBasketPreview() {
        String artNum = orderDetailsProductArticle().getText().replaceAll("^.+\\s", "");
        reOrderBtn().shouldBe(visible).click();
        new Main_page_Logic().hoverOverPreviewBasketAndCheckArtItemNum(artNum);
        return this;
    }

    @Step("Check percentage {expectedPercentage} in status bar. Profile_orders_page")
    public Profile_orders_page_Logic checkBarStatusPercentage(String expectedPercentage) {
        barStatusPercentage(expectedPercentage).shouldBe(visible);
        return this;
    }

    @Step("Click open order tab. Profile_orders_page")
    public Profile_orders_page_Logic clickOpenOrderTab() {
        openOrdersTab().click();
        return this;
    }

    @Step("Click completed order tab. Profile_orders_page")
    public Profile_orders_page_Logic clickCompletedOrderTab() {
        completedOrdersTab().click();
        return this;
    }

}
