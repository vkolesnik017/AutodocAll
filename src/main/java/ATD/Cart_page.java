package ATD;


import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

class Cart_page {

    CartAccount_page nextButtonClick() {
        $(By.xpath("//a[@data-ga-action='Next_click']")).click();
        return page(CartAccount_page.class);
    }
}
