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

public class QC_3419 {

    private final DataBase dataBaseATD = new DataBase("ATD");
    private final Product_page_Logic productPageLogicATD = new Product_page_Logic();
    private final MotoroilViscosity_page_Logic motoroilViscosityPageLogic = new MotoroilViscosity_page_Logic();
    private final Motoroil_search_Logic motoroilSearchLogic = new Motoroil_search_Logic();
    private final Search_page_Logic search_page_logic = new Search_page_Logic();
    private String productID;

    @BeforeClass
    void setUp() throws SQLException {
        setUpBrowser(false, "chrome", "77.0", false);
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product103"));
        productID = productPageLogicATD
                .checkAbsenceGrayBtnOnPage()
                .getProductId();
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description("Check the functionality of disabling the product in aws (in country exceptions)")
    public void testCheckFunctionalityOfDisabledProductInCountryExceptions() throws SQLException {
        new ProductCard_aws(productID).openProductCardPageAndLogin()
                .removeGoodsFromSitesAcrossTheCountry("DE");
        openPage(dataBaseATD.getFullRouteByRouteName("prod", "DE", "main"));
        new Main_page_Logic().checkAbsenceTextInAutocomplete("881925", "Valvoline Motoröl  (881925)");
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product103"));
        productPageLogicATD.checkPresenceGrayBtnOnPage();
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "motoroil_viscosity7"));
        motoroilViscosityPageLogic.checkAbsenceArticleNum("881925")
                .checkAbsenceSpecificProductInGrayBtnInLiterBlock(productID);
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "motoroil_search6"));
        motoroilSearchLogic.checkAbsenceArticleNum("881925")
                .checkAbsenceSpecificProductInGrayBtnInLiterBlock(productID);
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "search59"));
        search_page_logic.checkAbsenceAddToCartBtnForSpecificItem(productID);
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "AT", "main", "product103"));
        productPageLogicATD.checkAbsenceGrayBtnOnPage();
    }

    @AfterMethod
    public void close() throws SQLException {
        new ProductCard_aws(productID).openProductCardPageAndLogin()
                .removeCountryFromBlockOfDisabledProductsByCountry("DE");
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product103"));
        productPageLogicATD.checkAbsenceGrayBtnOnPage();
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "motoroil_viscosity7"));
        motoroilViscosityPageLogic.checkPresenceSpecificArticleNumOnListing("881925")
                .checkAbsenceGrayBtnAtExpectedProduct(productID);
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "motoroil_search6"));
        motoroilSearchLogic.checkPresenceSpecificArticleNumOnListing("881925")
                .checkAbsenceGrayBtnAtExpectedProduct(productID);
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "search59"));
        search_page_logic.checkPresenceSpecificArticleNumOnListing("881925")
                .checkPresenceAddToCartBtnForSpecificItem(productID)
                .checkAbsenceGrayBtnAtExpectedProduct(productID);
        closeWebDriver();
    }
}