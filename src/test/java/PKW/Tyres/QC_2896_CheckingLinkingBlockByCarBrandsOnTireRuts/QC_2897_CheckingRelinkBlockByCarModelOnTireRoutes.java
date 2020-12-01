package PKW.Tyres.QC_2896_CheckingLinkingBlockByCarBrandsOnTireRuts;

import Common.SetUp;
import PKW.Tyres_maker_page_Logic;
import PKW.Tyres_page_Logic;
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
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

public class QC_2897_CheckingRelinkBlockByCarModelOnTireRoutes {

    private Tyres_page_Logic tyresPageLogic = new Tyres_page_Logic();
    private Tyres_maker_page_Logic tyresMakerPageLogic = new Tyres_maker_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("PKW").setUpShopsWithMainRoute("subprod", "DE", "main_tyres");
    }

    @DataProvider(name = "route2", parallel = true)
    Object[] dataProvider2() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("subprod", "DE", "main_tyres", "tyres_maker");
    }


    @Test(dataProvider = "route")
    @Owner(value = "Sergey_QA")
    @Description(value = "Test checking relink block by car model on tires ruts")
    public void testCheckingRelinkBlockByCarModelOnTireRuts(String route) {
        openPage(route);
        String urlModel = tyresPageLogic.getUrlModelInRelinkByCarBlock();
        String urlBtnMore = tyresPageLogic.getUrlBtnMorelInRelinkByCarBlock();
        tyresPageLogic.checkPresenceRelinkByCarBlock()
                .clickOnModelInRelinkByCarBlock();
        String urlAfterTransition = url();
        Assert.assertEquals(urlModel, urlAfterTransition);
        back();
        tyresPageLogic.clickBtnMoreInRelinkByCarBlock()
       .checkPresenceAllCarsBlock();
        String urlPageAllCars = url();
        Assert.assertEquals(urlBtnMore, urlPageAllCars);
    }

    @Test(dataProvider = "route2")
    @Owner(value = "Sergey_QA")
    @Description(value = "Test checking relink block by car model on tires ruts")
    public void test2CheckingRelinkBlockByCarModelOnTireRuts(String route) {
        openPage(route);
        String urlModel = tyresMakerPageLogic.getUrlModelInRelinkByCarBlock();
        String urlBtnMore = tyresMakerPageLogic.getUrlBtnMorelInRelinkByCarBlock();
        tyresMakerPageLogic.checkPresenceRelinkByCarBlock()
                .clickOnModelInRelinkByCarBlock();
        String urlAfterTransition = url();
        Assert.assertEquals(urlModel, urlAfterTransition);
        back();
        tyresMakerPageLogic.clickBtnMoreInRelinkByCarBlock()
                .checkPresenceAllCarsBlock();
        String urlPageAllCars = url();
        Assert.assertEquals(urlBtnMore, urlPageAllCars);
    }


    @AfterMethod
    public void close() {
        closeWebDriver();
    }

}
