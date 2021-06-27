package com.wikilift.aprendeasumar.data.model

import android.widget.Toast

data class NumberToShow(
    val number1: Int,
    val number2: Int,
    val fakeSolution: Int,
    val fakeSolution2: Int,
    val acertado: Boolean = false
) {
    var array = ArrayList<Int>()

    fun getChallenge(): Int = number1 + number2


    fun getRandom(): ArrayList<Int> {
        while (array.size < 3) {
            var teste: Int = gettRandom()
            if (!array.contains(teste)) {
                array.add(teste)
            }
        }

        return array

    }

    private fun gettRandom(): Int = (1..3).random()


    fun getRandomButton(lista:Int): String {

        return when (lista) {
            1 -> {
                "$fakeSolution"
            }
            2 -> {
                "$fakeSolution2"
            }

            else -> {
                "${getChallenge()}"
            }
        }


        }


}
