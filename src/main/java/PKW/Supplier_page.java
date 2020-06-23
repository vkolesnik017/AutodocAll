package PKW;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;



public class Supplier_page {


    SelenideElement nameTitleOnPageBrand() {
        return $x("//div[@class='title']/h1");
    }


}
