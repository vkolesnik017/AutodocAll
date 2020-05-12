package ATD.Selectors.QC_663_HorizontalCarSelector;


import ATD.Main_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class QC_682_DisplayChooseModelTooltipFirstTimeEnterSession {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "categories_maker");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Display Choose Model Tooltip First Time Enter Session")
    public void testDisplayChooseModelTooltipFirstTimeEnterSession(String route) {
        open(route);
        new Main_page_Logic().checkModelChooseTooltipInSelector();
    }

    @AfterMethod
    public void tearDown() {
        close();
    }
}
