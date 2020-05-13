package ATD;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static ATD.SetUp.setUpBrowser;


public class ForTest {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "search9");
    }

    @Test(dataProvider = "route")
    public void testRetryVisibility(String route) {
        System.out.println("HI");
    }
}



