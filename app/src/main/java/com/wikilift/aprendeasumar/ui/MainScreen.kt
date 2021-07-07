package com.wikilift.aprendeasumar.ui


import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.media.MediaPlayer
import android.os.*


import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast


import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import com.wikilift.aprendeasumar.MainActivity
import com.wikilift.aprendeasumar.R
import com.wikilift.aprendeasumar.core.IOnBackPressed
import com.wikilift.aprendeasumar.core.hide
import com.wikilift.aprendeasumar.core.show


import com.wikilift.aprendeasumar.data.local.NumberDataSource
import com.wikilift.aprendeasumar.data.model.User

import com.wikilift.aprendeasumar.databinding.FragmentmainscreenBinding


import com.wikilift.aprendeasumar.repository.NumberRepoImpl
import com.wikilift.aprendeasumar.viewModel.NumberViewModel
import com.wikilift.aprendeasumar.viewModel.NumberViewModelFactory


class MainScreen : Fragment(R.layout.fragmentmainscreen), View.OnClickListener, IOnBackPressed {

    private lateinit var binding: FragmentmainscreenBinding


    private val args: MainScreenArgs by navArgs()
    private var operation:Int=0

    private var result: Int = 0
    private var answered = false
    private var fail: String? = null
    private lateinit var gson: Gson
    private var back = false
    private lateinit var mediaPlayer: MediaPlayer
    private val viewModel by viewModels<NumberViewModel> {
        NumberViewModelFactory(NumberRepoImpl(NumberDataSource()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        operation=args.operationSelector
        fail= getText(R.string.no_sense).toString()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentmainscreenBinding.bind(view)

        selectTittle()

        gson = Gson()
        binding.btn1.setOnClickListener(this)
        binding.btn2.setOnClickListener(this)
        binding.btn3.setOnClickListener(this)
        init()
        binding.btnNext.setOnClickListener {
            this.vibratePhone()
            init()

        }

    }


    @SuppressLint("SetTextI18n")
    private fun init() {
        sound(R.raw.tictac, true)
        restoreColor()
        toggleDeactivateClick(true)
        back = false
        binding.btnAnswer.hide()
        binding.txtTablero.text = showInputUser()
        binding.txtCounter.show()


        binding.btnNext.hide()
        binding.btn1.show()
        binding.btn2.show()
        binding.btn3.show()


        val obj = viewModel.startChallenge()
        obj.operation = operation
        obj.stabilizeLevel()

        val test: MutableList<Int> = obj.getRandom()
        result = obj.getChallenge()

        binding.txtAsk.text = "${obj.number1}${selectSymbol()}${obj.number2}="
        binding.btn1.text = obj.getRandomButton(test[0])
        binding.btn2.text = obj.getRandomButton(test[1])
        binding.btn3.text = obj.getRandomButton(test[2])
        countdown()
        answered = false


    }

    private fun countdown() {
        val obj: User? = MainActivity.user

        object : CountDownTimer(obj!!.getDifficult(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if (answered) {
                    cancel()
                } else {
                    binding.txtCounter.text = (millisUntilFinished / 1000).toString()
                }
            }

            override fun onFinish() {
                mediaPlayer.stop()
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
                binding.btn1.isClickable = true
                binding.btn2.isClickable = true
                binding.btn3.isClickable = true
            }
            false -> {
                binding.btn1.isClickable = false
                binding.btn2.isClickable = false
                binding.btn3.isClickable = false
            }
        }

    }

    private fun restoreColor() {
        binding.btn1.backgroundTintList = ColorStateList.valueOf(Color.rgb(52, 180, 235))
        binding.btn2.backgroundTintList = ColorStateList.valueOf(Color.rgb(52, 180, 235))
        binding.btn3.backgroundTintList = ColorStateList.valueOf(Color.rgb(52, 180, 235))
    }

    override fun onClick(v: View?) {
        val animation: Animation? = AnimationUtils.loadAnimation(
            context,
            R.anim.buttonanim
        )
        when (v) {
            binding.btn1 -> {
                this.vibratePhone()
                mediaPlayer.stop()
                binding.btn1.startAnimation(animation)
                if (binding.btn1.text.equals(result.toString())) {
                    victory()

                } else {
                    fail = binding.btn1.text.toString()
                    fail()
                }
            }
            binding.btn2 -> {
                this.vibratePhone()
                mediaPlayer.stop()
                binding.btn2.startAnimation(animation)
                if (binding.btn2.text.equals(result.toString())) {
                    victory()


                } else {
                    fail = binding.btn2.text.toString()
                    fail()

                }
            }
            binding.btn3 -> {
                this.vibratePhone()
                mediaPlayer.stop()
                binding.btn3.startAnimation(animation)
                if (binding.btn3.text.equals(result.toString())) {
                    victory()


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
        binding.btnAnswer.backgroundTintList = ColorStateList.valueOf(Color.rgb(220, 0, 0))
        binding.btnAnswer.text = fail.toString()
        binding.btnAnswer.visibility = View.VISIBLE
        binding.btn1.visibility = View.GONE
        binding.btn2.visibility = View.GONE
        binding.btn3.visibility = View.GONE
        binding.btnNext.visibility = View.VISIBLE
        binding.txtCounter.visibility = View.GONE
        binding.btnAnswer.startAnimation(animation)
        binding.txtAsk.startAnimation(animation)
        sound(R.raw.fail, false)
        binding.txtAsk.append(
            "\n ${getText(R.string.error)}\n" +
                    " ${getText(R.string.answer)}:\n${result}"
        )
        toggleDeactivateClick(false)
        answered = true
    }

    private fun victory() {
        val animation: Animation? = AnimationUtils.loadAnimation(
            context,
            R.anim.buttonanim
        )

        binding.btnAnswer.backgroundTintList = ColorStateList.valueOf(Color.rgb(0, 200, 0))
        binding.btnAnswer.text = "$result"
        binding.btnAnswer.visibility = View.VISIBLE
        binding.btn1.visibility = View.GONE
        binding.btn2.visibility = View.GONE
        binding.btn3.visibility = View.GONE
        binding.btnNext.visibility = View.VISIBLE
        binding.txtCounter.visibility = View.GONE
        binding.txtAsk.startAnimation(animation)
        binding.btnAnswer.startAnimation(animation)
        sound(R.raw.succes, false)
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
        mediaPlayer.stop()

        return back
    }
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
    private fun sound(resID:Int,isLooping:Boolean) {
        mediaPlayer = MediaPlayer.create(context,resID)
        if(isLooping){
            mediaPlayer.isLooping=true
        }
        mediaPlayer.start()


    }
    private fun selectTittle(){
        when(operation){
            1->binding.txtHeader.text=getText(R.string.sum)
            2->binding.txtHeader.text=getText(R.string.rest)
            3->{
                binding.txtHeader.text=getText(R.string.multiply)
                binding.txtHeader.textSize=40f
            }
            4->binding.txtHeader.text=getText(R.string.division)
        }
    }

    private fun selectSymbol():String{
        return when(operation){
            1->"+"
            2->"-"
            3->"*"
            4->"/"
            else ->""
        }
    }
    private fun showInputUser():String="${getText(R.string.student)}: ${MainActivity.user?.name}\n" +
            "${getText(R.string.points)}: ${MainActivity.user?.points}/${MainActivity.user?.pointsToNextLevel}\n" +
            "${getText(R.string.level)}: ${MainActivity.user?.level}"


}

