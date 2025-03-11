package com.example.expertcourseandroidproject.views

import android.util.Log

class GameViewModel(private val repository: GameRepository) {

    fun next(): GameUiState {
        repository.next()
        return init()

    }

    fun check(text: String): GameUiState {
        val originalWord = repository.originalWord()
        val isCorrect = originalWord.equals(text, ignoreCase = true)

        return if(isCorrect){
            GameUiState.Correct
        }
        else{
            GameUiState.Incorrect
        }
    }

    fun skip(): GameUiState {
        repository.next()
        return init()
    }


    fun handleUserInput(text: String): GameUiState {
        repository.saveUserInput(text)
        val shuffleWord = repository.shuffledWord()
        val isSufficient = text.length == shuffleWord.length

        return if (isSufficient){
            GameUiState.Sufficient
        }
        else{
            GameUiState.Insufficient
        }
    }


    fun init(): GameUiState {
            val shuffleWord = repository.shuffledWord()
            return GameUiState.Initial(shuffleWord, repository.userInput())
    }




}
