package ATD;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Reviews_Confirmation_page {

    SelenideElement reviewsConfirmationMessge() {
        return $(".icon_faq > p");
    }

}
