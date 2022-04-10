package com.draganstojanov.numberformatter.ext

import com.draganstojanov.numberformatter.Formatter.formatter
import com.draganstojanov.numberformatter.util.ShowDecimals


fun Int.addSingleLeadingZero(): String = if (this.toString().length <= 2) "0${this}".takeLast(2) else this.toString()

fun Number.addLeadingZeros(leadingZeros: Int): String = formatter(this, leadingZeros = leadingZeros)

fun Number.showDecimals(showDecimals: ShowDecimals): String = formatter(this, showDecimals = showDecimals)

fun Number.showIntegerPartIfZero(showIntIfZero: Boolean): String = formatter(this, showIntIfZero = showIntIfZero)

fun Number.fixedNumberOfDecimals(decimals: Int): String = formatter(this, fixedDecimals = decimals, calculateFixedDecimals = decimals > 0)

fun Number.maxNumberOfDecimals(decimals: Int): String = formatter(this, maxDecimals = decimals, calculateMaxDecimals = decimals > 0)



