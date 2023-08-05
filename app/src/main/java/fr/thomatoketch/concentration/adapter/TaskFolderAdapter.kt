package fr.thomatoketch.concentration.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.thomatoketch.concentration.MainActivity
import fr.thomatoketch.concentration.R
import fr.thomatoketch.concentration.TaskFolderModel

class TaskFolderAdapter(
    private val context: MainActivity,
    private val folderList: List<TaskFolderModel>,
    private  val layoutId: Int
) : RecyclerView.Adapter<TaskFolderAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //boite pour ranger tous les composants a controler dans fragment_task
        val iconFolder: ImageView? = view.findViewById(R.id.logo_item)
        val folderName: TextView? = view.findViewById(R.id.textView)
        val folderColor: View? = view.findViewById(R.id.icon_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        //nombre d'item à afficher dans notre liste
        return folderList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //recuperer les infos du fichiers (nom + icone + couleur)
        val currentFolder = folderList[position]

        //mettre à jour les infos du fichier
        holder.folderName?.text = currentFolder.name

        if (currentFolder.color != null) {
            val newColor = Color.parseColor(currentFolder.color)
            holder.folderColor?.backgroundTintList = ColorStateList.valueOf(newColor)

        }
    }


}