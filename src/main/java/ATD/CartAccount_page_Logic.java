package ATD;

import static ATD.CommonMethods.password;

import static ATD.CommonMethods.mailRandom;
import static com.codeborne.selenide.Selenide.page;

public class CartAccount_page_Logic extends CartAccount_page{

    public String fillindRegistrationFields(String qc){
        String mail = qc + mailRandom();
        registrationFormEmailInput().setValue(mail);
        registrationFormPasswordInput().setValue(password);
        registrationFormNextBtnClick();
        return mail;
    }

    public CartAddress_page registrationFormNextBtnClick() {
        registrationButton().click();
        return page(CartAddress_page.class);
    }


    public CartAccount_page_Logic checkingDatenschutzerklarungLinkBehavior() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(datenschutzerklarungLink(), "underline solid rgb(50, 103, 214)");
        return this;
    }

}
