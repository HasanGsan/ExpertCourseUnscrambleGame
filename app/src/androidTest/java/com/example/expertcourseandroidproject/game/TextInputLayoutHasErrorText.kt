package com.example.expertcourseandroidproject.game

import android.view.View
import android.widget.Button
import androidx.annotation.StringRes
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description

class TextInputLayoutHasErrorText (
    @StringRes private val errorResId: Int
) : BoundedMatcher<View, Button>(Button::class.java) {
    override fun describeTo(description: Description) {
        description.appendText("error doesn't match with expected $errorResId")
    }

    override fun matchesSafely(item: Button): Boolean {
        return item.error == item.context.getString(errorResId)
    }
}