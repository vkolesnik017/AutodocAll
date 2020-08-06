package PKW;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class Profile_page {


    SelenideElement idUser() {
        return $x("//div[@id='content']//span[@style='float: right']");
    }
}
