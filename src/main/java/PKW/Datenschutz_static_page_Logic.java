package PKW;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static Common.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.switchTo;

public class Datenschutz_static_page_Logic extends Datenschutz_static_page {


    @Step("Gets the status of the links code. Datenschutz_static_page")
    public Datenschutz_static_page_Logic getStatusLinksCode() throws IOException {
        textBlock().shouldBe(visible);
        Assert.assertFalse(textBlock().text().isEmpty());
        for (int i = 0; i < linksOnThePage().size(); i++) {
            linksOnThePage().get(i).isDisplayed();
            if (linksOnThePage().get(i).has(text("@"))) {
                continue;
            }
            String linksOnThePage = linksOnThePage().get(i).getAttribute("href");
            URL url = new URL(linksOnThePage);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setInstanceFollowRedirects(true);
            int responseCode = http.getResponseCode();
            if (responseCode == 301 || responseCode == 200) {
                continue;
            } else {
                Assert.fail("The status code is not 200 or 301");
            }
        }
        return this;
    }

    @Step("Check the href attribute in the email. Datenschutz_static_page")
    public Datenschutz_static_page_Logic checkHrefAttributeOnEmails() throws IOException {
        for (int i = 0; i < emailPkwLinks().size(); i++) {
            emailPkwLinks().get(i).shouldHave(attribute("href", "mailto:privacy-policy@pkwteile.de"));
            return this;
        }
        emailKlarna().shouldHave(attribute("href", "mailto:datenschutz@klarna.de"));
        emailTrustpilot().shouldHave(attribute("href", "mailto:support@trustpilot.com"));
        return this;
    }

    @Step("Checks the clickable links, pdf download and acrobat reader buttons. Datenschutz_static_page")
    public Datenschutz_static_page_Logic checkDownloadPdf() {
        pdfDownloadButton().shouldHave(attribute("class", "link"), attribute("url", "pdf/datenschutz"));
        acrobatReaderButton().click();
        switchTo().window(1);
        checkingContainsUrl("/reader/");
        closeWindow();
        switchTo().window(0);
        return this;
    }
}
