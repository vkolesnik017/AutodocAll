package AWS;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.io.File;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class ProductsPdf_aws {

    private SelenideElement loginField() {
        return $(byId("login"));
    }

    private SelenideElement skinFilterFirstLine() {
        return $(byId("form_Filter[skins]"));
    }

    private SelenideElement skinFilterSecondLine() {
        return $(byId("form_Filteradd[skins]"));
    }

    private SelenideElement artIdField() {
        return $(byId("form_Filteradd[articleId]"));
    }

    private SelenideElement typeOfFileSecondLine() {
        return $(byId("form_Filteradd[type]"));
    }

    private SelenideElement btnUpload() {
        return $x("//a[@class='btn btn-primary btn-sm btn-upload-images']");
    }

    private String url = "https://aws.autodoc.de/products/pdf";

    @Step(" open product PDF. ProductsPdf_aws")
    public ProductsPdf_aws openProductsPdf() {
        open(url);
        if (loginField().isDisplayed()) {
            new Login_aws().loginInAws();
        }
        return this;
    }

    @Step("set Skin In First Line. ProductsPdf_aws")
    public ProductsPdf_aws setSkinInFirstLine(String skin) {
        skinFilterFirstLine().shouldBe(visible).setValue(skin);
        return this;
    }

    @Step("set Skin In second Line. ProductsPdf_aws")
    public ProductsPdf_aws setSkinInSecondLine(String skin) {
        skinFilterSecondLine().shouldBe(visible).selectOptionByValue(skin);
        return this;
    }

    @Step("set article number of product. ProductsPdf_aws")
    public ProductsPdf_aws setArtIdProduct(String artId) {
        artIdField().shouldBe(visible).setValue(artId);
        return this;
    }

    @Step("set Type Of File Second Line. ProductsPdf_aws")
    public ProductsPdf_aws setTypeOfFileSecondLine(String type) {
        typeOfFileSecondLine().shouldBe(visible).selectOptionByValue(type);
        return this;
    }

    @Step("upload file from PC. ProductsPdf_aws")
    public ProductsPdf_aws uploadFile(String path) {
        clickOnUploadButton();
        File file = new File(path);
        return this;
    }

    @Step("click on Upload button. ProductsPdf_aws")
    public ProductsPdf_aws clickOnUploadButton() {
        btnUpload().click();
        return this;
    }
}
