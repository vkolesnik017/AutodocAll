package AWS;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.awt.*;

import static ATD.CommonMethods.uploadFileViaWindowsPopUp;
import static com.codeborne.selenide.Condition.*;
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

    private SelenideElement preloader() {
        return $x("//div[@class='preloader-upload']");
    }

    private SelenideElement alertMessage() {
        return $x("//div[@class='sticky border-top-right st-error']");
    }

    private SelenideElement removeMessage() {
        return $x("//div[@class='btn btn-danger btn-mini btn-remove']");
    }

    private SelenideElement textOfAlertMessage() {
        return $x("//div[@class='sticky-note']");
    }

    private SelenideElement btnSearch() {
        return $(byId("form_submit"));
    }

    private SelenideElement artIdField(String idOfProduct) {
        return $x("//td[text()='" + idOfProduct + "']");
    }

    private SelenideElement btnForwardOfPaginator() {
        return $x("//ul[@class='pagination']//a[text()='»']");
    }

    private SelenideElement doNotDisplayCheckbox(String artId) {
        return $x("//td[text()='" + artId + "']/ancestor::tr/td[8]/input");
    }

    private SelenideElement btnRemoveRecord(String artId) {
        return $x("//td[text()='" + artId + "']/ancestor::tr//td[10]/button");
    }

    private SelenideElement uploadFileField(String artId) {
        return $x("//td[text()='" + artId + "']/ancestor::tr/td[7]");
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
        skinFilterFirstLine().shouldBe(visible).selectOptionByValue(skin);
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
    public ProductsPdf_aws uploadFile(String path) throws AWTException {
        clickOnUploadButton();
        uploadFileViaWindowsPopUp(path);
        return this;
    }

    @Step("click on Upload button. ProductsPdf_aws")
    public ProductsPdf_aws clickOnUploadButton() {
        btnUpload().click();
        return this;
    }

    @Step("presence Preloader. ProductsPdf_aws")
    public ProductsPdf_aws presencePreloader() {
        preloader().shouldHave(attribute("style", "display: block;"));
        preloader().shouldHave(attribute("style", "display: none;"));
        return this;
    }

    @Step("presence Alert Message. ProductsPdf_aws")
    public ProductsPdf_aws presenceAlertMessage(String alertText) {
        alertMessage().shouldBe(visible);
        textOfAlertMessage().shouldBe(visible).shouldHave(text(alertText));
        alertMessage().shouldNotBe(visible);
        return this;
    }

    @Step("display of remove message. ProductsPdf_aws")
    public ProductsPdf_aws displayOfRemoveMessage(String alertText) {
        textOfAlertMessage().shouldBe(visible).shouldHave(text(alertText));
        textOfAlertMessage().shouldNotBe(visible);
        return this;
    }

    @Step("click on Search button. ProductsPdf_aws")
    public ProductsPdf_aws clickOnSearch() {
        btnSearch().click();
        return this;
    }

    @Step("click on Do Not Display checkbox. ProductsPdf_aws")
    public ProductsPdf_aws clickOnDoNotDisplayCheckBox(String idOfProduct) {
        while (!artIdField(idOfProduct).isDisplayed()) {
            btnForwardOfPaginator().click();
        }
        doNotDisplayCheckbox(idOfProduct).click();
        return this;
    }

    @Step("get Title Of Upload File. ProductsPdf_aws")
    public String getTitleOfUploadFile(String idOfProduct) {
        return uploadFileField(idOfProduct).getText();
    }


    @Step("remove uploaded file. ProductsPdf_aws")
    public ProductsPdf_aws removeFile(String idOfProduct) {
        while (!artIdField(idOfProduct).isDisplayed()) {
            btnForwardOfPaginator().click();
        }
        btnRemoveRecord(idOfProduct).click();
        switchTo().alert().accept();
        return this;
    }
}
