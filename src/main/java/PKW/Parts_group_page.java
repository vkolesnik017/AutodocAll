package PKW;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;


public class Parts_group_page {


    SelenideElement titlePage() {
        return $x("//div[@class='top_title']/h2");
    }


}
