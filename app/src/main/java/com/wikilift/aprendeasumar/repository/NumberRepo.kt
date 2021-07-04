package com.wikilift.aprendeasumar.repository

import com.wikilift.aprendeasumar.data.model.NumberToShow

interface NumberRepo {
    fun getData(): NumberToShow
}