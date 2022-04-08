package com.draganstojanov.numberformatter

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.draganstojanov.numberformatter.databinding.ActivityMainBinding
import com.draganstojanov.numberformatter.ext.addLeadingZeros
import com.draganstojanov.numberformatter.util.ShowDecimals

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private lateinit var numberFormatter: NumberFormatter

    private var number: Number? = 0
    var leadingZeros: Int = 0
//    var showDecimals: ShowDecimals = ShowDecimals.DEFAULT
//    var showIntIfZero: Boolean = true
//    var fixedDecimals: Int = 0
//    var maxDecimals: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setup()
    }

    private fun setup() {

        setInitialValues()

        binding.input.doOnTextChanged { text, _, _, _ ->
            if (!text.isNullOrEmpty()) {
                number = try {
                    text.toString().toInt()
                } catch (e: NumberFormatException) {
                    text.toString().toFloat()
                }
                runFormatter()
            } else {
                binding.input.setText("0")
            }
        }

        binding.zerosInput.doOnTextChanged { text, _, _, _ ->
            leadingZeros = if (!text.isNullOrEmpty()) {
                text.toString().toInt()
            } else {
                0
            }
            runFormatter()
        }


    }

    private fun runFormatter() {
        numberFormatter.leadingZeros = leadingZeros
        binding.zerosFormattedNumber.text = number?.addLeadingZeros(leadingZeros)

        binding.formattedNumber.text = numberFormatter.getFormatted(number)
    }

    private fun setInitialValues() {

        hideKeyboard()

        numberFormatter = NumberFormatter(
            leadingZeros = 0,
            showDecimals = ShowDecimals.DEFAULT,
            showIntIfZero = true,
            fixedDecimals = 0,
            maxDecimals = 0
        )

        binding.input.requestFocus()
        binding.input.setText("0")
        binding.zerosInput.setText("")

        runFormatter()
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(window.decorView.windowToken, 0)
    }
}