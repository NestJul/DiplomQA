package ru.iteco.fmhandroid.ui.chert.test;

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.chert.pageObject.AuthorizationPage;
import ru.iteco.fmhandroid.ui.chert.pageObject.MainPage;

@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationPageTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    AuthorizationPage authorizationPage = new AuthorizationPage();
    MainPage mainPage = new MainPage();
    private View decorView;


    @Before
    public void setUp() {
        mActivityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());
        try {
            authorizationPage.signInButtonCheckVisibility();
        } catch (Exception e) {
            authorizationPage.logOutFromProfile();
        }
    }

    @DisplayName("Авторизация зарегистрированного пользователя")
    @Test
    public void registeredUserAuth() {
        authorizationPage.signInButtonCheckVisibility();
        authorizationPage.successfulAuthorization();
        mainPage.checkVisibilityOfMainPage();
    }

    @DisplayName("Авторизация с использованием невалидного пароля")
    @Test
    public void nonvalidPasswordAuth() {
        authorizationPage.signInButtonCheckVisibility();
        authorizationPage.failAuthorizationWithWrongPassword();
        authorizationPage.authErrorMesDisplay();

    }

    @DisplayName("Авторизация с использованием пустых полей")
    @Test
    public void emptyAuthFields() {
        authorizationPage.signInButtonCheckVisibility();
        authorizationPage.failAuthorizationWithEmptyFields();
        authorizationPage.authErrorMesDisplay();

    }

    @DisplayName("Выход из авторизированного профиля приложения")
    @Test
    public void escapeFromAuthorizatedProfile() {
        authorizationPage.signInButtonCheckVisibility();
        authorizationPage.successfulAuthorization();
        mainPage.checkVisibilityOfMainPage();
        authorizationPage.logOutFromProfile();
        authorizationPage.signInButtonCheckVisibility();


    }


}
