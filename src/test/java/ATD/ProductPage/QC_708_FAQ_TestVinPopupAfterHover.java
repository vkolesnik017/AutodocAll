package ATD.ProductPage;


import ATD.DataBase;
import ATD.Product_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.closeCookiesFooterMessage;
import static ATD.CommonMethods.openPage;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;

public class QC_708_FAQ_TestVinPopupAfterHover {
    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private DataBase dataBase = new DataBase();

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks vin popup after hover")
    public void testVinPopupAfterHover() throws SQLException {
        openPage("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "product14"));
        closeCookiesFooterMessage();
        product_page_logic.vinInfo().hover();
        product_page_logic.vinInfoDropdown().shouldBe(visible);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
