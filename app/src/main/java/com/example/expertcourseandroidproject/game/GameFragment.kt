package com.example.expertcourseandroidproject.game

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.Fragment
import com.example.expertcourseandroidproject.databinding.ActivityMainBinding
import com.example.expertcourseandroidproject.views.UnscrambleApp

class GameFragment : Fragment() {

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


        binding = ActivityMainBinding.inflate(layoutInflater)

        requireActivity().setContentView(binding.root) // todo oncreate view

        viewModel = (requireActivity().application as UnscrambleApp).viewModel

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

//        uiState = if(savedInstanceState == null)
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