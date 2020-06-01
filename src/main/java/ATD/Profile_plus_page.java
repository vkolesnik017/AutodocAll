package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Profile_plus_page {

    SelenideElement nameOfUser() {return $x("//span[@class='name']");}
}
