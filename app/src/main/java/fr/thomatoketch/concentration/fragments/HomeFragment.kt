package fr.thomatoketch.concentration.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import android.widget.Button
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater

import androidx.fragment.app.Fragment
import fr.thomatoketch.concentration.MainActivity
import fr.thomatoketch.concentration.R

class HomeFragment(
    private val context: MainActivity
) : Fragment() {
    private lateinit var countDownTextView: TextView
    private lateinit var countDownTimer: CountDownTimer


    //permet d'injecter le layout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_home, container, false)

        //bouton pour le pomodoro
        var startButton = view?.findViewById<Button>(R.id.Boutton_rebours)
        var pauseButton = view?.findViewById<Button>(R.id.bouton_pause)
        var giveUpButton = view?.findViewById<Button>(R.id.bouton_abandon)

        //timer
        countDownTextView = view?.findViewById(R.id.décompte) as TextView //je sais c'est bancal le bail

        startButton?.setOnClickListener {
            startButton?.visibility = View.GONE
            pauseButton?.visibility = View.VISIBLE
            giveUpButton?.visibility = View.VISIBLE
            startCountdown(3600) // Démarrer le compte à rebours de 10 secondes
        }
        return view
    }
    private fun startCountdown(seconds: Long) {
        val totalMillis = seconds * 1000

        countDownTimer = object : CountDownTimer(totalMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60
                val timeLeftFormatted = String.format("%02d:%02d", minutes, seconds)
                countDownTextView.text = timeLeftFormatted
            }

            override fun onFinish() {
                countDownTextView.text = "00:00"
            }
        }

        countDownTimer.start()
    }
}