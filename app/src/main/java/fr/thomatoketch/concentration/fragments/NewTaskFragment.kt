package fr.thomatoketch.concentration.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.thomatoketch.concentration.MainActivity
import fr.thomatoketch.concentration.R
import fr.thomatoketch.concentration.adapter.NewTaskAdapter
import fr.thomatoketch.concentration.data.ViewModel
import fr.thomatoketch.concentration.utils.SpacingitemDecorator

class NewTaskFragment(private val context: MainActivity): Fragment() {

    private lateinit var viewModel: ViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_task, container, false)

        val adapter = NewTaskAdapter(context)
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

        return view
    }


}