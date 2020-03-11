package ATD.ProductGroups;

import ATD.Product_page_Logic;
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

public class QC_1592_HeavyCargo {


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "product6");
    }


    @Owner(value = "Chelombitko")
    @Test(dataProvider = "route")
    @Description(value = "Test checks the opening (Versand) of the page and the presence of the block (Sperrgutversand)")
    @Flaky
    public void checkingOrderWithHeavyCargo(String route) {
        openPage(route);
        new Product_page_Logic().checkingHeavyCargoLinkTransition();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
