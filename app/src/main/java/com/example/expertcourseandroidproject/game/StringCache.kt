package com.example.expertcourseandroidproject.game

import android.content.SharedPreferences

interface StringCache {

    fun save(newValue: String)

    fun read() : String

    class Base(
        private val sharedPreferences: SharedPreferences,
        private val key: String,
        private val defaultValue: String =  "",
    ) : StringCache {

        override fun read(): String {
            return sharedPreferences.getString(key, defaultValue) ?: defaultValue
        }

        override fun save(newValue: String) {
            sharedPreferences.edit().putString(key, newValue).apply()
        }
    }

}