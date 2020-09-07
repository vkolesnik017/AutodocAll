package PKW.StaticPage;

import PKW.Main_page_Logic;
import PKW.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static PKW.CommonMethods.openPage;
import static PKW.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_249_StaticPage_Versand {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Owner(value = "LavrynenkoOlha")
    @Flaky
    @Description(value = "Test checks elements on the Versand page")
    public void testStaticPage_Versand(String route) throws SQLException {
        openPage(route);

        new Main_page_Logic().clickFooterVersandLink()
                .checkingBoxBlock()
                .checkingDeliveryCountryBlock()
                .checkingBlockWithTermsOfTheDelivery() //4
                .checkingFaqBlock() //8
                .questionsAndAnswersFaq()
                .checkingTextInTheQuestionsBlock()
                .spergutVersandBlock()
                .spergutVersandTableFirstTab()
                .tableCountryPriceWithPriceTabOne()
                .spergutVersandTableSecondTab()
                .checkingFlagTabTwo()
                .checkingTextAndPriceTabTwo()
                .checkingDownloadButtons()
                .checkingBackButton();


       /*
        new Main_page_Logic().clickFooterVersandLink()
                .checkingBoxBlock()
                .checkingDeliveryCountryBlock()
                .checkingCountAndTextNearTheCountriesForTheFirstCountryBlock()
                .checkingTheFlagInTheFirstCountryBlock()
                .checkingTextAndPriceForFirstThreeCountries()
                .checkingFlagsForFirstThreeCountry()
                .checkingTextNearTheThreeIcons()
                .checkingBlockWithTermsOfTheDelivery()
                .checkingFaqBlock()
                .questionsAndAnswersFaq()
                .checkingTextInTheQuestionsBlock()
                .spergutVersandBlock()
                .spergutVersandTableFirstTab()
                .tableCountryPriceWithPriceTabOne()
                .spergutVersandTableSecondTab()
                .checkingFlagTabTwo()
                .checkingTextAndPriceTabTwo()
                .checkingDownloadButtons()
                .checkingBackButton();*/
    }
    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}

