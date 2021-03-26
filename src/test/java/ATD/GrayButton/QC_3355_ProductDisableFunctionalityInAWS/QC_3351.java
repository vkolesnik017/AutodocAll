package ATD.GrayButton.QC_3355_ProductDisableFunctionalityInAWS;

import ATD.Category_car_list_page_Logic;
import ATD.Main_page_Logic;
import ATD.Product_page_Logic;
import ATD.Search_page_Logic;
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

public class QC_3351 {

    private final DataBase db = new DataBase("ATD");
    private final Product_page_Logic productPageLogic = new Product_page_Logic();
    private final Search_page_Logic searchPageLogic = new Search_page_Logic();
    private final Category_car_list_page_Logic categoryCarListPageLogic = new Category_car_list_page_Logic();
    private String productID;

    @BeforeClass
    void setUp() throws SQLException {
        setUpBrowser(false, "chrome", "77.0", false);
        openPage(db.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product98"));
        productID = productPageLogic
                .checkAbsenceGrayBtnOnPage()
                .getProductId();
        openPage(db.getFullRouteByRouteAndSubroute("prod", "EN", "main", "product98"));
        productPageLogic.checkAbsenceGrayBtnOnPage();
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description("Checking product disconnect functionality in aws (grey button by country)")
    public void testCheckFunctionalityOfDisabledProductInGreyBtnByCountry() throws SQLException {
        new ProductCard_aws(productID).openProductCardPageAndLogin()
                .addToGreyBtnByCountryMethod("DE");
        openPage(db.getFullRouteByRouteName("prod", "DE", "main"));
        new Main_page_Logic().checkPresenceTextInAutocomplete("4376518","VAN WEZEL Kühlergitter  (4376518)");
        openPage(db.getFullRouteByRouteAndSubroute("prod", "DE", "main", "search52"));
        searchPageLogic.checkPresenceGrayBtnAtExpectedProduct(productID);
        openPage(db.getFullRouteByRouteAndSubroute("prod", "DE", "main", "category_car_list71"));
        categoryCarListPageLogic.checkPresenceGrayBtnAtExpectedProduct(productID);
        openPage(db.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product98"));
        productPageLogic.checkPresenceGrayBtnOnPage();
        openPage(db.getFullRouteByRouteAndSubroute("prod", "EN", "main", "product98"));
        productPageLogic.checkAbsenceGrayBtnOnPage();
    }

    @AfterMethod
    public void close() throws SQLException {
        new ProductCard_aws(productID).openProductCardPageAndLogin()
                .removeCountryFromBlockOfDisabledProductsByGrayBtn("DE");
        openPage(db.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product98"));
        productPageLogic.checkAbsenceGrayBtnOnPage();
        openPage(db.getFullRouteByRouteAndSubroute("prod", "DE", "main", "search52"));
        searchPageLogic.checkAbsenceGrayBtnAtExpectedProduct(productID);
        openPage(db.getFullRouteByRouteAndSubroute("prod", "DE", "main", "category_car_list71"));
        categoryCarListPageLogic.checkAbsenceGrayBtnAtExpectedProduct(productID);
        closeWebDriver();
    }
}
