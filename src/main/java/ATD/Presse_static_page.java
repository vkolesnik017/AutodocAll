package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

class Presse_static_page {

    SelenideElement presseHeader() {
        return $(By.xpath("//*[@class='press-release__header']"));
    }

    SelenideElement presseHeaderTitle() {
        return $(By.xpath("//*[@class='press-release__header-title']"));
    }

    SelenideElement presseHeaderFirstText() {
        return $(By.xpath("//*[@class='press-release__header-advantage']"));
    }

    SelenideElement presseHeaderSecondText() {
        return $(By.xpath("//*[@class='press-release__header-text']//p"));
    }

    SelenideElement presseInfoBlock() {
        return $(By.xpath("//*[@class='press-inform']"));
    }

    SelenideElement presseInfoTitle() {
        return $(By.xpath("//*[@class='press-inform__title']"));
    }

    SelenideElement presseInfoText() {
        return $(By.xpath("//*[@class='press-inform__text']//p"));
    }

    SelenideElement preReleaseBlock() {
        return $(By.xpath("//*[@class='press-content'][1]"));
    }

    SelenideElement presseFirstPersonBlock() {
        return $(By.xpath("//*[@class='press-inform__persone'][1]"));
    }

    SelenideElement pressePhotos() {
        return $(By.xpath("//*[@class='press-inform__persone']//div//img"));
    }

    SelenideElement presseSecondPersonBlock() {
        return $(By.xpath("//*[@class='press-inform__persone'][2]"));
    }

    SelenideElement mehrButton() {
        return $(By.xpath("//*[@class='press-content__more js-press-content__more'][1]"));
    }

    SelenideElement atdHilft() {
        return $(By.xpath("//*[@class='atd-hilft']"));
    }

    SelenideElement atdHilftImage() {
        return $(By.xpath("//*[@class='atd-hilft__img']//img"));
    }

    ElementsCollection articleTexts() {
        return $$(By.xpath("//*[@class='atd-hilft__text']//p"));
    }

    ElementsCollection articleTitle() {
        return $$(By.xpath("//*[@class='press-article__title']//a"));
    }

    ElementsCollection downloadPDF() {
        return $$(By.xpath("//*[@class='press-article__file press-article__file--pdf']"));
    }

    ElementsCollection downloadJPG() {
        return $$(By.xpath("//*[@class='press-article__file press-article__file--jpg']"));
    }

    ElementsCollection artTitle() {
        return $$(By.xpath("//*[@class='press-article__title']")).filter(visible);
    }

    SelenideElement hilftArticleTitle() {
        return $(By.xpath("//*[@class='hilft-article__title']"));
    }

    SelenideElement hilftArticleDownloadPDF() {
        return $(By.xpath("//*[@class='link hilft-article__file hilft-article__file--file']"));
    }

    SelenideElement autodocPresseBlock() {
        return $(By.xpath("//*[@class='atd-presse']"));
    }

    SelenideElement autodocPresseBlockTitle() {
        return $(By.xpath("//*[@class='atd-presse__header']//span"));
    }

    SelenideElement autodocPresseButtonForward() {
        return $(By.xpath("//*[@class='presse-slides js-presse-slides slick-initialized slick-slider']//button[@class='slick-next slick-arrow']"));
    }

    SelenideElement autodocPresseButtonBack() {
        return $(By.xpath("//*[@class='presse-slides js-presse-slides slick-initialized slick-slider']//button[@class='slick-prev slick-arrow']"));
    }

    ElementsCollection activeArticlesInSlider() {
        return $$(By.xpath("//div[@class='slick-track']/*[self::div[@class='presse-slides__item slick-slide slick-current slick-active'] " +
                "or self::div[@class='presse-slides__item slick-slide slick-active'] or self::div[@class='presse-slides__item slick-slide']]//span"));
    }

    ElementsCollection imagesOfTheArticlesInSlider() {
        return $$(By.xpath("//div[@class='slick-track']/*[self::div[@class='presse-slides__item slick-slide slick-current slick-active'] " +
                "or self::div[@class='presse-slides__item slick-slide slick-active'] or self::div[@class='presse-slides__item slick-slide']]//img"));
    }

    ElementsCollection activeArticlesInSliderFive() {
        return $$(By.xpath("//div[@class='slick-track']/*[self::div[@class='presse-slides__item slick-slide slick-current slick-active'] " +
                "or self::div[@class='presse-slides__item slick-slide slick-active']]//span"));
    }

    SelenideElement autodocPresseTitlePresentation() {
        return $(By.xpath("//*[@class='press-content'][2]//div[1]"));
    }

    SelenideElement blockWithPresentation() {
        return $(By.xpath("//*[@class='press-content__presse']//a"));
    }

    ElementsCollection activeImagesInPresentationBlock() {
        return $$(By.xpath("//*[@class='slick-track']//*[self::div[@class='presse-gallery__thumb slick-slide slick-current slick-active'] " +
                "or self::div[@class='presse-gallery__thumb slick-slide slick-active'] or  self::div[@class='presse-gallery__thumb slick-slide']]//div//img"));
    }

    ElementsCollection imageElement() {
        return $$(By.xpath("//*[@class='presse-gallery__slide-inside']//a"));
    }

    ElementsCollection imageElementActiveSix() {
        return $$(By.xpath("//*[@class='slick-track']//*[self::div[@class='presse-gallery__thumb slick-slide slick-active'] " +
                "or self::div[@class='presse-gallery__thumb slick-slide slick-current slick-active']]//div//img"));
    }

    SelenideElement secondImageElementActive() {
        return $x("//div[contains(@class,'presse-gallery__thumb')]//*[@data-slick-index='1']/div/img");
    }

    SelenideElement presseGalleryActiveSlide() {
        return $x("//*[@class='presse-gallery__slide-inside']//*[@tabindex='0']/..//img");
    }

    SelenideElement forwardButtonPresentation() {
        return $(By.xpath("//*[@class='presse-gallery__slider js-presse-gallery__slider-init slick-initialized slick-slider']//*[@class='slick-next slick-arrow']"));
    }

    SelenideElement presseButtonBackPresentation() {
        return $(By.xpath("//*[@class='presse-gallery__slider js-presse-gallery__slider-init slick-initialized slick-slider']//*[@class='slick-prev slick-arrow']"));
    }

    ElementsCollection mainImageInPresentation() {
        return $$(By.xpath("//*[@class='presse-gallery__slide-inside']//img"));
    }

    SelenideElement presseGalleryHeader() {
        return $(".presse-gallery__header");
    }

    SelenideElement presseGalleryList() {
        return $(".presse-gallery__list");
    }

    SelenideElement presseGalleryContent() {
        return $(".presse-gallery__content > p");
    }

    SelenideElement blockMoreAboutAutodoc() {
        return $x("//div[@class='press-content'][2]");
    }

    SelenideElement headerInBlockMoreAboutAutodoc() {
        return $x("//div[@class='press-content'][2]/div/span");
    }

    SelenideElement pdfInBlockMoreAboutAutodoc() {
        return $x("//div[@class='press-content'][2]/div/a");
    }


}

