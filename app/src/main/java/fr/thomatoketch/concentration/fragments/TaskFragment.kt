package fr.thomatoketch.concentration.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.thomatoketch.concentration.Communicator
import fr.thomatoketch.concentration.MainActivity
import fr.thomatoketch.concentration.R
import fr.thomatoketch.concentration.TaskItemClickListener
import fr.thomatoketch.concentration.adapter.TaskAdapter
import fr.thomatoketch.concentration.data.Task
import fr.thomatoketch.concentration.data.ViewModel
import fr.thomatoketch.concentration.utils.SpacingitemDecorator
import kotlinx.android.synthetic.main.fragment_task.view.backButton
import kotlinx.android.synthetic.main.fragment_task.view.floatingActionButton
import java.io.Serializable

class TaskFragment(private val context: MainActivity, val folderId: Int): Fragment(), TaskItemClickListener {

    //TODO("Enlever entrée folderId et utiliser viewModel a la place")
    private lateinit var viewModel: ViewModel
    private lateinit var communicator: Communicator
    private lateinit var totalTime: TextView
    private lateinit var taskFinished: TextView
    private lateinit var taskRemaining: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_task, container, false)

        val adapter = TaskAdapter(context, this)
        val verticalRecyclerView = view?.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView?.adapter = adapter
        verticalRecyclerView?.layoutManager = LinearLayoutManager(context)

        viewModel = ViewModelProvider(context).get(ViewModel::class.java)
        viewModel.readAllTaskByFolder?.observe(viewLifecycleOwner, Observer { task ->
            Log.d("TAG", "voir si bon id ${task}")
            adapter.setData(task[0].task)
            adapter.defineColor(task[0].folder.color)
        })

        //met des espaces entre les items
        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider, null)?.let {
            dividerItemDecoration.setDrawable(it)
        }
        verticalRecyclerView?.addItemDecoration(dividerItemDecoration)

        val itemMargin = SpacingitemDecorator()
        verticalRecyclerView?.addItemDecoration(itemMargin)

        val textTitle = view.findViewById<TextView>(R.id.textView)

        //Afficher les statistiques et le bon titre avec le nom du dossier
        totalTime = view.findViewById(R.id.text_current_time)
        taskFinished = view.findViewById(R.id.text_current_task_finished)
        taskRemaining = view.findViewById(R.id.text_current_task_remaining)

        viewModel.getFolderInfoById(folderId).observe(context, Observer { data ->
            Log.d("TAG", "$data")
            if (data != null) {
                textTitle.text = data.name
                totalTime.text = longToHour(data.time)
                taskFinished.text = data.task_finished.toString()
                taskRemaining.text = data.task_remaining.toString()
            }
        })

        view.floatingActionButton.setOnClickListener {
            //Apparition de la page pour creer une tache
            val transaction = context.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, TaskAddFragment(context, folderId))
            transaction.addToBackStack(null)
            transaction.commit()
        }

        view.backButton.setOnClickListener {
            context.onBackPressed();
        }



        return view
    }

    override fun onTaskItemClick(task: Task) {

        communicator = activity as Communicator
        communicator.passData(task)
    }

    private fun longToHour(millisUntilFinished: Long): String {
        // Convertir un Long en un temps en format 00H00
        //val minutes = (millisUntilFinished / 1000) / 60 //TODO changer ici quand on remettra des minutes au lieu des secondes
        //val secondes = (millisUntilFinished / 1000) % 60
        val minutes = (millisUntilFinished) / 60
        val secondes = (millisUntilFinished) % 60
        return String.format("%02dH%02d", minutes, secondes)
    }

}