package ATD.Catalog.QC_1447_AbsenceOf4xx3xxResponsesInCatalogCategories;


import ATD.Categories_page_Logic;
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
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1449 {

    private Categories_page_Logic categoriesPageLogic = new Categories_page_Logic();


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
    @Owner(value = "Sergey_QA")
    @Description(value = "Test checks Absence Of 4xx 3xx Responses In Dropdown Catalog Categories")
    public void testAbsenceOf4xx3xxResponsesInDropdownCatalogCategories(String route) throws Exception {
        openPage(route);
        categoriesPageLogic.clickCatalogInHeader();
        ArrayList<String> categories = categoriesPageLogic.getHrefOrUrlCategoriesThenWriteToList(categoriesPageLogic.dropdownCategories());
        categoriesPageLogic.checkCategoriesForServerResponses200(categories);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
