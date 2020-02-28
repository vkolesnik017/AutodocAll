package ATD.Search.QC_548_SearchTooltips;

import ATD.Main_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class QC_552_ArticleNumberTooltipsInSearch {

    private String articleNumber = "40594";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route")
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Evlentiev")
    @Description(value = "At the top of the tooltips, at least three values are displayed that contain 40594")
    public void testArticleNumberTooltipsInSearch(String route) {
        open(route);
        new Main_page_Logic().inputTextInSearchBar(articleNumber)
                .tooltipsToSearch().shouldHave(sizeNotEqual(0)).filter(text(articleNumber + ")")).shouldHave(sizeGreaterThanOrEqual(3));
    }

    @AfterMethod
    private void tearDown() {
        close();
    }

}
