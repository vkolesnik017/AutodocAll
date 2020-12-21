package ATD.Search.QC_548_SearchTooltips;

import ATD.LKW_main_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.open;

public class QC_3120_InformationPopUpWithSearchTips {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routeLKW")
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_main");
    }

    @Test(dataProvider = "routeLKW")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test check information pop-up with search tips")
    public void testCheckInformationPopUpWithSearchTips(String route) {
        open(route);
        new LKW_main_page_Logic().clickOnExampleIconInSearchBar()
                .checkValuesInExampleInfoPopUp()
                .closeExampleInfoPopUp()
                .clickOnExampleIconInSearchBar()
                .clickOnInvisibleMainLogo();
    }
}
