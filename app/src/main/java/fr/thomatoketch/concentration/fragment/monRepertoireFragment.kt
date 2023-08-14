package fr.thomatoketch.concentration.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.thomatoketch.concentration.R
import fr.thomatoketch.concentration.adapter.ActivityAdapter

class monRepertoireFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.mon_repertoire, container, false)

        //recuperer le recyclerView
        //val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        //verticalRecyclerView.adapter = ActivityAdapter(R.layout.item_vertical_repertoire)
    }


}