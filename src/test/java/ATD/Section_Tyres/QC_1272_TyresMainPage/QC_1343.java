package ATD.Section_Tyres.QC_1272_TyresMainPage;

import Common.SetUp;
import ATD.TyresListing_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1343 {

    private TyresListing_page_Logic tyresListingPageLogic = new TyresListing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_maker,tyres,tyres2,tyres3,tyres4,tyres_feature");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Transition To Diameter Listing From Popular Diameters Relink Block")
    public void tesGoToDiameterListingFromPopularDiametersRelinkBlock(String route) {
        openPage(route);
        String diameterFromRelinkBlock = tyresListingPageLogic.getDiameterFromRelinkBlock();
        tyresListingPageLogic.checkDiameterRelink();
        String diameterFromSelector = tyresListingPageLogic.getValueDiameterFromSelector();
        Assert.assertEquals(diameterFromRelinkBlock, diameterFromSelector);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
