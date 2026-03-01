package ru.iteco.fmhandroid.ui.chert.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.chert.data.Helper.waitId;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class OurMissionPage {
    private static final int headerLoveIsAllId = R.id.our_mission_title_text_view;
    private static final ViewInteraction citationComments = onView(allOf(
            withId(R.id.our_mission_item_open_card_image_button),
            isDescendantOfA(withId(R.id.our_mission_item_list_recycler_view)),
            hasSibling(withText("«Хоспис для меня - это то, каким должен быть мир.\""))
    ));

    public void checkVisibilityOfOurMissionPage() {
        Allure.step("Check Our Mission Page Visibility");
        waitId((headerLoveIsAllId), 10000);
    }
}
