package fr.thomatoketch.concentration.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shawnlin.numberpicker.NumberPicker
import fr.thomatoketch.concentration.MainActivity
import fr.thomatoketch.concentration.R
import fr.thomatoketch.concentration.data.Task
import fr.thomatoketch.concentration.data.ViewModel
import kotlinx.android.synthetic.main.fragment_task_add.view.floatingActionButton
import kotlinx.android.synthetic.main.popup_add_folder.edName

class TaskAddFragment(private val context: MainActivity, private val folderId: Int): Fragment() {

    private lateinit var viewModel: ViewModel
    private lateinit var color: String
    var numberTask: Int = 3
    var time: Int = 15
    var breakTime: Int = 5

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_task_add, container, false)

        viewModel = ViewModelProvider(context).get(ViewModel::class.java)
        viewModel.getFolderInfoById(folderId).observe(context, Observer { data ->
            color = data.color
        })

        val numberTaskPicker = view.findViewById<NumberPicker>(R.id.task_number_picker)
        val timePicker = view.findViewById<NumberPicker>(R.id.time_number_picker)
        val breakPicker = view.findViewById<NumberPicker>(R.id.break_number_picker)

        //Recupere les infos des "numberpicker"
        numberTaskPicker.setOnValueChangedListener{ vTaskPicker, oldValue, newValue ->
            numberTask = newValue
        }

        timePicker.setOnValueChangedListener{ vTimePicker, oldValue, newValue ->
            time = newValue
        }

        breakPicker.setOnValueChangedListener{ vBreakPicker, oldValue, newValue ->
            breakTime = newValue
        }

        view.floatingActionButton.setOnClickListener {
            val name = edName.text.toString()
            //TODO("Récupérer les infos de la case description et ajouter une partie description dans la database")

            //Create task object
            val task = Task(0, name, folderId, color, time, 0, numberTask)
            //Add data to database
            viewModel.addTask(task)

            //Retourne sur la page precedente (TaskPage dossier correspondant)
            val transaction = context.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, TaskFragment(context, folderId))
            transaction.addToBackStack(null)
            transaction.commit()

            Toast.makeText(context, "Tâche créé avec succès", Toast.LENGTH_SHORT).show()

        }

        return view
    }
}