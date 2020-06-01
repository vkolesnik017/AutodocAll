package MOTO.QC_355_BenefitsBlock;

import ATD.Moto_Category_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;

public class QC_359_ElementsOfBenefitsBlock {

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
    @Description(value = "Test checks elements of benefits block")
    public void testChecksElementsOfBenefitsBlock(String route) {
        openPage(route);

        new Moto_Category_page_Logic()
                .presenceOfElementsAtBenefitBlock();
    }
}
