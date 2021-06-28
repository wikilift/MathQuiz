package com.wikilift.aprendeasumar.data.model



data class NumberToShow(
    val number1: Int,
    val number2: Int,
    val fakeSolution: Int,
    val fakeSolution2: Int,
    var operation: Int = 0


) {
    var array = mutableListOf<Int>()

    fun getChallenge(): Int {
        return when (operation) {
            1 -> number1 + number2
            2 -> number1 - number2
            3 -> number1 * number2
            4->number1 / number2
            else -> -1
        }


    }


    fun getRandom(): MutableList<Int> {
        while (array.size < 3) {
            var teste: Int = gettRandom()
            if (!array.contains(teste)) {
                array.add(teste)
            }
        }

        return array

    }

    private fun gettRandom(): Int = (1..3).random()


    fun getRandomButton(lista: Int): String {

        return when (lista) {
            1 -> "$fakeSolution"

            2 -> "$fakeSolution2"


            3 -> "${getChallenge()}"

            else -> "ERROR"
        }


    }


}
