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

public class QC_3354 {

    private final DataBase dataBaseATD = new DataBase("ATD");
    private final DataBase dataBasePKW = new DataBase("PKW");
    private final Product_page_Logic productPageLogicATD = new Product_page_Logic();
    private final PKW.Product_page_Logic productPageLogicPKW = new PKW.Product_page_Logic();
    private final Search_page_Logic searchPageLogic = new Search_page_Logic();
    private final Category_car_list_page_Logic categoryCarListPageLogic = new Category_car_list_page_Logic();
    private String productID;

    @BeforeClass
    void setUp() throws SQLException {
        setUpBrowser(false, "chrome", "77.0", false);
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product99"));
        productID = productPageLogicATD
                .checkAbsenceGrayBtnOnPage()
                .getProductId();
        openPage(dataBasePKW.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product15"));
        productPageLogicPKW.checkPresenceBtnAddToBasket();
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description("Check the functionality of disabling the product in aws (gray button on the skin)")
    public void testCheckFunctionalityOfDisabledProductInGrayBtnInSkin() throws SQLException {
        new ProductCard_aws(productID).openProductCardPageAndLogin()
                .addToGreyBtnBySkinMethod("atd");
        openPage(dataBaseATD.getFullRouteByRouteName("prod", "DE", "main"));
        new Main_page_Logic().checkPresenceTextInAutocomplete("114 859","TOPRAN Wischarm, Scheibenreinigung  (114 859)");
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "search54"));
        searchPageLogic.checkPresenceGrayBtnAtExpectedProduct(productID);
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "category_car_list72"));
        categoryCarListPageLogic.checkPresenceGrayBtnAtExpectedProduct(productID);
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product99"));
        productPageLogicATD.checkPresenceGrayBtnOnPage();
        openPage(dataBasePKW.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product15"));
        productPageLogicPKW.checkPresenceBtnAddToBasket()
                .checkAbsenceGrayBtn();
    }

    @AfterMethod
    public void close() throws SQLException {
        new ProductCard_aws(productID).openProductCardPageAndLogin()
                .removeSkinFromBlockOfDisabledProductsByGrayBtn();
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "search54"));
        searchPageLogic.checkAbsenceGrayBtnAtExpectedProduct(productID);
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "category_car_list72"));
        categoryCarListPageLogic.checkAbsenceGrayBtnAtExpectedProduct(productID);
        openPage(dataBaseATD.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product99"));
        productPageLogicATD.checkAbsenceGrayBtnOnPage();
        openPage(dataBasePKW.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product15"));
        productPageLogicPKW.checkPresenceBtnAddToBasket()
                .checkAbsenceGrayBtn();
        closeWebDriver();
    }
}
