package ATD.Selector_Tires.QC_1104_TyresSelector;

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

public class QC_2713 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routesMoto", parallel = true)
    Object[] dataProviderMoto() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres4");
    }

    @Test(dataProvider = "routesMoto")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test сheck that size values don't change when season changes in selector")
    public void testCheckSizeValuesDontChangeWhenSeasonChanges(String route) {
        openPage(route);
        new Tyres_page_Logic().defaultValuesOfSelector("120", "70", "17").checkOfSeasonMotoSelector("0").selectSeasonInSelector("allwetter")
                .defaultValuesOfSelector("120", "70", "17").selectSeasonInSelector("winter")
                .defaultValuesOfSelector("120", "70", "17");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
