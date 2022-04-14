package com.draganstojanov.numberformatter

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.draganstojanov.numberformatter.databinding.ActivityMainBinding
import com.draganstojanov.numberformatter.ext.*
import com.draganstojanov.numberformatter.util.DecimalsMode

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var numberFormatter: NumberFormatter

    private var number: Number? = 0
    var mDigits: Int = 0
    var mDecimalsMode: DecimalsMode = DecimalsMode.DEFAULT
    var mShowIntIfZero: Boolean = true
    var mMaxDecimals: Int = 0
    var mAddZerosAtEnd: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }

    private fun setup() {

        setInitialValues()

        with(binding.input) {
            doOnTextChanged { text, _, _, _ ->

                val txt = if (text.toString() == ".") ".0" else text.toString()
                number = if (txt.isNotEmpty())
                    try {
                        txt.toInt()
                    } catch (e: NumberFormatException) {
                        txt.toFloat()
                    } else 0
                runFormatter()
            }
        }

        val numbersAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8)
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        with(binding.zerosSpinner) {
            adapter = numbersAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    mDigits = p2
                    runFormatter()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    mDigits = 0
                    runFormatter()
                }
            }
        }

        with(binding.showSpinner) {
            adapter = ArrayAdapter(
                this@MainActivity,
                android.R.layout.simple_spinner_item,
                arrayOf("DEFAULT", "ALWAYS", "ALWAYS_INCLUDING_INTEGERS", "IF_CONTAINS")
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    mDecimalsMode = DecimalsMode.values()[p2]
                    runFormatter()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    mDecimalsMode = DecimalsMode.DEFAULT
                    runFormatter()
                }
            }
        }

        binding.showIntCheckbox.setOnCheckedChangeListener { _, state ->
            mShowIntIfZero = state
            runFormatter()
        }

        with(binding.maxSpinner) {
            adapter = numbersAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    mMaxDecimals = p2
                    runFormatter()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    mMaxDecimals = 0
                    runFormatter()
                }
            }
        }

        binding.maxZerosCheckbox.setOnCheckedChangeListener { _, state ->
            mAddZerosAtEnd = state
            runFormatter()
        }

        binding.resetBtn.setOnClickListener { setInitialValues() }

    }

    private fun runFormatter() {

        numberFormatter.apply {
            digits = mDigits
            decimalsMode = mDecimalsMode
            showIntIfZero = mShowIntIfZero
            maxDecimals = mMaxDecimals
            addZerosAtEnd = mAddZerosAtEnd
        }

        binding.zerosFormattedNumber.text = number?.addLeadingZeros(mDigits)
        binding.showFormattedNumber.text = number?.decimalsDisplayMode(mDecimalsMode)
        binding.showIntFormattedNumber.text = number?.showIntegerPartIfZero(mShowIntIfZero)
        binding.maxFormattedNumber.text = number?.maxDecimals(mMaxDecimals, mAddZerosAtEnd)


        if (number is Int)
            binding.singleZeroFormattedNumber.text = (number as Int).addSingleLeadingZero()
        else binding.singleZeroFormattedNumber.text = ""

        binding.formattedNumber.text = numberFormatter.getFormatted(number)
    }

    private fun setInitialValues() {

        hideKeyboard()

        mDigits = 0
        mDecimalsMode = DecimalsMode.DEFAULT
        mShowIntIfZero = true
        mMaxDecimals = 0
        mAddZerosAtEnd = false

        numberFormatter = NumberFormatter()

        with(binding.input) {
            requestFocus()
            setText("")
        }
        binding.zerosSpinner.setSelection(0)
        binding.showSpinner.setSelection(0)
        binding.showIntCheckbox.isChecked = true
        binding.maxSpinner.setSelection(0)
        binding.maxZerosCheckbox.isChecked = false

        runFormatter()
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(window.decorView.windowToken, 0)
    }
}