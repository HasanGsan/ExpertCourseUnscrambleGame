package com.example.expertcourseandroidproject.main

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.expertcourseandroidproject.databinding.ActivityMainBinding
import com.example.expertcourseandroidproject.views.GameUiState
import com.example.expertcourseandroidproject.views.GameViewModel
import com.example.expertcourseandroidproject.views.UnscrambleApp


class MainActivity : AppCompatActivity() {

    private lateinit var uiState: GameUiState
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: GameViewModel

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

        override fun afterTextChanged(p0: Editable?) {
            uiState = viewModel.handleUserInput(text = p0.toString())
            update.invoke()
        }

    }

    private val update : () -> Unit = {
        uiState.update(
            binding.shuffledWordTextView,
            binding.inputView,
            binding.skipButton,
            binding.checkCorrectString,
            binding.nextButton,
        )
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
            update.invoke()

        }

        binding.checkCorrectString.setOnClickListener{
            uiState = viewModel.check(text = binding.inputView.text())
            update.invoke()
        }

        binding.nextButton.setOnClickListener{
            uiState = viewModel.next()
            update.invoke()
        }

//        uiState = if(savedInstanceState == null) Эта запись у меня не работает почему-то
//            viewModel.init()
//        else
//            GameUiState.Empty

        uiState = viewModel.init()



        update.invoke()


    }

    override fun onResume() {
        super.onResume()
        binding.inputView.addTextChangedListener(textWatcher)
    }

    override fun onPause() {
        super.onPause()
        binding.inputView.removeTextChangedListener(textWatcher)
    }


}
