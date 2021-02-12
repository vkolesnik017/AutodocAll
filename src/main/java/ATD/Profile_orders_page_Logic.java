package ATD;

import AWS.Order_aws;
import Common.DataBase;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.ArrayList;

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

    @Step("Checks presence delivery status block. Profile_orders_page")
    public Profile_orders_page_Logic checkPresenceDeliveryStatusBlock() {
        deliveryStatusBlock().shouldBe(visible);
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
}
