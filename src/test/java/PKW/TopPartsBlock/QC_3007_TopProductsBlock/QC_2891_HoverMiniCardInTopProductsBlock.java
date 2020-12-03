package PKW.TopPartsBlock.QC_3007_TopProductsBlock;

import PKW.Tyres_maker_page_Logic;
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
import static PKW.CommonMethods.getNameRouteFromJSVarInHTML;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2891_HoverMiniCardInTopProductsBlock {

    public Tyres_maker_page_Logic tyresMakerPageLogic = new Tyres_maker_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("subprod", "DE", "main_tyres", "tyres_maker,tyres_maker_group");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test check hover mini card in top products block")
    public void testCheckHoverMiniCardInTopProductsBlock(String route) {
        openPage(route);
        tyresMakerPageLogic.checkPresenceTopTyresBlock()
                .clickBtnDetailsInPopupFromTopBlock();
        Assert.assertEquals(getNameRouteFromJSVarInHTML(), "tyre_item");
    }


    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
