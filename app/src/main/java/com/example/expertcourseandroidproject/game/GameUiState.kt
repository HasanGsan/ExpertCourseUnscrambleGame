package com.example.expertcourseandroidproject.game

import com.example.expertcourseandroidproject.views.check.UpdateCheckButton
import com.example.expertcourseandroidproject.views.input.UpdateInput
import com.example.expertcourseandroidproject.views.shuffledWorld.UpdateText
import com.example.expertcourseandroidproject.views.visibilitybutton.UpdateVisibility
import com.example.expertcourseandroidproject.views.visibilitybutton.VisibilityUiState



interface GameUiState{

    fun update(
        shuffledWordTextView: UpdateText,
        inputView: UpdateInput,
        skip: UpdateVisibility,
        check: UpdateCheckButton,
        next: UpdateVisibility
    )

    object Empty : GameUiState {
        override fun update(
            shuffledWordTextView: UpdateText,
            inputView: UpdateInput,
            skip: UpdateVisibility,
            check: UpdateCheckButton,
            next: UpdateVisibility
        ) = Unit

    }


    abstract class Abstract(
        private val inputUiState: InputUiState,
        private val checkUiState: CheckUiState,
        ) : GameUiState {

        override fun update(
            shuffledWordTextView: UpdateText,
            inputView: UpdateInput,
            skip: UpdateVisibility,
            check: UpdateCheckButton,
            next: UpdateVisibility
        ) {
            inputView.update(inputUiState)
            check.update(checkUiState)
        }


    }

    data class Initial(
        private val shuffledWord: String,
        private val userInput: String = ""
    ) : Abstract(
        InputUiState.Initial(userInput),
        CheckUiState.Disabled,
    ) {
        override fun update(
            shuffledWordTextView: UpdateText,
            inputView: UpdateInput,
            skip: UpdateVisibility,
            check: UpdateCheckButton,
            next: UpdateVisibility
        ) {
            super.update(shuffledWordTextView, inputView, skip, check, next)
            shuffledWordTextView.update(shuffledWord)
            skip.update(VisibilityUiState.Visible)
            next.update(VisibilityUiState.Gone)

        }
    }

    object Insufficient: Abstract(
        InputUiState.Insufficient,
        CheckUiState.Disabled,
    )

    object Sufficient : Abstract(
        InputUiState.Sufficient,
        CheckUiState.Enabled,
    )

    object Correct : Abstract(
        InputUiState.Correct,
        CheckUiState.Invisible,
    ) {
        override fun update(
            shuffledWordTextView: UpdateText,
            inputView: UpdateInput,
            skip: UpdateVisibility,
            check: UpdateCheckButton,
            next: UpdateVisibility
        ) {
            super.update(shuffledWordTextView, inputView, skip, check, next)
            skip.update(VisibilityUiState.Gone)
            next.update(VisibilityUiState.Visible)
        }
    }

    object Incorrect : Abstract(
        InputUiState.Incorrect,
        CheckUiState.Disabled,
    )





}

