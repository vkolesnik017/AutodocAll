package ATD.ProductPage.QC_2741_ProductPage_CarRoute;


import ATD.CommonMethods;
import Common.DataBase;
import ATD.Product_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class QC_963 {

    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private CommonMethods commonMethods = new CommonMethods();
    private DataBase dataBase = new DataBase("ATD");

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks presence of elements on product page")
    public void testPresenceOfElements() throws SQLException {
        //steps in QC: 1
        openPage(dataBase.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product13"));
        //2
        product_page_logic.carSelectorBlock().shouldBe(visible);
        //3
        product_page_logic.minimizedCharactericticBlock().shouldBe(visible);
        product_page_logic.uncoverCharactericticBtn().click();
        product_page_logic.maximizedCharacteristicBlock().shouldBe(visible);
        product_page_logic.coverCharacteristicBtn().click();
        product_page_logic.minimizedCharactericticBlock().shouldBe(visible);
        //5
        product_page_logic.breadcrumbsBlock().shouldBe(visible);
        //6
        product_page_logic.breadcrumbsBlock().shouldBe(visible);
        product_page_logic.breadcrumbsCategoryDropdownButton().shouldBe(visible).click();
        product_page_logic.breadcrumbsFirstCategoryFromDropdown().shouldBe(visible).click();
        checkingContainsUrl("autoteile/bremsbelag-10130");
        open(dataBase.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product13"));
        //7
        product_page_logic.breadcrumbsBlock().shouldBe(visible);
        product_page_logic.breadcrumbsCategoryButton().shouldBe(visible).click();
        checkingContainsUrl("autoteile/bremsscheibe-10132");
        open(dataBase.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product13"));
        //8
        product_page_logic.breadcrumbsBlock().shouldBe(visible);
        product_page_logic.breadcrumbsParentCategoryDropdownButton().shouldBe(visible).click();
        product_page_logic.breadcrumbsFirstParentCategoryFromDropdown().shouldBe(visible).click();
        checkingContainsUrl("autoteile/ole-flussigkeiten");
        open(dataBase.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product13"));
        //9
        product_page_logic.breadcrumbsBlock().shouldBe(visible);
        product_page_logic.breadcrumbsParentCategotyButton().shouldBe(visible).click();
        checkingContainsUrl("autoteile/bremsanlage");
        open(dataBase.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product13"));
        //10
        product_page_logic.breadcrumbsBlock().shouldBe(visible);
        product_page_logic.breadcrumbsCatalogButton().click();
        checkingContainsUrl("autoteile");
        open(dataBase.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product13"));
        //4
        product_page_logic.featuresBlock().shouldBe(visible);
        product_page_logic.jahreIcon().shouldBe(visible).hover();
        Assert.assertEquals(product_page_logic.jahreIcon().getCssValue("color"), "rgba(51, 100, 219, 1)");
        product_page_logic.tageIcon().shouldBe(visible).hover();
        Assert.assertEquals(product_page_logic.tageIcon().getCssValue("color"), "rgba(51, 100, 219, 1)");
        product_page_logic.tagePopup().shouldBe(visible);
        product_page_logic.safeOrderIcon().shouldBe(visible).hover();
        Assert.assertEquals(product_page_logic.safeOrderIconColor().getCssValue("color"), "rgba(51, 100, 219, 1)");
        product_page_logic.safeOrderPopup().shouldBe(visible);
        //11
        product_page_logic.productImageBlock().scrollTo().shouldBe(visible);
        //12
        product_page_logic.imageInSlider().shouldBe(visible).click();
        product_page_logic.productImageBlock().shouldBe(visible).click();
        product_page_logic.fullProductImage().waitUntil(visible, 5000);
        product_page_logic.closeFullImagePreview().click();
        //13
        product_page_logic.productImageSlider().shouldBe(visible);
        //14
        product_page_logic.priceBlock().shouldBe(visible);
        //15
        product_page_logic.addProductToCart();
        product_page_logic.closePopupOtherCategoryIfYes();
        //16
        product_page_logic.counterIncreaseForPaired("2");
        product_page_logic.counterDecreaseForPaired("4");
        //17
        product_page_logic.brandButtonOnImage().click();
        commonMethods.checkingUrlAndCloseTab(dataBase.getFullRouteByRouteAndSubroute("prod", "DE", "main", "category_name_brand11"));
        //18
        product_page_logic.versandkostenButton().click();
        switchTo().window(1);
        checkingContainsUrl("services/versand");
        closeWindow();
        switchTo().window(0);
        open(dataBase.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product13"));
        //19
        product_page_logic.raitingBlock().click();
        //20
        product_page_logic.feedbackBlock().shouldBe(visible);
        //21
        product_page_logic.similarPropertiesBlock().scrollTo().shouldBe(visible);
        product_page_logic.linkInSimilarPropertiesBlock().scrollIntoView(false).waitUntil(visible, 4000);
        executeJavaScript("arguments[0].click();", product_page_logic.linkInSimilarPropertiesBlock());
//        product_page_logic.linkInSimilarPropertiesBlock().click();
        product_page_logic.similarPropertiesBlock().waitUntil(visible, 3000);
        checkingContainsUrl("bosch/1165812");
        open(dataBase.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product13"));
        //23
        product_page_logic.paymentMethodsBlock().shouldBe(visible);
        //24
        product_page_logic.deliveryServicesBlock().shouldBe(visible);
        //25
        product_page_logic.videoBlock().scrollTo().shouldBe(visible);
        //26
        product_page_logic.pdfTutorialsBlock().scrollTo().shouldBe(visible);
        product_page_logic.checkPdfLinksForDownload();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
