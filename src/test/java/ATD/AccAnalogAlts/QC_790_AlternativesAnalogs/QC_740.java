package ATD.AccAnalogAlts.QC_790_AlternativesAnalogs;

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

public class QC_740 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_product");
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
    public void close() {
        closeWebDriver();
    }
}
