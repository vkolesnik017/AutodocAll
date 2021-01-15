package ATD.Section_Tyres.QC_1272_TyresMainPage;

import ATD.Tyres_feature_page_Logic;
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

public class QC_2794 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_feature,tyres_feature2,tyres_feature3,tyres_feature4,tyres_feature5,tyres_feature6,tyres_feature7,tyres_maker");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test сheck presence of SEO text")
    public void testCheckPresenceOfSeoText(String route) {
        openPage(route);
        new Tyres_feature_page_Logic().checkSeoBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
