package ATD;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Sponsorship_static_page {

    public SelenideElement sponsorsHeader() { return $(By.cssSelector(".sponsors-header")); }

    public SelenideElement raceGallery() { return $(By.xpath("//*[@class='race-gallery__wrapper']")); }

    public SelenideElement mapText() { return $(By.xpath("//*[@class='add_part']/p")); }

    public SelenideElement sendShipForm() { return $(By.cssSelector("#send_ship")); }

    public SelenideElement submitShipDataButton() { return $(By.cssSelector("#submit_ship_data")); }

    public SelenideElement emailError() { return $(By.cssSelector(".email_error")); }
}
