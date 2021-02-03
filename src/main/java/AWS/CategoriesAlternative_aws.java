package AWS;

import Common.DataBase;
import Common.SetUp;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.sql.SQLException;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class CategoriesAlternative_aws {
    private String awsEnv;
    private String alternativeCategoriesAws;
    private String alternativeCategoriesProductAws;

    private SelenideElement alternativeTitle(String id, String skin, String language) {
        return $x("//table//td[contains(text(),'" + id + "')][1]/following-sibling::td[starts-with(text(),'" + skin + "')]/following-sibling::td[contains(text(),'" + language + "')]/preceding-sibling::td[2]");
    }

    private SelenideElement genericIdField() {
        return $(byId("form_id"));
    }


    public CategoriesAlternative_aws() throws SQLException {
        this.awsEnv = "https://aws.";
        init(awsEnv);
    }

    public CategoriesAlternative_aws(String envFromTest) throws SQLException {
        SetUp setUp = new SetUp();
        this.awsEnv = setUp.getEnvForAws(envFromTest);
        init(awsEnv);
    }

    private void init(String awsEnv) throws SQLException {
        this.alternativeCategoriesAws = awsEnv + "autodoc.de/" + new DataBase("ATD").getRouteByRouteName("DE", "categoriesAlternativeAws");
        this.alternativeCategoriesProductAws = awsEnv + "autodoc.de/" + new DataBase("ATD").getRouteByRouteName("DE", "categoriesAlternativeAws2");
    }

    @Step("open alternative categories page in aws. CategoriesAlternative_aws")
    public CategoriesAlternative_aws openAlternativeCategoriesInAwsWithLogin() {
        open(alternativeCategoriesAws);
        new Login_aws().loginInAws();
        return this;
    }

    @Step("open alternative categories page in aws. CategoriesAlternative_aws")
    public CategoriesAlternative_aws openAlternativeCategoriesProductInAwsWithLogin() {
        open(alternativeCategoriesProductAws);
        new Login_aws().loginInAws();
        return this;
    }

    @Step("presence alternative title for generic. CategoriesAlternative_aws")
    public CategoriesAlternative_aws presenceAlternativeTitleForGeneric(String id, String skin, String language) {
        alternativeTitle(id, skin, language).shouldBe(visible).shouldNotBe(empty);
        return this;
    }

    @Step("get alternative title. CategoriesAlternative_aws")
    public String getAlternativeTitle(String id, String skin, String language) {
        String title = alternativeTitle(id, skin, language).shouldBe(visible).getText();
        return title;
    }

    @Step("set generic ID {id}. CategoriesAlternative_aws")
    public CategoriesAlternative_aws setGenericId(String id) {
        genericIdField().shouldBe(visible).setValue(id).pressEnter();
        return this;
    }
}
