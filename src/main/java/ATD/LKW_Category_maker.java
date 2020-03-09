package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class LKW_Category_maker {

    protected SelenideElement childCategoryBlockSideBar() {return $x("//div[@class='block categories blue topSubCats']");}
}
