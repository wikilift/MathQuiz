package com.wikilift.aprendeasumar.ui

import android.content.res.ColorStateList
import android.graphics.Color

import android.os.Bundle
import android.os.CountDownTimer


import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast


import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.wikilift.aprendeasumar.MainActivity

import com.wikilift.aprendeasumar.R

import com.wikilift.aprendeasumar.data.local.NumberDataSource
import com.wikilift.aprendeasumar.data.model.User
import com.wikilift.aprendeasumar.databinding.FragmentMainScreenBinding

import com.wikilift.aprendeasumar.repository.NumberRepoImpl
import com.wikilift.aprendeasumar.viewModel.NumberViewModel
import com.wikilift.aprendeasumar.viewModel.NumberViewModelFactory


class MainScreen : Fragment(R.layout.fragment_main_screen), View.OnClickListener, IOnBackPressed {

    private lateinit var binding: FragmentMainScreenBinding
    private var result: Int = 0
    private var answered = false
    private var fail: String? = "Sin respuesta"
    private lateinit var gson: Gson
    private var back = false
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


    private fun init() {
        restoreColor()
        toggleDeactivateClick(true)
        back = false
        binding.btnAnswer.visibility = View.GONE
        binding.txtTablero?.text = "${getText(R.string.student)}: ${MainActivity.user?.name}\n" +
               "${getText(R.string.points)}: ${MainActivity.user?.points}/${MainActivity.user?.pointsToNextLevel}\n" +
               "${getText(R.string.level)}: ${MainActivity.user?.level}"
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
        obj.operation = 1
        var test: MutableList<Int> = obj.getRandom()
        result = obj.getChallenge()

        binding.txtAsk.text = "${obj.number1}+${obj.number2}="
        binding.btn1.text = "${obj.getRandomButton(test[0])}"
        binding.btn2.text = "${obj.getRandomButton(test[1])}"
        binding.btn3.text = "${obj.getRandomButton(test[2])}"


    }

    private fun countdown() {
        val obj: User? = MainActivity.user

        object : CountDownTimer(obj!!.getDifficult(), 1000) {
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
                    if (!back) {
                        fail()
                    }


                }
            }
        }.start()
    }

    private fun toggleDeactivateClick(toggle: Boolean) {
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

    private fun restoreColor() {
        binding.btn1?.backgroundTintList = ColorStateList.valueOf(Color.rgb(52, 180, 235))
        binding.btn2?.backgroundTintList = ColorStateList.valueOf(Color.rgb(52, 180, 235))
        binding.btn3?.backgroundTintList = ColorStateList.valueOf(Color.rgb(52, 180, 235))
    }

    override fun onClick(v: View?) {
        val animation: Animation? = AnimationUtils.loadAnimation(
            context,
            R.anim.buttonanim
        )
        when (v) {
            binding.btn1 -> {

                binding.btn1?.startAnimation(animation)
                if (binding.btn1?.text.equals(result.toString())) {
                    succes()

                } else {
                    fail = binding.btn1.text.toString()
                    fail()
                }
            }
            binding.btn2 -> {

                binding.btn2?.startAnimation(animation)
                if (binding.btn2?.text.equals(result.toString())) {
                    succes()


                } else {
                    fail = binding.btn2.text.toString()
                    fail()

                }
            }
            binding.btn3 -> {

                binding.btn3?.startAnimation(animation)
                if (binding.btn3?.text.equals(result.toString())) {
                    succes()


                } else {
                    fail = binding.btn3.text.toString()
                    fail()

                }
            }
        }


    }

    private fun fail() {
        val animation: Animation? = AnimationUtils.loadAnimation(
            context,
            R.anim.buttonanim
        )
        binding.btnAnswer?.backgroundTintList = ColorStateList.valueOf(Color.rgb(255, 0, 0))
        binding.btnAnswer?.text = "${fail.toString()}"
        binding.btnAnswer?.visibility = View.VISIBLE
        binding.btn1?.visibility = View.GONE
        binding.btn2?.visibility = View.GONE
        binding.btn3?.visibility = View.GONE
        binding.btnNext?.visibility = View.VISIBLE
        binding.txtCounter?.visibility = View.GONE
        binding.btnAnswer?.startAnimation(animation)
        binding.txtAsk?.startAnimation(animation)
        binding.txtAsk?.append(
            "\n ${getText(R.string.error)}\n" +
                    " ${getText(R.string.answer)}:\n${result}"
        )
        toggleDeactivateClick(false)
        answered = true
    }

    private fun succes() {
        val animation: Animation? = AnimationUtils.loadAnimation(
            context,
            R.anim.buttonanim
        )

        binding.btnAnswer.backgroundTintList = ColorStateList.valueOf(Color.rgb(0, 255, 0))
        binding.btnAnswer.text = "$result"
        binding.btnAnswer.visibility = View.VISIBLE
        binding.btn1.visibility = View.GONE
        binding.btn2.visibility = View.GONE
        binding.btn3.visibility = View.GONE
        binding.btnNext.visibility = View.VISIBLE
        binding.txtCounter.visibility = View.GONE
        binding.txtAsk?.startAnimation(animation)
        binding.btnAnswer?.startAnimation(animation)
        binding.txtAsk.append("\n ${getText(R.string.correct)}")
        toggleDeactivateClick(false)
        answered = true
        saveLevelPoint()
    }

    private fun saveLevelPoint() {
        MainActivity.user?.levelUp(1)
        val jsonStrings = gson.toJson(MainActivity.user)
        MainActivity.prefs.name = jsonStrings
    }

    override fun onBackPressed(): Boolean {
        back = true

        return back
    }

}

