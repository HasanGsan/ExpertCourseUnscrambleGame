package com.example.expertcourseandroidproject.page

import android.util.Log

class GameViewModel(private val repository: GameRepository) {

    fun init(): GameUiState {
        return GameUiState.Initial(repository.shuffledWord())
    }

    fun pullOut(text: String): GameUiState {

        return if(text.isEmpty()){
            GameUiState.Correct(repository.shuffledWord())
        }
        else{
            GameUiState.Insufficient(repository.shuffledWord())
        }

    }

    fun check(text: Any): GameUiState {
        val isCorrect = text.toString().trim().equals(repository.originalWord().trim(), ignoreCase = true)
        Log.d("CheckUiState", isCorrect.toString())
        Log.d("CheckUiState", "isCorrect = $isCorrect, text = '${text.toString()}', original = '${repository.originalWord()}'")
        return if(isCorrect){
            GameUiState.Correct(repository.shuffledWord())
            next()
        }
        else{
            GameUiState.Incorrect(repository.shuffledWord())
        }
    }

    fun next(): GameUiState {
        repository.next()
        return GameUiState.Sufficient(repository.shuffledWord())

    }

    fun skip(): GameUiState {
        repository.next()
        return GameUiState.Insufficient(repository.shuffledWord())
    }


}
