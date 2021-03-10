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

public class QC_2556 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_maker,category_group,category_group_fuel4");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test checks Transition To Club Page from the PDF manuals block")
    public void testChecksTransitionToClubPageFromPDFManualBlock(String route) throws SQLException, IOException {
        openPage(route);
        new Group_list_page_Logic().checkTransitionToClubPageFromPDFManualBlock();
    }


    @DataProvider(name = "routes2", parallel = true)
    Object[] dataProvider2() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_group_year4,group_list,model_maker_list");
    }

    @Test(dataProvider = "routes2")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test checks Transition To Club Page from the PDF manuals block")
    public void test2ChecksTransitionToClubPageFromPDFManualBlock(String route) throws SQLException, IOException {
        openPage(route);
        new Group_list_page_Logic().checkTransitionToClubPageFromPDFManualBlock();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
