package AWS;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class Order_aws {

    private String orderNumber;
    private String url = "https://aws.autodoc.de/order/view/";

    private SelenideElement phoneNumberField() {
        return $(byName("Order[rTelefon]"));
    }

    private SelenideElement pfandField() {
        return $(byId("Pfand"));
    }

    private SelenideElement testIcon() {
        return $("[data-hint='Test']");
    }

    private SelenideElement productQuantity() {
        return $(By.xpath("//*[@id='table_order_products_list']/tbody/tr/td[16]"));
    }

    private SelenideElement articleNumber() {
        return $(By.xpath("//table[@id='table_order_products_list']/tbody/tr/td[6]/a[1]"));
    }

    private SelenideElement saveChangesInOrderBtn() {
        return $x("//button[contains(@class,'submit-order')]");
    }

    // Adding product to order menu
    private SelenideElement addingBtn() {
        return $(By.xpath("//div[@class='dt_gal_actions']/a[@class='btn btn-success']"));
    }

    private SelenideElement articleNumberField() {
        return $(By.id("AddProduct[articleNo]"));
    }

    private SelenideElement countAddProductField() {
        return $(By.id("AddProduct[count]"));
    }

    private SelenideElement addingProductBtn() {
        return $(By.xpath("//a[@class='btn btn-success btn-add']"));
    }

    private SelenideElement errorPopup() {
        return $(By.xpath("//input[@id='AddProduct[count]']/../i"));
    }

    public Order_aws(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Order_aws openOrderInAwsWithLogin() {
        open(url + orderNumber);
        new Login_aws().loginInAws();
        checkOrderHasTestPhone();
        checkOrderHasTestStatus();
        testIcon().shouldBe(visible);
        return this;
    }

    public Order_aws checkQuantityOfProduct(int expectedQuantity) {
        int quantityOfProduct = Integer.parseInt(productQuantity().getText());
        Assert.assertEquals(quantityOfProduct, expectedQuantity);
        return this;
    }

    @Step("Method checks appearing of tooltip when we try add incorrect product quantity")
    public Order_aws checkTooltipByAddingIncorrectProductQuantity(String productQuantity) {
        String articleNumber = articleNumber().getText();
        addingBtn().scrollTo().click();
        articleNumberField().setValue(articleNumber);
        countAddProductField().setValue(productQuantity);
        addingProductBtn().click();
        errorPopup().shouldHave(attribute("data-hint", "Это парный продукт, количество должно быть чётным"));
        return this;
    }

    @Step
    public Order_aws checkOrderHasTestPhone() {
        phoneNumberField().shouldHave(value("+002"));
        testIcon().shouldBe(visible);
        return this;
    }

    @Step
    public Order_aws checkOrderHasExpectedPfandPrice(String pfandPrice) {
        pfandField().shouldHave(exactValue(pfandPrice));
        return this;
    }

    // locators and methods for block of status order (Status ändern)

    private SelenideElement selectorOfStatuses() {
        return $x("//select[@id='form_OrderStatus[newStatus]']");
    }

    private SelenideElement statusOrder() {
        return $("a.btn-link.btn-ajaxmode");
    }

    @Step
    public Order_aws checkOrderHasTestStatus() {
        statusOrder().shouldHave(text(": Testbestellungen"));
        return this;
    }

    @Step
    public Order_aws setStatusOrderToTestbestellungen() {
        if (!orderNumber.equals(null)) {
            open(url + orderNumber);
            if (!statusOrder().attr("data-status-id").equals("60")) {
                selectorOfStatuses().selectOptionByValue("60");
                saveChangesInOrderBtn().click();
                statusOrder().shouldHave(attribute("data-status-id", "60")).shouldHave(text("Testbestellungen"));
            }
            checkOrderHasTestStatus();
        }
        return this;
    }

    @Step
    public Order_aws setStatusOrderToVersendetVorkasse() {
        selectorOfStatuses().selectOptionByValue("3");
        saveChangesInOrderBtn().click();
        statusOrder().shouldHave(attribute("data-status-id", "3"));
        return this;
    }

    // locator and methods for block of delivery info (Versandinfo)

    private SelenideElement deliveryInfoRadioGLS() {
        return $x("//input[@value='GLS' and contains(@name,'Delivery[0]')]");
    }

    private SelenideElement deliveryInfoSendungsnummerField() {
        return $(byId("form_OrderDelivery[0][DeliveryNr]"));
    }

    private SelenideElement deliveryInforDeltiField() {
        return $(byId("form_OrderDelivery[0][DeltiId]"));
    }

    @Step
    public Order_aws addDeliveryConditionGLS() {
        deliveryInfoRadioGLS().click();
        deliveryInfoSendungsnummerField().setValue("test");
        deliveryInforDeltiField().setValue("test");
        saveChangesInOrderBtn().click();
        deliveryInforDeltiField().shouldBe(not(visible));
        return this;
    }




}
