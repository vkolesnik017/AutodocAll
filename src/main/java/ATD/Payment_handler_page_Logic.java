package ATD;

import com.codeborne.selenide.ex.ElementNotFound;
import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;

public class Payment_handler_page_Logic extends Payment_handler_page {

    @Step("Closing popup after making order. Payment_handler_page")
    public Payment_handler_page_Logic closePopupAfterOrder() {
        try {
            popupAfterOrder().shouldBe(visible);
            closePopupAfterOrderBtn().click();
        } catch (ElementNotFound e) {
        }
        return this;
    }

    @Step("Getting order number. Payment_handler_page")
    public String getOrderNumber() {
        return orderNumber().getText();
    }

    @Step("Checks success text in header. Payment_handler_page")
    public Payment_handler_page_Logic checkSuccessTextInHeader() {
        successTextInHeader().shouldBe(visible);
        return this;
    }

    @Step(": for Payment_handler_page")
    public Profile_plus_page_Logic goToProfilePlusPage() {
        new Main_page_Logic().profilePlusBtnClickInHeader();
        return page(Profile_plus_page_Logic.class);
    }

    @Step("Checks organization name {expectedName}. Payment_handler_page")
    public Payment_handler_page_Logic checkOrganizationName(String expectedName) {
        nameOfOrganization().shouldHave(text(expectedName));
        return this;
    }

    @Step("Compares price of the order in details with price {priceOnAllDataPage} on alldat page. Payment_handler_page")
    public Payment_handler_page_Logic comparesPriceOfOrderDetailsWithPriceOnAllDataPage(Float priceOnAllDataPage) {
        String price = priceOrder().getText();
        price = price.substring(0, price.indexOf(" ")).replaceAll(",", ".");
        Float priceInOrderDetails = Float.parseFloat(price);
        Assert.assertEquals(priceOnAllDataPage, priceInOrderDetails);
        return this;
    }

    @Step("Get text requisites. Payment_handler_page")
    public String getTextRequisites() {
        return requisites().getText();
    }

    @Step("Click on the link for PDF. Payment_handler_page")
    public Payment_handler_page_Logic clickOnLinkForPDF() {
        lincForPDF().shouldBe(visible);
        lincForPDF().click();
        sleep(3000);
        return this;
    }
}
