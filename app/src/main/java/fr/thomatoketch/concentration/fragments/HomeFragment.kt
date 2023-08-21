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
import fr.thomatoketch.concentration.FolderPopup
import fr.thomatoketch.concentration.FolderRepository
import fr.thomatoketch.concentration.adapter.TaskFolderAdapter

class HomeFragment(
    private val context: MainActivity
) : Fragment() {
    private lateinit var countDownTextView: TextView
    private lateinit var countDownTimer: CountDownTimer

    private var tempsInitial: Long = 3600 // Exemple : 600 secondes (10 minutes)
    private var tempsRestant: Long = tempsInitial


    //permet d'injecter le layout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_home, container, false)

        //bouton pour le pomodoro
        var startButton = view?.findViewById<Button>(R.id.bouton_rebours)
        var pauseButton = view?.findViewById<Button>(R.id.bouton_pause)
        var giveUpButton = view?.findViewById<Button>(R.id.bouton_abandon)
        var activityButton = view?.findViewById<Button>(R.id.bouton_activite)
        var restartButton = view?.findViewById<Button>(R.id.bouton_restart)

        //timer
        countDownTextView = view?.findViewById(R.id.décompte) as TextView //je sais c'est bancal le bail

        startButton?.setOnClickListener {
            startButton?.visibility = View.GONE
            pauseButton?.visibility = View.VISIBLE
            giveUpButton?.visibility = View.VISIBLE
            startTimer(3600) // Démarrer le compte à rebours de 10 secondes
        }

        pauseButton?.setOnClickListener {
            pauseButton?.visibility = View.GONE
            restartButton?.visibility = View.VISIBLE
            pauseTimer()
        }

        restartButton?.setOnClickListener {
            restartButton?.visibility = View.GONE
            pauseButton?.visibility = View.VISIBLE
            startTimer(tempsRestant)
        }

        giveUpButton?.setOnClickListener {
            startButton?.visibility = View.VISIBLE
            pauseButton?.visibility = View.GONE
            giveUpButton?.visibility = View.GONE
            restartButton?.visibility = View.GONE
            startTimer(0) // Démarrer le compte à rebours de 10 secondes
        }


        activityButton?.setOnClickListener{
            FolderPopup(TaskFolderAdapter(context, FolderRepository.Singleton.folderList, R.layout.item_folder, "TaskPopup")).show()
        }

        return view
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
                countDownTextView.text = timeLeftFormatted
            }

            override fun onFinish() {
                countDownTextView.text = "00:00"
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