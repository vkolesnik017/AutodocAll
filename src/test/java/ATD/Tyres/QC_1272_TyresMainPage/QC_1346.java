package ATD.Tyres.QC_1272_TyresMainPage;


import ATD.Tyres_feature_page_Logic;
import Common.SetUp;
import ATD.Tyres_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1346 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routesTyresPage")
    Object[] dataProviderTyresPage() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres");
    }

    @DataProvider(name = "routesTyresFeature")
    Object[] dataProviderTyresFeature() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_maker,tyres_feature");
    }

    @Test(dataProvider = "routesTyresPage")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Transition To All Sizes Page From Tyres Main Page")
    public void testGoToAllSizesPageFromTyresMainPage(String route) {
        openPage(route);
        new Tyres_page_Logic().clickAllTyresSizesButton();
        checkingContainsUrl("reifen/type_list");
    }

    @Test(dataProvider = "routesTyresFeature")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test Checks Transition To All Sizes Page From Tyres feature and maker page")
    public void testGoToAllSizesPageFromTyresFeatureAndMakerPage(String route) {
        openPage(route);
        new Tyres_feature_page_Logic().clickAllTyresSizesBtnInSizeBlock();
        checkingContainsUrl("reifen/type_list");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
