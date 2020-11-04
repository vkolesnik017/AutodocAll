package PKW;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;

public class CartAllData_page_Logic extends CartAllData_page {


    @Step("Next buttin clicking. CartAllData_page")
    public Payment_handler_page_Logic nextBtnClick() {
        nextBtn().click();
        return page(Payment_handler_page_Logic.class);
    }

    @Step("Checks presence payments method label. CartAllData_page")
    public CartAllData_page_Logic checkPresencePaymentsMethodLabel(SelenideElement ... locator) {
        for (SelenideElement expectedLocator : locator) {
            expectedLocator.shouldBe(visible);
        }
        return this;
    }

    @Step("Checks presence of payment methods label for required country. CartAllData_page")
    public CartAllData_page_Logic checksPresencePaymentsMethodLabelForRequiredCountry(String shop) {
        if (shop.equals("IT")) {
            visaLabel().shouldBe(visible);
            masterCardLabel().shouldBe(visible);
            postPayLabel().shouldBe(visible);
            cartaSiLabel().shouldBe(visible);
        } else {
            visaLabel().shouldBe(visible);
            masterCardLabel().shouldBe(visible);
        }
        return this;
    }

    @Step("Get total price of the CartAllData_page")
    public Float getTotalPriceAllDataPage(String shop) {
        Float totalPrice = null;
        totalOrderPrice().shouldBe(visible);
        if (shop.equals("EN")) {
            totalPrice = getTotalPriceAllDataPageForEnShop();
        } else {
            String realPrice = totalOrderPrice().getText();
            realPrice = realPrice.substring(0, realPrice.indexOf(" ")).replaceAll(",", ".");
            totalPrice = Float.parseFloat(realPrice);
        }
        return totalPrice;
    }

    @Step("Get total price for EN shop. CartAllData_page")
    public Float getTotalPriceAllDataPageForEnShop() {
        String realPrice;
        if (labelVAT().isDisplayed()) {
            String vat = labelVAT().getText();
            realPrice = totalOrderPrice().getText().replace("£ ", "").replace(vat, "");
        } else {
            realPrice = totalOrderPrice().getText().replace("£ ", "");
        }
        realPrice = realPrice.replaceAll(",", ".");
        Float totalPrice = Float.parseFloat(realPrice);
        return totalPrice;
    }

    @Step("Next buttin clicking. CartAllData_page")
    public CartAllData_page_Logic payPalBtnClick() {
        sleep(5000);
        payPalBtn().click();
        return this;
    }

    @Step("Checks for the label of the bank payment method. CartAllData_page")
    public CartAllData_page_Logic checksForLabelOfBankPaymentMethod() {
        if ((firstLabelBank().isDisplayed()) || secondLabelBank().isDisplayed() || thirdLabelBank().isDisplayed() || fourthLabelBank().isDisplayed()) {
            System.out.println("Label bank is visible");
        } else {
            Assert.fail("Leib bank not visible");
        }
        return this;
    }

    @Step("Checks for presence Safe Order block for Heavy Loads. CartAllData_page")
    public CartAllData_page_Logic checkPresenceSafeOrderBlock() {
        safeOrderBlock().shouldBe(visible);
        return this;
    }

    @Step("Checks that the Safe Order checkbox is selected. CartAllData_page")
    public CartAllData_page_Logic checkThatSafeOrderCheckboxIsSelected() {
        safeOrderCheckbox().shouldHave(attribute("checked"));
        return this;
    }

    @Step("Checks that the Safe Order checkbox is not selected. CartAllData_page")
    public CartAllData_page_Logic checkThatSafeOrderCheckboxIsNotSelected() {
        safeOrderCheckbox().shouldNotHave(attribute("checked"));
        return this;
    }

    @Step("Click Safe Order Checkbox. CartAllData_page")
    public CartAllData_page_Logic clickSafeOrderCheckbox() {
        safeOrderCheckbox().click();
        return this;
    }

    @Step("Add safe order price in order and checks what total price included SO. CartAllData_page")
    public CartAllData_page_Logic addSafeOrderInOrderAndCheckTotalPriceIncludedSO(String shop) {
        float price = getTotalPriceAllDataPage(shop);
        String priceSO = priceOfSafeOrder().getText();
        float realPriseSO = Float.parseFloat(priceSO.substring(0, priceSO.indexOf(" ")).replaceAll(",", "."));
        clickSafeOrderCheckbox();
        sleep(2000);
        Float totalPrice = getTotalPriceAllDataPage(shop);
        float totalPriceIncludedSO = price + realPriseSO;
        BigDecimal result = new BigDecimal(totalPriceIncludedSO);
        BigDecimal formatPriceUp = result.setScale(2, RoundingMode.UP);
        float roundMax = Float.parseFloat(String.valueOf(formatPriceUp));
        BigDecimal formatPriceDOWN = result.setScale(2, RoundingMode.FLOOR);
        float roundMin = Float.parseFloat(String.valueOf((formatPriceDOWN)));
        float res = 0.0f;
        if (totalPrice.equals(roundMax)) {
            res = roundMax;
        }
        if (totalPrice.equals(roundMin)) {
            res = roundMin;
        }
        Assert.assertEquals(res, totalPrice);
        return this;
    }
}
