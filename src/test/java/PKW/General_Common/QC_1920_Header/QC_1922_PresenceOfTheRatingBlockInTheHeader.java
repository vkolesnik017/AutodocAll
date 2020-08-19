package PKW.General_Common.QC_1920_Header;

import PKW.Main_page_Logic;
import PKW.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static PKW.CommonMethods.openPage;
import static PKW.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1922_PresenceOfTheRatingBlockInTheHeader {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
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