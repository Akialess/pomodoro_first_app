package fr.thomatoketch.concentration.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.thomatoketch.concentration.R
import fr.thomatoketch.concentration.data.Folder
import kotlinx.android.synthetic.main.item_folder_popup.view.textView

class NewTaskFolderAdapter: RecyclerView.Adapter<NewTaskFolderAdapter.MyViewHolder>() {

    private var folderList = emptyList<Folder>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_folder, parent, false))
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentFolder = folderList[position]
        holder.itemView.textView.text = currentFolder.name.toString()
        //holder.itemView.truc.text = currentFolder.color.toString()
    }

    override fun getItemCount(): Int {
        return folderList.size
    }

    fun setData(folder: List<Folder>) {
        this.folderList = folder
        notifyDataSetChanged()
    }
}