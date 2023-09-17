package fr.thomatoketch.concentration

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.thomatoketch.concentration.adapter.TaskAdapter
import fr.thomatoketch.concentration.adapter.TaskFolderAdapter
import fr.thomatoketch.concentration.data.Task
import fr.thomatoketch.concentration.data.ViewModel
import fr.thomatoketch.concentration.fragments.HomeFragment

class FolderPopup(private val context: MainActivity, private val homePage: HomeFragment): Dialog(context), FolderItemClickListener, TaskItemClickListener {

    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_folder)

        //mise en place de l'adapter
        val adapter = TaskFolderAdapter(context, this)
        val recyclerView = findViewById<RecyclerView>(R.id.vertical_recycler_view)
        recyclerView.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(context)

        // Observer la liste de dossiers et mettre à jour l'adapter
        viewModel = ViewModelProvider(context).get(ViewModel::class.java)
        viewModel.readAllData.observe(context, Observer{ folderList ->
            adapter.setData(folderList)
        })
    }

    override fun onFolderItemClick(folderId: Int) {
        Log.d("TAG", "id folder popup : $folderId")
        val adapter = TaskAdapter(context, this)
        val recyclerView = findViewById<RecyclerView>(R.id.vertical_recycler_view)
        recyclerView.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(context)

        // Observer la liste de dossiers et mettre à jour l'adapter

        viewModel.getTaskByFolder(folderId)
        viewModel = ViewModelProvider(context).get(ViewModel::class.java)
        viewModel.readAllTaskByFolder?.observe(context, Observer { task ->
            Log.d("TAG", "${task[0].task}")
            adapter.setData(task[0].task)
        })

        //afficher le nom du fichier sur la popup
        val textPopup = findViewById<TextView>(R.id.textView_name_folder_option)
        viewModel.getFolderInfoById(folderId).observe(context, Observer { data ->
            Log.d("TAG", "$data")
            textPopup.text = data.name
        })
    }

    override fun onTaskItemClick(task: Task) {
        homePage.setTask(task)
        homePage.startTimer(task.time.toLong())

        dismiss()
    }
}