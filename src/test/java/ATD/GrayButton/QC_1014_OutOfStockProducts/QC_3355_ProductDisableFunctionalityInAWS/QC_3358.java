package ATD.GrayButton.QC_1014_OutOfStockProducts.QC_3355_ProductDisableFunctionalityInAWS;

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

public class QC_3358 {

    private DataBase dataBaseATD = new DataBase("ATD");
    private DataBase dataBasePKW = new DataBase("PKW");
    private Product_page_Logic productPageLogicATD = new Product_page_Logic();
    private PKW.Product_page_Logic productPageLogicPKW = new PKW.Product_page_Logic();
    private String productID;

    @BeforeClass
    void setUp() throws SQLException {
        setUpBrowser(false, "chrome", "77.0", false);
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product97"));
        productID = productPageLogicATD
                .checkAbsenceGrayBtnOnPage()
                .getProductId();
        openPage(dataBasePKW.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product14"));
        productPageLogicPKW.checkPresenceBtnAddToBasket();
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description("Check the functionality of disabling the product in aws (in country exceptions)")
    public void testCheckFunctionalityOfDisabledProductInCountryExceptions() throws SQLException {
        new ProductCard_aws(productID).openProductCardPageAndLogin()
                .removeGoodsFromSitesAcrossTheCountry("DE");
        openPage(dataBaseATD.getFullRouteByRouteName("prod", "DE", "main"));
        new Main_page_Logic().checkTextInAutocomplete("JGT243T");
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "search50"));
        new Search_page_Logic().checkAbsenceArticleNum("JGT243T");
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "category_car_list70"));
        new Category_car_list_page_Logic().checkAbsenceArticleNum("JGT243T");
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product97"));
        productPageLogicATD.checkPresenceGrayBtnOnPage();
        openPage(dataBasePKW.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product14"));
        productPageLogicPKW.checkPresenceGrayBtn();
        openPage(dataBasePKW.getFullRouteByRouteAndSubroute("prod", "DE", "main", "search3"));
        new PKW.Search_page_Logic().checkAbsenceArticleNum("JGT243T");
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "BE", "main", "product97"));
        productPageLogicATD.checkAbsenceGrayBtnOnPage();
    }

    @AfterMethod
    public void close() throws SQLException {
        new ProductCard_aws(productID).openProductCardPageAndLogin()
                .removeCountryFromBlockOfDisabledProductsByCountry("DE");
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product97"));
        productPageLogicATD.checkAbsenceGrayBtnOnPage();
        closeWebDriver();
    }
}