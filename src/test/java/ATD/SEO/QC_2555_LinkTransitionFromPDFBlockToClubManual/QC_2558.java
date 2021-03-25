package ATD.SEO.QC_2555_LinkTransitionFromPDFBlockToClubManual;

import ATD.Group_list_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2558 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", true);
    }

    @DataProvider(name = "routes", parallel = false)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_maker,category_group,category_group_fuel4,category_group_year4,group_list,model_maker_list");
    }

    @Test(dataProvider = "routes", enabled = false) //TODO bug SEO-2549
    @Flaky
    @Owner(value = "LavrynenkoOlha")
    @Description(value = "Test checks downloads of the manuals")
    public void testChecksElementsInPDFManualBlock(String route) throws IOException {
        openPage(route);
        new Group_list_page_Logic().checkDownloadsOfManuals();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
