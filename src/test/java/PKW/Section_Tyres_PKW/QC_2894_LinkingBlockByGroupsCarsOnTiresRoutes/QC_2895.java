package PKW.Section_Tyres_PKW.QC_2894_LinkingBlockByGroupsCarsOnTiresRoutes;

import Common.SetUp;
import PKW.Tyres_maker_group_page_Logic;
import PKW.Tyres_maker_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;


public class QC_2895 {

    private Tyres_maker_page_Logic tyresMakerPageLogic = new Tyres_maker_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0",false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("subprod", "DE", "main_tyres", "tyres_maker,tyres_maker_group");
    }


    @Test(dataProvider = "route")
    @Owner(value = "Sergey_QA")
    @Description(value = "Test Checking the linking block by groups of cars on tire routes")
    public void testCheckingLinkingBlockByGroupsCarOnTires(String route) {
        openPage(route);
        tyresMakerPageLogic.checkPresencePremiumCarBlock()
                .checkWorkPaginationInPremiumBlock();
        String nameCar = tyresMakerPageLogic.getTexNameActiveCarFromPremiumBlock();
        tyresMakerPageLogic.clickOnCarFromPremiumBlock();
        String carNameTitle = new Tyres_maker_group_page_Logic().getCarNameTitle();
        Assert.assertTrue(carNameTitle.contains(nameCar));
    }


    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
