package com.example.expertcourseandroidproject.stats

import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.example.expertcourseandroidproject.R
import com.example.expertcourseandroidproject.game.ButtonUi
import org.hamcrest.Matcher

class StatsPage(skips: Int, fails: Int, corrects: Int) {


    private val containerIdMatcher: Matcher<View> = withParent(withId(R.id.statsLayout))
    private val containerClassTypeMatcher: Matcher<View> =
        withParent(isAssignableFrom(FrameLayout::class.java)) //ВОт тут поменять контейнер если что

    private val statsUi = StatsUi(containerIdMatcher, containerClassTypeMatcher, skips = skips, fails = fails, corrects = corrects)

    private val newGameUi = ButtonUi(
        R.id.newGameButton,
        "#FF0000", R.string.new_game,
        containerIdMatcher,
        containerClassTypeMatcher
    )

    fun assertInitialState(){
        statsUi.assertVisible()
    }

    fun clickNewGame() {
        newGameUi.click()
    }

}