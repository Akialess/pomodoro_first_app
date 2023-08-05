package fr.thomatoketch.concentration.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.thomatoketch.concentration.FolderRepository.Singleton.folderList
import fr.thomatoketch.concentration.MainActivity
import fr.thomatoketch.concentration.R
import fr.thomatoketch.concentration.TaskFolderModel
import fr.thomatoketch.concentration.adapter.TaskFolderAdapter

class TaskFragment(private val context: MainActivity) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_task, container, false)

        //ne pas oublier d'ajouter l'icone



        //afficher les dossiers en mode vertical
        val verticalRecyclerView = view?.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        Log.d("TAG", "here in task fragment for the view")
        printArrayListToLog(folderList)
        Log.d("TAG", "here in task fragment after the print of list for the view")
        verticalRecyclerView?.adapter = TaskFolderAdapter(folderList, R.layout.item_task) //folderlist vient du singleton

        return view
    }
    fun printArrayListToLog(list: ArrayList<TaskFolderModel>) {
        val lenofList = list.size
        Log.d("TAG", "Longueur liste : $lenofList")
        for (item in list) {
            Log.d("TAG", item.name)
        }
    }

}