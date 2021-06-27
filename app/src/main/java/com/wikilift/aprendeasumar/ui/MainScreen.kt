package com.wikilift.aprendeasumar.ui

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import com.wikilift.aprendeasumar.R
import com.wikilift.aprendeasumar.data.local.NumberDataSource
import com.wikilift.aprendeasumar.databinding.FragmentMainScreenBinding
import com.wikilift.aprendeasumar.repository.NumberRepoImpl
import com.wikilift.aprendeasumar.viewModel.NumberViewModel
import com.wikilift.aprendeasumar.viewModel.NumberViewModelFactory


class MainScreen : Fragment(R.layout.fragment_main_screen), View.OnClickListener {

    private lateinit var binding: FragmentMainScreenBinding
    private var result: Int = 0
    private val viewModel by viewModels<NumberViewModel> {
        NumberViewModelFactory(NumberRepoImpl(NumberDataSource()))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainScreenBinding.bind(view)

        init()
        binding.btnNext.setOnClickListener{

            init()
        }

    }

    fun init() {
        binding.btnNext.visibility = View.GONE
        binding.btn1.visibility = View.VISIBLE
        binding.btn2.visibility = View.VISIBLE
        binding.btn3.visibility = View.VISIBLE

        binding.btn1.setOnClickListener(this)
        binding.btn2.setOnClickListener(this)
        binding.btn3.setOnClickListener(this)
        var obj = viewModel.fetchInfo()
        var test: ArrayList<Int> = obj.getRandom()
        result = obj.getChallenge()
        binding.txtAsk.text = "${obj.number1}+${obj.number2}="
        binding.btn1.text = "${obj.getRandomButton(test.indexOf(1))}"
        binding.btn2.text = "${obj.getRandomButton(test.indexOf(3))}"
        binding.btn3.text = "${obj.getRandomButton(test.indexOf(2))}"

    }


    override fun onClick(v: View?) {
        val animation: Animation = AnimationUtils.loadAnimation(
            context,
            R.anim.buttonanim
        )
        when (v) {
            binding.btn1 -> {
                Toast.makeText(context, "peneduro", Toast.LENGTH_LONG).show()
                binding.btn1.startAnimation(animation)
                if (binding.btn1?.text.equals(result.toString())) {
                    binding.btn1.backgroundTintList = ColorStateList.valueOf(Color.rgb(0, 255, 0))

                    binding.btn2.visibility = View.GONE
                    binding.btn3.visibility = View.GONE
                    binding.btnNext.visibility = View.VISIBLE
                } else {
                    binding.btn1?.backgroundTintList = ColorStateList.valueOf(Color.rgb(255, 0, 0))

                    binding.btn2.visibility = View.GONE
                    binding.btn3.visibility = View.GONE
                    binding.btnNext.visibility = View.VISIBLE
                }
            }
            binding.btn2 -> {
                Toast.makeText(context, "peneblando", Toast.LENGTH_LONG).show()
                binding.btn2.startAnimation(animation)
                if (binding.btn2?.text.equals(result.toString())) {
                    binding.btn2?.backgroundTintList = ColorStateList.valueOf(Color.rgb(0, 255, 0))

                    binding.btn1.visibility = View.GONE
                    binding.btn3.visibility = View.GONE
                    binding.btnNext.visibility = View.VISIBLE
                } else {
                    binding.btn2?.backgroundTintList = ColorStateList.valueOf(Color.rgb(255, 0, 0))

                    binding.btn1.visibility = View.GONE
                    binding.btn3.visibility = View.GONE
                    binding.btnNext.visibility = View.VISIBLE

                }
            }
            binding.btn3 -> {
                Toast.makeText(context, "penemorcillon", Toast.LENGTH_LONG).show()
                binding.btn3.startAnimation(animation)
                if (binding.btn3?.text.equals(result.toString())) {
                    binding.btn3?.backgroundTintList = ColorStateList.valueOf(Color.rgb(0, 255, 0))

                    binding.btn1.visibility = View.GONE
                    binding.btn2.visibility = View.GONE
                    binding.btnNext.visibility = View.VISIBLE
                } else {
                    binding.btn3?.backgroundTintList = ColorStateList.valueOf(Color.rgb(255, 0, 0))

                    binding.btn1.visibility = View.GONE
                    binding.btn2.visibility = View.GONE
                    binding.btnNext.visibility = View.VISIBLE

                }
            }
        }


    }
}