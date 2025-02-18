package com.example.expertcourseandroidproject

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.expertcourseandroidproject.game.GamePage
import com.example.expertcourseandroidproject.main.MainActivity
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class OneScenarioTest {

    @get:Rule
    val scenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var gamePage: GamePage

    @Before
    fun setup(){
        gamePage = GamePage(word = "Стрелять".reversed())
    }

    @Test
    fun caseNumber1() {
//        gamePage.assertInitialState()
//
//        gamePage.addInput(text = "Стрелят")
//        gamePage.assertInsufficientState()
//
//        gamePage.addInput(text = "ь")
//        gamePage.assertSufficientState()
//
//        gamePage.clickCheck()
//        gamePage.assertCorrectState()
//
//        gamePage.clickNext()
//
//        gamePage = GamePage(word = "Прыгать")
//        gamePage.assertInitialState()
        gamePage.assertInitialState()

        gamePage.addInput(text = "Стрелятб")
        gamePage.assertSufficientState()

        gamePage.addInput(text = "ь")
        gamePage.assertInsufficientState()

        gamePage.addInput(text = "Стрелять")

        gamePage.clickCheck()
        gamePage.addInput(text = "")

        gamePage = GamePage(word = "Бежать".reversed())
        gamePage.assertInitialState()

    }

    @Test
    fun caseNumber2(){
        gamePage.assertInitialState()

        gamePage.clickSkip()
        gamePage = GamePage(word = "Бежать".reversed())
        gamePage.assertInitialState()

        gamePage.addInput(text = "Бежать")
        gamePage.assertInsufficientState()

        gamePage.clickSkip()
        gamePage = GamePage(word = "Прыгать".reversed())
        gamePage.assertInitialState()

        gamePage.addInput(text = "Анекдот")
        gamePage.assertInsufficientState()

        gamePage.addInput(text = "а")
        gamePage.assertSufficientState()

        gamePage.clickSkip()
        gamePage = GamePage(word = "Летать".reversed())
        gamePage.assertInitialState()

        gamePage.addInput(text = "Летать")
        gamePage.assertInsufficientState()

        gamePage.addInput(text = "е")
        gamePage.assertSufficientState()

        gamePage.clickCheck()
        gamePage.assertIncorrectState()

        gamePage.clickSkip()
        gamePage = GamePage(word = "Прыгать".reversed())
        gamePage.assertInitialState()

        gamePage.addInput(text = "прыг")
        gamePage.assertInsufficientState()

        gamePage.addInput(text = "е")
        gamePage.assertSufficientState()

        gamePage.clickCheck()
        gamePage.assertIncorrectState()

        gamePage.removeInputLastLetter()
        gamePage.assertInsufficientState()

        gamePage.addInput(text = "л")
        gamePage.assertSufficientState()

        gamePage.removeInputLastLetter()
        gamePage.assertInsufficientState()

        gamePage.addInput(text = "е")
        gamePage.assertSufficientState()

        gamePage.clickCheck()
        gamePage.assertIncorrectState()

    }

    @Test
    fun caseNumber3(){

    }



}