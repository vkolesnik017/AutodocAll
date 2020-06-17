package ATD;


import io.qameta.allure.Step;
import org.testng.Assert;
import static com.codeborne.selenide.Condition.*;

public class Listing_chemicals_Page_Logic extends Listing_chemicals_Page {

    @Step("Get name title Category. Listing_chemicals_Page")
    public String getNameTitleCategory() {
        return titleNameCategory().getText();
    }

    @Step("Checking presence name title. Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic checkingPresenceNameTitle() {
        Assert.assertFalse(titleNameCategory().text().isEmpty());
        return this;
    }

    @Step("Checking presence brands block. Listing_chemicals_Page")
    public Listing_chemicals_Page_Logic checkingPresenceBrandsBlock() {
        blockBrands().shouldBe(visible);
        return this;
    }
}
