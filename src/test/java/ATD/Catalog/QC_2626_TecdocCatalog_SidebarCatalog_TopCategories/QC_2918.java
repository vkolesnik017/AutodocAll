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
import java.util.Arrays;
import java.util.List;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2918 {
    private String dataFile = "C:/Autotests/files/data/QC_2918.xls";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "maker_car_list3");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checking prioritizing child by sales on catalog page with top car")
    public void testPrioritizingChildBySalesOnCatalogPageWithTopCar(String route) {
        openPage(route);
        List<String> notUsedCategories = Arrays.asList("Reifen", "Autopflege", "Werkzeuge & Werkstattausrüstung","Autozubehör");
        new Maker_car_list_page_Logic().presenceOfTecDocCatalog().compareDisplayOrderOfChildCategoriesWithFile(dataFile, notUsedCategories);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
