package ATD.MOTO.QC_385_BlockBrandsAndModelsOfAutoManufacturers;

import ATD.Moto_Category_maker_page_Logic;
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

public class QC_390_AbilityToOpenAndMinimizedOfBrandsBlock {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_maker3");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks ability to open and minimized  of brands and models block")
    public void testChecksAbilityToOpenAndMinimizedOfBrandsBlock(String route) {
        openPage(route);

        new Moto_Category_maker_page_Logic()
                .presenceOfModelsBlock()
                .openOfModelsBlock()
                .minimizedOfModelBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
