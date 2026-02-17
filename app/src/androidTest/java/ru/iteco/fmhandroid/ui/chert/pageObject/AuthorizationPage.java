package ru.iteco.fmhandroid.ui.chert.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.chert.data.Helper.waitId;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.chert.data.Data;

public class AuthorizationPage {
    private static final ViewInteraction loginField = onView((allOf(withHint("Login"), isDescendantOfA(withId(R.id.login_text_input_layout)))));
    private static final ViewInteraction passwordField = onView((allOf(withHint("Password"), isDescendantOfA(withId(R.id.password_text_input_layout)))));
    private static final ViewInteraction signInButton = onView(withId(R.id.enter_button));
    private static final ViewInteraction profileIcon = onView(withId(R.id.authorization_image_button));
    private static final ViewInteraction logOutButton = onView(withText("Log out"));
    private static final int signInButtonID = R.id.enter_button;

    public void signInButtonCheckVisibility() {
        Allure.step("Check auth button visibility");
        onView(isRoot()).perform(waitId((signInButtonID), 10000));
    }

    public void logOutFromProfile() {
        Allure.step("Logout from active profile");
        profileIcon.perform(click());
        logOutButton.perform(click());
    }

    public void successfulAuthorization() {
        Allure.step("Successful auth with valid data");
        loginField.check(matches(isDisplayed())).perform(typeText(Data.validLogin), closeSoftKeyboard());
        passwordField.check(matches(isDisplayed())).perform(typeText(Data.validPassword), closeSoftKeyboard());
        signInButton.perform(click());
    }


    public void failAuthorizationWithWrongLogin() {
        Allure.step("Try to login with wrong login");
        loginField.perform(typeText(Data.nonValidLogin), closeSoftKeyboard());
        passwordField.perform(typeText(Data.validPassword), closeSoftKeyboard());
        signInButton.perform(click());
    }

    public void failAuthorizationWithWrongPassword() {
        Allure.step("Try to login with wrong password");
        loginField.perform(typeText(Data.validLogin), closeSoftKeyboard());
        passwordField.perform(typeText(Data.nonValidPassword), closeSoftKeyboard());
        signInButton.perform(click());
    }

    public void failAuthorizationWithEmptyFields() {
        Allure.step("Try to login with empty fields");
        signInButton.perform(click());
    }

    public void authErrorMesDisplay() {
        Allure.step("Check toast with error text");
        onView(withText(R.string.error));
    }


}
