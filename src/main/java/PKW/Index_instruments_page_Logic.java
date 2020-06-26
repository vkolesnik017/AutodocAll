package PKW;

import io.qameta.allure.Step;
import org.testng.Assert;

public class Index_instruments_page_Logic extends Index_instruments_page {

    @Step("Checking presence title main page. Index_instruments_page")
    public Index_instruments_page_Logic checkingPresenceTitleMainPage() {
        Assert.assertFalse(titleMainPage().text().isEmpty());
        return this;
    }


}
