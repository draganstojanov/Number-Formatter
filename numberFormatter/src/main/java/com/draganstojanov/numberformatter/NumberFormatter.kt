package com.draganstojanov.numberformatter

import com.draganstojanov.numberformatter.util.DecimalsMode


class NumberFormatter(
    var digits: Int = 0,
    var decimalsMode: DecimalsMode = DecimalsMode.DEFAULT,
    var showIntIfZero: Boolean = true,
    var maxDecimals: Int = 0,
    var addZerosAtEnd: Boolean = false
) {

    fun getFormatted(number: Number?): String = Formatter.formatter(
        number = number,
        digits = digits,
        decimalsMode = decimalsMode,
        showIntIfZero = showIntIfZero,
        maxDecimals = maxDecimals,
        addZerosAtEnd = addZerosAtEnd
    )

}