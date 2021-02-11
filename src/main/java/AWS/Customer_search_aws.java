package AWS;


import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class Customer_search_aws {

    private final String urlPage = "https://aws.autodoc.de/customer/search";

    public Customer_search_aws openSearchInAwsWithLogin() {
        open(urlPage);
        new Login_aws().loginInAws();
        return this;
    }

    private SelenideElement emailFiled() {
        return $(By.xpath("//input[@name='Filter[email]']"));
    }

    private SelenideElement searchBtn() {
        return $(By.xpath("//div[@class='form-group']/input[@name='search']"));
    }

    private SelenideElement idClientField() {
        return $(By.xpath("//td[@class='customer-id-cell']/a[2]"));
    }

    private SelenideElement idClientFieldForEnter() {
        return $(By.xpath("//input[@name='Filter[id]']"));
    }


    public Customer_search_aws enterMailAndClickSearch(String mail){
        emailFiled().setValue(mail);
        searchBtn().click();
        return this;
    }

    @Step("Enter id user and click search. Customer_search_aws")
    public Customer_search_aws enterIdAndClickSearch(String Id){
        idClientFieldForEnter().setValue(Id);
        searchBtn().click();
        return this;
    }

    public String getClientId(){
        return idClientField().getText();
    }

    @Step("Transition on customer view page. Customer_search_aws")
    public Customer_view_aws transitionOnCustomerViewPage() {
        idClientField().shouldBe(visible).click();
        return page(Customer_view_aws.class);
    }

}
