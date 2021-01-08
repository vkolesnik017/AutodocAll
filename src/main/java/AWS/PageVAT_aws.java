package AWS;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class PageVAT_aws {

    public String pageVAT_aws = "https://aws.autodoc.de/prices/country/tva/edit";

    private SelenideElement vatForGB() {
        return $x("//*[@data-letter='U']//td//*[@data-country='11']");
    }

    private SelenideElement vatForDE() {
        return $x("//*[@data-letter='G']//td//*[@data-country='1']");
    }

    private SelenideElement vatForBE() {
        return $x("//*[@data-letter='B']//td//*[@data-country='5']");
    }

    private SelenideElement vatForIE() {
        return $x("//*[@data-letter='I']//td//*[@data-country='120']");
    }

    private SelenideElement vatForCH() {
        return $x("//*[@data-letter='S']//td//*[@data-country='3']");
    }

    private SelenideElement vatForNL() {
        return $x("//*[@data-letter='H']//td//*[@data-country='4']");
    }

    private SelenideElement vatForPL() {
        return $x("//*[@data-letter='P']//td//*[@data-country='15']");
    }

    @Step("Get VAT for United Kingdom. PageVAT_aws")
    public String getVatForGB() {
        open(pageVAT_aws);
        new Login_aws().loginInAws();
        String vatGB = vatForGB().getValue();
        return vatGB;
    }

    @Step("Get VAT for Germany. PageVAT_aws")
    public String getVatForDE() {
        open(pageVAT_aws);
        new Login_aws().loginInAws();
        String vatDE = vatForDE().getValue();
        return vatDE;
    }

    @Step("Get VAT for Belgium. PageVAT_aws")
    public String getVatForBE() {
        open(pageVAT_aws);
        new Login_aws().loginInAws();
        String vatBE = vatForBE().getValue();
        return vatBE;
    }

    @Step("Get VAT for Ireland. PageVAT_aws")
    public String getVatForIE() {
        open(pageVAT_aws);
        new Login_aws().loginInAws();
        String vatIE = vatForIE().getValue();
        return vatIE;
    }

    @Step("Get VAT for Switzerland. PageVAT_aws")
    public String getVatForCH() {
        open(pageVAT_aws);
        new Login_aws().loginInAws();
        String vatCH = vatForCH().getValue();
        return vatCH;
    }

    @Step("Get VAT for Poland. PageVAT_aws")
    public String getVatForPL() {
        open(pageVAT_aws);
        new Login_aws().loginInAws();
        String vatPL = vatForPL().getValue();
        return vatPL;
    }

    @Step("Get VAT for Holland. PageVAT_aws")
    public String getVatForNL() {
        open(pageVAT_aws);
        new Login_aws().loginInAws();
        String vatNL = vatForNL().getValue();
        return vatNL;
    }
}
