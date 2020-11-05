package ATD;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Profile_page {

    public SelenideElement nameOfClient() {
        return $(byXpath("//div[@class='name_cash']//span[@class='name']"));
    }

    SelenideElement autodocLinkActiveFirst() {
        return $(By.xpath("//li[@class='plus-link active']"));
    }

    SelenideElement clientID() {
        return $(byXpath("//div[@class='top_title']/span"));
    }

    SelenideElement autodocPlusBtn() {
        return $x("//a[@data-ga-action='Sidebar_Plus']");
    }

    // history orders tab (page) (Meine Bestellungen)
    SelenideElement bestelldetailsButton(String numberOrder) {
        return $x("//td[@class='order-nr' and text()='" + numberOrder + "']/../td[@class='order-data']/a");
    }

    // details order page, appear after click bestelldetails Button
    SelenideElement returnOrReplaceItemButton() {
        return $(".returnOrdersPopupCaller");
    }

    // settings tab (Einstellungen)
    SelenideElement einstellungenActive() {
        return $(By.xpath("//li[@class='settings_link active']"));
    }

    public SelenideElement settingsTabBtn() {
        return $(byCssSelector(".settings_link"));
    }

    public SelenideElement oldPasswordFiled() {
        return $(byName("old_pass"));
    }

    public SelenideElement newPasswordField() {
        return $(byName("new_pass"));
    }

    public SelenideElement newPasswordConfirmField() {
        return $(byName("new_pass_confirm"));
    }

    public SelenideElement changePasswordBtn() {
        return $(byCssSelector(".password_submit"));
    }

    public SelenideElement closeSuccessfulPasswordChangePopup() {
        return $(byXpath("//*[@class='popup ']//*[contains(text(),'wurden aktualisiert')]/..//a"));
    }

    SelenideElement einstellungenCheckbox() {
        return $(By.xpath("//div[@class='change_pass another_email']//input[@type='checkbox']"));
    }

    SelenideElement popupAfterCheckedCheckbox() {
        return $(By.id("popup_update"));
    }

    SelenideElement closePopupAfterCheckedCheckbox() {
        return $(By.xpath("//div[@class='popup_content']//a[@class='close']"));
    }

}
