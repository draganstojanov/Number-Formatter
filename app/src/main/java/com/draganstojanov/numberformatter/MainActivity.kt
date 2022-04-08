package com.draganstojanov.numberformatter

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.draganstojanov.numberformatter.databinding.ActivityMainBinding
import com.draganstojanov.numberformatter.ext.fixedNumberOfDecimals
import com.draganstojanov.numberformatter.ext.showDecimals
import com.draganstojanov.numberformatter.ext.showIntegerPartIfZero

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setup()
    }

    private fun setup() {

        val  nf= NumberFormatter(leadingZeros = 3)

        Log.d("TEST+CLAZZ", nf.formatted(2.34))


//        Log.d("TEST", 1.addLeadingZeros(2))
//        Log.d("TEST", 1.23.addLeadingZeros(3))
//        Log.d("TEST", 123.addLeadingZeros(4))
//        Log.d("TEST", 12.0.addLeadingZeros(3))
//        Log.d("TEST", 13f.addLeadingZeros(3))
//        Log.d("TEST", 123.5678.addLeadingZeros(6))

        Log.d("TEST", 1.showDecimals(com.draganstojanov.numberformatter.util.ShowDecimals.IF_CONTAINS))
        Log.d("TEST", 2.0.showDecimals(com.draganstojanov.numberformatter.util.ShowDecimals.IF_CONTAINS))
        Log.d("TEST", 3f.showDecimals(com.draganstojanov.numberformatter.util.ShowDecimals.IF_CONTAINS))
        Log.d("TEST", 4.23.showDecimals(com.draganstojanov.numberformatter.util.ShowDecimals.IF_CONTAINS))
        Log.d("TEST", .999.showDecimals(com.draganstojanov.numberformatter.util.ShowDecimals.IF_CONTAINS))
        Log.d("TEST", 0.888.showDecimals(com.draganstojanov.numberformatter.util.ShowDecimals.IF_CONTAINS))

        Log.d("TEST", 1.showIntegerPartIfZero(false))
        Log.d("TEST", 1.showIntegerPartIfZero(true))


        Log.d("TEST", 0.1.showIntegerPartIfZero(false))
        Log.d("TEST", 0.1.showIntegerPartIfZero(true))

        Log.d("TEST", .1.showIntegerPartIfZero(false))
        Log.d("TEST", .1.showIntegerPartIfZero(true))


        Log.d("TEST", 0.1234999.fixedNumberOfDecimals(4))
        Log.d("TEST", 0.123456.fixedNumberOfDecimals(2))
        Log.d("TEST", 0.123.fixedNumberOfDecimals(9))



//        Log.d("TEST", 1.23.alwaysShowDecimals(true))
//        Log.d("TEST", 1.23.alwaysShowDecimals(false))
//
//        Log.d("TEST", 1f.alwaysShowDecimals(true))
//        Log.d("TEST", 1f.alwaysShowDecimals(false))
//
//        Log.d("TEST", 1.alwaysShowDecimalsInclInteger(true))
//        Log.d("TEST", 1.alwaysShowDecimalsInclInteger(false))


        binding.btn.setOnClickListener {
            val n = binding.input.text.toString()
//            val f =n.addLeadingZeros(4)
//            binding.result.text=f



        }

12
    }
}