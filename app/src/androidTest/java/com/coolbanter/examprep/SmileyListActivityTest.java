package com.coolbanter.examprep;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class SmileyListActivityTest {

    @Rule
    public ActivityScenarioRule<SmileyListActivity> mActivityTest = new ActivityScenarioRule<>(SmileyListActivity.class);

    @Test
    public void checkFab(){
        onView(withId(R.id.fab))
                .check(matches(isDisplayed())).perform(click());

    }

}