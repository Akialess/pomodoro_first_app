package fr.thomatoketch.concentration.fragments

import android.content.res.ColorStateList
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
import fr.thomatoketch.concentration.data.Task
import kotlinx.android.synthetic.main.fragment_home.view.name_task
import kotlinx.android.synthetic.main.item_task.view.icon_item
import kotlinx.android.synthetic.main.item_task.view.timeTask
import kotlinx.android.synthetic.main.item_task.view.totalTaskScore

class HomeFragment(
    private val context: MainActivity,
) : Fragment(){
    private lateinit var countDownTextView: TextView
    private lateinit var countDownTimer: CountDownTimer

    private var tempsInitial: Long = 3600 // Exemple : 600 secondes (10 minutes)
    private var tempsRestant: Long = tempsInitial

    private lateinit var view: View

    private lateinit var activityButton: Button
    private lateinit var taskActivity: View


    //permet d'injecter le layout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        view = inflater.inflate(R.layout.fragment_home, container, false)

        //bouton pour le pomodoro
        var startButton = view.findViewById<Button>(R.id.bouton_rebours)
        var pauseButton = view.findViewById<Button>(R.id.bouton_pause)
        var giveUpButton = view.findViewById<Button>(R.id.bouton_abandon)
        activityButton = view.findViewById<Button>(R.id.bouton_activite)
        var restartButton = view.findViewById<Button>(R.id.bouton_restart)

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

            //on refait apparaitre le bouton choisir une activite
            taskActivity.visibility = View.GONE
            activityButton.visibility = View.VISIBLE
        }


        activityButton?.setOnClickListener{
            //FolderPopup(TaskFolderAdapter(context, FolderRepository.Singleton.folderList, R.layout.item_folder, "TaskPopup")).show()
            FolderPopup(context, this).show()
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

    fun setTask(task: Task) {
        taskActivity = view.findViewById(R.id.taskActivity)
        activityButton.visibility = View.GONE
        taskActivity.visibility = View.VISIBLE

        taskActivity.name_task.text = task.name.toString()
        val newColor = android.graphics.Color.parseColor(task.color) //convertir la couleur en un entier
        taskActivity.icon_item.backgroundTintList = ColorStateList.valueOf(newColor) //change la couleur du fond de l'icone
        taskActivity.totalTaskScore.text = task.totalTask.toString()
        taskActivity.timeTask.text = task.time.toString()

    }



}