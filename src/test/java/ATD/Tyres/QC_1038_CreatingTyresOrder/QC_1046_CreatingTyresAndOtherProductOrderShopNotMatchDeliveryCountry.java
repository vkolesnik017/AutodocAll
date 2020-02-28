package ATD.Tyres.QC_1038_CreatingTyresOrder;


import ATD.TyresListing_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.open;

public class QC_1046_CreatingTyresAndOtherProductOrderShopNotMatchDeliveryCountry {
    private String emailDE = "qc_1046_autotestDE@mailinator.com";
    private String passwordDE = "password";

    private TyresListing_page_Logic tyresListingPageLogic = new TyresListing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks order with tyres where shop match delivery country")
    public void testTyresOrderShopMatchDeliveryCountry() {
        openPage("https://www.autodoc.de/search?keyword=Bremsscheiben&maker_id=121&model_id=1994&car_id=8799");
        tyresListingPageLogic.addFirstProductToCart();
        open("https://autodoc.de/reifen/search?Width=120&CrossSections=70&R_ZR=r&Size=17&type=motorrad");
        String tyreId = tyresListingPageLogic.getTyreId();
        System.out.println(tyreId);
        tyresListingPageLogic.addFirstProductAndGoToCart()
                            .nextButtonClick()
                            .signIn(emailDE, passwordDE)
                            .nextBtnClick()
                            .chooseVorkasse()
                            .nextBtnClick()
                            .checkRemovingTyresFromAlldataWithOtherProducts(tyreId);
    }
}
