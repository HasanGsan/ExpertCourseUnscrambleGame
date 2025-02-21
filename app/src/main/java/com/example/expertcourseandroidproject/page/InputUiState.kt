package com.example.expertcourseandroidproject.page

import com.example.expertcourseandroidproject.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.io.Serializable

interface   InputUiState {

    fun update(inputLayout: TextInputLayout, inputEditText: TextInputEditText)

    abstract class Abstract(
        private val errorIsVisible: Boolean,
        private val enabled: Boolean,
        private val clearText: Boolean,
    ) : InputUiState {

        override fun update(inputLayout: TextInputLayout, inputEditText: TextInputEditText) {
            inputLayout.isErrorEnabled = errorIsVisible
            if(errorIsVisible)
                inputLayout.error = inputLayout.context.getString(R.string.incorrect_message)
            inputLayout.isEnabled = enabled
            if(clearText)
                inputEditText.setText(R.string.empty)
        }

    }

    object Initial : Abstract(false, true, true)
    object Sufficient : Abstract(false, true, false)
    object Insufficient : Abstract(false, true, false)
    object Correct : Abstract(false, false, false) //Поставил тут true во втором параметре потому что inputEditText отключался после очистки
    object Incorrect : Abstract(true, true, false)
}

