package com.example.expertcourseandroidproject.game

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.expertcourseandroidproject.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

class ShuffleWorldUi(
    text: String,
    containerIdMatcher: Matcher<View>,
    containerClassTypeMatcher: Matcher<View>
) {

    private val interaction: ViewInteraction = onView(
        allOf(
            withId(R.id.shuffledWordTextView),
            withText(text),
            containerIdMatcher,
            containerClassTypeMatcher,
            isAssignableFrom(TextView::class.java)

        )
    )

    fun assertTextVisible() {
        interaction.check(matches(isCompletelyDisplayed()))
    }

    fun assertDoesNotExist() {
        interaction.check(doesNotExist())
    }


}