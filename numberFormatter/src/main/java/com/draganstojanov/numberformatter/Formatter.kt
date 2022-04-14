package com.draganstojanov.numberformatter

import android.util.Log
import com.draganstojanov.numberformatter.util.DecimalsMode
import kotlin.math.pow
import kotlin.math.roundToInt

internal object Formatter {

    private const val NO_DECIMAL_VALUE = -1
    private const val ZEROS_STRING = "00000000"

    internal fun formatter(
        number: Number?,
        digits: Int = 0,
        decimalsMode: DecimalsMode = DecimalsMode.DEFAULT,
        showIntIfZero: Boolean = true,
        maxDecimals: Int = 0,
        addZerosAtEnd: Boolean = false
    ): String {
        val intPart = number?.toInt()
        var decPart = if (number.toString().contains(".")) number.toString().substringAfter(".").toInt() else NO_DECIMAL_VALUE


        /*decimalsDisplayMode*/
        var intString = intPart.toString()
        var decString: String = when (decimalsMode) {
            DecimalsMode.DEFAULT,
            DecimalsMode.ALWAYS -> if (decPart == NO_DECIMAL_VALUE) "" else ".${decPart}"
            DecimalsMode.ALWAYS_INCLUDING_INTEGERS -> if (decPart == NO_DECIMAL_VALUE) ".0" else ".${decPart}"
            DecimalsMode.IF_CONTAINS -> if (decPart == NO_DECIMAL_VALUE || decPart == 0) "" else ".${decPart}"
        }


        /*maxDecimals*/
        if (maxDecimals > 0 && decString.isNotEmpty()) {
            if (maxDecimals <= 8) {
                decPart = if (decPart == NO_DECIMAL_VALUE) 0 else decPart

                var pow = 10.0.pow(decPart.toString().length - maxDecimals)
                if (!addZerosAtEnd && pow < 1) {
                    pow = 1.0
                }

                decString = if (decPart == 0) {
                    ".${ZEROS_STRING}".take(maxDecimals + 1)
                } else ".${(decPart / pow).roundToInt()}".take(maxDecimals + 1)
            } else {
                Log.e("NumberFormatter", "maxDecimals - Max no of decimals is 8")
            }
        }


        /*showIntegerPartIfZero*/
        if (!showIntIfZero && decString.isNotEmpty()) {
            if (intPart == 0) {
                intString = ""
            }
        }


        /*addLeadingZeros*/
        if (digits > 1) {
            if (digits <= 8) {
                if (intString.isNotEmpty()) {
                    if (intPart.toString().length <= digits) {
                        intString = "${ZEROS_STRING}${intString}".takeLast(digits)
                    }
                }
            } else {
                Log.e("NumberFormatter", "addLeadingZeros - Max no of digits is 8")
            }
        }

        return "${intString}${decString}"
    }

}