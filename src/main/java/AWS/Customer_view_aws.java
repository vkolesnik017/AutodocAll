package AWS;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class Customer_view_aws {


    private SelenideElement companyNumberLogsBlock() {
        return $x("//div[@class='mt-20']");
    }


    @Step("Checks absence block logs company number. Customer_view_aws")
    public Customer_view_aws checkAbsenceBlockLogsCompanyNumber() {
        companyNumberLogsBlock().shouldNotBe(visible);
        return this;
    }
}
