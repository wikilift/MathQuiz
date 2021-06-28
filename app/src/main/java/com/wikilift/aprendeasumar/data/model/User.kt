package com.wikilift.aprendeasumar.data.model



import com.wikilift.aprendeasumar.MainActivity
import java.io.Serializable


data class User(
    val name: String? =null,
    var points: Int=0,
    var level: Int=0
):Serializable {
    fun levelUp(point: Int) {

        MainActivity.user?.points = MainActivity.user?.points?.plus(point)!!
        if (MainActivity.user?.points == 100) {
            MainActivity.user?.level =MainActivity.user?.points?.plus(1)!!

        }
    }
}




