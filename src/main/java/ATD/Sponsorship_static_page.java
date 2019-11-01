package ATD;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

class Sponsorship_static_page {

    SelenideElement sponsorsHeader() { return $(By.cssSelector(".sponsors-header")); }

    SelenideElement raceGallery() { return $(By.xpath("//*[@class='race-gallery__wrapper']")); }

    SelenideElement mapText() { return $(By.xpath("//*[@class='add_part']/p")); }

    SelenideElement sendShipForm() { return $(By.cssSelector("#send_ship")); }

    SelenideElement submitShipDataButton() { return $(By.cssSelector("#submit_ship_data")); }

    SelenideElement emailError() { return $(By.cssSelector(".email_error")); }
}
