package ATD;

import Common.DataBase;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;

import static ATD.CommonMethods.waitingWhileLinkBecomeExpected;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.testng.Assert.assertEquals;

public class About_us_static_page_Logic extends About_us_static_page {

    @Step("Check the elements in the Twelve years block main header banner. About_us_static_page")
    public About_us_static_page_Logic checkElementsInMainHeaderBanner() {
        mainHeaderBanner().shouldBe(visible);
        textFirstOnTheImage().shouldBe(visible);
        Assert.assertFalse(textFirstOnTheImage().text().isEmpty());
        textOnTheImageSecond().shouldBe(visible);
        Assert.assertFalse(textOnTheImageSecond().text().isEmpty());
        textOnTheImageThird().shouldBe(visible);
        Assert.assertFalse(textOnTheImageThird().text().isEmpty());
        textSubtextOnTheImage().shouldBe(visible);
        Assert.assertFalse(textSubtextOnTheImage().text().isEmpty());
        textHeadingOfNumbers().shouldBe(visible);
        Assert.assertFalse(textHeadingOfNumbers().text().isEmpty());
        textAutodoc().shouldBe(visible);
        Assert.assertFalse(textAutodoc().text().isEmpty());
        textTwelve().shouldBe(visible);
        Assert.assertFalse(textTwelve().text().isEmpty());
        return this;
    }

    @Step("Checking the presence of the numbers in the main header Block. About_us_static_page")
    public About_us_static_page_Logic checkingNumberAndTextInMainHeaderBlock() {
        for (int i = 0; i < numbers().size(); i++) {
            numbers().get(i).shouldBe(visible);
            Assert.assertFalse(numbers().get(i).text().isEmpty());
            textNearNumbers().get(i).shouldBe(visible);
            Assert.assertFalse(textNearNumbers().get(i).text().isEmpty());
        }
        return this;
    }

    @Step("Checking the presence of the elements in the gallery Block. About_us_static_page")
    public About_us_static_page_Logic checkingElementsInGalleryBlock() {
        galleryBlock().shouldBe(visible);
        galleryTextHeading().shouldBe(visible);
        Assert.assertFalse(galleryTextHeading().text().isEmpty());
        galleryText().shouldBe(visible);
        Assert.assertFalse(galleryText().text().isEmpty());
        galleryImage().shouldBe(visible);
        galleryMehrButton().scrollTo().shouldBe(visible);
        Assert.assertFalse(galleryMehrButton().text().isEmpty());
        return this;
    }

    @Step("Checking the Mehr button functionality in the Gallery block and checking all images status code. About_us_static_page")
    public About_us_static_page_Logic checkingMehrButtonInGalleryBlock() throws IOException {
        galleryMehrButton().click();
        openImageGallery().shouldBe(visible);
        firstImageGallery().shouldBe(visible);
        nextButtonImageGallery().click();
        secondImageGallery().shouldBe(visible);
        previousButtonImageGallery().click();
        firstImageGallery().shouldBe(visible);
        closeButtonImageGallery().shouldBe(visible).click();
        openImageGallery().shouldNotBe(visible);
        for (int i = 0; i < allImagesInGallery().size(); i++) {
            String urlByAllImages = allImagesInGallery().get(i).getAttribute("src");
            URL url = new URL(urlByAllImages);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setInstanceFollowRedirects(true);
            int responseCode = http.getResponseCode();
            assertEquals(responseCode, 200);
        }
        return this;
    }

    @Step("Checking the presence of the Owners Block and elements here. About_us_static_page")
    public About_us_static_page_Logic checkingElementsPresenceInOwnerBlock() throws IOException {
        ownersBlock().shouldBe(visible);
        ownersBlockHeadingText().shouldBe(visible);
        Assert.assertFalse(ownersBlockHeadingText().text().isEmpty());
        for (int i = 0; i < threeOwnersBlocks().size(); i++) {
            threeOwnersBlocks().get(i).shouldBe(visible);
            threeOwnersBlocksText().get(i).shouldBe(visible);
            Assert.assertFalse(threeOwnersBlocksText().get(i).text().isEmpty());
        }
        for (int i = 0; i < threeOwnersBlocksPhotoImg().size(); i++) {
            threeOwnersBlocksPhotoImg().get(i).shouldBe(visible);
            String urlByAllPhotos = threeOwnersBlocksPhotoImg().get(i).getAttribute("src");
            URL url = new URL(urlByAllPhotos);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setInstanceFollowRedirects(true);
            int responseCode = http.getResponseCode();
            assertEquals(responseCode, 200);
        }
        return this;
    }


    @Step("Checking the photo of owners functionality. About_us_static_page")
    public About_us_static_page_Logic checkPhotoOfOwnersFunctionality() throws IOException, SQLException, InterruptedException {
        for (int i = 0; i < threeOwnersBlocksPhoto().size(); i++) {
            threeOwnersBlocks().get(i).scrollIntoView(true);
            String photoUrls = threeOwnersBlocksPhoto().get(i).getAttribute("href");
            threeOwnersBlocksPhoto().get(i).click();
            Assert.assertEquals(photoUrls, url());
            alonePhoto().shouldBe(visible);
            String alonePhoto = alonePhoto().getAttribute("src");
            URL url = new URL(alonePhoto);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setInstanceFollowRedirects(true);
            int responseCode = http.getResponseCode();
            assertEquals(responseCode, 200);
            textAboutOwnersBlock().shouldBe(visible);
            textAboutOwnersHeading().shouldBe(visible);
            Assert.assertFalse(textAboutOwnersHeading().text().isEmpty());
            textAboutOwnersHeadingSecond().shouldBe(visible);
            Assert.assertFalse(textAboutOwnersHeadingSecond().text().isEmpty());
            aboutOwnersMainText().shouldBe(visible);
            Assert.assertFalse(aboutOwnersMainText().text().isEmpty());
            stepsBreadcrumbs().shouldBe(visible);
            stepOneBreadcrumb().click();
            waitingWhileLinkBecomeExpected(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "staticAboutUs"));
            ownersBlock().scrollIntoView("{block: \"center\"}");
        }
        return this;
    }

    @Step("Check the elements in the years info block . About_us_static_page")
    public About_us_static_page_Logic checkElementsInYearsInfoBlock() {
        yearsInfoBlock().shouldBe(visible);
        yearsInfoBlockHeading().shouldBe(visible);
        Assert.assertFalse(yearsInfoBlockHeading().text().isEmpty());
        yearsInfoBlockHeadingAutodoc().shouldBe(visible);
        Assert.assertFalse(yearsInfoBlockHeadingAutodoc().text().isEmpty());
        numberOfYears().shouldBe(visible);
        Assert.assertFalse(numberOfYears().text().isEmpty());
        for (int i = 0; i < blocksWithYearsAndText().size(); i++) {
            blocksWithYearsAndText().get(i).shouldBe(visible);
            for (int j = 0; j < years().size(); i++) {
                years().get(i).shouldBe(visible);
                yearsInfoBlockYears().get(i).shouldBe(visible);
            }
        }
        return this;
    }

    @Step("Checking the map info block and presence of the elements here. About_us_static_page")
    public About_us_static_page_Logic checkingMapInfoBlock() throws IOException {
        mapInfo().shouldBe(visible);
        mapInfoTextBig().shouldBe(visible);
        Assert.assertFalse(mapInfoTextBig().text().isEmpty());
        mapInfoTextSub().shouldBe(visible);
        Assert.assertFalse(mapInfoTextSub().text().isEmpty());
        mapInfoRow().shouldBe(visible);
        for (int i = 0; i < mapInfoRowFlag().size(); i++) {
            mapInfoRowFlag().get(i).shouldBe(visible);
            String mapInfoRowFlag = mapInfoRowFlag().get(i).getAttribute("src");
            URL url = new URL(mapInfoRowFlag);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setInstanceFollowRedirects(true);
            int responseCode = http.getResponseCode();
            assertEquals(responseCode, 200);
            mapInfoRowText().get(i).shouldBe(visible);
            Assert.assertFalse(mapInfoRowText().get(i).text().isEmpty());
            mapInfoRowCountry().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("Checking the statistics block. About_us_static_page")
    public About_us_static_page_Logic checkingStatisticsBlock() throws IOException {
        statisticsBlock().shouldBe(visible);
        statisticsBlockHeading().shouldBe(visible);
        Assert.assertFalse(statisticsBlockHeading().text().isEmpty());
        for (int i = 0; i < iconStaticBlock().size(); i++) {
            iconStaticBlock().get(i).shouldBe(visible);
            iconStaticIcon().get(i).shouldBe(visible);
            String iconStaticIcon = iconStaticIcon().get(i).getAttribute("src");
            URL url = new URL(iconStaticIcon);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setInstanceFollowRedirects(true);
            int responseCode = http.getResponseCode();
            assertEquals(responseCode, 200);
            iconStaticText().get(i).shouldBe(visible);
            Assert.assertFalse(iconStaticText().get(i).text().isEmpty());
        }
        return this;
    }

    @Step("Checking the statistics block. About_us_static_page")
    public About_us_static_page_Logic checkingGraphBlock() {
        graphBlock().shouldBe(visible);
        graphBlockSmallText().shouldBe(visible);
        Assert.assertFalse(graphBlockSmallText().text().isEmpty());
        graphBlockBigText().shouldBe(visible);
        Assert.assertFalse(graphBlockBigText().text().isEmpty());
        graphBlockPercent().shouldBe(visible);
        Assert.assertFalse(graphBlockPercent().text().isEmpty());
        graphBlockRow().shouldBe(visible);
        for (int i = 0; i < graphBlockRows().size(); i++) {
            graphBlockRows().get(i).shouldBe(visible);
            graphBlockRowsText().get(i).shouldBe(visible);
            Assert.assertFalse(graphBlockRowsText().get(i).text().isEmpty());
        }
        return this;
    }
}
