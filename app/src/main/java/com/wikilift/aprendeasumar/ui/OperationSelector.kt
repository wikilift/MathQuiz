package com.wikilift.aprendeasumar.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.wikilift.aprendeasumar.R
import com.wikilift.aprendeasumar.databinding.FragmentMainScreenBinding
import com.wikilift.aprendeasumar.databinding.FragmentSelectorOperacionesBinding


class OperationSelector : Fragment(R.layout.fragment_selector_operaciones),View.OnClickListener,IOnBackPressed {

    private lateinit var binding: FragmentSelectorOperacionesBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSelectorOperacionesBinding.bind(view)
        addListener()


    }

    override fun onClick(v: View?) {
        when(v){
            binding.sum->findNavController().navigate(R.id.action_selectorOperaciones_to_mainScreen)
            binding.rest->findNavController().navigate(R.id.action_selectorOperaciones_to_fragmentRest)
            binding.multiply->findNavController().navigate(R.id.action_selectorOperaciones_to_fragmentMultiply)
            binding.div->findNavController().navigate(R.id.action_selectorOperaciones_to_fragmentDiv)
            binding.imgProfile->findNavController().navigate(R.id.action_selectorOperaciones_to_fragmentProfile)
        }
    }
    private fun addListener(){
        binding.sum.setOnClickListener(this)
        binding.rest.setOnClickListener(this)
        binding.multiply.setOnClickListener(this)
        binding.div.setOnClickListener(this)
        binding.imgProfile.setOnClickListener(this)

    }
    override fun onBackPressed(): Boolean =true

}