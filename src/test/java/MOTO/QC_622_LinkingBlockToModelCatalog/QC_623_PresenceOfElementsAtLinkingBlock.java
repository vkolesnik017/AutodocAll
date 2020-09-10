package MOTO.QC_622_LinkingBlockToModelCatalog;

import Common.DataBase;
import ATD.Moto_makers_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;

public class QC_623_PresenceOfElementsAtLinkingBlock {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_makers");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks the presence of a block and a header, click transitions - linking block")
    public void testChecksPresenceOfElementsAtLinkingBlock(String route) throws SQLException {
        openPage(route);

        new Moto_makers_page_Logic()
                .checkElementsOfLinkingBlock()
                .clickOnTopModelInLinkingBlock(1, 4);
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "moto_catalog_model4"));
    }

}
