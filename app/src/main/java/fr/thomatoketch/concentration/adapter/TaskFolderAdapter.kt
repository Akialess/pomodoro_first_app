
package fr.thomatoketch.concentration.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import fr.thomatoketch.concentration.FolderItemClickListener
import fr.thomatoketch.concentration.MainActivity
import fr.thomatoketch.concentration.R
import fr.thomatoketch.concentration.data.Folder
import fr.thomatoketch.concentration.data.ViewModel
import kotlinx.android.synthetic.main.item_folder_popup.view.icon_item
import kotlinx.android.synthetic.main.item_folder_popup.view.textView

class TaskFolderAdapter(val context: MainActivity, private val folderItemClickListener: FolderItemClickListener): RecyclerView.Adapter<TaskFolderAdapter.MyViewHolder>() {

    private var folderList = emptyList<Folder>()
    private lateinit var viewModel: ViewModel

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        //boite pour ranger tous les composants a controler dans fragment_folder
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_folder, parent, false))
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentFolder = folderList[position]
        holder.itemView.textView.text = currentFolder.name.toString()
        if (currentFolder.color != null) {
            val newColor = Color.parseColor(currentFolder.color) //convertir la couleur en un entier
            holder.itemView.icon_item?.backgroundTintList =
                ColorStateList.valueOf(newColor) //change la couleur du fond de l'icone

        }
        viewModel = ViewModelProvider(context).get(ViewModel::class.java)
        //holder.itemView.truc.text = currentFolder.color.toString()

        holder.itemView.setOnClickListener{
            //permet de différencier si l'adapter est dans le HomeFragment ou dans TaskFragment
            folderItemClickListener.onFolderItemClick(currentFolder.id)
        }
    }

    override fun getItemCount(): Int {
        return folderList.size
    }

    fun setData(folder: List<Folder>) {
        this.folderList = folder
        notifyDataSetChanged()
    }
}