package ATD.Catalog.QC_2626_TecdocCatalog_SidebarCatalog_TopCategories;


import ATD.Categories_page_Logic;
import Common.DataBase;
import Common.SetUp;
import AWS.CatalogCategories_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ATD.CommonMethods.comparingParentCategoriesWithAws;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_609 {

    private Categories_page_Logic categoriesPageLogic = new Categories_page_Logic();

    private ArrayList<String> parentCategoriesTecdocCatalogWithoutCar;
    private ArrayList<String> parentCategoriesCatalogFAQwithoutCar;
    private List<String> parentCategoriesAWS;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "categories");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Display Overcategories FAQ and Catalog Route Without Car")
    public void testDisplayOvercategoriesFAQandCatalogRouteWithoutCar(String route) throws Exception {
        openPage(route);
        parentCategoriesTecdocCatalogWithoutCar = categoriesPageLogic.getAllParentCategoriesFromTecdocCatalog();

        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "faqHash"));
        parentCategoriesCatalogFAQwithoutCar = categoriesPageLogic.getAllParentCategoriesFromTecdocCatalog();

        parentCategoriesAWS = new CatalogCategories_aws("prod").getActiveParentCategories();
        comparingParentCategoriesWithAws(parentCategoriesAWS, parentCategoriesTecdocCatalogWithoutCar);
        comparingParentCategoriesWithAws(parentCategoriesAWS, parentCategoriesCatalogFAQwithoutCar);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
