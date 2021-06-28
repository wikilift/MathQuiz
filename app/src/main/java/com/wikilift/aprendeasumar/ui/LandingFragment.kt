package com.wikilift.aprendeasumar.ui


import android.os.Bundle
import android.os.Handler
import android.os.Looper


import androidx.fragment.app.Fragment

import android.view.View


import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.wikilift.aprendeasumar.MainActivity
import com.wikilift.aprendeasumar.R

import com.wikilift.aprendeasumar.data.model.User
import com.wikilift.aprendeasumar.databinding.FragmentLandingBinding



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
                binding.txtName.error = "${getText(R.string.must_name)}"
            } else {


                val user = User(binding.txtName.text.toString(), 0, 0)
                val jsonStrings = gson.toJson(user)
                MainActivity.prefs.name = jsonStrings
                binding.btnSignin.visibility=View.GONE
                binding.btnSignin.hint=""
                jsonString = MainActivity.prefs.name
                MainActivity.user = gson.fromJson(jsonString, User::class.java)


                binding.txtName.visibility=View.GONE

                binding.progressBar.visibility = View.VISIBLE
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.progressBar.visibility = View.GONE
                    findNavController().navigate(R.id.action_landingFragment_to_selectorOperaciones)
                }, 2000)


            }
        }


    }

    private fun compareInit() {
        if (MainActivity.user?.name != null) {

            binding.txtName.visibility = View.GONE
            binding.txtWelcome.append("\n ${MainActivity.user?.name}")
            binding.btnStart.visibility = View.GONE


            binding.progressBar.visibility = View.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed({
                binding.progressBar.visibility = View.GONE
                findNavController().navigate(R.id.action_landingFragment_to_selectorOperaciones)
            }, 3000)

        } else {
            binding.btnStart.visibility = View.GONE
            binding.txtName.visibility = View.VISIBLE
            binding.btnSignin.visibility = View.VISIBLE

        }
    }


}


