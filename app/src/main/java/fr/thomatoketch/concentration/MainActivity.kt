package fr.thomatoketch.concentration

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.view.View

import android.animation.ObjectAnimator
import android.content.Intent
import android.widget.ImageView
import fr.thomatoketch.concentration.fragment.monRepertoireFragment

class MainActivity : AppCompatActivity() {

    private lateinit var countdownTextView: TextView
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var startButton: Button
    private lateinit var pauseButton: Button
    private lateinit var giveUpButton: Button
    private lateinit var repertoireButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countdownTextView = findViewById(R.id.décompte)
        startButton = findViewById<Button>(R.id.Boutton_rebours)
        pauseButton= findViewById<Button>(R.id.bouton_pause)
        giveUpButton = findViewById<Button>(R.id.bouton_abandon)
        repertoireButton = findViewById<Button>(R.id.bouton_repertoire)

        startButton.setOnClickListener {
            startButton.visibility = View.GONE
            pauseButton.visibility = View.VISIBLE
            giveUpButton.visibility = View.VISIBLE
            startCountdown(3600) // Démarrer le compte à rebours de 10 secondes
        }

        //injetcter fragment
        //val transaction = supportFragmentManager.beginTransaction()
        //transaction.replace(R.id.fragment_container, monRepertoireFragment())
        //transaction.addToBackStack(null)
        //transaction.commit()
    }

    private fun onButtonClicked(view: View) {
        val intent = Intent(this, monRepertoireFragment::class.java)
        startActivity(intent)
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
