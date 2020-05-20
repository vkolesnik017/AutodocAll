package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Index_instruments_page_Logic extends Index_instruments_page {

    @Step("Checks presence main title on page tools. Index_instruments_page")
    public Index_instruments_page_Logic checkPresenceMainTitle() {
        titleMainPage().shouldBe(visible);
        return this;
    }

    @Step("Checks current url .Index_instruments_page")
    public Index_instruments_page_Logic checkCurrentUrl(String subRoute) throws SQLException {
        DataBase db = new DataBase();
        Assert.assertEquals(url(), db.getFullRouteByRouteAndSubroute("prod", "DE", "main", subRoute));
        return this;
    }
}
