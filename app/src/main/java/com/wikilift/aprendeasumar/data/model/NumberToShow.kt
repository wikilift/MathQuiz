package com.wikilift.aprendeasumar.data.model

data class NumberToShow(
    val number1: Int,
    val number2: Int,
    val fakeSolution: Int,
    val fakeSolution2: Int,
    val acertado: Boolean = false
) {
    private fun getChallenge(): Int = number1 + number2
}
