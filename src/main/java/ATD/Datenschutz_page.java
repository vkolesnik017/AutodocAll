package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;

public class Datenschutz_page {

    SelenideElement titlePage() {
        return $(By.xpath("//div[@class='head_name']"));
    }

    protected SelenideElement logo() {
        return $(By.cssSelector(".logo>a>img"));
    }

    SelenideElement mainBlock() {
        return $(By.cssSelector(".text_page.agb_page"));
    }

    protected ElementsCollection links() {
        return $$x("//div[@class='text_page agb_page']//a");
    }
}
