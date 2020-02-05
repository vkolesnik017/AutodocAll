package ATD.ProductPage;


import ATD.DataBase;
import ATD.Product_page;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.closeCookiesFooterMessage;
import static ATD.CommonMethods.openPage;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;

public class QC_708_FAQ_TestVinPopupAfterHover {
    private Product_page productPage = new Product_page();
    private DataBase dataBase = new DataBase();

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks vin popup after hover")
    public void testVinPopupAfterHover() throws SQLException {
        openPage("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "product14"));
        closeCookiesFooterMessage();
        productPage.vinInfo().hover();
        productPage.vinInfoDropdown().shouldBe(visible);
        close();
    }
}
