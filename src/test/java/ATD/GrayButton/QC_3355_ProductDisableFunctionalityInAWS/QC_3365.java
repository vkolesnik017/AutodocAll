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

public class QC_3365 {

    private final DataBase dataBaseATD = new DataBase("ATD");
    private final DataBase dataBasePKW = new DataBase("PKW");
    private final Product_page_Logic productPageLogicATD = new Product_page_Logic();
    private final PKW.Product_page_Logic productPageLogicPKW = new PKW.Product_page_Logic();
    private String productID;

    @BeforeClass
    void setUp() throws SQLException {
        setUpBrowser(false, "chrome", "77.0", false);
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product32"));
        productID = productPageLogicATD
                .checkAbsenceGrayBtnOnPage()
                .getProductId();
        openPage(dataBasePKW.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product12"));
        productPageLogicPKW.checkPresenceBtnAddToBasket();
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description("Check the functionality of disabling the product in aws (in exceptions by skin)")
    public void testCheckFunctionalityOfDisabledProductInSkinExceptions() throws SQLException {
        new ProductCard_aws(productID).openProductCardPageAndLogin()
                .removeGoodsFromSitesAcrossTheSkin("atd");
        openPage(dataBaseATD.getFullRouteByRouteName("prod", "DE", "main"));
        new Main_page_Logic().checkAbsenceTextInAutocomplete("JGT243T", "GOETZE Dichtring, Ventilschaft  (50-307044-50)");
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "search55"));
        new Search_page_Logic().checkAbsenceArticleNum("50-307044-50");
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "category_car_list73"));
        new Category_car_list_page_Logic().checkAbsenceArticleNum("50-307044-50");
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product32"));
        productPageLogicATD.checkPresenceGrayBtnOnPage();
        openPage(dataBasePKW.getFullRouteByRouteAndSubroute("prod", "DE", "main", "search4"));
        new PKW.Search_page_Logic().checkPresenceAddToCartBtnForSpecificItem(productID);
        openPage(dataBasePKW.getFullRouteByRouteAndSubroute("prod", "DE", "main", "car_parts6"));
        new PKW.Car_parts_Logic().checkPresenceAddToCartBtnForSpecificItem(productID);
    }

    @AfterMethod
    public void close() throws SQLException {
        new ProductCard_aws(productID).openProductCardPageAndLogin()
                .removeCountryFromBlockOfDisabledProductsBySkin();
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "category_car_list73"));
        new Category_car_list_page_Logic().checkPresenceArticleNum("50-307044-50")
                .checkPresenceAddToCartBtnForSpecificItem(productID);
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "search55"));
        new Search_page_Logic().checkPresenceArticleNum("50-307044-50")
                .checkPresenceAddToCartBtnForSpecificItem(productID);
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product32"));
        productPageLogicATD.checkAbsenceGrayBtnOnPage();
        closeWebDriver();
    }
}
