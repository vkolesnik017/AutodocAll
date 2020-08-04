package ATD;

import AWS.CatalogCategories_aws;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class ForTest extends SetUp {


    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    public void test(String route) throws SQLException {
        new CatalogCategories_aws("test").getAllParentCategoriesNameFromAWS();
    }

}


