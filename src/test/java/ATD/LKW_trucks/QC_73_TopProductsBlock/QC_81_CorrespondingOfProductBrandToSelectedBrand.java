package ATD.LKW_trucks.QC_73_TopProductsBlock;

import ATD.LKW_Category_brand_page_Logic;
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

public class QC_81_CorrespondingOfProductBrandToSelectedBrand {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_brand,lkw_category_maker_brand");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check corresponding brand of top product to selected brand on page which is open")
    public void testChecksCorrespondingOfProductBrandToSelectedBrand(String route) {
        openPage(route);
        new LKW_Category_brand_page_Logic()
                .productCorrespondTOSelectedBrand("MANN-FILTER");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
