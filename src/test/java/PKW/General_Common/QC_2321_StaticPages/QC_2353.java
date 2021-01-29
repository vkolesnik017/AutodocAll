package PKW.General_Common.QC_2321_StaticPages;

import Common.SetUp;
import PKW.Main_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2353 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "main");
    }

    @Test(dataProvider = "route")
    @Owner(value = "LavrynenkoOlha")
    @Flaky
    @Description(value = "Test checks request form for new delivery country on the Versand page")
    public void testStaticPage_Versand(String route) throws SQLException {
        openPage(route);
        new Main_page_Logic().clickFooterVersandLink()
                .openingTheDeliveryForm()
                .sendingEmptyDeliveryForm()
                .fillingAndSendingDeliveryForm();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
