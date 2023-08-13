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
import fr.thomatoketch.concentration.TaskModel

class TaskAdapter(
    val context: MainActivity,
    private val taskList: List<TaskModel>,
    private  val layoutId: Int
) : RecyclerView.Adapter<TaskAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //boite pour ranger tous les composants a controler dans fragment_folder
        val taskName: TextView? = view.findViewById(R.id.name_task)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        //nombre d'item à afficher dans notre liste
        return taskList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //recuperer les infos du fichiers (nom + icone + couleur)
        val currentTask = taskList[position]

        //mettre à jour les infos du fichier

        holder.taskName?.text = currentTask.name
    }


}