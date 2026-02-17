package ru.iteco.fmhandroid.ui.chert.data;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

import static ru.iteco.fmhandroid.ui.chert.data.Data.categories;

import android.content.res.Resources;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Random;
import java.util.concurrent.TimeoutException;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class Helper {
    public static ViewAction waitForView(final Matcher<View> viewMatcher, final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for a specific view during " + millis + " millis.";
            }

            @Override
            public void perform(final UiController uiController, final View view) {
                uiController.loopMainThreadUntilIdle();
                final long endTime = System.currentTimeMillis() + millis;

                while (System.currentTimeMillis() < endTime) {
                    if (isViewPresent(view, viewMatcher)) {
                        return;
                    }
                    uiController.loopMainThreadForAtLeast(50);
                }

                throw createTimeoutException(view);
            }

            private boolean isViewPresent(View rootView, Matcher<View> viewMatcher) {
                for (View child : TreeIterables.breadthFirstViewTraversal(rootView)) {
                    if (viewMatcher.matches(child)) {
                        return true;
                    }
                }
                return false;
            }

            private PerformException createTimeoutException(View view) {
                return new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException("View not found after " + millis + " ms"))
                        .build();
            }
        };
    }

    public static ViewAction waitId(final int viewId, final long millis) {
        return waitForView(withId(viewId), millis);
    }

    public static int getRecyclerViewItemCount(@IdRes int recyclerViewId) {
        final int[] number = new int[1];
        onView(allOf(withId(recyclerViewId), isDisplayed()))
                .check((view, noViewFoundException) -> {
                    if (view instanceof RecyclerView) {
                        RecyclerView neededRecyclerView = (RecyclerView) view;
                        RecyclerView.Adapter adapter = neededRecyclerView.getAdapter();
                        if (adapter != null) {
                            number[0] = adapter.getItemCount();
                        }
                    }
                });
        return number[0];
    }

    // Search helper for RecyclerView by positions and ids
    public static class RecyclerViewMatcher {
        private final int recyclerViewId;

        public RecyclerViewMatcher(int recyclerViewId) {
            this.recyclerViewId = recyclerViewId;
        }

        public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
            return new RecyclerViewMatcher(recyclerViewId);
        }

        public Matcher<View> atPosition(final int position) {
            return atPositionOnView(position, -1);
        }

        public Matcher<View> atPositionOnView(final int position, final int targetViewId) {
            return new TypeSafeMatcher<View>() {
                Resources resources = null;
                View childView;

                public void describeTo(Description description) {
                    String idDescription = Integer.toString(recyclerViewId);
                    if (this.resources != null) {
                        try {
                            idDescription = this.resources.getResourceName(recyclerViewId);
                        } catch (Resources.NotFoundException e) {
                            idDescription = String.format("%s (resource not found)", recyclerViewId);
                        }
                    }

                    description.appendText("with id: " + idDescription);
                }

                public boolean matchesSafely(View view) {
                    this.resources = view.getResources();

                    if (childView == null) {
                        RecyclerView recyclerView = view.getRootView().findViewById(recyclerViewId);
                        if (recyclerView != null && recyclerView.getId() == recyclerViewId) {
                            RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                            if (viewHolder != null) {
                                childView = viewHolder.itemView;
                            }
                        } else {
                            return false;
                        }
                    }

                    if (targetViewId == -1) {
                        return view == childView;
                    } else {
                        View targetView = childView.findViewById(targetViewId);
                        return view == targetView;
                    }
                }
            };
        }
    }

    public static String getTextFromNews(int fieldId, int position) {
        final String[] itemText = new String[1];
        onView(RecyclerViewMatcher.withRecyclerView(R.id.news_list_recycler_view).atPosition(position))
                .check((view, noViewFoundException) -> {
                    if (noViewFoundException != null) {
                        throw noViewFoundException;
                    }
                    TextView textView = view.findViewById(fieldId);
                    itemText[0] = textView.getText().toString();
                });
        return itemText[0];
    }

    public static String randomCategory() {
        Random random = new Random();
        return categories.get(random.nextInt(categories.size()));
    }

    public static void pauseExecution(long millis) {
        Allure.step("Wait " + millis / 1000 + " sec");
        SystemClock.sleep(millis);
    }


}
