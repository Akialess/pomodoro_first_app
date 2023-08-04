package fr.thomatoketch.concentration.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.thomatoketch.concentration.MainActivity
import fr.thomatoketch.concentration.R
import fr.thomatoketch.concentration.TaskFolderModel
import fr.thomatoketch.concentration.adapter.TaskFolderAdapter

class TaskFragment(private val context: MainActivity) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_task, container, false)

        //creer une liste qui va stocker ces plants
        val folderList = arrayListOf<TaskFolderModel>()

        //ne pas oublier d'ajouter l'icone



        //afficher les dossiers en mode vertical
        val verticalRecyclerView = view?.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView?.adapter = TaskFolderAdapter(folderList, R.layout.item_task)

        return view
    }

}