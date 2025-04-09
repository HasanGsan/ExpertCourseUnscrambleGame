package com.example.expertcourseandroidproject.views

import android.view.View
import com.example.expertcourseandroidproject.game.CheckUiState
import com.example.expertcourseandroidproject.views.check.UpdateCheckButton
import org.junit.Assert.assertEquals
import org.junit.Test


class CheckUiStateTest {

    @Test
    fun testEnabled() {
        val checkUiState = CheckUiState.Enabled

        val button = FakeButton()
        checkUiState.update(button)

        assertEquals(View.VISIBLE, button.visibility)
        assertEquals(true, button.checkUiState)
    }

}

private class FakeButton : UpdateCheckButton {
    override fun update(uiState: CheckUiState) {
        throw IllegalStateException("not used here")
    }

    var visibility:Int? = null
    var checkUiState: Boolean? = null

    override fun update(visibility: Int, enabled: Boolean) {
        this.visibility = visibility
        this.checkUiState = enabled
    }

}