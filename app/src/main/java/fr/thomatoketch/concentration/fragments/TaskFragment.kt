package fr.thomatoketch.concentration.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.thomatoketch.concentration.MainActivity
import fr.thomatoketch.concentration.R
import fr.thomatoketch.concentration.adapter.TaskAdapter
import fr.thomatoketch.concentration.data.ViewModel
import fr.thomatoketch.concentration.utils.SpacingitemDecorator
import kotlinx.android.synthetic.main.fragment_task.view.floatingActionButton

class TaskFragment(private val context: MainActivity, val folderId: Int): Fragment() {

    //TODO("Enlever entrée folderId et utiliser viewModel a la place")
    private lateinit var viewModel: ViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_task, container, false)

        val adapter = TaskAdapter(context)
        val verticalRecyclerView = view?.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView?.adapter = adapter
        verticalRecyclerView?.layoutManager = LinearLayoutManager(context)

        viewModel = ViewModelProvider(context).get(ViewModel::class.java)
        viewModel.readAllTaskByFolder?.observe(viewLifecycleOwner, Observer { task ->
            Log.d("TAG", "voir si bon id ${task}")
            adapter.setData(task[0].task)
        })

        //met des espaces entre les items
        var dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider, null)?.let {
            dividerItemDecoration.setDrawable(it)
        }
        verticalRecyclerView?.addItemDecoration(dividerItemDecoration)

        val itemMargin = SpacingitemDecorator()
        verticalRecyclerView?.addItemDecoration(itemMargin)


        val textTitle = view.findViewById<TextView>(R.id.textView)
        //TODO("recuperer les infos du fichier pour pouvoir personnaliser la page Task et modifier folderId en dessous")
        viewModel.getFolderInfoById(folderId).observe(context, Observer { data ->
            Log.d("TAG", "$data")
            textTitle.text = data.name
        })

        view.floatingActionButton.setOnClickListener {
            val transaction = context.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, TaskAddFragment(context, folderId))
            transaction.addToBackStack(null)
            transaction.commit()

            Toast.makeText(context, "Bouton cliqué", Toast.LENGTH_SHORT).show()
        }


        return view
    }
}