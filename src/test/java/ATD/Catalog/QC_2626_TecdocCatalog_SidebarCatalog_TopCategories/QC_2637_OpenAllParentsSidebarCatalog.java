package ATD.Catalog.QC_2626_TecdocCatalog_SidebarCatalog_TopCategories;

import ATD.Category_car_list_page_Logic;
import AWS.CatalogCategories_aws;
import Common.SetUp;
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

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2637_OpenAllParentsSidebarCatalog {

    private List<String> parentFromSidebar, parentFromAws, groupRating, sortedParentIdFromAwsByRating;
    private CatalogCategories_aws catalogCategoriesAws = new CatalogCategories_aws();
    private Category_car_list_page_Logic categoryCarListPageLogic = new Category_car_list_page_Logic();

    public QC_2637_OpenAllParentsSidebarCatalog() throws SQLException {
    }

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main",  "category_car_list,search2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test checking display order parens between Sidebar and AWS")
    public void testCheckingDisplayOrderParensBetweenSidebarAndAws(String route) {
        openPage(route);
        parentFromSidebar = categoryCarListPageLogic.getIdListParentsTeilecatalogInSidebar();
        parentFromAws = catalogCategoriesAws.getAllParentIdByGroupRating();
        groupRating = catalogCategoriesAws.getAllParentGroupRating();
        sortedParentIdFromAwsByRating = catalogCategoriesAws.createListAnyElementsByGroupRating(parentFromAws, groupRating);
        categoryCarListPageLogic.compareTwoListsBetweenFrontAndAws(parentFromSidebar, parentFromAws);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
