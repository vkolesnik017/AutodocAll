package ATD;

import Common.DataBase;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static Common.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;

public class Group_list_page_Logic extends Group_list_page {

    @Step("Checks elements in the pdf Manual block. Group_list_page")
    public Group_list_page_Logic checkElementsInPDFManualBlock() {
        pdfManualTitleBlock().shouldBe(visible);
        Assert.assertFalse(pdfManualTitleBlock().text().isEmpty());
        for (int i = 0; i < previewImages().size(); i++) {
            previewImages().get(i).shouldBe(visible);
            titlesOfManuals().get(i).shouldBe(visible);
            Assert.assertFalse(titlesOfManuals().get(i).text().isEmpty());
            downloadLinkOfManuals().get(i).shouldBe(visible);
            Assert.assertFalse(downloadLinkOfManuals().get(i).text().isEmpty());
            sizeFile().get(i).shouldBe(visible);
            Assert.assertFalse(sizeFile().get(i).text().isEmpty());
            typeFile().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("Checks elements in the pdf Manual block. Group_list_page")
    public Group_list_page_Logic checkTransitionToClubPageFromPDFManualBlock() throws SQLException, IOException {
        pdfManualBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        for (int i = 0; i < previewImages().size(); i++) {
            titlesOfManuals().get(i).shouldBe(visible).click();
            switchTo().window(1);
            checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "club_manuals_home"));
            closeWindow();
            switchTo().window(0);
            String linkInsideTitle = titlesOfManuals().get(i).getAttribute("href");
            URL url = new URL(linkInsideTitle);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setInstanceFollowRedirects(true);
            int responseCode = http.getResponseCode();
            assertEquals(responseCode, 200);
        }
        return this;
    }

    @Step("Checks downloads of the manuals. Group_list_page")
    public Group_list_page_Logic checkDownloadsOfManuals() throws IOException {
        pdfManualBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        for (int i = 0; i < downloadLinkOfManuals().size(); i++) {
            downloadLinkOfManuals().get(i).click();
            sleep(5000);
            String titlePDF = titlesOfManuals().get(i).getAttribute("title").replaceAll("^\\s*(([^\\s]+\\s*){1,5})", "");
            String nameFile = downloadLinkOfManuals().get(i).getAttribute("url").replaceAll("^.+\\/", "");
            Common.File.assertThatPdfContainsText("C:/Users/User/Downloads/" + nameFile + "", titlePDF);
        }
        return this;
    }

    @Step(": from. Group_list_page")
    public AutodocClub_page_Logic clickBannerAutodocClub() {
        return new Product_page_Logic().clickBannerAutodocClub();
    }

    @Step(": from. Group_list_page")
    public String getUrlAutodocClubFromBannerAutodocClub() {
        return new Product_page_Logic().getUrlAutodocClubFromBannerAutodocClub();
    }

    @Step("presence Of TOP Products Block. Group_list_page")
    public Group_list_page_Logic presenceOfTopProductsBlock() {
        topProductsBlock().shouldBe(visible);
        return this;
    }

    @Step("display of Headline Of Seo Block. Group_list_page")
    public Group_list_page_Logic displayHeadlineOfSeoBlock(String expectedHeadline) {
        headlineSeoText().shouldBe(visible).shouldHave(text(expectedHeadline));
        return this;
    }

    @Step("check Titles Of Seo Block. Group_list_page")
    public Group_list_page_Logic checkTitlesOfSeoBlock(List<String> expectedTitles) {
        List<String> frontSeoTitles = titlesOfSeoBlock().stream().map(n -> n.getText()).collect(Collectors.toList());
        Assert.assertEquals(frontSeoTitles, expectedTitles);
        return this;
    }
}
