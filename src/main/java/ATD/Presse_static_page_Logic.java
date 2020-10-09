package ATD;

import io.qameta.allure.Step;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.testng.Assert;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.testng.Assert.assertEquals;

public class Presse_static_page_Logic extends Presse_static_page {

    @Step("Checking the presence of the text in the blocks. Presse_static_page")
    public Presse_static_page_Logic checkingPresenceOfTheBlocks() {
        presseHeader().shouldBe(visible);
        presseHeaderTitle().shouldBe(visible);
        Assert.assertFalse(presseHeaderTitle().text().isEmpty());
        presseHeaderFirstText().shouldBe(visible);
        Assert.assertFalse(presseHeaderFirstText().text().isEmpty());
        presseHeaderSecondText().shouldBe(visible);
        Assert.assertFalse(presseHeaderFirstText().text().isEmpty());
        presseInfoBlock().shouldBe(visible, exist);
        presseInfoTitle().shouldBe(visible);
        Assert.assertFalse(presseInfoTitle().text().isEmpty());
        presseInfoText().shouldBe(visible);
        Assert.assertFalse(presseInfoTitle().text().isEmpty());
        presseFirstPersonBlock().shouldBe(visible, exist);
        presseSecondPersonBlock().shouldBe(visible, exist);
        return this;
    }

    @Step("Open the Articles block. Presse_static_page")
    public Presse_static_page_Logic openTheArticleBlock() {
        presseContentBlock().shouldBe(visible, exist);
        atdHilft().shouldBe(visible, exist);
        int amountCount;
        while (mehrButton().isDisplayed()) {
            amountCount = artTitle().size();
            mehrButton().scrollIntoView("{block: \"center\"}");
            mehrButton().click();
            artTitle().shouldHave(sizeGreaterThan(amountCount));
        }
        return this;
    }

    @Step("Open the Articles block. Presse_static_page")
    public Presse_static_page_Logic checkingTheTexts() {
        for (int i = 0; i < articleTexts().size(); i++) {
            articleTexts().get(i).shouldBe(visible);
            Assert.assertFalse(articleTexts().get(i).text().isEmpty());
        }
        return this;
    }

    @Step("Checking the titles and compare with titles in the pdf. Presse_static_page")
    public Presse_static_page_Logic checkingTheTitlesInTheCards() {
        for (int i = 0; i < articleTitle().size(); i++) {
            articleTitle().get(i).shouldBe(visible);
            String titleOfTheArticle = articleTitle().get(i).getText();
            articleTitle().get(i).click();
            switchTo().window(1);
            String url = url();

            try {
                String pdfContent = readPdfContent(url);
                Assert.assertTrue(pdfContent.contains(titleOfTheArticle.replaceAll("\\W", "")));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            closeWindow();
            switchTo().window(0);
        }
        return this;
    }

    @Step("Checking the download pdf link pdf . Presse_static_page")
    public Presse_static_page_Logic checkingTheDownloadsPDF() throws IOException {
        for (int i = 0; i < downloadPDF().size(); i++) {
            File report = downloadPDF().get(i).download();
            report.delete();
        }
        return this;
    }

    @Step("Checking the download jpg link jpg . Presse_static_page")
    public Presse_static_page_Logic checkingTheDownloadsJPG() throws IOException {
        for (int i = 0; i < downloadJPG().size(); i++) {
            File report = downloadJPG().get(i).download();
            report.delete();
        }
        return this;
    }

    @Step("Checking the content in the opened pdf article . Presse_static_page")
    public static String readPdfContent(String url) throws IOException {
        URL pdfUrl = new URL(url);
        InputStream in = pdfUrl.openStream();
        BufferedInputStream bf = new BufferedInputStream(in);
        PDDocument doc = PDDocument.load(bf);
        int numberOfPages = getPageCount(doc);
        System.out.println("The total number of pages " + numberOfPages);
        String content = new PDFTextStripper().getText(doc).replaceAll(" ", "").replaceAll("\\W", "");
        doc.close();
        return content;
    }

    @Step("Get the total number of pages in the pdf document. Presse_static_page")
    public static int getPageCount(PDDocument doc) {
        int pageCount = doc.getNumberOfPages();
        return pageCount;
    }

    @Step("Gets the status of the photo code. Presse_static_page")
    public Presse_static_page_Logic getStatusPhotoCod() throws IOException {
        if (pressePhotos().isDisplayed()) {
            String linkInsideImage = pressePhotos().getAttribute("src");
            URL url = new URL(linkInsideImage);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setInstanceFollowRedirects(true);
            int responseCode = http.getResponseCode();
            assertEquals(responseCode, 200);
        }
        return this;
    }

    @Step("Gets the status of the image code. Presse_static_page")
    public Presse_static_page_Logic getStatusImageCod() throws IOException {
        if (atdHilftImage().isDisplayed()) {
            String linkInsideImage = atdHilftImage().getAttribute("src");
            URL url = new URL(linkInsideImage);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setInstanceFollowRedirects(true);
            int responseCode = http.getResponseCode();
            assertEquals(responseCode, 200);
        }
        return this;
    }

    @Step("Checking the download pdf link pdf . Presse_static_page")
    public Presse_static_page_Logic checkingTheDownloadsPDFHilftBlock() throws IOException {
        File report = hilftArticleDownloadPDF().download();
        report.delete();
        return this;
    }

    @Step("Checking the title in the Hilft Block in the pdf. Presse_static_page")
    public Presse_static_page_Logic checkingTheTitleHilftBlockPDF() throws IOException {

        hilftArticleTitle().shouldBe(visible);
        String titleOfTheArticleHilft = hilftArticleTitle().getText();
        hilftArticleTitle().click();
        switchTo().window(1);
        String url = url();
        String pdfContent = readPdfContent(url);
        Assert.assertTrue(pdfContent.contains(titleOfTheArticleHilft.replaceAll("\\W", "")));
        closeWindow();
        switchTo().window(0);
        return this;
    }

    @Step("Checking the active articles in the slider . Presse_static_page")
    public Presse_static_page_Logic checkingTheActiveArticle() throws IOException {
        for (int i = 0; i < activeArticlesInSlider().size(); i++) {
            String attributeUrl = activeArticlesInSlider().get(i).getAttribute("url").replaceAll("(^.+\\/\\/)(\\w{3}\\.\\w{2,10}\\W?\\w{2,}\\.\\w{2,3})(.+)", "$2");
            activeArticlesInSlider().get(i).click();
            switchTo().window(1);
            String currentArticleUrl = url();
            Assert.assertTrue(currentArticleUrl.contains(attributeUrl));
            closeWindow();
            switchTo().window(0);
        }
        return this;
    }
}





