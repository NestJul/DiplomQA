package ru.iteco.fmhandroid.ui.chert.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static ru.iteco.fmhandroid.ui.chert.data.Helper.waitId;
import androidx.test.espresso.ViewInteraction;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class AboutPage {
    private static final int appVersionId = R.id.about_version_title_text_view;
    private static final ViewInteraction privacyPolicy = onView(withId(R.id.about_privacy_policy_label_text_view));
    private static final ViewInteraction privacyPolicyText = onView(withId(R.id.about_privacy_policy_value_text_view));
    private static final ViewInteraction termsOfUse = onView(withId(R.id.about_terms_of_use_label_text_view));
    private static final ViewInteraction termsOfUseText = onView(withId(R.id.about_terms_of_use_value_text_view));
    private static final ViewInteraction aboutCompany = onView(withId(R.id.about_company_info_label_text_view));

    public void checkVisibilityOfAboutPage() {
        Allure.step("Check page visibility on AboutPage transition");
        onView(isRoot()).perform(waitId((appVersionId), 10000));
    }

}
