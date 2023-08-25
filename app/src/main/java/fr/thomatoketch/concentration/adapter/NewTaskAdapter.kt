package fr.thomatoketch.concentration.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.thomatoketch.concentration.R
import fr.thomatoketch.concentration.data.Task
import kotlinx.android.synthetic.main.item_folder_popup.view.textView

class NewTaskAdapter: RecyclerView.Adapter<NewTaskAdapter.MyViewHolder>(){
    private var taskList = emptyList<Task>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentFolder = taskList[position]
        holder.itemView.textView.text = currentFolder.name.toString()
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun setData(task: List<Task>) {
        this.taskList = task
        notifyDataSetChanged()
    }
}