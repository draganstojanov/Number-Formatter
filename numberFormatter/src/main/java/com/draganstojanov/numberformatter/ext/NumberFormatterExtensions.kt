package com.draganstojanov.numberformatter.ext

import android.util.Log
import com.draganstojanov.numberformatter.Formatter.formatter
import com.draganstojanov.numberformatter.util.DecimalsMode

fun Int.addSingleLeadingZero(): String {
    return if (this in 0..9) {
        "0${this}".takeLast(2)
    } else {
        Log.e("NumberFormatter", "addSingleLeadingZero - number must be 0 <= number <= 9")
        this.toString()
    }
}

/**
 * [digits] = no of digits in integer part of number
 *
 * values : 1 <= digits <= 8
 *
 */
fun Number.addLeadingZeros(digits: Int): String = formatter(this, digits = digits)


/**
 * [decimalsDisplayMode] = way we display decimals
 *
 * values:
 *
 * DEFAULT
 *
 * ALWAYS_INCLUDING_INTEGERS
 *
 * IF_CONTAINS
 *
 */
fun Number.decimalsDisplayMode(decimalsMode: DecimalsMode): String = formatter(this, decimalsMode = decimalsMode)

/**
 * [showIntIfZero] :Boolean
 */
fun Number.showIntegerPartIfZero(showIntIfZero: Boolean): String = formatter(this, showIntIfZero = showIntIfZero)

/**
 * [maxDecimals] = max no of decimals shown
 *
 * values : 1 <= digits <= 8
 *
 * [addZerosAtEnd] :Boolean
 *
 */
fun Number.maxDecimals(maxDecimals: Int, addZerosAtEnd: Boolean = false): String =
    formatter(this, maxDecimals = maxDecimals, addZerosAtEnd = addZerosAtEnd)



