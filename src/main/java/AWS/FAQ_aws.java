package AWS;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.*;

public class FAQ_aws {

    private String url = "https://aws.autodoc.de/faq";

    public FAQ_aws openFaqAwsPage() {
        open(url);
        return this;
    }

    public FAQ_aws openAndLoginFaqAwsPage() {
        open(url);
        new Login_aws().loginInAws();
        return this;
    }

    public FAQ_aws searchQuestionAndPublished(String mail) {
        clientInfoFiels().setValue(mail);
        searchBtn().click();
        faqQuestionByMail(mail).shouldBe(appear);
        checkboxFaqQuestionByMail(mail).click();
        publishedBtn().click();
        confirm();
        publishedSplashyFaqQuestionByMail(mail).shouldBe(appear);
        sleep(5);
        return this;
    }

    public FAQ_aws searchQuestionAndUnPublished(String mail) {
        clientInfoFiels().setValue(mail);
        searchBtn().click();
        faqQuestionByMail(mail).shouldBe(appear);
        checkboxFaqQuestionByMail(mail).click();
        unpublishedBtn().click();
        confirm();
        unpublishedSplashyFaqQuestionByMail(mail).shouldBe(appear);
        sleep(2);
        return this;
    }

    private SelenideElement clientInfoFiels() {
        return $(By.id("form_Filter[customerInfo]"));
    }

    private SelenideElement searchBtn() {
        return $(By.xpath("//button[@name='search']"));
    }

    private SelenideElement faqQuestionByMail(String mail) {
        return $(By.xpath("//*[contains(text(), '" + mail + "')]"));
    }

    private SelenideElement checkboxFaqQuestionByMail(String mail) {
        return $(By.xpath("//*[contains(text(),'" + mail + "')]/ancestor::tr/td[@class]/input"));
    }

    private SelenideElement publishedSplashyFaqQuestionByMail(String mail) {
        return $(By.xpath("//*[contains(text(),'" + mail + "')]/ancestor::tr//i[@class='splashy-okay']"));
    }

    private SelenideElement unpublishedSplashyFaqQuestionByMail(String mail) {
        return $(By.xpath("//*[contains(text(),'" + mail + "')]/ancestor::tr//i[@class='splashy-error_x']"));
    }

    private SelenideElement publishedBtn() {
        return $(By.xpath("//button[@class='btn btn-success control-publish']"));
    }

    private SelenideElement unpublishedBtn() {
        return $(By.xpath("//button[@class='btn btn-default control-disable']"));

    }

    public SelenideElement faqResponseInput() {
        return $x("//*[@id='Question[answer]']");
    }

    public SelenideElement faqAWSsubmitButton() {
        return $(".submit-faq");
    }

    public SelenideElement searchTextOnPage(String textForSearch) {
        return $x("//*[contains(text(),'" + textForSearch + "')]");

    }
}
