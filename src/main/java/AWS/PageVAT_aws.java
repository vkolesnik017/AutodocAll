package AWS;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class PageVAT_aws {

    public String pageVAT_aws = "https://aws.autodoc.de/prices/country/tva/edit";

    private SelenideElement vatForGB() {
        return $x("//*[@data-letter='U']//td//*[@id='form_tva']");
    }

    @Step("Get VAT for United Kingdom. PageVAT_aws")
    public String getVatForGB() {
        open(pageVAT_aws);
        new Login_aws().loginInAws();
        String vatGB = vatForGB().getText();
        return vatGB;
    }
}
