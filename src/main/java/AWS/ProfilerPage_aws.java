package AWS;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class ProfilerPage_aws {

    public static String profilerPage_aws = "https://aws.autodoc.de/price/profiler";

    private SelenideElement inputOrderId() {
        return $x("//input[@name='orderId']");
    }

    private SelenideElement inputArticleId() {
        return $x("//input[@name='orderArticleId']");
    }

    private SelenideElement submitBtn() {
        return $x("//button[@class='btn btn-primary']");
    }

    private SelenideElement taxFormula() {
        return $x("(//table[@class='table table-condensed table-bordered']/tbody)[2]//tr[4]//td[2]");
    }

    private SelenideElement standardMultiplier() {
        return $x("(//table[@class='table table-condensed table-bordered']/tbody)[2]//tr[1]//td[2]");
    }

    private SelenideElement totalPrice() {
        return $x("(//table[@class='table table-condensed table-bordered']/tbody)[2]//tr[5]//td[2]");
    }


    @Step("Filling filds OrderId {orderId} and ArticleId {articleId}. ProfilerPage_aws")
    public ProfilerPage_aws fillingFieldsOrderIdAndArticleId(String orderId, String articleId) {
        inputOrderId().shouldBe(visible).setValue(orderId);
        inputArticleId().shouldBe(visible).setValue(articleId);
        submitBtn().shouldBe(visible).click();
        return this;
    }

    @Step("Checks VAT {expectedVAT} in tax formula")
    public ProfilerPage_aws checkVatInTazFormula(String expectedVAT) {
        taxFormula().shouldBe(visible);
        taxFormula().shouldHave(text(expectedVAT));
        return this;
    }

    @Step("Check standard multiplier. ProfilerPage_aws")
    public ProfilerPage_aws checkStandardMultiplier(String expectedMultiplier) {
        standardMultiplier().shouldBe(visible);
        standardMultiplier().shouldHave(text(expectedMultiplier));
        return this;
    }

    @Step("Check total price. ProfilerPage_aws")
    public ProfilerPage_aws checkTotalPrice(float totalPrice) {
        totalPrice().shouldBe(visible);
        String price = Float.toString(totalPrice);
        totalPrice().shouldHave(exactText(price));
        return this;
    }


}

