package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Profile_deposit_page {

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
}
