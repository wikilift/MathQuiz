package com.wikilift.aprendeasumar.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.wikilift.aprendeasumar.R
import com.wikilift.aprendeasumar.core.Resource
import com.wikilift.aprendeasumar.data.local.NumberDataSource
import com.wikilift.aprendeasumar.data.model.NumberToShow
import com.wikilift.aprendeasumar.databinding.FragmentMainScreenBinding
import com.wikilift.aprendeasumar.repository.NumberRepoImpl
import com.wikilift.aprendeasumar.viewModel.NumberViewModel
import com.wikilift.aprendeasumar.viewModel.NumberViewModelFactory


class MainScreen : Fragment(R.layout.fragment_main_screen) {

    private lateinit var  binding: FragmentMainScreenBinding
    private val viewModel by viewModels <NumberViewModel> {
       NumberViewModelFactory(NumberRepoImpl(NumberDataSource()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentMainScreenBinding.bind(view)
        val obj=viewModel.fetchInfo()







    }


}
