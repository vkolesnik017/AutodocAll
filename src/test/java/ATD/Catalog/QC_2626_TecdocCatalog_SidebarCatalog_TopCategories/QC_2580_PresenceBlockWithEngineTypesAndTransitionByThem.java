package ATD.Catalog.QC_2626_TecdocCatalog_SidebarCatalog_TopCategories;

import ATD.Moto_Catalog_model_page_Logic;
import ATD.Moto_Catalog_page_Logic;
import Common.SetUp;
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

public class QC_2580_PresenceBlockWithEngineTypesAndTransitionByThem {

    private Moto_Catalog_model_page_Logic motoCatalogModelPageLogic = new Moto_Catalog_model_page_Logic();
    private Moto_Catalog_page_Logic motoCatalogPageLogic = new Moto_Catalog_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main",  "moto_catalog_model2,moto_catalog_model4,moto_catalog_model5");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test checking presence block with engine types and transition by them only moto")
    public void testCheckingPresenceBlockWithEngineTypesAndTransitionByThem(String route) {
        openPage(route);
        motoCatalogModelPageLogic.checkPresenceModelAndEnginesTypeBlock()
                .clickOnTooltipFromModelAndEnginesTypeBlock()
                .checkConformityVehicleInTooltipFromModelAndEngineBlock()
                .clickBtnPlusFromModelAndEnginesTypeBlock();
        String dataEngine = motoCatalogModelPageLogic.getDataFromFirstPositionInModelAndEngineBlock();
        motoCatalogModelPageLogic.clickFirstPositionFromModelAndEngineBlock();
        motoCatalogPageLogic. openMotoSelectorsBlock();
        String dataEngineFromSelector = motoCatalogPageLogic.getDataFromMotorOfMotoSelector();
        Assert.assertEquals(dataEngine, dataEngineFromSelector);

    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
