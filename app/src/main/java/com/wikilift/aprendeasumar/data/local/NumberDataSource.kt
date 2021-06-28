package com.wikilift.aprendeasumar.data.local

import com.wikilift.aprendeasumar.data.model.NumberToShow

class NumberDataSource() {
    private fun getRandom(): Int = (1..100).random()
    fun getNumber(): NumberToShow {
        var n1 = (1..150).random()
        var n2 = (1..50).random()
        val nm = NumberToShow(n1, n2, getRandom(), getRandom())
        return nm
    }

}