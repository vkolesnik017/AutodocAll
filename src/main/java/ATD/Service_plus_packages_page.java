package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class Service_plus_packages_page {

    ElementsCollection priceValueForPeriod() {
        return $$(".atd-plus-services__item-price span");
    }

    SelenideElement monthlyPriceSwitch() {
        return $x("//div[@class='switch js--atd-plus-services-switch']//span[text()='monatlich']");
    }

    SelenideElement btnMoreOfGeneralConditionBlock() {
        return $(".atd-plus-return__more button");
    }

    SelenideElement cancellationForm() {
        return $x("//p[text()='– Ende der Widerrufsbelehrung –']/preceding-sibling::img");
    }

    SelenideElement registrationPopUp() {
        return $x("//div[@class='autodoc_login_popup popup_login pass']");
    }

    SelenideElement servicePackagesBlock() {
        return $(".atd-plus-services__packs");
    }

    ElementsCollection servicePackageIcons() {
        return $$(".atd-plus-services__item-bonus img");
    }

    SelenideElement serviceOrderImageBlock() {
        return $(".atd-plus-safe");
    }

    SelenideElement plusServiceMainLogo() {
        return $(".atd-plus-services__logo > img");
    }

    SelenideElement plusServicePageLogo() {
        return $(".atd-plus-services__logo > span");
    }

    SelenideElement plusServiceTitle() {
        return $(".atd-plus-services__title");
    }

    SelenideElement plusServiceMainText() {
        return $(".atd-plus-services__text");
    }

    SelenideElement ActiveYearPriceSwitch() {
        return $x("//div[contains(@class,'atd-plus-services-switch')]//label/../input[@id='two']");
    }

    //Elements in the block with a subscription to the Base package
    SelenideElement titleInBasePackBlock() {
        return $x("//div[contains(@class,'base-pack')][1]//div[contains(@class,'title')]");
    }

    SelenideElement shippedIconInBasePack() {
        return $x("//div[contains(@class,'base-pack')][1]//img[@alt='shipped-icon']/..//span");
    }

    SelenideElement returnIconInBasePack() {
        return $x("//div[contains(@class,'base-pack')][1]//img[@alt='return-icon']/..//span");
    }

    SelenideElement vipIconInBasePack() {
        return $x("//div[contains(@class,'base-pack')][1]//img[@alt='vip-icon']/..//span");
    }

    SelenideElement btnActiveBasicPackage() {
        return $x("//div[contains(@class,'base-pack')][1]//div[contains(@class,'item-start')]/a");
    }

    //Elements in the block with a subscription to the Optimal package
    SelenideElement titleInOptimalPacBlock() {
        return $x("//div[contains(@class,'optimal-pack')]//div[contains(@class,'title')]");
    }

    SelenideElement shippedIconInOptimalPack() {
        return $x("//div[contains(@class,'optimal-pack')]//img[@alt='shipped-icon']/..//span");
    }

    SelenideElement returnIconInOptimalPack() {
        return $x("//div[contains(@class,'optimal-pack')]//img[@alt='return-icon']/..//span");
    }

    SelenideElement vipIconInOptimalPack() {
        return $x("//div[contains(@class,'optimal-pack')]//img[@alt='vip-icon']/..//span");
    }

    SelenideElement x2IconInOptimalPack() {
        return $x("//div[contains(@class,'optimal-pack')]//img[@alt='2x-icon']/..//span");
    }

    SelenideElement btnActiveOptimalPackage() {
        return $x("//div[contains(@class,'optimal-pack')]//div[contains(@class,'item-start')]/a");
    }

    //Elements in the block with a subscription to the Prof package
    SelenideElement titleInProfPacBlock() {
        return $x("//div[contains(@class,'profi-pack')]//div[contains(@class,'title')]");
    }

    SelenideElement shippedIconInProfPack() {
        return $x("//div[contains(@class,'profi-pack')]//img[@alt='shipped-icon']/..//span");
    }

    SelenideElement returnIconInProfPack() {
        return $x("//div[contains(@class,'profi-pack')]//img[@alt='return-icon']/..//span");
    }

    SelenideElement vipIconInProfPack() {
        return $x("//div[contains(@class,'profi-pack')]//img[@alt='vip-icon']/..//span");
    }

    SelenideElement x2IconInProfPack() {
        return $x("//div[contains(@class,'profi-pack')]//img[@alt='2x-icon']/..//span");
    }

    SelenideElement discountIconInProfPack() {
        return $x("//div[contains(@class,'profi-pack')]//img[@alt='discount-icon']/..//span");
    }

    SelenideElement subscribeIconInProfPack() {
        return $x("//div[contains(@class,'profi-pack')]//img[@alt='subscribe-icon']/..//span");
    }

    SelenideElement btnActiveProfPackage() {
        return $x("//div[contains(@class,'profi-pack')]//div[contains(@class,'item-start')]/a");
    }

    //Elements in the block with a subscription to the Expert package
    SelenideElement titleInExpertPacBlock() {
        return $x("//div[contains(@class,'base-pack')][2]//div[contains(@class,'title')]");
    }

    SelenideElement shippedIconInExpertPack() {
        return $x("//div[contains(@class,'base-pack')][2]//img[@alt='shipped-icon']/..//span");
    }

    SelenideElement returnIconInExpertPack() {
        return $x("//div[contains(@class,'base-pack')][2]//img[@alt='return-icon']/..//span");
    }

    SelenideElement vipIconInExpertPack() {
        return $x("//div[contains(@class,'base-pack')][2]//img[@alt='vip-icon']/..//span");
    }

    SelenideElement x2IconInExpertPack() {
        return $x("//div[contains(@class,'base-pack')][2]//img[@alt='2x-icon']/..//span");
    }

    SelenideElement discountIconInExpertPack() {
        return $x("//div[contains(@class,'base-pack')][2]//img[@alt='discount-icon']/..//span");
    }

    SelenideElement subscribeIconInExpertPack() {
        return $x("//div[contains(@class,'base-pack')][2]//img[@alt='subscribe-icon']/..//span");
    }

    SelenideElement expertIconInExpertPack() {
        return $x("//div[contains(@class,'base-pack')][2]//img[@alt='expert-icon']/..//span");
    }

    SelenideElement btnActiveExpertPackage() {
        return $x("//div[contains(@class,'base-pack')][2]//div[contains(@class,'item-start')]/a");
    }

    //Elements in the block with a subscription to the Trial package
    SelenideElement trialBlock() {
        return $(".atd-plus-services__bottom");
    }

    SelenideElement titleInTrialBlock() {
        return $(".atd-plus-services__bottom > p");
    }

    SelenideElement textInTrialBlock() {
        return $x("//div[@class='atd-plus-services__bottom']//div[@class='text']");
    }

    SelenideElement btnActiveTrialPackage() {
        return $x("//div[@data-ga-action='trial']");
    }

    //Elements in the delivery block
    SelenideElement deliveryBlock() {
        return $(".atd-plus-delivery");
    }

    SelenideElement titleInDeliveryBlock() {
        return $(".atd-plus-delivery__title");
    }

    SelenideElement costStandardInDeliveryBlock() {
        return $x("//div[contains(@class,'cost standart')]/div[1]");
    }

    SelenideElement costStandardTextInDeliveryBlock() {
        return $x("//div[contains(@class,'cost standart')]/div[2]");
    }

    SelenideElement costLowerInDeliveryBlock() {
        return $x("//div[contains(@class,'cost lower')]/div[1]");
    }

    SelenideElement costLowerTextInDeliveryBlock() {
        return $x("//div[contains(@class,'cost lower')]/div[2]");
    }

    SelenideElement logoAutodocInDeliveryBlock() {
        return $x("//div[contains(@class,'cost lower')]/div[3]//img[1]");
    }

    SelenideElement logoPlusInDeliveryBlock() {
        return $x("//div[contains(@class,'cost lower')]/div[3]//img[2]");
    }

    //Elements in the Expert block
    SelenideElement plusExpertBlock() {
        return $(".atd-plus-expert");
    }

    SelenideElement plusExpertHeading() {
        return $x("//div[@class='atd-plus-expert__heading']");
    }

    SelenideElement plusExpertSubheading() {
        return $x("//div[@class='atd-plus-expert__subheading']");
    }

    SelenideElement plusExpertMainText() {
        return $x("//div[@class='atd-plus-expert']//div[@class='text']");
    }

    SelenideElement plusExpertListHeading() {
        return $x("//div[@class='atd-plus-expert__list-heading']");
    }

    SelenideElement plusExpertTextInListHeading() {
        return $x("//div[@class='atd-plus-expert']//ul");
    }

    //Elements in the Discount block
    SelenideElement plusDiscountBlock() {
        return $(".atd-plus-discount");
    }

    SelenideElement plusDiscountSticker() {
        return $(".atd-plus-twice__sticker>img");
    }

    SelenideElement plusDiscountTitle() {
        return $(".atd-plus-twice__title");
    }

    SelenideElement plusDiscountLogoPlus() {
        return $(".atd-plus-twice__plus");
    }

    SelenideElement plusDiscountMainText() {
        return $(".atd-plus-twice__text");
    }

    //Elements in the Personal block
    SelenideElement plusPersonalBlock() {
        return $(".atd-plus-personal");
    }

    SelenideElement plusPersonalSticker() {
        return $(".atd-plus-personal__sticker");
    }

    SelenideElement plusPersonalTitle() {
        return $(".atd-plus-personal__title");
    }

    SelenideElement plusPersonalMainText() {
        return $(".atd-plus-personal__text");
    }

    SelenideElement plusPersonalLabel() {
        return $(".atd-plus-personal__label");
    }

    //Elements in the plus Safe block
    SelenideElement plusSafeBlock() {
        return $(".atd-plus-safe");
    }

    SelenideElement plusSafeTitle() {
        return $(".atd-plus-safe__title");
    }

    SelenideElement plusSafeSubtitle() {
        return $(".atd-plus-safe__subtitle");
    }

    SelenideElement plusSafeMainText() {
        return $(".atd-plus-safe__text");
    }

    //Element in the plus ready block
    SelenideElement plusReadyBlock() {
        return $(".atd-plus-ready");
    }

    SelenideElement plusReadyTitle() {
        return $(".atd-plus-ready__title");
    }

    SelenideElement plusReadyText() {
        return $(".atd-plus-ready__text");
    }

    SelenideElement plusReadySubtitle() {
        return $(".atd-plus-ready__subtitle");
    }

    SelenideElement plusReadyInfo() {
        return $(".atd-plus-ready__info");
    }

    SelenideElement plusReadyLogoPlus() {
        return $(".atd-plus-ready__plus");
    }

    //Element in the plus return block
    SelenideElement plusReturnBlock() {
        return $(".atd-plus-return");
    }

    SelenideElement plusReturnTitle() {
        return $(".atd-plus-return__title");
    }

    SelenideElement plusReturnContent() {
        return $(".atd-plus-return__content");
    }

    SelenideElement plusReturnMoreBtn() {
        return $(".atd-plus-return__more");
    }
}
