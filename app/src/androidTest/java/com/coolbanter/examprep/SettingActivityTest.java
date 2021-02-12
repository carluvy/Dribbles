package com.coolbanter.examprep;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)

public class SettingActivityTest {
    @Rule
    public ActivityTestRule<SettingActivity> mTestRule = new ActivityTestRule<>(SettingActivity.class);

//    public ActivityScenarioRule<SettingActivity> mActivityTest = new ActivityScenarioRule<>(SettingActivity.class);



//    @Rule
//    public ActivityTestRule<SettingActivity>

    //    @Before
//    public void setup() {
//        Context context = getApplicationContext();
//        context = contains("c")
//    }

//    @Test
//    public void check() {
//        onView(allOf(withId(android.R.id.summary), withText("Show number of possible answers"),
//                        isDisplayed()));
//        textView3.perform(click());
//
//    }



    @Test
    public void checkPref() {
        Context context = getApplicationContext();
        String[] myArray = mTestRule.getActivity().getResources().getStringArray(R.array.number_smileys_answers);
//
//        onData(allOf(
//                is(instanceOf(Preference.class)),
//                withKey(mTestRule.getActivity().getResources().getString(R.string.pref_key_answers)),
//                withSummary(R.string.pref_answers_summary),
//                withTitle(R.string.pref_answers_title)))
//                .onChildView(withText(R.string.pref_answers_title))
//                .check(matches(isCompletelyDisplayed()));
//﻿
////        onData(PreferenceMatchers.withKey(context.getString(R.string.pref_key_answers)))
////                .check(matches(isDisplayed())).perform(click());
//        onView(
//                allOf(withId(android.R.id.title), withText("Number of answers"),
//                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.preference.Preference.class))),
//                        isDisplayed()));

//        int size = myArray.length;
//        for (int i = 0; i < size; i++) {
//            onView(withText("Show number of possible answers.")).check(matches(withText(containsString(myArray[i]))));
//        }

//        onView(withText(mTestRule.getActivity().getResources()
//                .getString(R.string.pref_key_answers))).perform(click());

//        onData(allOf(is(instanceOf(Preference.class)), withKey("pref_answer_key")))
//                .onChildView(withText(R.string.pref_answers_title))
//                .perform(click());
//        onView(instanceOf(androidx.preference.ListPreference.class)).perform(click());
//    (String.valueOf(R.string.pref_key_answers))).check(matches(isEnabled())).perform(click());


// test works
//        onView(withId(androidx.preference.R.id.recycler_view))
//                .perform(RecyclerViewActions.actionOnItem(hasDescendant(withText(R.string.pref_answers_summary)),
//                        click()));

//       test works
//        onView(withId(androidx.preference.R.id.recycler_view))
//                .perform(RecyclerViewActions.actionOnItem(hasDescendant(withText(R.string.pref_answers_title)),
//                        click()));


// test works
        onView(withText(mTestRule.getActivity()
                .getResources().getString(R.string.pref_answers_title))).perform(click()).check(matches(isCompletelyDisplayed()));

//
////        onData(allOf(is(instanceOf(ListPreference.class)), hasEntry(equalTo("pref_key_answers"), is("number of answers"))))
////                .perform(click());
//
//        int size = myArray.length;
//        for (int i = 0; i < size; i++) {
//            onView(allOf(withId(android.R.id.summary), withText("Show number of possible answers."),
//                    withParent(withParent(IsInstanceOf.<View>instanceOf(androidx.preference.Preference.class))), isDisplayed()));
//
////            onView(withText("Show number of possible answers.")).check(matches(isDisplayed())).perform(click());
//            onData(is(myArray[i])).perform(click());
//            onView(withId(R.id.n_answers))
//                    .check(matches(withText(containsString(myArray[i]))));
//////        }
//        }
    }
//
////
//        onView(withText(R.string.pref_answers_title)).perform(click()).check(matches(isDisplayed()));
//                .perform(click());
//
////
////        onData(allOf(
////                is(instanceOf(Preference.class)),
////                withText(context.getResources().getString(R.string.pref_key_answers))))
////                .check(matches(isDisplayed()));
//
//
////        onData(allOf(
////                is(instanceOf(Preference.class)),
////                withText(context.getResources().getString(R.string.pref_answers_title)))).perform(click());
//////                .onChildView(withText(context.getResources()
////                        .getString(R.string.pref_answers_title))).perform(click());
//
//
////        onData(allOf(is(instanceOf(Preference.class)),
////                withKey("pref_key_answers"))).onChildView(withText(R.string.pref_key_answers)).perform(click());
////        onView(withText(context.getResources().getString(R.string.pref_key_answers))).perform(click());
//
////        onData(PreferenceMatchers.withKey(context.getString(R.string.pref_key_answers))).perform(click());
//
////////        String [] myArray = mScenarioRule.getScenario().onActivity();
//        int size = myArray.length;
////        for (int i = 0; i < size; i++) {
//////            onView(withText(context.getResources().getString(R.string.pref_answers_title))).perform(click());
////            onView(withId(R.id.n_answers)).perform(click());
////            onData(is(myArray[i])).check(matches(withText( containsString(myArray[i]))));
////            onData(PreferenceMatchers.withKey(String.valueOf(R.string.pref_key_answers)))
////                    .inRoot(withRowString(1, "pre")).perform(click())
////            onData(PreferenceMatchers.withTitle(R.string.pref_answers_title))
////                    .perform(click());
////            onData(is(myArray[i])).perform(click());
////            onView(withId(R.id.n_answers))
////                    .check(matches(withText(containsString(myArray[i]))));
////        onData(allOf(is(instanceOf(Preference.class)), withKey("pref_answer_key"))).check(matches(isDisplayed()));
////        onData(allOf(is(instanceOf(Preference.class)), withKey("pref_answer_key")))
////                .onChildView(withClassName(is(ListPreference.class.getName()))).perform(click());
////        onData(PreferenceMatchers.withKey("pref_answer_key")).check(matches(isDisplayed()));
//    }

//        onView(withText(mScenarioRule.getClass().getResources().getString(R.string.list_entry_sring))
//                .perform(click());


}