package ATD;

import io.qameta.allure.Step;

import static ATD.CommonMethods.password;

import static ATD.CommonMethods.mailRandom;
import static com.codeborne.selenide.Selenide.page;

public class CartAccount_page_Logic extends CartAccount_page{


    @Step("Filling fields in registration form. CartAccount_page")
    public String fillingRegistrationFields(String qc){
        String mail = qc + mailRandom();
        registrationFormEmailInput().setValue(mail);
        registrationFormPasswordInput().setValue(password);
        registrationFormNextBtnClick();
        return mail;
    }

    @Step("clicking Next button in registration form. CartAccount_page")
    public CartAddress_page registrationFormNextBtnClick() {
        registrationButton().click();
        return page(CartAddress_page.class);
    }

    @Step(":registration form. CartAccount_page")
    public CartAccount_page_Logic checkingDatenschutzerklarungLinkBehaviorRegistrationForm() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(datenschutzerklarungLink(), "underline solid rgb(50, 103, 214)");
        return this;
    }

}