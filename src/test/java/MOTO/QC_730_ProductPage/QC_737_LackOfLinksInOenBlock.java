package MOTO.QC_730_ProductPage;

import ATD.Moto_Product_page_Logic;
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

public class QC_737_LackOfLinksInOenBlock {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_product4");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks lack of links in OEN block")
    public void testChecksLackOfLinksInOenBlock(String route) {
        openPage(route);

        new Moto_Product_page_Logic()
                .checkOenBlock()
                .selectMotoInHorizontalSelector("4057", "12344", "103815")
                .presenceOfMotoBrandAtInfoMessage("KAWASAKI MOTORCYCLES EN 500")
                .checkOenBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
