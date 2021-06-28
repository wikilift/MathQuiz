package com.wikilift.aprendeasumar.ui


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log

import androidx.fragment.app.Fragment

import android.view.View

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.wikilift.aprendeasumar.MainActivity
import com.wikilift.aprendeasumar.R

import com.wikilift.aprendeasumar.data.model.User
import com.wikilift.aprendeasumar.databinding.FragmentLandingBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO


class LandingFragment : Fragment(R.layout.fragment_landing) {

    private lateinit var binding: FragmentLandingBinding
    private lateinit var gson: Gson
    private var jsonString: String? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLandingBinding.bind(view)
        gson = Gson()

        binding.btnStart.setOnClickListener {

            jsonString = MainActivity.prefs.name
            MainActivity.user = gson.fromJson(jsonString, User::class.java)
            compareInit()


        }

        binding.btnSignin.setOnClickListener {
            if (binding.txtName.length() < 3) {
                binding.txtName.error = "Debe introducir un nombre"
            } else {


                val user = User(binding.txtName.text.toString(), 0, 0)
                var jsonStrings = gson.toJson(user)
                MainActivity.prefs.name = jsonStrings
                binding.progressBar.visibility = View.VISIBLE
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.progressBar.visibility = View.GONE
                    findNavController().navigate(R.id.action_landingFragment_to_mainScreen)
                }, 3000)


            }
        }


    }

    private fun compareInit() {
        if (MainActivity.user?.name != null) {
            binding.txtNotYou.visibility = View.GONE
            binding.txtName.visibility = View.GONE
            binding.txtWelcome.append("\n ${MainActivity.user?.name}")
            binding.btnStart.visibility = View.GONE


            binding.progressBar.visibility = View.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed({
                binding.progressBar.visibility = View.GONE
                findNavController().navigate(R.id.action_landingFragment_to_mainScreen)
            }, 3000)

        } else {
            binding.btnStart.visibility = View.GONE
            binding.txtName.visibility = View.VISIBLE
            binding.btnSignin.visibility = View.VISIBLE
            binding.txtNotYou.visibility = View.VISIBLE
        }
    }


}


