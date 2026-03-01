package ru.iteco.fmhandroid.ui.chert.test;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.chert.data.Data;
import ru.iteco.fmhandroid.ui.chert.data.Helper;
import ru.iteco.fmhandroid.ui.chert.pageObject.AboutPage;
import ru.iteco.fmhandroid.ui.chert.pageObject.AuthorizationPage;
import ru.iteco.fmhandroid.ui.chert.pageObject.MainPage;
import ru.iteco.fmhandroid.ui.chert.pageObject.NewsPage;
import ru.iteco.fmhandroid.ui.chert.pageObject.OurMissionPage;

@RunWith(AllureAndroidJUnit4.class)

public class MainPageTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    AuthorizationPage authorizationPage = new AuthorizationPage();
    MainPage mainPage = new MainPage();
    NewsPage newsPage = new NewsPage();
    AboutPage aboutPage = new AboutPage();
    OurMissionPage ourMissionPage = new OurMissionPage();

    @Before
    public void setUp() {
        try {
            mainPage.checkVisibilityOfMainPage();
        } catch (Exception e) {
            authorizationPage.signInButtonCheckVisibility();
            authorizationPage.successfulAuthorization();
            mainPage.checkVisibilityOfMainPage();
        }
    }

    @DisplayName("Отображение разделов приложения по нажатию кнопки на AppMenu")
    @Test
    public void displayingAppMenuItems() {
        mainPage.clickAppMenuBar();
        mainPage.checkVisibilityOfAppMenuBarItems();
    }

    @DisplayName("Переход в раздел 'About' через AppMenu")
    @Test
    public void viewingAboutPage() {
        mainPage.clickAppMenuBar();
        mainPage.clickAboutItem();
        aboutPage.checkVisibilityOfAboutPage();
    }

    @DisplayName("Переход в раздел 'OurMission' через AppBar")
    @Test
    public void viewingOurMissionPage() {
        mainPage.clickOurMissionIcon();
        ourMissionPage.checkVisibilityOfOurMissionPage();
    }


    @DisplayName("Переход в раздел 'News' через AppMenu")
    @Test
    public void viewingNewsPage() {
        mainPage.clickAppMenuBar();
        mainPage.clickNewsItem();
        newsPage.checkVisibilityOfNewsPage();
    }

    @DisplayName("Переход в раздел 'News' через главную страницу")
    @Test
    public void transitionToNewPageFromMainPage() {
        mainPage.clickShowAllNews();
        newsPage.checkVisibilityOfNewsPage();
    }

}