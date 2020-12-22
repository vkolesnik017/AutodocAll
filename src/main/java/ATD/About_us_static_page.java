package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

class About_us_static_page {

    SelenideElement mainHeaderBanner() {
        return $(By.xpath("//*[@class='top-banner']"));
    }

    SelenideElement textFirstOnTheImage() {
        return $(By.xpath("//*[@class='top-banner__text-first']"));
    }

    SelenideElement textOnTheImageSecond() {
        return $(By.xpath("//*[@class='top-banner__text-big']"));
    }

    SelenideElement textOnTheImageThird() {
        return $(By.xpath("//*[@class='top-banner__text-bottom']"));
    }

    SelenideElement textSubtextOnTheImage() {
        return $(By.xpath("//*[@class='top-banner__subtext']"));
    }

    SelenideElement textHeadingOfNumbers() {
        return $(By.xpath("//*[@class='top-banner__numbers-heading']"));
    }

    SelenideElement textAutodoc() {
        return $(By.xpath("//*[@class='top-banner__text-first']//span"));
    }

    SelenideElement textTwelve() {
        return $(By.xpath("//*[@class='top-banner__text-big']//span"));
    }

    ElementsCollection numbers() {
        return $$(By.xpath("//*[@class='nr']"));
    }

    ElementsCollection textNearNumbers() {
        return $$(By.xpath("//*[@class='top-banner__numbers-item']//*[@class='text']"));
    }

    SelenideElement galleryBlock() {
        return $(By.xpath("//*[@class='gallery']"));
    }

    SelenideElement galleryTextHeading() {
        return $(By.xpath("//*[@class='gallery__text-heading']"));
    }

    SelenideElement galleryText() {
        return $(By.xpath("//*[@class='gallery__text-paragraph']"));
    }

    SelenideElement galleryImage() {
        return $(By.xpath("//*[@class='gallery__images']"));
    }

    SelenideElement galleryMehrButton() {
        return $(By.xpath("//*[@class='open js-click-link-gallery']"));
    }

    SelenideElement openImageGallery() {
        return $(By.xpath("//*[@class='fancybox-outer']"));
    }

    SelenideElement nextButtonImageGallery() {
        return $(By.xpath("//*[@class='fancybox-nav fancybox-next']"));
    }

    SelenideElement previousButtonImageGallery() {
        return $(By.xpath("//*[@class='fancybox-nav fancybox-prev']"));
    }

    SelenideElement firstImageGallery() {
        return $(By.xpath("//*[@alt='about_us_gallery1']"));
    }

    SelenideElement secondImageGallery() {
        return $(By.xpath("//*[@alt='about_us_gallery2'][1]"));
    }

    SelenideElement closeButtonImageGallery() {
        return $(By.xpath("//*[@class='fancybox-item fancybox-close']"));
    }

    ElementsCollection allImagesInGallery() {
        return $$x("//*[@class='gallery__images']//img");
    }

    ElementsCollection threeOwnersBlocks() {
        return $$x("//*[@class='owners-block__item active']");
    }

    ElementsCollection threeOwnersBlocksText() {
        return $$x("//*[@class='owners-block__item-heading']");
    }

    ElementsCollection threeOwnersBlocksPhoto() {
        return $$x("//*[@class='owners-block__item active']");
    }

    ElementsCollection threeOwnersBlocksPhotoImg() {
        return $$x("//*[@class='owners-block__item active']//img");
    }

    SelenideElement ownersBlock() {
        return $(By.xpath("//*[@class='owners-block']"));
    }

    SelenideElement ownersBlockHeadingText() {
        return $(By.xpath("//*[@class='owners-block__heading']//span"));
    }

    SelenideElement alonePhoto() {
        return $(By.xpath("//*[@class='about-us-single__image']//img"));
    }

    SelenideElement stepsBreadcrumbs() {
        return $(By.xpath("//*[@class='steps breadcrumbs']"));
    }

    SelenideElement stepOneBreadcrumb() {
        return $(By.xpath("//*[@class='step_1 active parts_step_1']//a"));
    }

    SelenideElement textAboutOwnersBlock() {
        return $(By.xpath("//*[@class='about-us-single__info']"));
    }

    SelenideElement textAboutOwnersHeading() {
        return $(By.xpath("//*[@class='about-us-single__info-heading']"));
    }

    SelenideElement textAboutOwnersHeadingSecond() {
        return $(By.xpath("//*[@class='about-us-single__info-heading']//span"));
    }

    SelenideElement aboutOwnersMainText() {
        return $(By.xpath("//*[@class='about-us-single__info-text']"));
    }

    SelenideElement yearsInfoBlock() {
        return $x("//*[@class='years-info']");
    }

    SelenideElement yearsInfoBlockHeading() {
        return $x("//*[@class='years-info']");
    }

    SelenideElement yearsInfoBlockHeadingAutodoc() {
        return $x("//*[@class='years-info']//span");
    }

    SelenideElement numberOfYears() {
        return $(By.xpath("//*[@class='years-info__nr']"));
    }

    SelenideElement mapInfo() {
        return $(By.xpath("//*[@class='map-info']"));
    }

    SelenideElement mapInfoTextBig() {
        return $(By.xpath("//*[@class='map-info__text-big']"));
    }

    SelenideElement mapInfoTextSub() {
        return $(By.xpath("//*[@class='map-info__text-sub']"));
    }

    SelenideElement mapInfoRow() {
        return $(By.xpath("//*[@class='map-info__row']"));
    }

    ElementsCollection iconStaticBlock() {
        return $$x("//*[@class='icons-statistic__block']");
    }

    ElementsCollection iconStaticIcon() {
        return $$x("//*[@class='icons-statistic__block-icon']//img");
    }

    ElementsCollection iconStaticText() {
        return $$x("//*[@class='icons-statistic__block-text']");
    }

    ElementsCollection mapInfoRowFlag() {
        return $$x("//*[@class='map-info__row-list']//li//img");
    }

    ElementsCollection mapInfoRowText() {
        return $$x("//*[@class='map-info__row-list']//li//span");
    }

    ElementsCollection mapInfoRowCountry() {
        return $$x("//*[@class='map-info__row-list']//li//span//b");
    }

    ElementsCollection blocksWithYearsAndText() {
        return $$x("years-info__block");
    }

    ElementsCollection years() {
        return $$x("//*[@class='years-info__block-nr n2008 active']");
    }

    ElementsCollection yearsInfoBlockYears() {
        return $$x("//*[@class='years-info__block-year']");
    }

    SelenideElement statisticsBlock() {
        return $(By.xpath("//*[@class='icons-statistic']"));
    }

    SelenideElement graphBlock() {
        return $(By.xpath("//*[@class='graph-block']"));
    }

    SelenideElement graphBlockSmallText() {
        return $(By.xpath("//*[@class='graph-block__small']"));
    }

    SelenideElement graphBlockBigText() {
        return $(By.xpath("//*[@class='graph-block__big']"));
    }

    SelenideElement graphBlockPercent() {
        return $(By.xpath("//*[@class='graph-block__percent']"));
    }

    SelenideElement graphBlockRow() {
        return $(By.xpath("//*[@class='graph-block__row']"));
    }

    SelenideElement statisticsBlockHeading() {
        return $(By.xpath("//*[@class='icons-statistic__heading']"));
    }

    ElementsCollection graphBlockRows() {
        return $$x("//div[contains(@class, 'graph-block__row-block')]");
    }

    ElementsCollection graphBlockRowsText() {
        return $$x("//div[contains(@class, 'graph-block__row-block')]//div");
    }
}
