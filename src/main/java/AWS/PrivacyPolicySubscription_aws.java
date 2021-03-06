package AWS;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

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

    private SelenideElement subscriptionCell() {
        return $(By.xpath("//table[@class='table table-borderedd']//td[contains(text(), 'Subscription')]"));
    }

    private SelenideElement subscriptionRequest() {
        return $(By.xpath("//table[@class='table table-borderedd']//td[contains(text(), 'Subscription Requested')]"));
    }

    private SelenideElement acceptedLabel(String status) {
        return $x("//table[@class='table table-borderedd']//td/i[@class='splashy-" + status + "']");
    }


    public PrivacyPolicySubscription_aws checkingPolicyForMail(String mail) {
        emailFiled().setValue(mail);
        searchBtn().click();
        privacyPolicyCell().shouldHave(text("Privacy Policy"));
        return this;
    }

    public PrivacyPolicySubscription_aws checkingPolicyAndSubscribeForMail(String mail) { //TODO добавить проверку(открыть эмэйл кнопка "SHOW")
        emailFiled().setValue(mail);
        searchBtn().click();
        privacyPolicyCell().shouldHave(text("Privacy Policy Acceptance"));
        subscriptionCell().shouldHave(text("Subscription Acceptance"));
        subscriptionRequest().shouldHave(text("Subscription Requested"));
        return this;
    }

    @Step("Checking a successful subscription to the newsletter. PrivacyPolicySubscription_aws")
    public PrivacyPolicySubscription_aws checkingSuccessfulSubscription(String mail) {
        emailFiled().setValue(mail);
        searchBtn().click();
        privacyPolicyCell().shouldHave(text("Privacy Policy"));
        acceptedLabel("okay").shouldBe(visible);
        return this;
    }
}
