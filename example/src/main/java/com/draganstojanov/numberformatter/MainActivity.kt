package com.draganstojanov.numberformatter

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.draganstojanov.numberformatter.databinding.ActivityMainBinding
import com.draganstojanov.numberformatter.ext.*
import com.draganstojanov.numberformatter.util.ShowDecimals

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var numberFormatter: NumberFormatter

    private var number: Number? = 0
    var mLeadingZeros: Int = 0
    var mShowDecimals: ShowDecimals = ShowDecimals.DEFAULT
    var mShowIntIfZero: Boolean = true
    var mFixedDecimals: Int = 0
    var mMaxDecimals: Int = 0

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
                number = if (!text.isNullOrEmpty())
                    try {
                        text.toString().toInt()
                    } catch (e: NumberFormatException) {
                        text.toString().toFloat()
                    } else 0
                runFormatter()
            }
        }

        val numbersAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            arrayOf(0, 1, 2, 3, 4, 5, 6)
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        with(binding.zerosSpinner) {
            adapter = numbersAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    mLeadingZeros = p2
                    runFormatter()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    mLeadingZeros = 0
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
                    mShowDecimals = ShowDecimals.values()[p2]
                    runFormatter()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    mShowDecimals = ShowDecimals.DEFAULT
                    runFormatter()
                }
            }
        }

        binding.showIntSwitch.setOnCheckedChangeListener { _, state ->
            mShowIntIfZero = state
            runFormatter()
        }

        with(binding.fixedSpinner) {
            adapter = numbersAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    mFixedDecimals = p2
                    runFormatter()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    mFixedDecimals = 0
                    runFormatter()
                }
            }
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

        binding.resetBtn.setOnClickListener{ setInitialValues()}

    }

    private fun runFormatter() {

        numberFormatter.apply {
            leadingZeros = mLeadingZeros
            showDecimals = mShowDecimals
            showIntIfZero = mShowIntIfZero
            fixedDecimals = mFixedDecimals
            maxDecimals = mMaxDecimals
        }

        binding.zerosFormattedNumber.text = number?.addLeadingZeros(mLeadingZeros)
        binding.showFormattedNumber.text = number?.showDecimals(mShowDecimals)
        binding.showIntFormattedNumber.text = number?.showIntegerPartIfZero(mShowIntIfZero)
        binding.fixedFormattedNumber.text = number?.fixedNumberOfDecimals(mFixedDecimals)
        binding.maxFormattedNumber.text = number?.maxNumberOfDecimals(mMaxDecimals)

        if (number is Int)
            binding.singleZeroFormattedNumber.text = (number as Int).addSingleLeadingZero()
        else binding.singleZeroFormattedNumber.text = ""

        binding.formattedNumber.text = numberFormatter.getFormatted(number)
    }

    private fun setInitialValues() {

        hideKeyboard()

        mLeadingZeros = 0
        mShowDecimals = ShowDecimals.DEFAULT
        mShowIntIfZero = true
        mFixedDecimals = 0
        mMaxDecimals = 0

        numberFormatter = NumberFormatter(
            leadingZeros = 0,
            showDecimals = ShowDecimals.DEFAULT,
            showIntIfZero = true,
            fixedDecimals = 0,
            maxDecimals = 0
        )

        with(binding.input) {
            requestFocus()
            setText("0")
        }
        binding.zerosSpinner.setSelection(0)
        binding.showSpinner.setSelection(0)
        binding.showIntSwitch.isChecked = true
        binding.fixedSpinner.setSelection(0)
        binding.maxSpinner.setSelection(0)

        runFormatter()
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(window.decorView.windowToken, 0)
    }
}