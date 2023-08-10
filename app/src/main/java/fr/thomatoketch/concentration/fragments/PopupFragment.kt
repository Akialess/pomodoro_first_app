package fr.thomatoketch.concentration.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.thomatoketch.concentration.FolderRepository.Singleton.folderList
import fr.thomatoketch.concentration.MainActivity
import fr.thomatoketch.concentration.R
import fr.thomatoketch.concentration.adapter.TaskFolderAdapter

class PopupFragment(private val context: MainActivity) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.popup_folder, container, false)

        //ne pas oublier d'ajouter l'icone

        //afficher les dossiers en mode vertical
        val verticalRecyclerView = view?.findViewById<RecyclerView>(R.id.vertical_recycler_view)

        verticalRecyclerView?.adapter = TaskFolderAdapter(context, folderList, R.layout.item_folder) //folderlist vient du singleton

        return view
    }
}