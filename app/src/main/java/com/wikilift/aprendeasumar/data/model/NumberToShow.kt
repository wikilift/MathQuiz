package com.wikilift.aprendeasumar.data.model

import com.wikilift.aprendeasumar.MainActivity


data class NumberToShow(
    var number1: Int,
    var number2: Int,
    var fakeSolution: Int,
    var fakeSolution2: Int,
    var operation: Int = 0


) {
    private var array = mutableListOf<Int>()

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
            val tested: Int = generateRandomForButtons()
            if (!array.contains(tested)) {
                array.add(tested)
            }
        }

        return array

    }

    private fun generateRandomForButtons(): Int = (1..3).random()


    fun getRandomButton(it: Int): String {

        return when (it) {
            1 -> "$fakeSolution"

            2 -> return when(operation) {
                1 -> "${fakeSolution2 + fakeSolution}"
                2->"${fakeSolution2 - fakeSolution}"
                3->"${fakeSolution2 * fakeSolution}"
                4->"$fakeSolution2"
                else->"error"
            }
            3 -> "${getChallenge()}"

            else -> "ERROR"
        }


    }

    private fun getRandomNumber1(): Int {
        val obj = MainActivity.user
        return when (obj!!.level) {
            0 -> {
                return when(operation){
                    3-> (1..5).random()
                    4-> (1..10).random()
                    else-> (1..20).random()
                }


            }
            1 -> {
                return when(operation){
                    3-> (1..10).random()
                    4-> (1..16).random()
                    else-> (1..50).random()
                }


            }
            2 -> {

                return when(operation){
                    3-> (1..12).random()
                    4-> (1..20).random()
                    else-> (1..70).random()
                }
            }
            3 -> {
                return when(operation){
                    3-> (1..15).random()
                    4-> (1..26).random()
                    else-> (1..100).random()
                }


            }
            4->{
                return when(operation){
                    3-> (1..17).random()
                    4-> (1..36).random()
                    else-> (1..150).random()
                }

            }
            5->{
                (1..200).random()
            }
            else->{
                (1..500).random()
            }
        }


    }

    fun stabilizeLevel(){
        number1=getRandomNumber1()
        number2=getRandomNumber1()
        fakeSolution=getRandomNumber1()
        fakeSolution2=getRandomNumber1()
        when(operation){
            2->{
                while (number1<number2){
                    number2=getRandomNumber1()
                }
            }

            4->{
                while (number1%number2!=0){
                    number2=getRandomNumber1()
                    number1=getRandomNumber1()
                }
            }
        }
    }



}
