package ATD.Catalog.QC_2626_TecdocCatalog_SidebarCatalog_TopCategories;


import ATD.Categories_page_Logic;
import ATD.FaqHash_page_Logic;
import ATD.Maker_car_list_page_Logic;
import AWS.CatalogCategories_aws;
import Common.DataBase;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

import static ATD.CommonMethods.*;
import static Common.CommonMethods.checkingContainsUrl;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_605 {

    private List<String> parentCategoriesCarList;
    private List<String> parentCategoriesFaq;
    private List<String> parentCategoriesTecDoc;
    private List<String> parentCategoriesAWS;
    DataBase db = new DataBase("ATD");

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "maker_car_list15");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Display Overcategories On Catalog Routes With Car")
    public void testDisplayOvercategoriesOnCatalogRoutesWithCar(String route) throws Exception {
        openPage(route);
        parentCategoriesCarList = new Maker_car_list_page_Logic().presenceOfTecDocCatalog().getParentCategoriesFromCatalog();

        openPage(db.getFullRouteByRouteAndSubroute("subprod", "DE", "main", "categories"));
        checkingContainsUrl(db.getRouteByRouteName("DE", "categories"));

        parentCategoriesTecDoc = new Categories_page_Logic().presenceOfTecDocCatalog().getParentCategories();

        openPage(db.getFullRouteByRouteAndSubroute("subprod", "DE", "main", "faqHash"));
        checkingContainsUrl(db.getRouteByRouteName("DE", "faqHash"));

        parentCategoriesFaq = new FaqHash_page_Logic().presenceOfTecDocCatalog().getParentCategories();
        parentCategoriesAWS = new CatalogCategories_aws("prod").getActiveParentCategories();

        comparingParentCategoriesWithAws(parentCategoriesAWS, parentCategoriesCarList);
        comparingParentCategoriesWithAws(parentCategoriesAWS, parentCategoriesFaq);
        comparingParentCategoriesWithAws(parentCategoriesAWS, parentCategoriesTecDoc);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
