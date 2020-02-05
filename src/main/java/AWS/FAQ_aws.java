package AWS;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class FAQ_aws {

    public SelenideElement faqResponseInput() { return $x("//*[@id='Question[answer]']"); }

    public SelenideElement faqAWSsubmitButton() { return $(".submit-faq"); }

    public SelenideElement searchTextOnPage(String textForSearch) {
       return $x("//*[contains(text(),'" + textForSearch + "')]");

    }
}
