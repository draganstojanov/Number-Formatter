package com.draganstojanov.numberformatter

import com.draganstojanov.numberformatter.util.ShowDecimals
import kotlin.math.pow
import kotlin.math.roundToInt

internal object Formatter {

    private const val NO_DECIMAL_VALUE = -1
    private const val ZEROS_STRING = "00000000"

    internal fun formatter(
        number: Number?,
        leadingZeros: Int = 0,
        showDecimals: ShowDecimals = ShowDecimals.DEFAULT,
        showIntIfZero: Boolean = true,
        maxDecimals: Int = 0,
        addZerosAtEnd: Boolean = false
    ): String {

        val intPart = number?.toInt()
        var decPart = if (number.toString().contains(".")) number.toString().substringAfter(".").toInt() else NO_DECIMAL_VALUE

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


        if (maxDecimals > 0 && decString.isNotEmpty()) {//maxDecimals

            decPart = if (decPart == NO_DECIMAL_VALUE) 0 else decPart

            var pow = 10.0.pow(decPart.toString().length - maxDecimals)
            if (!addZerosAtEnd && pow < 1) {
                pow = 1.0
            }

            decString = if (decPart == 0) {
                ".${ZEROS_STRING}".take(maxDecimals + 1)
            } else ".${(decPart / pow).roundToInt()}".take(maxDecimals + 1)

        }

        if (!showIntIfZero && decString.isNotEmpty()) {//showIntegerPartIfZero
            if (intPart == 0) {
                intString = ""
            }
        }

        if (leadingZeros > 1) {// addLeadingZeros
            if (intString.isNotEmpty()) {
                if (intPart.toString().length <= leadingZeros) {
                    intString = "${ZEROS_STRING}${intString}".takeLast(leadingZeros)
                }
            }
        }




        return "${intString}${decString}"
    }

}