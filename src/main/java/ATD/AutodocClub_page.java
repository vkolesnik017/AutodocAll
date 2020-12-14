package ATD;


import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class AutodocClub_page {


    SelenideElement iconAutodocClub() {
        return $x("//div[@class='seo-tool__container']//img[@alt='club.autodoc.de']");
    }


}
