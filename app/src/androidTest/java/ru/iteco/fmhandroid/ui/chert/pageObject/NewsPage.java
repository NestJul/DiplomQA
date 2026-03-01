package ru.iteco.fmhandroid.ui.chert.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;


import static ru.iteco.fmhandroid.ui.chert.data.Helper.waitId;

import android.view.View;

import androidx.annotation.IdRes;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.Matcher;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.chert.data.Data;
import ru.iteco.fmhandroid.ui.chert.data.Helper;


public class NewsPage {

    private static final int sortNewsButtonID = R.id.sort_news_material_button;
    private static final int filterNewsButtonID = R.id.filter_news_material_button;
    private static final int editNewsButtonID = R.id.edit_news_material_button;
    private static final int addNewsButtonID = R.id.add_news_image_view;
    private static final int topPartOfCreatingNewsTitleID = R.id.custom_app_bar_title_text_view;
    private static final int topPartOfEditingNewsTitleID = R.id.custom_app_bar_title_text_view;
    public static final int filterButtonID = R.id.filter_button;
    public View decorView;
    private static final ViewInteraction sortNewsButton = onView(withId(sortNewsButtonID));
    private static final ViewInteraction filterNewsButton = onView(withId(filterNewsButtonID));
    private static final ViewInteraction editNewsButton = onView(withId(editNewsButtonID));
    private static final ViewInteraction addNewsButton = onView(withId(addNewsButtonID));
    private static final ViewInteraction categoryField = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    private static final ViewInteraction titleField = onView(withId(R.id.news_item_title_text_input_edit_text));
    private static final ViewInteraction publicationDateField = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
    public static int publicationDateFieldID = R.id.news_item_publication_date_text_view;
    private static final ViewInteraction publicationTimeField = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
    private static final ViewInteraction descriptionField = onView(withId(R.id.news_item_description_text_input_edit_text));
    public static int postDescrId = R.id.news_item_description_text_view;
    private static final ViewInteraction switcherActivity = onView(withId(R.id.switcher));
    private static final ViewInteraction saveButton = onView(withId(R.id.save_button));
    private static final ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
    public static final ViewInteraction newsRecyclerView = onView(withId(R.id.news_list_recycler_view));
    public static final int newsRecyclerViewID = R.id.news_list_recycler_view;
    public static final int filterNewsHeaderID = R.id.filter_news_title_text_view;
    public static final int newsStatusId = R.id.news_item_published_text_view;
    public static final int publicationDateId = R.id.news_item_publication_date_text_view;
    public static final ViewInteraction filterButton = onView(withId(filterButtonID));
    public static final ViewInteraction publicationDateStartField = onView(withId(R.id.news_item_publish_date_start_text_input_edit_text));
    public static final ViewInteraction publicationDateEndField = onView(withId(R.id.news_item_publish_date_end_text_input_edit_text));
    public static final ViewInteraction notActiveNewsCheckboxFilter = onView(withId(R.id.filter_news_inactive_material_check_box));
    public static final ViewInteraction activeNewsCheckboxFilter = onView(withId(R.id.filter_news_active_material_check_box));
    private static final Matcher<View> changingNewsButton = allOf(withId(R.id.edit_news_item_image_view), withContentDescription("News editing button"));
    private static final Matcher<View> deletingNewsButton = allOf(withId(R.id.delete_news_item_image_view), withContentDescription("News delete button"));
    public static ViewInteraction cancelAlertDialog = onView(withId(android.R.id.button2));
    public static ViewInteraction okAlertDialog = onView(allOf(withId(android.R.id.button1), withText("OK")));
    public static final ViewInteraction popupCategoryMenu = onView(
            allOf(
                    withId(com.google.android.material.R.id.text_input_end_icon),
                    withContentDescription("Show dropdown menu")));


    public void checkVisibilityOfNewsPage() {
        Allure.step("Check NewPage visibility");
        onView(isRoot()).perform(waitId((sortNewsButtonID), 10000));
    }

    public void clickEditNewsButton() {
        Allure.step("Click Edit News button");
        onView(isRoot()).perform(waitId(editNewsButtonID, 10000));
        editNewsButton.perform(click());
    }


    public void checkVisibilityOfCreatingNewsWindow() {
        Allure.step("Check Creating News Window visibility");
        onView(isRoot()).perform(waitId((topPartOfCreatingNewsTitleID), 10000));
    }

    public void checkVisibilityOfEditingNewsWindow() {
        Allure.step("Check Editing News Window visibility");
        onView(isRoot()).perform(waitId((topPartOfEditingNewsTitleID), 10000));
    }

    public void checkVisibilityOfControlPanel() {
        Allure.step("Check Control Panel visibility");
        onView(isRoot()).perform(waitId((addNewsButtonID), 10000));
    }

    public void clickAddNewsButton() {
        Allure.step("Click Add News Button");
        onView(isRoot()).perform(waitId(addNewsButtonID, 10000));
        addNewsButton.perform(click());
    }

    public void clickSortNewsButton() {
        Allure.step("Click Sort News Button");
        onView(isRoot()).perform(waitId(sortNewsButtonID, 10000));
        sortNewsButton.perform(click());
    }

    public void clickFilterNewsButton() {
        Allure.step("Click Filter News Button");
        onView(isRoot()).perform(waitId(filterNewsButtonID, 10000));
        filterNewsButton.perform(click());
    }

    public void clickFilterButton() {
        Allure.step("Click Filter Button");
        onView(isRoot()).perform(waitId(filterButtonID, 10000));
        filterButton.perform(click());
    }

    public void clickPopupCategoryList() {
        Allure.step("Click Popup Category List");
        popupCategoryMenu.perform(click());
    }

    public void setNewsCategory(String category) {
        Allure.step("Input News Category");
        categoryField.check(matches(isDisplayed())).perform(replaceText(category), closeSoftKeyboard());
    }

    public void setPostRandomCategoryWithPopupList(String randCat) {
        Allure.step("Input Post Random Category With Popup List");
        clickPopupCategoryList();
        onView(withText(randCat))
                .inRoot(RootMatchers.isPlatformPopup())
                .perform(click());
    }

    public void setNewsTitle(String title) {
        Allure.step("Input News Title");
        titleField.check(matches(isDisplayed())).perform(replaceText(title), closeSoftKeyboard());
    }

    public void setNewsDescription(String description) {
        Allure.step("Set News Description");
        descriptionField.check(matches(isDisplayed())).perform(replaceText(description), closeSoftKeyboard());
    }

    public static void setPublicationDate(int days) {
        Allure.step("Input Publication Date");
        publicationDateField.check(matches(isDisplayed())).perform(replaceText(Data.creatingPublicationDate(days)), closeSoftKeyboard());
    }

    public void setPublicationTime(int hours) {
        Allure.step("Input Publication Time");
        publicationTimeField.check(matches(isDisplayed())).perform(replaceText(Data.creatingPublicationTime(hours)), closeSoftKeyboard());
    }

    public String setPublicationDateStart(int days) {
        return Data.creatingPublicationDate(days);
    }

    public String setPublicationDateEnd(int days) {
        return Data.creatingPublicationDate(days);
    }

    public void clickSaveNewPostButton() {
        Allure.step("Click Save New Post Button");
        saveButton.perform(click());
    }

    public void clickCancelNewPostButton() {
        Allure.step("Click Cancel New Post Button");
        cancelButton.perform(click());
    }

    public void changeSwitcherPosition() {
        Allure.step("Change Switcher Position");
        switcherActivity.perform(click());
    }

    public int countAmountOfNewsElements() {
        Allure.step("Count Amount Of News Elements");
        return Helper.getRecyclerViewItemCount(R.id.news_list_recycler_view);
    }

    public void searchingNewPostTitle(String neededTitle) {
        Allure.step("Search New Post Title");
        newsRecyclerView.check(matches(isDisplayed()))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText(neededTitle))));
    }

    public void checkNewPostCategory(String category) {
        Allure.step("Check New Post Category, category = " + category);
        categoryField.check(matches(isDisplayed()))
                .check(matches(withText(category)));
    }


    public void checkSearchResultIsDisplayed(String text) {
        Allure.step("Check visibility on search element with text = '" + text);
        ViewInteraction titleView = onView(allOf(withText(text),
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        titleView.check(matches(isDisplayed()));
        titleView.check(matches(withText(containsString(text))));
    }

    public void checkSearchResultIsDisplayed(String text1, String text2) {
        Allure.step("Check visibility on search element with text1 = " + text1 + " and text2 = " + text2);
        ViewInteraction titleView = onView(allOf(withText(text1),withText(text2),
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        titleView.check(matches(isDisplayed()));
        titleView.check(matches(withText(containsString(text1))));
    }

    public String getCreationDateOfThePost(int fieldID, int recyclerViewPos) {
        Allure.step("Get Creation Date Of The Post");
        scrollToItem(recyclerViewPos);
        return Helper.getTextFromNews(fieldID, recyclerViewPos);
    }

    public void scrollToItem(int position) {
        Allure.step("Scroll To Item On Position = " + position);
        onView(withId(newsRecyclerViewID)).perform(scrollToPosition(position));
    }

    public void checkVisibilityOfFilterNewsPanel() {
        Allure.step("Check Visibility Of Filter News Panel");
        onView(isRoot()).perform(waitId((filterNewsHeaderID), 10000));
    }

    public void setPublicationDateStartFilter(String startDate) {
        Allure.step("Set Publication Date Start Filter");
        publicationDateStartField.check(matches(isDisplayed()))
                .perform(replaceText(startDate), closeSoftKeyboard());
    }

    public void setPublicationDateEndFilter(String endDate) {
        Allure.step("Set Publication Date End Filter");
        publicationDateEndField.check(matches(isDisplayed()))
                .perform(replaceText(endDate), closeSoftKeyboard());
    }

    public void checkAllNewsNeededField(String expectedString, @IdRes int recyclerViewId, int viewingNewsFieldId) {
        Allure.step("Check All News Needed Field: " + expectedString);
        for (int i = 0; i < Helper.getRecyclerViewItemCount(recyclerViewId); i++) {
            scrollToNewsItem(i);
            String actualStatus = Helper.getTextFromNews(viewingNewsFieldId, i);
            assertEquals("Wait text '" + expectedString + "' for element " + i,
                    expectedString, actualStatus);
        }
    }

    public int checkPositionWithDescription(String expectedString, @IdRes int recyclerViewId, int viewingNewsFieldId) {
        Allure.step("Check Position With Description: " + expectedString);
        int counterPosition = 0;
        for (int i = 0; i < Helper.getRecyclerViewItemCount(recyclerViewId); i++) {
            scrollToNewsItem(i);
            String actualStatus = Helper.getTextFromNews(viewingNewsFieldId, i);
            if (expectedString.contains(actualStatus)) {
                counterPosition = i;
                break;
            }

        }
        return counterPosition;
    }

    public void checkPostDescription(@IdRes int descrFieldId, int position, String expectedString) {
        Allure.step("Check Post Description : " + expectedString);
        scrollToNewsItem(position);
        String actualDescr = Helper.getTextFromNews(descrFieldId, position);
        assertEquals("Wait text '" + expectedString,
                expectedString, actualDescr);
    }

    public void checkPostStatus(@IdRes int statusFieldId, int position, String expectedString) {
        Allure.step("Check Post Status : " + expectedString);
        scrollToNewsItem(position);
        String actualDescr = Helper.getTextFromNews(statusFieldId, position);
        assertEquals("Wait text '" + expectedString,
                expectedString, actualDescr);
    }


    public void scrollToNewsItem(int position) {
        Allure.step("Scroll To News Item with position: " + position);
        newsRecyclerView.perform(scrollToPosition(position))
                .perform(actionOnItemAtPosition(position, scrollTo()))
                .check(matches(isDisplayed()));
    }

    public void filterWithWrongDateErrorMesDisplay() {
        Allure.step("Show Date filter error");
        onView(withText(R.string.wrong_news_date_period)).check(matches(isDisplayed()));
    }

    public void clickNotActiveCheckboxFilter() {
        Allure.step("Click Not Active Checkbox Filter");
        notActiveNewsCheckboxFilter.perform(click());
    }

    public void clickActiveCheckboxFilter() {
        Allure.step("Click Active Checkbox Filter");
        activeNewsCheckboxFilter.perform(click());
    }


    public void clickEditButton(String title) {
        Allure.step("Click Edit Button");
        onView(allOf(changingNewsButton, hasSibling(withText(title)))).perform(click());
    }

    public void clickDeleteButton(String title) {
        Allure.step("Click Delete Button");
        onView(allOf(deletingNewsButton, hasSibling(withText(title)))).perform(click());
    }

    public void clickOkInAlertDialog() {
        Allure.step("Click Ok In Alert Dialog");
        okAlertDialog.perform(click());
    }

    public void clickCancelInAlertDialog() {
        Allure.step("Click Cancel In Alert Dialog");
        cancelAlertDialog.perform(click());
    }

    public void checkTitleInEditingMode(String title) {
        Allure.step("Check Title In Editing Mode " + title);
        titleField.check(matches(withText(title)));
    }

    public void checkDescriptionInEditingMode(String descr) {
        Allure.step("Check Description In Editing Mode " + descr);
        descriptionField.check(matches(withText(descr)));
    }

    public void checkDateInEditingMode(String date) {
        Allure.step("Check Date In Editing Mode " + date);
        publicationDateField.check(matches(withText(date)));
    }

    public void checkSwitcherInEditingMode(String text) {
        Allure.step("Check Switcher In Editing Mode " + text);
        switcherActivity.check(matches(withText(text)));
    }

    public void checkPostDoesNotExist(String title) {
        Allure.step("Check Post Does Not Exist " + title);
        onView(allOf(withText(title))).check(doesNotExist());
    }

    public void checkErrorMessage() {
        Allure.step("Check Error Message");
        onView(withText(Data.popupErrorMessage));
    }

    public void checkCategoryErrorMessage() {
        Allure.step("Check Category Error Message");
        onView(withText(Data.popupCategoryErrorMessage));
    }
}
