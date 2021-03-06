package com.wikilift.aprendeasumar.ui


import android.content.Context

import android.os.*

import androidx.fragment.app.Fragment

import android.view.View

import androidx.navigation.fragment.findNavController
import com.wikilift.aprendeasumar.R

import com.wikilift.aprendeasumar.databinding.FragmentoperationselectorBinding
import com.wikilift.aprendeasumar.core.IOnBackPressed


class OperationSelector : Fragment(R.layout.fragmentoperationselector),View.OnClickListener,
    IOnBackPressed {

    private lateinit var binding: FragmentoperationselectorBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentoperationselectorBinding.bind(view)
        addListener()

    }


    override fun onClick(v: View?) {

        when(v){
            binding.sum->{
                this.vibratePhone()
                val action=OperationSelectorDirections.actionOperationSelectorToMainScreen(1)
                findNavController().navigate(action)
            }
            binding.rest->{
                this.vibratePhone()
                val action=OperationSelectorDirections.actionOperationSelectorToMainScreen(2)
               findNavController().navigate(action)

            }
            binding.multiply->{
                this.vibratePhone()
                val action=OperationSelectorDirections.actionOperationSelectorToMainScreen(3)
                findNavController().navigate(action)
            }
            binding.div->{
                this.vibratePhone()
                val action=OperationSelectorDirections.actionOperationSelectorToMainScreen(4)
                findNavController().navigate(action)

            }
            binding.imgProfile->{
                this.vibratePhone()
               findNavController().navigate(R.id.action_operationSelector_to_fragmentProfile)

            }
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
    private fun Fragment.vibratePhone() {
        val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (vibrator.hasVibrator()) { // Vibrator availability checking
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(
                    VibrationEffect.createOneShot(
                        200,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                ) // New vibrate method for API Level 26 or higher
            } else {
                @Suppress("DEPRECATION")
                vibrator.vibrate(200) // Vibrate method for below API Level 26
            }
        }
    }


}