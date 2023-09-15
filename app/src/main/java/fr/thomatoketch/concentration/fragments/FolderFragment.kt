package fr.thomatoketch.concentration.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.thomatoketch.concentration.FolderItemClickListener
import fr.thomatoketch.concentration.FolderPopupAdd
import fr.thomatoketch.concentration.MainActivity
import fr.thomatoketch.concentration.R
import fr.thomatoketch.concentration.adapter.TaskFolderAdapter
import fr.thomatoketch.concentration.data.ViewModel
import kotlinx.android.synthetic.main.fragment_folder.view.floatingActionButton

class FolderFragment(private val context: MainActivity) : Fragment(), FolderItemClickListener {

    private lateinit var viewModel: ViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_folder, container, false)

        //ne pas oublier d'ajouter l'icone

        //afficher les dossiers en mode vertical
        val adapter = TaskFolderAdapter(context, this)
        val verticalRecyclerView = view?.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView?.adapter = adapter
        verticalRecyclerView?.layoutManager = LinearLayoutManager(context)

        viewModel = ViewModelProvider(context).get(ViewModel::class.java)
        viewModel.readAllData.observe(viewLifecycleOwner, Observer { folder ->
            adapter.setData(folder)
        })


        //Bouton pour ajouter des fichiers
        view.floatingActionButton.setOnClickListener {
            FolderPopupAdd(context).show()
        }

        return view
    }

    override fun onFolderItemClick(folderId: Int) {
        //Log.d("TAG", "id of folder : ${folderId}")
        viewModel.getTaskByFolder(folderId)
        val transaction = context.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, TaskFragment(context, folderId))
        transaction.addToBackStack(null)
        transaction.commit()

    }
}