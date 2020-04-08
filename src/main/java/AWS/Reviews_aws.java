package AWS;


import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class Reviews_aws {
    private String url = "https://aws.autodoc.de/reviews";

    public Reviews_aws openReviewsAwsPage() {
        open(url);
        return this;
    }

    public Reviews_aws openAndLoginReviewsAwsPage() {
        open(url);
        new Login_aws().loginInAws();
        return this;
    }

    public Reviews_aws searchQuestionAndPublished(String mail) {
        clientMailFiels().setValue(mail);
        searchBtn().click();
        faqQuestionByMail(mail).shouldBe(appear);
        checkboxFaqQuestionByMail(mail).click();
        publishedBtn().click();
        confirm();
        publishedSplashyFaqQuestionByMail(mail).shouldBe(appear);
        sleep(5);
        return this;
    }

    public Reviews_aws searchQuestionAndUnPublished(String mail) {
        clientMailFiels().setValue(mail);
        searchBtn().click();
        faqQuestionByMail(mail).shouldBe(appear);
        checkboxFaqQuestionByMail(mail).click();
        unpublishedBtn().click();
        confirm();
        unpublishedSplashyFaqQuestionByMail(mail).shouldBe(appear);
        sleep(2);
        return this;
    }

    private SelenideElement clientMailFiels() {
        return $(By.id("form_Filter[email]"));
    }

    private SelenideElement searchBtn() {
        return $(By.xpath("//input[@class='btn btn-success search']"));
    }

    private SelenideElement faqQuestionByMail(String mail) {
        return $(By.xpath("//*[contains(text(), '" + mail + "')]"));
    }

    private SelenideElement checkboxFaqQuestionByMail(String mail) {
        return $(By.xpath("//*[contains(text(), '" + mail + "')]/ancestor::tr/td/input"));
    }

    private SelenideElement publishedSplashyFaqQuestionByMail(String mail) {
        return $(By.xpath("//*[contains(text(),'" + mail + "')]/ancestor::tr//i[@class='splashy-okay']"));
    }

    private SelenideElement unpublishedSplashyFaqQuestionByMail(String mail) {
        return $(By.xpath("//*[contains(text(), '" + mail + "')]/ancestor::tr//i[@class='splashy-warning']"));
    }

    private SelenideElement publishedBtn() {
        return $(By.xpath("//button[@class='btn btn-success control-publish']"));
    }

    private SelenideElement unpublishedBtn() {
        return $(By.xpath("//button[@class='btn btn-default control-disable']"));
    }

    public SelenideElement searchTextOnPage(String textForSearch) {
        return $x("//*[contains(text(),'" + textForSearch + "')]");
    }

    @Step("Check review in aws. Reviews_aws")
    public Reviews_aws checkReviewInAWS(String randomEmail, String reviewMessage) {
        searchTextOnPage(randomEmail).shouldBe(visible);
        searchTextOnPage(reviewMessage).shouldBe(visible);
        return this;
    }
}
