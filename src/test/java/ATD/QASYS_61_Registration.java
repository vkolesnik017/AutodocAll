package ATD;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import static ATD.CommonMethods.setUpBrowser;

public class QASYS_61_Registration {
    @BeforeClass
    void setUp() {
        setUpBrowser(true, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new CommonMethods().setUpShop("prod", "AT");
    }

}
