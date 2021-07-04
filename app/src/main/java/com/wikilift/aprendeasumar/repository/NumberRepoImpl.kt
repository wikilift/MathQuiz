package com.wikilift.aprendeasumar.repository

import com.wikilift.aprendeasumar.data.local.NumberDataSource
import com.wikilift.aprendeasumar.data.model.NumberToShow


class NumberRepoImpl(private val dataSource: NumberDataSource): NumberRepo {
    override fun getData(): NumberToShow = dataSource.getNumber()

}