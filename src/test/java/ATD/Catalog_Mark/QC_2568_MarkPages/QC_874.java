package ATD.Catalog_Mark.QC_2568_MarkPages;

import Common.DataBase;
import ATD.Moto_main_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_874 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on TOP model element")
    public void testChecksTransitionByClickOnTopModelElement(String route) throws SQLException {
        openPage(route);

        new Moto_main_page_Logic()
                .selectTopMotoModelsFromBlock(0);
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "moto_catalog_model4"));
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
