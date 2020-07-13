package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class Profile_bonusSystem_page {

    SelenideElement bonusLabel() {
        return $x("//span[@class='deposit']");
    }

    public  SelenideElement headerBonusAmountCurrency() {
        return $x("//div[@class='header__bonus-amount']//a//span[2]");
    }

    SelenideElement bonusesCombustionDate() {
        return $x("//span[@class='deposit__valid']/span");
    }

    SelenideElement bonusTable() {
        return $x("//div[@class='bonus_table']");
    }

    SelenideElement accruedBonusInTable() {
        return $x("//div[@class='table_over']//tr//td[3]");
    }

    SelenideElement bonusProgramBlock() {
        return $x("//div[@class='info']/ul");
    }

    ElementsCollection listOfBonusInTable() {
        return $$x("//div[@class='table_over']//tbody/tr");
    }

// Locators for pagination block
    SelenideElement paginationBloc() {
        return $x("//div[@id='light-pagination']");
    }

    SelenideElement secondPageLink() {
       return  $x("//div[@id='light-pagination']/ul/li[3]/a");
    }

    SelenideElement firstLinkPage() {
        return $x("//div[@id='light-pagination']/ul/li[2]/a");
    }

    SelenideElement pageLinkNext() {
        return $x("//div[@id='light-pagination']/ul/li[4]/a");
    }

    SelenideElement pageLinkPrev() {
        return $x("//div[@id='light-pagination']/ul/li[1]/a");
    }

}
