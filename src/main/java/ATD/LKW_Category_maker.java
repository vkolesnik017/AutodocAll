package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

class LKW_Category_maker {

    SelenideElement childCategoryBlockSideBar() {return $x("//div[@class='block categories blue topSubCats']");}
}
