package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class LKW_maker_page {

    protected SelenideElement titleOfSidebar() {
        return $x("//div[@class='links-title']");
    }
}
