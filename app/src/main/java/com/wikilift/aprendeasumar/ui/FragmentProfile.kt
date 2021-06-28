package com.wikilift.aprendeasumar.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.wikilift.aprendeasumar.MainActivity
import com.wikilift.aprendeasumar.R
import com.wikilift.aprendeasumar.data.model.User
import com.wikilift.aprendeasumar.databinding.FragmentProfileBinding


class FragmentProfile : Fragment(R.layout.fragment_profile),IOnBackPressed {

    private lateinit var binding: FragmentProfileBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentProfileBinding.bind(view)
        val obj: User? =MainActivity.user
        when(obj?.level){
            0->{
                binding.txtGenius.text="Aprendiz"
                binding.circleImageView.setBackgroundResource(R.drawable.learning)
            }
            1->{
                binding.txtGenius.text="Muy inteligente"
                binding.circleImageView.setBackgroundResource(R.drawable.clever)
            }
            2->{
                binding.txtGenius.text="¡Genio!"
                binding.circleImageView.setBackgroundResource(R.drawable.genius)
            }
        }
        binding.profile.text= "Alumno: ${MainActivity.user?.name}\n" +
        "Puntos: ${MainActivity.user?.points}/100\n" +
                "Nivel: ${MainActivity.user?.level}"

    }
    override fun onBackPressed(): Boolean =true
}