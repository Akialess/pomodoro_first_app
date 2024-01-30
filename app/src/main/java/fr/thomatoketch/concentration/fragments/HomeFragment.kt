package fr.thomatoketch.concentration.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.TextView
import android.widget.Button
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fr.thomatoketch.concentration.MainActivity
import fr.thomatoketch.concentration.R
import fr.thomatoketch.concentration.FolderPopup
import fr.thomatoketch.concentration.data.Task
import fr.thomatoketch.concentration.data.ViewModel
import kotlinx.android.synthetic.main.fragment_home.view.name_task
import kotlinx.android.synthetic.main.item_task.view.finishTaskScore
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

    private lateinit var viewModel: ViewModel

    private lateinit var view: View

    private lateinit var activityButton: Button
    private lateinit var taskActivity: View
    private lateinit var startButton: Button
    private lateinit var pauseButton: Button
    private lateinit var giveUpButton:Button
    private lateinit var restartButton: Button

    var currentTask: Task? = null


    //permet d'injecter le layout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        view = inflater.inflate(R.layout.fragment_home, container, false)
        viewModel = ViewModelProvider(context).get(ViewModel::class.java)

        //bouton pour le pomodoro
        startButton = view.findViewById<Button>(R.id.bouton_rebours)
        pauseButton = view.findViewById<Button>(R.id.bouton_pause)
        giveUpButton = view.findViewById<Button>(R.id.bouton_abandon)
        activityButton = view.findViewById<Button>(R.id.bouton_activite)
        restartButton = view.findViewById<Button>(R.id.bouton_restart)
        taskActivity = view.findViewById(R.id.taskActivity)

        //timer
        countDownTextView = view.findViewById(R.id.décompte) as TextView //je sais c'est bancal le bail

        startButton.setOnClickListener {

            if (taskActivity.visibility == View.GONE || taskActivity.visibility == View.INVISIBLE) {
                startTimer(3600) // Démarrer le compte à rebours
            }
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

            onDestroy()
            //on refait apparaitre le bouton choisir une activite
            taskActivity.visibility = View.GONE
            activityButton.visibility = View.VISIBLE
        }


        activityButton.setOnClickListener{
            FolderPopup(context, this).show()
        }

        return view
    }

    fun startTimer(secondes: Long) {

        startButton.visibility = View.GONE
        pauseButton.visibility = View.VISIBLE
        giveUpButton.visibility = View.VISIBLE

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
                //on incremente de 1 le finishedTask pour la tache faite
                currentTask?.let { updateTask(it) }
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
        countDownTextView.text = "00:00"
    }

    fun setTask(task: Task) {
        //modification layout
        currentTask = task

        activityButton.visibility = View.GONE
        taskActivity.visibility = View.VISIBLE

        taskActivity.name_task.text = task.name.toString()
        val newColor = android.graphics.Color.parseColor(task.color) //convertir la couleur en un entier
        taskActivity.icon_item.backgroundTintList = ColorStateList.valueOf(newColor) //change la couleur du fond de l'icone
        taskActivity.finishTaskScore.text = task.remainingTask.toString()
        taskActivity.totalTaskScore.text = task.totalTask.toString()
        taskActivity.timeTask.text = task.time.toString()

    }

    fun updateTask(task: Task) {
        //TODO("verifier input")
        val updatedTask = Task(
            task.id,
            task.name,
            task.folderId,
            task.color,
            task.time,
            task.remainingTask + 1,
            task.totalTask
        )

        viewModel.updateTask(updatedTask)
        //modif affichage
        taskActivity.finishTaskScore.text = (task.remainingTask + 1).toString()

        Toast.makeText(context, "Tâche finie", Toast.LENGTH_SHORT).show()
    }

}