package com.example.expertcourseandroidproject.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.expertcourseandroidproject.databinding.ActivityMainBinding
import com.example.expertcourseandroidproject.page.GameRepository
import com.example.expertcourseandroidproject.page.GameUiState
import com.example.expertcourseandroidproject.page.GameViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)



        ViewCompat.setOnApplyWindowInsetsListener(binding.constraintLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewModel: GameViewModel = GameViewModel(GameRepository.Base())



        binding.skipButton.setOnClickListener {
            val uiState: GameUiState = viewModel.skip()
            binding.inputText.setText("")
            uiState.update(binding = binding)
        }

        binding.checkCorrectString.setOnClickListener{
            val uiState: GameUiState = viewModel.check(text = binding.inputText.text.toString())
            uiState.update(binding = binding)
        }

        binding.nextButton.setOnClickListener{
            val uiState: GameUiState = viewModel.next()
            binding.inputText.setText("")
            uiState.update(binding = binding)
        }

        binding.inputText.addTextChangedListener {
            val uiState: GameUiState = viewModel.handleUserInput(text = it.toString())

            uiState.update(binding = binding)


        }

        val uiState: GameUiState = viewModel.init()
        uiState.update(binding = binding)



    }
}
