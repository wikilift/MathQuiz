package com.wikilift.aprendeasumar.ui

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.annotation.RequiresApi

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.wikilift.aprendeasumar.MainActivity

import com.wikilift.aprendeasumar.R

import com.wikilift.aprendeasumar.data.local.NumberDataSource
import com.wikilift.aprendeasumar.databinding.FragmentMainScreenBinding

import com.wikilift.aprendeasumar.repository.NumberRepoImpl
import com.wikilift.aprendeasumar.viewModel.NumberViewModel
import com.wikilift.aprendeasumar.viewModel.NumberViewModelFactory


class MainScreen : Fragment(R.layout.fragment_main_screen), View.OnClickListener {

    private lateinit var binding: FragmentMainScreenBinding
    private var result: Int = 0
    private var answered = false
    private lateinit var gson: Gson
    private val viewModel by viewModels<NumberViewModel> {
        NumberViewModelFactory(NumberRepoImpl(NumberDataSource()))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainScreenBinding.bind(view)
        gson = Gson()

        init()
        binding.btnNext.setOnClickListener {

            init()
        }

    }


    fun init() {
        restoreColor()
        toggleDeactivateClick(true)
        binding.txtTablero?.text = "Alumno: ${MainActivity.user?.name}\n" +
                "Puntos: ${MainActivity.user?.points}/1000\n" +
                "Nivel: ${MainActivity.user?.level}"
        binding.txtCounter.visibility = View.VISIBLE
        countdown()
        answered = false

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

    private fun countdown() {
        val obj = MainActivity.user?.level
        var time: Long = 10000
        when (obj) {
            0 -> 10000
            1 -> 8000
            2 -> 5000

        }
        object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if (answered) {
                    cancel()
                } else {
                    binding.txtCounter?.text = (millisUntilFinished / 1000).toString()
                }
            }

            override fun onFinish() {
                if (answered) {
                    cancel()
                } else {
                    val animation: Animation = AnimationUtils.loadAnimation(
                        context,
                        R.anim.buttonanim
                    )
                    binding.btn1?.visibility = View.GONE
                    binding.btn2?.visibility = View.GONE
                    binding.btn3?.visibility = View.GONE
                    binding.txtCounter?.visibility = View.GONE
                    binding.btnNext?.visibility = View.VISIBLE

                    binding.txtAsk?.startAnimation(animation)
                    binding.txtAsk?.append("\n ¡TIEMPO! \n Respuesta:\n${result}")
                    toggleDeactivateClick(false)
                }

            }
        }.start()
    }

    fun toggleDeactivateClick(toggle: Boolean) {
        when (toggle) {
            true -> {
                binding.btn1?.isClickable = true
                binding.btn2?.isClickable = true
                binding.btn3?.isClickable = true
            }
            false -> {
                binding.btn1?.isClickable = false
                binding.btn2?.isClickable = false
                binding.btn3?.isClickable = false
            }
        }

    }

    fun restoreColor() {
        binding.btn1?.backgroundTintList = ColorStateList.valueOf(Color.rgb(52, 180, 235))
        binding.btn2?.backgroundTintList = ColorStateList.valueOf(Color.rgb(52, 180, 235))
        binding.btn3?.backgroundTintList = ColorStateList.valueOf(Color.rgb(52, 180, 235))
    }

    override fun onClick(v: View?) {
        val animation: Animation = AnimationUtils.loadAnimation(
            context,
            R.anim.buttonanim
        )
        when (v) {
            binding.btn1 -> {

                binding.btn1.startAnimation(animation)
                if (binding.btn1?.text.equals(result.toString())) {
                    binding.btn1.backgroundTintList = ColorStateList.valueOf(Color.rgb(0, 255, 0))

                    binding.btn2.visibility = View.GONE
                    binding.btn3.visibility = View.GONE
                    binding.btnNext.visibility = View.VISIBLE
                    binding.txtCounter.visibility = View.GONE
                    binding.txtAsk.startAnimation(animation)
                    binding.txtAsk.append("\n ¡CORRECTO!")
                    toggleDeactivateClick(false)
                    answered = true
                    saveLevelPoint()

                } else {
                    binding.btn1?.backgroundTintList = ColorStateList.valueOf(Color.rgb(255, 0, 0))

                    binding.btn2.visibility = View.GONE
                    binding.btn3.visibility = View.GONE
                    binding.btnNext.visibility = View.VISIBLE
                    binding.txtCounter.visibility = View.GONE
                    binding.txtAsk.startAnimation(animation)
                    binding.txtAsk.append("\n ¡ERROR! \n Respuesta:\n${result}")
                    toggleDeactivateClick(false)
                    answered = true
                }
            }
            binding.btn2 -> {

                binding.btn2.startAnimation(animation)
                if (binding.btn2?.text.equals(result.toString())) {
                    binding.btn2?.backgroundTintList = ColorStateList.valueOf(Color.rgb(0, 255, 0))

                    binding.btn1.visibility = View.GONE
                    binding.btn3.visibility = View.GONE
                    binding.btnNext.visibility = View.VISIBLE
                    binding.txtCounter.visibility = View.GONE
                    binding.txtAsk.startAnimation(animation)
                    binding.txtAsk.append("\n ¡CORRECTO!")
                    toggleDeactivateClick(false)
                    answered = true
                    saveLevelPoint()


                } else {
                    binding.btn2?.backgroundTintList = ColorStateList.valueOf(Color.rgb(255, 0, 0))

                    binding.btn1.visibility = View.GONE
                    binding.btn3.visibility = View.GONE
                    binding.btnNext.visibility = View.VISIBLE
                    binding.txtCounter.visibility = View.GONE
                    binding.txtAsk.startAnimation(animation)
                    binding.txtAsk.append(
                        "\n ¡ERROR!\n" +
                                " Respuesta:\n${result}"
                    )
                    toggleDeactivateClick(false)
                    answered = true

                }
            }
            binding.btn3 -> {

                binding.btn3.startAnimation(animation)
                if (binding.btn3?.text.equals(result.toString())) {
                    binding.btn3?.backgroundTintList = ColorStateList.valueOf(Color.rgb(0, 255, 0))

                    binding.btn1.visibility = View.GONE
                    binding.btn2.visibility = View.GONE
                    binding.btnNext.visibility = View.VISIBLE
                    binding.txtCounter.visibility = View.GONE
                    binding.txtAsk.startAnimation(animation)
                    binding.txtAsk.append("\n ¡CORRECTO!")
                    toggleDeactivateClick(false)
                    answered = true
                    saveLevelPoint()
                    Toast.makeText(context, "${MainActivity.user?.points}", Toast.LENGTH_SHORT)
                        .show()

                } else {
                    binding.btn3?.backgroundTintList = ColorStateList.valueOf(Color.rgb(255, 0, 0))

                    binding.btn1.visibility = View.GONE
                    binding.btn2.visibility = View.GONE
                    binding.btnNext.visibility = View.VISIBLE
                    binding.txtCounter.visibility = View.GONE
                    binding.txtAsk.startAnimation(animation)
                    binding.txtAsk.append(
                        "\n ¡ERROR!\n" +
                                " Respuesta:\n${result}"
                    )
                    toggleDeactivateClick(false)
                    answered = true

                }
            }
        }


    }

    private fun saveLevelPoint() {
        MainActivity.user?.levelUp(1)
        var jsonStrings = gson.toJson(MainActivity.user)
        MainActivity.prefs.name = jsonStrings
    }
}