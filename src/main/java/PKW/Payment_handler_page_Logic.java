package PKW;

import Common.DataBase;
import com.codeborne.selenide.ex.ElementNotFound;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.ArrayList;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.sleep;

public class Payment_handler_page_Logic extends Payment_handler_page {

    @Step("Getting order number. Payment_handler_page")
    public String getOrderNumber() {
        return orderNumber().getText();
    }

    @Step("Closing popup after making order. Payment_handler_page")
    public Payment_handler_page_Logic closePopupAfterOrder() {
        try {
            popupAfterOrder().shouldBe(visible);
            closePopupAfterOrderBtn().click();
        } catch (ElementNotFound e) {
        }
        return this;
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

    @Step("Click on the link for PDF. Payment_handler_page")
    public Payment_handler_page_Logic clickOnLinkForPDF() {
        lincForPDF().shouldBe(visible);
        lincForPDF().click();
        sleep(3000);
        return this;
    }

    @Step("Get text requisites. Payment_handler_page")
    public String getTextRequisites() {
        return requisites().getText();
    }

    @Step("Compares expected requisites with actual. Payment_handler_page")
    public Payment_handler_page_Logic compareExpectedRequisitesWithActual(String shop) throws SQLException {
        String requisitesOnTheSite = getTextRequisites().replaceAll("\n", " ");
        ArrayList<String> requisites;
        ArrayList<String> requisitesParameter = new ArrayList<>();
        requisitesParameter.add("Owner");
        requisitesParameter.add("Account number");
        requisitesParameter.add("Sort Code");
        requisitesParameter.add("Bank");
        requisitesParameter.add("IBAN");
        requisitesParameter.add("BIC_SWIFT");
        if (shop.equals("CH") || shop.equals("CZ") || shop.equals("DK") || shop.equals("EN") || shop.equals("HU") || shop.equals("NO") || shop.equals("PL") ||
                shop.equals("RO") || shop.equals("SE")) {
            for (int i = 0; i < requisitesParameter.size(); i++) {
                System.out.println(requisitesParameter.get(i));
                requisites = new DataBase("PKW").getNameRequisitesMethod("bank_requisites_pkw", shop, String.valueOf(requisitesParameter.get(i)));
                String requisitesStringFormat = requisites.toString().replaceAll("(\\[)(.+)(\\])","$2");
                System.out.println(requisitesStringFormat);
                System.out.println(requisitesOnTheSite);
                if (requisitesStringFormat != "null") {
                    Assert.assertTrue(requisitesOnTheSite.contains(requisitesStringFormat));
                }
            }
        }


        /*ArrayList<String> requisites;
        if (shop.equals("CH") || shop.equals("CZ") || shop.equals("DK") || shop.equals("EN") || shop.equals("HU") || shop.equals("NO") || shop.equals("PL") ||
            shop.equals("RO") || shop.equals("SE")) {
            for (int i = 0; i < new DataBase("PKW").getNameRequisitesMethod("bank_requisites_pkw", shop, "Owner").size(); i++) {}
            requisites = new DataBase("PKW").getNameRequisitesMethod("bank_requisites_pkw", shop, "Owner",
                    "Account number", "Sort Code", "Bank", "IBAN", "BIC_SWIFT");
        } else {
            requisites = new DataBase("PKW").getNameRequisitesMethod("bank_requisites_pkw", "other", "Owner",
                    "Account number", "Sort Code", "Bank", "IBAN", "BIC_SWIFT");
        }
        String requisitesFormat = requisites.toString().replaceAll(",", "").replaceAll("null", "\n").replaceAll("\n", "");
        System.out.println(requisitesFormat);
        String requisitesOnTheSite = getTextRequisites().replaceAll("\n", "");
        System.out.println(requisitesOnTheSite);
        Assert.assertTrue(requisitesOnTheSite.contains(requisitesFormat));*/
        return this;
    }
}
