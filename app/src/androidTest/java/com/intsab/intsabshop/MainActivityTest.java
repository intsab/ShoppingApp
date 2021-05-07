package com.intsab.intsabshop;


import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction textView = onView(
                allOf(withId(R.id.tv_cat_name), withText("electronics"),
                        withParent(allOf(withId(R.id.bg_category),
                                withParent(withId(R.id.rv_categories)))),
                        isDisplayed()));
        textView.check(matches(withText("electronics")));

        ViewInteraction button = onView(
                allOf(withId(R.id.add_to_cart), withText("ADD TO CART"),
                        withParent(allOf(withId(R.id.main_item),
                                withParent(withId(R.id.rv_items)))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction imageView = onView(
                allOf(withId(R.id.image),
                        withParent(withParent(withId(R.id.view_pager))),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction imageView2 = onView(
                allOf(withId(R.id.image),
                        withParent(withParent(withId(R.id.view_pager))),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));
    }
}
