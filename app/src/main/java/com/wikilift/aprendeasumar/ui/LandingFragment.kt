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
import com.wikilift.aprendeasumar.databinding.FragmentlandingBinding


class LandingFragment : Fragment(R.layout.fragmentlanding), IOnBackPressed {

    private lateinit var binding: FragmentlandingBinding
    private lateinit var gson: Gson
    private var jsonString: String? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentlandingBinding.bind(view)
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
                   findNavController().navigate(R.id.action_operationSelector_to_mainScreen)

                    /*******************************************************************************************/
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
                findNavController().navigate(R.id.action_landingFragment_to_operationSelector)

               /**********************************************************************************************/
            }, 3000)

        } else {
            binding.btnStart.visibility = View.GONE
            binding.txtName.visibility = View.VISIBLE
            binding.btnSignin.visibility = View.VISIBLE

        }
    }

    override fun onBackPressed(): Boolean=true

}


