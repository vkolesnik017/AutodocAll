package PKW.Tyres.QC_2892_CheckingLinkingBlockBySizeOnTireRoutes;

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
import static PKW.CommonMethods.checkingContainsUrl;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

public class QC_2893_CheckingLinkingBlockBySizeOnTiresRoutes {

    private Tyres_page_Logic tyresPageLogic = new Tyres_page_Logic();
    private Tyres_maker_page_Logic tyresMakerPageLogic = new Tyres_maker_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("PKW").setUpShopsWithMainRoute("subprod", "DE", "main_tyres");
    }

    @DataProvider(name = "route2", parallel = true)
    Object[] dataProvider2() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("subprod", "DE", "main_tyres", "tyres,tyres2,tyres3");
    }

    @DataProvider(name = "routeCar", parallel = true)
    Object[] dataProviderCar() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("subprod", "DE", "main_tyres", "tyres_maker,tyres_maker_group");
    }


    @Test(dataProvider = "route")
    @Owner(value = "Sergey_QA")
    @Description(value = "Test Checking the linking block by size on the tires routes")
    public void testCheckingLinkingBlockBySizeOnTireRoutes(String route) {
        openPage(route);
        String urlBtnSize = tyresPageLogic.getUrlBtnSizeInRelinkBlockBySize();
        String textBtnSize = tyresPageLogic.getTextBtnSizeInRelinkBlockBySize().replace(" ", "-").toLowerCase();
        tyresPageLogic.checkPresenceRelinkBlockBySize()
                .clickOnSizeDiameterFromRelinkBlock();
        String urlPage = url();
        Assert.assertEquals(urlBtnSize, urlPage);
        checkingContainsUrl(textBtnSize);
        back();
        String urlBtnWheelDimensions = tyresPageLogic.getUrlBtnWheelDimensionsInRelinkBlockBySize();
        String textBtnWheelDimensions = tyresPageLogic.getTextBtnWheelDimensionsInRelinkBlockBySize().replace("/", "-").toLowerCase();
        tyresPageLogic.checkPresenceRelinkBlockBySize()
                .clickOnWheelDimensionsFromRelinkBlock();
        String urlPageDimensions = url();
        Assert.assertEquals(urlBtnWheelDimensions, urlPageDimensions);
        checkingContainsUrl(textBtnWheelDimensions);
        back();
        tyresPageLogic.clickBtnAllSizeInRelinkBySizeBlock()
                .checkPresenceTiresSizeTable();
    }


    @Test(dataProvider = "route2")
    @Owner(value = "Sergey_QA")
    @Description(value = "Test Checking the linking block by size on the tires routes")
    public void test2CheckingLinkingBlockBySizeOnTireRoutes(String route) {
        openPage(route);
        String urlBtnSize = tyresPageLogic.getUrlBtnSizeInRelinkBlockBySize();
        String textBtnSize = tyresPageLogic.getTextBtnSizeInRelinkBlockBySize().replace(" ", "-").toLowerCase();
        tyresPageLogic.checkPresenceRelinkBlockBySize()
                .clickOnSizeDiameterFromRelinkBlock();
        String urlPage = url();
        Assert.assertEquals(urlBtnSize, urlPage);
        checkingContainsUrl(textBtnSize);
        back();
        String urlBtnWheelDimensions = tyresPageLogic.getUrlBtnWheelDimensionsInRelinkBlockBySize();
        String textBtnWheelDimensions = tyresPageLogic.getTextBtnWheelDimensionsInRelinkBlockBySize().replace("/", "-").toLowerCase();
        tyresPageLogic.checkPresenceRelinkBlockBySize()
                .clickOnWheelDimensionsFromRelinkBlock();
        String urlPageDimensions = url();
        Assert.assertEquals(urlBtnWheelDimensions, urlPageDimensions);
        checkingContainsUrl(textBtnWheelDimensions);
        back();
        tyresPageLogic.clickBtnAllSizeInRelinkBySizeBlock()
                .checkPresenceTiresSizeTable();
    }


    @Test(dataProvider = "routeCar")
    @Owner(value = "Sergey_QA")
    @Description(value = "Test Checking the linking block by size on the tires routes")
    public void test3CheckingLinkingBlockBySizeOnTireRoutes(String route) {
        openPage(route);
        String urlBtnSize = tyresMakerPageLogic.getUrlBtnSizeInRelinkBlockBySize();
        String textBtnSize = tyresMakerPageLogic.getTextBtnSizeInRelinkBlockBySize().replace("\" ", "-").toLowerCase();
        tyresMakerPageLogic.checkPresenceRelinkBlockBySize()
                .clickOnSizeDiameterFromRelinkBlock();
        String urlPage = url();
        Assert.assertEquals(urlBtnSize, urlPage);
        checkingContainsUrl(textBtnSize);
        back();
        String urlBtnWheelDimensions = tyresMakerPageLogic.getUrlBtnWheelDimensionsInRelinkBlockBySize();
        String textBtnWheelDimensions = tyresMakerPageLogic.getTextBtnWheelDimensionsInRelinkBlockBySize().replace("/", "-").replace(" ","").toLowerCase();
        tyresMakerPageLogic.checkPresenceRelinkBlockBySize()
                .clickOnWheelDimensionsFromRelinkBlock();
        String urlPageDimensions = url();
        Assert.assertEquals(urlBtnWheelDimensions, urlPageDimensions);
        checkingContainsUrl(textBtnWheelDimensions);
        back();
        tyresMakerPageLogic.clickBtnAllSizeInRelinkBySizeBlock()
                .checkPresenceTiresSizeTable();
    }


    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
