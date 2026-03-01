package ru.iteco.fmhandroid.ui.chert.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static ru.iteco.fmhandroid.ui.chert.data.Helper.waitForView;
import static ru.iteco.fmhandroid.ui.chert.data.Helper.waitId;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.chert.data.Helper;

public class MainPage {
    private static final int mainPageTextID = R.id.all_news_text_view;
    private static final int appMainMenuButtonID = R.id.main_menu_image_button;
    private static final int ourMissionIconID = R.id.our_mission_image_button;
    private static final String appMenuItemMainText = "Main";
    private static final String appMenuItemNewsText = "News";
    private static final String appMenuItemAboutText = "About";
    private static final ViewInteraction mainPageText = onView(withId(mainPageTextID));
    private static final ViewInteraction appMainMenuButton = onView(withId(appMainMenuButtonID));
    private static final ViewInteraction appMenuItemMain = onView(withText(appMenuItemMainText));
    private static final ViewInteraction appMenuItemNews = onView(withText(appMenuItemNewsText));
    private static final ViewInteraction appMenuItemAbout = onView(withText(appMenuItemAboutText));
    private static final ViewInteraction ourMissionIcon = onView(withId(ourMissionIconID));
    private static final ViewInteraction showAllNews = onView((allOf(withId(R.id.all_news_text_view), withText("ALL NEWS"))));

    public void checkVisibilityOfMainPage() {
        Allure.step("Check MainPage visibility");
        onView(isRoot()).perform(waitId((mainPageTextID), 10000));
    }

    public void clickAppMenuBar() {
        Allure.step("Click AppBar");
        Helper.pauseExecution(1000);
        appMainMenuButton.perform(click());
    }

    public void checkVisibilityOfAppMenuBarItems() {
        Allure.step("Check AppMenu elements visibility");
        Helper.waitForView(
                anyOf(
                        withText(appMenuItemMainText),
                        withText(appMenuItemNewsText),
                        withText(appMenuItemAboutText)
                ),
                10000
        );
    }

    public void clickAboutItem() {
        Allure.step("Go to About");
        Helper.pauseExecution(500);
        appMenuItemAbout.perform(click());
    }

    public void clickNewsItem() {
        Allure.step("Go to News");
        Helper.pauseExecution(500);
        appMenuItemNews.perform(click());
    }

    public void clickOurMissionIcon() {
        Allure.step("Go to Our Mission");
        Helper.pauseExecution(500);
        ourMissionIcon.perform(click());
    }

    public void clickShowAllNews() {
        Allure.step("Show all news");
        Helper.pauseExecution(500);
        showAllNews.perform(click());
    }
}

