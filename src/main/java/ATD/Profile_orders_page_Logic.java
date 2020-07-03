package ATD;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

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
        SelenideElement trackingNumInDeliveryPage = $x("//span[@class='shipmentNumber']");
        for (int i = 0; i < trackingNumber().size(); i++) {
            String tracingNum = trackingNumber().get(i).getText();
            trackingNumber().get(i).click();
            switchTo().window(1);
            String numInDeliveryPage = trackingNumInDeliveryPage.getText();
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
        SelenideElement trackingNumInDeliveryPage = $x("//span[@class='shipmentNumber']");
        for (int i = 0; i < trackingNumFromTooltip().size(); i++) {
            String trackingNum = trackingNumFromTooltip().get(i).getText();
            trackingNumFromTooltip().get(i).click();
            switchTo().window(1);
            String numInDeliveryPage = trackingNumInDeliveryPage.getText();
            closeWindow();
            switchTo().window(0);
            Assert.assertEquals(trackingNum, numInDeliveryPage);
        }
        return this;
    }

    @Step("Transition to delivery page and get URL. Profile_orders_page")
    public String transitionToDeliveryPageAndGetURL() {
        oneTrackingNumber().click();
        switchTo().window(1);
        String deliveryPageURL = url();
        closeWindow();
        switchTo().window(0);
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
}
