package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;

public class Service_packages_page_Logic extends Service_packages_page {

    @Step("set Monthly Price. Service_packages_page")
    public Service_packages_page_Logic setMonthlyPrice() {
        monthlyPriceSwitch().shouldBe(visible).click();
        priceValueForPeriod().get(0).shouldHave(exactText("/Monat"));
        return this;
    }

    @Step("click on 'More' button Of General Condition Block. Service_packages_page")
    public Service_packages_page_Logic clickOnBtnMoreOfGeneralConditionBlock() {
        btnMoreOfGeneralConditionBlock().click();
        return this;
    }

    @Step("presence Of Cancellation Form. Service_packages_page")
    public Service_packages_page_Logic presenceOfCancellationForm(boolean label) {
        if (label == true) {
            cancellationForm().shouldBe(visible);
        } else {
            cancellationForm().shouldNotBe(visible);
        }
        return this;
    }

    @Step("click On Active Basic Package. Service_packages_page")
    public Service_packages_page_Logic clickOnActiveBasicPackage() {
        btnActiveBasicPackage().click();
        return this;
    }

    @Step("presence Of Registration PopUp. Service_packages_page")
    public Service_packages_page_Logic presenceOfRegistrationPopUp() {
        registrationPopUp().shouldBe(visible);
        return this;
    }
}
