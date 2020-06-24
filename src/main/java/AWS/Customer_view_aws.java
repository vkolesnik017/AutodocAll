package AWS;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Customer_view_aws {


    private String url = "https://aws.autodoc.de/customer/view/";


    private SelenideElement customerIdBlock() {
        return $x("//*[@class='customer-id-cell']");
    }

    private SelenideElement companyNumberLogsBlock() {
        return $x("//div[@class='mt-20']");
    }

    private SelenideElement columnIdCompany(String id) {
        return $x("//div[@class='mt-20']//tbody//tr//td[text()='" + id + "']");
    }

    private SelenideElement columnResponse(String status) {
        return $x("//div[@class='mt-20']//tbody//tr//td[text()='" + status + "                    ']");
    }

    private SelenideElement columnBillingOrShipping(String billingOrShipping) {
        return $x("//div[@class='mt-20']//tbody//tr//td[text()='" + billingOrShipping + "']");
    }

    private SelenideElement errorNameStatus() {
        return $x("//div[@class='mt-20']//td[6]");
    }

    private SelenideElement errorCityStatus() {
        return $x("//div[@class='mt-20']//td[7]");
    }

    private SelenideElement fieldNameInBilling() {
        return $x("//input[@id='form_rVorname']");
    }

    private SelenideElement fieldSurnameInBilling() {
        return $x("//input[@id='form_rName']");
    }

    private SelenideElement fieldStreetInBilling() {
        return $x("//input[@id='form_rStrasse']");
    }

    private SelenideElement fieldHouseNumberInBilling() {
        return $x("//input[@id='form_payment_house']");
    }

    private SelenideElement fieldPostcodeInBilling() {
        return $x("//input[@id='form_rPlz']");
    }

    private SelenideElement fieldCityInBilling() {
        return $x("//input[@id='form_rOrt']");
    }

    private SelenideElement fieldCountryInBilling() {
        return $x("//select[@id='form_payment_country_id']");
    }

    private SelenideElement fieldMailInBilling() {
        return $x("//input[@id='form_Email']");
    }

    private SelenideElement fieldPhoneInBilling() {
        return $x("//input[@id='form_rTelefon']");
    }

    private SelenideElement fieldNameInDeliveryAddress() {
        return $x("//input[@id='form_lVorname']");
    }

    private SelenideElement fieldSurnameInDeliveryAddress() {
        return $x("//input[@id='form_lName']");
    }

    private SelenideElement fieldStreetInDeliveryAddress() {
        return $x("//input[@id='form_lStrasse']");
    }

    private SelenideElement fieldHouseNumberInDeliveryAddress() {
        return $x("//input[@id='form_delivery_house']");
    }

    private SelenideElement fieldPostcodeInDeliveryAddress() {
        return $x("//input[@id='form_lPlz']");
    }

    private SelenideElement fieldCityInDeliveryAddress() {
        return $x("//input[@id='form_lOrt']");
    }

    private SelenideElement fieldCountryInDeliveryAddress() {
        return $x("//select[@id='form_delivery_country_id']");
    }

    private SelenideElement fieldPhoneInDeliveryAddress() {
        return $x("//input[@id='form_lTelefon']");
    }

    private SelenideElement fieldNameReceiver() {
        return $x("//input[@id='form_BankData[AccOwner]']");
    }

    private SelenideElement fieldIbanNum() {
        return $x("//input[@id='form_BankData[AccIBAN]']");
    }

    private SelenideElement bankData() {
        return $x("//div[@id='bankData']");
    }

    private SelenideElement subscriptionBlock() {
        return $(".subscription_box");
    }

    private SelenideElement checkStatusOkayInSubscriptConsentLogs() {
        return $x("//div[@class='col-sm-12 col-md-5']//tr[1]//td[3]//i[@class='splashy-okay']");
    }

    private SelenideElement customerDepositTable() {
        return $x("//div[@class='col-sm-12 col-md-7']//div[8]");
    }

    private SelenideElement depositBalanceAfterLastCrediting() {
        return $x("//table[@class='table table-striped table-bordered table-condensed orders customer-deposit']//tr[1]//td[4]");
    }

    @Step("Get deposit balance after the last crediting . Customer_view_aws")
    public Float getDepositBalanceAfterLastCrediting() {
       return Float.valueOf(depositBalanceAfterLastCrediting().getText());
    }

    @Step("Checks presence customer deposit table. Customer_view_aws")
    public Customer_view_aws checkPresenceCustomerDepositTable() {
        customerDepositTable().shouldBe(visible);
        return this;
    }


    @Step("Checks that the last log has the status OK in the block subscription block. Customer_view_aws")
    public Customer_view_aws checkStatusOfLastLog() {
        subscriptionBlock().shouldHave(visible);
        checkStatusOkayInSubscriptConsentLogs().shouldBe(visible);
        return this;
    }

    @Step("Checks presence bank data block. Customer_view_aws")
    public Customer_view_aws checkPresenceBankDataBlock() {
        bankData().scrollTo();
        bankData().shouldBe(visible);
        return this;
    }

    @Step("Get name receiver in bank, customer data block. Customer_view_aws")
    public String getNameInReceiverInCurrentBankBlock() {
        return String.valueOf(fieldNameReceiver().getAttribute("value"));
    }

    @Step("Get IBAN num in bank, customer data block. Customer_view_aws")
    public String getIbanNumInCurrentBankBlock() {
        return String.valueOf(fieldIbanNum().getAttribute("value"));
    }


    @Step("Open customer personal area {customerID}. Customer_view_aws")
    public Customer_view_aws openCustomerPersonalArea(String customerID) {
        open(url + customerID);
        new Login_aws().loginInAws();
        customerIdBlock().shouldBe(visible);
        return this;
    }

    @Step("Gets user data. Customer_view_aws")
    public ArrayList getUserData() {
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

    @Step("Checks Error status in (Error in the name) column. Customer_view_aws")
    public Customer_view_aws checkErrorStatusInNameErrorColumn(String errorStatus) {
        errorNameStatus().shouldHave(text(errorStatus));
        return this;
    }

    @Step("Checks Error status in (Error in the city) column. Customer_view_aws")
    public Customer_view_aws checkErrorStatusInCityErrorColumn(String errorStatus) {
        errorCityStatus().shouldHave(text(errorStatus));
        return this;
    }

    @Step("Checks billingOrShipping in block logs company numbers. Customer_view_aws")
    public Customer_view_aws checkBillingOrShippingInBlockLogsCompanyNumbers(String billingOrShipping) {
        columnBillingOrShipping(billingOrShipping).shouldBe(visible);
        return this;
    }

    @Step("Checks id company in block logs company numbers. Customer_view_aws")
    public Customer_view_aws checkIdCompanyInBlockLogsCompanyNumbers(String idCompany) {
        columnIdCompany(idCompany).shouldBe(visible);
        return this;
    }

    @Step("Checks response in block logs company numbers. Customer_view_aws")
    public Customer_view_aws checkResponseInBlockLogsCompanyNumbers(String status) {
        columnResponse(status).shouldBe(visible);
        return this;
    }

    @Step("Checks absence block logs company numbers. Customer_view_aws")
    public Customer_view_aws checkAbsenceBlockLogsCompanyNumbers() {
        companyNumberLogsBlock().shouldNotBe(visible);
        return this;
    }

    @Step("Checks presence block logs company numbers. Customer_view_aws")
    public Customer_view_aws checkPresenceBlockLogsCompanyNumbers() {
        companyNumberLogsBlock().shouldBe(visible);
        return this;
    }
}
