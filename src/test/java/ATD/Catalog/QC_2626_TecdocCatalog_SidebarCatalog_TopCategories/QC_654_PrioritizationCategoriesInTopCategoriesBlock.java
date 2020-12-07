package ATD.Catalog.QC_2626_TecdocCatalog_SidebarCatalog_TopCategories;

import ATD.Maker_car_list_page_Logic;
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

public class QC_654_PrioritizationCategoriesInTopCategoriesBlock {

    private Maker_car_list_page_Logic makerCarListPageLogic = new Maker_car_list_page_Logic();
    private String dataFile = "C:/Autotests/files/data/QC_654_data.xls";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "maker_car_list3");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test checking prioritization categories in top categories block")
    public void testCheckingPrioritizationCategoriesInTopCategoriesBlock(String route) {
        openPage(route);
        makerCarListPageLogic.checkPresenceTopCategoriesBlock();
        ArrayList<String> overCategories = makerCarListPageLogic.getCategoriesFromTopCategories(makerCarListPageLogic.overCategoriesInTopCategoriesBlock());
        ArrayList<String> categories = makerCarListPageLogic.getCategoriesFromTopCategories(makerCarListPageLogic.categoriesInTopCategoriesBlock());
        makerCarListPageLogic.checkDisplayCategoriesBetweenTopCategoriesAndExcel(overCategories, categories, dataFile);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
