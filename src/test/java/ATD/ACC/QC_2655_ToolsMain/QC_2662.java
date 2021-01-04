package ATD.ACC.QC_2655_ToolsMain;

import ATD.Index_instruments_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2662 {

   private Index_instruments_page_Logic indexInstrumentsPageLogic = new Index_instruments_page_Logic();


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "index_instruments");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks Lack 4xx And 3xx Server Responses When Moving To Category.")
    public void testLack4xxAnd3xxServerResponsesWhenMovingToCategory(String route) throws IOException {
        openPage(route);
        ArrayList<String> allCategories = indexInstrumentsPageLogic.getUrlCategoriesAndSeparateCategoriesThenWriteToList(indexInstrumentsPageLogic.categoriesFromLogicalUnion(),
                                                                                                                          indexInstrumentsPageLogic.nameSeparateCategories());
        indexInstrumentsPageLogic.checkCategoriesForServerResponses200(allCategories);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
