package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

class Presse_static_page {

    // Locators for press-release header block
    SelenideElement pressHeaderBlock() {
        return $(By.xpath("//*[@class='press-release__header']"));
    }

    SelenideElement pressHeaderTitle() {
        return $(By.xpath("//*[@class='press-release__header-title']"));
    }

    SelenideElement pressHeaderHeaderAdvantage() {
        return $(By.xpath("//*[@class='press-release__header-advantage']"));
    }

    SelenideElement pressHeaderMainText() {
        return $(By.xpath("//*[@class='press-release__header-text']//p"));
    }

    SelenideElement pressInfoBlock() {
        return $(By.xpath("//*[@class='press-inform']"));
    }

    // Locators for press-inform block
    SelenideElement pressInfoTitle() {
        return $(By.xpath("//*[@class='press-inform__title']"));
    }

    SelenideElement pressInfoMainText() {
        return $(By.xpath("//*[@class='press-inform__text']//p"));
    }

    SelenideElement pressInfoFirstPersonBlock() {
        return $(By.xpath("//*[@class='press-inform__persone'][1]"));
    }

    SelenideElement pressPhotos() {
        return $(By.xpath("//*[@class='press-inform__persone']//div//img"));
    }

    SelenideElement pressInfoSecondPersonBlock() {
        return $(By.xpath("//*[@class='press-inform__persone'][2]"));
    }

    // Locator for press-content block
    SelenideElement pressContentReleaseBlock() {
        return $(By.xpath("//*[@class='press-content'][1]"));
    }

    SelenideElement pressContentReleaseHeader() {
        return $x("(//div[@class='press-content__header']/span)[1]");
    }

    SelenideElement pressContentReleaseArticleBlock() {
        return $x("//div[@class='press-content__articles js-press-content']");
    }

    SelenideElement pressContentArticleDate() {
        return $(".press-article__date");
    }

    ElementsCollection pressContentArticleImg() {
        return $$(".press-article__img");
    }

    ElementsCollection pressContentArticleTitle() {
        return $$(By.xpath("//*[@class='press-article__title']//a"));
    }

    ElementsCollection pressContentBtnDownloadPDF() {
        return $$(By.xpath("//*[@class='press-article__file press-article__file--pdf']"));
    }

    ElementsCollection pressContentBtnDownloadJPG() {
        return $$(By.xpath("//*[@class='press-article__file press-article__file--jpg']"));
    }

    SelenideElement pressContentMoreButton() {
        return $(By.xpath("//*[@class='press-content__more js-press-content__more'][1]"));
    }

    //Locator for autodoc-help block
    SelenideElement helpBlock() {
        return $(By.xpath("//*[@class='atd-hilft']"));
    }

    SelenideElement helpImage() {
        return $(By.xpath("//*[@class='atd-hilft__img']//img"));
    }

    ElementsCollection helpArticleTexts() {
        return $$(By.xpath("//*[@class='atd-hilft__text']//p"));
    }

    SelenideElement helpArticleTitle() {
        return $(By.xpath("//*[@class='hilft-article__title']"));
    }

    SelenideElement articleDownloadPdfInHelpBlock() {
        return $(By.xpath("//*[@class='link hilft-article__file hilft-article__file--file']"));
    }

    // Locator for atd-press block
    SelenideElement autodocPressBlock() {
        return $(By.xpath("//*[@class='atd-presse']"));
    }

    SelenideElement autodocPressBlockTitle() {
        return $(By.xpath("//*[@class='atd-presse__header']//span"));
    }

    SelenideElement autodocPressButtonForward() {
        return $(By.xpath("(//button[@aria-label='Next'])[1]"));
    }

    SelenideElement autodocPressButtonBack() {
        return $(By.xpath("(//button[@aria-label='Previous'])[1]"));
    }

    SelenideElement autodocPressSliderList() {
        return $x("//div[@class='slick-list']");
    }

    ElementsCollection autodocPressActiveArticlesInSlider() {
        return $$(By.xpath("//div[@class='slick-track']/*[self::div[@class='presse-slides__item slick-slide slick-current slick-active'] " +
                "or self::div[@class='presse-slides__item slick-slide slick-active'] or self::div[@class='presse-slides__item slick-slide']]//span"));
    }

    ElementsCollection autodocPressImagesOfTheArticlesInSlider() {
        return $$(By.xpath("//div[@class='slick-track']/*[self::div[@class='presse-slides__item slick-slide slick-current slick-active'] " +
                "or self::div[@class='presse-slides__item slick-slide slick-active'] or self::div[@class='presse-slides__item slick-slide']]//img"));
    }

    ElementsCollection activeArticlesInSliderFive() {
        return $$(By.xpath("//div[@class='slick-track']/*[self::div[@class='presse-slides__item slick-slide slick-current slick-active'] " +
                "or self::div[@class='presse-slides__item slick-slide slick-active']]//span"));
    }

    // Locator for more about Autodoc block
    SelenideElement blockMoreAboutAutodoc() {
        return $x("//div[@class='press-content'][2]");
    }

    SelenideElement headerInBlockMoreAboutAutodoc() {
        return $(By.xpath("//div[@class='press-content'][2]/div/span"));
    }

    SelenideElement pdfInBlockMoreAboutAutodoc() {
        return $(By.xpath("//div[@class='press-content'][2]/div/a"));
    }

    // Locator for Gallery block
    SelenideElement pressGalleryListBlock() {
        return $(".presse-gallery__list");
    }

    SelenideElement pressGalleryHeaderBlock() {
        return $(".presse-gallery__header");
    }

    ElementsCollection mainImageInGalleryBlock() {
        return $$(By.xpath("//*[@class='presse-gallery__slide-inside']//img"));
    }

    SelenideElement pressGalleryContentBlock() {
        return $(".presse-gallery__content > p");
    }

    ElementsCollection activeImagesInGalleryBlock() {
        return $$(By.xpath("//*[@class='slick-track']//*[self::div[@class='presse-gallery__thumb slick-slide slick-current slick-active'] " +
                "or self::div[@class='presse-gallery__thumb slick-slide slick-active'] or  self::div[@class='presse-gallery__thumb slick-slide']]//div//img"));
    }

    SelenideElement oneBtnSaveInGalleryBlock() {
        return $x("//*[@class='presse-gallery__slide-inside']//a");
    }

    ElementsCollection allBtnSaveInGalleryBlock() {
        return $$(By.xpath("//*[@class='presse-gallery__slide-inside']//a"));
    }

    ElementsCollection sixActiveImageInGalleryBlock() {
        return $$(By.xpath("//*[@class='slick-track']//*[self::div[@class='presse-gallery__thumb slick-slide slick-active'] " +
                "or self::div[@class='presse-gallery__thumb slick-slide slick-current slick-active']]//div//img"));
    }

    SelenideElement secondImageInGalleryBlock() {
        return $x("//div[contains(@class,'presse-gallery__thumb')]//*[@data-slick-index='1']/div/img");
    }

    SelenideElement activeSlideInGalleryBlock() {
        return $x("//*[@class='presse-gallery__slide-inside']//*[@tabindex='0']/..//img");
    }

    SelenideElement nexSlideBtnInGalleryBlock() {
        return $(By.xpath("(//button[@aria-label='Next'])[2]"));
    }

    SelenideElement previousSlideBtnInGalleryBlock() {
        return $(By.xpath("(//button[@aria-label='Previous'])[2]"));
    }

}

