package com.example.expertcourseandroidproject.game

import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.expertcourseandroidproject.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

class CheckButtonUi(containerIdMatcher: Matcher<View>, containerClassTypeMatcher: Matcher<View>) : AbstractButtonUi(
    onView(
        allOf(
            withId(R.id.checkCorrectString),
            withText(R.string.check_str),
            isAssignableFrom(AppCompatButton::class.java),
            ButtonColorMatcher("#5D5AFF"),
            containerIdMatcher,
            containerClassTypeMatcher
        )
    )
) {
    fun assertVisibleDisabled() {
        interaction.check(matches(isNotEnabled()))
            .check(matches(isCompletelyDisplayed()))
    }

    fun assertVisibleEnabled() {
        interaction.check(matches(isEnabled()))
            .check(matches(isCompletelyDisplayed()))
    }
}


