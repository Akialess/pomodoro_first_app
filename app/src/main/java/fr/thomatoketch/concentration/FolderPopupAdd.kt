package fr.thomatoketch.concentration

import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import android.view.Window
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.thomatoketch.concentration.adapter.ColorAdapter
import fr.thomatoketch.concentration.data.Folder
import fr.thomatoketch.concentration.data.ViewModel
import fr.thomatoketch.concentration.data.Task
import kotlinx.android.synthetic.main.popup_add_folder.add_btn
import kotlinx.android.synthetic.main.popup_add_folder.edName

class FolderPopupAdd(private val context: MainActivity
) : Dialog(context) {

    private lateinit var viewModel: ViewModel
    private lateinit var adapter: ColorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_add_folder)

        adapter = ColorAdapter(context)
        val recyclerView = findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        viewModel = ViewModelProvider(context).get(ViewModel::class.java)

        add_btn.setOnClickListener{
            insertDataToDatabase()
            //insertDataToDatabaseTest()
        }

    }

    private fun insertDataToDatabase() {
        val name = edName.text.toString()
        val color = adapter.getSelectedColor().color


        if (inputCheck(name, color)){
            //Create folder object
            val folder = Folder(0, name, color)
            //Add data to database
            viewModel.addFolder(folder)
            Toast.makeText(context, "Dossier créé", Toast.LENGTH_SHORT).show()
            //Enlever popup
            dismiss()

        } else {
            Toast.makeText(context, "Veuillez donner un nom", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(name: String, color: String): Boolean{
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(color))
    }

    private fun insertDataToDatabaseTest() {
        val name = edName.text.toString()

        //Create folder object
        val task = Task(0, name, 2, "#FFFFFF", 10, 0, 0)
        //Add data to database
        viewModel.addTask(task)
        Toast.makeText(context, "Test créé", Toast.LENGTH_SHORT).show()
        //Enlever popup
        dismiss()

    }
}