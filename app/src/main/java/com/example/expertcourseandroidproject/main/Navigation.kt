package com.example.expertcourseandroidproject.main

import com.example.expertcourseandroidproject.game.GameScreen
import com.example.expertcourseandroidproject.game.NavigateToGame
import com.example.expertcourseandroidproject.views.stats.NavigateToStats
import com.example.expertcourseandroidproject.views.stats.StatsScreen

interface Navigation : NavigateToGame, NavigateToStats {

    fun navigate(screen: Screen)

    override fun navigateToGame() = navigate(GameScreen)

    override fun navigateToStats() = navigate(StatsScreen)

}