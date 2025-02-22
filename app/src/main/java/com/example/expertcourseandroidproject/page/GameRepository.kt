package com.example.expertcourseandroidproject.page

import kotlin.streams.toList

interface GameRepository {

    fun shuffledWord() : String
    fun originalWord() : String
    fun next()

    class Base(
        private val shuffleStrategyBase: ShuffleStrategy = ShuffleStrategy.Reverse(),
        private val list: List<String> = listOf(
            "Shooting", "Running", "Flying", "Jumping"
        )
    ) : GameRepository {

        private val shuffledList = list.map { shuffleStrategyBase.shuffle(it) }

        private var index = 0

        override fun shuffledWord() : String = shuffledList[index]

        override fun originalWord() : String = list[index]

        override fun next(){
            index++
            if(index == list.size){
                index = 0
            }
        }

    }

}

interface ShuffleStrategy {
    fun shuffle(source: String) : String

    class Base : ShuffleStrategy {
        override fun shuffle(source: String): String {
            return source.toList().joinToString("")

        }
    }

    class Reverse : ShuffleStrategy {
        override fun shuffle(source: String): String {
            return source.reversed()
        }
    }
}