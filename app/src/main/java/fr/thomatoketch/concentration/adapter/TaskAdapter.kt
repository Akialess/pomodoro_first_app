package fr.thomatoketch.concentration.adapter

import android.content.res.ColorStateList
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.thomatoketch.concentration.MainActivity
import fr.thomatoketch.concentration.R
import fr.thomatoketch.concentration.TaskItemClickListener
import fr.thomatoketch.concentration.data.Task
import kotlinx.android.synthetic.main.item_task.view.finishTaskScore
import kotlinx.android.synthetic.main.item_task.view.icon_item
import kotlinx.android.synthetic.main.item_task.view.name_task
import kotlinx.android.synthetic.main.item_task.view.timeTask
import kotlinx.android.synthetic.main.item_task.view.totalTaskScore

class TaskAdapter(private val context: MainActivity, private val taskItemClickListener: TaskItemClickListener): RecyclerView.Adapter<TaskAdapter.MyViewHolder>(){
    private var taskList = emptyList<Task>()
    private var color = "2457C5"
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentTask = taskList[position]

        holder.itemView.name_task.text = currentTask.name.toString()

        val newColor = android.graphics.Color.parseColor(this.color) //convertir la couleur en un entier

        holder.itemView.icon_item.backgroundTintList = ColorStateList.valueOf(newColor) //change la couleur du fond de l'icone

        holder.itemView.finishTaskScore.text = currentTask.remainingTask.toString()

        holder.itemView.totalTaskScore.text = currentTask.totalTask.toString()

        holder.itemView.timeTask.text = currentTask.time.toString()

        //quand on clique sur l'item
        holder.itemView.setOnClickListener {
            //permet de differencier si on est dans FolderPopup ou TaskFragment
            Log.d("TAG", "etape 1 voir si vide : ${currentTask.name}")
            taskItemClickListener.onTaskItemClick(currentTask)
        }

    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun setData(task: List<Task>) {
        this.taskList = task
        this.taskList = taskList.filter { it.remainingTask != it.totalTask }
        Log.d("TAG", "${taskList}")
        notifyDataSetChanged()
    }

    fun defineColor(color: String) {
        this.color = color
        notifyDataSetChanged()
    }

}