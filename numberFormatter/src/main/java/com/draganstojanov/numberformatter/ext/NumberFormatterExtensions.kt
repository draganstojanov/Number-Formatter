package com.draganstojanov.numberformatter.ext

import com.draganstojanov.numberformatter.Formatter.formatter
import com.draganstojanov.numberformatter.util.ShowDecimals


fun Int.addSingleLeadingZero(): String = if (this.toString().length <= 2) "0${this}".takeLast(2) else this.toString()

fun Number.addLeadingZeros(leadingZeros: Int): String = formatter(this, leadingZeros = leadingZeros)

fun Number.showDecimals(showDecimals: ShowDecimals): String = formatter(this, showDecimals = showDecimals)

fun Number.showIntegerPartIfZero(showIntIfZero: Boolean): String = formatter(this, showIntIfZero = showIntIfZero)

fun Number.maxDecimals(decimals: Int, addZerosAtEnd: Boolean = false): String = formatter(this, maxDecimals = decimals, addZerosAtEnd = addZerosAtEnd)



