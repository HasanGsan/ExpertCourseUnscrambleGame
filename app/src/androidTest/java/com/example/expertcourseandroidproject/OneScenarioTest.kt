package com.example.expertcourseandroidproject

import android.util.Log
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
        gamePage = GamePage(word = "Shooting".reversed())
    }

    @Test
    fun caseNumber1() {
        gamePage.assertInitialState()

        gamePage.addInput(text = "Shoot")
        gamePage.assertInsufficientState()

        gamePage.addInput(text = "in")
        gamePage.assertInsufficientState()

        gamePage.addInput(text = "g")
        gamePage.assertSufficientState()

        gamePage.clickCheck()

        gamePage.assertCorrectState()
        gamePage.clickNext()


        gamePage = GamePage(word = "Running".reversed())
        gamePage.assertInitialState()

    }

    @Test
    fun caseNumber2(){
        gamePage.assertInitialState()

        gamePage.clickSkip()

        gamePage = GamePage(word = "Летать".reversed())
        gamePage.assertInitialState()

        gamePage.addInput("Летат")
        gamePage.clickCheck()
        gamePage.assertIncorrectState()
        gamePage.assertInsufficientState()

        gamePage.addInput("Летате")
        gamePage.clickCheck()
        gamePage.assertIncorrectState()
        gamePage.assertSufficientState()

        gamePage.addInput("Летату")

        gamePage.clickCheck()
        gamePage.addInput("")

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

//    @Test
//    fun caseNumber3(){
//
//    }



}