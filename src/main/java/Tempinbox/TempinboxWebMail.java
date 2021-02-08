package Tempinbox;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class TempinboxWebMail {
    private String emailUrl = "https://www.tempinbox.xyz/";

    private SelenideElement emailField() {
        return $(byName("email"));
    }

    private SelenideElement btnCreateEmail() {
        return $x("//div[@class='tm-domain-selector']/following-sibling::input");
    }

    private SelenideElement generatedEmailField() {
        return $x("//div[@class='col-10']/input");
    }

    private SelenideElement subscriptionLetter() {
        return $x("//div[@id='mails']//div[text()='Noch ein weiterer Schritt und Sie haben unseren Newsletter abonniert.']");
    }

    private SelenideElement btnRefresh() {
        return $x("//div[@class='action-item refresh']/span/i");
    }

    private SelenideElement btnConfirmSubscription() {
        return $x("//a[contains(text(),'Abonnement bestätigen')]");
    }

    private SelenideElement btnCloseAlertBlock() {
        return $x("//div[@class='toprow']/div[1]/div");
    }

    @Step("set email. TempinboxWebMail ")
    public TempinboxWebMail setEmail(String email) {
        emailField().shouldBe(visible).setValue(email);
        return this;
    }

    @Step("set email. TempinboxWebMail ")
    public TempinboxWebMail openTempWebMail() {
        open(emailUrl);
        return this;
    }

    @Step("click on create email. TempinboxWebMail ")
    public TempinboxWebMail clickOnCreateEmail() {
        btnCreateEmail().click();
        return this;
    }

    @Step("click on create email. TempinboxWebMail ")
    public String getGeneratedEmail() {
        return generatedEmailField().shouldBe(visible).attr("value");
    }

    @Step("click on subscription letter. TempinboxWebMail ")
    public TempinboxWebMail clickOnSubscriptionLetter() {
        btnRefresh().shouldBe(visible);
        for (int i = 0; i < 3; i++) {
            btnRefresh().waitWhile(attribute("fas fa-sync-alt fa-spin"), 2000);
            btnRefresh().waitWhile(attribute("fas fa-sync-alt fa-spin pause-spinner"), 2000);
        }
        subscriptionLetter().shouldBe(visible).click();
        return this;
    }

    @Step("click on confirm subscription. TempinboxWebMail ")
    public TempinboxWebMail clickOnConfirmSubscription() {
        btnConfirmSubscription().shouldBe(visible).click();
        sleep(2000);  // ТУТ НЕОБХОДИМ SLEEP, ТАК КАК НЕ К ЧЕМУ ПРИВЯЗАТЬСЯ ПО ОЖИДАНИЮ
        return this;
    }


}
