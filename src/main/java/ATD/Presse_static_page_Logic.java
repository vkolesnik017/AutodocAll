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
import java.net.URL;

import static Common.CommonMethods.checkingContainsUrl;
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
        preReleaseBlock().shouldBe(visible, exist);
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

    @Step("Checking the text in the Hilft Article. Presse_static_page")
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
            articleTitle().get(i).shouldBe(visible).scrollIntoView("{block: \"center\"}");
            articleTitle().get(i).click();
            switchTo().window(1);
            String url = url();
            Assert.assertTrue(url.contains("pdf"));
            closeWindow();
            switchTo().window(0);
        }
        return this;
    }

    @Step("Checking the content in the opened pdf article . Presse_static_page")
    public static String readPdfContent(String url) throws IOException {
        URL pdfUrl = new URL(url);
        InputStream in = pdfUrl.openStream();
        BufferedInputStream bf = new BufferedInputStream(in);
        PDDocument doc = PDDocument.load(bf);
        String content = new PDFTextStripper().getText(doc).replaceAll(" ", "").replaceAll("\\W", "");
        bf.close();
        return content;
    }

    @Step("Checking the download pdf link . Presse_static_page")
    public Presse_static_page_Logic checkingTheDownloadsPDF() throws IOException {
        for (int i = 0; i < downloadPDF().size(); i++) {
            downloadPDF().get(i).scrollIntoView("{block: \"center\"}");
            File report = downloadPDF().get(i).download();
            report.delete();
        }
        return this;
    }

    @Step("Checking the download jpg link  . Presse_static_page")
    public Presse_static_page_Logic checkingTheDownloadsJPG() throws IOException {
        for (int i = 0; i < downloadJPG().size(); i++) {
            File report = downloadJPG().get(i).download();
            report.delete();
        }
        return this;
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
        hilftArticleDownloadPDF().shouldBe(visible).scrollIntoView("{block: \"center\"}");
        hilftArticleDownloadPDF().click();
        switchTo().window(1);
        String url = url();
        Assert.assertTrue(url.contains("pdf"));
        closeWindow();
        switchTo().window(0);
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
    public Presse_static_page_Logic checkingTheActiveArticle() {
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

    @Step("Checking the back and forward buttons in the slider. Presse_static_page")
    public Presse_static_page_Logic checkingTheBackForwardButtons() {
        String firstArticle = activeArticlesInSliderFive().first().getAttribute("url");
        autodocPresseButtonForward().click();
        String firstArticleAfterClick = activeArticlesInSliderFive().first().getAttribute("url");
        Assert.assertNotEquals(firstArticleAfterClick, firstArticle);
        for (int i = 0; i < activeArticlesInSliderFive().size(); i++) {
            activeArticlesInSlider().get(i).shouldBe(visible);
        }
        autodocPresseButtonBack().click();
        String firstArticleAfterClickBack = activeArticlesInSliderFive().first().getAttribute("url");
        Assert.assertEquals(firstArticleAfterClickBack, firstArticle);
        return this;
    }

    @Step("Checking the images in the slider. Presse_static_page")
    public Presse_static_page_Logic checkingTheImagesStatusCode() throws IOException {
        for (int i = 0; i < imagesOfTheArticlesInSlider().size(); i++) {
            String linkInsideImage = imagesOfTheArticlesInSlider().get(i).getAttribute("src");
            URL url = new URL(linkInsideImage);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setInstanceFollowRedirects(true);
            int responseCode = http.getResponseCode();
            assertEquals(responseCode, 200);
        }
        autodocPresseBlock().shouldBe(visible);
        autodocPresseBlockTitle().shouldBe(visible);
        Assert.assertFalse(autodocPresseBlockTitle().text().isEmpty());
        return this;
    }

    @Step("Checking the block with the presentation . Presse_static_page")
    public Presse_static_page_Logic checkingThePresentation(String expectedUrl) {
        autodocPresseTitlePresentation().shouldBe(visible);
        Assert.assertFalse(autodocPresseTitlePresentation().text().isEmpty());
        blockWithPresentation().shouldBe(exist).scrollIntoView("{block: \"center\"}").click();
        switchTo().window(1);
        checkingContainsUrl(expectedUrl);
        closeWindow();
        switchTo().window(0);
        return this;
    }

    @Step("Checking the image in the presentation block. Presse_static_page")
    public Presse_static_page_Logic checkingThePresentationImage() throws IOException {
        for (int i = 0; i < activeImagesInPresentationBlock().size(); i++) {
            activeImagesInPresentationBlock().get(i).isDisplayed();
            String linkInsideImage = activeImagesInPresentationBlock().get(i).getAttribute("src");
            URL url = new URL(linkInsideImage);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setInstanceFollowRedirects(true);
            int responseCode = http.getResponseCode();
            assertEquals(responseCode, 200);
        }
        return this;
    }

    @Step("Checking the download of the image in the presentation block. Presse_static_page")
    public Presse_static_page_Logic checkingTheDownloadImage(String pathname) {
        for (int i = 0; i < imageElement().size(); i++) {
            String attribute = imageElement().get(i).getAttribute("download");
            imageElement().get(i).scrollIntoView("{block: \"center\"}");
            imageElement().get(i).click();
            File file = new File(pathname + attribute);
            String nameFile = file.getName();
            sleep(1000);
            file.delete();
            Assert.assertEquals(attribute, nameFile);
            forwardButtonPresentation().click();
        }
        return this;
    }

    @Step("Checking the back and forward buttons in the slider in the presentation block. Presse_static_page")
    public Presse_static_page_Logic checkingTheBackForwardButtonsPresentation() {
        String firstImage = imageElementActiveSix().first().getAttribute("src");
        String mainImage = mainImageInPresentation().first().getAttribute("src");
        Assert.assertEquals(firstImage, mainImage);
        for (int i = 0; i < imageElementActiveSix().size(); i++) {
            imageElementActiveSix().get(i).shouldHave(attribute("src"));
        }
        sleep(1000);    // Sleep для временной проверки стабильности. Если все ок , заменить !
        forwardButtonPresentation().shouldBe(visible).click();
        String firstImageAfterClick = imageElementActiveSix().first().getAttribute("src");
        Assert.assertNotEquals(firstImage, firstImageAfterClick);
        for (int i = 0; i < imageElementActiveSix().size(); i++) {
            imageElementActiveSix().get(i).shouldBe(visible);
        }
        sleep(1000);   // Sleep для временной проверки стабильности. Если все ок , заменить !
        presseButtonBackPresentation().shouldBe(visible).click();
        String firstImageAfterClickBack = imageElementActiveSix().first().getAttribute("src");
        Assert.assertEquals(firstImage, firstImageAfterClickBack);
        presseGalleryContent().shouldBe(visible);
        Assert.assertFalse(presseGalleryContent().text().isEmpty());
        return this;
    }

    @Step("Checking the Main images status code in the presentation. Presse_static_page")
    public Presse_static_page_Logic checkingTheMainImagesStatusCode() throws IOException {
        for (int i = 0; i < mainImageInPresentation().size(); i++) {
            presseGalleryList().isDisplayed();
            String linkInsideImage = mainImageInPresentation().get(i).getAttribute("src");
            URL url = new URL(linkInsideImage);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setInstanceFollowRedirects(true);
            int responseCode = http.getResponseCode();
            assertEquals(responseCode, 200);
        }
        return this;
    }

    @Step("Check presence general element in presse gallery block. Presse_static_page")
    public Presse_static_page_Logic checkGeneralElementInGalleryBlock() {
        presseGalleryHeader().scrollIntoView("{block: \"center\"}").shouldBe(visible).shouldHave(text("Galerie"));
        presseGalleryList().shouldBe(visible);
        presseGalleryContent().shouldBe(visible);
        return this;
    }

    @Step("Click second img element and checks that it is displayed in gallery slide. Presse_static_page")
    public Presse_static_page_Logic clickSecondImgAndCheckDisplayedInGallery() {
        sleep(2000);
        String linkImg = secondImageElementActive().scrollIntoView("{block: \"center\"}").getAttribute("src");
        secondImageElementActive().click();
        sleep(2000);
        String linkImgInActiveSlide = presseGalleryActiveSlide().getAttribute("src");
        Assert.assertEquals(linkImg, linkImgInActiveSlide);
        presseButtonBackPresentation().shouldBe(visible).click();
        return this;
    }

    @Step("Check presence block more about autodoc and check the rdf opening. Presse_static_page")
    public Presse_static_page_Logic checkBlockMoreAboutAutodoc() {
        blockMoreAboutAutodoc().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        headerInBlockMoreAboutAutodoc().shouldBe(visible).shouldHave(text("MEHR ÜBER AUTODOC"));
        pdfInBlockMoreAboutAutodoc().shouldBe(visible).click();
        switchTo().window(1);
        checkingContainsUrl("tmp/ATD2020.pdf");
        return this;
    }

}






