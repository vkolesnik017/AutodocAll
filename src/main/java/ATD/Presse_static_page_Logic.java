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
import java.util.ArrayList;
import java.util.List;

import static Common.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.testng.Assert.assertEquals;

public class Presse_static_page_Logic extends Presse_static_page {

    @Step("Checking the Press-Release Header and Press-Inform blocks. Presse_static_page")
    public Presse_static_page_Logic checkingPresReleaseHeaderAndPressInfoBlock() {
        pressHeaderBlock().shouldBe(visible);
        pressHeaderTitle().shouldBe(visible);
        Assert.assertFalse(pressHeaderTitle().text().isEmpty());
        pressHeaderHeaderAdvantage().shouldBe(visible);
        Assert.assertFalse(pressHeaderHeaderAdvantage().text().isEmpty());
        pressHeaderMainText().shouldBe(visible);
        Assert.assertFalse(pressHeaderHeaderAdvantage().text().isEmpty());
        pressInfoBlock().shouldBe(visible, exist);
        pressInfoTitle().shouldBe(visible);
        Assert.assertFalse(pressInfoTitle().text().isEmpty());
        pressInfoMainText().shouldBe(visible);
        Assert.assertFalse(pressInfoTitle().text().isEmpty());
        pressInfoFirstPersonBlock().shouldBe(visible, exist);
        pressInfoSecondPersonBlock().shouldBe(visible, exist);
        return this;
    }

    @Step("Checks and open the Articles block. Presse_static_page")
    public Presse_static_page_Logic checkAndOpenTheArticleBlock() {
        pressContentReleaseBlock().shouldBe(visible, exist);
        int amountCount;
        while (pressContentMoreButton().isDisplayed()) {
            amountCount = pressContentArticleTitle().size();
            pressContentMoreButton().scrollIntoView("{block: \"center\"}");
            pressContentMoreButton().click();
            pressContentArticleTitle().shouldHave(sizeGreaterThan(amountCount));
        }
        return this;
    }

    @Step("Checking the text in the Help Article. Presse_static_page")
    public Presse_static_page_Logic checkingTheTexts() {
        for (int i = 0; i < helpArticleTexts().size(); i++) {
            helpArticleTexts().get(i).shouldBe(visible);
            Assert.assertFalse(helpArticleTexts().get(i).text().isEmpty());
        }
        return this;
    }

    @Step("Checking the titles and compare with titles in the pdf. Presse_static_page")
    public Presse_static_page_Logic checkingTheTitlesInTheCards() {
        for (int i = 0; i < pressContentArticleTitle().size(); i++) {
            pressContentArticleTitle().get(i).shouldBe(visible).scrollIntoView("{block: \"center\"}");
            pressContentArticleTitle().get(i).click();
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

    @Step("Checking the download pdf link in Press Content block. Presse_static_page")
    public Presse_static_page_Logic checkingTheDownloadsPDF() throws IOException {
        for (int i = 0; i < pressContentBtnDownloadPDF().size(); i++) {
            pressContentBtnDownloadPDF().get(i).scrollIntoView("{block: \"center\"}");
            File report = pressContentBtnDownloadPDF().get(i).download();
            report.delete();
        }
        return this;
    }

    @Step("Checking the download jpg link in Press Content block. Presse_static_page")
    public Presse_static_page_Logic checkingTheDownloadsJPG() throws IOException {
        for (int i = 0; i < pressContentBtnDownloadJPG().size(); i++) {
            File report = pressContentBtnDownloadJPG().get(i).download();
            report.delete();
            sleep(2000);
        }
        return this;
    }

    @Step("Gets the status code for img in Press Info block. Presse_static_page")
    public Presse_static_page_Logic getStatusCodForImgInPressInfoBlock() throws IOException {
        if (pressPhotos().isDisplayed()) {
            String linkInsideImage = pressPhotos().getAttribute("src");
            URL url = new URL(linkInsideImage);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setInstanceFollowRedirects(true);
            int responseCode = http.getResponseCode();
            assertEquals(responseCode, 200);
        }
        return this;
    }

    @Step("Gets the status code for img in Help block. Presse_static_page")
    public Presse_static_page_Logic getStatusCodForImgInHelpBlock() throws IOException {
        if (helpImage().isDisplayed()) {
            String linkInsideImage = helpImage().getAttribute("src");
            URL url = new URL(linkInsideImage);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setInstanceFollowRedirects(true);
            int responseCode = http.getResponseCode();
            assertEquals(responseCode, 200);
        }
        return this;
    }

    @Step("Download pdf and checking link pdf in Autodoc help block. Presse_static_page")
    public Presse_static_page_Logic checkingTheDownloadsPdfInHelpBlock() throws IOException {
        articleDownloadPdfInHelpBlock().shouldBe(visible).scrollIntoView("{block: \"center\"}");
        articleDownloadPdfInHelpBlock().click();
        switchTo().window(1);
        String url = url();
        Assert.assertTrue(url.contains("pdf"));
        closeWindow();
        switchTo().window(0);
        return this;
    }

    @Step("Checks the title in the Autodoc help block against the title in the pdf. Presse_static_page")
    public Presse_static_page_Logic checkingTitleInHelpBlockAgainstInPDF() throws IOException {
        helpArticleTitle().shouldBe(visible);
        String titleOfTheArticleHelp = helpArticleTitle().getText();
        helpArticleTitle().click();
        switchTo().window(1);
        String url = url();
        String pdfContent = readPdfContent(url);
        Assert.assertTrue(pdfContent.contains(titleOfTheArticleHelp.replaceAll("\\W", "")));
        closeWindow();
        switchTo().window(0);
        return this;
    }

    @Step("Checking the active articles in the slider for atd-presse block. Presse_static_page")
    public Presse_static_page_Logic checkingTheActiveArticleForAtdPresseBlock() {
        for (int i = 0; i < autodocPressActiveArticlesInSlider().size(); i++) {
            String attributeUrl = autodocPressActiveArticlesInSlider().get(i).getAttribute("url").replaceAll("(^.+\\/\\/)(\\w{3}\\.\\w{2,10}\\W?\\w{2,}\\.\\w{2,3})(.+)", "$2");
            autodocPressActiveArticlesInSlider().get(i).click();
            switchTo().window(1);
            String currentArticleUrl = url();
            Assert.assertTrue(currentArticleUrl.contains(attributeUrl));
            closeWindow();
            switchTo().window(0);
        }
        return this;
    }

    @Step("Checking the back and forward buttons in the slider for atd-presse block. Presse_static_page")
    public Presse_static_page_Logic checkingTheBackForwardButtonsForAtdPresseBlock() {
        String firstArticle = activeArticlesInSliderFive().first().getAttribute("url");
        autodocPressButtonForward().shouldBe(visible).click();
        sleep(2000);
        String firstArticleAfterClick = activeArticlesInSliderFive().first().getAttribute("url");
        Assert.assertNotEquals(firstArticleAfterClick, firstArticle);
        for (int i = 0; i < activeArticlesInSliderFive().size(); i++) {
            autodocPressActiveArticlesInSlider().get(i).shouldBe(visible);
        }
        autodocPressButtonBack().shouldBe(visible).click();
        sleep(2000);
        String firstArticleAfterClickBack = activeArticlesInSliderFive().first().getAttribute("url");
        Assert.assertEquals(firstArticleAfterClickBack, firstArticle);
        return this;
    }

    @Step("Checking the images in the slider for atd presse block. Presse_static_page")
    public Presse_static_page_Logic checkingTheImagesStatusCodeForAtdPresseBlock() throws IOException {
        for (int i = 0; i < autodocPressImagesOfTheArticlesInSlider().size(); i++) {
            String linkInsideImage = autodocPressImagesOfTheArticlesInSlider().get(i).getAttribute("src");
            URL url = new URL(linkInsideImage);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setInstanceFollowRedirects(true);
            int responseCode = http.getResponseCode();
            assertEquals(responseCode, 200);
        }
        autodocPressBlock().shouldBe(visible);
        autodocPressBlockTitle().shouldBe(visible);
        Assert.assertFalse(autodocPressBlockTitle().text().isEmpty());
        return this;
    }

    @Step("Compares the current logo link to the expected one. Presse_static_page")
    public Presse_static_page_Logic comparesCurrentLogoLinkToExpectedOne(List <String> expectedLink) {
        autodocPressSliderList().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        ArrayList <String> listWithActualLinksToLogo = new ArrayList<>();
        for (int i = 0; i < autodocPressImagesOfTheArticlesInSlider().size(); i++) {
            String linkImage = autodocPressImagesOfTheArticlesInSlider().get(i).getAttribute("src");
            listWithActualLinksToLogo.add(linkImage);
        }
        System.out.println(expectedLink);
        System.out.println(listWithActualLinksToLogo);
        Assert.assertEquals(listWithActualLinksToLogo, expectedLink);
        return this;
    }

    @Step("Checking the block with the presentation. Presse_static_page")
    public Presse_static_page_Logic checkingThePresentation(String expectedUrl) {
        headerInBlockMoreAboutAutodoc().shouldBe(visible);
        Assert.assertFalse(headerInBlockMoreAboutAutodoc().text().isEmpty());
        pdfInBlockMoreAboutAutodoc().shouldBe(exist).scrollIntoView("{block: \"center\"}").click();
        switchTo().window(1);
        checkingContainsUrl(expectedUrl);
        closeWindow();
        switchTo().window(0);
        return this;
    }

    @Step("Checking the image in the presentation block. Presse_static_page")
    public Presse_static_page_Logic checkingThePresentationImage() throws IOException {
        for (int i = 0; i < activeImagesInGalleryBlock().size(); i++) {
            activeImagesInGalleryBlock().get(i).isDisplayed();
            String linkInsideImage = activeImagesInGalleryBlock().get(i).getAttribute("src");
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
        for (int i = 0; i < allBtnSaveInGalleryBlock().size(); i++) {
            String attribute = allBtnSaveInGalleryBlock().get(i).getAttribute("download");
            allBtnSaveInGalleryBlock().get(i).scrollIntoView("{block: \"center\"}");
            allBtnSaveInGalleryBlock().get(i).click();
            File file = new File(pathname + attribute);
            String nameFile = file.getName();
            sleep(1000);
            file.delete();
            Assert.assertEquals(attribute, nameFile);
            nexSlideBtnInGalleryBlock().click();
        }
        return this;
    }

    @Step("Checking the back and forward buttons in the slider in the Gallery block. Presse_static_page")
    public Presse_static_page_Logic checkingTheBackForwardButtonsInGalleryBlock() {
        String firstImage = sixActiveImageInGalleryBlock().first().getAttribute("src");
        String mainImage = mainImageInGalleryBlock().first().getAttribute("src");
        Assert.assertEquals(firstImage, mainImage);
        for (int i = 0; i < sixActiveImageInGalleryBlock().size(); i++) {
            sixActiveImageInGalleryBlock().get(i).shouldHave(attribute("src"));
        }
        sleep(1000);    // Sleep для временной проверки стабильности. Если все ок , заменить !
        nexSlideBtnInGalleryBlock().shouldBe(visible).click();
        String firstImageAfterClick = sixActiveImageInGalleryBlock().first().getAttribute("src");
        Assert.assertNotEquals(firstImage, firstImageAfterClick);
        for (int i = 0; i < sixActiveImageInGalleryBlock().size(); i++) {
            sixActiveImageInGalleryBlock().get(i).shouldBe(visible);
        }
        sleep(1000);   // Sleep для временной проверки стабильности. Если все ок , заменить !
        previousSlideBtnInGalleryBlock().shouldBe(visible).click();
        String firstImageAfterClickBack = sixActiveImageInGalleryBlock().first().getAttribute("src");
        Assert.assertEquals(firstImage, firstImageAfterClickBack);
        pressGalleryContentBlock().shouldBe(visible);
        Assert.assertFalse(pressGalleryContentBlock().text().isEmpty());
        return this;
    }

    @Step("Checking the Main images status code in the presentation. Presse_static_page")
    public Presse_static_page_Logic checkingTheMainImagesStatusCode() throws IOException {
        for (int i = 0; i < mainImageInGalleryBlock().size(); i++) {
            pressGalleryListBlock().isDisplayed();
            String linkInsideImage = mainImageInGalleryBlock().get(i).getAttribute("src");
            URL url = new URL(linkInsideImage);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setInstanceFollowRedirects(true);
            int responseCode = http.getResponseCode();
            assertEquals(responseCode, 200);
        }
        return this;
    }

    @Step("Check presence general element in presse Gallery block. Presse_static_page")
    public Presse_static_page_Logic checkGeneralElementInGalleryBlock() {
        pressGalleryHeaderBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible).shouldHave(text("Galerie"));
        pressGalleryListBlock().shouldBe(visible);
        pressGalleryContentBlock().shouldBe(visible);
        oneBtnSaveInGalleryBlock().shouldBe(visible);
        activeSlideInGalleryBlock().shouldBe(visible);
        nexSlideBtnInGalleryBlock().shouldBe(visible);
        previousSlideBtnInGalleryBlock().shouldBe(visible);
        return this;
    }

    @Step("Click second img element and checks that it is displayed in gallery slide. Presse_static_page")
    public Presse_static_page_Logic clickSecondImgAndCheckDisplayedInGallery() {
        sleep(2000);
        String linkImg = secondImageInGalleryBlock().scrollIntoView("{block: \"center\"}").getAttribute("src");
        secondImageInGalleryBlock().click();
        sleep(2000);
        String linkImgInActiveSlide = activeSlideInGalleryBlock().getAttribute("src");
        Assert.assertEquals(linkImg, linkImgInActiveSlide);
        previousSlideBtnInGalleryBlock().shouldBe(visible).click();
        return this;
    }

    @Step("Check elements in atd-help block. Presse_static_page")
    public Presse_static_page_Logic checkElementsInHelpBlock() {
        helpBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        helpImage().shouldBe(visible);
        articleDownloadPdfInHelpBlock().shouldBe(visible);
        return this;
    }

    @Step("Check presence block more about autodoc and check the rdf opening. Presse_static_page")
    public Presse_static_page_Logic checkBlockMoreAboutAutodoc() {
        blockMoreAboutAutodoc().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        headerInBlockMoreAboutAutodoc().shouldBe(visible).shouldHave(text("MEHR ÜBER AUTODOC"));
        pdfInBlockMoreAboutAutodoc().shouldBe(visible).click();
        switchTo().window(1);
        checkingContainsUrl("tmp/ATD2020.pdf");
        closeWindow();
        switchTo().window(0);
        return this;
    }

    @Step("Check elements in atd-press block. Presse_static_page")
    public Presse_static_page_Logic checkElementsInPresBlock(String expectedTitleText) {
        autodocPressBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        autodocPressBlockTitle().shouldBe(visible).shouldHave(text(expectedTitleText));
        autodocPressButtonForward().shouldBe(visible);
        autodocPressButtonBack().shouldBe(visible);
        autodocPressSliderList().shouldBe(visible);
        return this;
    }

    @Step("Check element in press Release block. Presse_static_page")
    public Presse_static_page_Logic checkElementInPressReleaseBlock(String expectedHeaderText) {
        pressContentReleaseBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        pressContentReleaseHeader().shouldBe(visible).shouldHave(text(expectedHeaderText));
        pressContentReleaseArticleBlock().shouldBe(visible);
        pressContentArticleDate().shouldBe(visible);
        for (int title = 0; title < pressContentArticleTitle().size(); title++) {
            pressContentArticleTitle().shouldHave(sizeGreaterThanOrEqual(4));
            pressContentArticleTitle().get(title).shouldBe(visible);
        }
        for (int img = 0; img < pressContentArticleImg().size(); img++) {
            pressContentArticleImg().shouldHave(sizeGreaterThanOrEqual(4));
            pressContentArticleImg().get(img).shouldBe(visible);
        }
        for (int pdf = 0; pdf < pressContentBtnDownloadPDF().size(); pdf++) {
            pressContentBtnDownloadPDF().shouldHave(sizeGreaterThanOrEqual(4));
            pressContentBtnDownloadPDF().get(pdf).shouldBe(visible);
        }
        for (int jpg = 0; jpg < pressContentBtnDownloadJPG().size(); jpg++) {
            pressContentBtnDownloadJPG().shouldHave(sizeGreaterThanOrEqual(4));
            pressContentBtnDownloadJPG().get(jpg).shouldBe(visible);
        }
        return this;
    }

}






