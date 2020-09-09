package PKW;

import Common.DataBase;
import io.qameta.allure.Step;
import java.sql.SQLException;
import static PKW.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Impressum_static_page_Logic extends Impressum_static_page {

    @Step("Checks the clickable links, text block visibility, contact sidebar block visibility . Impressum_static_page")
    public Impressum_static_page_Logic checkElementsOnThePage() throws SQLException {

        blockImpressumtext().shouldBe(visible).shouldHave(text("Impressum"));
        contactSidebar().shouldBe(visible);

        pkwEmail().shouldHave(attribute("href", "mailto:info@pkwteile.de"));
        atdEmail().shouldHave(attribute("href", "mailto:info@autodoc.de"));
        pkwSiteLink().click();
        checkingContainsUrl(new DataBase().getFullRouteByRouteName("prod", "DE", "main"));
        back();
        atdSiteLink().click();
        checkingContainsUrl(new Common.DataBase().getFullRouteByRouteName("prod", "DE", "main"));
        back();
        osTextLink().click();
        checkingContainsUrl("odr/main/index.cfm?event=main.home.chooseLanguage");
        back();
        return this;
    }
}

