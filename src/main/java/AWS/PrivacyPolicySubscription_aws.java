package AWS;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PrivacyPolicySubscription_aws {

    private final String urlPage = "https://aws.autodoc.de/customer/privacyPolicySubscriptionAcceptance";

    public PrivacyPolicySubscription_aws openPolicySubscriptionWithLogin() {
        open(urlPage);
        new Login_aws().loginInAws("autodoc-8530", "24a871fc");
        return this;
    }

    private SelenideElement emailFiled() {
        return $(By.id("form_Filter[email]"));
    }

    private SelenideElement searchBtn() {
        return $(By.xpath("//button[@name='searchBig']"));
    }

    private SelenideElement privacyPolicyCell() {
        return $(By.xpath("//table[@class='table table-borderedd']//td[contains(text(), 'Privacy Policy')]"));
    }

    public PrivacyPolicySubscription_aws checkingPolicyForMail(String mail){
        emailFiled().setValue(mail);
        searchBtn().click();
        privacyPolicyCell().shouldHave(Condition.text("Privacy Policy"));
        return this;
    }
}
