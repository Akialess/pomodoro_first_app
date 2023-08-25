package fr.thomatoketch.concentration.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.thomatoketch.concentration.FolderPopupAdd
import fr.thomatoketch.concentration.MainActivity
import fr.thomatoketch.concentration.R
import fr.thomatoketch.concentration.adapter.NewTaskFolderAdapter
import fr.thomatoketch.concentration.data.FolderViewModel
import kotlinx.android.synthetic.main.fragment_folder.view.floatingActionButton

class FolderFragment(private val context: MainActivity) : Fragment() {

    private lateinit var folderViewModel: FolderViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_folder, container, false)

        //ne pas oublier d'ajouter l'icone

        //afficher les dossiers en mode vertical
        val adapter = NewTaskFolderAdapter()
        val verticalRecyclerView = view?.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView?.adapter = adapter
        verticalRecyclerView?.layoutManager = LinearLayoutManager(context)
        //verticalRecyclerView?.adapter = TaskFolderAdapter(context, folderList, R.layout.item_folder, "TaskFragment")

        folderViewModel = ViewModelProvider(context).get(FolderViewModel::class.java)
        folderViewModel.readAllData.observe(viewLifecycleOwner, Observer { folder ->
            adapter.setData(folder)
        })



        view.floatingActionButton.setOnClickListener {
            Log.d("TAG", "le bouton a bien fonctionne")
            FolderPopupAdd(context).show()
        }

        return view
    }
}