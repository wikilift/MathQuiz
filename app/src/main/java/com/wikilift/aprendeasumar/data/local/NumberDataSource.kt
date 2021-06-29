package com.wikilift.aprendeasumar.data.local

import com.wikilift.aprendeasumar.MainActivity
import com.wikilift.aprendeasumar.data.model.NumberToShow

class NumberDataSource() {

    private fun getRandomNumber1(): Int {
        val obj = MainActivity.user
        var level = obj!!.level
        return when (level) {
            0 -> {
                return (1..20).random()

            }
            1 -> {
                (1..50).random()

            }
            2 -> {
                (1..80).random()

            }
            3 -> {
                (1..100).random()

            }
            4->{
                (1..150).random()
            }
            5->{
                (1..200).random()
            }
            else->{
                (1..500).random()
            }
        }


    }


    private fun getRandomNumber2(): Int {
        val obj = MainActivity.user
        var level = obj!!.level
        return when (level) {
            0 -> {
                (1..10).random()

            }
            1 -> {
                (1..20).random()

            }
            2 -> {
                (1..50).random()

            }
            3 -> {
                (1..80).random()

            }
            4 -> {
                (1..100).random()

            }
            5 -> {
                (1..1500).random()

            }
            else->{
                (1..250).random()
            }
        }


    }



fun getNumber(): NumberToShow {

    val nm = NumberToShow(getRandomNumber1(), getRandomNumber2(), getRandomNumber1(), getRandomNumber2())
    return nm
}

}