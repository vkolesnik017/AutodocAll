package PKW.Section_Oil.QC_964_OilTypesBlockOnMainOilPage;

import Common.DataBase;
import PKW.Motoroil_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_966 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to listing by selected type of oil")
    public void testChecksTransitionToListingBySelectedTypeOfOil(String route) throws SQLException {
        openPage(route);

        new Motoroil_page_Logic()
                .presenceOfOilTypesBlock()
                .selectTypeOfOil(0);
        checkingContainsUrl(new DataBase("PKW").getRouteByRouteName("DE", "motoroil_chemical_type2"));
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
