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

        gamePage = GamePage(word = "Running".reversed())
        gamePage.assertInitialState()

        gamePage.addInput("R")
        gamePage.assertInsufficientState()

        gamePage.addInput("unnin")
        gamePage.assertInsufficientState()

        gamePage.addInput("e")
        gamePage.assertSufficientState()
        gamePage.clickCheck()
        gamePage.assertIncorrectState()

        gamePage.removeInputLastLetter()
        gamePage.addInput("g")

        gamePage.clickCheck()
        gamePage.assertCorrectState()
        gamePage.clickNext()

        gamePage = GamePage(word = "Flying".reversed())
        gamePage.assertInitialState()

        gamePage.clickSkip()
        gamePage = GamePage(word = "Jumping".reversed())
        gamePage.assertInitialState()

        gamePage.addInput("j")
        gamePage.assertInsufficientState()

        gamePage.addInput("u")
        gamePage.assertInsufficientState()

        gamePage.addInput("m")
        gamePage.assertInsufficientState()

        gamePage.addInput("p")
        gamePage.assertInsufficientState()

        gamePage.addInput("i")
        gamePage.assertInsufficientState()

        gamePage.addInput("n")
        gamePage.assertInsufficientState()

        gamePage.addInput("e")
        gamePage.assertSufficientState()

        gamePage.removeInputLastLetter()

        gamePage.addInput("e")
        gamePage.assertSufficientState()
        gamePage.clickCheck()
        gamePage.assertIncorrectState()

        gamePage.removeInputLastLetter()

        gamePage.addInput("d")
        gamePage.assertSufficientState()
        gamePage.clickCheck()
        gamePage.assertIncorrectState()

        gamePage.removeInputLastLetter()

        gamePage.addInput("g")
        gamePage.clickCheck()
        gamePage.assertCorrectState()
        gamePage.clickNext()

        gamePage = GamePage(word = "Shooting".reversed())
        gamePage.assertInitialState()


    }

//    @Test
//    fun caseNumber3(){
//
//    }



}