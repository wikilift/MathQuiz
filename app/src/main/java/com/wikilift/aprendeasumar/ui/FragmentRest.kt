package com.wikilift.aprendeasumar.ui



import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.media.MediaPlayer
import android.os.*


import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils



import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.wikilift.aprendeasumar.MainActivity
import com.wikilift.aprendeasumar.R


import com.wikilift.aprendeasumar.data.local.NumberDataSource
import com.wikilift.aprendeasumar.data.model.User


import com.wikilift.aprendeasumar.databinding.FragmentrestBinding


import com.wikilift.aprendeasumar.repository.NumberRepoImpl
import com.wikilift.aprendeasumar.viewModel.NumberViewModel
import com.wikilift.aprendeasumar.viewModel.NumberViewModelFactory


class FragmentRest : Fragment(R.layout.fragmentrest), View.OnClickListener, IOnBackPressed {

    private lateinit var binding: FragmentrestBinding
    private var result: Int = 0
    private var answered = false
    private var fail: String? = "Sin respuesta"
    private lateinit var gson: Gson
    private var back = false
    private lateinit var mediaPlayer:MediaPlayer
    private val viewModel by viewModels<NumberViewModel> {
        NumberViewModelFactory(NumberRepoImpl(NumberDataSource()))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentrestBinding.bind(view)
        gson = Gson()

        init()
        binding.btnNext.setOnClickListener {
            this.vibratePhone()
            init()

        }

    }


    private fun init() {
        sound(R.raw.tictac, true)
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
        obj.operation = 2
        var test: MutableList<Int> = obj.getRandom()
        result = obj.getChallenge()

        binding.txtAsk.text = "${obj.number1}-${obj.number2}="
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
                mediaPlayer.stop()
                if (answered) {
                    cancel()
                } else {
                    if (!back) {
                        fail()
                        cancel()
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
                mediaPlayer.stop()
                this.vibratePhone()
                binding.btn1?.startAnimation(animation)
                if (binding.btn1?.text.equals(result.toString())) {
                    succes()

                } else {
                    fail = binding.btn1.text.toString()
                    fail()
                }
            }
            binding.btn2 -> {
                mediaPlayer.stop()
                this.vibratePhone()
                binding.btn2?.startAnimation(animation)
                if (binding.btn2?.text.equals(result.toString())) {
                    succes()


                } else {
                    fail = binding.btn2.text.toString()
                    fail()

                }
            }
            binding.btn3 -> {
                mediaPlayer.stop()
                this.vibratePhone()
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
        binding.btnAnswer?.text = "$fail"
        binding.btnAnswer?.visibility = View.VISIBLE
        binding.btn1?.visibility = View.GONE
        binding.btn2?.visibility = View.GONE
        binding.btn3?.visibility = View.GONE
        binding.btnNext?.visibility = View.VISIBLE
        binding.txtCounter?.visibility = View.GONE
        binding.btnAnswer?.startAnimation(animation)
        binding.txtAsk?.startAnimation(animation)
        sound(R.raw.fail, false)
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

        binding.btnAnswer.backgroundTintList = ColorStateList.valueOf(Color.rgb(0, 200, 0))
        binding.btnAnswer.text = "$result"
        binding.btnAnswer.visibility = View.VISIBLE
        binding.btn1.visibility = View.GONE
        binding.btn2.visibility = View.GONE
        binding.btn3.visibility = View.GONE
        binding.btnNext.visibility = View.VISIBLE
        binding.txtCounter.visibility = View.GONE
        binding.txtAsk?.startAnimation(animation)
        binding.btnAnswer?.startAnimation(animation)
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
        mediaPlayer.stop()
        back = true

        return back
    }
    private fun vibratePhone() {
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


}

interface IOnBackPressed {
    fun onBackPressed(): Boolean
}