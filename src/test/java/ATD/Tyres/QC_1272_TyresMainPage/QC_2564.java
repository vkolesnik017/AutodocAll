package ATD.Tyres.QC_1272_TyresMainPage;

import ATD.Tyres_page_Logic;
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

public class QC_2564 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres2,tyres3,tyres4");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = " Test Сhecking the number of identical brands and size tyres in top block (Lkw Moto Off)")
    public void testCheckingProductsFromTopTyresBlockLkwMotoOff(String route) {
        openPage(route);
        new Tyres_page_Logic().checkingNumberIdenticalBrandAndSizeInTopBlock(3);


    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
