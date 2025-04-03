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

    private fun ActivityScenarioRule<*>.doWithRecreate(block: ()->Unit) {
        block.invoke()
        scenario.recreate()
        block.invoke()
    }

    @Test
    fun caseNumber1() {
        scenarioRule.doWithRecreate(gamePage::assertInitialState)

        gamePage.addInput(text = "Shoot")
        scenarioRule.doWithRecreate(gamePage::assertInsufficientState)

        gamePage.addInput(text = "in")
        scenarioRule.doWithRecreate(gamePage::assertInsufficientState)

        gamePage.addInput(text = "g")
        scenarioRule.doWithRecreate(gamePage::assertSufficientState)

        gamePage.clickCheck()
        scenarioRule.doWithRecreate(gamePage::assertCorrectState)
        gamePage.clickNext()


        gamePage = GamePage(word = "Running".reversed())
        scenarioRule.doWithRecreate(gamePage::assertInitialState)

    }

    @Test
    fun caseNumber2(){
        scenarioRule.doWithRecreate(gamePage::assertInitialState)

        gamePage.clickSkip()

        gamePage = GamePage(word = "Running".reversed())
        scenarioRule.doWithRecreate(gamePage::assertInitialState)

        gamePage.addInput("R")
        scenarioRule.doWithRecreate(gamePage::assertInsufficientState)

        gamePage.addInput("unnin")
        scenarioRule.doWithRecreate(gamePage::assertInsufficientState)

        gamePage.addInput("e")
        scenarioRule.doWithRecreate(gamePage::assertSufficientState)
        gamePage.clickCheck()
        scenarioRule.doWithRecreate(gamePage::assertIncorrectState)

        gamePage.removeInputLastLetter()

        gamePage.addInput("g")

        gamePage.clickCheck()
        scenarioRule.doWithRecreate(gamePage::assertCorrectState)
        gamePage.clickNext()

        gamePage = GamePage(word = "Flying".reversed())
        scenarioRule.doWithRecreate(gamePage::assertInitialState)

        gamePage.clickSkip()
        gamePage = GamePage(word = "Jumping".reversed())
        scenarioRule.doWithRecreate(gamePage::assertInitialState)

        gamePage.addInput("j")
        scenarioRule.doWithRecreate(gamePage::assertInsufficientState)

        gamePage.addInput("u")
        scenarioRule.doWithRecreate(gamePage::assertInsufficientState)

        gamePage.addInput("m")
        scenarioRule.doWithRecreate(gamePage::assertInsufficientState)

        gamePage.addInput("p")
        scenarioRule.doWithRecreate(gamePage::assertInsufficientState)

        gamePage.addInput("i")
        scenarioRule.doWithRecreate(gamePage::assertInsufficientState)

        gamePage.addInput("n")
        scenarioRule.doWithRecreate(gamePage::assertInsufficientState)

        gamePage.addInput("e")
        scenarioRule.doWithRecreate(gamePage::assertSufficientState)

        gamePage.removeInputLastLetter()

        gamePage.addInput("e")
        scenarioRule.doWithRecreate(gamePage::assertSufficientState)
        gamePage.clickCheck()
        scenarioRule.doWithRecreate(gamePage::assertIncorrectState)

        gamePage.removeInputLastLetter()

        gamePage.addInput("d")
        scenarioRule.doWithRecreate(gamePage::assertSufficientState)
        gamePage.clickCheck()
        scenarioRule.doWithRecreate(gamePage::assertIncorrectState)

        gamePage.removeInputLastLetter()

        gamePage.addInput("g")
        gamePage.clickCheck()
        scenarioRule.doWithRecreate(gamePage::assertCorrectState)
        gamePage.clickNext()

        gamePage = GamePage(word = "Shooting".reversed())
        scenarioRule.doWithRecreate(gamePage::assertInitialState)


    }

    @Test
    fun caseNumber3(){


        scenarioRule.doWithRecreate(gamePage::assertInitialState)

        gamePage = GamePage(word = "Shooting".reversed())
        scenarioRule.doWithRecreate(gamePage::assertInitialState)

        gamePage.addInput(text = "qwertyio")
        gamePage.clickCheck()
        scenarioRule.doWithRecreate(gamePage::assertIncorrectState)

        gamePage.clickSkip()

        gamePage = GamePage(word = "Running".reversed())
        scenarioRule.doWithRecreate(gamePage::assertInitialState)
        gamePage.addInput(text = "asdfasd")
        gamePage.clickCheck()
        scenarioRule.doWithRecreate(gamePage::assertIncorrectState)


        gamePage.clickNext()
        gamePage.assertNotVisible()

        val gameOverPage = GameOverPage(incorrects = 2, corrects = 0,)
        gameOverPage.assertInitialState()
        scenarioRule.scenario.recreate()
        gameOverPage.assertInitialState()

        gameOverPage.clickNewGame()
        gameOverPage.assertNotVisible()

        gamePage = GamePage(word = "Shooting".reversed())
        scenarioRule.doWithRecreate(gamePage::assertInitialState)

        gamePage.addInput(text = "Shooting")
        gamePage.clickCheck()
        scenarioRule.doWithRecreate(gamePage::assertCorrectState)

        gamePage.clickNext()

        gamePage = GamePage(word = "Running".reversed())
        scenarioRule.doWithRecreate(gamePage::assertInitialState)
        gamePage.addInput(text = "asdfasd")
        gamePage.clickCheck()
        scenarioRule.doWithRecreate(gamePage::assertIncorrectState)

        gamePage.clickSkip()
        gameOverPage.assertNotVisible()

        gameOverPage = GameOverPage(incorrects = 1, corrects = 1,)
        gameOverPage.assertInitialState()
        scenarioRule.scenario.recreate()
        gameOverPage.assertInitialState()


        gameOverPage.clickNewGame()
        gameOverPage.assertNotVisible()

        gamePage = GamePage(word = "Shooting".reversed())
        scenarioRule.doWithRecreate(gamePage::assertInitialState)

        gamePage.addInput(text = "Shooting")
        gamePage.clickCheck()
        scenarioRule.doWithRecreate(gamePage::assertCorrectState)

        gamePage.clickNext()

        gamePage = GamePage(word = "Running".reversed())
        scenarioRule.doWithRecreate(gamePage::assertInitialState)
        gamePage.addInput(text = "Running")
        gamePage.clickCheck()
        scenarioRule.doWithRecreate(gamePage::assertCorrectState)

        gamePage.clickNext()
        val gameOverPage = GameOverPage(incorrects = 0, corrects = 2,)
        gameOverPage.assertInitialState()
        scenarioRule.scenario.recreate()
        gameOverPage.assertInitialState()

    }





}