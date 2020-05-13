package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class LKW_Error_page {

    SelenideElement headlineInHeader() {return $x("//div[@class='title_page']");}

    SelenideElement mainErrorImage() {return $x("//div[@class='img']");}
}
