package com.example.expertcourseandroidproject.main

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.example.expertcourseandroidproject.page.UnscrambleApp


class MainActivity : AppCompatActivity() {

    private lateinit var uiState: GameUiState
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: GameViewModel

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

        override fun afterTextChanged(p0: Editable?) {
            uiState = viewModel.handleUserInput(text = p0.toString())
            uiState.update(binding = binding)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)



        ViewCompat.setOnApplyWindowInsetsListener(binding.constraintLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = (application as UnscrambleApp).viewModel



        binding.skipButton.setOnClickListener {
            uiState = viewModel.skip()
            binding.inputText.setText("")
            uiState.update(binding = binding)
        }

        binding.checkCorrectString.setOnClickListener{
            uiState = viewModel.check(text = binding.inputText.text.toString())
            uiState.update(binding = binding)
        }

        binding.nextButton.setOnClickListener{
            uiState = viewModel.next()
            binding.inputText.setText("")
            uiState.update(binding = binding)
        }

        uiState = if(savedInstanceState == null)
            viewModel.init()
        else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                savedInstanceState.getSerializable(KEY, GameUiState::class.java) as GameUiState
             else
                savedInstanceState.getSerializable(KEY) as GameUiState
        }
        uiState.update(binding = binding)



    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, uiState)
    }

    override fun onResume() {
        super.onResume()
        binding.inputText.addTextChangedListener(textWatcher)
    }

    override fun onPause() {
        super.onPause()
        binding.inputText.removeTextChangedListener(textWatcher)
    }

    companion object {
        private const val KEY = "uiState"
    }

}
