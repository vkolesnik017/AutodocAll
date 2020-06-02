package MOTO.QC_745_ChildCategoriesBlock;

import ATD.Moto_Parent_Category_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_750_ListOfAllChildCategories {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_parent_category,moto_parent_category_maker2");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks list of all child categories")
    public void testChecksListOfAllChildCategories(String route)  {
        openPage(route);

        new Moto_Parent_Category_page_Logic()
                .checkOfChildCategoriesList();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }

}
