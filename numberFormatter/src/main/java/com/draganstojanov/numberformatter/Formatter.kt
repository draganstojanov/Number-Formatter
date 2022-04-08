package com.draganstojanov.numberformatter

import com.draganstojanov.numberformatter.util.NO_DECIMAL_VALUE
import com.draganstojanov.numberformatter.util.ShowDecimals
import kotlin.math.pow
import kotlin.math.roundToInt

internal object Formatter {

 internal   fun formatter(
     number: Number?,
     leadingZeros: Int = 0,
     showDecimals: ShowDecimals = ShowDecimals.DEFAULT,
     showIntIfZero: Boolean = true,
     fixedDecimals: Int = 0,
     maxDecimals: Int = 0
    ): String {

        val intPart = number?.toInt()
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

        if (leadingZeros > 1) {// addLeadingZeros
            if (intString.isNotEmpty()) {
                if (intPart.toString().length <= leadingZeros) {
                    intString = "${stringOfZeros(leadingZeros)}${intString}".takeLast(leadingZeros)
                }
            }


        }

//    if (leadingZeros > 1) {// addLeadingZeros
//        if (intPart.toString().length > zeros && intString.isNotEmpty()) {
//            throw StringIndexOutOfBoundsException("Integer part of number has more figures than number of zeros")//todo ???
//        }
//        intString = "${stringOfZeros(leadingZeros)}${intString}".takeLast(leadingZeros)
//
//    }

        return "${intString}${decString}"
    }

    private fun stringOfZeros(leadingZeros: Int): String {
        var z = ""
        for (i in 1..leadingZeros) {
            z += "0"
        }
        return z
    }
}