package com.example.expertcourseandroidproject.game

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.example.expertcourseandroidproject.R
import org.hamcrest.Matcher


class GamePage(word: String) {

    private val containerIdMatcher: Matcher<View> = withParent(withId(R.id.constraintLayout))
    private val containerClassTypeMatcher: Matcher<View> =
        withParent(isAssignableFrom(ConstraintLayout::class.java))

    private val shuffleWord = ShuffleWorldUi(
        text = word,
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = containerClassTypeMatcher

    )

    private val inputUi = InputUi(
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = containerClassTypeMatcher
    )

    private val skipUi = ButtonUi(
        id = R.id.skipButton,
        colorHex = "#5AFFF5",
        textResId = R.string.miss_str,
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = containerClassTypeMatcher
    )

    private val nextUi = ButtonUi(
        id = R.id.nextButton,
        colorHex = "#FF5AEE",
        textResId = R.string.next_str,
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = containerClassTypeMatcher
    )

    private val checkUi = CheckButtonUi(
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = containerClassTypeMatcher
    )

    fun assertInitialState() {
        shuffleWord.assertTextVisible()
        inputUi.assertInitialState()
        skipUi.assertVisible()
        checkUi.assertVisibleDisabled()
        nextUi.assertNotVisible()
    }

    fun addInput(text: String) {
        inputUi.addInput(text = text)
    }

    fun assertInsufficientState() {
        shuffleWord.assertTextVisible()
        inputUi.assertInsufficientState()
        skipUi.assertVisible()
        checkUi.assertVisibleDisabled()
        nextUi.assertNotVisible()
    }

    fun assertSufficientState() {
        shuffleWord.assertTextVisible()
        inputUi.assertSufficientState()
        skipUi.assertVisible()
        checkUi.assertVisibleEnabled()
        nextUi.assertNotVisible()
    }

    fun clickNext() {
        nextUi.click()
    }

    fun assertCorrectState() {
        shuffleWord.assertTextVisible()
        inputUi.assertCorrectState()
        skipUi.assertNotVisible()
        checkUi.assertNotVisible()
        nextUi.assertVisible()
    }

    fun clickSkip() {
        skipUi.click()
    }

    fun clickCheck() {
        checkUi.click()
    }

    fun assertIncorrectState() {
        shuffleWord.assertTextVisible()
        inputUi.assertIncorrectState()
        skipUi.assertVisible()
        checkUi.assertVisibleDisabled()
        nextUi.assertNotVisible()
    }

    fun removeInputLastLetter() {
        inputUi.removeInputLastLetter()
    }

}