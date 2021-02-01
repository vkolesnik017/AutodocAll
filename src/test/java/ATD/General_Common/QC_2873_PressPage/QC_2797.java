package ATD.General_Common.QC_2873_PressPage;

import ATD.Static_page_atd_page_Logic;
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
import static Common.File.assertThatPdfContainsText;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2797 {


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", true);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "staticPresse");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transitions by tab in top sections block on main page")
    public void testChecksTransitionsByTabInTopSectionsBlockOnMainPage(String route) throws IOException {
        openPage(route);
        new Static_page_atd_page_Logic().presenceHelpingBlock().downloadPdfInHelpingBlock();
        assertThatPdfContainsText("D:/ЗАГРУЗКИ/Projektbericht_Autodoc_Internet.pdf", "ÜBER DIE AKTION");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
