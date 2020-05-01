package AWS;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class Customer_view_aws {


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
