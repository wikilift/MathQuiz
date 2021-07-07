package com.wikilift.aprendeasumar.data.model



import com.wikilift.aprendeasumar.MainActivity
import java.io.Serializable


data class User(
    val name: String? = null,
    var points: Int = 0,
    var level: Int = 0,
    var pointsToNextLevel: Int = 50
) : Serializable {
    fun levelUp(point: Int) {

        when (level) {
            0 -> {
                if (MainActivity.user?.points!! >= 50) {
                    MainActivity.user?.level = MainActivity.user?.level?.plus(1)!!
                    MainActivity.user?.points = 0
                    MainActivity.user?.pointsToNextLevel = 100

                }

            }
            1 -> {
                if (MainActivity.user?.points!! >= 100) {
                    MainActivity.user?.level = MainActivity.user?.level?.plus(1)!!
                    MainActivity.user?.points = 0
                    MainActivity.user?.pointsToNextLevel = 180

                }

            }
            2 -> {
                if (MainActivity.user?.points!! >= 180) {
                    MainActivity.user?.level = MainActivity.user?.level?.plus(1)!!
                    MainActivity.user?.points = 0
                    MainActivity.user?.pointsToNextLevel = 280

                }

            }
            3 -> {
                if (MainActivity.user?.points!! >= 280) {
                    MainActivity.user?.level = MainActivity.user?.level?.plus(1)!!
                    MainActivity.user?.points = 0
                    MainActivity.user?.pointsToNextLevel = 380

                }

            }
            4 -> {
                if (MainActivity.user?.points!! >= 380) {
                    MainActivity.user?.level = MainActivity.user?.level?.plus(1)!!
                    MainActivity.user?.points = 0
                    MainActivity.user?.pointsToNextLevel = 500

                }

            }
            5 -> {
                if (MainActivity.user?.points!! >= 500) {
                    MainActivity.user?.level = MainActivity.user?.level?.plus(1)!!
                    MainActivity.user?.points = 0
                    MainActivity.user?.pointsToNextLevel = 750

                }

            }
            else -> {

                if (MainActivity.user?.points!! >= 750) {
                    MainActivity.user?.level = MainActivity.user?.level?.plus(1)!!
                    MainActivity.user?.points = 0
                    MainActivity.user?.pointsToNextLevel = 750

                }


            }

        }

        MainActivity.user?.points = MainActivity.user?.points?.plus(point)!!

    }

    fun getDifficult(): Long {
        return when (level) {
            0 -> 15000
            1 -> 12500
            2 -> 10000
            3 -> 8500
            4 -> 6500
            5 -> 5500
            else -> 4500

        }
    }

}




