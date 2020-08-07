package MOTO.QC_355_BenefitsBlock;

import ATD.Moto_Category_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_358_BehaviorOfBenefitBlock {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category,moto_category_maker");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks behavior of benefit block at hover")
    public void testChecksBehaviorOfBenefitBlock(String route) {
        openPage(route);

        new Moto_Category_page_Logic()
                .visibilityOfTooltipAtBenefitsLinks();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
