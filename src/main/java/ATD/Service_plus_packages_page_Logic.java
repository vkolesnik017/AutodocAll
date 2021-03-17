package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;

public class Service_plus_packages_page_Logic extends Service_plus_packages_page {

    @Step("set Monthly Price. Service_packages_page")
    public Service_plus_packages_page_Logic setMonthlyPrice() {
        monthlyPriceSwitch().shouldBe(visible).click();
        priceValueForPeriod().get(0).shouldHave(exactText("/Monat"));
        return this;
    }

    @Step("click on 'More' button Of General Condition Block. Service_packages_page")
    public Service_plus_packages_page_Logic clickOnBtnMoreOfGeneralConditionBlock() {
        btnMoreOfGeneralConditionBlock().click();
        return this;
    }

    @Step("presence Of Cancellation Form. Service_packages_page")
    public Service_plus_packages_page_Logic presenceOfCancellationForm(boolean label) {
        if (label == true) {
            cancellationForm().shouldBe(visible);
        } else {
            cancellationForm().shouldNotBe(visible);
        }
        return this;
    }

    @Step("click On Active Basic Package. Service_packages_page")
    public Service_plus_packages_page_Logic clickOnActiveBasicPackage() {
        btnActiveBasicPackage().click();
        return this;
    }

    @Step("presence Of Registration PopUp. Service_packages_page")
    public Service_plus_packages_page_Logic presenceOfRegistrationPopUp() {
        registrationPopUp().shouldBe(visible);
        return this;
    }

    @Step("absence Of Safe Order option. Service_packages_page")
    public Service_plus_packages_page_Logic absenceOfSoOption() {
        List<String> iconTitle = servicePackageIcons().stream().map(n -> n.getAttribute("src").replaceAll("^.+\\/", "").replaceAll("\\..+$", "")).collect(Collectors.toList());
        Assert.assertFalse(iconTitle.contains("return-icon"));
        return this;
    }

    @Step("presence of service packages. Service_packages_page")
    public Service_plus_packages_page_Logic presenceOfServicePackages() {
        servicePackagesBlock().shouldBe(visible);
        return this;
    }

    @Step("absence Of Safe Order Image Block . Service_packages_page")
    public Service_plus_packages_page_Logic absenceOfSoImageBlock() {
        serviceOrderImageBlock().shouldNotBe(visible);
        return this;
    }

    @Step("Check elements in plus service block. Service_packages_page")
    public Service_plus_packages_page_Logic checkElementsInPlusServiceBlock() {
        plusServiceMainLogo().shouldBe(visible);
        plusServicePageLogo().shouldBe(visible);
        plusServiceTitle().shouldBe(visible);
        plusServiceMainText().shouldBe(visible);
        activeYearPriceSwitch().shouldHave(attribute("checked"));
        return this;
    }

    @Step("Check elements in the block with a subscription to the Base package. Service_packages_page")
    public Service_plus_packages_page_Logic checkElementsInBasePack(String namePack, String textInStartPackBtn) {
        titleInBasePackBlock().shouldBe(visible).shouldHave(text(namePack));
        shippedIconInBasePack().shouldBe(visible);
        returnIconInBasePack().shouldBe(visible);
        vipIconInBasePack().shouldBe(visible);
        btnActiveBasicPackage().shouldBe(visible).shouldHave(text(textInStartPackBtn));
        return this;
    }

    @Step("Check elements in the block with a subscription to the Optimal package. Service_packages_page")
    public Service_plus_packages_page_Logic checkElementsInOptimalPack(String namePack, String textInStartPackBtn) {
        titleInOptimalPacBlock().shouldBe(visible).shouldHave(text(namePack));
        shippedIconInOptimalPack().shouldBe(visible);
        returnIconInOptimalPack().shouldBe(visible);
        vipIconInOptimalPack().shouldBe(visible);
        x2IconInOptimalPack().shouldBe(visible);
        btnActiveOptimalPackage().shouldBe(visible).shouldHave(text(textInStartPackBtn));
        return this;
    }

    @Step("Check elements in the block with a subscription to the Prof package. Service_packages_page")
    public Service_plus_packages_page_Logic checkElementsInProfPack(String namePack, String textInStartPackBtn) {
        titleInProfPacBlock().shouldBe(visible).shouldHave(text(namePack));
        shippedIconInProfPack().shouldBe(visible);
        returnIconInProfPack().shouldBe(visible);
        vipIconInProfPack().shouldBe(visible);
        x2IconInProfPack().shouldBe(visible);
        discountIconInProfPack().shouldBe(visible);
        subscribeIconInProfPack().shouldBe(visible);
        btnActiveProfPackage().shouldBe(visible).shouldHave(text(textInStartPackBtn));
        return this;
    }

    @Step("Check elements in the block with a subscription to the Expert package. Service_packages_page")
    public Service_plus_packages_page_Logic checkElementsInExpertPack(String namePack, String textInStartPackBtn) {
        titleInExpertPacBlock().shouldBe(visible).shouldHave(text(namePack));
        shippedIconInExpertPack().shouldBe(visible);
        returnIconInExpertPack().shouldBe(visible);
        vipIconInExpertPack().shouldBe(visible);
        x2IconInExpertPack().shouldBe(visible);
        discountIconInExpertPack().shouldBe(visible);
        subscribeIconInExpertPack().shouldBe(visible);
        expertIconInExpertPack().shouldBe(visible);
        btnActiveExpertPackage().shouldBe(visible).shouldHave(text(textInStartPackBtn));
        return this;
    }

    @Step("Check elements in the block with a subscription to the Trial package. Service_packages_page")
    public Service_plus_packages_page_Logic checkElementsInTrialPack() {
        trialBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        titleInTrialBlock().shouldBe(visible);
        textInTrialBlock().shouldBe(visible);
        btnActiveTrialPackage().shouldBe(visible);
        return this;
    }

    @Step("Check elements in delivery block. Service_packages_page")
    public Service_plus_packages_page_Logic checkElementsInDeliveryBlock() {
        deliveryBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        titleInDeliveryBlock().shouldBe(visible);
        costStandardInDeliveryBlock().shouldBe(visible);
        costStandardTextInDeliveryBlock().shouldBe(visible);
        costLowerInDeliveryBlock().shouldBe(visible);
        costLowerTextInDeliveryBlock().shouldBe(visible);
        logoAutodocInDeliveryBlock().shouldBe(visible);
        logoPlusInDeliveryBlock().shouldBe(visible);
        return this;
    }

    @Step("Check elements in expert block. Service_packages_page")
    public Service_plus_packages_page_Logic checkElementsInExpertBlock() {
        plusExpertBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        plusExpertHeading().shouldBe(visible);
        plusExpertSubheading().shouldBe(visible);
        plusExpertMainText().shouldBe(visible);
        plusExpertListHeading().shouldBe(visible);
        plusExpertTextInListHeading().shouldBe(visible);
        return this;
    }

    @Step("Check elements in Discount block. Service_packages_page")
    public Service_plus_packages_page_Logic checkElementsInDiscountBlock() {
        plusDiscountBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        plusDiscountSticker().shouldBe(visible);
        plusDiscountTitle().shouldBe(visible);
        plusDiscountLogoPlus().shouldBe(visible);
        plusDiscountMainText().shouldBe(visible);
        return this;
    }

    @Step("Check elements in Personal block. Service_packages_page")
    public Service_plus_packages_page_Logic checkElementsInPersonalBlock() {
        plusPersonalBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        plusPersonalSticker().shouldBe(visible);
        plusPersonalTitle().shouldBe(visible);
        plusPersonalMainText().shouldBe(visible);
        plusPersonalLabel().shouldBe(visible);
        return this;
    }

    @Step("Check elements in plus Safe block. Service_packages_page")
    public Service_plus_packages_page_Logic checkElementsInPlusSafeBlock() {
        plusSafeBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        plusSafeTitle().shouldBe(visible);
        plusSafeSubtitle().shouldBe(visible);
        plusSafeMainText().shouldBe(visible);
        return this;
    }

    @Step("Check elements in plus Ready block. Service_packages_page")
    public Service_plus_packages_page_Logic checkElementsInPlusReadyBlock() {
        plusReadyBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        plusReadyTitle().shouldBe(visible);
        plusReadyText().shouldBe(visible);
        plusReadySubtitle().shouldBe(visible);
        plusReadyInfo().shouldBe(visible);
        plusReadyLogoPlus().shouldBe(visible);
        return this;
    }

    @Step("Check elements in plus Return block. Service_packages_page")
    public Service_plus_packages_page_Logic checkElementsInPlusReturnBlock() {
        plusReturnBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        plusReturnTitle().shouldBe(visible);
        plusReturnContent().shouldBe(visible);
        plusReturnMoreBtn().shouldBe(visible);
        return this;
    }
}
