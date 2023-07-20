package fr.thomatoketch.concentration.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import fr.thomatoketch.concentration.R

import android.animation.ObjectAnimator
import android.widget.ImageView

class Adapter : AppCompatActivity(){

    private lateinit var countdownTextView: TextView
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var startButton: Button
    private lateinit var pauseButton: Button
    private lateinit var giveUpButton: Button

    //Injecte fragment_home sur la page

    override fun onCreate(savedInstanceState: Bundle?){

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countdownTextView = findViewById(R.id.décompte)
        startButton = findViewById<Button>(R.id.Boutton_rebours)
        pauseButton = findViewById<Button>(R.id.bouton_pause)
        giveUpButton = findViewById<Button>(R.id.bouton_abandon)

        startButton.setOnClickListener {
            startButton.visibility = View.GONE
            pauseButton.visibility = View.VISIBLE
            giveUpButton.visibility = View.VISIBLE
            startCountdown(3600) // Démarrer le compte à rebours de 10 secondes
        }
    }

    private fun startCountdown(seconds: Long) {
        val totalMillis = seconds * 1000

        countDownTimer = object : CountDownTimer(totalMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60
                val timeLeftFormatted = String.format("%02d:%02d", minutes, seconds)
                countdownTextView.text = timeLeftFormatted
            }

            override fun onFinish() {
                countdownTextView.text = "00:00"
            }
        }

        countDownTimer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::countDownTimer.isInitialized) {
            countDownTimer.cancel()
        }
    }
}