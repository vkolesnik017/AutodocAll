package ATD.FiltersSorting.QC_195_FiltersSorting_outputSorting;

import ATD.Category_car_list_page_Logic;
import ATD.Moto_Category_car_list_page_Logic;
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

public class QC_3400 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_car_list17");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "kolesnik")
    @Description(value = "test checks Sorting on the current issue by default, provided that there is more than one generic product in the issue")
    public void testCheckSortingInTecDocListing(String route) {
        openPage(route);
        List<String> expectedGenerics = new Moto_Category_car_list_page_Logic()
                .displayGenericFilterBlock()
                .getTitleOfGenerics();
        new Category_car_list_page_Logic()
                .checkTecDocListing(expectedGenerics);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}

