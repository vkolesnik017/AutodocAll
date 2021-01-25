package ATD.ProductPage.QC_2741_ProductPage_CarRoute;

import ATD.Product_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3098 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {

        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product53");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test checking the presence of a TAB block and anchoring when clicking on block elements")
    public void testCheckPresenceOfTabBlockAnchoringByClickOnElement(String route) {
        openPage(route);
        new Product_page_Logic().presenceTabBlock(6)
                .checkTabLinks(route)
                .presenceOfInterestingArticleLinks()
                .checkCountOfReviews();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
