package ATD;


import io.qameta.allure.Step;

import java.sql.SQLException;

import static ATD.CommonMethods.waitingWhileLinkBecomeExpected;
import static com.codeborne.selenide.Selenide.switchTo;

public class TyresProduct_page_Logic extends TyresProduct_page {

    @Step("Click delivery link and check redirect. TyresProduct_page")
    public TyresProduct_page_Logic clickDeliveryLinkAndCheckRedirect() throws SQLException {
        deliveryLink().click();
        switchTo().window(1);
        waitingWhileLinkBecomeExpected(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "staticVersand"));
        return this;
    }

}
