package AWS;

import Common.DataBase;
import com.codeborne.selenide.*;
import com.codeborne.selenide.conditions.Or;
import com.codeborne.selenide.ex.ElementShould;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static Common.CommonMethods.roundOfTheCost;
import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class Order_aws {

    public Order_aws(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Order_aws() {
    }

    private String orderNumber;
    private String url = "https://aws.autodoc.de/order/view/";

    private SelenideElement orderID() {
        return $x("//div[@class='form-group']//strong[text()='ID Заказа']/..");
    }

    private SelenideElement filterHeader() {
        return $(".row-fluid");
    }

    private SelenideElement totalPriceOrder() {
        return $x("//td[@class='inf_grandTotal']");
    }

    private SelenideElement phoneNumberField() {
        return $(byName("Order[rTelefon]"));
    }

    private SelenideElement pfandFieldInPaymentAndDeliveryTermsBlock() {
        return $x("//input[@id='Pfand']");
    }

    private SelenideElement testIcon() {
        return $("[data-hint='Test']");
    }

    private SelenideElement iconIdInBlackList() {
        return $x("//div[@data-hint='ID in Blacklist']");
    }

    private SelenideElement blackListLabel() {
        return $x("//div[@class='form-group']//div[text()=' Blacklist']");
    }

    private SelenideElement infoOrderAccountInBlackList() {
        return $x("//span[normalize-space()='Account is in Blacklist']");
    }

    private SelenideElement productQuantity() {
        return $(By.xpath("//*[@id='table_order_products_list']/tbody/tr/td[16]"));
    }

    private SelenideElement articleNumber() {
        return $(By.xpath("//table[@id='table_order_products_list']/tbody/tr/td[6]/a[1]"));
    }

    private SelenideElement articleID() {
        return $x("//table[@id='table_order_products_list']/tbody/tr/td[5]/a[1]");
    }

    private SelenideElement saveChangesInOrderBtn() {
        return $x("//button[contains(@class,'submit-order')]");
    }

    private SelenideElement orderSearchTad() {
        return $x("(//ul[@id='mobile-nav']//li//a[contains(@href,'search-orders')])[3]");
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

    public SelenideElement fieldPostcodeInBilling() {
        return $x("//input[@id='form_Order[rPlz]']");
    }

    public SelenideElement errorIconInFieldPostalConForBilling() {
        return $x("//input[@id='form_Order[rPlz]']/..//i[contains(@class,'icon-error')]");
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

    public SelenideElement fieldPostcodeInDeliveryAddress() {
        return $x("//input[@id='form_Order[lPlz]']");
    }

    public SelenideElement errorIconInFieldPostalConForShipping() {
        return $x("//input[@id='form_Order[lPlz]']/..//i[contains(@class,'icon-error')]");
    }

    private SelenideElement fieldCityInDeliveryAddress() {
        return $x("//input[@id='form_Order[lOrt]']");
    }

    private SelenideElement fieldCountryInDeliveryAddress() {
        return $x("//select[@id='form_Order[delivery_country_id]']");
    }

    private SelenideElement deliveryCountrySelector(String country) {
        return $x("//select[@id='form_Order[delivery_country_id]']//option[text()='" + country + "']");
    }

    private SelenideElement fieldPhoneInDeliveryAddress() {
        return $x("//input[@id='form_Order[lTelefon]']");
    }

    private SelenideElement paymentMethod() {
        return $x("//select[@name='Order[PaymentID]']");
    }

    private SelenideElement contoNR() {
        return $x("//strong[contains(text(),'Conto NR')]/..");
    }

    private SelenideElement deliveryCost() {
        return $(".inf_deliveryCost");
    }

    // Adding product to order menu
    private SelenideElement addingBtn() {
        return $(By.xpath("//div[@class='dt_gal_actions']/a[@class='btn btn-success']"));
    }

    private SelenideElement popUpAddProduct() {
        return $x("//div[@id='addProduct']//div[@class='modal-dialog']");
    }

    private SelenideElement popUpBlackList() {
        return $x("//div[@class='modal-dialog']//*[contains(text(),'Blacklist')]");
    }

    private SelenideElement closePopUPBlackList() {
        return $x("//a[@class='btn btn-danger']");
    }

    private SelenideElement suppliersTableInPopUpAddProduct() {
        return $x("//table[contains(@class,'contains-suppliers')]");
    }

    private SelenideElement storageTableInPopUpAddProduct() {
        return $x("//table[contains(@class,'contains-storage')]");
    }

    private SelenideElement prodictListInPopUpAddProduct() {
        return $x("//table[@id='products_list_add']");
    }

    private SelenideElement productInPopUpAddProduct(String articlID) {
        return $x("//input[@value='" + articlID + "']");
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

    private SelenideElement errorPopupInTopRight() {
        return $x("//div[@class='sticky-queue top-right']");
    }

    SelenideElement vatPercentageInOrder() {
        return $x("//li[@class='search-choice']//span");
    }

    SelenideElement vatPercentageInOrder(String serialNumber) {
        return $x("(//li[@class='search-choice']//span)["+ serialNumber +"]");
    }

    SelenideElement sellingPrice() {
        return $x("//a[@class='payment-in-order']//abbr");
    }

    ElementsCollection sellingPriceOfAddedGoods() {
        return $$x("//a[@class='payment-in-order']//abbr");
    }

    private SelenideElement sellingPriceOfCertainProduct(String articleID) {
        return $x("//a[text()='" + articleID + "']/../..//td[14]//abbr");
    }

    private SelenideElement vatFromSellingPrice(String articleID) {
        return $x("//a[text()='" + articleID + "']/../..//td[14]/span[@class='inkl']");
    }

    private SelenideElement labelDangerOfCertainProduct(String artID) {
        return $x("//a[text()='" + artID + "']/..//span[@class='label label-danger']");
    }

    private SelenideElement pfandSumInAddedProductTabel(String articlNum) {
        return $x("//a[text()='" + articlNum + "']/../..//td[17]/a");
    }

    // locators and methods for block of status order (Status ändern)
    private SelenideElement selectorOfStatuses() {
        return $x("//select[@id='form_OrderStatus[newStatus]']");
    }

    private SelenideElement statusOrder() {
        return $("a.btn-link.btn-ajaxmode");
    }

    // locator and methods for block of delivery info (Versandinfo)
    private SelenideElement deliveryInfoSendungsnummerField() {
        return $(byId("form_OrderDelivery[0][DeliveryNr]"));
    }

    private SelenideElement packageContentButton() {
        return $("[name='packageContent']");
    }

    // Block with Products
    private SelenideElement reclamationButton() {
        return $(".show-reclamation");
    }

    private SelenideElement sellingProductPrice() {
        return $x("//table[@id='table_order_products_list']//td[13]/a");
    }

    private SelenideElement deliveryPriceOrderAWS() {
        return $(".inf_deliveryCost > a");
    }

    private SelenideElement deliveryPriceInPaymentAndDeliveryTermsBlock() {
        return $x("//input[@id='Order[DeliveryCost]']");
    }

    private SelenideElement heavyLoadsDeliveryInPaymentAndDeliveryTermsBlock() {
        return $x("//input[@id='surcharge']");
    }

    private SelenideElement heavyLoadsInAddedProductTabel(String articlNum) {
        return $x("//a[text()='" + articlNum + "']/../..//td[18]/a");
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

    private SelenideElement saveButtonInPopup() {
        return $(".btn-save");
    }

    private SelenideElement listWithReclamations() {
        return $(byId("statistic_list"));
    }

    private SelenideElement safeOrderSelector() {
        return $(byId("form_securityDeliveryStatusChange"));
    }

    private SelenideElement safeOrderPrice() {
        return $x("(//div[@class='form-group safe-order-block']//input)[1]");
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

    private SelenideElement btnAddedGoodsInOrder() {
        return $x("//a[@class='btn btn-success']//i");
    }

    private SelenideElement fieldArticleNumInPopUpAddProduct() {
        return $x("//input[@id='AddProduct[articleNo]']");
    }

    private SelenideElement btnAddedGoodsInPopUpAddProduct() {
        return $(".btn-add");
    }

    private SelenideElement tableWithPopUpAddProduct() {
        return $x("//table[@id='products_list_add']");
    }

    private SelenideElement tableOfWarehousesAndSuppliers() {
        return $x("//div[@id='products_list_add']");
    }

    private SelenideElement productArticleIDInPopUpAddProduct(String artID) {
        return $x("//input[@id='form_AddProduct[articleId]'][@value='" + artID + "']");
    }

    private ElementsCollection incomeWithoutVat() {
        return $$x("//tbody//input[@name='row_sel']/../..//td[21]");
    }

    private SelenideElement totalIncomeWithoutVat() {
        return $x("//span[@class='inf_profit']");
    }

    private SelenideElement discountMoney() {
        return $x("//td[@class='inf_discountMoney']");
    }

    private SelenideElement discountInPercent() {
        return $x("//b[@class='inf_discount']");
    }

    private ElementsCollection addedGoods() {
        return $$x("//input[@name='row_sel']/../..");
    }

    private SelenideElement countProducts() {
        return $(".inf_countProducts");
    }

    private SelenideElement checkboxOfAddedProduct(String artID) {
        return $x("//a[text()='" + artID + "']/../..//input[@name='row_sel']");
    }

    private SelenideElement btnRemoveProduct() {
        return $x("//i[@class='splashy-error_small']");
    }

    private SelenideElement removeProductPopUp() {
        return $x("//div[@id='removeProduct']");
    }

    private SelenideElement btnNoInRemoveProductPopUp() {
        return $x("//div[@id='removeProduct']//a[2]");
    }

    private SelenideElement btnYesInRemoveProductPopUp() {
        return $x("//div[@id='removeProduct']//a[1]");
    }

    private SelenideElement refundBtn() {
        return $(".printGu");
    }

    private SelenideElement productInRefundTable(String articleNum) {
        return $x("//input[@value='" + articleNum + "']");
    }

    private SelenideElement quantityProductInRefundTable() {
        return $x("//input[@id='form_RefundProducts[0][quantity]']");
    }

    private SelenideElement editItemBtn(String articleID) {
        return $x("//a[text()='" + articleID + "']/../..//a[@class='btn btn-default btn-sm edit-product']");
    }

    private SelenideElement popUpEditItem() {
        return $x("//div[@id='editProduct']");
    }

    private SelenideElement fieldEditQuantityInPopUpEditItem() {
        return $x("//div[@id='editProduct']//input[@id='AddProduct[count]']");
    }

    private SelenideElement columnProductQuantity() {
        return $x("//td[16]");
    }

    private SelenideElement expectedQuantityColumn() {
        return $x("//body//td[11][@class='show-requests']");
    }

    private SelenideElement columnSumProduct() {
        return $x("//td[19]");
    }

    private SelenideElement columnIncomeWithoutVat() {
        return $x("//td[21]");
    }

    private SelenideElement currentStatusInOrder() {
        return $x("//a[@class='btn btn-link btn-ajaxmode']");
    }

    private SelenideElement deliveryMethod(String numberDelivery) {
        return $x("//select[@name='OrderDelivery[" + numberDelivery + "][Carrier]']");
    }

    private SelenideElement fieldTrackingNumbers(String fieldTrackingNum) {
        return $x("//input[@name='OrderDelivery[" + fieldTrackingNum + "][DeliveryNr]']");
    }

    private SelenideElement fieldSavedTrackingNumber() {
        return $x("//input[@class='form-control size-140 delivery-nr']");
    }

    private ElementsCollection allFieldsSavedTrackingNumber() {
        return $$x("//input[@class='form-control size-140 delivery-nr']");
    }

    private SelenideElement addDeliveryInOrderBtn() {
        return $(".add-order-delivery");
    }

    private SelenideElement selectedStatus(String nameStatus) {
        return $x("//select[@class='order-new-status']//option[text()[contains(.,'" + nameStatus + "')]]");
    }

    private SelenideElement btnAutoBySearchFromTheSite() {
        return $x("//div[@class='dt_gal_actions']/a[@class='btn m-left10 btn-search-selection btn-default']");
    }

    private SelenideElement vinNumInPopupFromBtnAutoBySearchFromTheSite() {
        return $x("//table[@class='table table-bordered table-striped']//tr[1]/td[8]");
    }

    private SelenideElement vinNumGarageInPopupFromBtnAutoBySearchFromTheSite() {
        return $x("//table[@class='table table-bordered table-striped']//tr[1]/td[9]");
    }

    private SelenideElement closePopupFromBtnAutoBySearchFromTheSite() {
        return $x("//body/div[@id='searchSelection']/div[1]/div[1]/div[3]/a[1]");
    }

    public SelenideElement reorderNumber() {
        return $x("//div[@class='w-box']//div[contains(text(),'Пере-Заказы')]/..//a");
    }

    public SelenideElement parentOrderNumber() {
        return $x("//div[@class='w-box']//div[text()='Родительский заказ']/..//a");
    }

    public SelenideElement blocksParentAndReOrderNumber() {
        return $x("//div[@class='w-box']//div[contains(text(),'Пере-Заказы')]/../../div[@class='w-box']//div[text()='Родительский заказ']");
    }

    public SelenideElement transactionCodBlock() {
        return $x("(//div[@class='form-group order-transaction w-100-pc']/span/input)[2]");
    }

    private ElementsCollection carId() {
        return $$x("//table[@id='table_order_products_list']//td[29]");
    }

    private SelenideElement carIdForSuitableCar() {
        return $x("//table[@id='table_order_products_list']//tr[1]/td[29]");
    }

    private SelenideElement carIdForNotSuitableCar() {
        return $x("//table[@id='table_order_products_list']//tr[2]/td[29]/span");
    }

    private SelenideElement accountsBtn() {
        return $x("//button[contains(@class,'printBillPopup')]");
    }

    private SelenideElement vatInAccountPopUp() {
        return $x("//select[@name='Bill[tva]']");
    }

    private SelenideElement closePopUpBtnInAccountPopUp() {
        return $x("//div[@id='printBillPopup']//a[contains(@class,'btn-close')]");
    }

    private SelenideElement returnButton() {
        return $x("//button[@name='printGu']");
    }

    private SelenideElement typeSelectInReturnPopup() {
        return $x("//select[@name='Refund[type]']");
    }

    private SelenideElement checkBoxProductInPopupReturn() {
        return $x("//input[@class='reCalc']/..//input[contains(@id,'form_RefundProducts')]");
    }

    private SelenideElement checkBoxDeliveryInPopupReturn() {
        return $x("//input[@id='form_Refund[isVersand]']");
    }

    private SelenideElement printBtnInPopupReturn() {
        return $x("//a[@class='btn btn-primary btn-print']");
    }

    private SelenideElement closePopupReturn() {
        return $x("//div[@id='printGu']//a[@class='btn btn-default btn-close']");
    }

    private SelenideElement payLinkAmount() {
        return $x("//table[contains(@class,'list-paylinks')]/tbody//td[@class='paylink-amount']");
    }

    private SelenideElement declarationBtn() {
        return $x("//button[@name='loadDeclaration']");
    }

    private SelenideElement modalWindowDeclaration() {
        return $x("//div[@id='declaration']");
    }

    private SelenideElement checkboxSelectAllInModalDeclaration() {
        return $x("//table[@id='declaration-product']//input[@class='select-all']");
    }

    private SelenideElement printBtnInModalDeclaration() {
        return $x("//a[@class='btn btn-primary btn-print']");
    }

    private SelenideElement closeBtnModalWindowDeclaration() {
        return $x("//div[@id='declaration']//a[@class='btn btn-default btn-close']");
    }

    private SelenideElement closeBtnModalWindowAddProduct() {
        return $x("//div[@id='addProduct']//a[@class='btn btn-default btn-close']");
    }

    private SelenideElement fiscalCodeFieldForBilling() {
        return $x("//input[@id='form_Order[rFiscalCode]']");
    }

    private SelenideElement fiscalCodeFieldForShipping() {
        return $x("//input[@id='form_Order[lFiscalCode]']");
    }




    @Step ("Check presence text in Fiscal code field Billing block . Order_aws")
    public Order_aws checkPresenceTextInFiscalCodeFieldForBilling(boolean visibleText) {
        fiscalCodeFieldForBilling().shouldBe(visible);
        if (visibleText == true) {
            Assert.assertFalse(fiscalCodeFieldForBilling().getValue().isEmpty());
        }else if (visibleText == false) {
            Assert.assertTrue(fiscalCodeFieldForBilling().getValue().isEmpty());
        }
        return this;
    }

    @Step ("Check presence text in Fiscal code field Shipping block . Order_aws")
    public Order_aws checkPresenceTextInFiscalCodeFieldForShipping(boolean visibleText) {
        fiscalCodeFieldForShipping().shouldBe(visible);
        if (visibleText == true) {
            Assert.assertFalse(fiscalCodeFieldForShipping().getValue().isEmpty());
        }else if (visibleText == false) {
            Assert.assertTrue(fiscalCodeFieldForShipping().getValue().isEmpty());
        }
        return this;
    }

    @Step("Set value in Fiscal code field for Billing. Order_aws")
    public Order_aws setValueInFiscalCodeForBilling(String text) {
        fiscalCodeFieldForBilling().shouldBe(visible);
        fiscalCodeFieldForBilling().setValue(text);
        return this;
    }

    @Step("Set value in Fiscal code field for Shipping. Order_aws")
    public Order_aws setValueInFiscalCodeForShipping(String text) {
        fiscalCodeFieldForShipping().shouldBe(visible);
        fiscalCodeFieldForShipping().setValue(text);
        return this;
    }

    @Step("Clear Fiscal Code field for Billing. Order_aws")
    public Order_aws clearFiscalCodeFieldForBilling() {
        fiscalCodeFieldForBilling().shouldBe(visible).clear();
        return this;
    }

    @Step("Clear Fiscal Code field for Shipping. Order_aws")
    public Order_aws clearFiscalCodeFieldForShipping() {
        fiscalCodeFieldForShipping().shouldBe(visible).clear();
        return this;
    }


    @Step("Click button accounts. Order_aws")
    public Order_aws clickBtnAccount() {
        accountsBtn().click();
        return this;
    }

    @Step("Closes popup accounts. Order_aws")
    public Order_aws closePopupAccounts() {
        closePopUpBtnInAccountPopUp().click();
        return this;
    }

    @Step("Open popup accounts and check VAT {expectedText}. Order_aws")
    public Order_aws openPopUpAccountsAndCheckVat(String expectedText) {
        clickBtnAccount();
        vatInAccountPopUp().shouldHave(text(expectedText));
        return this;
    }

    @Step("Checking compliance the Car Id {carIdFromProduct} in the ordered goods. Order_aws")
    public Order_aws checkingComplianceCarIdInOrderedGoods(String carIdFromProduct) {
        columnProductQuantity().scrollIntoView(true);
        for (int i = 0; i < carId().size(); i++) {
            carId().get(i).shouldHave(exactText(carIdFromProduct));
        }
        return this;
    }

    @Step("Checks by color that the first product fits the car and the second one does not. Order_aws")
    public Order_aws checksByCarIdThatProductsFitsCar() {
        carIdForSuitableCar().shouldHave(cssValue("color", "rgba(34, 34, 34, 1)"));
        carIdForNotSuitableCar().shouldHave(cssValue("color", "rgba(255, 0, 0, 1)"));
        return this;
    }

    @Step("get VIN num in popup from btn Auto By Search From The Site. Order_aws")
    public Order_aws getVinNumInPopupFromBtnAutoBySearchFromTheSite(String vinNum) {
        vinNumInPopupFromBtnAutoBySearchFromTheSite().shouldHave(text(vinNum));
        closePopupFromBtnAutoBySearchFromTheSite().shouldBe(visible).click();
        return this;
    }

    @Step("Click btn Auto By Search From The Site. Order_aws")
    public Order_aws clickBtnAutoBySearchFromTheSite() {
        btnAutoBySearchFromTheSite().scrollIntoView(true).shouldBe(visible).click();
        return this;
    }

    @Step("Get list saved tracking number. Order_aws")
    public ArrayList<String> getListSavedTrackingNumber() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < allFieldsSavedTrackingNumber().size(); i++) {
            list.add(allFieldsSavedTrackingNumber().get(i).getAttribute("value"));
        }
        return list;
    }

    @Step("Get saved tracking number. Order_aws")
    public String getSavedTrackingNumber() {
        return String.valueOf(fieldSavedTrackingNumber().getAttribute("value"));
    }

    @Step("Selects status {nameStatus} order. Order_aws")
    public Order_aws selectStatusOrder(String nameStatus) {
        selectedStatus(nameStatus).click();
        return this;
    }

    @Step("Selects delivery service {methodDelivery}, {numberDelivery} and enters a tracking number {fieldTrackingNum}. Order_aws")
    public Order_aws selectDeliveryAndEnterTrackingNum(String numberDelivery, String expectedDelivery, String fieldTrackingNum, String trackingNum) {
        deliveryMethod(numberDelivery).selectOptionContainingText(expectedDelivery);
        fieldTrackingNumbers(fieldTrackingNum).sendKeys(trackingNum);
        addDeliveryInOrderBtn().click();
        return this;
    }

    @Step("Click button added delivery in order. Order_aws")
    public Order_aws clickBtnAddedDeliveryInOrderBtn() {
        addDeliveryInOrderBtn().click();
        return this;
    }

    @Step("Checks current status {expectedStatus} in order. Order_aws")
    public Order_aws checkCurrentStatusInOrder(String expectedStatus) {
        currentStatusInOrder().shouldBe(visible);
        if (currentStatusInOrder().text().contains(expectedStatus)) {
            System.out.println("Status is order as expected");
        }
        else {
            Assert.fail("Status is order is not as expected");
        }
        return this;
    }

    @Step("Get order ID of order. Order_aws")
    public String getOrderIdOfOrder() {
        return orderID().getText().substring(10);
    }

    @Step("Checks the quantity of goods {expectedQuantity} in column count products. Order_aws")
    public Order_aws checkQuantityOfGoodsInColumnCountProduct(String expectedQuantity) {
        countProducts().shouldBe(visible);
        countProducts().shouldHave(text(expectedQuantity));
        return this;
    }

    @Step("Checks the quantity of goods {expectedQuantity} in column expected quantity column. Order_aws")
    public Order_aws checkQuantityOfGoodsInColumnExpectedQuantity(String expectedQuantity) {
        expectedQuantityColumn().shouldBe(visible);
        expectedQuantityColumn().shouldHave(text(expectedQuantity));
        return this;
    }

    @Step("Checks the quantity of goods {expectedQuantity} in column Quantity product. Order_aws")
    public Order_aws checkQuantityOfGoodsInColumnQuantity(String expectedQuantity) {
        columnProductQuantity().shouldBe(visible);
        columnProductQuantity().shouldHave(text(expectedQuantity));
        return this;
    }

    @Step("Checks the quantity of goods {expectedQuantity} in refund table. Order_aws")
    public Order_aws checksQuantityOfGoodsInRefundTable(String expectedQuantity) {
        quantityProductInRefundTable().shouldBe(visible);
        quantityProductInRefundTable().shouldHave(attribute("value", expectedQuantity));
        return this;
    }

    @Step("Edit quantity of goods {expectedQuantity} and click save button. Order_aws")
    public Order_aws editQuantityOfItemInPopUpEditItem(String expectedQuantity) {
        popUpEditItem().shouldBe(visible);
        fieldEditQuantityInPopUpEditItem().clear();
        fieldEditQuantityInPopUpEditItem().sendKeys(expectedQuantity);
        saveButtonInPopup().click();
        return this;
    }

    @Step("Click edit item button. Order_aws")
    public Order_aws clickEditItemBtn(String articleID) {
        editItemBtn(articleID).scrollTo();
        editItemBtn(articleID).click();
        return this;
    }

    @Step("Checks the absence of goods in the refund table. Order_aws")
    public Order_aws checkAbsenceOfGoodsInRefundTable(String articleNum) {
        productInRefundTable(articleNum).shouldNotBe(visible);
        return this;
    }

    @Step("Checks the presence of goods in the refund table . Order_aws")
    public Order_aws checkPresenceOfGoodsInRefundTable(String articleNum) {
        productInRefundTable(articleNum).shouldBe(visible);
        return this;
    }

    @Step("Click refund button. Order_aws")
    public Order_aws clickRefundBtn() {
        filterHeader().scrollTo();
        refundBtn().click();
        return this;
    }

    @Step("Click remove product button. Order_aws")
    public Order_aws clickRemoveProductBtn() {
        btnRemoveProduct().scrollTo();
        btnRemoveProduct().click();
        return this;
    }

    @Step("Checks presence remove product popUp and click button Yes. Order_aws")
    public Order_aws clickBtnYesInRemoveProductPopUp() {
        removeProductPopUp().shouldBe(visible);
        btnYesInRemoveProductPopUp().click();
        return this;
    }

    @Step("Checks presence remove product popUp and click button No. Order_aws")
    public Order_aws clickBtnNoInRemoveProductPopUp() {
        removeProductPopUp().shouldBe(visible);
        btnNoInRemoveProductPopUp().click();
        return this;
    }

    @Step("Select the checkbox of the desired product. Order_aws")
    public Order_aws selectCheckboxDesiredProduct(String artID) {
        checkboxOfAddedProduct(artID).scrollTo();
        checkboxOfAddedProduct(artID).click();
        return this;
    }

    @Step("Compares the quantity of items added to the quantity in the column Quantity of products. Order_aws")
    public Order_aws compareQuantityOfItemsWithQuantityInColumnQuantityOfProducts() {
        int quantityProduct = Integer.parseInt(countProducts().getText());
        Assert.assertEquals(addedGoods().size(), quantityProduct);
        return this;
    }

    @Step("Chooses the article id of the desired product {artID} in PopUp AddProduct. Order_aws")
    public Order_aws chooseArticleIDOfDesiredProduct(String artID) {
       if (productArticleIDInPopUpAddProduct(artID).isDisplayed()) {
           productArticleIDInPopUpAddProduct(artID).click();
       }
        return this;
    }

    @Step("Checks presence table with PopUp AddProduct. Order_aws")
    public Order_aws checkPresenceTableWithPopUpAddProduct() {
        tableWithPopUpAddProduct().shouldBe(visible);
        return this;
    }

    @Step("Checks presence table with warehouses and suppliers. Order_aws")
    public Order_aws checkPresenceTableOfWarehousesAndSuppliers() {
        if (tableOfWarehousesAndSuppliers().isDisplayed()) {
            clickBtnAddedGoodsInPopUpAddProduct();
        }
        return this;
    }

    @Step("Click button AddedGoods in PopUp AddProduct. Order_aws")
    public Order_aws clickBtnAddedGoodsInPopUpAddProduct() {
        if (btnAddedGoodsInPopUpAddProduct().isDisplayed()) {
            btnAddedGoodsInPopUpAddProduct().click();
            sleep(3000);
        }
        return this;
    }

    @Step("Filling field ArticleNum in PopUp AddProduct. Order_aws")
    public Order_aws fillingFieldArticleNumInPopUpAddProduct(String ArticleNum) {
        fieldArticleNumInPopUpAddProduct().sendKeys(ArticleNum);
        return this;
    }

    @Step("Checks presence popUp AddProduct. Order_aws")
    public Order_aws checkPresencePopUpAddProduct() {
        popUpAddProduct().shouldBe(visible);
        return this;
    }

    @Step("Click button AddedGoods in order. Order_aws")
    public Order_aws clickBtnAddedGoodsINOrder() {
        btnAddedGoodsInOrder().scrollTo();
        btnAddedGoodsInOrder().click();
        return this;
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

    public Order_aws openOrderInAwsAndLogsInIfUserIntoLogged() {
        Login_aws login_aws = new Login_aws();
        open(url + orderNumber);
        if (login_aws.loginForm().isDisplayed()) {
            login_aws.loginInAws();
            checkWhatOrderOpened();
            checkOrderHasTestPhone();
            testIcon().shouldBe(visible);
        } else {
            checkOrderHasTestPhone();
        }
        return this;
    }

    @Step("Open order in AWS with login and check Black List label. Order_aws")
    public Order_aws openOrderInAwsAndCheckBlackListLabel() {
        open(url + orderNumber);
        new Login_aws().loginInAws();
        checkWhatOrderOpened();
        blackListLabel().shouldBe(visible);
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

    @Step("Method checks appearing of tooltip when we try add incorrect product quantity. Order_aws")
    public Order_aws checkTooltipByAddingIncorrectProductQuantity(String productQuantity) {
        String articleNumber = articleNumber().getText();
        addingBtn().scrollTo().click();
        articleNumberField().setValue(articleNumber);
        countAddProductField().setValue(productQuantity);
        addingProductBtn().click();
        errorPopup().shouldHave(attribute("data-hint", "Это парный продукт, количество должно быть чётным"));
        return this;
    }

    @Step("Checks presence a test phone number. Order_aws")
    public Order_aws checkOrderHasTestPhone() {
        phoneNumberField().shouldHave(or("value", value("+002"), value("+001")));
        testIcon().shouldBe(visible);
        return this;
    }

    @Step("Checks that there is a postscript in the order information that the account is on the blacklist. Order_aws")
    public Order_aws checkInfoOrderAccountInBlackList() {
        infoOrderAccountInBlackList().waitUntil(visible, 3000);
        return this;
    }

    @Step
    public Order_aws checkOrderHasTestStatus() {
        statusOrder().waitUntil(visible, 5000);
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

    @Step
    public Order_aws addDeliveryConditionGLS(String numberDelivery, String expectedDelivery) {
        deliveryMethod(numberDelivery).selectOptionContainingText(expectedDelivery);
        deliveryInfoSendungsnummerField().setValue("test");
        saveChangesInOrderBtn().click();
        packageContentButton().shouldBe(visible);
        return this;
    }

    @Step
    public Float getSellingProductPrice() {
        return Float.valueOf(sellingProductPrice().attr("data-sum"));
    }


    @Step("Transition to the personal account of the customer. Order_aws")
    public Customer_view_aws clickCustomerId() {
        sleep(2000);
        customerId().scrollIntoView("{block: \"center\"}").click();
        return page(Customer_view_aws.class);
    }

    @Step("Re save order and change status in test. Order_aws")
    public Order_aws reSaveOrder() {
        btnChangeOrderStatusInTest().scrollTo();
        btnChangeOrderStatusInTest().click();
        saveChangesInOrderBtn().click();
        sleep(5000);
        return this;
    }

    @Step("Click order search tab button. Order_aws")
    public SearchOrders_page_aws clickOrderSearchTad() {
        orderSearchTad().click();
        return page(SearchOrders_page_aws.class);
    }

    @Step("Save order. Order_aws")
    public Order_aws saveOrder() {
        saveChangesInOrderBtn().scrollTo().click();
        sleep(5000);
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
        saveButtonInPopup().click();
        listWithReclamations().waitUntil(appear, 40000);
        return this;
    }

    @Step("Check delivery price {expectedDeliveryPriceOrderAWS} in order AWS. Order_aws")
    public Order_aws checkDeliveryPriceOrderAWS(String expectedDeliveryPriceOrderAWS) {
        deliveryPriceOrderAWS().shouldHave(attribute("data-sum", expectedDeliveryPriceOrderAWS.replaceAll(",", ".")));
        return this;
    }

    @Step("Check delivery price {expectedDeliveryPriceOrderAWS} in order AWS. Order_aws")
    public Order_aws checkDeliveryPriceOrderAWS(Float expectedDeliveryPriceOrderAWS) {
        deliveryPriceOrderAWS().shouldHave(attribute("data-sum", String.valueOf(expectedDeliveryPriceOrderAWS)));
        return this;
    }

    @Step("Check Heavy Loads delivery price {expectedHeavyLoadsDeliveryPriceOrderAWS} in order AWS. Order_aws")
    public Order_aws checkHeavyLoadsDeliveryPriceOrderAWS(String expectedHeavyLoadsDeliveryPriceOrderAWS) {
        heavyLoadsDeliveryPriceOrderAWS().shouldHave(attribute("data-sum", expectedHeavyLoadsDeliveryPriceOrderAWS));
        return this;
    }

    @Step("Get total Price in Order AWS. Order_aws")
    public Float getTotalPriceOrderAWS() {
        return Float.valueOf(totalPriceOrder().getText());
    }

    @Step("Get selling price in Order AWS. Order_aws")
    public Float getSellingProductPriceOrderAWS() {
        return Float.valueOf(sellingPrice().getText());
    }

    @Step("Get selling price of a certain product {articleID}. Order_aws")
    public Float getSellingPriceOfCertainProduct(String articleID) {
        return Float.valueOf(sellingPriceOfCertainProduct(articleID).getText());
    }

    @Step("Check presence of VAT in the selling price of product{idProduct}. Order_aws")
    public Order_aws checkPresenceVatInSellingPrice(String articleID, String expectedText) {
        vatFromSellingPrice(articleID).scrollIntoView("{block: \"center\"}").shouldHave(text(expectedText));
        return this;
    }

    @Step("Get product quantity from column product quantity")
    public Float getProductQuantity() {
        return Float.valueOf(columnProductQuantity().getText());
    }

    @Step("Get total sum product from column sum of product. Order_aws")
    public Float getTotalSumProductFromColumnSumOfProduct() {
        return Float.valueOf(columnSumProduct().getText());
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

    @Step("Checks VAT status {statusVatOrder} in order. Order_aws")
    public Order_aws checkVatStatusInOrder(String statusVatOrder, String serialNumber) {
        vatPercentageInOrder(serialNumber).shouldHave(text(statusVatOrder));
        return this;
    }

    @Step("Checks payment method {PaymentMethod} in order. Order_aws")
    public Order_aws checkPaymentMethodInOrder(String PaymentMethod) {
        paymentMethod().shouldHave(text(PaymentMethod));
        return this;
    }

    @Step("Checks payment method {PaymentMethod} in order. Order_aws")
    public Order_aws checkPaymentMethodInOrder(String firstBank, String secondBank, String thirdBank, String fourthBank, String fifthBank,
                                               String sixthBank, String seventhBank) {
        paymentMethod().shouldHave(or("text", text(firstBank), text(secondBank), text(thirdBank), text(fourthBank), text(fifthBank),
                                                    text(sixthBank), text(seventhBank)));
        return this;
    }

    @Step("Checks firm confirmation status in order {firmConfirmationStatus}. Order_aws")
    public Order_aws checkFirmConfirmationStatus(String firmConfirmationStatus) {
        firmConfirmationSelector().shouldHave(text(firmConfirmationStatus));
        return this;
    }

    @Step("Chooses firm confirmation status in order {expectedConfirmationStatus}. Order_aws")
    public Order_aws choosesFirmConfirmationStatus(String expectedConfirmationStatus) {
        firmConfirmationSelector().selectOptionByValue(expectedConfirmationStatus);
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
        ArrayList<String> userData = new ArrayList<>();
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

    @Step("Get total cost including selling cost {sellingCost} and delivery cost {deliveryCost}. Order_aws")
    public Float getTotalCostIncludingSellingCostAndDeliveryCost(Float sellingCost, Float deliveryCost) {
        Float cost = sellingCost + deliveryCost;
        String formatCost = new DecimalFormat(".##").format(cost).replaceAll(",", ".");
        return Float.valueOf(formatCost);
    }

    @Step("Get total cost delivery amount {deliveryCost} and safe order {safeOrderCost}. Order_aws")
    public Float getTotalCostDeliveryAmountAndSafeOrder(Float deliveryCost, Float safeOrderCost) {
        Float cost = deliveryCost + safeOrderCost;
        String formatCost = new DecimalFormat(".##").format(cost).replaceAll(",", ".");
        return Float.valueOf(formatCost);
    }

    @Step("Get the total cost including selling cost {sellingCost} delivery cost {deliveryCost} and safe order {safeOrderCost}. Order_aws")
    public Float getTotalCostIncludingDeliveryAndSafeOrder(Float sellingCost, Float deliveryCost, Float safeOrderCost) {
        return sellingCost + deliveryCost + safeOrderCost;
    }

    @Step("Get the total cost including selling cost {sellingCost} delivery cost {deliveryCost} and delivery cost of heavy loads {costOfHeavyLoads}. Order_aws")
    public Float getTotalCostIncludingDeliveryAndDeliveryCostOfHeavyLoads(Float sellingCost, Float deliveryCost, Float costOfHeavyLoads) {
        Float cost = sellingCost + deliveryCost + costOfHeavyLoads;
        String formatCost = new DecimalFormat(".##").format(cost).replaceAll(",", ".");
        return Float.parseFloat(formatCost);
    }

    @Step("Subtracts one product cost {sellingCostOneProduct} from the total order cost {totalCost}. Order_aws")
    public Float subtractsRemovedProductCostFromTotalOrderCost(Float totalCost, Float sellingCostOneProduct) {
        return totalCost - sellingCostOneProduct;
    }

    @Step("Get the price {price} of goods by multiplying it by the quantity {expectedQuantity}. Order_aws")
    public Float getPriceOfGoodsByMultiplyingItByQuantity(Float sellingPrice, Float expectedQuantity) {
        return sellingPrice * expectedQuantity;
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

    @Step("Get delivery cost in order. Order_aws")
    public Float getDeliveryCostInOrder() {
        return Float.valueOf(deliveryCost().getText());
    }

    @Step("Get delivery cost in order from delivery block. Order_aws")
    public Float getDeliveryCostInOrderFromDeliveryBlock() {
        String deliveryCost = deliveryPriceInPaymentAndDeliveryTermsBlock().getValue();
        return Float.valueOf(deliveryCost);
    }

    @Step("Compares the actual shipping price with the expected one. Order_aws")
    public Order_aws compereActualDeliveryCostWithExpected(Float expectedDeliveryCost) {
        Float deliveryPrice = getDeliveryCostInOrderFromDeliveryBlock();
        float res = roundOfTheCost(expectedDeliveryCost, deliveryPrice);
        Assert.assertEquals(res, deliveryPrice);
        return this;
    }

    @Step("Checks delivery costs for a Country {deliveryCostForCountry} or Region {deliveryCostForRegion}. Order_aws")
    public Order_aws checkDeliveryCostForCountryOrRegion(float deliveryCostForRegion, float deliveryCostForCountry, String shop) {
        if (shop.equals("DE")) {
            compereActualDeliveryCostWithExpected(deliveryCostForRegion);
        }
        if (shop.equals("Germany")) {
            compereActualDeliveryCostWithExpected(deliveryCostForRegion);
        }
        if (shop.equals("LI")) {
            compereActualDeliveryCostWithExpected(deliveryCostForCountry);
        }
        if (shop.equals("Liechtenstein")) {
            compereActualDeliveryCostWithExpected(deliveryCostForCountry);
        }
        return this;
    }

    @Step("Get safe order price. Order_aws")
    public Float getSafeOrderPrice() {
        return Float.valueOf(safeOrderPrice().getValue());
    }

    @Step("Checks safe order price {expectedPrice}. Order_aws")
    public Order_aws checkSafeOrderPrice(String expectedPrice) {
        safeOrderPrice().shouldHave(value(expectedPrice));
        return this;
    }

    @Step("Get delivery cost of heavy loads in order. Order_aws")
    public Float getDeliveryCostOfHeavyLoads() {
        return Float.valueOf(heavyLoadsDeliveryPriceOrderAWS().getText());
    }

    @Step("Compares the delivery cost of heavy loads in the order with the cost on the website. Order_aws")
    public Order_aws compereDeliveryCostOfHeavyLoadsWithCostOnSite(Float expectedDeliveryCost) {
        float heavyLoadsDeliveryCost = Float.parseFloat(heavyLoadsDeliveryInPaymentAndDeliveryTermsBlock().getValue());
        Assert.assertEquals(heavyLoadsDeliveryCost, expectedDeliveryCost);
        return this;
    }

    @Step("Checks order has expected deposit Price {ExpectedDepositCost}. Order_aws")
    public Order_aws checkOrderHasExpectedPfandPrice(Float ExpectedDepositCost) {
        float depositCost = Float.parseFloat(pfandFieldInPaymentAndDeliveryTermsBlock().getValue());
        Assert.assertEquals(depositCost, ExpectedDepositCost);
        return this;
    }

    @Step("Get deposit sum in added product tabel {articlNum}. Order_aws")
    public float getPfandSumInAddedProductTabel(String articlNum) {
        String depositSum = pfandSumInAddedProductTabel(articlNum).getText();
        return Float.parseFloat(depositSum);
    }

    @Step("Get heavy loads sum in added product tabel {articlNum}. Order_aws")
    public Float getHeavyLoads(String articleNum) {
        String heavyLoadsSum = heavyLoadsInAddedProductTabel(articleNum).getAttribute("data-sum");
        return Float.valueOf(heavyLoadsSum);
    }

    @Step("Compares the prices of added products with the prices on the site {priceWithSite}. Order_aws")
    public Order_aws comparePriceOfAddedProductWithPricesOnSites(ArrayList priceWithSite) {
        ArrayList<Float> list = new ArrayList<>();
        for (int i = 0; i < sellingPriceOfAddedGoods().size(); i++) {
            list.add(Float.parseFloat(sellingPriceOfAddedGoods().get(i).getText()));
        }
        Assert.assertEquals(priceWithSite, list);
        return this;
    }

    @Step("Get the total cost of all goods including delivery{deliveryCost} and safe order{safeOrderCost}. Order_aws")
    public Float getTotalCostOfAllGoodsAndDeliveryAndSafeOrder(Float deliveryCost, Float safeOrderCost) {
        Float costDeliveryAndSafeOrder = getTotalCostDeliveryAmountAndSafeOrder(deliveryCost, safeOrderCost);
        float sumOfAllGoods = 0.0f;
        for (int i = 0; i < sellingPriceOfAddedGoods().size(); i++) {
            float priceOfOneItem = Float.parseFloat(sellingPriceOfAddedGoods().get(i).getText());
            sumOfAllGoods = sumOfAllGoods + priceOfOneItem;
        }
        return sumOfAllGoods + costDeliveryAndSafeOrder;
    }

    @Step("Plus the selling price of all added items including delivery. Order_aws")
    public Float plusSellingPriceOfAllAddedItemsIncludingDelivery() {
        Float deliveryPrice = getDeliveryCostInOrder();
        float sumOfAllGoods = 0.0f;
        for (int i = 0; i < sellingPriceOfAddedGoods().size(); i++) {
            float priceOfOneItem = Float.parseFloat(sellingPriceOfAddedGoods().get(i).getText());
            sumOfAllGoods = sumOfAllGoods + priceOfOneItem;
        }
        Float sum = (sumOfAllGoods + deliveryPrice);
        String totalSum = new DecimalFormat(".##").format(sum).replaceAll(",", ".");
        return Float.valueOf(totalSum);
    }

    @Step("Get total sum of income without VAT. Order_aws")
    public Float getTotalSumIncomeWithoutVAT() {
        sleep(5000);
        return Float.valueOf(totalIncomeWithoutVat().getText());
    }

    @Step("Get sum discount money. Order_aws")
    public String getSumDiscountMoney() {
       return discountMoney().getText();
    }

    @Step("Get discount in percent. Order_aws")
    public String getDiscountInPercent() {
        return discountInPercent().getText();
    }

    @Step("Get cost from column income without VAT. Order_aws")
    public Float getCostFromColumnIncomeWithoutVat() {
        return Float.valueOf(columnIncomeWithoutVat().getText());
    }

    @Step("Calculates the amount of an item by dividing the total amount of the item {sumProduct Column} by the number of items {productQuantity}. Order_aws")
    public Float dividingPriceByQuantity(Float sumProductColumn, Float productQuantity, Float sellingPrice) {
        float cost = sumProductColumn / productQuantity;
        return roundOfTheCost(cost, sellingPrice);
    }

    @Step("Calculates goods amount by multiplying product price {sellingCostOneProduct} " +
            "by number of goods {productQuantity} and plus the delivery{costDelivery}. Order_aws")
    public Float multiplyPriceByQuantityAndPlusDeliveryCost(Float sellingCostOneProduct, Float productQuantity, Float costDelivery) {
        return sellingCostOneProduct * productQuantity + costDelivery;
    }

    @Step("Checking correct text in input field. Order_aws")
    private void checkCorrectTextAndFillInput(SelenideElement element, String correctText) {
        Configuration.fastSetValue = false;
        if (!element.getValue().equals(correctText)) {
            element.clear();
            element.setValue(correctText);
        }
    }

    @Step("Filling postal code {postalCodeOrCode} in block delivery address. Order_aws")
    public Order_aws fillingPostalCodeInBlockDeliveryAddress(String postalCodeOrCode) {
        checkCorrectTextAndFillInput(fieldPostcodeInDeliveryAddress(), postalCodeOrCode);
        return this;
    }

    @Step("Chooses delivery country {country}. Order_aws")
    public Order_aws choosesDeliveryCountry(String country) {
        deliveryCountrySelector(country).click();
        return this;
    }

    @Step("Checks that order delivery is free. Order_aws")
    public Order_aws checkThatOrderDeliveryIsFree() {
        if (safeOrderSelector().is(text("Включен"))) {
            String safeOrderPrice = safeOrderPrice().getValue();
            String soPriceFormat = safeOrderPrice.replace(safeOrderPrice.substring(safeOrderPrice.indexOf(".")),"");
            deliveryPriceOrderAWS().shouldHave(text(soPriceFormat));
            deliveryPriceInPaymentAndDeliveryTermsBlock().shouldHave(value(soPriceFormat));
        } else if (safeOrderSelector().is(text("Выключен"))) {
            deliveryPriceOrderAWS().shouldHave(text("0"));
            deliveryPriceInPaymentAndDeliveryTermsBlock().shouldHave(value("0"));
        }
        return this;
    }

    @Step("Checks sum delivery in order. Order_aws")
    public Order_aws checkSumDeliveryInOrder(Float deliveryPrice) {
        deliveryPriceInPaymentAndDeliveryTermsBlock().shouldHave(value(String.valueOf(deliveryPrice)));
        deliveryPriceOrderAWS().shouldHave(text(String.valueOf(deliveryPrice)));
        return this;
    }


    @Step("get VIN num garage in popup from btn Auto By Search From The Site. Order_aws")
    public Order_aws getVinNumGarageInPopupFromBtnAutoBySearchFromTheSite(String vinNum) {
        vinNumGarageInPopupFromBtnAutoBySearchFromTheSite().shouldHave(text(vinNum));
        closePopupFromBtnAutoBySearchFromTheSite().shouldBe(visible).click();
        return this;
    }

    @Step("Checks presence reorder number and transition and goes to it. Order_aws")
    public Order_aws transitionToReorderNumber() {
        reorderNumber().scrollTo().shouldBe(visible).click();
        return this;
    }

    @Step("Checks presence transaction cod block. Order_aws")
    public Order_aws checkPresenceTransactionCodBloc() {
        transactionCodBlock().scrollTo().shouldBe(visible);
        return this;
    }

    @Step("Click return button. Order_aws")
    public Order_aws clickReturnButton() {
        returnButton().should(visible);
        returnButton().click();
        return this;
    }

    @Step("Chooses return type. Order_aws")
    public Order_aws chooseReturnType(String expectedMethod) {
        typeSelectInReturnPopup().shouldBe(visible);
        typeSelectInReturnPopup().selectOptionContainingText(expectedMethod);
        return this;
    }

    @Step("Click check box product in popup return. Order_aws")
    public Order_aws clickCheckBoxProductInPopupReturn() {
        checkBoxProductInPopupReturn().shouldBe(visible);
        checkBoxProductInPopupReturn().click();
        return this;
    }

    @Step("Click check box delivery in popup return. Order_aws")
    public Order_aws clickCheckBoxDeliveryInPopupReturn() {
        checkBoxDeliveryInPopupReturn().shouldBe(visible);
        checkBoxDeliveryInPopupReturn().click();
        return this;
    }

    @Step("Click print button in popup return. Order_aws")
    public Order_aws clickPrintButtonInPopupReturn() {
        printBtnInPopupReturn().click();
        sleep(8000);
        return this;
    }

    @Step("Close popup return. Order_aws")
    public Order_aws clickBtnClosePopUpReturn() {
        closePopupReturn().click();
        return this;
    }

    @Step("Get article ID. Order_aws")
    public String getArticleId() {
        return articleID().getText();
    }

    @Step("Filling fields postal code {postalCod} in billing. Order_aws")
    public Order_aws fillingFieldsPostalCodInBilling(String postalCod) {
        fieldPostcodeInBilling().setValue(postalCod);
        return this;
    }

    @Step("Check postal code inside field postal code {expectedCod} in billing block. Order_aws")
    public Order_aws checkPostalCodInBillingBlock(String expectedCod) {
        fieldPostcodeInBilling().shouldHave(value(expectedCod));
        return this;
    }

    @Step("Filling fields postal code {postalCod} in shipping. Order_aws")
    public Order_aws fillingFieldsPostalCodInShipping(String postalCod) {
        fieldPostcodeInDeliveryAddress().setValue(postalCod);
        return this;
    }

    @Step("Check postal code inside field postal code {expectedCod} in shipping block. Order_aws")
    public Order_aws checkPostalCodInShippingBlock(String expectedCod) {
        fieldPostcodeInDeliveryAddress().shouldHave(value(expectedCod));
        return this;
    }

    @Step("Check presence expected element. Order_aws")
    public Order_aws checkPresenceExpectedElement(SelenideElement element) {
        element.shouldHave(visible);
        return this;
    }

    @Step("Check absence expected element. Order_aws")
    public Order_aws checkAbsenceExpectedElement(SelenideElement element) {
        element.shouldNotBe(visible);
        return this;
    }

    @Step("Checks presence paylink amount. Order_aws")
    public Order_aws checkPresencePauLinkAmount() {
        payLinkAmount().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        return this;
    }

    @Step("Checks expected text {expectedText} in label danger of certain product {artID}. Order_aws")
    public Order_aws checkTextInLabelDanger(String artID, String expectedText) {
        labelDangerOfCertainProduct(artID).waitUntil(visible, 10000).shouldHave(text(expectedText));
        return this;
    }

    @Step("Click button declaration. Order_aws")
    public Order_aws clickBtnDeclaration() {
        declarationBtn().click();
        return this;
    }

    @Step("Check modal window declaration and click print button. Order_aws")
    public Order_aws checkModalWindowDeclarationAndClickPrintBtn() {
        modalWindowDeclaration().waitUntil(visible, 10000);
        checkboxSelectAllInModalDeclaration().shouldBe(visible).click();
        printBtnInModalDeclaration().click();
        sleep(5000);
        closeBtnModalWindowDeclaration().waitUntil(visible, 10000);
        closeBtnModalWindowDeclaration().click();
        return this;
    }

    @Step("Click button added product. Order_aws")
    public Order_aws clickBtnAddProduct() {
        addingBtn().scrollTo().click();
        popUpAddProduct().shouldBe(visible);
        return this;
    }

    @Step("Clos modal Window added product. Order_aws")
    public Order_aws closeModalWindowAddProduct() {
        closeBtnModalWindowAddProduct().click();
        return this;
    }

    @Step("Adding product {articleNumber, productQuantity} in order. Order_aws")
    public Order_aws addProductInOrder(String articleNumber, String productQuantity) {
        clickBtnAddProduct();
        articleNumberField().setValue(articleNumber);
        countAddProductField().setValue(productQuantity);
        addingProductBtn().click();
        sleep(2000);
        return this;
    }

    @Step("Confirmation of adding goods to the order. Order_aws")
    public Order_aws confirmationAddingGoodsToOrder(String articlID) {
        if (suppliersTableInPopUpAddProduct().isDisplayed() || storageTableInPopUpAddProduct().isDisplayed()) {
            addingProductBtn().click();
        }
            if (prodictListInPopUpAddProduct().isDisplayed()) {
                productInPopUpAddProduct(articlID).click();
                addingProductBtn().click();
                productInPopUpAddProduct(articlID).waitUntil(not(visible), 10000);
                if (storageTableInPopUpAddProduct().isDisplayed() || suppliersTableInPopUpAddProduct().isDisplayed()) {
                    addingProductBtn().click();
                }
            }
        else if (addingProductBtn().isDisplayed()) {
            addingProductBtn().click();
        }
        popUpAddProduct().waitUntil(not(visible), 10000);
        return this;
    }

    @Step("Checks presence error popup in top right. Order_aws")
    public Order_aws checkPresenceErrorPopupInTopRight() {
        errorPopupInTopRight().shouldBe(visible);
        return this;
    }

    @Step("Checks the phone number for extra characters. Order_aws")
    public Order_aws checkPhoneNumberForExtraCharacters(String expectedChar) {
        String phoneNum = fieldPhoneInBilling().getValue().replaceAll("^\\d{2}", "");
        if (phoneNum.startsWith("0")) {
            Assert.fail("Number starts at zero");
        } else {
            System.out.println("Number does not start at zero");
        }
        if (phoneNum.contains(expectedChar)) {
            Assert.fail("The number contains the symbol");
        } else {
            System.out.println("The number does not contain the symbol");
        }
        return this;
    }

    @Step("Check and close pop up Black List. Order_aws")
    public Order_aws checkAndClosePopUpBlackList() {
        popUpBlackList().waitUntil(visible, 5000);
        closePopUPBlackList().click();
        return this;
    }

    @Step("Check presence icon ID in BlackList. Order_was")
    public Order_aws checkIconIdInBlackList() {
        iconIdInBlackList().waitUntil(visible, 3000);
        return this;
    }

}
