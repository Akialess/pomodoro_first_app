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
import kotlinx.android.synthetic.main.popup_add_folder.edName
import kotlinx.android.synthetic.main.popup_edit_folder.cancel_btn
import kotlinx.android.synthetic.main.popup_edit_folder.save_btn

class FolderPopupEdit (private val context: MainActivity, private val folderId: Int
) : Dialog(context) {

    private lateinit var viewModel: ViewModel
    private lateinit var adapter: ColorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_edit_folder)

        //affiche l'option pour choisir une couleur
        adapter = ColorAdapter(context)
        val recyclerView = findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        viewModel = ViewModelProvider(context).get(ViewModel::class.java)

        //Bouton ajouter
        save_btn.setOnClickListener {
            updateDataToDatabase(folderId)
        }

        cancel_btn.setOnClickListener {
            dismiss()
        }

    }

    private fun updateDataToDatabase(folderId: Int) {
        //Recupere les options mis par l'utilisateur
        val name = edName.text.toString()
        val color = adapter.getSelectedColor().color


        if (inputCheck(name, color)){
            //Create folder object
            val folder = Folder(folderId, name, color)
            //Add data to database
            viewModel.updateFolder(folder)
            Toast.makeText(context, "Dossier modifié", Toast.LENGTH_SHORT).show()
            //Enlever popup
            dismiss()
        } else {
            Toast.makeText(context, "Veuillez donner un nom", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(name: String, color: String): Boolean{
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(color))
    }

}