package com.example.eventmakr.eventmakr.Activities;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.eventmakr.eventmakr.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class creaeEventTest {

    @Rule
    public ActivityTestRule<SplashScreen> mActivityTestRule = new ActivityTestRule<>(SplashScreen.class);

    @Test
    public void creaeEventTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
//        try {
//            Thread.sleep(60000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        ViewInteraction cardView = onView(
//                allOf(withId(R.id.buttonLogIn),
//                        withParent(allOf(withId(R.id.activity_main),
//                                withParent(withId(android.R.id.content)))),
//                        isDisplayed()));
//        cardView.perform(click());
//
//        // Added a sleep statement to match the app's execution delay.
//        // The recommended way to handle such scenarios is to use Espresso idling resources:
//        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
//        try {
//            Thread.sleep(3589135);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        ViewInteraction tj = onView(
//                allOf(withText("Sign In"),
//                        withParent(allOf(withId(R.id.signInButtonGoogle),
//                                withParent(withId(R.id.fragmentGoogleSignIn)))),
//                        isDisplayed()));
//        tj.perform(click());
//
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(2718);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction cardView2 = onView(
                allOf(withId(R.id.buttonPlanning),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        cardView2.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(2512);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fabNewEvent), isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinner_occasion), isDisplayed()));
        appCompatSpinner.perform(click());

        ViewInteraction linearLayout = onView(
                allOf(withClassName(is("android.widget.LinearLayout")), isDisplayed()));
        linearLayout.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTextInputEventName), isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editTextInputEventName), isDisplayed()));
        appCompatEditText2.perform(replaceText("Mary's wedding"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.editTextZipCode), isDisplayed()));
        appCompatEditText3.perform(replaceText("55663"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.editTextZipCode), withText("55663"), isDisplayed()));
        appCompatEditText4.perform(pressImeActionButton());

        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("CREATE")));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recyclerViewEventsList),
                        withParent(withId(R.id.containerEvents)),
                        isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(2210);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction cardView3 = onView(
                allOf(withId(R.id.cardViewCaterers),
                        withParent(allOf(withId(R.id.layoutVendorMenu1),
                                withParent(withId(R.id.layoutVendors)))),
                        isDisplayed()));
        cardView3.perform(click());

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.recyclerViewVendorList),
                        withParent(withId(R.id.containerRecyclerVendorFragment)),
                        isDisplayed()));
        recyclerView2.perform(actionOnItemAtPosition(0, click()));

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.imageViewVendorProductItem),
                        withParent(withId(R.id.cardViewVendorProductItem_helper)),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.editTextQuantity), isDisplayed()));
        appCompatEditText5.perform(replaceText("1"), closeSoftKeyboard());

        pressBack();

        ViewInteraction cardView4 = onView(
                allOf(withId(R.id.buttonProductItemSelect), isDisplayed()));
        cardView4.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.imageViewVendorProductItem),
                        withParent(withId(R.id.cardViewVendorProductItem_helper)),
                        isDisplayed()));
        appCompatImageView2.perform(click());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.editTextQuantity), isDisplayed()));
        appCompatEditText6.perform(replaceText("2"), closeSoftKeyboard());

        pressBack();

        ViewInteraction cardView5 = onView(
                allOf(withId(R.id.buttonProductItemSelect), isDisplayed()));
        cardView5.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction cardView6 = onView(
                allOf(withId(R.id.buttonMyItems), isDisplayed()));
        cardView6.perform(click());

        ViewInteraction cardView7 = onView(
                allOf(withId(R.id.buttonContactVendor), isDisplayed()));
        cardView7.perform(click());

        ViewInteraction imageButton = onView(
                allOf(withClassName(is("android.widget.ImageButton")),
                        withParent(withId(R.id.toolbarEvent)),
                        isDisplayed()));
        imageButton.perform(click());

    }

}
