package com.wikilift.aprendeasumar.ui

import android.app.AlertDialog
import android.content.DialogInterface
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
import kotlinx.coroutines.NonCancellable.cancel


class FragmentProfile : Fragment(R.layout.fragment_profile),IOnBackPressed {

    private lateinit var binding: FragmentProfileBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentProfileBinding.bind(view)
        val obj: User? =MainActivity.user
        when(obj?.level){
            0->{
                binding.txtGenius.text="${getText(R.string.apprentice)}"
                binding.circleImageView.setBackgroundResource(R.drawable.learning)
            }
            1->{
                binding.txtGenius.text="${getText(R.string.clever)}"
                binding.circleImageView.setBackgroundResource(R.drawable.clever)
            }
            2->{
                binding.txtGenius.text="${getText(R.string.genius)}"
                binding.circleImageView.setBackgroundResource(R.drawable.genius)
            }
        }
        binding.profile.text= "${getText(R.string.student)}: ${MainActivity.user?.name}\n" +
        "${getText(R.string.points)}: ${MainActivity.user?.points}/100\n" +
                "${getText(R.string.level)}: ${MainActivity.user?.level}"

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

    override fun onBackPressed(): Boolean =true

}

