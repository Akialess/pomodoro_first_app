package fr.thomatoketch.concentration.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import fr.thomatoketch.concentration.MainActivity
import fr.thomatoketch.concentration.R
import fr.thomatoketch.concentration.adapter.TaskAdapter
import fr.thomatoketch.concentration.utils.SpacingitemDecorator

class TaskFragment(private val context: MainActivity, private val adapter: TaskAdapter) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_task, container, false)

        val verticalRecyclerView = view?.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView?.adapter = adapter

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