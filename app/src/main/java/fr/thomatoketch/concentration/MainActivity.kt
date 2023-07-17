package fr.thomatoketch.concentration

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

import android.animation.ObjectAnimator
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    private lateinit var countdownTextView: TextView
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var startButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countdownTextView = findViewById(R.id.décompte)
        startButton = findViewById(R.id.Boutton_rebours)

        startButton.setOnClickListener {
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
