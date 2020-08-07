package PKW;

import io.qameta.allure.Step;
import org.testng.Assert;

public class Profile_page_Logic extends Profile_page {

    @Step("Checking presence id user on page. Profile_page")
    public Profile_page_Logic checkingPresenceIdUser() {
        Assert.assertFalse(idUser().text().isEmpty());
        return this;
    }
}
