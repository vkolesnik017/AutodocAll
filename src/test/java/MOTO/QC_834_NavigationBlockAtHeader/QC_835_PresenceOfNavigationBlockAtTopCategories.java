package MOTO.QC_834_NavigationBlockAtHeader;

import ATD.Moto_main_page_Logic;
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

public class QC_835_PresenceOfNavigationBlockAtTopCategories {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_main");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of navigation block at TOP categories")
    public void testChecksPresenceOfNavigationBlockAtTopCategories(String route) {
        openPage(route);

        new Moto_main_page_Logic()
                .presenceOfNavigationBlock()
                .checkTopCategoriesInHeader();

    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
