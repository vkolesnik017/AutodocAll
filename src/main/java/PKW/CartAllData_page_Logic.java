package PKW;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.sleep;

public class CartAllData_page_Logic extends CartAllData_page {


    @Step("Checks presence payments method label. CartAllData_page")
    public CartAllData_page_Logic checkPresencePaymentsMethodLabel(SelenideElement locator) {
        locator.shouldBe(visible);
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

}
