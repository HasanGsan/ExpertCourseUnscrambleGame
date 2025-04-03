package com.example.expertcourseandroidproject

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.example.expertcourseandroidproject.game.ButtonUi

import org.hamcrest.Matcher

class GameOverPage(incorrects: Int, corrects: Int) {


    private val containerIdMatcher: Matcher<View> = withParent(withId(R.id.gameOverContainer))
    private val containerClassTypeMatcher: Matcher<View> =
        withParent(isAssignableFrom(ConstraintLayout::class.java))

    private val statsUi = StatsUi(incorrects = incorrects, corrects = corrects,
        containerIdMatcher, containerClassTypeMatcher)

    private val newGameUi = ButtonUi(
        R.id.newGameButton,
        "#5D5AFF",
        R.string.new_game,
        containerIdMatcher,
        containerClassTypeMatcher
    )

    fun assertInitialState() {
        statsUi.assertVisible()
    }

    fun clickNewGame() {
        newGameUi.click()
    }

    fun assertNotVisible() {
        statsUi.assertDoesNotExist()
    }


}
