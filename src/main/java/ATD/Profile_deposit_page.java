package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Profile_deposit_page {

    SelenideElement profileDepositBtn() {
        return $x("//a[@data-ga-action='Sidebar_MyDepositAccount']");
    }

    SelenideElement depositBalance() {
        return $x("//span[@class='green']");
    }

    SelenideElement depositTable() {
        return $x("//div[@class='memb_history deposit']/div[2]");
    }

    SelenideElement pdfDocDepositLink() {
        return $x("//span[@class='link deposit-print-img']");
    }

    ElementsCollection listOfDepositInTable() {
        return $$x("//div[@class='table_over']//tbody//tr");
    }

    SelenideElement titlePage() {
        return $x("//div[@class='title']");
    }

    SelenideElement columnTypeOfTransaction() {
        return $x("//table//tr/th[1]");
    }

    SelenideElement columnData() {
        return $x("//table//tr/th[2]");
    }

    SelenideElement columnQuantity() {
        return $x("//table//tr/th[3]");
    }

    SelenideElement columnBalance() {
        return $x("//table//tr/th[4]");
    }

    SelenideElement columnSerialNum() {
        return $x("//table//tr/th[5]");
    }

    SelenideElement columnPdf() {
        return $x("//table//tr/th[6]");
    }

    SelenideElement typeOfTransactionInsideTable() {
        return $x("//table//tr/td[1]");
    }

    SelenideElement dataInsideTable() {
        return $x("//table//tr/td[2]");
    }

    SelenideElement quantityInsideTable() {
        return $x("//table//tr/td[3]");
    }

    SelenideElement balanceInsideTable() {
        return $x("//table//tr/td[4]");
    }

    SelenideElement serialNumInsideTable() {
        return $x("//table//tr/td[5]");
    }

    SelenideElement pdfInsideTable() {
        return $x("//table//tr/td[6]");
    }
}
