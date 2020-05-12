package AWS;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class OrderAdd_page_aws {

    private SelenideElement fieldCustomerID() {
        return $x("//input[@id='Order[CustomerID]']");
    }

    private SelenideElement selectorForSkin() {
        return $x("//div[@id='form_Order_orderProjectId__chzn']");
    }

    private SelenideElement listSkins(String skin) {
        return $x("//div[@class='chzn-drop']//ul//li[text()='" + skin + "']");
    }

    private SelenideElement searchBoxBySkin() {
        return $x("//div[@class='chzn-search']");
    }

    private SelenideElement fieldNameInBilling() {
        return $x("//input[@id='Order[rVorname]']");
    }

    private SelenideElement fieldSurnameInBilling() {
        return $x("//input[@id='Order[rName]']");
    }

    private SelenideElement fieldStreetInBilling() {
        return $x("//input[@id='Order[rStrasse]']");
    }

    private SelenideElement fieldHouseNumberInBilling() {
        return $x("//input[@id='Order[payment_house]']");
    }

    private SelenideElement fieldPostcodeInBilling() {
        return $x("//input[@id='Order[rPlz]']");
    }

    private SelenideElement fieldCityInBilling() {
        return $x("//input[@id='Order[rOrt]']");
    }

    private SelenideElement fieldCountryInBilling() {
        return $x("//select[@id='form_Order[payment_country_id]']");
    }

    private SelenideElement fieldMailInBilling() {
        return $x("//input[@id='Order[Email]']");
    }

    private SelenideElement fieldPhoneInBilling() {
        return $x("//input[@id='Order[rTelefon]']");
    }

    private SelenideElement fieldNameInDeliveryAddress() {
        return $x("//input[@id='Order[lVorname]']");
    }

    private SelenideElement fieldSurnameInDeliveryAddress() {
        return $x("//input[@id='Order[lName]']");
    }

    private SelenideElement fieldStreetInDeliveryAddress() {
        return $x("//input[@id='Order[lStrasse]']");
    }

    private SelenideElement fieldHouseNumberInDeliveryAddress() {
        return $x("//input[@id='Order[delivery_house]']");
    }

    private SelenideElement fieldPostcodeInDeliveryAddress() {
        return $x("//input[@id='Order[lPlz]']");
    }

    private SelenideElement fieldCityInDeliveryAddress() {
        return $x("//input[@id='Order[lOrt]']");
    }

    private SelenideElement fieldCountryInDeliveryAddress() {
        return $x("//select[@id='form_Order[delivery_country_id]']");
    }

    private SelenideElement fieldPhoneInDeliveryAddress() {
        return $x("//input[@id='Order[lTelefon]']");
    }

    private SelenideElement paymentMethod() {
        return $x("//select[@id='form_Order[PaymentID]']");
    }

    private SelenideElement deliveryMethod() {
        return $x("//select[@id='form_Order[DeliveryID]']");
    }

    private SelenideElement SafeOrder() {
        return $x("//select[@name='securityDeliveryStatusChange']");
    }

    private SelenideElement productSearchField() {
        return $(".search-products");
    }

    private SelenideElement btnAddProduct() {
        return $(".btn-add-product");
    }

    private SelenideElement tableOfSuppliers() {
        return $(".contains-suppliers");
    }

    private SelenideElement btnSelect() {
        return $(".prices-select");
    }

    private SelenideElement articleOfAddedProduct() {
        return $x("//tr[@class='targerProduct ']//td[2]");
    }

    private SelenideElement deliveryCost() {
        return $x("//input[@id='Order[DeliveryCost]']");
    }

    private SelenideElement safeOrderCost() {
        return $x("//input[@id='isSecurityDelivery']");
    }

    private SelenideElement saveOrderBtn() {
        return $(".submit-order");
    }

    @Step("Click save order button. OrderAdd_page_aws")
    public Order_aws clickSaveOrderBtn() {
        saveOrderBtn().click();
        return page(Order_aws.class);
    }

    @Step("Get delivery cost. OrderAdd_page_aws")
    public Double getDeliveryCost() {
        String deliveryCost = deliveryCost().getAttribute("data-default");
        Double realDeliveryCost = Double.parseDouble(deliveryCost);
        return realDeliveryCost;
    }

    @Step("Get safe order cost. OrderAdd_page_aws")
    public Double getSafeOrderCost() {
        String safeOrderCost = safeOrderCost().getValue();
        Double realSafeOrderCost = Double.parseDouble(safeOrderCost);
        return realSafeOrderCost;
    }

    @Step("Added product {articleNum}. OrderAdd_page_aws")
    public OrderAdd_page_aws addProduct(String articleNum) {
        productSearchField().sendKeys(articleNum);
        btnAddProduct().click();
        return this;
    }

    @Step("Checks presence table of suppliers and click button Select. OrderAdd_page_aws")
    public OrderAdd_page_aws checkPresenceTableOfSuppliersAndClickBtnSelect() {
        tableOfSuppliers().shouldBe(visible);
        btnSelect().click();
        return this;
    }

    @Step("Checks article number of added product {articleNum}. OrderAdd_page_aws")
    public OrderAdd_page_aws checkArticleOfAddedProduct(String articleNum) {
        articleOfAddedProduct().shouldHave(text(articleNum));
        return this;
    }

    @Step("Selected payment expectedMethod {expectedMethod}. OrderAdd_page_aws")
    public OrderAdd_page_aws selectedPaymentMethod(String expectedMethod) {
        paymentMethod().selectOptionContainingText(expectedMethod);
        return this;
    }

    @Step("Selected delivery expectedMethod {expectedMethod}. OrderAdd_page_aws")
    public OrderAdd_page_aws selectedDeliveryMethod(String expectedMethod) {
        deliveryMethod().selectOptionContainingText(expectedMethod);
        return this;
    }

    @Step("Selected status in safe order {status}. OrderAdd_page_aws")
    public OrderAdd_page_aws selectedStatusInSafeOrder(String status) {
        SafeOrder().selectOptionContainingText(status);
        return this;
    }


    @Step("Gets user data. OrderAdd_page_aws")
    public ArrayList getUserDataInOrder() {
        String nameText = fieldNameInBilling().getValue();
        String surnameText = fieldSurnameInBilling().getValue();
        String streetText = fieldStreetInBilling().getValue();
        String houseNumberText = fieldHouseNumberInBilling().getValue();
        String postcodeText = fieldPostcodeInBilling().getValue();
        String cityText = fieldCityInBilling().getValue();
        String countryText = fieldCountryInBilling().getText();
        String mailText = fieldMailInBilling().getValue();
        String phoneText = fieldPhoneInBilling().getValue();
        String nameDeliveryAddressText = fieldNameInDeliveryAddress().getValue();
        String surnameDeliveryAddressText = fieldSurnameInDeliveryAddress().getValue();
        String streetDeliveryAddressText = fieldStreetInDeliveryAddress().getValue();
        String houseDeliveryAddressNumberText = fieldHouseNumberInDeliveryAddress().getValue();
        String postcodeDeliveryAddressText = fieldPostcodeInDeliveryAddress().getValue();
        String cityDeliveryAddressText = fieldCityInDeliveryAddress().getValue();
        String countryDeliveryAddressText = fieldCountryInDeliveryAddress().getText();
        String phoneDeliveryAddressText = fieldPhoneInDeliveryAddress().getValue();
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

    @Step("Fills in the field CustomerID {CustomerID}. OrderAdd_page_aws")
    public OrderAdd_page_aws fillsInFieldCustomerID(String CustomerID) {
        fieldCustomerID().sendKeys(CustomerID);
        return this;
    }

    @Step("Choosing skin in selector {skin}. OrderAdd_page_aws")
    public OrderAdd_page_aws chooseSkinInSelector(String skin) {
        selectorForSkin().click();
        sleep(2000);
        if (!searchBoxBySkin().isDisplayed()) {
            selectorForSkin().click();
        }
        listSkins(skin).click();
        return this;
    }
}
