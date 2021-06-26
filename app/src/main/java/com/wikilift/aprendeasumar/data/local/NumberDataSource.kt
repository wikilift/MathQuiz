package com.wikilift.aprendeasumar.data.local

import com.wikilift.aprendeasumar.data.model.NumberToShow

class NumberDataSource {
    private fun getRandom():Int=  (0..100).random()
     fun getNumber():NumberToShow{
        val nm= NumberToShow(getRandom(),getRandom(),getRandom(),getRandom())
        return nm
    }

}