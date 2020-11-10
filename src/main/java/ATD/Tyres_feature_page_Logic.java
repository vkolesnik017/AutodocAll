package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

import static ATD.CommonMethods.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.back;

public class Tyres_feature_page_Logic extends Tyres_feature_page {

    @Step("checkingAbsenceOfCurrentLinkInLinkungBlock. Tyres_feature_page")
    public Tyres_feature_page_Logic checkingAbsenceOfCurrentLinkInLinkingBlock(String url) {
        List<String> urlsOfLinkingBlock = btnMoreOfLinkingBlock().stream().map(n -> getAttributeFromUnVisibleElement(n, "url")).collect(Collectors.toList());
        Assert.assertFalse(urlsOfLinkingBlock.contains(url));
        return this;
    }


    @Step("presence of linking block. Tyres_feature_page")
    public Tyres_feature_page_Logic presenceOfLinkingBlock() {
        linkingBlock().shouldBe(visible);
        return this;
    }

    @Step("check swipe by click on paginator. Tyres_feature_pagee")
    public Tyres_feature_page_Logic checkSwipeByClick() {
        linkingBlock().scrollIntoView("{block: \"center\"}");
        String titleOfFirstLinkingBlock = visibleTitleOfLinkingBlocks().get(0).shouldBe(visible).getText();
        btnPaginatorOfLinkingBlock().get(1).click();
        presenceOfTitleLinkingBlock();
        presenceOfTitleLinkingBlock();
        visibleTitleOfLinkingBlocks().get(0).shouldNotHave(exactText(titleOfFirstLinkingBlock));
        presenceOfTitleLinkingBlock();
        presenceOfTitleLinkingBlock();
        btnPaginatorOfLinkingBlock().get(0).click();
        presenceOfTitleLinkingBlock();
        presenceOfTitleLinkingBlock();
        visibleTitleOfLinkingBlocks().get(0).shouldHave(exactText(titleOfFirstLinkingBlock));
        return this;
    }

    @Step("check swipe by moving block. Tyres_feature_page")
    public Tyres_feature_page_Logic checkSwipeByMoving() {
        linkingBlock().scrollIntoView("{block: \"center\"}");
        swipeByMovingToRight();
        swipeByMovingToLeft();
        return this;
    }

    @Step("swipe by moving to right in Linking Block. Tyres_feature_page")
    public Tyres_feature_page_Logic swipeByMovingToRight() {
        linkingBlock().scrollIntoView("{block: \"center\"}");
        presenceOfTitleLinkingBlock();
        String titleOfFirstLinkingBlock = visibleTitleOfLinkingBlocks().get(0).getText();
        actions().clickAndHold(visibleTitleOfLinkingBlocks().get(1)).moveToElement(visibleTitleOfLinkingBlocks().get(0)).release(visibleTitleOfLinkingBlocks().get(1)).build().perform();
        visibleTitleOfLinkingBlocks().get(0).shouldNotHave(exactText(titleOfFirstLinkingBlock));
        return this;
    }

    @Step("swipe by moving to left in Linking Block. Tyres_feature_page")
    public Tyres_feature_page_Logic swipeByMovingToLeft() {
        linkingBlock().scrollIntoView("{block: \"center\"}");
        presenceOfTitleLinkingBlock();
        headLineOfLinkingBlock().hover();
        String titleOfFirstLinkingBlock = visibleTitleOfLinkingBlocks().get(0).getText();
        actions().clickAndHold(visibleTitleOfLinkingBlocks().get(0)).moveToElement(visibleTitleOfLinkingBlocks().get(1)).release(visibleTitleOfLinkingBlocks().get(0)).build().perform();
        presenceOfTitleLinkingBlock();
        visibleTitleOfLinkingBlocks().get(0).shouldNotHave(exactText(titleOfFirstLinkingBlock));
        return this;
    }

    @Step("presence of title  in linking block. Tyres_feature_page")
    public Tyres_feature_page_Logic presenceOfTitleLinkingBlock() {
        for (int i = 0; i < 3; i++) {
            visibleTitleOfLinkingBlocks().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("swipe to right in Linking block. Tyres_feature_page")
    public Tyres_feature_page_Logic swipeToRightInLinkingBlock() {
        for (int i = 0; i < btnPaginatorOfLinkingBlock().size() - 1; i++) {
            String titleOfFirstLinkingBlock = visibleTitleOfLinkingBlocks().get(0).getText();
            btnPaginatorOfLinkingBlock().get(i + 1).click();
            presenceOfTitleLinkingBlock();
            presenceOfTitleLinkingBlock();
            visibleTitleOfLinkingBlocks().get(0).shouldNotHave(exactText(titleOfFirstLinkingBlock));
        }
        return this;
    }

    @Step("swipe to right in Linking block. Tyres_feature_page")
    public Tyres_feature_page_Logic swipeToLeftInLinkingBlock() {
        for (int i = btnPaginatorOfLinkingBlock().size() - 1; i > 0; i--) {
            String titleOfFirstLinkingBlock = visibleTitleOfLinkingBlocks().get(0).getText();
            btnPaginatorOfLinkingBlock().get(i - 1).click();
            presenceOfTitleLinkingBlock();
            presenceOfTitleLinkingBlock();
            visibleTitleOfLinkingBlocks().get(0).shouldNotHave(exactText(titleOfFirstLinkingBlock));
        }
        return this;
    }

    @Step("total count of Linking block. Tyres_feature_page")
    public Tyres_feature_page_Logic totalCountOfLinkingBlock(int size) {
        titlesOfLinkingBlocks().shouldHaveSize(size);
        return this;
    }

    @Step("appearance of animation in Linking block. Tyres_feature_pagee")
    public Tyres_feature_page_Logic appearanceOfAnimationInLinkingBlock() {
        headLineOfLinkingBlock().hover();
        for (int i = 0; i < visibleTitleOfLinkingBlocks().size(); i++) {
            visibleTitleOfLinkingBlocks().get(i).hover();
            btnMoreOfLinkingBlock().get(i).shouldBe(visible).shouldNotBe(empty);
        }
        return this;
    }

    @Step("check titles of linking block. Tyres_feature_page")
    public Tyres_feature_page_Logic checkTitlesOfLinkingBlock() {
        List<String> titlesOfAllLinkingBlock = titlesOfLinkingBlocks().stream().map(n -> getTextFromUnVisibleElement(n)).collect(Collectors.toList());
        for (int i = 0; i < titlesOfAllLinkingBlock.size(); i++) {
            Assert.assertFalse(titlesOfAllLinkingBlock.get(i).equals(" "));
        }
        return this;
    }

    @Step("presence of Headline at Linking block. Tyres_feature_page")
    public Tyres_feature_page_Logic presenceOfHeadlineAtLinkingBlock() {
        headLineOfLinkingBlock().shouldBe(visible);
        return this;
    }

    @Step("check transitions in Linking block. Tyres_feature_page")
    public Tyres_feature_page_Logic checkTransitionsInLinkingBlock(String url) {
        checkTransitionsOfVisibleLinkingBlock(url);
        checkTransitionsOfLastLinkingBlock(url);
        return this;
    }

    @Step("check transitions of visible Linking block. Tyres_feature_page")
    public Tyres_feature_page_Logic checkTransitionsOfVisibleLinkingBlock(String currentUrl) {
        for (int i = 0; i < visibleTitleOfLinkingBlocks().size(); i++) {
            linkingBlock().scrollIntoView("{block: \"center\"}");
            presenceOfTitleLinkingBlock();
            presenceOfTitleLinkingBlock();
            String url = btnMoreOfLinkingBlock().get(i).getAttribute("url");
            visibleTitleOfLinkingBlocks().get(i).shouldBe(visible).click();
            new Tyres_feature_page_Logic().checkingAbsenceOfCurrentLinkInLinkingBlock(url);
            back();
            waitWhileRouteContainsExpectedCondition(currentUrl);
        }
        return this;
    }

    @Step("check transitions of last Linking block. Tyres_feature_page")
    public Tyres_feature_page_Logic checkTransitionsOfLastLinkingBlock(String currentUrlSite) {
        for (int i = 0; i < btnPaginatorOfLinkingBlock().size() - 1; i++) {
            linkingBlock().scrollIntoView("{block: \"center\"}");
            presenceOfTitleLinkingBlock();
            String titleOfLastLinkingBlock = visibleTitleOfLinkingBlocks().get(3).getText();
            btnPaginatorOfLinkingBlock().get(i + 1).click();
            presenceOfTitleLinkingBlock();
            visibleTitleOfLinkingBlocks().get(3).shouldNotHave(exactText(titleOfLastLinkingBlock));
            visibleTitleOfLinkingBlocks().get(3).hover();
            String currentUrl = visibleBtnMoreOfLinkingBlock().get(0).getAttribute("url");
            visibleTitleOfLinkingBlocks().get(3).click();
            new Tyres_feature_page_Logic().checkingAbsenceOfCurrentLinkInLinkingBlock(currentUrl);
            back();
            waitWhileRouteContainsExpectedCondition(currentUrlSite);
        }
        return this;
    }
}
