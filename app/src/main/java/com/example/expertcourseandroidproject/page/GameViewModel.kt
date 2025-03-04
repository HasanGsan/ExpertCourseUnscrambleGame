package com.example.expertcourseandroidproject.page

import android.util.Log

class GameViewModel(private val repository: GameRepository) {

    fun init(): GameUiState {
        val shuffleWord = repository.shuffledWord()
        return GameUiState.Initial(shuffleWord, repository.userInput())
    }

    fun handleUserInput(text: String): GameUiState {
        repository.saveUserInput(text)
        val shuffleWord = repository.shuffledWord()
        val isSufficient = text.length == shuffleWord.length

        return if (isSufficient){
            GameUiState.Sufficient(shuffleWord)
        }
        else{
            GameUiState.Insufficient(shuffleWord)
        }
    }

    fun check(text: String): GameUiState {
        val shuffleWord = repository.shuffledWord()
        val originalWord = repository.originalWord()
        val isCorrect = originalWord.equals(text, ignoreCase = true)

        return if(isCorrect){
            GameUiState.Correct(shuffleWord)
        }
        else{
            GameUiState.Incorrect(shuffleWord)
        } 
    }

    fun next(): GameUiState {
        repository.next()
        return init()

    }

    fun skip(): GameUiState {
        repository.next()
        return init()
    }


}
