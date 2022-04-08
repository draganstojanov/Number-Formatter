package com.draganstojanov.numberformatter

import com.draganstojanov.numberformatter.util.ShowDecimals


class NumberFormatter(
    val leadingZeros: Int = 0,
    val showDecimals: ShowDecimals = ShowDecimals.DEFAULT,
    val showIntIfZero: Boolean = true,
    val fixedDecimals: Int = 0,
    val maxDecimals: Int = 0
) {

    fun formatted(number: Number): String = Formatter.formatter(
        number = number,
        leadingZeros = leadingZeros,
        showDecimals = showDecimals,
        showIntIfZero = showIntIfZero,
        fixedDecimals = fixedDecimals,
        maxDecimals = maxDecimals
    )

}