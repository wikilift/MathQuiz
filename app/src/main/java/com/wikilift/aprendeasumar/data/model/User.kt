package com.wikilift.aprendeasumar.data.model



import com.wikilift.aprendeasumar.MainActivity
import java.io.Serializable


data class User(
    val name: String? =null,
    var points: Int=0,
    var level: Int=0
):Serializable {
    fun levelUp(point: Int) {
        if (MainActivity.user?.points!! >= 100) {
            MainActivity.user?.level =MainActivity.user?.level?.plus(1)!!
            MainActivity.user?.points=0

        }
        MainActivity.user?.points = MainActivity.user?.points?.plus(point)!!

    }
}




