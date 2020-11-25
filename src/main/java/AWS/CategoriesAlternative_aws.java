package AWS;

import Common.DataBase;
import Common.SetUp;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.sql.SQLException;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class CategoriesAlternative_aws {
    private String awsEnv;
    private String alternativeCategoriesAws;

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
    }

    private SelenideElement alternativeTitle(String id, String skin, String language) {
        return $x("//table//td[contains(text(),'" + id + "')][1]/following-sibling::td[starts-with(text(),'" + skin + "')]/following-sibling::td[contains(text(),'" + language + "')]/preceding-sibling::td[2]");
    }

    @Step("open alternative categories page in aws")
    public CategoriesAlternative_aws openAlternativeCategoriesInAwsWithLogin() {
        open(alternativeCategoriesAws);
        new Login_aws().loginInAws();
        return this;
    }

    @Step("presence alternative title for generic")
    public CategoriesAlternative_aws presenceAlternativeTitleForGeneric(String id, String skin, String language) {
        alternativeTitle(id, skin, language).shouldBe(visible).shouldNotBe(empty);
        return this;
    }

    @Step("get alternative title")
    public String getAlternativeTitle(String id, String skin, String language) {
        String title = alternativeTitle(id, skin, language).shouldBe(visible).getText();
        return title;
    }
}
