package MOTO.QC_730_ProductPage;

import ATD.Moto_Product_page_Logic;
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
import static com.codeborne.selenide.Selenide.close;

public class QC_740_PresenceOfDetailInformationPopUp {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_product");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of detail information popUp")
    public void testChecksPresenceOfDetailInformationPopUp(String route) {
        openPage(route);

        new Moto_Product_page_Logic().selectMotoInHorizontalSelector("4081", "12111", "104173")
                .presenceOfMotoBrandAtInfoMessage("BMW MOTORCYCLES K 1 (K589")
                .presenceOfAnalogProductBlock()
                .presenceOfDetailInfoPopUp();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
