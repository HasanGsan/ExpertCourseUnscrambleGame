package com.example.expertcourseandroidproject.page

import android.util.Log

class GameViewModel(private val repository: GameRepository) {

    fun init(): GameUiState {
        return GameUiState.Initial(repository.shuffledWord())
    }

    fun pullOut(text: String): GameUiState {

        return if(text.length == repository.shuffledWord().length){
           GameUiState.Sufficient(repository.shuffledWord())
        }
        else{
            GameUiState.Insufficient(repository.shuffledWord())
        }

    }

    fun check(text: String): GameUiState {
        val isCorrect = text.toString().trim().equals(repository.originalWord().trim(), ignoreCase = true)
//        Log.d("CheckUiState", isCorrect.toString())
//        Log.d("CheckUiState", "isCorrect = $isCorrect, text = '${text.toString()}', original = '${repository.originalWord()}'")
        return if(isCorrect){
            GameUiState.Correct(repository.shuffledWord())
        }
        else{
            GameUiState.Incorrect(repository.shuffledWord())
        }
    }

    fun next(): GameUiState {
        repository.next()
        return GameUiState.Initial(repository.shuffledWord())

    }

    fun skip(): GameUiState {
        repository.next()
        return GameUiState.Initial(repository.shuffledWord())
    }


}
