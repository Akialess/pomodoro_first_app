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
    private lateinit var restartButton: Button
    private lateinit var giveUpButton: Button
    private lateinit var repertoireButton: Button
    private var tempsInitial: Long = 3600 // Exemple : 600 secondes (10 minutes)
    private var tempsRestant: Long = tempsInitial



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countdownTextView = findViewById(R.id.décompte)
        startButton = findViewById<Button>(R.id.Boutton_rebours)
        pauseButton= findViewById<Button>(R.id.bouton_pause)
        restartButton= findViewById<Button>(R.id.bouton_restart)
        giveUpButton = findViewById<Button>(R.id.bouton_abandon)
        repertoireButton = findViewById<Button>(R.id.bouton_repertoire)

        startButton.setOnClickListener {
            startButton.visibility = View.GONE
            pauseButton.visibility = View.VISIBLE
            giveUpButton.visibility = View.VISIBLE
            startTimer(tempsInitial) // Démarrer le compte à rebours de 10 secondes
        }

        pauseButton.setOnClickListener {
            pauseButton.visibility = View.GONE
            restartButton.visibility = View.VISIBLE
            pauseTimer()
        }

        restartButton.setOnClickListener {
            restartButton.visibility = View.GONE
            pauseButton.visibility = View.VISIBLE
            startTimer(tempsRestant)
        }

        giveUpButton.setOnClickListener {
            startButton.visibility = View.VISIBLE
            pauseButton.visibility = View.GONE
            giveUpButton.visibility = View.GONE
            restartButton.visibility = View.GONE
            startTimer(0) // Démarrer le compte à rebours de 10 secondes
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

    private fun startTimer(secondes: Long) {
        val totalMillis = secondes * 1000

        if (::countDownTimer.isInitialized) {
            countDownTimer.cancel()
        }

        countDownTimer = object : CountDownTimer(totalMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tempsRestant = millisUntilFinished / 1000
                val minutes = (millisUntilFinished / 1000) / 60
                val secondes = (millisUntilFinished / 1000) % 60
                val timeLeftFormatted = String.format("%02d:%02d", minutes, secondes)
                countdownTextView.text = timeLeftFormatted
            }

            override fun onFinish() {
                countdownTextView.text = "00:00"
            }
        }

        countDownTimer.start()
    }

    private fun pauseTimer() {
        countDownTimer.cancel()
    }


    override fun onDestroy() {
        super.onDestroy()
        if (::countDownTimer.isInitialized) {
            countDownTimer.cancel()
        }
    }
}
