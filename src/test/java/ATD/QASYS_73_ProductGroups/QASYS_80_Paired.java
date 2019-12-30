package ATD.QASYS_73_ProductGroups;

import ATD.DataBase;
import ATD.Search_page;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.getShopFromRoute;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_80_Paired {


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }


    @Flaky
    @Owner(value = "alex_qa")
    @Test(dataProvider = "route", enabled = true)
    @Description(value = "Test check making order with paired product")
    public void checkingOrderWithPaired(String route) throws SQLException {
        String shop = getShopFromRoute(route);
        open(route + "/" + new DataBase().getRouteByRouteName(shop, "search8"));
        String testMail = "atdautotest@mailinator.com";
        new Search_page().counterIncrease(2).counterDecrease(4).detailsClick()
                .counterIncrease(2).counterDecrease(4).counterIncrease(2);
    }
}
