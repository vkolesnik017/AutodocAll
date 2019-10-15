package ATD;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

class CartAccount_page {

    SelenideElement registrationFormEmailInput() {
        return $(By.xpath("//form[@class='registerForm']//input[@name='Email']"));
    }

    SelenideElement registrationFormPasswordInput() {
        return $(By.xpath("//form[@class='registerForm']//input[@name='Password']"));
    }

    CartAddress_page registrationFormNextButtonClick() {
        $(By.xpath("//form[@class='registerForm']//div[@class='button-continue']/a")).click();
        return page(CartAddress_page.class);
    }
}
