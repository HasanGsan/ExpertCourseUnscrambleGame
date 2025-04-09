package com.example.expertcourseandroidproject.views

import android.app.Application
import android.content.Context
import com.example.expertcourseandroidproject.game.GameRepository
import com.example.expertcourseandroidproject.game.GameViewModel
import com.example.expertcourseandroidproject.game.IntCache
import com.example.expertcourseandroidproject.game.ShuffleStrategy
import com.example.expertcourseandroidproject.game.StringCache

class UnscrambleApp : Application() {

     lateinit var viewModel: GameViewModel

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = getSharedPreferences("UnscrambleAppData", Context.MODE_PRIVATE)
        viewModel = GameViewModel(
            GameRepository.Base(
                IntCache.Base(sharedPreferences, "indexKey", 0),
                StringCache.Base(sharedPreferences, "userInputKey", ""),
                ShuffleStrategy.Reverse()
        ))
    }
}