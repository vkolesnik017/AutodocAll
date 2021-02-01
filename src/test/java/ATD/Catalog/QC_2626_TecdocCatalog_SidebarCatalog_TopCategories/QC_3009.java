package ATD.Catalog.QC_2626_TecdocCatalog_SidebarCatalog_TopCategories;

import ATD.Main_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3009 {
    Main_page_Logic mainPage = new Main_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShopsWithMainRoute("prod", "DE", "main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check Prioritization Of Top Parent Categories")
    public void testCheckPrioritizationOfTopParentCategories(String route) {
        openPage(route);
        List<String> parentCategories = mainPage.presenceOfTyresCategory().getTopParentCategories();
        mainPage.chooseBrandModelTypeInSelector("GAZ", "36959", "121901")
                .goToCatalog()
                .presenceOfTecDocCatalog();
        mainPage.clickOnMainLogo()
                .presenceOfTyresCategory();
        List<String> parentCategoriesWithCar = mainPage.presenceOfTyresCategory().getTopParentCategories();
        Assert.assertEquals(parentCategoriesWithCar, parentCategories);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
