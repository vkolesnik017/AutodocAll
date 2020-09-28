package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;

public class LKW_Search_page_Logic extends LKW_Search_page{

    @Step("checking of visibility of TecDoc Listing block .LKW_Search_page")
    public LKW_Search_page_Logic visibilityOfTecDocListingBlock() {
        listOfProductInTecDocListingBlock().shouldBe(visible);
        return this;
    }

    @Step("get id of Dangerous product .LKW_Search_page")
    public String getIdOfDangerousProduct(int positionOfProduct) {
        return btnAddDangerousProductToWishList().get(positionOfProduct).getAttribute("data-product-id");
    }

    @Step("get signal word from first dangerous product Listing View. LKW_Search_page")
    public String getSignalWordFromFirstDangerousProductListingView(int positionOfProduct) {
        return signalWordOfDangerousProductListingView().get(positionOfProduct).getText();
    }

    @Step("get attribute of Warning icon in pop-Up .LKW_Search_page")
    public List<String> getAttributeOfWarningIconInPopUp(int positionOfProduct) {
        List<String> attribute = new ArrayList<>();
        for (int i = 0; i < attributeOfWarningIcon(positionOfProduct + 1).size(); i++) {
            attribute.add(attributeOfWarningIcon(positionOfProduct + 1).get(i).shouldBe(visible).getAttribute("style").replace("background-image: url(\"","").replace("\");",""));
        }
        return attribute;
    }

    @Step("click on dangerous label of product and compare elements. LKW_Search_page")
    public LKW_Search_page_Logic clickOnDangerousLabelAndCompareElements(int positionOfProduct, String signalWord, List<String> attributeOfWarningIcon) {
        btnMoreOfDangerousProducts().get(positionOfProduct).shouldBe(visible).click();
        blackBackground().shouldHave(attribute("style", "display: block;"));
        warningPopUp().shouldBe(visible).shouldHave(attribute("style", "display: block;"));
        titleOfDangerousPopUp().shouldBe(visible).shouldHave(exactText(signalWord));
        infoTextOfDangerousPopUp().shouldNotBe(empty);
        List<String> attributeOfDangerousIcon = dangerousIconInWarningPopUp().stream().map(n -> n.getAttribute("style").replace("background-image: url(\"", "").replace("\");", "")).collect(Collectors.toList());
        Assert.assertEquals(attributeOfDangerousIcon, attributeOfWarningIcon);
        return this;
    }
}
