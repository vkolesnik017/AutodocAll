package MOTO.QC_361_BrandsBlock;

import ATD.Moto_Categories_maker_page_Logic;
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

public class QC_365_PresenceOfHeadlineAtTopCategories {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_categories_maker2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of headline at TOP parent categories")
    public void testChecksPresenceOfHeadlineAtTopCategories(String route) {
        openPage(route);

        new Moto_Categories_maker_page_Logic()
                .presenceOfHeadlineAtTopParentCategories();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
