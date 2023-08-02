package fr.thomatoketch.concentration.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import fr.thomatoketch.concentration.R

class TaskAdapter(private  val layoutId: Int) : RecyclerView.Adapter<TaskAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //boite pour ranger tous les composants a controler dans fragment_task
        val iconTask = view.findViewById<ImageView>(R.id.logo_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        //nombre d'item à afficher dans notre liste
        return 15
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {}


}