package com.wikilift.aprendeasumar.ui

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
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
            binding.sum->{
                this.vibratePhone()
                findNavController().navigate(R.id.action_selectorOperaciones_to_mainScreen)
            }
            binding.rest->{
                this.vibratePhone()
                findNavController().navigate(R.id.action_selectorOperaciones_to_fragmentRest)
            }
            binding.multiply->{
                this.vibratePhone()
                findNavController().navigate(R.id.action_selectorOperaciones_to_fragmentMultiply)
            }
            binding.div->{
                this.vibratePhone()
                findNavController().navigate(R.id.action_selectorOperaciones_to_fragmentDiv)
            }
            binding.imgProfile->{
                this.vibratePhone()
                findNavController().navigate(R.id.action_selectorOperaciones_to_fragmentProfile)
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