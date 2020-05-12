package ATD;


import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;



class Index_accessories_page {

    SelenideElement blockFeatures() {
        return $x("//div[@class='features__wrapp']");
    }
}
