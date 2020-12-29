package ATD.SEO.QC_2549_InformationBlockOfCategoryNameRoute;

import ATD.Category_name_page_Logic;
import Common.DataBase;
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

public class QC_2664_CharacteristicBlock {
    private Category_name_page_Logic categoryNamePage = new Category_name_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_name12");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks Characteristic block")
    public void testChecksCharacteristicBlock(String route) throws SQLException {
        openPage(route);
        categoryNamePage.checkDisplayCharacteristicBlock().checkCountOfCharacteristicBLock()
                .checkOfDuplicateInCharacteristicBlock();
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod","DE","main","category_name13"));
        categoryNamePage.checkPositionOfEmptyValuesInCharacteristicBlock();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
