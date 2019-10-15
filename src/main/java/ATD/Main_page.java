package ATD;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static ATD.CommonMethods.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

class Main_page {
    //Header
    SelenideElement loginButton() {
        return $(By.xpath("//div[@class='header__profile logined sigin_btn']/a"));
    }

    SelenideElement searchBar() {
        return $(By.xpath("//input[@class='header-search__input']"));
    }

    Search_page searchButtonClick() {
        $(By.xpath("//form[@id='search_form']/a")).click();
        return page(Search_page.class);
    }

    Cart_page cartClick() {
        $(By.xpath("//a[@class='header-cart__link']")).click();
        return page(Cart_page.class);
    }

    SelenideElement logoutButton(){
       return  $(By.xpath("//a[@class='header__logout logout_but']"));
    }

    // Login and registration popup
    SelenideElement emailInputInLoginPopup() {
        return $(By.xpath("//input[@name='Email']"));
    }

    SelenideElement passwordInputInLoginPopup() {
        return $(By.xpath("//input[@name='Password']"));
    }

    Main_page singInButtonClickInLoginPopup() {
        $(By.xpath("//a[@class='enter submit']")).click();
        return this;
    }

    SelenideElement registrationButtonInLoginPopup() {
        return $(By.xpath("//form[@id='login_top']/p/a"));
    }

    Main_page fillRequiredFieldsForRegistration(String firstName, String secondName, String mail) {
        $(By.xpath("//input[@id='form_rVorname']")).setValue(firstName);
        $(By.xpath("//input[@id='rName']")).setValue(secondName);
        $(By.xpath("//input[@id='email']")).setValue(mail);
        $(By.xpath("//a[@class='register_step']")).click();
        return this;
    }

    Profile_page fillPasswordFieldsAndClickRegistration() {
        $(By.xpath("//input[@name='new_pass']")).setValue(password);
        $(By.xpath("//input[@name='new_pass_confirm']")).setValue(password);
        $(By.xpath("//div[@class='button register_submit fast']/a")).click();
        return page(Profile_page.class);
    }

    // Footer
    SelenideElement footerForm() {
        return $(By.xpath("//footer"));
    }

    Contact_static_page clickContact() {
        $(By.xpath("//div[@class='inform_menu']/ul/li[4]/a")).click();
        return page(Contact_static_page.class);
    }
}
