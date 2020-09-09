package ATD.ProductPage;


import Common.DataBase;
import ATD.Product_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_708_FAQ_TestVinPopupAfterHover {

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks vin popup after hover")
    public void testVinPopupAfterHover() throws SQLException {
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "product14"));
        new Product_page_Logic().checkVinPopup();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
