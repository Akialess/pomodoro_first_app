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
import fr.thomatoketch.concentration.MainActivity
import fr.thomatoketch.concentration.R
import fr.thomatoketch.concentration.TaskItemClickListener
import fr.thomatoketch.concentration.adapter.TaskAdapter
import fr.thomatoketch.concentration.data.ViewModel
import fr.thomatoketch.concentration.utils.SpacingitemDecorator
import kotlinx.android.synthetic.main.fragment_task.view.backButton
import kotlinx.android.synthetic.main.fragment_task.view.floatingActionButton

class TaskFragment(private val context: MainActivity, val folderId: Int): Fragment(), TaskItemClickListener {

    //TODO("Enlever entrée folderId et utiliser viewModel a la place")
    private lateinit var viewModel: ViewModel
    private lateinit var backButton: Button

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

        //met le bon titre avec le nom du fichier
        viewModel.getFolderInfoById(folderId).observe(context, Observer { data ->
            Log.d("TAG", "$data")
            textTitle.text = data.name
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
}