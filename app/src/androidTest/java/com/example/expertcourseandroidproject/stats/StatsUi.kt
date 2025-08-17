package com.example.expertcourseandroidproject.stats

import android.R
import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import java.util.regex.Pattern.matches

class StatsUi(
    containerIdMatcher: Matcher<View>,
    containerClassTypeMatcher: Matcher<View>,
    skips: Int,
    fails: Int,
    corrects: Int
) {

    private val interaction: ViewInteraction = onView(
        allOf(
            withId(R.id.statsTextView),
            isAssignableFrom(TextView::class.java),
            containerIdMatcher,
            containerClassTypeMatcher,
            withText("Game Over\n\nSkips: $skips\nFails: $fails\nCorrects: $corrects")
        )

    )

    fun assertVisible() {
        interaction.check(matches(isDisplayed()))
    }

}
