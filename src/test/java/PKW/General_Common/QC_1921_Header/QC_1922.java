package PKW.General_Common.QC_1921_Header;

import Common.SetUp;
import PKW.Main_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1922 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("PKW").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Owner(value = "LavrynenkoOlha")
    @Description(value = "Test checks the elements in the rating block in the header")
    public void testRatingBlockInTheHeader(String route) throws SQLException {
        openPage(route);
        new Main_page_Logic().checkHeaderRatingElements()
                .checkHeaderAppAndAgbLinks();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}