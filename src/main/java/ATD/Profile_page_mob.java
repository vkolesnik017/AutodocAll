package ATD;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

class Profile_page_mob {

    SelenideElement addressBtn() {
        return $(By.xpath("//li[@class='icon directory']"));
    }
}
