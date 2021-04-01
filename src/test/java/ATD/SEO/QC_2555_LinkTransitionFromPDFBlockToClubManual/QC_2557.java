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

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2557 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_maker,category_group,category_group_fuel4,category_group_year4,group_list,model_maker_list");
    }

    @Test(dataProvider = "routes" )
    @Flaky
    @Owner(value = "LavrynenkoOlha")
    @Description(value = "Test checks elements in the PDF manuals block")
    public void testChecksElementsInPDFManualBlock(String route) {
        openPage(route);
        new Group_list_page_Logic().checkElementsInPDFManualBlock();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
