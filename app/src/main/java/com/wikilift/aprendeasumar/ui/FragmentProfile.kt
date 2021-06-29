package com.wikilift.aprendeasumar.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.wikilift.aprendeasumar.MainActivity
import com.wikilift.aprendeasumar.R
import com.wikilift.aprendeasumar.data.model.User
import com.wikilift.aprendeasumar.databinding.FragmentProfileBinding
import kotlinx.coroutines.NonCancellable.cancel


class FragmentProfile : Fragment(R.layout.fragment_profile),IOnBackPressed {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var gson:Gson

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentProfileBinding.bind(view)
        gson=Gson()
        val obj: User? =MainActivity.user
        when(obj?.level){
            0->{
                binding.txtGenius.text="${getText(R.string.apprentice)}"
                binding.circleImageView.setBackgroundResource(R.drawable.learning)
            }
            1->{
                binding.txtGenius.text="${getText(R.string.clever)}"
                binding.circleImageView.setBackgroundResource(R.drawable.brain)
            }
            2->{
                binding.txtGenius.text="${getText(R.string.graduated)}"
                binding.circleImageView.setBackgroundResource(R.drawable.clever)
            }
            3->{
                binding.txtGenius.text="${getText(R.string.library)}"
                binding.circleImageView.setBackgroundResource(R.drawable.library)
            }
            4->{
                binding.txtGenius.text="${getText(R.string.nuclear)}"
                binding.circleImageView.setBackgroundResource(R.drawable.nuclear)
            }
            5->{
                binding.txtGenius.text="${getText(R.string.robot)}"
                binding.circleImageView.setBackgroundResource(R.drawable.robot)
            }
            else->{
                binding.txtGenius.text="${getText(R.string.genius)}"
                binding.circleImageView.setBackgroundResource(R.drawable.genius)
            }
        }
        makeText()

        binding.imgRestore.setOnClickListener{
            val builder = AlertDialog.Builder(context)
            builder.setMessage(R.string.sure)
                .setPositiveButton(R.string.ok,
                    DialogInterface.OnClickListener { dialog, id ->
                        MainActivity.user?.level=0
                        MainActivity.user?.pointsToNextLevel=0
                        MainActivity.user?.points=0
                        makeText()
                        val jsonStrings = gson.toJson(MainActivity.user)
                        MainActivity.prefs.name = jsonStrings
                    }).setNegativeButton("cancel",DialogInterface.OnClickListener{dialog,id->
                        return@OnClickListener
                })

            // Create the AlertDialog object and return it
            builder.create()
            builder.show()
        }

        binding.imgAbout.setOnClickListener{
            val builder = AlertDialog.Builder(context)
            builder.setMessage(R.string.disclaimer)
                .setPositiveButton(R.string.ok,
                    DialogInterface.OnClickListener { dialog, id ->
                        // FIRE ZE MISSILES!
                    })

            // Create the AlertDialog object and return it
            builder.create()
            builder.show()
        }
    }

    private fun makeText(){
        binding.profile.text= "${getText(R.string.student)}: ${MainActivity.user?.name}\n" +
                "${getText(R.string.points)}: ${MainActivity.user?.points}/${MainActivity.user?.pointsToNextLevel}\n" +
                "${getText(R.string.level)}: ${MainActivity.user?.level}"
    }
    override fun onBackPressed(): Boolean =true

}

