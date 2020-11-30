package ATD.Catalog.QC_1447_AbsenceOf4xx3xxResponsesInCatalogCategories;


import ATD.*;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1448_AbsenceOf4xx3xxResponsesInTecdocCatalogCategories {

    private Main_page_Logic mainPageLogic = new Main_page_Logic();
    private Categories_page_Logic categoriesPageLogic = new Categories_page_Logic();
    private Supplier_page_Logic supplierPageLogic = new Supplier_page_Logic();
    private Category_car_list_page_Logic categoryCarListPageLogic = new Category_car_list_page_Logic();
    private Search_page_Logic searchPageLogic = new Search_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }


    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShopsWithMainRoute("prod", "DE", "main");
    }

    @DataProvider(name = "routesCategories", parallel = true)
    Object[] dataProviderCategories() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "categories");
    }

    @DataProvider(name = "routesSupplier2", parallel = true)
    Object[] dataProviderSupplier2() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "supplier2");
    }

    @DataProvider(name = "routesCategory_car_list55", parallel = true)
    Object[] dataProviderСategory_car_list55() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list55");
    }

    @DataProvider(name = "routesSearch37", parallel = true)
    Object[] dataProviderSearch37() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "search37");
    }


    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test checks Absence Of 4xx 3xx Responses In Tecdoc Catalog Categories")
    public void testAbsenceOf4xx3xxResponsesInTecdocCatalogCategories(String route) throws IOException {
        openPage(route);
        ArrayList<String> categories = mainPageLogic.getHrefOrUrlCategoriesThenWriteToList(mainPageLogic.categoriesFromCatalog());
        ArrayList<String> overCategories = mainPageLogic.getHrefOrUrlCategoriesThenWriteToList(mainPageLogic.overCategoriesFromCatalog());
        mainPageLogic.checkCategoriesForServerResponses200(categories);
        mainPageLogic.checkCategoriesForServerResponses200(overCategories);
    }

    @Test(dataProvider = "routesCategories")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test checks Absence Of 4xx 3xx Responses In Tecdoc Catalog Categories")
    public void test2_AbsenceOf4xx3xxResponsesInTecdocCatalogCategories(String route) throws IOException {
        openPage(route);
        ArrayList<String> categories = categoriesPageLogic.getHrefOrUrlCategoriesThenWriteToList(categoriesPageLogic.tecdocCategories());
        ArrayList<String> overCategories = categoriesPageLogic.getHrefOrUrlCategoriesThenWriteToList(categoriesPageLogic.tecdocOverCategories());
        categoriesPageLogic.checkCategoriesForServerResponses200(categories);
        categoriesPageLogic.checkCategoriesForServerResponses200(overCategories);
    }

    @Test(dataProvider = "routesSupplier2")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test checks Absence Of 4xx 3xx Responses In Tecdoc Catalog Categories")
    public void test3_AbsenceOf4xx3xxResponsesInTecdocCatalogCategories(String route) throws IOException {
        openPage(route);
        ArrayList<String> categories = supplierPageLogic.getHrefOrUrlCategoriesThenWriteToList(supplierPageLogic.categoriesCatalog());
        supplierPageLogic.checkCategoriesForServerResponses200(categories);
    }

    @Test(dataProvider = "routesCategory_car_list55")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test checks Absence Of 4xx 3xx Responses In Tecdoc Catalog Categories")
    public void test4_AbsenceOf4xx3xxResponsesInTecdocCatalogCategories(String route) throws IOException {
        openPage(route);
        categoryCarListPageLogic.clickBtnTeilecatalogInSidebar();
        ArrayList<String> categories = categoryCarListPageLogic.getHrefOrUrlCategoriesThenWriteToList(categoryCarListPageLogic.parentFromTeilecatalogInSidebarHref());
        categoryCarListPageLogic.checkCategoriesForServerResponses200(categories);
    }

    @Test(dataProvider = "routesSearch37")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test checks Absence Of 4xx 3xx Responses In Tecdoc Catalog Categories")
    public void test5_AbsenceOf4xx3xxResponsesInTecdocCatalogCategories(String route) throws IOException {
        openPage(route);
        ArrayList<String> categories = searchPageLogic.getHrefOrUrlCategoriesThenWriteToList(searchPageLogic.categoriesFromSideBar());
        searchPageLogic.checkCategoriesForServerResponses200(categories);
    }


    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
