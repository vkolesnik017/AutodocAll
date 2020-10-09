package PKW;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;


public class Supplier_page {

    SelenideElement nameSecondBreadCrumb() {
        return $x("//div[@class='crabs']//span[@class='last']");
    }
}
