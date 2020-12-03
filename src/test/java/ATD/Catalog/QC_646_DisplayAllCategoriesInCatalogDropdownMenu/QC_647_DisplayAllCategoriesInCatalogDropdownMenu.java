package ATD.Catalog.QC_646_DisplayAllCategoriesInCatalogDropdownMenu;


import ATD.Categories_page_Logic;
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

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_647_DisplayAllCategoriesInCatalogDropdownMenu {

    private Categories_page_Logic categoriesPageLogic = new Categories_page_Logic();
    private CatalogCategories_aws catalogCategoriesAws = new CatalogCategories_aws("prod");

    private ArrayList<String> childCategoriesInDropdownMenu;
    private ArrayList<String> parentCategoriesInDropdownMenu;
    private ArrayList<String> childCategoriesInAWS;
    private ArrayList<String> parentCategoriesInAWS;

    public QC_647_DisplayAllCategoriesInCatalogDropdownMenu() throws SQLException {
    }

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "categories");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Display All Categories In Catalog Dropdown Menu")
    public void testDisplayAllCategoriesInCatalogDropdownMenu(String route) throws Exception {
        openPage(route);
        childCategoriesInDropdownMenu = categoriesPageLogic.getAllChildCategoriesFromCatalogDropdownMenu();
        parentCategoriesInDropdownMenu = categoriesPageLogic.getAllParentCategoriesFromCatalogDropdownMenu();

        childCategoriesInAWS = catalogCategoriesAws.getAllChildCategoriesFromAWS();
        parentCategoriesInAWS = catalogCategoriesAws.getAllParentCategoriesFromAWS();

        categoriesPageLogic.compareCategoriesFromCatalogAndAWS(childCategoriesInDropdownMenu, childCategoriesInAWS)
                .compareCategoriesFromCatalogAndAWS(parentCategoriesInDropdownMenu, parentCategoriesInAWS);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
