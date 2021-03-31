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

public class QC_3579 {

    private final DataBase db = new DataBase("ATD");
    private final Product_page_Logic productPageLogic = new Product_page_Logic();
    private final Product_page_Logic productPageLogicATD = new Product_page_Logic();
    private final MotoroilViscosity_page_Logic motoroilViscosityPageLogic = new MotoroilViscosity_page_Logic();
    private final Motoroil_search_Logic motoroilSearchLogic = new Motoroil_search_Logic();
    private final Main_page_Logic mainPageLogic = new Main_page_Logic();
    private final Search_page_Logic search_page_logic = new Search_page_Logic();
    private String productID;

    @BeforeClass
    void setUp() throws SQLException {
        setUpBrowser(false, "chrome", "77.0", false);
        openPage(db.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product102"));
        productID = productPageLogic
                .checkAbsenceGrayBtnOnPage()
                .getProductId();
        openPage(db.getFullRouteByRouteAndSubroute("prod", "CH", "main", "product102"));
        productPageLogic.checkAbsenceGrayBtnOnPage();
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description("Functional check gray button by country on oils")
    public void testCheckFunctionalityOfDisabledOisLProductInGreyBtnByCountry() throws SQLException {
        new ProductCard_aws(productID).openProductCardPageAndLogin()
                .addToGreyBtnByCountryMethod("DE");
        openPage(db.getFullRouteByRouteName("prod", "DE", "main"));
        mainPageLogic.checkPresenceProductNamesInAutocompleteList("105898", "MOTUL Motoröl  (105898)");
        openPage(db.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product102"));
        productPageLogicATD.checkPresenceGrayBtnOnPage();
        openPage(db.getFullRouteByRouteAndSubroute("prod", "DE", "main", "motoroil_viscosity6"));
        motoroilViscosityPageLogic.checkAbsenceArticleNum("105898")
                .checkPresenceSpecificProductInGrayBtnInLiterBlock(productID);
        openPage(db.getFullRouteByRouteAndSubroute("prod", "DE", "main", "motoroil_search5"));
        motoroilSearchLogic.checkAbsenceArticleNum("105898")
                .checkPresenceSpecificProductInGrayBtnInLiterBlock(productID);
        openPage(db.getFullRouteByRouteAndSubroute("prod", "DE", "main", "search58"));
        search_page_logic.checkPresenceGrayBtnAtExpectedProduct(productID);
        openPage(db.getFullRouteByRouteAndSubroute("prod", "CH", "main", "product102"));
        productPageLogic.checkAbsenceGrayBtnOnPage();
    }

    @AfterMethod
    public void close() throws SQLException {
        new ProductCard_aws(productID).openProductCardPageAndLogin()
                .removeCountryFromBlockOfDisabledProductsByGrayBtn("DE");
        openPage(db.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product102"));
        productPageLogicATD.checkAbsenceGrayBtnOnPage();
        openPage(db.getFullRouteByRouteAndSubroute("prod", "DE", "main", "motoroil_viscosity6"));
        motoroilViscosityPageLogic.checkPresenceSpecificArticleNumOnListing("105898")
                .checkAbsenceGrayBtnAtExpectedProduct(productID);
        openPage(db.getFullRouteByRouteAndSubroute("prod", "DE", "main", "motoroil_search5"));
        motoroilSearchLogic.checkPresenceSpecificArticleNumOnListing("105898")
                .checkAbsenceGrayBtnAtExpectedProduct(productID);
        openPage(db.getFullRouteByRouteAndSubroute("prod", "DE", "main", "search58"));
        search_page_logic.checkPresenceSpecificArticleNumOnListing("105898")
                .checkPresenceAddToCartBtnForSpecificItem(productID)
                .checkAbsenceGrayBtnAtExpectedProduct(productID);
        closeWebDriver();
    }
}