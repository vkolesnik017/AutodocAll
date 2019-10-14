package ATD;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

class Main {

    // Footer
    SelenideElement footerForm() {
        return $(By.xpath("//footer"));
    }

    Contact_static_page clickContact() {
        $(By.xpath("//div[@class='inform_menu']/ul/li[4]/a")).click();
        return page(Contact_static_page.class);
    }
}
