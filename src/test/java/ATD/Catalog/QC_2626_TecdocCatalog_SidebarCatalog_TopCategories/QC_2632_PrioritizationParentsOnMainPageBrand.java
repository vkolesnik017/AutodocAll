package ATD.Catalog.QC_2626_TecdocCatalog_SidebarCatalog_TopCategories;

import ATD.Supplier_page_Logic;
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
import java.util.List;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2632_PrioritizationParentsOnMainPageBrand {

    private List<String> parentFromBrandPage, parentFromAws, groupRating, sortedParentIdFromAwsByRating;
    private Supplier_page_Logic supplierPageLogic = new Supplier_page_Logic();
    private CatalogCategories_aws catalogCategoriesAws = new CatalogCategories_aws();

    public QC_2632_PrioritizationParentsOnMainPageBrand() throws SQLException {
    }


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main",  "supplier");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test checking display parens between brand page and AWS")
    public void testCheckingDisplayParensBetweenBrandPageAndAws(String route) {
        openPage(route);
        parentFromBrandPage = supplierPageLogic.getIdListParentsFromBrandPage();
        parentFromAws = catalogCategoriesAws.getAllParentIdByGroupRating();
        groupRating = catalogCategoriesAws.getAllParentGroupRating();
        sortedParentIdFromAwsByRating = catalogCategoriesAws.createListAnyElementsByGroupRating(parentFromAws, groupRating);
        supplierPageLogic.compareTwoListsBetweenFrontAndAwsFrom(parentFromBrandPage, sortedParentIdFromAwsByRating);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
