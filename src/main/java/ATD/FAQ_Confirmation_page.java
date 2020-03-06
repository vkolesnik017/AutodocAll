package ATD;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class FAQ_Confirmation_page {

     SelenideElement faqConfirmationMessge() { return $(".icon_faq > p"); }

}
