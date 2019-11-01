package ATD;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

class Partnership_static_page {

    SelenideElement logo() {
        return $(By.cssSelector(".logo>a>img"));
    }

    SelenideElement title() { return $(byCssSelector(".partner_title")); }

    SelenideElement logo2() { return $(byXpath("//*[@class='col one']/img")); }

    SelenideElement textPartner() { return $(By.xpath("//*[@class='col one']/p")); }

    SelenideElement textPartner2() { return $(By.xpath("//*[@class='col two']")); }

    SelenideElement textPartner3() { return $(By.xpath("//*[@class='col three']")); }

    SelenideElement offerTitle() { return $(By.cssSelector(".p_title")); }

    SelenideElement offerBlock1() { return $(By.xpath("//*[@class='part_with_us']/ul/li[1]")); }

    SelenideElement offerImage1() { return $(By.xpath("//*[@class='part_with_us']/ul/li[1]/div/img")); }

    SelenideElement offerBlock2() { return $(By.xpath("//*[@class='part_with_us']/ul/li[2]")); }

    SelenideElement offerImage2() { return $(By.xpath("//*[@class='part_with_us']/ul/li[2]/div/img")); }

    SelenideElement mapText() { return $(By.xpath("//*[@class='add_part']/p")); }

    SelenideElement sendShipForm() { return $(By.cssSelector("#send_ship")); }

    SelenideElement submitShipDataButton() { return $(By.cssSelector("#submit_ship_data")); }

    SelenideElement emailError() { return $(By.cssSelector(".email_error")); }
}
