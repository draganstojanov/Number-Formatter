package com.draganstojanov.numberformatter


internal class NF(var number: Number) {

    private val NO_DECIMAL_VALUE = -1
    private val ZEROS_STRING = "00000000"

    private var intPart: Int? = null
    private var decPart: Int? = null
    private var intString: String? = null
    private var decString: String? = null


    internal fun addLeadingZeros(leadingZeros: Int): String {
        parseToStrings()
        return addLeadingZerosImpl(leadingZeros)
    }

    private fun addLeadingZerosImpl(leadingZeros: Int): String {
        if (intString?.isNotEmpty() == true) {
            if (intPart.toString().length <= leadingZeros) {
                intString = "${ZEROS_STRING}${intString}".takeLast(leadingZeros)
            }
        }
        return "${intString}${decString}"
    }


    private fun parseToStrings() {
        intPart = number?.toInt()
        decPart = if (number.toString().contains(".")) number.toString().substringAfter(".").toInt() else NO_DECIMAL_VALUE
    }


}