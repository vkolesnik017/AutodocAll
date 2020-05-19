package AWS;

import ATD.DataBase;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementShould;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.ArrayList;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class Order_aws {

    public Order_aws(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Order_aws() {}

    private String orderNumber;
    private String url = "https://aws.autodoc.de/order/view/";

    private SelenideElement totalPriceOrder() {
        return $x("//td[@class='inf_grandTotal']");
    }

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

    private SelenideElement fieldNameInBilling() {
        return $x("//input[@id='form_Order[rVorname]']");
    }

    private SelenideElement fieldSurnameInBilling() {
        return $x("//input[@id='form_Order[rName]']");
    }

    private SelenideElement fieldStreetInBilling() {
        return $x("//input[@id='form_Order[rStrasse]']");
    }

    private SelenideElement fieldHouseNumberInBilling() {
        return $x("//input[@id='form_Order[payment_house]']");
    }

    private SelenideElement fieldPostcodeInBilling() {
        return $x("//input[@id='form_Order[rPlz]']");
    }

    private SelenideElement fieldCityInBilling() {
        return $x("//input[@id='form_Order[rOrt]']");
    }

    private SelenideElement fieldCountryInBilling() {
        return $x("//select[@id='form_Order[payment_country_id]']");
    }

    private SelenideElement fieldMailInBilling() {
        return $x("//input[@id='form_Order[Email]']");
    }

    private SelenideElement fieldPhoneInBilling() {
        return $x("//input[@id='form_Order[rTelefon]']");
    }

    private SelenideElement fieldNameInDeliveryAddress() {
        return $x("//input[@id='form_Order[lVorname]']");
    }

    private SelenideElement fieldSurnameInDeliveryAddress() {
        return $x("//input[@id='form_Order[lName]']");
    }

    private SelenideElement fieldStreetInDeliveryAddress() {
        return $x("//input[@id='form_Order[lStrasse]']");
    }

    private SelenideElement fieldHouseNumberInDeliveryAddress() {
        return $x("//input[@id='form_Order[delivery_house]']");
    }

    private SelenideElement fieldPostcodeInDeliveryAddress() {
        return $x("//input[@id='form_Order[lPlz]']");
    }

    private SelenideElement fieldCityInDeliveryAddress() {
        return $x("//input[@id='form_Order[lOrt]']");
    }

    private SelenideElement fieldCountryInDeliveryAddress() {
        return $x("//select[@id='form_Order[delivery_country_id]']");
    }

    private SelenideElement fieldPhoneInDeliveryAddress() {
        return $x("//input[@id='form_Order[lTelefon]']");
    }

    private SelenideElement paymentMethod() {
        return $x("//select[@name='Order[PaymentID]']");
    }

    private SelenideElement contoNR() {
        return $x("//body//div[@id='contentwrapper']//div//div//div//div[1]//div[2]//div[14]//div[1]");
    }

    private SelenideElement deliveryCost() {
        return $(".inf_deliveryCost");
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

    SelenideElement vatPercentageInOrder() {
        return $x("//select[@id='form_Order[isVat]']");
    }

    SelenideElement sellingPrice() {
        return $x("//a[@class='payment-in-order']//abbr");
    }

    ElementsCollection sellingPriceOfAddedGoods() {
        return $$x("//a[@class='payment-in-order']//abbr");
    }

    private Order_aws checkWhatOrderOpened() {
        // Иногда, если заказ в AWS открыть сразу быстро после создания, он может не успеть подгрузися в AWS
        if ($("body").text().equals("Not found")) {
            refresh();
            phoneNumberField().shouldBe(visible);
        }
        return this;
    }

    public Order_aws openOrderInAwsWithLogin() {
        open(url + orderNumber);
        new Login_aws().loginInAws();
        checkWhatOrderOpened();
        checkOrderHasTestPhone();
        testIcon().shouldBe(visible);
        return this;
    }

    public Order_aws openOrderInAwsWithoutLogin() {
        open(url + orderNumber);
        checkOrderHasTestPhone();
        return this;
    }

    public Order_aws openOrderInAwsWithoutLoginAndCheckTestIcon() {
        open(url + orderNumber);
        checkOrderHasTestPhone();
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
        String valueOfTestStatus = "60";
        if (orderNumber != null) {
            open(url + orderNumber);
            if (!statusOrder().attr("data-status-id").equals(valueOfTestStatus)) {
                selectorOfStatuses().selectOptionByValue(valueOfTestStatus);
                saveChangesInOrderBtn().click();
                try { // Иногда приме смене статуса заказа, визуально он меняется, после повторного обновления страницы подргужается смененный статус, причина пока не установлена
                    statusOrder().waitUntil(attribute("data-status-id", valueOfTestStatus), 20000);
                } catch (ElementShould e) {
                    refresh();
                    statusOrder().shouldBe(attribute("data-status-id", valueOfTestStatus));
                }
                checkOrderHasTestStatus();
            }
        }
        return this;
    }

    @Step
    public Order_aws setStatusOrderToVersendetVorkasse() {
        selectorOfStatuses().selectOptionByValue("40");
        saveChangesInOrderBtn().click();
        try { // Иногда приме смене статуса заказа, визуально он меняется, после повторного обновления страницы подргужается смененный статус, причина пока не установлена
            statusOrder().waitUntil(attribute("data-status-id", "40"), 30000);
        } catch (ElementShould e) {
            refresh();
            statusOrder().waitUntil(attribute("data-status-id", "40"), 30000);
        }
        return this;
    }

    // locator and methods for block of delivery info (Versandinfo)

    private SelenideElement deliveryInfoRadioGLS() {
        return $x("//input[@value='GLS' and contains(@name,'Delivery[0]')]");
    }

    private SelenideElement deliveryInfoSendungsnummerField() {
        return $(byId("form_OrderDelivery[0][DeliveryNr]"));
    }

    private SelenideElement packageContentButton() {
        return $("[name='packageContent']");
    }

    @Step
    public Order_aws addDeliveryConditionGLS() {
        deliveryInfoRadioGLS().click();
        deliveryInfoSendungsnummerField().setValue("test");
        saveChangesInOrderBtn().click();
        packageContentButton().shouldBe(visible);
        return this;
    }

    // Block with Products

    private SelenideElement reclamationButton() {
        return $(".show-reclamation");
    }

    private SelenideElement sellingProductPrice() {
        return $x("//table[@id='table_order_products_list']//td[13]/a");
    }

    @Step
    public Float getSellingProductPrice() {
        return Float.valueOf(sellingProductPrice().attr("data-sum"));
    }

    private SelenideElement deliveryDeliveryPriceOrderAWS() {
        return $(".inf_deliveryCost > a");
    }

    private SelenideElement heavyLoadsDeliveryPriceOrderAWS() {
        return $(".inf_surcharge > a");
    }

    // locators and methods for Popup of reclamation, appears after click reclamation button
    private SelenideElement addNewReclamationButton() {
        return $(byId("addNewReclamation"));
    }

    private SelenideElement checkBoxProductInPopupOfAddedReclamation() {
        return $x("//input[@name='reclamationOrderProductId']");
    }

    private ElementsCollection causesReturnInSelect() {
        return $$x("//select[@name='causes']/optgroup/option");
    }

    private SelenideElement selectWithCausesReturn() {
        return $x("//select[@name='causes']");
    }

    private SelenideElement selectMountedOrNot() {
        return $x("//select[contains(@id,'reclamation-mounting')]");
    }

    private ElementsCollection optionsInSelectMountedOrNot() {
        return $$x("//select[contains(@id,'reclamation-mounting')]/option[position()>1]");
    }

    private SelenideElement formForMessage() {
        return $(byId("reclamation-comment"));
    }

    private SelenideElement saveButtonInPopupOfReturn() {
        return $(".btn-save");
    }

    private SelenideElement listWithReclamations() {
        return $(byId("statistic_list"));
    }

    private SelenideElement safeOrderSelector() {
        return $(byId("form_securityDeliveryStatusChange"));
    }

    private SelenideElement btnChangeOrderStatusInTest() {
        return $x("//button[@class='btn btn-info']");
    }

    private SelenideElement firmConfirmationSelector() {
        return $x("//select[@name='Order[umsatCheck]']");
    }

    private SelenideElement customerId() {
        return $x("//div[@class='data-text']//a[@target='_blank']");
    }


    @Step("Transition to the personal account of the customer. Order_aws")
    public Customer_view_aws clickCustomerId() {
        sleep(2000);
        customerId().click();
        return page(Customer_view_aws.class);
    }

    @Step("Re save order. Order_aws")
    public Order_aws reSaveOrder() {
        btnChangeOrderStatusInTest().scrollTo();
        btnChangeOrderStatusInTest().click();
        saveChangesInOrderBtn().click();
        sleep(2000);
        return this;
    }

    @Step
    public Order_aws openPopupOfAddReclamation() {
        executeJavaScript("window.scrollTo(0, document.body.scrollHeight)");
        reclamationButton().click();
        addNewReclamationButton().click();
        sleep(2000);
        checkBoxProductInPopupOfAddedReclamation().click();
        return this;
    }

    @Step("Checking translation of causes in popup of reclamation in aws. Order_aws")
    public Order_aws checkingTranslateOfCausesForReturn(String language) throws SQLException {
        ElementsCollection causes = causesReturnInSelect().shouldHaveSize(16);
        for (SelenideElement cause : causes) {
            String valueText = cause.getValue();
            String expectedText = new DataBase().getTranslate("retoure_translate_aws", language, valueText);
            cause.shouldHave(exactText(expectedText));
        }
        return this;
    }

    @Step
    public Order_aws chooseRandomCauseReturnInSelect() {
        ElementsCollection causes = causesReturnInSelect().shouldHave(sizeNotEqual(0));
        int randomCause = (int) (Math.random() * causes.size()) + 1;
        selectWithCausesReturn().selectOption(randomCause);
        sleep(2000);
        if (selectMountedOrNot().isDisplayed()) {
            ElementsCollection options = optionsInSelectMountedOrNot().shouldHave(sizeNotEqual(0));
            int randomOption = (int) (Math.random() * options.size()) + 1;
            selectMountedOrNot().selectOption(randomOption);
        }
        return this;
    }

    @Step
    public Order_aws fillInFormForMessageReture() {
        formForMessage().setValue("autotest");
        return this;
    }

    @Step
    public Order_aws clickSaveReclamationButton() {
        saveButtonInPopupOfReturn().click();
        listWithReclamations().waitUntil(appear, 40000);
        return this;
    }

    @Step("Check delivery price {expectedDeliveryPriceOrderAWS} in order AWS. Order_aws")
    public Order_aws checkDeliveryPriceOrderAWS(String expectedDeliveryPriceOrderAWS) {
        deliveryDeliveryPriceOrderAWS().shouldHave(attribute("data-sum", expectedDeliveryPriceOrderAWS));
        return this;
    }

    @Step("Check delivery price {expectedDeliveryPriceOrderAWS} in order AWS. Order_aws")
    public Order_aws checkDeliveryPriceOrderAWS(Double expectedDeliveryPriceOrderAWS) {
        deliveryDeliveryPriceOrderAWS().shouldHave(attribute("data-sum", String.valueOf(expectedDeliveryPriceOrderAWS)));
        return this;
    }

    @Step("Check Heavy Loads delivery price {expectedHeavyLoadsDeliveryPriceOrderAWS} in order AWS. Order_aws")
    public Order_aws checkHeavyLoadsDeliveryPriceOrderAWS(String expectedHeavyLoadsDeliveryPriceOrderAWS) {
        heavyLoadsDeliveryPriceOrderAWS().shouldHave(attribute("data-sum", expectedHeavyLoadsDeliveryPriceOrderAWS));
        return this;
    }

    @Step("Get total Price in Order AWS. Order_aws")
    public Float getTotalPriceOrderAWS() {
        String price = totalPriceOrder().getText();
        Float totalPriceOrder = Float.parseFloat(price);
        return totalPriceOrder;
    }

    @Step("Get selling price in Order AWS. Order_aws")
    public Float getSellingProductPriceOrderAWS() {
        String price = sellingPrice().getText();
        Float sellingPriceOrder = Float.parseFloat(price);
        return sellingPriceOrder;
    }

    @Step("Checks that Safe Order is turned off. Order_aws")
    public Order_aws checkThatStatusSafeOrderIsOff() {
        safeOrderSelector().shouldHave(text("Выключен"));
        return this;
    }

    @Step("Checks that Safe Order is turned On. Order_aws")
    public Order_aws checkThatStatusSafeOrderIsOn() {
        safeOrderSelector().shouldHave(text("Включен"));
        return this;
    }

    @Step("Checks VAT status {statusVatOrder} in order. Order_aws")
    public Order_aws checkVatStatusInOrder(String statusVatOrder) {
        vatPercentageInOrder().shouldHave(text(statusVatOrder));
        return this;
    }

    @Step("Checks payment method {PaymentMethod} in order. Order_aws")
    public Order_aws checkPaymentMethodInOrder(String PaymentMethod) {
        paymentMethod().shouldHave(text(PaymentMethod));
        return this;
    }

    @Step("Checks firm confirmation status in order {firmConfirmationStatus}. Order_aws")
    public Order_aws checkFirmConfirmationStatus(String firmConfirmationStatus) {
        firmConfirmationSelector().shouldHave(text(firmConfirmationStatus));
        return this;
    }

    @Step("Gets user data. Order_aws")
    public ArrayList getUserDataInOrder() {
        String nameText = fieldNameInBilling().getValue();
        String surnameText = fieldSurnameInBilling().getValue();
        String streetText = fieldStreetInBilling().getValue();
        String houseNumberText = fieldHouseNumberInBilling().getValue();
        String postcodeText = fieldPostcodeInBilling().getValue();
        String cityText = fieldCityInBilling().getValue();
        String countryText = fieldCountryInBilling().getText();
        String mailText = fieldMailInBilling().getValue();
        String phoneText = fieldPhoneInBilling().getValue().replaceAll("^\\d{2}", "");
        String nameDeliveryAddressText = fieldNameInDeliveryAddress().getValue();
        String surnameDeliveryAddressText = fieldSurnameInDeliveryAddress().getValue();
        String streetDeliveryAddressText = fieldStreetInDeliveryAddress().getValue();
        String houseDeliveryAddressNumberText = fieldHouseNumberInDeliveryAddress().getValue();
        String postcodeDeliveryAddressText = fieldPostcodeInDeliveryAddress().getValue();
        String cityDeliveryAddressText = fieldCityInDeliveryAddress().getValue();
        String countryDeliveryAddressText = fieldCountryInDeliveryAddress().getText();
        String phoneDeliveryAddressText = fieldPhoneInDeliveryAddress().getValue().replaceAll("^\\d{2}", "");
        ArrayList <String> userData = new ArrayList<>();
        userData.add(nameText);
        userData.add(surnameText);
        userData.add(streetText);
        userData.add(houseNumberText);
        userData.add(postcodeText);
        userData.add(cityText);
        userData.add(countryText);
        userData.add(mailText);
        userData.add(phoneText);
        userData.add(nameDeliveryAddressText);
        userData.add(surnameDeliveryAddressText);
        userData.add(streetDeliveryAddressText);
        userData.add(houseDeliveryAddressNumberText);
        userData.add(postcodeDeliveryAddressText);
        userData.add(cityDeliveryAddressText);
        userData.add(countryDeliveryAddressText);
        userData.add(phoneDeliveryAddressText);
        return userData;
    }

    @Step("Get total cost delivery amount {deliveryCost} and safe order {safeOrderCost}. Order_aws")
    public String getTotalCostDeliveryAmountAndSafeOrder(Float deliveryCost, Float safeOrderCost) {
        Float totalDeliveryAmountAndSafeOrder = deliveryCost + safeOrderCost;
        return String.valueOf((totalDeliveryAmountAndSafeOrder));
    }

    @Step("Get the total cost {sellingCost} including delivery {deliveryCost} and safe order {safeOrderCost}. Order_aws")
    public Float getTotalCostIncludingDeliveryAndSafeOrder(Float sellingCost, Float deliveryCost , Float safeOrderCost) {
        Float totalCost = sellingCost + deliveryCost + safeOrderCost;
        return totalCost;
    }

    @Step("Checks conto NR number {contoNR}. Order_aws")
    public Order_aws checkContoNR(String contoNR) {
        contoNR().shouldHave(text(contoNR));
        return this;
    }

    @Step("Checks delivery cost {deliveryCost}. Order_aws")
    public Order_aws checkDeliveryCost(String deliveryCost) {
        deliveryCost().shouldHave(text(deliveryCost));
        return this;
    }

    @Step("Compares the prices of added products with the prices on the site {priceWithSite}. Order_aws")
    public Order_aws comparePriceOfAddedProductWithPricesOnSites(ArrayList priceWithSite) {
        ArrayList <Float> list = new ArrayList<>();
        for (int i = 0; i < sellingPriceOfAddedGoods().size(); i++) {
            list.add(Float.parseFloat(sellingPriceOfAddedGoods().get(i).getText()));
        }
        Assert.assertEquals(list, priceWithSite);
        return this;
    }


    @Step("Get the total cost of all goods including delivery{deliveryCost} and safe order{safeOrderCost}. Order_aws")
    public Float getTotalCostOfAllGoodsAndDeliveryAndSafeOrder(Float deliveryCost, Float safeOrderCost) {
        String costDeliveryAndSafeOrder = getTotalCostDeliveryAmountAndSafeOrder(deliveryCost, safeOrderCost);
        Float realCostDeliveryAndSafeOrder = Float.parseFloat(costDeliveryAndSafeOrder);
        Float sumOfAllGoods = 0.0f;
        for (int i = 0; i < sellingPriceOfAddedGoods().size(); i++) {
            Float priceOfOneItem = Float.parseFloat(sellingPriceOfAddedGoods().get(i).getText());
            sumOfAllGoods = sumOfAllGoods + priceOfOneItem;
        }
        return (sumOfAllGoods + realCostDeliveryAndSafeOrder);
    }
}
