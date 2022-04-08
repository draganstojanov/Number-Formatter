package com.draganstojanov.numberformatter

import com.draganstojanov.numberformatter.util.ShowDecimals


class NumberFormatter(
    var leadingZeros: Int = 0,
    var showDecimals: ShowDecimals = ShowDecimals.DEFAULT,
    var showIntIfZero: Boolean = true,
    var fixedDecimals: Int = 0,
    var maxDecimals: Int = 0
) {

    fun getFormatted(number: Number?): String = Formatter.formatter(
        number = number,
        leadingZeros = leadingZeros,
        showDecimals = showDecimals,
        showIntIfZero = showIntIfZero,
        fixedDecimals = fixedDecimals,
        maxDecimals = maxDecimals
    )

}