package ATD;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

class Profile_page {
    SelenideElement nameOfClient(){return $(By.xpath("//div[@data-tab-id='vehicle']//span[@class='name']"));}
}
