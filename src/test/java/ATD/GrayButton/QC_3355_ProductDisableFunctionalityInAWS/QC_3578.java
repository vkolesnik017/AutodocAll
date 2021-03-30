package ATD.GrayButton.QC_3355_ProductDisableFunctionalityInAWS;

import ATD.*;
import AWS.ProductCard_aws;
import Common.DataBase;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3578 {

    private final DataBase dataBaseATD = new DataBase("ATD");
    private final DataBase dataBasePKW = new DataBase("PKW");
    private final Product_page_Logic productPageLogicATD = new Product_page_Logic();
    private final PKW.Product_page_Logic productPageLogicPKW = new PKW.Product_page_Logic();
    private final MotoroilViscosity_page_Logic motoroilViscosityPageLogic = new MotoroilViscosity_page_Logic();
    private final Motoroil_search_Logic motoroilSearchLogic = new Motoroil_search_Logic();
    private final Main_page_Logic mainPageLogic = new Main_page_Logic();
    private final Search_page_Logic search_page_logic = new Search_page_Logic();
    private String productID;

    @BeforeClass
    void setUp() throws SQLException {
        setUpBrowser(false, "chrome", "77.0", false);
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product101"));
        productID = productPageLogicATD
                .checkAbsenceGrayBtnOnPage()
                .getProductId();
        openPage(dataBasePKW.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product18"));
        productPageLogicPKW.checkPresenceBtnAddToBasket();
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description("Checking the function of the skim disconnect on the oils")
    public void testCheckFunctionalityOfDisabledOilProductInGrayBtnInSkin() throws SQLException {
        new ProductCard_aws(productID).openProductCardPageAndLogin()
                .removeGoodsFromSitesAcrossTheSkin("atd");
        openPage(dataBaseATD.getFullRouteByRouteName("prod", "DE", "main"));
        mainPageLogic.checkAbsenceProductNamesInAutocompleteList("15046C", "CASTROL Motoröl  (15046C)");
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product101"));
        productPageLogicATD.checkPresenceGrayBtnOnPage();
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "motoroil_viscosity5"));
        motoroilViscosityPageLogic.checkAbsenceArticleNum("15046C")
                .checkAbsenceSpecificProductInGrayBtnInLiterBlock(productID);
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "motoroil_search4"));
        motoroilSearchLogic.checkAbsenceArticleNum("15046C")
                .checkAbsenceSpecificProductInGrayBtnInLiterBlock(productID);
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "search57"));
        search_page_logic.checkAbsenceAddToCartBtnForSpecificItem(productID);
        openPage(dataBasePKW.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product18"));
        productPageLogicPKW.checkPresenceBtnAddToBasket();
    }

    @AfterMethod
    public void close() throws SQLException {
        new ProductCard_aws(productID).openProductCardPageAndLogin()
                .removeCountryFromBlockOfDisabledProductsBySkin();
        openPage(dataBaseATD.getFullRouteByRouteName("prod", "DE", "main"));
        mainPageLogic.checkPresenceProductNamesInAutocompleteList("15046C", "CASTROL Motoröl  (15046C)");
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product101"));
        productPageLogicATD.checkAbsenceGrayBtnOnPage();
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "motoroil_viscosity5"));
        motoroilViscosityPageLogic.checkPresenceSpecificArticleNumOnListing("15046C")
                .checkAbsenceGrayBtnAtExpectedProduct(productID);
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "motoroil_search4"));
        motoroilSearchLogic.checkPresenceSpecificArticleNumOnListing("15046C")
                .checkAbsenceGrayBtnAtExpectedProduct(productID);
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "search57"));
        search_page_logic.checkPresenceSpecificArticleNumOnListing("15046C")
                .checkPresenceAddToCartBtnForSpecificItem(productID)
                .checkAbsenceGrayBtnAtExpectedProduct(productID);
        closeWebDriver();
    }
}