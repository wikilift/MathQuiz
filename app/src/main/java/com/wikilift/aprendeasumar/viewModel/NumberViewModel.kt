package com.wikilift.aprendeasumar.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.wikilift.aprendeasumar.core.Resource
import com.wikilift.aprendeasumar.data.model.NumberToShow
import com.wikilift.aprendeasumar.repository.NumberRepo
import kotlinx.coroutines.Dispatchers

class NumberViewModel(private val repo:NumberRepo): ViewModel() {
    fun fetchInfo(): NumberToShow = repo.getData()
}

class NumberViewModelFactory(private val repo: NumberRepo):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(NumberRepo::class.java).newInstance(repo)
    }

}