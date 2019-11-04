package ATD;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class Partnership_static_page {

    public SelenideElement logo() {
        return $(By.cssSelector(".logo>a>img"));
    }

    public SelenideElement title() { return $(byCssSelector(".partner_title")); }

    public SelenideElement logo2() { return $(byXpath("//*[@class='col one']/img")); }

    public SelenideElement textPartner() { return $(By.xpath("//*[@class='col one']/p")); }

    public SelenideElement textPartner2() { return $(By.xpath("//*[@class='col two']")); }

    public SelenideElement textPartner3() { return $(By.xpath("//*[@class='col three']")); }

    public SelenideElement offerTitle() { return $(By.cssSelector(".p_title")); }

    public SelenideElement offerBlock1() { return $(By.xpath("//*[@class='part_with_us']/ul/li[1]")); }

    public SelenideElement offerImage1() { return $(By.xpath("//*[@class='part_with_us']/ul/li[1]/div/img")); }

    public SelenideElement offerBlock2() { return $(By.xpath("//*[@class='part_with_us']/ul/li[2]")); }

    public SelenideElement offerImage2() { return $(By.xpath("//*[@class='part_with_us']/ul/li[2]/div/img")); }

    public SelenideElement mapText() { return $(By.xpath("//*[@class='add_part']/p")); }

    public SelenideElement sendShipForm() { return $(By.cssSelector("#send_ship")); }

    public SelenideElement submitShipDataButton() { return $(By.cssSelector("#submit_ship_data")); }

    public SelenideElement emailError() { return $(By.cssSelector(".email_error")); }
}
