package com.draganstojanov.numberformatter

import kotlin.math.pow
import kotlin.math.roundToInt

private const val NO_DECIMAL_VALUE = -1

fun Int.addLeadingZero(): String = formatter(this, zeros = this.toString().length + 1)

fun Number.addLeadingZeros(zeros: Int): String = formatter(this, zeros = zeros)

fun Number.showDecimals(showDecimals: ShowDecimals): String = formatter(this, showDecimals = showDecimals)

fun Number.showIntegerPartIfZero(showIntIfZero: Boolean): String = formatter(this, showIntIfZero = showIntIfZero)

fun Number.fixedNumberOfDecimals(decimals: Int): String = formatter(this, fixedDecimals = decimals)

fun Number.maxNumberOfDecimals(decimals: Int): String = formatter(this, maxDecimals = decimals)


@Throws(StringIndexOutOfBoundsException::class)
private fun formatter(
    number: Number,
    zeros: Int = 0,
    showDecimals: ShowDecimals = ShowDecimals.DEFAULT,
    showIntIfZero: Boolean = true,
    fixedDecimals: Int = 0,
    maxDecimals: Int = 0
): String {

    val intPart = number.toInt()
    val decPart = if (number.toString().contains(".")) number.toString().substringAfter(".").toInt() else NO_DECIMAL_VALUE

    var intString = intPart.toString()
    var decString: String = when (showDecimals) {//showDecimals
        ShowDecimals.DEFAULT,
        ShowDecimals.ALWAYS -> {
            if (decPart == NO_DECIMAL_VALUE) "" else ".${decPart}"
        }

        ShowDecimals.ALWAYS_INCLUDING_INTEGERS -> {
            if (decPart == NO_DECIMAL_VALUE) ".0" else ".${decPart}"
        }

        ShowDecimals.IF_CONTAINS -> {
            if (decPart == NO_DECIMAL_VALUE || decPart == 0) "" else ".${decPart}"
        }
    }

//    if (fixedDecimals > 0 && decString.isNotEmpty()) {
//        val pow = decPart / 10.0.pow(decPart.toString().length - fixedDecimals)
//        decString = ".${pow.roundToInt()}${stringOfZeros(fixedDecimals)}".take(fixedDecimals + 1)
//    }

    if ((fixedDecimals > 0 || maxDecimals > 0) && decString.isNotEmpty()) {//fixedNumberOfDecimals,maxNumberOfDecimals
        val pow = decPart / 10.0.pow(decPart.toString().length - fixedDecimals)

        decString = ".${pow.roundToInt()}".take(fixedDecimals + 1)

        if (fixedDecimals > 0) {
            decString = "${decString}${stringOfZeros(fixedDecimals)}".take(fixedDecimals + 1)
        }
    }

    if (!showIntIfZero && decString.isNotEmpty()) {//showIntegerPartIfZero
        if (intPart == 0) {
            intString = ""
        }
    }

    if (zeros > 1) {// addLeadingZeros
        if (intPart.toString().length > zeros && intString.isNotEmpty()) {
            throw StringIndexOutOfBoundsException("Integer part of number has more figures than number of zeros")//todo ???
        }
        intString = "${stringOfZeros(zeros)}${intString}".takeLast(zeros)

    }

    return "${intString}${decString}"
}

private fun stringOfZeros(zeros: Int): String {
    var z = ""
    for (i in 1..zeros) {
        z += "0"
    }
    return z
}

