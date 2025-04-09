package com.example.expertcourseandroidproject.game

interface GameRepository {

    fun shuffledWord() : String
    fun originalWord() : String
    fun next()
    fun saveUserInput(value: String)
    fun userInput() : String

    class Base(
        private val index: IntCache,
        private val userInput: StringCache,
        private val shuffleStrategyBase: ShuffleStrategy = ShuffleStrategy.Base(),
        private val list: List<String> = listOf(
            "Shooting", "Running", "Flying", "Jumping"
        )
    ) : GameRepository {

        private val shuffledList = list.map { shuffleStrategyBase.shuffle(it) }

//        override fun shuffledWord() : String = shuffledList[index.read()]
override fun shuffledWord(): String = shuffleStrategyBase.shuffle(list[index.read()])


        override fun originalWord() : String = list[index.read()]

        override fun next(){
            index.save(index.read() + 1)
            if(index.read() == list.size){
                index.save(0)
            }
            userInput.save("")
        }


        override fun saveUserInput(value: String) {
            userInput.save(value)
        }

        override fun userInput(): String {
            return userInput.read()
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